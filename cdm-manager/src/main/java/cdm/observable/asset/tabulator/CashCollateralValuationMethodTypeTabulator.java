package cdm.observable.asset.tabulator;

import cdm.observable.asset.CashCollateralValuationMethod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(CashCollateralValuationMethodTypeTabulator.Impl.class)
public interface CashCollateralValuationMethodTypeTabulator extends Tabulator<CashCollateralValuationMethod> {
	@Singleton
	class Impl implements CashCollateralValuationMethodTypeTabulator {
		private final Field applicableCsaField;
		private final Field cashCollateralCurrencyField;
		private final Field cashCollateralInterestRateField;
		private final Field agreedDiscountRateField;
		private final Field protectedPartyField;
		private final Field prescribedDocumentationAdjustmentField;

		public Impl() {
			this.applicableCsaField = new FieldImpl(
				"applicableCsa",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashCollateralCurrencyField = new FieldImpl(
				"cashCollateralCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashCollateralInterestRateField = new FieldImpl(
				"cashCollateralInterestRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.agreedDiscountRateField = new FieldImpl(
				"agreedDiscountRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.protectedPartyField = new FieldImpl(
				"protectedParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.prescribedDocumentationAdjustmentField = new FieldImpl(
				"prescribedDocumentationAdjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CashCollateralValuationMethod input) {
			FieldValue applicableCsa = new FieldValueImpl(applicableCsaField, Optional.ofNullable(input.getApplicableCsa()));
			FieldValue cashCollateralCurrency = new FieldValueImpl(cashCollateralCurrencyField, Optional.ofNullable(input.getCashCollateralCurrency()));
			FieldValue cashCollateralInterestRate = new FieldValueImpl(cashCollateralInterestRateField, Optional.ofNullable(input.getCashCollateralInterestRate())
				.map(x -> x.getValue()));
			FieldValue agreedDiscountRate = new FieldValueImpl(agreedDiscountRateField, Optional.ofNullable(input.getAgreedDiscountRate())
				.map(x -> x.getValue()));
			FieldValue protectedParty = new FieldValueImpl(protectedPartyField, Optional.ofNullable(input.getProtectedParty()));
			FieldValue prescribedDocumentationAdjustment = new FieldValueImpl(prescribedDocumentationAdjustmentField, Optional.ofNullable(input.getPrescribedDocumentationAdjustment()));
			return Arrays.asList(
				applicableCsa,
				cashCollateralCurrency,
				cashCollateralInterestRate,
				agreedDiscountRate,
				protectedParty,
				prescribedDocumentationAdjustment
			);
		}
	}
}
