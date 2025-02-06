package cdm.event.common.tabulator;

import cdm.event.common.TradeState;
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


@ImplementedBy(TradeStateTypeTabulator.Impl.class)
public interface TradeStateTypeTabulator extends Tabulator<TradeState> {
	@Singleton
	class Impl implements TradeStateTypeTabulator {
		private final Field tradeField;
		private final Field stateField;
		private final Field resetHistoryField;
		private final Field transferHistoryField;
		private final Field observationHistoryField;
		private final Field valuationHistoryField;

		private final TradeTypeTabulator tradeTypeTabulator;
		private final StateTypeTabulator stateTypeTabulator;
		private final ResetTypeTabulator resetTypeTabulator;
		private final TransferStateTypeTabulator transferStateTypeTabulator;
		private final ObservationEventTypeTabulator observationEventTypeTabulator;
		private final ValuationTypeTabulator valuationTypeTabulator;

		@Inject
		public Impl(TradeTypeTabulator tradeTypeTabulator, StateTypeTabulator stateTypeTabulator, ResetTypeTabulator resetTypeTabulator, TransferStateTypeTabulator transferStateTypeTabulator, ObservationEventTypeTabulator observationEventTypeTabulator, ValuationTypeTabulator valuationTypeTabulator) {
			this.tradeTypeTabulator = tradeTypeTabulator;
			this.stateTypeTabulator = stateTypeTabulator;
			this.resetTypeTabulator = resetTypeTabulator;
			this.transferStateTypeTabulator = transferStateTypeTabulator;
			this.observationEventTypeTabulator = observationEventTypeTabulator;
			this.valuationTypeTabulator = valuationTypeTabulator;
			this.tradeField = new FieldImpl(
				"trade",
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
			this.resetHistoryField = new FieldImpl(
				"resetHistory",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferHistoryField = new FieldImpl(
				"transferHistory",
				true,
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
		public List<FieldValue> tabulate(TradeState input) {
			FieldValue trade = Optional.ofNullable(input.getTrade())
				.map(x -> new NestedFieldValueImpl(tradeField, Optional.of(tradeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(tradeField, Optional.empty()));
			FieldValue state = Optional.ofNullable(input.getState())
				.map(x -> new NestedFieldValueImpl(stateField, Optional.of(stateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(stateField, Optional.empty()));
			FieldValue resetHistory = Optional.ofNullable(input.getResetHistory())
				.map(x -> x.stream()
					.map(_x -> resetTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(resetHistoryField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(resetHistoryField, Optional.empty()));
			FieldValue transferHistory = Optional.ofNullable(input.getTransferHistory())
				.map(x -> x.stream()
					.map(_x -> transferStateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(transferHistoryField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(transferHistoryField, Optional.empty()));
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
				trade,
				state,
				resetHistory,
				transferHistory,
				observationHistory,
				valuationHistory
			);
		}
	}
}
