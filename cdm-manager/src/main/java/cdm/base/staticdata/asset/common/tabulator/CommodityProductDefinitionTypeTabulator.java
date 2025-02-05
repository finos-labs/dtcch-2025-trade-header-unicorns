package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.CommodityProductDefinition;
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


@ImplementedBy(CommodityProductDefinitionTypeTabulator.Impl.class)
public interface CommodityProductDefinitionTypeTabulator extends Tabulator<CommodityProductDefinition> {
	@Singleton
	class Impl implements CommodityProductDefinitionTypeTabulator {
		private final Field referenceFrameworkField;
		private final Field priceSourceField;
		private final Field commodityInfoPublisherField;
		private final Field exchangeIdField;

		private final CommodityReferenceFrameworkTypeTabulator commodityReferenceFrameworkTypeTabulator;
		private final PriceSourceTypeTabulator priceSourceTypeTabulator;

		@Inject
		public Impl(CommodityReferenceFrameworkTypeTabulator commodityReferenceFrameworkTypeTabulator, PriceSourceTypeTabulator priceSourceTypeTabulator) {
			this.commodityReferenceFrameworkTypeTabulator = commodityReferenceFrameworkTypeTabulator;
			this.priceSourceTypeTabulator = priceSourceTypeTabulator;
			this.referenceFrameworkField = new FieldImpl(
				"referenceFramework",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceSourceField = new FieldImpl(
				"priceSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commodityInfoPublisherField = new FieldImpl(
				"commodityInfoPublisher",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exchangeIdField = new FieldImpl(
				"exchangeId",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CommodityProductDefinition input) {
			FieldValue referenceFramework = Optional.ofNullable(input.getReferenceFramework())
				.map(x -> new NestedFieldValueImpl(referenceFrameworkField, Optional.of(commodityReferenceFrameworkTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceFrameworkField, Optional.empty()));
			FieldValue priceSource = Optional.ofNullable(input.getPriceSource())
				.map(x -> new NestedFieldValueImpl(priceSourceField, Optional.of(priceSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceSourceField, Optional.empty()));
			FieldValue commodityInfoPublisher = new FieldValueImpl(commodityInfoPublisherField, Optional.ofNullable(input.getCommodityInfoPublisher()));
			FieldValue exchangeId = new FieldValueImpl(exchangeIdField, Optional.ofNullable(input.getExchangeId())
				.map(x -> x.getValue()));
			return Arrays.asList(
				referenceFramework,
				priceSource,
				commodityInfoPublisher,
				exchangeId
			);
		}
	}
}
