package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableDateTypeTabulator;
import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.asset.InterestRatePayout;
import cdm.product.common.schedule.tabulator.CalculationPeriodDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.PaymentDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.ResetDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.StubPeriodTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
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


@ImplementedBy(InterestRatePayoutTypeTabulator.Impl.class)
public interface InterestRatePayoutTypeTabulator extends Tabulator<InterestRatePayout> {
	@Singleton
	class Impl implements InterestRatePayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field rateSpecificationField;
		private final Field dayCountFractionField;
		private final Field calculationPeriodDatesField;
		private final Field paymentDatesField;
		private final Field paymentDateField;
		private final Field paymentDelayField;
		private final Field resetDatesField;
		private final Field discountingMethodField;
		private final Field compoundingMethodField;
		private final Field cashflowRepresentationField;
		private final Field stubPeriodField;
		private final Field bondReferenceField;
		private final Field fixedAmountField;
		private final Field floatingAmountField;
		private final Field spreadCalculationMethodField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final RateSpecificationTypeTabulator rateSpecificationTypeTabulator;
		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;
		private final PaymentDatesTypeTabulator paymentDatesTypeTabulator;
		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;
		private final ResetDatesTypeTabulator resetDatesTypeTabulator;
		private final DiscountingMethodTypeTabulator discountingMethodTypeTabulator;
		private final CashflowRepresentationTypeTabulator cashflowRepresentationTypeTabulator;
		private final StubPeriodTypeTabulator stubPeriodTypeTabulator;
		private final BondReferenceTypeTabulator bondReferenceTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, RateSpecificationTypeTabulator rateSpecificationTypeTabulator, CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator, PaymentDatesTypeTabulator paymentDatesTypeTabulator, AdjustableDateTypeTabulator adjustableDateTypeTabulator, ResetDatesTypeTabulator resetDatesTypeTabulator, DiscountingMethodTypeTabulator discountingMethodTypeTabulator, CashflowRepresentationTypeTabulator cashflowRepresentationTypeTabulator, StubPeriodTypeTabulator stubPeriodTypeTabulator, BondReferenceTypeTabulator bondReferenceTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.rateSpecificationTypeTabulator = rateSpecificationTypeTabulator;
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.paymentDatesTypeTabulator = paymentDatesTypeTabulator;
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.resetDatesTypeTabulator = resetDatesTypeTabulator;
			this.discountingMethodTypeTabulator = discountingMethodTypeTabulator;
			this.cashflowRepresentationTypeTabulator = cashflowRepresentationTypeTabulator;
			this.stubPeriodTypeTabulator = stubPeriodTypeTabulator;
			this.bondReferenceTypeTabulator = bondReferenceTypeTabulator;
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
			this.rateSpecificationField = new FieldImpl(
				"rateSpecification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayCountFractionField = new FieldImpl(
				"dayCountFraction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodDatesField = new FieldImpl(
				"calculationPeriodDates",
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
			this.paymentDateField = new FieldImpl(
				"paymentDate",
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
			this.resetDatesField = new FieldImpl(
				"resetDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discountingMethodField = new FieldImpl(
				"discountingMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.compoundingMethodField = new FieldImpl(
				"compoundingMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashflowRepresentationField = new FieldImpl(
				"cashflowRepresentation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stubPeriodField = new FieldImpl(
				"stubPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.bondReferenceField = new FieldImpl(
				"bondReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixedAmountField = new FieldImpl(
				"fixedAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingAmountField = new FieldImpl(
				"floatingAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadCalculationMethodField = new FieldImpl(
				"spreadCalculationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InterestRatePayout input) {
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
			FieldValue rateSpecification = Optional.ofNullable(input.getRateSpecification())
				.map(x -> new NestedFieldValueImpl(rateSpecificationField, Optional.of(rateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rateSpecificationField, Optional.empty()));
			FieldValue dayCountFraction = new FieldValueImpl(dayCountFractionField, Optional.ofNullable(input.getDayCountFraction())
				.map(x -> x.getValue()));
			FieldValue calculationPeriodDates = Optional.ofNullable(input.getCalculationPeriodDates())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesField, Optional.of(calculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesField, Optional.empty()));
			FieldValue paymentDates = Optional.ofNullable(input.getPaymentDates())
				.map(x -> new NestedFieldValueImpl(paymentDatesField, Optional.of(paymentDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDatesField, Optional.empty()));
			FieldValue paymentDate = Optional.ofNullable(input.getPaymentDate())
				.map(x -> new NestedFieldValueImpl(paymentDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDateField, Optional.empty()));
			FieldValue paymentDelay = new FieldValueImpl(paymentDelayField, Optional.ofNullable(input.getPaymentDelay()));
			FieldValue resetDates = Optional.ofNullable(input.getResetDates())
				.map(x -> new NestedFieldValueImpl(resetDatesField, Optional.of(resetDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetDatesField, Optional.empty()));
			FieldValue discountingMethod = Optional.ofNullable(input.getDiscountingMethod())
				.map(x -> new NestedFieldValueImpl(discountingMethodField, Optional.of(discountingMethodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(discountingMethodField, Optional.empty()));
			FieldValue compoundingMethod = new FieldValueImpl(compoundingMethodField, Optional.ofNullable(input.getCompoundingMethod()));
			FieldValue cashflowRepresentation = Optional.ofNullable(input.getCashflowRepresentation())
				.map(x -> new NestedFieldValueImpl(cashflowRepresentationField, Optional.of(cashflowRepresentationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashflowRepresentationField, Optional.empty()));
			FieldValue stubPeriod = Optional.ofNullable(input.getStubPeriod())
				.map(x -> new NestedFieldValueImpl(stubPeriodField, Optional.of(stubPeriodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(stubPeriodField, Optional.empty()));
			FieldValue bondReference = Optional.ofNullable(input.getBondReference())
				.map(x -> new NestedFieldValueImpl(bondReferenceField, Optional.of(bondReferenceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(bondReferenceField, Optional.empty()));
			FieldValue fixedAmount = new FieldValueImpl(fixedAmountField, Optional.ofNullable(input.getFixedAmount()));
			FieldValue floatingAmount = new FieldValueImpl(floatingAmountField, Optional.ofNullable(input.getFloatingAmount()));
			FieldValue spreadCalculationMethod = new FieldValueImpl(spreadCalculationMethodField, Optional.ofNullable(input.getSpreadCalculationMethod()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				rateSpecification,
				dayCountFraction,
				calculationPeriodDates,
				paymentDates,
				paymentDate,
				paymentDelay,
				resetDates,
				discountingMethod,
				compoundingMethod,
				cashflowRepresentation,
				stubPeriod,
				bondReference,
				fixedAmount,
				floatingAmount,
				spreadCalculationMethod
			);
		}
	}
}
