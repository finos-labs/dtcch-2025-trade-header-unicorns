package cdm.base.datetime.tabulator;

import cdm.base.datetime.AveragingSchedule;
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


@ImplementedBy(AveragingScheduleTypeTabulator.Impl.class)
public interface AveragingScheduleTypeTabulator extends Tabulator<AveragingSchedule> {
	@Singleton
	class Impl implements AveragingScheduleTypeTabulator {
		private final Field startDateField;
		private final Field endDateField;
		private final Field averagingPeriodFrequencyField;

		private final CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator;

		@Inject
		public Impl(CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator) {
			this.calculationPeriodFrequencyTypeTabulator = calculationPeriodFrequencyTypeTabulator;
			this.startDateField = new FieldImpl(
				"startDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.endDateField = new FieldImpl(
				"endDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingPeriodFrequencyField = new FieldImpl(
				"averagingPeriodFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AveragingSchedule input) {
			FieldValue startDate = new FieldValueImpl(startDateField, Optional.ofNullable(input.getStartDate()));
			FieldValue endDate = new FieldValueImpl(endDateField, Optional.ofNullable(input.getEndDate()));
			FieldValue averagingPeriodFrequency = Optional.ofNullable(input.getAveragingPeriodFrequency())
				.map(x -> new NestedFieldValueImpl(averagingPeriodFrequencyField, Optional.of(calculationPeriodFrequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingPeriodFrequencyField, Optional.empty()));
			return Arrays.asList(
				startDate,
				endDate,
				averagingPeriodFrequency
			);
		}
	}
}
