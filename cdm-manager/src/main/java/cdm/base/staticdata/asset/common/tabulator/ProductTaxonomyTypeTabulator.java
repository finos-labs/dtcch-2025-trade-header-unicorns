package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.ProductTaxonomy;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ProductTaxonomyTypeTabulator.Impl.class)
public interface ProductTaxonomyTypeTabulator extends Tabulator<ProductTaxonomy> {
	@Singleton
	class Impl implements ProductTaxonomyTypeTabulator {
		private final Field sourceField;
		private final Field valueField;
		private final Field primaryAssetClassField;
		private final Field secondaryAssetClassField;
		private final Field productQualifierField;

		private final TaxonomyValueTypeTabulator taxonomyValueTypeTabulator;

		@Inject
		public Impl(TaxonomyValueTypeTabulator taxonomyValueTypeTabulator) {
			this.taxonomyValueTypeTabulator = taxonomyValueTypeTabulator;
			this.sourceField = new FieldImpl(
				"source",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valueField = new FieldImpl(
				"value",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.primaryAssetClassField = new FieldImpl(
				"primaryAssetClass",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.secondaryAssetClassField = new FieldImpl(
				"secondaryAssetClass",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.productQualifierField = new FieldImpl(
				"productQualifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ProductTaxonomy input) {
			FieldValue source = new FieldValueImpl(sourceField, Optional.ofNullable(input.getSource()));
			FieldValue value = Optional.ofNullable(input.getValue())
				.map(x -> new NestedFieldValueImpl(valueField, Optional.of(taxonomyValueTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valueField, Optional.empty()));
			FieldValue primaryAssetClass = new FieldValueImpl(primaryAssetClassField, Optional.ofNullable(input.getPrimaryAssetClass())
				.map(x -> x.getValue()));
			FieldValue secondaryAssetClass = new FieldValueImpl(secondaryAssetClassField, Optional.ofNullable(input.getSecondaryAssetClass())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue productQualifier = new FieldValueImpl(productQualifierField, Optional.ofNullable(input.getProductQualifier()));
			return Arrays.asList(
				source,
				value,
				primaryAssetClass,
				secondaryAssetClass,
				productQualifier
			);
		}
	}
}
