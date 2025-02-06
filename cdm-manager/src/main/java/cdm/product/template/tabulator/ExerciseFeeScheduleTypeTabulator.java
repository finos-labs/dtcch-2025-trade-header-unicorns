package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.base.math.tabulator.ScheduleTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.schedule.tabulator.AmountScheduleTypeTabulator;
import cdm.product.template.ExerciseFeeSchedule;
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


@ImplementedBy(ExerciseFeeScheduleTypeTabulator.Impl.class)
public interface ExerciseFeeScheduleTypeTabulator extends Tabulator<ExerciseFeeSchedule> {
	@Singleton
	class Impl implements ExerciseFeeScheduleTypeTabulator {
		private final Field payerField;
		private final Field receiverField;
		private final Field notionalReferenceField;
		private final Field feeAmountScheduleField;
		private final Field feeRateScheduleField;
		private final Field feePaymentDateField;

		private final MoneyTypeTabulator moneyTypeTabulator;
		private final AmountScheduleTypeTabulator amountScheduleTypeTabulator;
		private final ScheduleTypeTabulator scheduleTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator, AmountScheduleTypeTabulator amountScheduleTypeTabulator, ScheduleTypeTabulator scheduleTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.amountScheduleTypeTabulator = amountScheduleTypeTabulator;
			this.scheduleTypeTabulator = scheduleTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.payerField = new FieldImpl(
				"payer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.receiverField = new FieldImpl(
				"receiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notionalReferenceField = new FieldImpl(
				"notionalReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.feeAmountScheduleField = new FieldImpl(
				"feeAmountSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.feeRateScheduleField = new FieldImpl(
				"feeRateSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.feePaymentDateField = new FieldImpl(
				"feePaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExerciseFeeSchedule input) {
			FieldValue payer = new FieldValueImpl(payerField, Optional.ofNullable(input.getPayer()));
			FieldValue receiver = new FieldValueImpl(receiverField, Optional.ofNullable(input.getReceiver()));
			FieldValue notionalReference = Optional.ofNullable(input.getNotionalReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(notionalReferenceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notionalReferenceField, Optional.empty()));
			FieldValue feeAmountSchedule = Optional.ofNullable(input.getFeeAmountSchedule())
				.map(x -> new NestedFieldValueImpl(feeAmountScheduleField, Optional.of(amountScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(feeAmountScheduleField, Optional.empty()));
			FieldValue feeRateSchedule = Optional.ofNullable(input.getFeeRateSchedule())
				.map(x -> new NestedFieldValueImpl(feeRateScheduleField, Optional.of(scheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(feeRateScheduleField, Optional.empty()));
			FieldValue feePaymentDate = Optional.ofNullable(input.getFeePaymentDate())
				.map(x -> new NestedFieldValueImpl(feePaymentDateField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(feePaymentDateField, Optional.empty()));
			return Arrays.asList(
				payer,
				receiver,
				notionalReference,
				feeAmountSchedule,
				feeRateSchedule,
				feePaymentDate
			);
		}
	}
}
