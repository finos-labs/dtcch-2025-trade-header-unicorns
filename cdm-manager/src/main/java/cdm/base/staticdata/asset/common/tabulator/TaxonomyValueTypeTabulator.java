package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.TaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(TaxonomyValueTypeTabulator.Impl.class)
public interface TaxonomyValueTypeTabulator extends Tabulator<TaxonomyValue> {
	@Singleton
	class Impl implements TaxonomyValueTypeTabulator {
		private final Field nameField;
		private final Field classificationField;

		private final TaxonomyClassificationTypeTabulator taxonomyClassificationTypeTabulator;

		@Inject
		public Impl(TaxonomyClassificationTypeTabulator taxonomyClassificationTypeTabulator) {
			this.taxonomyClassificationTypeTabulator = taxonomyClassificationTypeTabulator;
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.classificationField = new FieldImpl(
				"classification",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TaxonomyValue input) {
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName())
				.map(x -> x.getValue()));
			FieldValue classification = Optional.ofNullable(input.getClassification())
				.map(x -> x.stream()
					.map(_x -> taxonomyClassificationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(classificationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(classificationField, Optional.empty()));
			return Arrays.asList(
				name,
				classification
			);
		}
	}
}
