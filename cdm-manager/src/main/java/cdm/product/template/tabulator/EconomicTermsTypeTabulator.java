package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.observable.asset.tabulator.CalculationAgentTypeTabulator;
import cdm.product.collateral.tabulator.CollateralTypeTabulator;
import cdm.product.template.EconomicTerms;
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


@ImplementedBy(EconomicTermsTypeTabulator.Impl.class)
public interface EconomicTermsTypeTabulator extends Tabulator<EconomicTerms> {
	@Singleton
	class Impl implements EconomicTermsTypeTabulator {
		private final Field effectiveDateField;
		private final Field terminationDateField;
		private final Field dateAdjustmentsField;
		private final Field payoutField;
		private final Field terminationProvisionField;
		private final Field calculationAgentField;
		private final Field nonStandardisedTermsField;
		private final Field collateralField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;
		private final PayoutTypeTabulator payoutTypeTabulator;
		private final TerminationProvisionTypeTabulator terminationProvisionTypeTabulator;
		private final CalculationAgentTypeTabulator calculationAgentTypeTabulator;
		private final CollateralTypeTabulator collateralTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator, PayoutTypeTabulator payoutTypeTabulator, TerminationProvisionTypeTabulator terminationProvisionTypeTabulator, CalculationAgentTypeTabulator calculationAgentTypeTabulator, CollateralTypeTabulator collateralTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.payoutTypeTabulator = payoutTypeTabulator;
			this.terminationProvisionTypeTabulator = terminationProvisionTypeTabulator;
			this.calculationAgentTypeTabulator = calculationAgentTypeTabulator;
			this.collateralTypeTabulator = collateralTypeTabulator;
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.terminationDateField = new FieldImpl(
				"terminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateAdjustmentsField = new FieldImpl(
				"dateAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payoutField = new FieldImpl(
				"payout",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.terminationProvisionField = new FieldImpl(
				"terminationProvision",
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
			this.nonStandardisedTermsField = new FieldImpl(
				"nonStandardisedTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralField = new FieldImpl(
				"collateral",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EconomicTerms input) {
			FieldValue effectiveDate = Optional.ofNullable(input.getEffectiveDate())
				.map(x -> new NestedFieldValueImpl(effectiveDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(effectiveDateField, Optional.empty()));
			FieldValue terminationDate = Optional.ofNullable(input.getTerminationDate())
				.map(x -> new NestedFieldValueImpl(terminationDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(terminationDateField, Optional.empty()));
			FieldValue dateAdjustments = Optional.ofNullable(input.getDateAdjustments())
				.map(x -> new NestedFieldValueImpl(dateAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateAdjustmentsField, Optional.empty()));
			FieldValue payout = Optional.ofNullable(input.getPayout())
				.map(x -> x.stream()
					.map(_x -> payoutTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(payoutField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(payoutField, Optional.empty()));
			FieldValue terminationProvision = Optional.ofNullable(input.getTerminationProvision())
				.map(x -> new NestedFieldValueImpl(terminationProvisionField, Optional.of(terminationProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(terminationProvisionField, Optional.empty()));
			FieldValue calculationAgent = Optional.ofNullable(input.getCalculationAgent())
				.map(x -> new NestedFieldValueImpl(calculationAgentField, Optional.of(calculationAgentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationAgentField, Optional.empty()));
			FieldValue nonStandardisedTerms = new FieldValueImpl(nonStandardisedTermsField, Optional.ofNullable(input.getNonStandardisedTerms()));
			FieldValue collateral = Optional.ofNullable(input.getCollateral())
				.map(x -> new NestedFieldValueImpl(collateralField, Optional.of(collateralTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralField, Optional.empty()));
			return Arrays.asList(
				effectiveDate,
				terminationDate,
				dateAdjustments,
				payout,
				terminationProvision,
				calculationAgent,
				nonStandardisedTerms,
				collateral
			);
		}
	}
}
