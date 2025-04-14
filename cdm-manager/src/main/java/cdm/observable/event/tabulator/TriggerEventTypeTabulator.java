package cdm.observable.event.tabulator;

import cdm.base.datetime.tabulator.AveragingScheduleTypeTabulator;
import cdm.base.datetime.tabulator.DateListTypeTabulator;
import cdm.observable.event.TriggerEvent;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(TriggerEventTypeTabulator.Impl.class)
public interface TriggerEventTypeTabulator extends Tabulator<TriggerEvent> {
	@Singleton
	class Impl implements TriggerEventTypeTabulator {
		private final Field scheduleField;
		private final Field triggerDatesField;
		private final Field triggerField;
		private final Field featurePaymentField;

		private final AveragingScheduleTypeTabulator averagingScheduleTypeTabulator;
		private final DateListTypeTabulator dateListTypeTabulator;
		private final TriggerTypeTabulator triggerTypeTabulator;
		private final FeaturePaymentTypeTabulator featurePaymentTypeTabulator;

		@Inject
		public Impl(AveragingScheduleTypeTabulator averagingScheduleTypeTabulator, DateListTypeTabulator dateListTypeTabulator, TriggerTypeTabulator triggerTypeTabulator, FeaturePaymentTypeTabulator featurePaymentTypeTabulator) {
			this.averagingScheduleTypeTabulator = averagingScheduleTypeTabulator;
			this.dateListTypeTabulator = dateListTypeTabulator;
			this.triggerTypeTabulator = triggerTypeTabulator;
			this.featurePaymentTypeTabulator = featurePaymentTypeTabulator;
			this.scheduleField = new FieldImpl(
				"schedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.triggerDatesField = new FieldImpl(
				"triggerDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.triggerField = new FieldImpl(
				"trigger",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.featurePaymentField = new FieldImpl(
				"featurePayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TriggerEvent input) {
			FieldValue schedule = Optional.ofNullable(input.getSchedule())
				.map(x -> x.stream()
					.map(_x -> averagingScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(scheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(scheduleField, Optional.empty()));
			FieldValue triggerDates = Optional.ofNullable(input.getTriggerDates())
				.map(x -> new NestedFieldValueImpl(triggerDatesField, Optional.of(dateListTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(triggerDatesField, Optional.empty()));
			FieldValue trigger = Optional.ofNullable(input.getTrigger())
				.map(x -> new NestedFieldValueImpl(triggerField, Optional.of(triggerTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(triggerField, Optional.empty()));
			FieldValue featurePayment = Optional.ofNullable(input.getFeaturePayment())
				.map(x -> new NestedFieldValueImpl(featurePaymentField, Optional.of(featurePaymentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(featurePaymentField, Optional.empty()));
			return Arrays.asList(
				schedule,
				triggerDates,
				trigger,
				featurePayment
			);
		}
	}
}
