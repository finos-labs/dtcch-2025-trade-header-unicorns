package cdm.base.staticdata.asset.credit.tabulator;

import cdm.base.staticdata.asset.credit.Obligations;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ObligationsTypeTabulator.Impl.class)
public interface ObligationsTypeTabulator extends Tabulator<Obligations> {
	@Singleton
	class Impl implements ObligationsTypeTabulator {
		private final Field categoryField;
		private final Field notSubordinatedField;
		private final Field specifiedCurrencyField;
		private final Field notSovereignLenderField;
		private final Field notDomesticCurrencyField;
		private final Field notDomesticLawField;
		private final Field listedField;
		private final Field notDomesticIssuanceField;
		private final Field fullFaithAndCreditObLiabilityField;
		private final Field generalFundObligationLiabilityField;
		private final Field revenueObligationLiabilityField;
		private final Field notContingentField;
		private final Field excludedField;
		private final Field othReferenceEntityObligationsField;
		private final Field designatedPriorityField;
		private final Field cashSettlementOnlyField;
		private final Field deliveryOfCommitmentsField;
		private final Field continuityField;

		private final SpecifiedCurrencyTypeTabulator specifiedCurrencyTypeTabulator;
		private final NotDomesticCurrencyTypeTabulator notDomesticCurrencyTypeTabulator;

		@Inject
		public Impl(SpecifiedCurrencyTypeTabulator specifiedCurrencyTypeTabulator, NotDomesticCurrencyTypeTabulator notDomesticCurrencyTypeTabulator) {
			this.specifiedCurrencyTypeTabulator = specifiedCurrencyTypeTabulator;
			this.notDomesticCurrencyTypeTabulator = notDomesticCurrencyTypeTabulator;
			this.categoryField = new FieldImpl(
				"category",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notSubordinatedField = new FieldImpl(
				"notSubordinated",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.specifiedCurrencyField = new FieldImpl(
				"specifiedCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notSovereignLenderField = new FieldImpl(
				"notSovereignLender",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notDomesticCurrencyField = new FieldImpl(
				"notDomesticCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notDomesticLawField = new FieldImpl(
				"notDomesticLaw",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.listedField = new FieldImpl(
				"listed",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notDomesticIssuanceField = new FieldImpl(
				"notDomesticIssuance",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fullFaithAndCreditObLiabilityField = new FieldImpl(
				"fullFaithAndCreditObLiability",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.generalFundObligationLiabilityField = new FieldImpl(
				"generalFundObligationLiability",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.revenueObligationLiabilityField = new FieldImpl(
				"revenueObligationLiability",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notContingentField = new FieldImpl(
				"notContingent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.excludedField = new FieldImpl(
				"excluded",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.othReferenceEntityObligationsField = new FieldImpl(
				"othReferenceEntityObligations",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.designatedPriorityField = new FieldImpl(
				"designatedPriority",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementOnlyField = new FieldImpl(
				"cashSettlementOnly",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryOfCommitmentsField = new FieldImpl(
				"deliveryOfCommitments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.continuityField = new FieldImpl(
				"continuity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Obligations input) {
			FieldValue category = new FieldValueImpl(categoryField, Optional.ofNullable(input.getCategory()));
			FieldValue notSubordinated = new FieldValueImpl(notSubordinatedField, Optional.ofNullable(input.getNotSubordinated()));
			FieldValue specifiedCurrency = Optional.ofNullable(input.getSpecifiedCurrency())
				.map(x -> new NestedFieldValueImpl(specifiedCurrencyField, Optional.of(specifiedCurrencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(specifiedCurrencyField, Optional.empty()));
			FieldValue notSovereignLender = new FieldValueImpl(notSovereignLenderField, Optional.ofNullable(input.getNotSovereignLender()));
			FieldValue notDomesticCurrency = Optional.ofNullable(input.getNotDomesticCurrency())
				.map(x -> new NestedFieldValueImpl(notDomesticCurrencyField, Optional.of(notDomesticCurrencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notDomesticCurrencyField, Optional.empty()));
			FieldValue notDomesticLaw = new FieldValueImpl(notDomesticLawField, Optional.ofNullable(input.getNotDomesticLaw()));
			FieldValue listed = new FieldValueImpl(listedField, Optional.ofNullable(input.getListed()));
			FieldValue notDomesticIssuance = new FieldValueImpl(notDomesticIssuanceField, Optional.ofNullable(input.getNotDomesticIssuance()));
			FieldValue fullFaithAndCreditObLiability = new FieldValueImpl(fullFaithAndCreditObLiabilityField, Optional.ofNullable(input.getFullFaithAndCreditObLiability()));
			FieldValue generalFundObligationLiability = new FieldValueImpl(generalFundObligationLiabilityField, Optional.ofNullable(input.getGeneralFundObligationLiability()));
			FieldValue revenueObligationLiability = new FieldValueImpl(revenueObligationLiabilityField, Optional.ofNullable(input.getRevenueObligationLiability()));
			FieldValue notContingent = new FieldValueImpl(notContingentField, Optional.ofNullable(input.getNotContingent()));
			FieldValue excluded = new FieldValueImpl(excludedField, Optional.ofNullable(input.getExcluded()));
			FieldValue othReferenceEntityObligations = new FieldValueImpl(othReferenceEntityObligationsField, Optional.ofNullable(input.getOthReferenceEntityObligations()));
			FieldValue designatedPriority = new FieldValueImpl(designatedPriorityField, Optional.ofNullable(input.getDesignatedPriority())
				.map(x -> x.getValue()));
			FieldValue cashSettlementOnly = new FieldValueImpl(cashSettlementOnlyField, Optional.ofNullable(input.getCashSettlementOnly()));
			FieldValue deliveryOfCommitments = new FieldValueImpl(deliveryOfCommitmentsField, Optional.ofNullable(input.getDeliveryOfCommitments()));
			FieldValue continuity = new FieldValueImpl(continuityField, Optional.ofNullable(input.getContinuity()));
			return Arrays.asList(
				category,
				notSubordinated,
				specifiedCurrency,
				notSovereignLender,
				notDomesticCurrency,
				notDomesticLaw,
				listed,
				notDomesticIssuance,
				fullFaithAndCreditObLiability,
				generalFundObligationLiability,
				revenueObligationLiability,
				notContingent,
				excluded,
				othReferenceEntityObligations,
				designatedPriority,
				cashSettlementOnly,
				deliveryOfCommitments,
				continuity
			);
		}
	}
}
