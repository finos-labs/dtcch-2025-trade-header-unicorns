package cdm.event.common.tabulator;

import cdm.event.common.PrimitiveInstruction;
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


@ImplementedBy(PrimitiveInstructionTypeTabulator.Impl.class)
public interface PrimitiveInstructionTypeTabulator extends Tabulator<PrimitiveInstruction> {
	@Singleton
	class Impl implements PrimitiveInstructionTypeTabulator {
		private final Field contractFormationField;
		private final Field executionField;
		private final Field exerciseField;
		private final Field partyChangeField;
		private final Field quantityChangeField;
		private final Field resetField;
		private final Field splitField;
		private final Field termsChangeField;
		private final Field transferField;
		private final Field indexTransitionField;
		private final Field stockSplitField;
		private final Field observationField;
		private final Field valuationField;

		private final ContractFormationInstructionTypeTabulator contractFormationInstructionTypeTabulator;
		private final ExecutionInstructionTypeTabulator executionInstructionTypeTabulator;
		private final ExerciseInstructionTypeTabulator exerciseInstructionTypeTabulator;
		private final PartyChangeInstructionTypeTabulator partyChangeInstructionTypeTabulator;
		private final QuantityChangeInstructionTypeTabulator quantityChangeInstructionTypeTabulator;
		private final ResetInstructionTypeTabulator resetInstructionTypeTabulator;
		private final SplitInstructionTypeTabulator splitInstructionTypeTabulator;
		private final TermsChangeInstructionTypeTabulator termsChangeInstructionTypeTabulator;
		private final TransferInstructionTypeTabulator transferInstructionTypeTabulator;
		private final IndexTransitionInstructionTypeTabulator indexTransitionInstructionTypeTabulator;
		private final StockSplitInstructionTypeTabulator stockSplitInstructionTypeTabulator;
		private final ObservationInstructionTypeTabulator observationInstructionTypeTabulator;
		private final ValuationInstructionTypeTabulator valuationInstructionTypeTabulator;

