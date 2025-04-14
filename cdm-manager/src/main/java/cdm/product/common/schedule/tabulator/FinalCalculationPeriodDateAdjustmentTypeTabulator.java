package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDatesTypeTabulator;
import cdm.product.asset.tabulator.InterestRatePayoutTypeTabulator;
import cdm.product.common.schedule.FinalCalculationPeriodDateAdjustment;
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


@ImplementedBy(FinalCalculationPeriodDateAdjustmentTypeTabulator.Impl.class)
public interface FinalCalculationPeriodDateAdjustmentTypeTabulator extends Tabulator<FinalCalculationPeriodDateAdjustment> {
	@Singleton
	class Impl implements FinalCalculationPeriodDateAdjustmentTypeTabulator {
		private final Field relevantUnderlyingDateReferenceField;
		private final Field swapStreamReferenceField;
		private final Field businessDayConventionField;

		private final AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator;
		private final InterestRatePayoutTypeTabulator interestRatePayoutTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator, InterestRatePayoutTypeTabulator interestRatePayoutTypeTabulator) {
			this.adjustableOrRelativeDatesTypeTabulator = adjustableOrRelativeDatesTypeTabulator;
			this.interestRatePayoutTypeTabulator = interestRatePayoutTypeTabulator;
			this.relevantUnderlyingDateReferenceField = new FieldImpl(
				"relevantUnderlyingDateReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.swapStreamReferenceField = new FieldImpl(
				"swapStreamReference",
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
		}

		@Override
		public List<FieldValue> tabulate(FinalCalculationPeriodDateAdjustment input) {
			FieldValue relevantUnderlyingDateReference = Optional.ofNullable(input.getRelevantUnderlyingDateReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(relevantUnderlyingDateReferenceField, Optional.of(adjustableOrRelativeDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relevantUnderlyingDateReferenceField, Optional.empty()));
			FieldValue swapStreamReference = Optional.ofNullable(input.getSwapStreamReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(swapStreamReferenceField, Optional.of(interestRatePayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(swapStreamReferenceField, Optional.empty()));
			FieldValue businessDayConvention = new FieldValueImpl(businessDayConventionField, Optional.ofNullable(input.getBusinessDayConvention()));
			return Arrays.asList(
				relevantUnderlyingDateReference,
				swapStreamReference,
				businessDayConvention
			);
		}
	}
}
