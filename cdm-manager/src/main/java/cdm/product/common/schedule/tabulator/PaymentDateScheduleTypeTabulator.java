package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.product.common.schedule.PaymentDateSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PaymentDateScheduleTypeTabulator.Impl.class)
public interface PaymentDateScheduleTypeTabulator extends Tabulator<PaymentDateSchedule> {
	@Singleton
	class Impl implements PaymentDateScheduleTypeTabulator {
		private final Field interimPaymentDatesField;
		private final Field finalPaymentDateField;

		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.interimPaymentDatesField = new FieldImpl(
				"interimPaymentDates",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPaymentDateField = new FieldImpl(
				"finalPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PaymentDateSchedule input) {
			FieldValue interimPaymentDates = Optional.ofNullable(input.getInterimPaymentDates())
				.map(x -> x.stream()
					.map(_x -> adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(interimPaymentDatesField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(interimPaymentDatesField, Optional.empty()));
			FieldValue finalPaymentDate = Optional.ofNullable(input.getFinalPaymentDate())
				.map(x -> new NestedFieldValueImpl(finalPaymentDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalPaymentDateField, Optional.empty()));
			return Arrays.asList(
				interimPaymentDates,
				finalPaymentDate
			);
		}
	}
}
