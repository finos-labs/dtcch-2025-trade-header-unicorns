package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.base.staticdata.asset.credit.tabulator.NotDomesticCurrencyTypeTabulator;
import cdm.base.staticdata.asset.credit.tabulator.SpecifiedCurrencyTypeTabulator;
import cdm.product.common.settlement.DeliverableObligations;
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


@ImplementedBy(DeliverableObligationsTypeTabulator.Impl.class)
public interface DeliverableObligationsTypeTabulator extends Tabulator<DeliverableObligations> {
	@Singleton
	class Impl implements DeliverableObligationsTypeTabulator {
		private final Field accruedInterestField;
		private final Field categoryField;
		private final Field notSubordinatedField;
		private final Field specifiedCurrencyField;
		private final Field notSovereignLenderField;
		private final Field notDomesticCurrencyField;
		private final Field notDomesticLawField;
		private final Field listedField;
		private final Field notContingentField;
		private final Field notDomesticIssuanceField;
		private final Field assignableLoanField;
		private final Field consentRequiredLoanField;
		private final Field directLoanParticipationField;
		private final Field transferableField;
		private final Field maximumMaturityField;
		private final Field acceleratedOrMaturedField;
		private final Field notBearerField;
		private final Field fullFaithAndCreditObLiabilityField;
		private final Field generalFundObligationLiabilityField;
		private final Field revenueObligationLiabilityField;
		private final Field indirectLoanParticipationField;
		private final Field excludedField;
		private final Field othReferenceEntityObligationsField;

		private final SpecifiedCurrencyTypeTabulator specifiedCurrencyTypeTabulator;
		private final NotDomesticCurrencyTypeTabulator notDomesticCurrencyTypeTabulator;
		private final PCDeliverableObligationCharacTypeTabulator pCDeliverableObligationCharacTypeTabulator;
		private final LoanParticipationTypeTabulator loanParticipationTypeTabulator;
		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(SpecifiedCurrencyTypeTabulator specifiedCurrencyTypeTabulator, NotDomesticCurrencyTypeTabulator notDomesticCurrencyTypeTabulator, PCDeliverableObligationCharacTypeTabulator pCDeliverableObligationCharacTypeTabulator, LoanParticipationTypeTabulator loanParticipationTypeTabulator, PeriodTypeTabulator periodTypeTabulator) {
			this.specifiedCurrencyTypeTabulator = specifiedCurrencyTypeTabulator;
			this.notDomesticCurrencyTypeTabulator = notDomesticCurrencyTypeTabulator;
			this.pCDeliverableObligationCharacTypeTabulator = pCDeliverableObligationCharacTypeTabulator;
			this.loanParticipationTypeTabulator = loanParticipationTypeTabulator;
			this.periodTypeTabulator = periodTypeTabulator;
			this.accruedInterestField = new FieldImpl(
				"accruedInterest",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
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
			this.notContingentField = new FieldImpl(
				"notContingent",
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
			this.assignableLoanField = new FieldImpl(
				"assignableLoan",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.consentRequiredLoanField = new FieldImpl(
				"consentRequiredLoan",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.directLoanParticipationField = new FieldImpl(
				"directLoanParticipation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferableField = new FieldImpl(
				"transferable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maximumMaturityField = new FieldImpl(
				"maximumMaturity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.acceleratedOrMaturedField = new FieldImpl(
				"acceleratedOrMatured",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notBearerField = new FieldImpl(
				"notBearer",
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
			this.indirectLoanParticipationField = new FieldImpl(
				"indirectLoanParticipation",
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
		}

		@Override
		public List<FieldValue> tabulate(DeliverableObligations input) {
			FieldValue accruedInterest = new FieldValueImpl(accruedInterestField, Optional.ofNullable(input.getAccruedInterest()));
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
			FieldValue notContingent = new FieldValueImpl(notContingentField, Optional.ofNullable(input.getNotContingent()));
			FieldValue notDomesticIssuance = new FieldValueImpl(notDomesticIssuanceField, Optional.ofNullable(input.getNotDomesticIssuance()));
			FieldValue assignableLoan = Optional.ofNullable(input.getAssignableLoan())
				.map(x -> new NestedFieldValueImpl(assignableLoanField, Optional.of(pCDeliverableObligationCharacTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(assignableLoanField, Optional.empty()));
			FieldValue consentRequiredLoan = Optional.ofNullable(input.getConsentRequiredLoan())
				.map(x -> new NestedFieldValueImpl(consentRequiredLoanField, Optional.of(pCDeliverableObligationCharacTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(consentRequiredLoanField, Optional.empty()));
			FieldValue directLoanParticipation = Optional.ofNullable(input.getDirectLoanParticipation())
				.map(x -> new NestedFieldValueImpl(directLoanParticipationField, Optional.of(loanParticipationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(directLoanParticipationField, Optional.empty()));
			FieldValue transferable = new FieldValueImpl(transferableField, Optional.ofNullable(input.getTransferable()));
			FieldValue maximumMaturity = Optional.ofNullable(input.getMaximumMaturity())
				.map(x -> new NestedFieldValueImpl(maximumMaturityField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(maximumMaturityField, Optional.empty()));
			FieldValue acceleratedOrMatured = new FieldValueImpl(acceleratedOrMaturedField, Optional.ofNullable(input.getAcceleratedOrMatured()));
			FieldValue notBearer = new FieldValueImpl(notBearerField, Optional.ofNullable(input.getNotBearer()));
			FieldValue fullFaithAndCreditObLiability = new FieldValueImpl(fullFaithAndCreditObLiabilityField, Optional.ofNullable(input.getFullFaithAndCreditObLiability()));
			FieldValue generalFundObligationLiability = new FieldValueImpl(generalFundObligationLiabilityField, Optional.ofNullable(input.getGeneralFundObligationLiability()));
			FieldValue revenueObligationLiability = new FieldValueImpl(revenueObligationLiabilityField, Optional.ofNullable(input.getRevenueObligationLiability()));
			FieldValue indirectLoanParticipation = Optional.ofNullable(input.getIndirectLoanParticipation())
				.map(x -> new NestedFieldValueImpl(indirectLoanParticipationField, Optional.of(loanParticipationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indirectLoanParticipationField, Optional.empty()));
			FieldValue excluded = new FieldValueImpl(excludedField, Optional.ofNullable(input.getExcluded()));
			FieldValue othReferenceEntityObligations = new FieldValueImpl(othReferenceEntityObligationsField, Optional.ofNullable(input.getOthReferenceEntityObligations()));
			return Arrays.asList(
				accruedInterest,
				category,
				notSubordinated,
				specifiedCurrency,
				notSovereignLender,
				notDomesticCurrency,
				notDomesticLaw,
				listed,
				notContingent,
				notDomesticIssuance,
				assignableLoan,
				consentRequiredLoan,
				directLoanParticipation,
				transferable,
				maximumMaturity,
				acceleratedOrMatured,
				notBearer,
				fullFaithAndCreditObLiability,
				generalFundObligationLiability,
				revenueObligationLiability,
				indirectLoanParticipation,
				excluded,
				othReferenceEntityObligations
			);
		}
	}
}
