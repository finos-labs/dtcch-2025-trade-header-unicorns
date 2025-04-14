package cdm.base.math.tabulator;

import cdm.base.math.Measure;
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


@ImplementedBy(MeasureTypeTabulator.Impl.class)
public interface MeasureTypeTabulator extends Tabulator<Measure> {
	@Singleton
	class Impl implements MeasureTypeTabulator {
		private final Field valueField;
		private final Field unitField;

		private final UnitTypeTypeTabulator unitTypeTypeTabulator;

		@Inject
		public Impl(UnitTypeTypeTabulator unitTypeTypeTabulator) {
			this.unitTypeTypeTabulator = unitTypeTypeTabulator;
			this.valueField = new FieldImpl(
				"value",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unitField = new FieldImpl(
				"unit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Measure input) {
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue unit = Optional.ofNullable(input.getUnit())
				.map(x -> new NestedFieldValueImpl(unitField, Optional.of(unitTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(unitField, Optional.empty()));
			return Arrays.asList(
				value,
				unit
			);
		}
	}
}
