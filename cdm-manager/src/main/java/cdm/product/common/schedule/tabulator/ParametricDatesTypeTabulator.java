package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.BusinessCentersTypeTabulator;
import cdm.product.common.schedule.ParametricDates;
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


@ImplementedBy(ParametricDatesTypeTabulator.Impl.class)
public interface ParametricDatesTypeTabulator extends Tabulator<ParametricDates> {
	@Singleton
	class Impl implements ParametricDatesTypeTabulator {
		private final Field dayTypeField;
		private final Field dayDistributionField;
		private final Field dayOfWeekField;
		private final Field dayFrequencyField;
		private final Field lagField;
		private final Field businessCentersField;

		private final LagTypeTabulator lagTypeTabulator;
		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;

		@Inject
		public Impl(LagTypeTabulator lagTypeTabulator, BusinessCentersTypeTabulator businessCentersTypeTabulator) {
			this.lagTypeTabulator = lagTypeTabulator;
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.dayTypeField = new FieldImpl(
				"dayType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayDistributionField = new FieldImpl(
				"dayDistribution",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayOfWeekField = new FieldImpl(
				"dayOfWeek",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayFrequencyField = new FieldImpl(
				"dayFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lagField = new FieldImpl(
				"lag",
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
		public List<FieldValue> tabulate(ParametricDates input) {
			FieldValue dayType = new FieldValueImpl(dayTypeField, Optional.ofNullable(input.getDayType()));
			FieldValue dayDistribution = new FieldValueImpl(dayDistributionField, Optional.ofNullable(input.getDayDistribution()));
			FieldValue dayOfWeek = new FieldValueImpl(dayOfWeekField, Optional.ofNullable(input.getDayOfWeek()));
			FieldValue dayFrequency = new FieldValueImpl(dayFrequencyField, Optional.ofNullable(input.getDayFrequency()));
			FieldValue lag = Optional.ofNullable(input.getLag())
				.map(x -> new NestedFieldValueImpl(lagField, Optional.of(lagTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lagField, Optional.empty()));
			FieldValue businessCenters = Optional.ofNullable(input.getBusinessCenters())
				.map(x -> new NestedFieldValueImpl(businessCentersField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersField, Optional.empty()));
			return Arrays.asList(
				dayType,
				dayDistribution,
				dayOfWeek,
				dayFrequency,
				lag,
				businessCenters
			);
		}
	}
}
