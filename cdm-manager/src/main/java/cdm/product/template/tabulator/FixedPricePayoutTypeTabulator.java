package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.common.schedule.tabulator.PaymentDatesTypeTabulator;
import cdm.product.common.settlement.tabulator.FixedPriceTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.FixedPricePayout;
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


@ImplementedBy(FixedPricePayoutTypeTabulator.Impl.class)
public interface FixedPricePayoutTypeTabulator extends Tabulator<FixedPricePayout> {
	@Singleton
	class Impl implements FixedPricePayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field paymentDatesField;
		private final Field fixedPriceField;
		private final Field scheduleField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final PaymentDatesTypeTabulator paymentDatesTypeTabulator;
		private final FixedPriceTypeTabulator fixedPriceTypeTabulator;
		private final CalculationScheduleTypeTabulator calculationScheduleTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, PaymentDatesTypeTabulator paymentDatesTypeTabulator, FixedPriceTypeTabulator fixedPriceTypeTabulator, CalculationScheduleTypeTabulator calculationScheduleTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.paymentDatesTypeTabulator = paymentDatesTypeTabulator;
			this.fixedPriceTypeTabulator = fixedPriceTypeTabulator;
			this.calculationScheduleTypeTabulator = calculationScheduleTypeTabulator;
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceQuantityField = new FieldImpl(
				"priceQuantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.principalPaymentField = new FieldImpl(
				"principalPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementTermsField = new FieldImpl(
				"settlementTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDatesField = new FieldImpl(
				"paymentDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixedPriceField = new FieldImpl(
				"fixedPrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.scheduleField = new FieldImpl(
				"schedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FixedPricePayout input) {
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(payerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue priceQuantity = Optional.ofNullable(input.getPriceQuantity())
				.map(x -> new NestedFieldValueImpl(priceQuantityField, Optional.of(resolvablePriceQuantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceQuantityField, Optional.empty()));
			FieldValue principalPayment = Optional.ofNullable(input.getPrincipalPayment())
				.map(x -> new NestedFieldValueImpl(principalPaymentField, Optional.of(principalPaymentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(principalPaymentField, Optional.empty()));
			FieldValue settlementTerms = Optional.ofNullable(input.getSettlementTerms())
				.map(x -> new NestedFieldValueImpl(settlementTermsField, Optional.of(settlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementTermsField, Optional.empty()));
			FieldValue paymentDates = Optional.ofNullable(input.getPaymentDates())
				.map(x -> new NestedFieldValueImpl(paymentDatesField, Optional.of(paymentDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDatesField, Optional.empty()));
			FieldValue fixedPrice = Optional.ofNullable(input.getFixedPrice())
				.map(x -> new NestedFieldValueImpl(fixedPriceField, Optional.of(fixedPriceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixedPriceField, Optional.empty()));
			FieldValue schedule = Optional.ofNullable(input.getSchedule())
				.map(x -> new NestedFieldValueImpl(scheduleField, Optional.of(calculationScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduleField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				paymentDates,
				fixedPrice,
				schedule
			);
		}
	}
}
