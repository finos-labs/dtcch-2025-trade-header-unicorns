package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableDatesTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.observable.asset.tabulator.MultipleValuationDatesTypeTabulator;
import cdm.observable.asset.tabulator.SingleValuationDateTypeTabulator;
import cdm.product.common.settlement.ValuationDate;
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


@ImplementedBy(ValuationDateTypeTabulator.Impl.class)
public interface ValuationDateTypeTabulator extends Tabulator<ValuationDate> {
	@Singleton
	class Impl implements ValuationDateTypeTabulator {
		private final Field singleValuationDateField;
		private final Field multipleValuationDatesField;
		private final Field valuationDateField;
		private final Field fxFixingDateField;
		private final Field fxFixingScheduleField;

		private final SingleValuationDateTypeTabulator singleValuationDateTypeTabulator;
		private final MultipleValuationDatesTypeTabulator multipleValuationDatesTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final FxFixingDateTypeTabulator fxFixingDateTypeTabulator;
		private final AdjustableDatesTypeTabulator adjustableDatesTypeTabulator;

		@Inject
		public Impl(SingleValuationDateTypeTabulator singleValuationDateTypeTabulator, MultipleValuationDatesTypeTabulator multipleValuationDatesTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, FxFixingDateTypeTabulator fxFixingDateTypeTabulator, AdjustableDatesTypeTabulator adjustableDatesTypeTabulator) {
			this.singleValuationDateTypeTabulator = singleValuationDateTypeTabulator;
			this.multipleValuationDatesTypeTabulator = multipleValuationDatesTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.fxFixingDateTypeTabulator = fxFixingDateTypeTabulator;
			this.adjustableDatesTypeTabulator = adjustableDatesTypeTabulator;
			this.singleValuationDateField = new FieldImpl(
				"singleValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multipleValuationDatesField = new FieldImpl(
				"multipleValuationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationDateField = new FieldImpl(
				"valuationDate",
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
			this.fxFixingScheduleField = new FieldImpl(
				"fxFixingSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationDate input) {
			FieldValue singleValuationDate = Optional.ofNullable(input.getSingleValuationDate())
				.map(x -> new NestedFieldValueImpl(singleValuationDateField, Optional.of(singleValuationDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(singleValuationDateField, Optional.empty()));
			FieldValue multipleValuationDates = Optional.ofNullable(input.getMultipleValuationDates())
				.map(x -> new NestedFieldValueImpl(multipleValuationDatesField, Optional.of(multipleValuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(multipleValuationDatesField, Optional.empty()));
			FieldValue valuationDate = Optional.ofNullable(input.getValuationDate())
				.map(x -> new NestedFieldValueImpl(valuationDateField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationDateField, Optional.empty()));
			FieldValue fxFixingDate = Optional.ofNullable(input.getFxFixingDate())
				.map(x -> new NestedFieldValueImpl(fxFixingDateField, Optional.of(fxFixingDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxFixingDateField, Optional.empty()));
			FieldValue fxFixingSchedule = Optional.ofNullable(input.getFxFixingSchedule())
				.map(x -> new NestedFieldValueImpl(fxFixingScheduleField, Optional.of(adjustableDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxFixingScheduleField, Optional.empty()));
			return Arrays.asList(
				singleValuationDate,
				multipleValuationDates,
				valuationDate,
				fxFixingDate,
				fxFixingSchedule
			);
		}
	}
}
