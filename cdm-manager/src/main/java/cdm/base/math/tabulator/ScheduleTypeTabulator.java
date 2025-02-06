package cdm.base.math.tabulator;

import cdm.base.math.Schedule;
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


@ImplementedBy(ScheduleTypeTabulator.Impl.class)
public interface ScheduleTypeTabulator extends Tabulator<Schedule> {
	@Singleton
	class Impl implements ScheduleTypeTabulator {
		private final Field valueField;
		private final Field datedValueField;

		private final DatedValueTypeTabulator datedValueTypeTabulator;

		@Inject
		public Impl(DatedValueTypeTabulator datedValueTypeTabulator) {
			this.datedValueTypeTabulator = datedValueTypeTabulator;
			this.valueField = new FieldImpl(
				"value",
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
		}

		@Override
		public List<FieldValue> tabulate(Schedule input) {
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue datedValue = Optional.ofNullable(input.getDatedValue())
				.map(x -> x.stream()
					.map(_x -> datedValueTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(datedValueField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(datedValueField, Optional.empty()));
			return Arrays.asList(
				value,
				datedValue
			);
		}
	}
}
