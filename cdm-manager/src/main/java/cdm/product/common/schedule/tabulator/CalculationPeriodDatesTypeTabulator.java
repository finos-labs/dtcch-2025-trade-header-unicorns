package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.base.datetime.tabulator.CalculationPeriodFrequencyTypeTabulator;
import cdm.product.common.schedule.CalculationPeriodDates;
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


@ImplementedBy(CalculationPeriodDatesTypeTabulator.Impl.class)
public interface CalculationPeriodDatesTypeTabulator extends Tabulator<CalculationPeriodDates> {
	@Singleton
	class Impl implements CalculationPeriodDatesTypeTabulator {
		private final Field effectiveDateField;
		private final Field terminationDateField;
		private final Field calculationPeriodDatesAdjustmentsField;
		private final Field firstPeriodStartDateField;
		private final Field firstRegularPeriodStartDateField;
		private final Field firstCompoundingPeriodEndDateField;
		private final Field lastRegularPeriodEndDateField;
		private final Field stubPeriodTypeField;
		private final Field calculationPeriodFrequencyField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;
		private final CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator, CalculationPeriodFrequencyTypeTabulator calculationPeriodFrequencyTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.calculationPeriodFrequencyTypeTabulator = calculationPeriodFrequencyTypeTabulator;
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.terminationDateField = new FieldImpl(
				"terminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodDatesAdjustmentsField = new FieldImpl(
				"calculationPeriodDatesAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstPeriodStartDateField = new FieldImpl(
				"firstPeriodStartDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstRegularPeriodStartDateField = new FieldImpl(
				"firstRegularPeriodStartDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstCompoundingPeriodEndDateField = new FieldImpl(
				"firstCompoundingPeriodEndDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lastRegularPeriodEndDateField = new FieldImpl(
				"lastRegularPeriodEndDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stubPeriodTypeField = new FieldImpl(
				"stubPeriodType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodFrequencyField = new FieldImpl(
				"calculationPeriodFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalculationPeriodDates input) {
			FieldValue effectiveDate = Optional.ofNullable(input.getEffectiveDate())
				.map(x -> new NestedFieldValueImpl(effectiveDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(effectiveDateField, Optional.empty()));
			FieldValue terminationDate = Optional.ofNullable(input.getTerminationDate())
				.map(x -> new NestedFieldValueImpl(terminationDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(terminationDateField, Optional.empty()));
			FieldValue calculationPeriodDatesAdjustments = Optional.ofNullable(input.getCalculationPeriodDatesAdjustments())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesAdjustmentsField, Optional.empty()));
			FieldValue firstPeriodStartDate = Optional.ofNullable(input.getFirstPeriodStartDate())
				.map(x -> new NestedFieldValueImpl(firstPeriodStartDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(firstPeriodStartDateField, Optional.empty()));
			FieldValue firstRegularPeriodStartDate = new FieldValueImpl(firstRegularPeriodStartDateField, Optional.ofNullable(input.getFirstRegularPeriodStartDate()));
			FieldValue firstCompoundingPeriodEndDate = new FieldValueImpl(firstCompoundingPeriodEndDateField, Optional.ofNullable(input.getFirstCompoundingPeriodEndDate()));
			FieldValue lastRegularPeriodEndDate = new FieldValueImpl(lastRegularPeriodEndDateField, Optional.ofNullable(input.getLastRegularPeriodEndDate()));
			FieldValue stubPeriodType = new FieldValueImpl(stubPeriodTypeField, Optional.ofNullable(input.getStubPeriodType()));
			FieldValue calculationPeriodFrequency = Optional.ofNullable(input.getCalculationPeriodFrequency())
				.map(x -> new NestedFieldValueImpl(calculationPeriodFrequencyField, Optional.of(calculationPeriodFrequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodFrequencyField, Optional.empty()));
			return Arrays.asList(
				effectiveDate,
				terminationDate,
				calculationPeriodDatesAdjustments,
				firstPeriodStartDate,
				firstRegularPeriodStartDate,
				firstCompoundingPeriodEndDate,
				lastRegularPeriodEndDate,
				stubPeriodType,
				calculationPeriodFrequency
			);
		}
	}
}
