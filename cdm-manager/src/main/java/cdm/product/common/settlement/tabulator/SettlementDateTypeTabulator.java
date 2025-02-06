package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableDatesTypeTabulator;
import cdm.base.datetime.tabulator.AdjustableOrAdjustedOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessDateRangeTypeTabulator;
import cdm.product.common.settlement.SettlementDate;
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


@ImplementedBy(SettlementDateTypeTabulator.Impl.class)
public interface SettlementDateTypeTabulator extends Tabulator<SettlementDate> {
	@Singleton
	class Impl implements SettlementDateTypeTabulator {
		private final Field adjustableOrRelativeDateField;
		private final Field valueDateField;
		private final Field adjustableDatesField;
		private final Field businessDateRangeField;
		private final Field cashSettlementBusinessDaysField;
		private final Field paymentDelayField;

		private final AdjustableOrAdjustedOrRelativeDateTypeTabulator adjustableOrAdjustedOrRelativeDateTypeTabulator;
		private final AdjustableDatesTypeTabulator adjustableDatesTypeTabulator;
		private final BusinessDateRangeTypeTabulator businessDateRangeTypeTabulator;

		@Inject
		public Impl(AdjustableOrAdjustedOrRelativeDateTypeTabulator adjustableOrAdjustedOrRelativeDateTypeTabulator, AdjustableDatesTypeTabulator adjustableDatesTypeTabulator, BusinessDateRangeTypeTabulator businessDateRangeTypeTabulator) {
			this.adjustableOrAdjustedOrRelativeDateTypeTabulator = adjustableOrAdjustedOrRelativeDateTypeTabulator;
			this.adjustableDatesTypeTabulator = adjustableDatesTypeTabulator;
			this.businessDateRangeTypeTabulator = businessDateRangeTypeTabulator;
			this.adjustableOrRelativeDateField = new FieldImpl(
				"adjustableOrRelativeDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valueDateField = new FieldImpl(
				"valueDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustableDatesField = new FieldImpl(
				"adjustableDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessDateRangeField = new FieldImpl(
				"businessDateRange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementBusinessDaysField = new FieldImpl(
				"cashSettlementBusinessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDelayField = new FieldImpl(
				"paymentDelay",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettlementDate input) {
			FieldValue adjustableOrRelativeDate = Optional.ofNullable(input.getAdjustableOrRelativeDate())
				.map(x -> new NestedFieldValueImpl(adjustableOrRelativeDateField, Optional.of(adjustableOrAdjustedOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(adjustableOrRelativeDateField, Optional.empty()));
			FieldValue valueDate = new FieldValueImpl(valueDateField, Optional.ofNullable(input.getValueDate()));
			FieldValue adjustableDates = Optional.ofNullable(input.getAdjustableDates())
				.map(x -> new NestedFieldValueImpl(adjustableDatesField, Optional.of(adjustableDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(adjustableDatesField, Optional.empty()));
			FieldValue businessDateRange = Optional.ofNullable(input.getBusinessDateRange())
				.map(x -> new NestedFieldValueImpl(businessDateRangeField, Optional.of(businessDateRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessDateRangeField, Optional.empty()));
			FieldValue cashSettlementBusinessDays = new FieldValueImpl(cashSettlementBusinessDaysField, Optional.ofNullable(input.getCashSettlementBusinessDays()));
			FieldValue paymentDelay = new FieldValueImpl(paymentDelayField, Optional.ofNullable(input.getPaymentDelay()));
			return Arrays.asList(
				adjustableOrRelativeDate,
				valueDate,
				adjustableDates,
				businessDateRange,
				cashSettlementBusinessDays,
				paymentDelay
			);
		}
	}
}
