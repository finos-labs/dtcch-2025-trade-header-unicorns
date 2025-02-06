package cdm.product.template.tabulator;

import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(OptionalEarlyTerminationAdjustedDatesTypeTabulator.Impl.class)
public interface OptionalEarlyTerminationAdjustedDatesTypeTabulator extends Tabulator<OptionalEarlyTerminationAdjustedDates> {
	@Singleton
	class Impl implements OptionalEarlyTerminationAdjustedDatesTypeTabulator {
		private final Field earlyTerminationEventField;

		private final EarlyTerminationEventTypeTabulator earlyTerminationEventTypeTabulator;

		@Inject
		public Impl(EarlyTerminationEventTypeTabulator earlyTerminationEventTypeTabulator) {
			this.earlyTerminationEventTypeTabulator = earlyTerminationEventTypeTabulator;
			this.earlyTerminationEventField = new FieldImpl(
				"earlyTerminationEvent",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OptionalEarlyTerminationAdjustedDates input) {
			FieldValue earlyTerminationEvent = Optional.ofNullable(input.getEarlyTerminationEvent())
				.map(x -> x.stream()
					.map(_x -> earlyTerminationEventTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(earlyTerminationEventField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(earlyTerminationEventField, Optional.empty()));
			return Arrays.asList(
				earlyTerminationEvent
			);
		}
	}
}
