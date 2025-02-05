package cdm.product.asset.tabulator;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.common.schedule.tabulator.RateScheduleTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(FixedRateSpecificationTypeTabulator.Impl.class)
public interface FixedRateSpecificationTypeTabulator extends Tabulator<FixedRateSpecification> {
	@Singleton
	class Impl implements FixedRateSpecificationTypeTabulator {
		private final Field rateScheduleField;

		private final RateScheduleTypeTabulator rateScheduleTypeTabulator;

		@Inject
		public Impl(RateScheduleTypeTabulator rateScheduleTypeTabulator) {
			this.rateScheduleTypeTabulator = rateScheduleTypeTabulator;
			this.rateScheduleField = new FieldImpl(
				"rateSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FixedRateSpecification input) {
			FieldValue rateSchedule = Optional.ofNullable(input.getRateSchedule())
				.map(x -> new NestedFieldValueImpl(rateScheduleField, Optional.of(rateScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rateScheduleField, Optional.empty()));
			return Arrays.asList(
				rateSchedule
			);
		}
	}
}
