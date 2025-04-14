package cdm.product.collateral.tabulator;

import cdm.base.staticdata.asset.common.tabulator.AssetTypeTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.CollateralIssuerTypeTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.CollateralTaxonomyTypeTabulator;
import cdm.observable.asset.tabulator.IndexTypeTabulator;
import cdm.product.collateral.CollateralCriteria;
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


@ImplementedBy(CollateralCriteriaTypeTabulator.Impl.class)
public interface CollateralCriteriaTypeTabulator extends Tabulator<CollateralCriteria> {
	@Singleton
	class Impl implements CollateralCriteriaTypeTabulator {
		private final Field AllCriteriaField;
		private final Field AnyCriteriaField;
		private final Field NegativeCriteriaField;
		private final Field CollateralIssuerTypeField;
		private final Field AssetTypeField;
		private final Field IssuerCountryOfOriginField;
		private final Field AssetCountryOfOriginField;
		private final Field CurrencyCodeEnumField;
		private final Field IssuerNameField;
		private final Field IssuerAgencyRatingField;
		private final Field SovereignAgencyRatingField;
		private final Field AssetAgencyRatingField;
		private final Field AssetMaturityField;
		private final Field SpecificAssetField;
		private final Field CollateralTaxonomyField;
		private final Field ListingExchangeField;
		private final Field ListingSectorField;
		private final Field IndexField;
		private final Field CounterpartyOwnIssuePermittedField;
		private final Field DomesticCurrencyIssuedField;

		private final AllCriteriaTypeTabulator allCriteriaTypeTabulator;
		private final AnyCriteriaTypeTabulator anyCriteriaTypeTabulator;
		private final NegativeCriteriaTypeTabulator negativeCriteriaTypeTabulator;
		private final CollateralIssuerTypeTypeTabulator collateralIssuerTypeTypeTabulator;
		private final AssetTypeTypeTabulator assetTypeTypeTabulator;
		private final IssuerCountryOfOriginTypeTabulator issuerCountryOfOriginTypeTabulator;
		private final AssetCountryOfOriginTypeTabulator assetCountryOfOriginTypeTabulator;
		private final IssuerNameTypeTabulator issuerNameTypeTabulator;
		private final IssuerAgencyRatingTypeTabulator issuerAgencyRatingTypeTabulator;
		private final SovereignAgencyRatingTypeTabulator sovereignAgencyRatingTypeTabulator;
		private final AssetAgencyRatingTypeTabulator assetAgencyRatingTypeTabulator;
		private final AssetMaturityTypeTabulator assetMaturityTypeTabulator;
		private final SpecificAssetTypeTabulator specificAssetTypeTabulator;
		private final CollateralTaxonomyTypeTabulator collateralTaxonomyTypeTabulator;
		private final ListingExchangeTypeTabulator listingExchangeTypeTabulator;
		private final ListingSectorTypeTabulator listingSectorTypeTabulator;
		private final IndexTypeTabulator indexTypeTabulator;
		private final CounterpartyOwnIssuePermittedTypeTabulator counterpartyOwnIssuePermittedTypeTabulator;
		private final DomesticCurrencyIssuedTypeTabulator domesticCurrencyIssuedTypeTabulator;

