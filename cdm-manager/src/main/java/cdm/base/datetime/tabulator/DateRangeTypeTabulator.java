package cdm.base.datetime.tabulator;

import cdm.base.datetime.DateRange;
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


@ImplementedBy(DateRangeTypeTabulator.Impl.class)
public interface DateRangeTypeTabulator extends Tabulator<DateRange> {
	@Singleton
	class Impl implements DateRangeTypeTabulator {
		private final Field startDateField;
		private final Field endDateField;

		public Impl() {
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
		}

		@Override
		public List<FieldValue> tabulate(DateRange input) {
			FieldValue startDate = new FieldValueImpl(startDateField, Optional.ofNullable(input.getStartDate()));
			FieldValue endDate = new FieldValueImpl(endDateField, Optional.ofNullable(input.getEndDate()));
			return Arrays.asList(
				startDate,
				endDate
			);
		}
	}
}
