package cdm.base.math.tabulator;

import cdm.base.math.NumberBound;
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


@ImplementedBy(NumberBoundTypeTabulator.Impl.class)
public interface NumberBoundTypeTabulator extends Tabulator<NumberBound> {
	@Singleton
	class Impl implements NumberBoundTypeTabulator {
		private final Field numberField;
		private final Field inclusiveField;

		public Impl() {
			this.numberField = new FieldImpl(
				"number",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.inclusiveField = new FieldImpl(
				"inclusive",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NumberBound input) {
			FieldValue number = new FieldValueImpl(numberField, Optional.ofNullable(input.getNumber()));
			FieldValue inclusive = new FieldValueImpl(inclusiveField, Optional.ofNullable(input.getInclusive()));
			return Arrays.asList(
				number,
				inclusive
			);
		}
	}
}
