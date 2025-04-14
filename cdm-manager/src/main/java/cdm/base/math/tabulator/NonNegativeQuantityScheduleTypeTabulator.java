package cdm.base.math.tabulator;

import cdm.base.datetime.tabulator.FrequencyTypeTabulator;
import cdm.base.math.NonNegativeQuantitySchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(NonNegativeQuantityScheduleTypeTabulator.Impl.class)
public interface NonNegativeQuantityScheduleTypeTabulator extends Tabulator<NonNegativeQuantitySchedule> {
	@Singleton
	class Impl implements NonNegativeQuantityScheduleTypeTabulator {
		private final Field valueField;
		private final Field unitField;
		private final Field datedValueField;
		private final Field multiplierField;
		private final Field frequencyField;

		private final UnitTypeTypeTabulator unitTypeTypeTabulator;
		private final DatedValueTypeTabulator datedValueTypeTabulator;
		private final MeasureTypeTabulator measureTypeTabulator;
		private final FrequencyTypeTabulator frequencyTypeTabulator;

		@Inject
		public Impl(UnitTypeTypeTabulator unitTypeTypeTabulator, DatedValueTypeTabulator datedValueTypeTabulator, MeasureTypeTabulator measureTypeTabulator, FrequencyTypeTabulator frequencyTypeTabulator) {
			this.unitTypeTypeTabulator = unitTypeTypeTabulator;
			this.datedValueTypeTabulator = datedValueTypeTabulator;
			this.measureTypeTabulator = measureTypeTabulator;
			this.frequencyTypeTabulator = frequencyTypeTabulator;
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
			this.datedValueField = new FieldImpl(
				"datedValue",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multiplierField = new FieldImpl(
				"multiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.frequencyField = new FieldImpl(
				"frequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NonNegativeQuantitySchedule input) {
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue unit = Optional.ofNullable(input.getUnit())
				.map(x -> new NestedFieldValueImpl(unitField, Optional.of(unitTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(unitField, Optional.empty()));
			FieldValue datedValue = Optional.ofNullable(input.getDatedValue())
				.map(x -> x.stream()
					.map(_x -> datedValueTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(datedValueField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(datedValueField, Optional.empty()));
			FieldValue multiplier = Optional.ofNullable(input.getMultiplier())
				.map(x -> new NestedFieldValueImpl(multiplierField, Optional.of(measureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(multiplierField, Optional.empty()));
			FieldValue frequency = Optional.ofNullable(input.getFrequency())
				.map(x -> new NestedFieldValueImpl(frequencyField, Optional.of(frequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(frequencyField, Optional.empty()));
			return Arrays.asList(
				value,
				unit,
				datedValue,
				multiplier,
				frequency
			);
		}
	}
}
