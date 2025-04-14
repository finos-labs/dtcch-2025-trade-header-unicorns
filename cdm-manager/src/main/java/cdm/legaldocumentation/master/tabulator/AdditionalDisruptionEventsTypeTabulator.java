package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AdditionalDisruptionEventsTypeTabulator.Impl.class)
public interface AdditionalDisruptionEventsTypeTabulator extends Tabulator<AdditionalDisruptionEvents> {
	@Singleton
	class Impl implements AdditionalDisruptionEventsTypeTabulator {
		private final Field changeInLawField;
		private final Field failureToDeliverField;
		private final Field insolvencyFilingField;
		private final Field hedgingDisruptionField;
		private final Field increasedCostOfHedgingField;
		private final Field foreignOwnershipEventField;
		private final Field lossOfStockBorrowField;
		private final Field maximumStockLoanRateField;
		private final Field increasedCostOfStockBorrowField;
		private final Field initialStockLoanRateField;
		private final Field determiningPartyField;
		private final Field additionalBespokeTermsField;

		private final ClauseTypeTabulator clauseTypeTabulator;

		@Inject
		public Impl(ClauseTypeTabulator clauseTypeTabulator) {
			this.clauseTypeTabulator = clauseTypeTabulator;
			this.changeInLawField = new FieldImpl(
				"changeInLaw",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.failureToDeliverField = new FieldImpl(
				"failureToDeliver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.insolvencyFilingField = new FieldImpl(
				"insolvencyFiling",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.hedgingDisruptionField = new FieldImpl(
				"hedgingDisruption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.increasedCostOfHedgingField = new FieldImpl(
				"increasedCostOfHedging",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.foreignOwnershipEventField = new FieldImpl(
				"foreignOwnershipEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lossOfStockBorrowField = new FieldImpl(
				"lossOfStockBorrow",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maximumStockLoanRateField = new FieldImpl(
				"maximumStockLoanRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.increasedCostOfStockBorrowField = new FieldImpl(
				"increasedCostOfStockBorrow",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialStockLoanRateField = new FieldImpl(
				"initialStockLoanRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.determiningPartyField = new FieldImpl(
				"determiningParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalBespokeTermsField = new FieldImpl(
				"additionalBespokeTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AdditionalDisruptionEvents input) {
			FieldValue changeInLaw = new FieldValueImpl(changeInLawField, Optional.ofNullable(input.getChangeInLaw()));
			FieldValue failureToDeliver = new FieldValueImpl(failureToDeliverField, Optional.ofNullable(input.getFailureToDeliver()));
			FieldValue insolvencyFiling = new FieldValueImpl(insolvencyFilingField, Optional.ofNullable(input.getInsolvencyFiling()));
			FieldValue hedgingDisruption = new FieldValueImpl(hedgingDisruptionField, Optional.ofNullable(input.getHedgingDisruption()));
			FieldValue increasedCostOfHedging = new FieldValueImpl(increasedCostOfHedgingField, Optional.ofNullable(input.getIncreasedCostOfHedging()));
			FieldValue foreignOwnershipEvent = new FieldValueImpl(foreignOwnershipEventField, Optional.ofNullable(input.getForeignOwnershipEvent()));
			FieldValue lossOfStockBorrow = new FieldValueImpl(lossOfStockBorrowField, Optional.ofNullable(input.getLossOfStockBorrow()));
			FieldValue maximumStockLoanRate = new FieldValueImpl(maximumStockLoanRateField, Optional.ofNullable(input.getMaximumStockLoanRate()));
			FieldValue increasedCostOfStockBorrow = new FieldValueImpl(increasedCostOfStockBorrowField, Optional.ofNullable(input.getIncreasedCostOfStockBorrow()));
			FieldValue initialStockLoanRate = new FieldValueImpl(initialStockLoanRateField, Optional.ofNullable(input.getInitialStockLoanRate()));
			FieldValue determiningParty = new FieldValueImpl(determiningPartyField, Optional.ofNullable(input.getDeterminingParty()));
			FieldValue additionalBespokeTerms = Optional.ofNullable(input.getAdditionalBespokeTerms())
				.map(x -> x.stream()
					.map(_x -> clauseTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(additionalBespokeTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(additionalBespokeTermsField, Optional.empty()));
			return Arrays.asList(
				changeInLaw,
				failureToDeliver,
				insolvencyFiling,
				hedgingDisruption,
				increasedCostOfHedging,
				foreignOwnershipEvent,
				lossOfStockBorrow,
				maximumStockLoanRate,
				increasedCostOfStockBorrow,
				initialStockLoanRate,
				determiningParty,
				additionalBespokeTerms
			);
		}
	}
}
