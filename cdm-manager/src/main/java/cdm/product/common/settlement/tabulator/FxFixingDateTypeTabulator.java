package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessCentersTypeTabulator;
import cdm.product.common.schedule.tabulator.DateRelativeToCalculationPeriodDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.DateRelativeToPaymentDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.DateRelativeToValuationDatesTypeTabulator;
import cdm.product.common.settlement.FxFixingDate;
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


@ImplementedBy(FxFixingDateTypeTabulator.Impl.class)
public interface FxFixingDateTypeTabulator extends Tabulator<FxFixingDate> {
	@Singleton
	class Impl implements FxFixingDateTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;
		private final Field dayTypeField;
		private final Field businessDayConventionField;
		private final Field businessCentersField;
		private final Field businessCentersReferenceField;
		private final Field dateRelativeToPaymentDatesField;
		private final Field dateRelativeToCalculationPeriodDatesField;
		private final Field dateRelativeToValuationDatesField;
		private final Field fxFixingDateField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;
		private final DateRelativeToPaymentDatesTypeTabulator dateRelativeToPaymentDatesTypeTabulator;
		private final DateRelativeToCalculationPeriodDatesTypeTabulator dateRelativeToCalculationPeriodDatesTypeTabulator;
		private final DateRelativeToValuationDatesTypeTabulator dateRelativeToValuationDatesTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator, DateRelativeToPaymentDatesTypeTabulator dateRelativeToPaymentDatesTypeTabulator, DateRelativeToCalculationPeriodDatesTypeTabulator dateRelativeToCalculationPeriodDatesTypeTabulator, DateRelativeToValuationDatesTypeTabulator dateRelativeToValuationDatesTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.dateRelativeToPaymentDatesTypeTabulator = dateRelativeToPaymentDatesTypeTabulator;
			this.dateRelativeToCalculationPeriodDatesTypeTabulator = dateRelativeToCalculationPeriodDatesTypeTabulator;
			this.dateRelativeToValuationDatesTypeTabulator = dateRelativeToValuationDatesTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.periodMultiplierField = new FieldImpl(
				"periodMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayTypeField = new FieldImpl(
				"dayType",
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
			this.businessCentersReferenceField = new FieldImpl(
				"businessCentersReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateRelativeToPaymentDatesField = new FieldImpl(
				"dateRelativeToPaymentDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateRelativeToCalculationPeriodDatesField = new FieldImpl(
				"dateRelativeToCalculationPeriodDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateRelativeToValuationDatesField = new FieldImpl(
				"dateRelativeToValuationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fxFixingDateField = new FieldImpl(
				"fxFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxFixingDate input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			FieldValue dayType = new FieldValueImpl(dayTypeField, Optional.ofNullable(input.getDayType()));
			FieldValue businessDayConvention = new FieldValueImpl(businessDayConventionField, Optional.ofNullable(input.getBusinessDayConvention()));
			FieldValue businessCenters = Optional.ofNullable(input.getBusinessCenters())
				.map(x -> new NestedFieldValueImpl(businessCentersField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersField, Optional.empty()));
			FieldValue businessCentersReference = Optional.ofNullable(input.getBusinessCentersReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(businessCentersReferenceField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersReferenceField, Optional.empty()));
			FieldValue dateRelativeToPaymentDates = Optional.ofNullable(input.getDateRelativeToPaymentDates())
				.map(x -> new NestedFieldValueImpl(dateRelativeToPaymentDatesField, Optional.of(dateRelativeToPaymentDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateRelativeToPaymentDatesField, Optional.empty()));
			FieldValue dateRelativeToCalculationPeriodDates = Optional.ofNullable(input.getDateRelativeToCalculationPeriodDates())
				.map(x -> new NestedFieldValueImpl(dateRelativeToCalculationPeriodDatesField, Optional.of(dateRelativeToCalculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateRelativeToCalculationPeriodDatesField, Optional.empty()));
			FieldValue dateRelativeToValuationDates = Optional.ofNullable(input.getDateRelativeToValuationDates())
				.map(x -> new NestedFieldValueImpl(dateRelativeToValuationDatesField, Optional.of(dateRelativeToValuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateRelativeToValuationDatesField, Optional.empty()));
			FieldValue fxFixingDate = Optional.ofNullable(input.getFxFixingDate())
				.map(x -> new NestedFieldValueImpl(fxFixingDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxFixingDateField, Optional.empty()));
			return Arrays.asList(
				periodMultiplier,
				period,
				dayType,
				businessDayConvention,
				businessCenters,
				businessCentersReference,
				dateRelativeToPaymentDates,
				dateRelativeToCalculationPeriodDates,
				dateRelativeToValuationDates,
				fxFixingDate
			);
		}
	}
}