		@Inject
		public Impl(AllCriteriaTypeTabulator allCriteriaTypeTabulator, AnyCriteriaTypeTabulator anyCriteriaTypeTabulator, NegativeCriteriaTypeTabulator negativeCriteriaTypeTabulator, CollateralIssuerTypeTypeTabulator collateralIssuerTypeTypeTabulator, AssetTypeTypeTabulator assetTypeTypeTabulator, IssuerCountryOfOriginTypeTabulator issuerCountryOfOriginTypeTabulator, AssetCountryOfOriginTypeTabulator assetCountryOfOriginTypeTabulator, IssuerNameTypeTabulator issuerNameTypeTabulator, IssuerAgencyRatingTypeTabulator issuerAgencyRatingTypeTabulator, SovereignAgencyRatingTypeTabulator sovereignAgencyRatingTypeTabulator, AssetAgencyRatingTypeTabulator assetAgencyRatingTypeTabulator, AssetMaturityTypeTabulator assetMaturityTypeTabulator, SpecificAssetTypeTabulator specificAssetTypeTabulator, CollateralTaxonomyTypeTabulator collateralTaxonomyTypeTabulator, ListingExchangeTypeTabulator listingExchangeTypeTabulator, ListingSectorTypeTabulator listingSectorTypeTabulator, IndexTypeTabulator indexTypeTabulator, CounterpartyOwnIssuePermittedTypeTabulator counterpartyOwnIssuePermittedTypeTabulator, DomesticCurrencyIssuedTypeTabulator domesticCurrencyIssuedTypeTabulator) {
			this.allCriteriaTypeTabulator = allCriteriaTypeTabulator;
			this.anyCriteriaTypeTabulator = anyCriteriaTypeTabulator;
			this.negativeCriteriaTypeTabulator = negativeCriteriaTypeTabulator;
			this.collateralIssuerTypeTypeTabulator = collateralIssuerTypeTypeTabulator;
			this.assetTypeTypeTabulator = assetTypeTypeTabulator;
			this.issuerCountryOfOriginTypeTabulator = issuerCountryOfOriginTypeTabulator;
			this.assetCountryOfOriginTypeTabulator = assetCountryOfOriginTypeTabulator;
			this.issuerNameTypeTabulator = issuerNameTypeTabulator;
			this.issuerAgencyRatingTypeTabulator = issuerAgencyRatingTypeTabulator;
			this.sovereignAgencyRatingTypeTabulator = sovereignAgencyRatingTypeTabulator;
			this.assetAgencyRatingTypeTabulator = assetAgencyRatingTypeTabulator;
			this.assetMaturityTypeTabulator = assetMaturityTypeTabulator;
			this.specificAssetTypeTabulator = specificAssetTypeTabulator;
			this.collateralTaxonomyTypeTabulator = collateralTaxonomyTypeTabulator;
			this.listingExchangeTypeTabulator = listingExchangeTypeTabulator;
			this.listingSectorTypeTabulator = listingSectorTypeTabulator;
			this.indexTypeTabulator = indexTypeTabulator;
			this.counterpartyOwnIssuePermittedTypeTabulator = counterpartyOwnIssuePermittedTypeTabulator;
			this.domesticCurrencyIssuedTypeTabulator = domesticCurrencyIssuedTypeTabulator;
			this.AllCriteriaField = new FieldImpl(
				"AllCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.AnyCriteriaField = new FieldImpl(
				"AnyCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.NegativeCriteriaField = new FieldImpl(
				"NegativeCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CollateralIssuerTypeField = new FieldImpl(
				"CollateralIssuerType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.AssetTypeField = new FieldImpl(
				"AssetType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.IssuerCountryOfOriginField = new FieldImpl(
				"IssuerCountryOfOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.AssetCountryOfOriginField = new FieldImpl(
				"AssetCountryOfOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CurrencyCodeEnumField = new FieldImpl(
				"CurrencyCodeEnum",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.IssuerNameField = new FieldImpl(
				"IssuerName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.IssuerAgencyRatingField = new FieldImpl(
				"IssuerAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.SovereignAgencyRatingField = new FieldImpl(
				"SovereignAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.AssetAgencyRatingField = new FieldImpl(
				"AssetAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.AssetMaturityField = new FieldImpl(
				"AssetMaturity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.SpecificAssetField = new FieldImpl(
				"SpecificAsset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CollateralTaxonomyField = new FieldImpl(
				"CollateralTaxonomy",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ListingExchangeField = new FieldImpl(
				"ListingExchange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ListingSectorField = new FieldImpl(
				"ListingSector",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.IndexField = new FieldImpl(
				"Index",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CounterpartyOwnIssuePermittedField = new FieldImpl(
				"CounterpartyOwnIssuePermitted",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.DomesticCurrencyIssuedField = new FieldImpl(
				"DomesticCurrencyIssued",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralCriteria input) {
			FieldValue AllCriteria = Optional.ofNullable(input.getAllCriteria())
				.map(x -> new NestedFieldValueImpl(AllCriteriaField, Optional.of(allCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AllCriteriaField, Optional.empty()));
			FieldValue AnyCriteria = Optional.ofNullable(input.getAnyCriteria())
				.map(x -> new NestedFieldValueImpl(AnyCriteriaField, Optional.of(anyCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AnyCriteriaField, Optional.empty()));
			FieldValue NegativeCriteria = Optional.ofNullable(input.getNegativeCriteria())
				.map(x -> new NestedFieldValueImpl(NegativeCriteriaField, Optional.of(negativeCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(NegativeCriteriaField, Optional.empty()));
			FieldValue CollateralIssuerType = Optional.ofNullable(input.getCollateralIssuerType())
				.map(x -> new NestedFieldValueImpl(CollateralIssuerTypeField, Optional.of(collateralIssuerTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CollateralIssuerTypeField, Optional.empty()));
			FieldValue AssetType = Optional.ofNullable(input.getAssetType())
				.map(x -> new NestedFieldValueImpl(AssetTypeField, Optional.of(assetTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetTypeField, Optional.empty()));
			FieldValue IssuerCountryOfOrigin = Optional.ofNullable(input.getIssuerCountryOfOrigin())
				.map(x -> new NestedFieldValueImpl(IssuerCountryOfOriginField, Optional.of(issuerCountryOfOriginTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IssuerCountryOfOriginField, Optional.empty()));
			FieldValue AssetCountryOfOrigin = Optional.ofNullable(input.getAssetCountryOfOrigin())
				.map(x -> new NestedFieldValueImpl(AssetCountryOfOriginField, Optional.of(assetCountryOfOriginTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetCountryOfOriginField, Optional.empty()));
			FieldValue CurrencyCodeEnum = new FieldValueImpl(CurrencyCodeEnumField, Optional.ofNullable(input.getCurrencyCodeEnum()));
			FieldValue IssuerName = Optional.ofNullable(input.getIssuerName())
				.map(x -> new NestedFieldValueImpl(IssuerNameField, Optional.of(issuerNameTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IssuerNameField, Optional.empty()));
			FieldValue IssuerAgencyRating = Optional.ofNullable(input.getIssuerAgencyRating())
				.map(x -> new NestedFieldValueImpl(IssuerAgencyRatingField, Optional.of(issuerAgencyRatingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IssuerAgencyRatingField, Optional.empty()));
			FieldValue SovereignAgencyRating = Optional.ofNullable(input.getSovereignAgencyRating())
				.map(x -> new NestedFieldValueImpl(SovereignAgencyRatingField, Optional.of(sovereignAgencyRatingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(SovereignAgencyRatingField, Optional.empty()));
			FieldValue AssetAgencyRating = Optional.ofNullable(input.getAssetAgencyRating())
				.map(x -> new NestedFieldValueImpl(AssetAgencyRatingField, Optional.of(assetAgencyRatingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetAgencyRatingField, Optional.empty()));
			FieldValue AssetMaturity = Optional.ofNullable(input.getAssetMaturity())
				.map(x -> new NestedFieldValueImpl(AssetMaturityField, Optional.of(assetMaturityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetMaturityField, Optional.empty()));
			FieldValue SpecificAsset = Optional.ofNullable(input.getSpecificAsset())
				.map(x -> new NestedFieldValueImpl(SpecificAssetField, Optional.of(specificAssetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(SpecificAssetField, Optional.empty()));
			FieldValue CollateralTaxonomy = Optional.ofNullable(input.getCollateralTaxonomy())
				.map(x -> new NestedFieldValueImpl(CollateralTaxonomyField, Optional.of(collateralTaxonomyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CollateralTaxonomyField, Optional.empty()));
			FieldValue ListingExchange = Optional.ofNullable(input.getListingExchange())
				.map(x -> new NestedFieldValueImpl(ListingExchangeField, Optional.of(listingExchangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ListingExchangeField, Optional.empty()));
			FieldValue ListingSector = Optional.ofNullable(input.getListingSector())
				.map(x -> new NestedFieldValueImpl(ListingSectorField, Optional.of(listingSectorTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ListingSectorField, Optional.empty()));
			FieldValue Index = Optional.ofNullable(input.getIndex())
				.map(x -> new NestedFieldValueImpl(IndexField, Optional.of(indexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IndexField, Optional.empty()));
			FieldValue CounterpartyOwnIssuePermitted = Optional.ofNullable(input.getCounterpartyOwnIssuePermitted())
				.map(x -> new NestedFieldValueImpl(CounterpartyOwnIssuePermittedField, Optional.of(counterpartyOwnIssuePermittedTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CounterpartyOwnIssuePermittedField, Optional.empty()));
			FieldValue DomesticCurrencyIssued = Optional.ofNullable(input.getDomesticCurrencyIssued())
				.map(x -> new NestedFieldValueImpl(DomesticCurrencyIssuedField, Optional.of(domesticCurrencyIssuedTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(DomesticCurrencyIssuedField, Optional.empty()));
			return Arrays.asList(
				AllCriteria,
				AnyCriteria,
				NegativeCriteria,
				CollateralIssuerType,
				AssetType,
				IssuerCountryOfOrigin,
				AssetCountryOfOrigin,
				CurrencyCodeEnum,
				IssuerName,
				IssuerAgencyRating,
				SovereignAgencyRating,
				AssetAgencyRating,
				AssetMaturity,
				SpecificAsset,
				CollateralTaxonomy,
				ListingExchange,
				ListingSector,
				Index,
				CounterpartyOwnIssuePermitted,
				DomesticCurrencyIssued
			);
		}
	}
}
