package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.ResourceLength;
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


@ImplementedBy(ResourceLengthTypeTabulator.Impl.class)
public interface ResourceLengthTypeTabulator extends Tabulator<ResourceLength> {
	@Singleton
	class Impl implements ResourceLengthTypeTabulator {
		private final Field lengthUnitField;
		private final Field lengthValueField;

		public Impl() {
			this.lengthUnitField = new FieldImpl(
				"lengthUnit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lengthValueField = new FieldImpl(
				"lengthValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ResourceLength input) {
			FieldValue lengthUnit = new FieldValueImpl(lengthUnitField, Optional.ofNullable(input.getLengthUnit()));
			FieldValue lengthValue = new FieldValueImpl(lengthValueField, Optional.ofNullable(input.getLengthValue()));
			return Arrays.asList(
				lengthUnit,
				lengthValue
			);
		}
	}
}
