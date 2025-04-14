package cdm.product.asset.tabulator;

import cdm.product.asset.DividendReturnTerms;
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


@ImplementedBy(DividendReturnTermsTypeTabulator.Impl.class)
public interface DividendReturnTermsTypeTabulator extends Tabulator<DividendReturnTerms> {
	@Singleton
	class Impl implements DividendReturnTermsTypeTabulator {
		private final Field dividendPayoutRatioField;
		private final Field dividendReinvestmentField;
		private final Field dividendEntitlementField;
		private final Field dividendAmountTypeField;
		private final Field performanceField;
		private final Field firstOrSecondPeriodField;
		private final Field extraordinaryDividendsPartyField;
		private final Field excessDividendAmountField;
		private final Field dividendCurrencyField;
		private final Field nonCashDividendTreatmentField;
		private final Field dividendCompositionField;
		private final Field specialDividendsField;
		private final Field materialDividendField;
		private final Field dividendPeriodField;

		private final DividendPayoutRatioTypeTabulator dividendPayoutRatioTypeTabulator;
		private final DividendCurrencyTypeTabulator dividendCurrencyTypeTabulator;
		private final DividendPeriodTypeTabulator dividendPeriodTypeTabulator;

		@Inject
		public Impl(DividendPayoutRatioTypeTabulator dividendPayoutRatioTypeTabulator, DividendCurrencyTypeTabulator dividendCurrencyTypeTabulator, DividendPeriodTypeTabulator dividendPeriodTypeTabulator) {
			this.dividendPayoutRatioTypeTabulator = dividendPayoutRatioTypeTabulator;
			this.dividendCurrencyTypeTabulator = dividendCurrencyTypeTabulator;
			this.dividendPeriodTypeTabulator = dividendPeriodTypeTabulator;
			this.dividendPayoutRatioField = new FieldImpl(
				"dividendPayoutRatio",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendReinvestmentField = new FieldImpl(
				"dividendReinvestment",
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
			this.dividendAmountTypeField = new FieldImpl(
				"dividendAmountType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.performanceField = new FieldImpl(
				"performance",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstOrSecondPeriodField = new FieldImpl(
				"firstOrSecondPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.extraordinaryDividendsPartyField = new FieldImpl(
				"extraordinaryDividendsParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.excessDividendAmountField = new FieldImpl(
				"excessDividendAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendCurrencyField = new FieldImpl(
				"dividendCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nonCashDividendTreatmentField = new FieldImpl(
				"nonCashDividendTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendCompositionField = new FieldImpl(
				"dividendComposition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.specialDividendsField = new FieldImpl(
				"specialDividends",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.materialDividendField = new FieldImpl(
				"materialDividend",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendPeriodField = new FieldImpl(
				"dividendPeriod",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendReturnTerms input) {
			FieldValue dividendPayoutRatio = Optional.ofNullable(input.getDividendPayoutRatio())
				.map(x -> x.stream()
					.map(_x -> dividendPayoutRatioTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(dividendPayoutRatioField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(dividendPayoutRatioField, Optional.empty()));
			FieldValue dividendReinvestment = new FieldValueImpl(dividendReinvestmentField, Optional.ofNullable(input.getDividendReinvestment()));
			FieldValue dividendEntitlement = new FieldValueImpl(dividendEntitlementField, Optional.ofNullable(input.getDividendEntitlement()));
			FieldValue dividendAmountType = new FieldValueImpl(dividendAmountTypeField, Optional.ofNullable(input.getDividendAmountType()));
			FieldValue performance = new FieldValueImpl(performanceField, Optional.ofNullable(input.getPerformance()));
			FieldValue firstOrSecondPeriod = new FieldValueImpl(firstOrSecondPeriodField, Optional.ofNullable(input.getFirstOrSecondPeriod()));
			FieldValue extraordinaryDividendsParty = new FieldValueImpl(extraordinaryDividendsPartyField, Optional.ofNullable(input.getExtraordinaryDividendsParty()));
			FieldValue excessDividendAmount = new FieldValueImpl(excessDividendAmountField, Optional.ofNullable(input.getExcessDividendAmount()));
			FieldValue dividendCurrency = Optional.ofNullable(input.getDividendCurrency())
				.map(x -> new NestedFieldValueImpl(dividendCurrencyField, Optional.of(dividendCurrencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendCurrencyField, Optional.empty()));
			FieldValue nonCashDividendTreatment = new FieldValueImpl(nonCashDividendTreatmentField, Optional.ofNullable(input.getNonCashDividendTreatment()));
			FieldValue dividendComposition = new FieldValueImpl(dividendCompositionField, Optional.ofNullable(input.getDividendComposition()));
			FieldValue specialDividends = new FieldValueImpl(specialDividendsField, Optional.ofNullable(input.getSpecialDividends()));
			FieldValue materialDividend = new FieldValueImpl(materialDividendField, Optional.ofNullable(input.getMaterialDividend()));
			FieldValue dividendPeriod = Optional.ofNullable(input.getDividendPeriod())
				.map(x -> x.stream()
					.map(_x -> dividendPeriodTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(dividendPeriodField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(dividendPeriodField, Optional.empty()));
			return Arrays.asList(
				dividendPayoutRatio,
				dividendReinvestment,
				dividendEntitlement,
				dividendAmountType,
				performance,
				firstOrSecondPeriod,
				extraordinaryDividendsParty,
				excessDividendAmount,
				dividendCurrency,
				nonCashDividendTreatment,
				dividendComposition,
				specialDividends,
				materialDividend,
				dividendPeriod
			);
		}
	}
}
