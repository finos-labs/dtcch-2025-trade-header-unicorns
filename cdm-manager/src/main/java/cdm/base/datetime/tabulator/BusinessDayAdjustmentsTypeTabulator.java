package cdm.base.datetime.tabulator;

import cdm.base.datetime.BusinessDayAdjustments;
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


@ImplementedBy(BusinessDayAdjustmentsTypeTabulator.Impl.class)
public interface BusinessDayAdjustmentsTypeTabulator extends Tabulator<BusinessDayAdjustments> {
	@Singleton
	class Impl implements BusinessDayAdjustmentsTypeTabulator {
		private final Field businessDayConventionField;
		private final Field businessCentersField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
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
		public List<FieldValue> tabulate(BusinessDayAdjustments input) {
			FieldValue businessDayConvention = new FieldValueImpl(businessDayConventionField, Optional.ofNullable(input.getBusinessDayConvention()));
			FieldValue businessCenters = Optional.ofNullable(input.getBusinessCenters())
				.map(x -> new NestedFieldValueImpl(businessCentersField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersField, Optional.empty()));
			return Arrays.asList(
				businessDayConvention,
				businessCenters
			);
		}
	}
}
