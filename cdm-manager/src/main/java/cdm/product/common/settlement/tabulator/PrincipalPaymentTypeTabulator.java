package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableDateTypeTabulator;
import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.settlement.PrincipalPayment;
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


@ImplementedBy(PrincipalPaymentTypeTabulator.Impl.class)
public interface PrincipalPaymentTypeTabulator extends Tabulator<PrincipalPayment> {
	@Singleton
	class Impl implements PrincipalPaymentTypeTabulator {
		private final Field principalPaymentDateField;
		private final Field payerReceiverField;
		private final Field principalAmountField;
		private final Field discountFactorField;
		private final Field presentValuePrincipalAmountField;

		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;
		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(AdjustableDateTypeTabulator adjustableDateTypeTabulator, PayerReceiverTypeTabulator payerReceiverTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.principalPaymentDateField = new FieldImpl(
				"principalPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.principalAmountField = new FieldImpl(
				"principalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discountFactorField = new FieldImpl(
				"discountFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.presentValuePrincipalAmountField = new FieldImpl(
				"presentValuePrincipalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PrincipalPayment input) {
			FieldValue principalPaymentDate = Optional.ofNullable(input.getPrincipalPaymentDate())
				.map(x -> new NestedFieldValueImpl(principalPaymentDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(principalPaymentDateField, Optional.empty()));
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(payerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue principalAmount = Optional.ofNullable(input.getPrincipalAmount())
				.map(x -> new NestedFieldValueImpl(principalAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(principalAmountField, Optional.empty()));
			FieldValue discountFactor = new FieldValueImpl(discountFactorField, Optional.ofNullable(input.getDiscountFactor()));
			FieldValue presentValuePrincipalAmount = Optional.ofNullable(input.getPresentValuePrincipalAmount())
				.map(x -> new NestedFieldValueImpl(presentValuePrincipalAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(presentValuePrincipalAmountField, Optional.empty()));
			return Arrays.asList(
				principalPaymentDate,
				payerReceiver,
				principalAmount,
				discountFactor,
				presentValuePrincipalAmount
			);
		}
	}
}
