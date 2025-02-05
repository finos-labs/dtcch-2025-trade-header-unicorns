package cdm.observable.asset.tabulator;

import cdm.observable.asset.ValuationMethod;
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


@ImplementedBy(ValuationMethodTypeTabulator.Impl.class)
public interface ValuationMethodTypeTabulator extends Tabulator<ValuationMethod> {
	@Singleton
	class Impl implements ValuationMethodTypeTabulator {
		private final Field valuationSourceField;
		private final Field quotationMethodField;
		private final Field valuationMethodField;
		private final Field quotationAmountField;
		private final Field minimumQuotationAmountField;
		private final Field cashCollateralValuationMethodField;

		private final ValuationSourceTypeTabulator valuationSourceTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;
		private final CashCollateralValuationMethodTypeTabulator cashCollateralValuationMethodTypeTabulator;

		@Inject
		public Impl(ValuationSourceTypeTabulator valuationSourceTypeTabulator, MoneyTypeTabulator moneyTypeTabulator, CashCollateralValuationMethodTypeTabulator cashCollateralValuationMethodTypeTabulator) {
			this.valuationSourceTypeTabulator = valuationSourceTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.cashCollateralValuationMethodTypeTabulator = cashCollateralValuationMethodTypeTabulator;
			this.valuationSourceField = new FieldImpl(
				"valuationSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quotationMethodField = new FieldImpl(
				"quotationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationMethodField = new FieldImpl(
				"valuationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quotationAmountField = new FieldImpl(
				"quotationAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.minimumQuotationAmountField = new FieldImpl(
				"minimumQuotationAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashCollateralValuationMethodField = new FieldImpl(
				"cashCollateralValuationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationMethod input) {
			FieldValue valuationSource = Optional.ofNullable(input.getValuationSource())
				.map(x -> new NestedFieldValueImpl(valuationSourceField, Optional.of(valuationSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationSourceField, Optional.empty()));
			FieldValue quotationMethod = new FieldValueImpl(quotationMethodField, Optional.ofNullable(input.getQuotationMethod()));
			FieldValue valuationMethod = new FieldValueImpl(valuationMethodField, Optional.ofNullable(input.getValuationMethod()));
			FieldValue quotationAmount = Optional.ofNullable(input.getQuotationAmount())
				.map(x -> new NestedFieldValueImpl(quotationAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quotationAmountField, Optional.empty()));
			FieldValue minimumQuotationAmount = Optional.ofNullable(input.getMinimumQuotationAmount())
				.map(x -> new NestedFieldValueImpl(minimumQuotationAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(minimumQuotationAmountField, Optional.empty()));
			FieldValue cashCollateralValuationMethod = Optional.ofNullable(input.getCashCollateralValuationMethod())
				.map(x -> new NestedFieldValueImpl(cashCollateralValuationMethodField, Optional.of(cashCollateralValuationMethodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashCollateralValuationMethodField, Optional.empty()));
			return Arrays.asList(
				valuationSource,
				quotationMethod,
				valuationMethod,
				quotationAmount,
				minimumQuotationAmount,
				cashCollateralValuationMethod
			);
		}
	}
}
