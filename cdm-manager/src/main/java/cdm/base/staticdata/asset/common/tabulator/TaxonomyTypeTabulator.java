package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.Taxonomy;
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


@ImplementedBy(TaxonomyTypeTabulator.Impl.class)
public interface TaxonomyTypeTabulator extends Tabulator<Taxonomy> {
	@Singleton
	class Impl implements TaxonomyTypeTabulator {
		private final Field sourceField;
		private final Field valueField;

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
		}

		@Override
		public List<FieldValue> tabulate(Taxonomy input) {
			FieldValue source = new FieldValueImpl(sourceField, Optional.ofNullable(input.getSource()));
			FieldValue value = Optional.ofNullable(input.getValue())
				.map(x -> new NestedFieldValueImpl(valueField, Optional.of(taxonomyValueTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valueField, Optional.empty()));
			return Arrays.asList(
				source,
				value
			);
		}
	}
}
