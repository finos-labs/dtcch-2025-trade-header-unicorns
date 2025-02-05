package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.Commodity;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
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


@ImplementedBy(CommodityTypeTabulator.Impl.class)
public interface CommodityTypeTabulator extends Tabulator<Commodity> {
	@Singleton
	class Impl implements CommodityTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field commodityProductDefinitionField;
		private final Field priceQuoteTypeField;
		private final Field deliveryDateReferenceField;
		private final Field descriptionField;

		private final AssetIdentifierTypeTabulator assetIdentifierTypeTabulator;
		private final TaxonomyTypeTabulator taxonomyTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final CommodityProductDefinitionTypeTabulator commodityProductDefinitionTypeTabulator;
		private final DeliveryDateParametersTypeTabulator deliveryDateParametersTypeTabulator;

		@Inject
		public Impl(AssetIdentifierTypeTabulator assetIdentifierTypeTabulator, TaxonomyTypeTabulator taxonomyTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator, CommodityProductDefinitionTypeTabulator commodityProductDefinitionTypeTabulator, DeliveryDateParametersTypeTabulator deliveryDateParametersTypeTabulator) {
			this.assetIdentifierTypeTabulator = assetIdentifierTypeTabulator;
			this.taxonomyTypeTabulator = taxonomyTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.commodityProductDefinitionTypeTabulator = commodityProductDefinitionTypeTabulator;
			this.deliveryDateParametersTypeTabulator = deliveryDateParametersTypeTabulator;
			this.identifierField = new FieldImpl(
				"identifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.taxonomyField = new FieldImpl(
				"taxonomy",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.isExchangeListedField = new FieldImpl(
				"isExchangeListed",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exchangeField = new FieldImpl(
				"exchange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relatedExchangeField = new FieldImpl(
				"relatedExchange",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commodityProductDefinitionField = new FieldImpl(
				"commodityProductDefinition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceQuoteTypeField = new FieldImpl(
				"priceQuoteType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryDateReferenceField = new FieldImpl(
				"deliveryDateReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.descriptionField = new FieldImpl(
				"description",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Commodity input) {
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> x.stream()
					.map(_x -> assetIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(identifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue taxonomy = Optional.ofNullable(input.getTaxonomy())
				.map(x -> x.stream()
					.map(_x -> taxonomyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(taxonomyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(taxonomyField, Optional.empty()));
			FieldValue isExchangeListed = new FieldValueImpl(isExchangeListedField, Optional.ofNullable(input.getIsExchangeListed()));
			FieldValue exchange = Optional.ofNullable(input.getExchange())
				.map(x -> new NestedFieldValueImpl(exchangeField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exchangeField, Optional.empty()));
			FieldValue relatedExchange = Optional.ofNullable(input.getRelatedExchange())
				.map(x -> x.stream()
					.map(_x -> legalEntityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(relatedExchangeField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(relatedExchangeField, Optional.empty()));
			FieldValue commodityProductDefinition = Optional.ofNullable(input.getCommodityProductDefinition())
				.map(x -> new NestedFieldValueImpl(commodityProductDefinitionField, Optional.of(commodityProductDefinitionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(commodityProductDefinitionField, Optional.empty()));
			FieldValue priceQuoteType = new FieldValueImpl(priceQuoteTypeField, Optional.ofNullable(input.getPriceQuoteType()));
			FieldValue deliveryDateReference = Optional.ofNullable(input.getDeliveryDateReference())
				.map(x -> new NestedFieldValueImpl(deliveryDateReferenceField, Optional.of(deliveryDateParametersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryDateReferenceField, Optional.empty()));
			FieldValue description = new FieldValueImpl(descriptionField, Optional.ofNullable(input.getDescription()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				commodityProductDefinition,
				priceQuoteType,
				deliveryDateReference,
				description
			);
		}
	}
}
