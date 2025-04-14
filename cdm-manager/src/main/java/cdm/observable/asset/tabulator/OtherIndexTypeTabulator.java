package cdm.observable.asset.tabulator;

import cdm.base.staticdata.asset.common.tabulator.AssetIdentifierTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.TaxonomyTypeTabulator;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.observable.asset.OtherIndex;
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


@ImplementedBy(OtherIndexTypeTabulator.Impl.class)
public interface OtherIndexTypeTabulator extends Tabulator<OtherIndex> {
	@Singleton
	class Impl implements OtherIndexTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field nameField;
		private final Field providerField;
		private final Field assetClassField;
		private final Field descriptionField;

		private final AssetIdentifierTypeTabulator assetIdentifierTypeTabulator;
		private final TaxonomyTypeTabulator taxonomyTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;

		@Inject
		public Impl(AssetIdentifierTypeTabulator assetIdentifierTypeTabulator, TaxonomyTypeTabulator taxonomyTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator) {
			this.assetIdentifierTypeTabulator = assetIdentifierTypeTabulator;
			this.taxonomyTypeTabulator = taxonomyTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
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
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.providerField = new FieldImpl(
				"provider",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.assetClassField = new FieldImpl(
				"assetClass",
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
		public List<FieldValue> tabulate(OtherIndex input) {
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
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName())
				.map(x -> x.getValue()));
			FieldValue provider = Optional.ofNullable(input.getProvider())
				.map(x -> new NestedFieldValueImpl(providerField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(providerField, Optional.empty()));
			FieldValue assetClass = new FieldValueImpl(assetClassField, Optional.ofNullable(input.getAssetClass()));
			FieldValue description = new FieldValueImpl(descriptionField, Optional.ofNullable(input.getDescription()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				name,
				provider,
				assetClass,
				description
			);
		}
	}
}
