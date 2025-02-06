package cdm.product.common.settlement.tabulator;

import cdm.base.math.tabulator.RoundingTypeTabulator;
import cdm.product.asset.tabulator.SpreadScheduleTypeTabulator;
import cdm.product.common.settlement.CommodityPriceReturnTerms;
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


@ImplementedBy(CommodityPriceReturnTermsTypeTabulator.Impl.class)
public interface CommodityPriceReturnTermsTypeTabulator extends Tabulator<CommodityPriceReturnTerms> {
	@Singleton
	class Impl implements CommodityPriceReturnTermsTypeTabulator {
		private final Field roundingField;
		private final Field spreadField;
		private final Field rollFeatureField;
		private final Field conversionFactorField;

		private final RoundingTypeTabulator roundingTypeTabulator;
		private final SpreadScheduleTypeTabulator spreadScheduleTypeTabulator;
		private final RollFeatureTypeTabulator rollFeatureTypeTabulator;

		@Inject
		public Impl(RoundingTypeTabulator roundingTypeTabulator, SpreadScheduleTypeTabulator spreadScheduleTypeTabulator, RollFeatureTypeTabulator rollFeatureTypeTabulator) {
			this.roundingTypeTabulator = roundingTypeTabulator;
			this.spreadScheduleTypeTabulator = spreadScheduleTypeTabulator;
			this.rollFeatureTypeTabulator = rollFeatureTypeTabulator;
			this.roundingField = new FieldImpl(
				"rounding",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadField = new FieldImpl(
				"spread",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rollFeatureField = new FieldImpl(
				"rollFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.conversionFactorField = new FieldImpl(
				"conversionFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CommodityPriceReturnTerms input) {
			FieldValue rounding = Optional.ofNullable(input.getRounding())
				.map(x -> new NestedFieldValueImpl(roundingField, Optional.of(roundingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(roundingField, Optional.empty()));
			FieldValue spread = Optional.ofNullable(input.getSpread())
				.map(x -> new NestedFieldValueImpl(spreadField, Optional.of(spreadScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(spreadField, Optional.empty()));
			FieldValue rollFeature = Optional.ofNullable(input.getRollFeature())
				.map(x -> new NestedFieldValueImpl(rollFeatureField, Optional.of(rollFeatureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rollFeatureField, Optional.empty()));
			FieldValue conversionFactor = new FieldValueImpl(conversionFactorField, Optional.ofNullable(input.getConversionFactor()));
			return Arrays.asList(
				rounding,
				spread,
				rollFeature,
				conversionFactor
			);
		}
	}
}
