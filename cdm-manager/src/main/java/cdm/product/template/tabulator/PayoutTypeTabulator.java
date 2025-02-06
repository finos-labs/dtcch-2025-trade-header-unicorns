package cdm.product.template.tabulator;

import cdm.product.asset.tabulator.CommodityPayoutTypeTabulator;
import cdm.product.asset.tabulator.CreditDefaultPayoutTypeTabulator;
import cdm.product.asset.tabulator.InterestRatePayoutTypeTabulator;
import cdm.product.template.Payout;
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


@ImplementedBy(PayoutTypeTabulator.Impl.class)
public interface PayoutTypeTabulator extends Tabulator<Payout> {
	@Singleton
	class Impl implements PayoutTypeTabulator {
		private final Field AssetPayoutField;
		private final Field CommodityPayoutField;
		private final Field CreditDefaultPayoutField;
		private final Field FixedPricePayoutField;
		private final Field InterestRatePayoutField;
		private final Field OptionPayoutField;
		private final Field PerformancePayoutField;
		private final Field SettlementPayoutField;

		private final AssetPayoutTypeTabulator assetPayoutTypeTabulator;
		private final CommodityPayoutTypeTabulator commodityPayoutTypeTabulator;
		private final CreditDefaultPayoutTypeTabulator creditDefaultPayoutTypeTabulator;
		private final FixedPricePayoutTypeTabulator fixedPricePayoutTypeTabulator;
		private final InterestRatePayoutTypeTabulator interestRatePayoutTypeTabulator;
		private final OptionPayoutTypeTabulator optionPayoutTypeTabulator;
		private final PerformancePayoutTypeTabulator performancePayoutTypeTabulator;
		private final SettlementPayoutTypeTabulator settlementPayoutTypeTabulator;

		@Inject
		public Impl(AssetPayoutTypeTabulator assetPayoutTypeTabulator, CommodityPayoutTypeTabulator commodityPayoutTypeTabulator, CreditDefaultPayoutTypeTabulator creditDefaultPayoutTypeTabulator, FixedPricePayoutTypeTabulator fixedPricePayoutTypeTabulator, InterestRatePayoutTypeTabulator interestRatePayoutTypeTabulator, OptionPayoutTypeTabulator optionPayoutTypeTabulator, PerformancePayoutTypeTabulator performancePayoutTypeTabulator, SettlementPayoutTypeTabulator settlementPayoutTypeTabulator) {
			this.assetPayoutTypeTabulator = assetPayoutTypeTabulator;
			this.commodityPayoutTypeTabulator = commodityPayoutTypeTabulator;
			this.creditDefaultPayoutTypeTabulator = creditDefaultPayoutTypeTabulator;
			this.fixedPricePayoutTypeTabulator = fixedPricePayoutTypeTabulator;
			this.interestRatePayoutTypeTabulator = interestRatePayoutTypeTabulator;
			this.optionPayoutTypeTabulator = optionPayoutTypeTabulator;
			this.performancePayoutTypeTabulator = performancePayoutTypeTabulator;
			this.settlementPayoutTypeTabulator = settlementPayoutTypeTabulator;
			this.AssetPayoutField = new FieldImpl(
				"AssetPayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CommodityPayoutField = new FieldImpl(
				"CommodityPayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CreditDefaultPayoutField = new FieldImpl(
				"CreditDefaultPayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.FixedPricePayoutField = new FieldImpl(
				"FixedPricePayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.InterestRatePayoutField = new FieldImpl(
				"InterestRatePayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.OptionPayoutField = new FieldImpl(
				"OptionPayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.PerformancePayoutField = new FieldImpl(
				"PerformancePayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.SettlementPayoutField = new FieldImpl(
				"SettlementPayout",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Payout input) {
			FieldValue AssetPayout = Optional.ofNullable(input.getAssetPayout())
				.map(x -> new NestedFieldValueImpl(AssetPayoutField, Optional.of(assetPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetPayoutField, Optional.empty()));
			FieldValue CommodityPayout = Optional.ofNullable(input.getCommodityPayout())
				.map(x -> new NestedFieldValueImpl(CommodityPayoutField, Optional.of(commodityPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CommodityPayoutField, Optional.empty()));
			FieldValue CreditDefaultPayout = Optional.ofNullable(input.getCreditDefaultPayout())
				.map(x -> new NestedFieldValueImpl(CreditDefaultPayoutField, Optional.of(creditDefaultPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CreditDefaultPayoutField, Optional.empty()));
			FieldValue FixedPricePayout = Optional.ofNullable(input.getFixedPricePayout())
				.map(x -> new NestedFieldValueImpl(FixedPricePayoutField, Optional.of(fixedPricePayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(FixedPricePayoutField, Optional.empty()));
			FieldValue InterestRatePayout = Optional.ofNullable(input.getInterestRatePayout())
				.map(x -> new NestedFieldValueImpl(InterestRatePayoutField, Optional.of(interestRatePayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(InterestRatePayoutField, Optional.empty()));
			FieldValue OptionPayout = Optional.ofNullable(input.getOptionPayout())
				.map(x -> new NestedFieldValueImpl(OptionPayoutField, Optional.of(optionPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(OptionPayoutField, Optional.empty()));
			FieldValue PerformancePayout = Optional.ofNullable(input.getPerformancePayout())
				.map(x -> new NestedFieldValueImpl(PerformancePayoutField, Optional.of(performancePayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(PerformancePayoutField, Optional.empty()));
			FieldValue SettlementPayout = Optional.ofNullable(input.getSettlementPayout())
				.map(x -> new NestedFieldValueImpl(SettlementPayoutField, Optional.of(settlementPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(SettlementPayoutField, Optional.empty()));
			return Arrays.asList(
				AssetPayout,
				CommodityPayout,
				CreditDefaultPayout,
				FixedPricePayout,
				InterestRatePayout,
				OptionPayout,
				PerformancePayout,
				SettlementPayout
			);
		}
	}
}
