package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.observable.asset.tabulator.DividendApplicabilityTypeTabulator;
import cdm.observable.asset.tabulator.ObservableTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.VarianceReturnTerms;
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


@ImplementedBy(VarianceReturnTermsTypeTabulator.Impl.class)
public interface VarianceReturnTermsTypeTabulator extends Tabulator<VarianceReturnTerms> {
	@Singleton
	class Impl implements VarianceReturnTermsTypeTabulator {
		private final Field valuationTermsField;
		private final Field annualizationFactorField;
		private final Field dividendApplicabilityField;
		private final Field equityUnderlierProvisionsField;
		private final Field sharePriceDividendAdjustmentField;
		private final Field expectedNField;
		private final Field initialLevelField;
		private final Field initialLevelSourceField;
		private final Field meanAdjustmentField;
		private final Field performanceField;
		private final Field varianceStrikePriceField;
		private final Field volatilityStrikePriceField;
		private final Field varianceCapFloorField;
		private final Field volatilityCapFloorField;
		private final Field vegaNotionalAmountField;
		private final Field exchangeTradedContractNearestField;

		private final ValuationTermsTypeTabulator valuationTermsTypeTabulator;
		private final DividendApplicabilityTypeTabulator dividendApplicabilityTypeTabulator;
		private final EquityUnderlierProvisionsTypeTabulator equityUnderlierProvisionsTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;
		private final VarianceCapFloorTypeTabulator varianceCapFloorTypeTabulator;
		private final VolatilityCapFloorTypeTabulator volatilityCapFloorTypeTabulator;
		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;
		private final ObservableTypeTabulator observableTypeTabulator;

		@Inject
		public Impl(ValuationTermsTypeTabulator valuationTermsTypeTabulator, DividendApplicabilityTypeTabulator dividendApplicabilityTypeTabulator, EquityUnderlierProvisionsTypeTabulator equityUnderlierProvisionsTypeTabulator, PriceTypeTabulator priceTypeTabulator, VarianceCapFloorTypeTabulator varianceCapFloorTypeTabulator, VolatilityCapFloorTypeTabulator volatilityCapFloorTypeTabulator, NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator, ObservableTypeTabulator observableTypeTabulator) {
			this.valuationTermsTypeTabulator = valuationTermsTypeTabulator;
			this.dividendApplicabilityTypeTabulator = dividendApplicabilityTypeTabulator;
			this.equityUnderlierProvisionsTypeTabulator = equityUnderlierProvisionsTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.varianceCapFloorTypeTabulator = varianceCapFloorTypeTabulator;
			this.volatilityCapFloorTypeTabulator = volatilityCapFloorTypeTabulator;
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.observableTypeTabulator = observableTypeTabulator;
			this.valuationTermsField = new FieldImpl(
				"valuationTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.annualizationFactorField = new FieldImpl(
				"annualizationFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendApplicabilityField = new FieldImpl(
				"dividendApplicability",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.equityUnderlierProvisionsField = new FieldImpl(
				"equityUnderlierProvisions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sharePriceDividendAdjustmentField = new FieldImpl(
				"sharePriceDividendAdjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.expectedNField = new FieldImpl(
				"expectedN",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialLevelField = new FieldImpl(
				"initialLevel",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialLevelSourceField = new FieldImpl(
				"initialLevelSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.meanAdjustmentField = new FieldImpl(
				"meanAdjustment",
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
			this.varianceStrikePriceField = new FieldImpl(
				"varianceStrikePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.volatilityStrikePriceField = new FieldImpl(
				"volatilityStrikePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.varianceCapFloorField = new FieldImpl(
				"varianceCapFloor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.volatilityCapFloorField = new FieldImpl(
				"volatilityCapFloor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.vegaNotionalAmountField = new FieldImpl(
				"vegaNotionalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exchangeTradedContractNearestField = new FieldImpl(
				"exchangeTradedContractNearest",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(VarianceReturnTerms input) {
			FieldValue valuationTerms = Optional.ofNullable(input.getValuationTerms())
				.map(x -> new NestedFieldValueImpl(valuationTermsField, Optional.of(valuationTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationTermsField, Optional.empty()));
			FieldValue annualizationFactor = new FieldValueImpl(annualizationFactorField, Optional.ofNullable(input.getAnnualizationFactor()));
			FieldValue dividendApplicability = Optional.ofNullable(input.getDividendApplicability())
				.map(x -> new NestedFieldValueImpl(dividendApplicabilityField, Optional.of(dividendApplicabilityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendApplicabilityField, Optional.empty()));
			FieldValue equityUnderlierProvisions = Optional.ofNullable(input.getEquityUnderlierProvisions())
				.map(x -> new NestedFieldValueImpl(equityUnderlierProvisionsField, Optional.of(equityUnderlierProvisionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(equityUnderlierProvisionsField, Optional.empty()));
			FieldValue sharePriceDividendAdjustment = new FieldValueImpl(sharePriceDividendAdjustmentField, Optional.ofNullable(input.getSharePriceDividendAdjustment()));
			FieldValue expectedN = new FieldValueImpl(expectedNField, Optional.ofNullable(input.getExpectedN()));
			FieldValue initialLevel = new FieldValueImpl(initialLevelField, Optional.ofNullable(input.getInitialLevel()));
			FieldValue initialLevelSource = new FieldValueImpl(initialLevelSourceField, Optional.ofNullable(input.getInitialLevelSource()));
			FieldValue meanAdjustment = new FieldValueImpl(meanAdjustmentField, Optional.ofNullable(input.getMeanAdjustment()));
			FieldValue performance = new FieldValueImpl(performanceField, Optional.ofNullable(input.getPerformance()));
			FieldValue varianceStrikePrice = Optional.ofNullable(input.getVarianceStrikePrice())
				.map(x -> new NestedFieldValueImpl(varianceStrikePriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(varianceStrikePriceField, Optional.empty()));
			FieldValue volatilityStrikePrice = Optional.ofNullable(input.getVolatilityStrikePrice())
				.map(x -> new NestedFieldValueImpl(volatilityStrikePriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(volatilityStrikePriceField, Optional.empty()));
			FieldValue varianceCapFloor = Optional.ofNullable(input.getVarianceCapFloor())
				.map(x -> new NestedFieldValueImpl(varianceCapFloorField, Optional.of(varianceCapFloorTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(varianceCapFloorField, Optional.empty()));
			FieldValue volatilityCapFloor = Optional.ofNullable(input.getVolatilityCapFloor())
				.map(x -> new NestedFieldValueImpl(volatilityCapFloorField, Optional.of(volatilityCapFloorTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(volatilityCapFloorField, Optional.empty()));
			FieldValue vegaNotionalAmount = Optional.ofNullable(input.getVegaNotionalAmount())
				.map(x -> new NestedFieldValueImpl(vegaNotionalAmountField, Optional.of(nonNegativeQuantityScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(vegaNotionalAmountField, Optional.empty()));
			FieldValue exchangeTradedContractNearest = Optional.ofNullable(input.getExchangeTradedContractNearest())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(exchangeTradedContractNearestField, Optional.of(observableTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exchangeTradedContractNearestField, Optional.empty()));
			return Arrays.asList(
				valuationTerms,
				annualizationFactor,
				dividendApplicability,
				equityUnderlierProvisions,
				sharePriceDividendAdjustment,
				expectedN,
				initialLevel,
				initialLevelSource,
				meanAdjustment,
				performance,
				varianceStrikePrice,
				volatilityStrikePrice,
				varianceCapFloor,
				volatilityCapFloor,
				vegaNotionalAmount,
				exchangeTradedContractNearest
			);
		}
	}
}
