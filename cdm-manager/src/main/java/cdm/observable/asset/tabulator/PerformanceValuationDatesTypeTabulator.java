package cdm.observable.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.observable.asset.PerformanceValuationDates;
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


@ImplementedBy(PerformanceValuationDatesTypeTabulator.Impl.class)
public interface PerformanceValuationDatesTypeTabulator extends Tabulator<PerformanceValuationDates> {
	@Singleton
	class Impl implements PerformanceValuationDatesTypeTabulator {
		private final Field determinationMethodField;
		private final Field valuationDatesField;
		private final Field valuationDateField;
		private final Field valuationTimeField;
		private final Field valuationTimeTypeField;

		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;

		@Inject
		public Impl(AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator) {
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.determinationMethodField = new FieldImpl(
				"determinationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationDatesField = new FieldImpl(
				"valuationDates",
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
			this.valuationTimeField = new FieldImpl(
				"valuationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationTimeTypeField = new FieldImpl(
				"valuationTimeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PerformanceValuationDates input) {
			FieldValue determinationMethod = new FieldValueImpl(determinationMethodField, Optional.ofNullable(input.getDeterminationMethod()));
			FieldValue valuationDates = Optional.ofNullable(input.getValuationDates())
				.map(x -> new NestedFieldValueImpl(valuationDatesField, Optional.of(adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationDatesField, Optional.empty()));
			FieldValue valuationDate = Optional.ofNullable(input.getValuationDate())
				.map(x -> new NestedFieldValueImpl(valuationDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationDateField, Optional.empty()));
			FieldValue valuationTime = Optional.ofNullable(input.getValuationTime())
				.map(x -> new NestedFieldValueImpl(valuationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationTimeField, Optional.empty()));
			FieldValue valuationTimeType = new FieldValueImpl(valuationTimeTypeField, Optional.ofNullable(input.getValuationTimeType()));
			return Arrays.asList(
				determinationMethod,
				valuationDates,
				valuationDate,
				valuationTime,
				valuationTimeType
			);
		}
	}
}
