package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.template.ExerciseFee;
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


@ImplementedBy(ExerciseFeeTypeTabulator.Impl.class)
public interface ExerciseFeeTypeTabulator extends Tabulator<ExerciseFee> {
	@Singleton
	class Impl implements ExerciseFeeTypeTabulator {
		private final Field payerField;
		private final Field receiverField;
		private final Field notionalReferenceField;
		private final Field feeAmountField;
		private final Field feeRateField;
		private final Field feePaymentDateField;

		private final MoneyTypeTabulator moneyTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
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
			this.feeAmountField = new FieldImpl(
				"feeAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.feeRateField = new FieldImpl(
				"feeRate",
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
		public List<FieldValue> tabulate(ExerciseFee input) {
			FieldValue payer = new FieldValueImpl(payerField, Optional.ofNullable(input.getPayer()));
			FieldValue receiver = new FieldValueImpl(receiverField, Optional.ofNullable(input.getReceiver()));
			FieldValue notionalReference = Optional.ofNullable(input.getNotionalReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(notionalReferenceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notionalReferenceField, Optional.empty()));
			FieldValue feeAmount = new FieldValueImpl(feeAmountField, Optional.ofNullable(input.getFeeAmount()));
			FieldValue feeRate = new FieldValueImpl(feeRateField, Optional.ofNullable(input.getFeeRate()));
			FieldValue feePaymentDate = Optional.ofNullable(input.getFeePaymentDate())
				.map(x -> new NestedFieldValueImpl(feePaymentDateField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(feePaymentDateField, Optional.empty()));
			return Arrays.asList(
				payer,
				receiver,
				notionalReference,
				feeAmount,
				feeRate,
				feePaymentDate
			);
		}
	}
}
