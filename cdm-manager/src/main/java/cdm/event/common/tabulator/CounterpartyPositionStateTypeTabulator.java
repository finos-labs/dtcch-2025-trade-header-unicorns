package cdm.event.common.tabulator;

import cdm.event.common.CounterpartyPositionState;
import cdm.event.position.tabulator.CounterpartyPositionTypeTabulator;
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


@ImplementedBy(CounterpartyPositionStateTypeTabulator.Impl.class)
public interface CounterpartyPositionStateTypeTabulator extends Tabulator<CounterpartyPositionState> {
	@Singleton
	class Impl implements CounterpartyPositionStateTypeTabulator {
		private final Field counterpartyPositionField;
		private final Field stateField;
		private final Field observationHistoryField;
		private final Field valuationHistoryField;

		private final CounterpartyPositionTypeTabulator counterpartyPositionTypeTabulator;
		private final StateTypeTabulator stateTypeTabulator;
		private final ObservationEventTypeTabulator observationEventTypeTabulator;
		private final ValuationTypeTabulator valuationTypeTabulator;

		@Inject
		public Impl(CounterpartyPositionTypeTabulator counterpartyPositionTypeTabulator, StateTypeTabulator stateTypeTabulator, ObservationEventTypeTabulator observationEventTypeTabulator, ValuationTypeTabulator valuationTypeTabulator) {
			this.counterpartyPositionTypeTabulator = counterpartyPositionTypeTabulator;
			this.stateTypeTabulator = stateTypeTabulator;
			this.observationEventTypeTabulator = observationEventTypeTabulator;
			this.valuationTypeTabulator = valuationTypeTabulator;
			this.counterpartyPositionField = new FieldImpl(
				"counterpartyPosition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stateField = new FieldImpl(
				"state",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationHistoryField = new FieldImpl(
				"observationHistory",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationHistoryField = new FieldImpl(
				"valuationHistory",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CounterpartyPositionState input) {
			FieldValue counterpartyPosition = Optional.ofNullable(input.getCounterpartyPosition())
				.map(x -> new NestedFieldValueImpl(counterpartyPositionField, Optional.of(counterpartyPositionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(counterpartyPositionField, Optional.empty()));
			FieldValue state = Optional.ofNullable(input.getState())
				.map(x -> new NestedFieldValueImpl(stateField, Optional.of(stateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(stateField, Optional.empty()));
			FieldValue observationHistory = Optional.ofNullable(input.getObservationHistory())
				.map(x -> x.stream()
					.map(_x -> observationEventTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(observationHistoryField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(observationHistoryField, Optional.empty()));
			FieldValue valuationHistory = Optional.ofNullable(input.getValuationHistory())
				.map(x -> x.stream()
					.map(_x -> valuationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(valuationHistoryField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(valuationHistoryField, Optional.empty()));
			return Arrays.asList(
				counterpartyPosition,
				state,
				observationHistory,
				valuationHistory
			);
		}
	}
}