		@Inject
		public Impl(ContractFormationInstructionTypeTabulator contractFormationInstructionTypeTabulator, ExecutionInstructionTypeTabulator executionInstructionTypeTabulator, ExerciseInstructionTypeTabulator exerciseInstructionTypeTabulator, PartyChangeInstructionTypeTabulator partyChangeInstructionTypeTabulator, QuantityChangeInstructionTypeTabulator quantityChangeInstructionTypeTabulator, ResetInstructionTypeTabulator resetInstructionTypeTabulator, SplitInstructionTypeTabulator splitInstructionTypeTabulator, TermsChangeInstructionTypeTabulator termsChangeInstructionTypeTabulator, TransferInstructionTypeTabulator transferInstructionTypeTabulator, IndexTransitionInstructionTypeTabulator indexTransitionInstructionTypeTabulator, StockSplitInstructionTypeTabulator stockSplitInstructionTypeTabulator, ObservationInstructionTypeTabulator observationInstructionTypeTabulator, ValuationInstructionTypeTabulator valuationInstructionTypeTabulator) {
			this.contractFormationInstructionTypeTabulator = contractFormationInstructionTypeTabulator;
			this.executionInstructionTypeTabulator = executionInstructionTypeTabulator;
			this.exerciseInstructionTypeTabulator = exerciseInstructionTypeTabulator;
			this.partyChangeInstructionTypeTabulator = partyChangeInstructionTypeTabulator;
			this.quantityChangeInstructionTypeTabulator = quantityChangeInstructionTypeTabulator;
			this.resetInstructionTypeTabulator = resetInstructionTypeTabulator;
			this.splitInstructionTypeTabulator = splitInstructionTypeTabulator;
			this.termsChangeInstructionTypeTabulator = termsChangeInstructionTypeTabulator;
			this.transferInstructionTypeTabulator = transferInstructionTypeTabulator;
			this.indexTransitionInstructionTypeTabulator = indexTransitionInstructionTypeTabulator;
			this.stockSplitInstructionTypeTabulator = stockSplitInstructionTypeTabulator;
			this.observationInstructionTypeTabulator = observationInstructionTypeTabulator;
			this.valuationInstructionTypeTabulator = valuationInstructionTypeTabulator;
			this.contractFormationField = new FieldImpl(
				"contractFormation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.executionField = new FieldImpl(
				"execution",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseField = new FieldImpl(
				"exercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyChangeField = new FieldImpl(
				"partyChange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityChangeField = new FieldImpl(
				"quantityChange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetField = new FieldImpl(
				"reset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.splitField = new FieldImpl(
				"split",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.termsChangeField = new FieldImpl(
				"termsChange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferField = new FieldImpl(
				"transfer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexTransitionField = new FieldImpl(
				"indexTransition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stockSplitField = new FieldImpl(
				"stockSplit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationField = new FieldImpl(
				"observation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationField = new FieldImpl(
				"valuation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PrimitiveInstruction input) {
			FieldValue contractFormation = Optional.ofNullable(input.getContractFormation())
				.map(x -> new NestedFieldValueImpl(contractFormationField, Optional.of(contractFormationInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contractFormationField, Optional.empty()));
			FieldValue execution = Optional.ofNullable(input.getExecution())
				.map(x -> new NestedFieldValueImpl(executionField, Optional.of(executionInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executionField, Optional.empty()));
			FieldValue exercise = Optional.ofNullable(input.getExercise())
				.map(x -> new NestedFieldValueImpl(exerciseField, Optional.of(exerciseInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseField, Optional.empty()));
			FieldValue partyChange = Optional.ofNullable(input.getPartyChange())
				.map(x -> new NestedFieldValueImpl(partyChangeField, Optional.of(partyChangeInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyChangeField, Optional.empty()));
			FieldValue quantityChange = Optional.ofNullable(input.getQuantityChange())
				.map(x -> new NestedFieldValueImpl(quantityChangeField, Optional.of(quantityChangeInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityChangeField, Optional.empty()));
			FieldValue reset = Optional.ofNullable(input.getReset())
				.map(x -> new NestedFieldValueImpl(resetField, Optional.of(resetInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetField, Optional.empty()));
			FieldValue split = Optional.ofNullable(input.getSplit())
				.map(x -> new NestedFieldValueImpl(splitField, Optional.of(splitInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(splitField, Optional.empty()));
			FieldValue termsChange = Optional.ofNullable(input.getTermsChange())
				.map(x -> new NestedFieldValueImpl(termsChangeField, Optional.of(termsChangeInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(termsChangeField, Optional.empty()));
			FieldValue transfer = Optional.ofNullable(input.getTransfer())
				.map(x -> new NestedFieldValueImpl(transferField, Optional.of(transferInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(transferField, Optional.empty()));
			FieldValue indexTransition = Optional.ofNullable(input.getIndexTransition())
				.map(x -> new NestedFieldValueImpl(indexTransitionField, Optional.of(indexTransitionInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indexTransitionField, Optional.empty()));
			FieldValue stockSplit = Optional.ofNullable(input.getStockSplit())
				.map(x -> new NestedFieldValueImpl(stockSplitField, Optional.of(stockSplitInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(stockSplitField, Optional.empty()));
			FieldValue observation = Optional.ofNullable(input.getObservation())
				.map(x -> new NestedFieldValueImpl(observationField, Optional.of(observationInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationField, Optional.empty()));
			FieldValue valuation = Optional.ofNullable(input.getValuation())
				.map(x -> new NestedFieldValueImpl(valuationField, Optional.of(valuationInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationField, Optional.empty()));
			return Arrays.asList(
				contractFormation,
				execution,
				exercise,
				partyChange,
				quantityChange,
				reset,
				split,
				termsChange,
				transfer,
				indexTransition,
				stockSplit,
				observation,
				valuation
			);
		}
	}
}
