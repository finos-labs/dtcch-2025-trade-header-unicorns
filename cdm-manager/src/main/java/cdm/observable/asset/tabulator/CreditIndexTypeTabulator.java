package cdm.observable.asset.tabulator;

import cdm.base.staticdata.asset.common.tabulator.AssetIdentifierTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.TaxonomyTypeTabulator;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.observable.asset.CreditIndex;
import cdm.product.asset.tabulator.ReferenceInformationTypeTabulator;
import cdm.product.asset.tabulator.SettledEntityMatrixTypeTabulator;
import cdm.product.asset.tabulator.TrancheTypeTabulator;
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


@ImplementedBy(CreditIndexTypeTabulator.Impl.class)
public interface CreditIndexTypeTabulator extends Tabulator<CreditIndex> {
	@Singleton
	class Impl implements CreditIndexTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field nameField;
		private final Field providerField;
		private final Field assetClassField;
		private final Field indexSeriesField;
		private final Field indexAnnexVersionField;
		private final Field indexAnnexDateField;
		private final Field indexAnnexSourceField;
		private final Field excludedReferenceEntityField;
		private final Field trancheField;
		private final Field settledEntityMatrixField;
		private final Field indexFactorField;
		private final Field seniorityField;

		private final AssetIdentifierTypeTabulator assetIdentifierTypeTabulator;
		private final TaxonomyTypeTabulator taxonomyTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final ReferenceInformationTypeTabulator referenceInformationTypeTabulator;
		private final TrancheTypeTabulator trancheTypeTabulator;
		private final SettledEntityMatrixTypeTabulator settledEntityMatrixTypeTabulator;

		@Inject
		public Impl(AssetIdentifierTypeTabulator assetIdentifierTypeTabulator, TaxonomyTypeTabulator taxonomyTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator, ReferenceInformationTypeTabulator referenceInformationTypeTabulator, TrancheTypeTabulator trancheTypeTabulator, SettledEntityMatrixTypeTabulator settledEntityMatrixTypeTabulator) {
			this.assetIdentifierTypeTabulator = assetIdentifierTypeTabulator;
			this.taxonomyTypeTabulator = taxonomyTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.referenceInformationTypeTabulator = referenceInformationTypeTabulator;
			this.trancheTypeTabulator = trancheTypeTabulator;
			this.settledEntityMatrixTypeTabulator = settledEntityMatrixTypeTabulator;
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
			this.indexSeriesField = new FieldImpl(
				"indexSeries",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexAnnexVersionField = new FieldImpl(
				"indexAnnexVersion",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexAnnexDateField = new FieldImpl(
				"indexAnnexDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexAnnexSourceField = new FieldImpl(
				"indexAnnexSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.excludedReferenceEntityField = new FieldImpl(
				"excludedReferenceEntity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.trancheField = new FieldImpl(
				"tranche",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settledEntityMatrixField = new FieldImpl(
				"settledEntityMatrix",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexFactorField = new FieldImpl(
				"indexFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.seniorityField = new FieldImpl(
				"seniority",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditIndex input) {
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
			FieldValue indexSeries = new FieldValueImpl(indexSeriesField, Optional.ofNullable(input.getIndexSeries()));
			FieldValue indexAnnexVersion = new FieldValueImpl(indexAnnexVersionField, Optional.ofNullable(input.getIndexAnnexVersion()));
			FieldValue indexAnnexDate = new FieldValueImpl(indexAnnexDateField, Optional.ofNullable(input.getIndexAnnexDate()));
			FieldValue indexAnnexSource = new FieldValueImpl(indexAnnexSourceField, Optional.ofNullable(input.getIndexAnnexSource())
				.map(x -> x.getValue()));
			FieldValue excludedReferenceEntity = Optional.ofNullable(input.getExcludedReferenceEntity())
				.map(x -> x.stream()
					.map(_x -> referenceInformationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(excludedReferenceEntityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(excludedReferenceEntityField, Optional.empty()));
			FieldValue tranche = Optional.ofNullable(input.getTranche())
				.map(x -> new NestedFieldValueImpl(trancheField, Optional.of(trancheTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(trancheField, Optional.empty()));
			FieldValue settledEntityMatrix = Optional.ofNullable(input.getSettledEntityMatrix())
				.map(x -> new NestedFieldValueImpl(settledEntityMatrixField, Optional.of(settledEntityMatrixTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settledEntityMatrixField, Optional.empty()));
			FieldValue indexFactor = new FieldValueImpl(indexFactorField, Optional.ofNullable(input.getIndexFactor()));
			FieldValue seniority = new FieldValueImpl(seniorityField, Optional.ofNullable(input.getSeniority()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				name,
				provider,
				assetClass,
				indexSeries,
				indexAnnexVersion,
				indexAnnexDate,
				indexAnnexSource,
				excludedReferenceEntity,
				tranche,
				settledEntityMatrix,
				indexFactor,
				seniority
			);
		}
	}
}
