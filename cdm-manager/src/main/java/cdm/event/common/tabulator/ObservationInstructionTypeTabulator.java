package cdm.event.common.tabulator;

import cdm.event.common.ObservationInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ObservationInstructionTypeTabulator.Impl.class)
public interface ObservationInstructionTypeTabulator extends Tabulator<ObservationInstruction> {
	@Singleton
	class Impl implements ObservationInstructionTypeTabulator {
		private final Field observationEventField;

		private final ObservationEventTypeTabulator observationEventTypeTabulator;

		@Inject
		public Impl(ObservationEventTypeTabulator observationEventTypeTabulator) {
			this.observationEventTypeTabulator = observationEventTypeTabulator;
			this.observationEventField = new FieldImpl(
				"observationEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationInstruction input) {
			FieldValue observationEvent = Optional.ofNullable(input.getObservationEvent())
				.map(x -> new NestedFieldValueImpl(observationEventField, Optional.of(observationEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationEventField, Optional.empty()));
			return Arrays.asList(
				observationEvent
			);
		}
	}
}
