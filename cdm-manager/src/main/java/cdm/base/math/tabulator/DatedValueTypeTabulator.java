package cdm.base.math.tabulator;

import cdm.base.math.DatedValue;
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


@ImplementedBy(DatedValueTypeTabulator.Impl.class)
public interface DatedValueTypeTabulator extends Tabulator<DatedValue> {
	@Singleton
	class Impl implements DatedValueTypeTabulator {
		private final Field dateField;
		private final Field valueField;

		public Impl() {
			this.dateField = new FieldImpl(
				"date",
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
		public List<FieldValue> tabulate(DatedValue input) {
			FieldValue date = new FieldValueImpl(dateField, Optional.ofNullable(input.getDate()));
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			return Arrays.asList(
				date,
				value
			);
		}
	}
}
