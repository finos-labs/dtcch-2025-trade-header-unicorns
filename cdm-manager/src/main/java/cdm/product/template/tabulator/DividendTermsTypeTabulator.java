package cdm.product.template.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.asset.tabulator.DividendPayoutRatioTypeTabulator;
import cdm.product.template.DividendTerms;
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


@ImplementedBy(DividendTermsTypeTabulator.Impl.class)
public interface DividendTermsTypeTabulator extends Tabulator<DividendTerms> {
	@Singleton
	class Impl implements DividendTermsTypeTabulator {
		private final Field manufacturedIncomeRequirementField;
		private final Field dividendEntitlementField;
		private final Field minimumBillingAmountField;

		private final DividendPayoutRatioTypeTabulator dividendPayoutRatioTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(DividendPayoutRatioTypeTabulator dividendPayoutRatioTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.dividendPayoutRatioTypeTabulator = dividendPayoutRatioTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.manufacturedIncomeRequirementField = new FieldImpl(
				"manufacturedIncomeRequirement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendEntitlementField = new FieldImpl(
				"dividendEntitlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.minimumBillingAmountField = new FieldImpl(
				"minimumBillingAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendTerms input) {
			FieldValue manufacturedIncomeRequirement = Optional.ofNullable(input.getManufacturedIncomeRequirement())
				.map(x -> new NestedFieldValueImpl(manufacturedIncomeRequirementField, Optional.of(dividendPayoutRatioTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(manufacturedIncomeRequirementField, Optional.empty()));
			FieldValue dividendEntitlement = new FieldValueImpl(dividendEntitlementField, Optional.ofNullable(input.getDividendEntitlement()));
			FieldValue minimumBillingAmount = Optional.ofNullable(input.getMinimumBillingAmount())
				.map(x -> new NestedFieldValueImpl(minimumBillingAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(minimumBillingAmountField, Optional.empty()));
			return Arrays.asList(
				manufacturedIncomeRequirement,
				dividendEntitlement,
				minimumBillingAmount
			);
		}
	}
}
