package cdm.product.template.tabulator;

import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.observable.asset.tabulator.ReferenceSwapCurveTypeTabulator;
import cdm.product.asset.tabulator.FixedRateSpecificationTypeTabulator;
import cdm.product.template.OptionStrike;
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


@ImplementedBy(OptionStrikeTypeTabulator.Impl.class)
public interface OptionStrikeTypeTabulator extends Tabulator<OptionStrike> {
	@Singleton
	class Impl implements OptionStrikeTypeTabulator {
		private final Field strikePriceField;
		private final Field strikeReferenceField;
		private final Field referenceSwapCurveField;
		private final Field averagingStrikeFeatureField;

		private final PriceTypeTabulator priceTypeTabulator;
		private final FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator;
		private final ReferenceSwapCurveTypeTabulator referenceSwapCurveTypeTabulator;
		private final AveragingStrikeFeatureTypeTabulator averagingStrikeFeatureTypeTabulator;

		@Inject
		public Impl(PriceTypeTabulator priceTypeTabulator, FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator, ReferenceSwapCurveTypeTabulator referenceSwapCurveTypeTabulator, AveragingStrikeFeatureTypeTabulator averagingStrikeFeatureTypeTabulator) {
			this.priceTypeTabulator = priceTypeTabulator;
			this.fixedRateSpecificationTypeTabulator = fixedRateSpecificationTypeTabulator;
			this.referenceSwapCurveTypeTabulator = referenceSwapCurveTypeTabulator;
			this.averagingStrikeFeatureTypeTabulator = averagingStrikeFeatureTypeTabulator;
			this.strikePriceField = new FieldImpl(
				"strikePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.strikeReferenceField = new FieldImpl(
				"strikeReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceSwapCurveField = new FieldImpl(
				"referenceSwapCurve",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingStrikeFeatureField = new FieldImpl(
				"averagingStrikeFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OptionStrike input) {
			FieldValue strikePrice = Optional.ofNullable(input.getStrikePrice())
				.map(x -> new NestedFieldValueImpl(strikePriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(strikePriceField, Optional.empty()));
			FieldValue strikeReference = Optional.ofNullable(input.getStrikeReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(strikeReferenceField, Optional.of(fixedRateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(strikeReferenceField, Optional.empty()));
			FieldValue referenceSwapCurve = Optional.ofNullable(input.getReferenceSwapCurve())
				.map(x -> new NestedFieldValueImpl(referenceSwapCurveField, Optional.of(referenceSwapCurveTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceSwapCurveField, Optional.empty()));
			FieldValue averagingStrikeFeature = Optional.ofNullable(input.getAveragingStrikeFeature())
				.map(x -> new NestedFieldValueImpl(averagingStrikeFeatureField, Optional.of(averagingStrikeFeatureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingStrikeFeatureField, Optional.empty()));
			return Arrays.asList(
				strikePrice,
				strikeReference,
				referenceSwapCurve,
				averagingStrikeFeature
			);
		}
	}
}
