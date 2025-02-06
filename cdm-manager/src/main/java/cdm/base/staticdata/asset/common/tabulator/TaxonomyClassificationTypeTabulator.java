package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.TaxonomyClassification;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(TaxonomyClassificationTypeTabulator.Impl.class)
public interface TaxonomyClassificationTypeTabulator extends Tabulator<TaxonomyClassification> {
	@Singleton
	class Impl implements TaxonomyClassificationTypeTabulator {
		private final Field classNameField;
		private final Field valueField;
		private final Field descriptionField;
		private final Field ordinalField;

		public Impl() {
			this.classNameField = new FieldImpl(
				"className",
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
			this.descriptionField = new FieldImpl(
				"description",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ordinalField = new FieldImpl(
				"ordinal",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TaxonomyClassification input) {
			FieldValue className = new FieldValueImpl(classNameField, Optional.ofNullable(input.getClassName()));
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue description = new FieldValueImpl(descriptionField, Optional.ofNullable(input.getDescription()));
			FieldValue ordinal = new FieldValueImpl(ordinalField, Optional.ofNullable(input.getOrdinal()));
			return Arrays.asList(
				className,
				value,
				description,
				ordinal
			);
		}
	}
}
