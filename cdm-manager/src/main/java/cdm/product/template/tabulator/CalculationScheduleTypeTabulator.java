package cdm.product.template.tabulator;

import cdm.product.template.CalculationSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CalculationScheduleTypeTabulator.Impl.class)
public interface CalculationScheduleTypeTabulator extends Tabulator<CalculationSchedule> {
	@Singleton
	class Impl implements CalculationScheduleTypeTabulator {
		private final Field schedulePeriodField;

		private final SchedulePeriodTypeTabulator schedulePeriodTypeTabulator;

		@Inject
		public Impl(SchedulePeriodTypeTabulator schedulePeriodTypeTabulator) {
			this.schedulePeriodTypeTabulator = schedulePeriodTypeTabulator;
			this.schedulePeriodField = new FieldImpl(
				"schedulePeriod",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalculationSchedule input) {
			FieldValue schedulePeriod = Optional.ofNullable(input.getSchedulePeriod())
				.map(x -> x.stream()
					.map(_x -> schedulePeriodTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(schedulePeriodField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(schedulePeriodField, Optional.empty()));
			return Arrays.asList(
				schedulePeriod
			);
		}
	}
}
