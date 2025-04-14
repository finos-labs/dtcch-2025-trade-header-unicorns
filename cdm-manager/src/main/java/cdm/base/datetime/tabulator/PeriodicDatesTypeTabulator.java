package cdm.base.datetime.tabulator;

import cdm.base.datetime.PeriodicDates;
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


@ImplementedBy(PeriodicDatesTypeTabulator.Impl.class)
public interface PeriodicDatesTypeTabulator extends Tabulator<PeriodicDates> {
	@Singleton
	class Impl implements PeriodicDatesTypeTabulator {
		private final Field startDateField;
		private final Field endDateField;
		private final Field periodFrequencyField;
		private final Field periodDatesAdjustmentsField;
		private final Field dayTypeField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.calculationPeriodFrequencyTypeTabulator = calculationPeriodFrequencyTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
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
			this.periodFrequencyField = new FieldImpl(
				"periodFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodDatesAdjustmentsField = new FieldImpl(
				"periodDatesAdjustments",
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
		}

		@Override
		public List<FieldValue> tabulate(PeriodicDates input) {
			FieldValue startDate = Optional.ofNullable(input.getStartDate())
				.map(x -> new NestedFieldValueImpl(startDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(startDateField, Optional.empty()));
			FieldValue endDate = Optional.ofNullable(input.getEndDate())
				.map(x -> new NestedFieldValueImpl(endDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(endDateField, Optional.empty()));
			FieldValue periodFrequency = Optional.ofNullable(input.getPeriodFrequency())
				.map(x -> new NestedFieldValueImpl(periodFrequencyField, Optional.of(calculationPeriodFrequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodFrequencyField, Optional.empty()));
			FieldValue periodDatesAdjustments = Optional.ofNullable(input.getPeriodDatesAdjustments())
				.map(x -> new NestedFieldValueImpl(periodDatesAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodDatesAdjustmentsField, Optional.empty()));
			FieldValue dayType = new FieldValueImpl(dayTypeField, Optional.ofNullable(input.getDayType()));
			return Arrays.asList(
				startDate,
				endDate,
				periodFrequency,
				periodDatesAdjustments,
				dayType
			);
		}
	}
}
