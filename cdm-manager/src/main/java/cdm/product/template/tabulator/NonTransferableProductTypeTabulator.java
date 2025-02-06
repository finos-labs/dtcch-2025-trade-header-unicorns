package cdm.product.template.tabulator;

import cdm.base.staticdata.asset.common.tabulator.ProductIdentifierTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.ProductTaxonomyTypeTabulator;
import cdm.product.template.NonTransferableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(NonTransferableProductTypeTabulator.Impl.class)
public interface NonTransferableProductTypeTabulator extends Tabulator<NonTransferableProduct> {
	@Singleton
	class Impl implements NonTransferableProductTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field economicTermsField;

		private final ProductIdentifierTypeTabulator productIdentifierTypeTabulator;
		private final ProductTaxonomyTypeTabulator productTaxonomyTypeTabulator;
		private final EconomicTermsTypeTabulator economicTermsTypeTabulator;

		@Inject
		public Impl(ProductIdentifierTypeTabulator productIdentifierTypeTabulator, ProductTaxonomyTypeTabulator productTaxonomyTypeTabulator, EconomicTermsTypeTabulator economicTermsTypeTabulator) {
			this.productIdentifierTypeTabulator = productIdentifierTypeTabulator;
			this.productTaxonomyTypeTabulator = productTaxonomyTypeTabulator;
			this.economicTermsTypeTabulator = economicTermsTypeTabulator;
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
			this.economicTermsField = new FieldImpl(
				"economicTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NonTransferableProduct input) {
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> x.stream()
					.map(_x -> productIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(identifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue taxonomy = Optional.ofNullable(input.getTaxonomy())
				.map(x -> x.stream()
					.map(_x -> productTaxonomyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(taxonomyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(taxonomyField, Optional.empty()));
			FieldValue economicTerms = Optional.ofNullable(input.getEconomicTerms())
				.map(x -> new NestedFieldValueImpl(economicTermsField, Optional.of(economicTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(economicTermsField, Optional.empty()));
			return Arrays.asList(
				identifier,
				taxonomy,
				economicTerms
			);
		}
	}
}
