package cdm.base.datetime.tabulator;

import cdm.base.datetime.BusinessDateRange;
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


@ImplementedBy(BusinessDateRangeTypeTabulator.Impl.class)
public interface BusinessDateRangeTypeTabulator extends Tabulator<BusinessDateRange> {
	@Singleton
	class Impl implements BusinessDateRangeTypeTabulator {
		private final Field startDateField;
		private final Field endDateField;
		private final Field businessDayConventionField;
		private final Field businessCentersField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
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
			this.businessDayConventionField = new FieldImpl(
				"businessDayConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCentersField = new FieldImpl(
				"businessCenters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BusinessDateRange input) {
			FieldValue startDate = new FieldValueImpl(startDateField, Optional.ofNullable(input.getStartDate()));
			FieldValue endDate = new FieldValueImpl(endDateField, Optional.ofNullable(input.getEndDate()));
			FieldValue businessDayConvention = new FieldValueImpl(businessDayConventionField, Optional.ofNullable(input.getBusinessDayConvention()));
			FieldValue businessCenters = Optional.ofNullable(input.getBusinessCenters())
				.map(x -> new NestedFieldValueImpl(businessCentersField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersField, Optional.empty()));
			return Arrays.asList(
				startDate,
				endDate,
				businessDayConvention,
				businessCenters
			);
		}
	}
}
