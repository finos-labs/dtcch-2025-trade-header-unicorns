package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.NumberRangeTypeTabulator;
import cdm.observable.asset.tabulator.DividendApplicabilityTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.CorrelationReturnTerms;
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


@ImplementedBy(CorrelationReturnTermsTypeTabulator.Impl.class)
public interface CorrelationReturnTermsTypeTabulator extends Tabulator<CorrelationReturnTerms> {
	@Singleton
	class Impl implements CorrelationReturnTermsTypeTabulator {
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
		private final Field correlationStrikePriceField;
		private final Field boundedCorrelationField;
		private final Field numberOfDataSeriesField;

		private final ValuationTermsTypeTabulator valuationTermsTypeTabulator;
		private final DividendApplicabilityTypeTabulator dividendApplicabilityTypeTabulator;
		private final EquityUnderlierProvisionsTypeTabulator equityUnderlierProvisionsTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;
		private final NumberRangeTypeTabulator numberRangeTypeTabulator;

		@Inject
		public Impl(ValuationTermsTypeTabulator valuationTermsTypeTabulator, DividendApplicabilityTypeTabulator dividendApplicabilityTypeTabulator, EquityUnderlierProvisionsTypeTabulator equityUnderlierProvisionsTypeTabulator, PriceTypeTabulator priceTypeTabulator, NumberRangeTypeTabulator numberRangeTypeTabulator) {
			this.valuationTermsTypeTabulator = valuationTermsTypeTabulator;
			this.dividendApplicabilityTypeTabulator = dividendApplicabilityTypeTabulator;
			this.equityUnderlierProvisionsTypeTabulator = equityUnderlierProvisionsTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.numberRangeTypeTabulator = numberRangeTypeTabulator;
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
			this.correlationStrikePriceField = new FieldImpl(
				"correlationStrikePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.boundedCorrelationField = new FieldImpl(
				"boundedCorrelation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.numberOfDataSeriesField = new FieldImpl(
				"numberOfDataSeries",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CorrelationReturnTerms input) {
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
			FieldValue correlationStrikePrice = Optional.ofNullable(input.getCorrelationStrikePrice())
				.map(x -> new NestedFieldValueImpl(correlationStrikePriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(correlationStrikePriceField, Optional.empty()));
			FieldValue boundedCorrelation = Optional.ofNullable(input.getBoundedCorrelation())
				.map(x -> new NestedFieldValueImpl(boundedCorrelationField, Optional.of(numberRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(boundedCorrelationField, Optional.empty()));
			FieldValue numberOfDataSeries = new FieldValueImpl(numberOfDataSeriesField, Optional.ofNullable(input.getNumberOfDataSeries()));
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
				correlationStrikePrice,
				boundedCorrelation,
				numberOfDataSeries
			);
		}
	}
}
