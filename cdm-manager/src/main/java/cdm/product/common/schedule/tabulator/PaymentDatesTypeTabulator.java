package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.base.datetime.tabulator.FrequencyTypeTabulator;
import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.product.common.schedule.PaymentDates;
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


@ImplementedBy(PaymentDatesTypeTabulator.Impl.class)
public interface PaymentDatesTypeTabulator extends Tabulator<PaymentDates> {
	@Singleton
	class Impl implements PaymentDatesTypeTabulator {
		private final Field paymentFrequencyField;
		private final Field firstPaymentDateField;
		private final Field lastRegularPaymentDateField;
		private final Field paymentDateScheduleField;
		private final Field payRelativeToField;
		private final Field paymentDaysOffsetField;
		private final Field paymentDatesAdjustmentsField;

		private final FrequencyTypeTabulator frequencyTypeTabulator;
		private final PaymentDateScheduleTypeTabulator paymentDateScheduleTypeTabulator;
		private final OffsetTypeTabulator offsetTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;

		@Inject
		public Impl(FrequencyTypeTabulator frequencyTypeTabulator, PaymentDateScheduleTypeTabulator paymentDateScheduleTypeTabulator, OffsetTypeTabulator offsetTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator) {
			this.frequencyTypeTabulator = frequencyTypeTabulator;
			this.paymentDateScheduleTypeTabulator = paymentDateScheduleTypeTabulator;
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.paymentFrequencyField = new FieldImpl(
				"paymentFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstPaymentDateField = new FieldImpl(
				"firstPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lastRegularPaymentDateField = new FieldImpl(
				"lastRegularPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDateScheduleField = new FieldImpl(
				"paymentDateSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payRelativeToField = new FieldImpl(
				"payRelativeTo",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDaysOffsetField = new FieldImpl(
				"paymentDaysOffset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDatesAdjustmentsField = new FieldImpl(
				"paymentDatesAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PaymentDates input) {
			FieldValue paymentFrequency = Optional.ofNullable(input.getPaymentFrequency())
				.map(x -> new NestedFieldValueImpl(paymentFrequencyField, Optional.of(frequencyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentFrequencyField, Optional.empty()));
			FieldValue firstPaymentDate = new FieldValueImpl(firstPaymentDateField, Optional.ofNullable(input.getFirstPaymentDate()));
			FieldValue lastRegularPaymentDate = new FieldValueImpl(lastRegularPaymentDateField, Optional.ofNullable(input.getLastRegularPaymentDate()));
			FieldValue paymentDateSchedule = Optional.ofNullable(input.getPaymentDateSchedule())
				.map(x -> new NestedFieldValueImpl(paymentDateScheduleField, Optional.of(paymentDateScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDateScheduleField, Optional.empty()));
			FieldValue payRelativeTo = new FieldValueImpl(payRelativeToField, Optional.ofNullable(input.getPayRelativeTo()));
			FieldValue paymentDaysOffset = Optional.ofNullable(input.getPaymentDaysOffset())
				.map(x -> new NestedFieldValueImpl(paymentDaysOffsetField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDaysOffsetField, Optional.empty()));
			FieldValue paymentDatesAdjustments = Optional.ofNullable(input.getPaymentDatesAdjustments())
				.map(x -> new NestedFieldValueImpl(paymentDatesAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDatesAdjustmentsField, Optional.empty()));
			return Arrays.asList(
				paymentFrequency,
				firstPaymentDate,
				lastRegularPaymentDate,
				paymentDateSchedule,
				payRelativeTo,
				paymentDaysOffset,
				paymentDatesAdjustments
			);
		}
	}
}
