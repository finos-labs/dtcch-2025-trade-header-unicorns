package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.CollateralTaxonomy;
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


@ImplementedBy(CollateralTaxonomyTypeTabulator.Impl.class)
public interface CollateralTaxonomyTypeTabulator extends Tabulator<CollateralTaxonomy> {
	@Singleton
	class Impl implements CollateralTaxonomyTypeTabulator {
		private final Field taxonomyValueField;
		private final Field taxonomySourceField;

		private final CollateralTaxonomyValueTypeTabulator collateralTaxonomyValueTypeTabulator;

		@Inject
		public Impl(CollateralTaxonomyValueTypeTabulator collateralTaxonomyValueTypeTabulator) {
			this.collateralTaxonomyValueTypeTabulator = collateralTaxonomyValueTypeTabulator;
			this.taxonomyValueField = new FieldImpl(
				"taxonomyValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.taxonomySourceField = new FieldImpl(
				"taxonomySource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralTaxonomy input) {
			FieldValue taxonomyValue = Optional.ofNullable(input.getTaxonomyValue())
				.map(x -> new NestedFieldValueImpl(taxonomyValueField, Optional.of(collateralTaxonomyValueTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(taxonomyValueField, Optional.empty()));
			FieldValue taxonomySource = new FieldValueImpl(taxonomySourceField, Optional.ofNullable(input.getTaxonomySource()));
			return Arrays.asList(
				taxonomyValue,
				taxonomySource
			);
		}
	}
}
