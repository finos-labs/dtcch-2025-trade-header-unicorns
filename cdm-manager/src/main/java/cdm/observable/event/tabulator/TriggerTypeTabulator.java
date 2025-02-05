package cdm.observable.event.tabulator;

import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.observable.event.Trigger;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(TriggerTypeTabulator.Impl.class)
public interface TriggerTypeTabulator extends Tabulator<Trigger> {
	@Singleton
	class Impl implements TriggerTypeTabulator {
		private final Field levelField;
		private final Field creditEventsField;
		private final Field creditEventsReferenceField;
		private final Field triggerTypeField;
		private final Field triggerTimeTypeField;

		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;
		private final CreditEventsTypeTabulator creditEventsTypeTabulator;

		@Inject
		public Impl(PriceScheduleTypeTabulator priceScheduleTypeTabulator, CreditEventsTypeTabulator creditEventsTypeTabulator) {
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.creditEventsTypeTabulator = creditEventsTypeTabulator;
			this.levelField = new FieldImpl(
				"level",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditEventsField = new FieldImpl(
				"creditEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditEventsReferenceField = new FieldImpl(
				"creditEventsReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.triggerTypeField = new FieldImpl(
				"triggerType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.triggerTimeTypeField = new FieldImpl(
				"triggerTimeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Trigger input) {
			FieldValue level = Optional.ofNullable(input.getLevel())
				.map(x -> x.stream()
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(levelField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(levelField, Optional.empty()));
			FieldValue creditEvents = Optional.ofNullable(input.getCreditEvents())
				.map(x -> new NestedFieldValueImpl(creditEventsField, Optional.of(creditEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditEventsField, Optional.empty()));
			FieldValue creditEventsReference = Optional.ofNullable(input.getCreditEventsReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(creditEventsReferenceField, Optional.of(creditEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditEventsReferenceField, Optional.empty()));
			FieldValue triggerType = new FieldValueImpl(triggerTypeField, Optional.ofNullable(input.getTriggerType()));
			FieldValue triggerTimeType = new FieldValueImpl(triggerTimeTypeField, Optional.ofNullable(input.getTriggerTimeType()));
			return Arrays.asList(
				level,
				creditEvents,
				creditEventsReference,
				triggerType,
				triggerTimeType
			);
		}
	}
}
