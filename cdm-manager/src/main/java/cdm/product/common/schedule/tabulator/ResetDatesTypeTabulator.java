package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.AdjustableDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.product.common.schedule.ResetDates;
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


@ImplementedBy(ResetDatesTypeTabulator.Impl.class)
public interface ResetDatesTypeTabulator extends Tabulator<ResetDates> {
	@Singleton
	class Impl implements ResetDatesTypeTabulator {
		private final Field calculationPeriodDatesReferenceField;
		private final Field resetRelativeToField;
		private final Field initialFixingDateField;
		private final Field fixingDatesField;
		private final Field finalFixingDateField;
		private final Field rateCutOffDaysOffsetField;
		private final Field resetFrequencyField;
		private final Field resetDatesAdjustmentsField;

		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;
		private final InitialFixingDateTypeTabulator initialFixingDateTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;
		private final OffsetTypeTabulator offsetTypeTabulator;
		private final ResetFrequencyTypeTabulator resetFrequencyTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;

		@Inject
		public Impl(CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator, InitialFixingDateTypeTabulator initialFixingDateTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, AdjustableDateTypeTabulator adjustableDateTypeTabulator, OffsetTypeTabulator offsetTypeTabulator, ResetFrequencyTypeTabulator resetFrequencyTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator) {
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.initialFixingDateTypeTabulator = initialFixingDateTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.resetFrequencyTypeTabulator = resetFrequencyTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.calculationPeriodDatesReferenceField = new FieldImpl(
				"calculationPeriodDatesReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetRelativeToField = new FieldImpl(
				"resetRelativeTo",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialFixingDateField = new FieldImpl(
				"initialFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixingDatesField = new FieldImpl(
				"fixingDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalFixingDateField = new FieldImpl(
				"finalFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateCutOffDaysOffsetField = new FieldImpl(
				"rateCutOffDaysOffset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetFrequencyField = new FieldImpl(
				"resetFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetDatesAdjustmentsField = new FieldImpl(
				"resetDatesAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ResetDates input) {
			FieldValue calculationPeriodDatesReference = Optional.ofNullable(input.getCalculationPeriodDatesReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.of(calculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.empty()));
			FieldValue resetRelativeTo = new FieldValueImpl(resetRelativeToField, Optional.ofNullable(input.getResetRelativeTo()));
			FieldValue initialFixingDate = Optional.ofNullable(input.getInitialFixingDate())
				.map(x -> new NestedFieldValueImpl(initialFixingDateField, Optional.of(initialFixingDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialFixingDateField, Optional.empty()));
			FieldValue fixingDates = Optional.ofNullable(input.getFixingDates())
				.map(x -> new NestedFieldValueImpl(fixingDatesField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixingDatesField, Optional.empty()));
			FieldValue finalFixingDate = Optional.ofNullable(input.getFinalFixingDate())
				.map(x -> new NestedFieldValueImpl(finalFixingDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalFixingDateField, Optional.empty()));
			FieldValue rateCutOffDaysOffset = Optional.ofNullable(input.getRateCutOffDaysOffset())
				.map(x -> new NestedFieldValueImpl(rateCutOffDaysOffsetField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rateCutOffDaysOffsetField, Optional.empty()));
			FieldValue resetFrequency = Optional.ofNullable(input.getResetFrequency())
				.map(x -> new NestedFieldValueImpl(resetFrequencyField, Optional.of(resetFrequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetFrequencyField, Optional.empty()));
			FieldValue resetDatesAdjustments = Optional.ofNullable(input.getResetDatesAdjustments())
				.map(x -> new NestedFieldValueImpl(resetDatesAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetDatesAdjustmentsField, Optional.empty()));
			return Arrays.asList(
				calculationPeriodDatesReference,
				resetRelativeTo,
				initialFixingDate,
				fixingDates,
				finalFixingDate,
				rateCutOffDaysOffset,
				resetFrequency,
				resetDatesAdjustments
			);
		}
	}
}
