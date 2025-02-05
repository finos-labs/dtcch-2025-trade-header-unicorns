package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.BuyerSellerTypeTabulator;
import cdm.observable.asset.tabulator.CalculationAgentTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.OptionalEarlyTermination;
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


@ImplementedBy(OptionalEarlyTerminationTypeTabulator.Impl.class)
public interface OptionalEarlyTerminationTypeTabulator extends Tabulator<OptionalEarlyTermination> {
	@Singleton
	class Impl implements OptionalEarlyTerminationTypeTabulator {
		private final Field singlePartyOptionField;
		private final Field mutualEarlyTerminationField;
		private final Field exerciseNoticeField;
		private final Field followUpConfirmationField;
		private final Field calculationAgentField;
		private final Field cashSettlementField;
		private final Field optionalEarlyTerminationAdjustedDatesField;
		private final Field exerciseTermsField;

		private final BuyerSellerTypeTabulator buyerSellerTypeTabulator;
		private final ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator;
		private final CalculationAgentTypeTabulator calculationAgentTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final OptionalEarlyTerminationAdjustedDatesTypeTabulator optionalEarlyTerminationAdjustedDatesTypeTabulator;
		private final ExerciseTermsTypeTabulator exerciseTermsTypeTabulator;

		@Inject
		public Impl(BuyerSellerTypeTabulator buyerSellerTypeTabulator, ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator, CalculationAgentTypeTabulator calculationAgentTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, OptionalEarlyTerminationAdjustedDatesTypeTabulator optionalEarlyTerminationAdjustedDatesTypeTabulator, ExerciseTermsTypeTabulator exerciseTermsTypeTabulator) {
			this.buyerSellerTypeTabulator = buyerSellerTypeTabulator;
			this.exerciseNoticeTypeTabulator = exerciseNoticeTypeTabulator;
			this.calculationAgentTypeTabulator = calculationAgentTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.optionalEarlyTerminationAdjustedDatesTypeTabulator = optionalEarlyTerminationAdjustedDatesTypeTabulator;
			this.exerciseTermsTypeTabulator = exerciseTermsTypeTabulator;
			this.singlePartyOptionField = new FieldImpl(
				"singlePartyOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mutualEarlyTerminationField = new FieldImpl(
				"mutualEarlyTermination",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseNoticeField = new FieldImpl(
				"exerciseNotice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.followUpConfirmationField = new FieldImpl(
				"followUpConfirmation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationAgentField = new FieldImpl(
				"calculationAgent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementField = new FieldImpl(
				"cashSettlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionalEarlyTerminationAdjustedDatesField = new FieldImpl(
				"optionalEarlyTerminationAdjustedDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseTermsField = new FieldImpl(
				"exerciseTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OptionalEarlyTermination input) {
			FieldValue singlePartyOption = Optional.ofNullable(input.getSinglePartyOption())
				.map(x -> new NestedFieldValueImpl(singlePartyOptionField, Optional.of(buyerSellerTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(singlePartyOptionField, Optional.empty()));
			FieldValue mutualEarlyTermination = new FieldValueImpl(mutualEarlyTerminationField, Optional.ofNullable(input.getMutualEarlyTermination()));
			FieldValue exerciseNotice = Optional.ofNullable(input.getExerciseNotice())
				.map(x -> x.stream()
					.map(_x -> exerciseNoticeTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(exerciseNoticeField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(exerciseNoticeField, Optional.empty()));
			FieldValue followUpConfirmation = new FieldValueImpl(followUpConfirmationField, Optional.ofNullable(input.getFollowUpConfirmation()));
			FieldValue calculationAgent = Optional.ofNullable(input.getCalculationAgent())
				.map(x -> new NestedFieldValueImpl(calculationAgentField, Optional.of(calculationAgentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationAgentField, Optional.empty()));
			FieldValue cashSettlement = Optional.ofNullable(input.getCashSettlement())
				.map(x -> new NestedFieldValueImpl(cashSettlementField, Optional.of(settlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashSettlementField, Optional.empty()));
			FieldValue optionalEarlyTerminationAdjustedDates = Optional.ofNullable(input.getOptionalEarlyTerminationAdjustedDates())
				.map(x -> new NestedFieldValueImpl(optionalEarlyTerminationAdjustedDatesField, Optional.of(optionalEarlyTerminationAdjustedDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(optionalEarlyTerminationAdjustedDatesField, Optional.empty()));
			FieldValue exerciseTerms = Optional.ofNullable(input.getExerciseTerms())
				.map(x -> new NestedFieldValueImpl(exerciseTermsField, Optional.of(exerciseTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseTermsField, Optional.empty()));
			return Arrays.asList(
				singlePartyOption,
				mutualEarlyTermination,
				exerciseNotice,
				followUpConfirmation,
				calculationAgent,
				cashSettlement,
				optionalEarlyTerminationAdjustedDates,
				exerciseTerms
			);
		}
	}
}
