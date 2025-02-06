package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.settlement.PaymentDetail;
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


@ImplementedBy(PaymentDetailTypeTabulator.Impl.class)
public interface PaymentDetailTypeTabulator extends Tabulator<PaymentDetail> {
	@Singleton
	class Impl implements PaymentDetailTypeTabulator {
		private final Field paymentDateField;
		private final Field paymentRuleField;
		private final Field paymentAmountField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final PaymentRuleTypeTabulator paymentRuleTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, PaymentRuleTypeTabulator paymentRuleTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.paymentRuleTypeTabulator = paymentRuleTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.paymentDateField = new FieldImpl(
				"paymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentRuleField = new FieldImpl(
				"paymentRule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentAmountField = new FieldImpl(
				"paymentAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PaymentDetail input) {
			FieldValue paymentDate = Optional.ofNullable(input.getPaymentDate())
				.map(x -> new NestedFieldValueImpl(paymentDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDateField, Optional.empty()));
			FieldValue paymentRule = Optional.ofNullable(input.getPaymentRule())
				.map(x -> new NestedFieldValueImpl(paymentRuleField, Optional.of(paymentRuleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentRuleField, Optional.empty()));
			FieldValue paymentAmount = Optional.ofNullable(input.getPaymentAmount())
				.map(x -> new NestedFieldValueImpl(paymentAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentAmountField, Optional.empty()));
			return Arrays.asList(
				paymentDate,
				paymentRule,
				paymentAmount
			);
		}
	}
}
