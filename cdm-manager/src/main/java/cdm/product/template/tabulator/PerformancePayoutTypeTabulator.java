package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.observable.asset.tabulator.ValuationDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.ObservationTermsTypeTabulator;
import cdm.product.common.schedule.tabulator.PaymentDatesTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.PerformancePayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PerformancePayoutTypeTabulator.Impl.class)
public interface PerformancePayoutTypeTabulator extends Tabulator<PerformancePayout> {
	@Singleton
	class Impl implements PerformancePayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field observationTermsField;
		private final Field valuationDatesField;
		private final Field paymentDatesField;
		private final Field underlierField;
		private final Field fxFeatureField;
		private final Field returnTermsField;
		private final Field portfolioReturnTermsField;
		private final Field initialValuationPriceField;
		private final Field interimValuationPriceField;
		private final Field finalValuationPriceField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final ObservationTermsTypeTabulator observationTermsTypeTabulator;
		private final ValuationDatesTypeTabulator valuationDatesTypeTabulator;
		private final PaymentDatesTypeTabulator paymentDatesTypeTabulator;
		private final UnderlierTypeTabulator underlierTypeTabulator;
		private final FxFeatureTypeTabulator fxFeatureTypeTabulator;
		private final ReturnTermsTypeTabulator returnTermsTypeTabulator;
		private final PortfolioReturnTermsTypeTabulator portfolioReturnTermsTypeTabulator;
		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, ObservationTermsTypeTabulator observationTermsTypeTabulator, ValuationDatesTypeTabulator valuationDatesTypeTabulator, PaymentDatesTypeTabulator paymentDatesTypeTabulator, UnderlierTypeTabulator underlierTypeTabulator, FxFeatureTypeTabulator fxFeatureTypeTabulator, ReturnTermsTypeTabulator returnTermsTypeTabulator, PortfolioReturnTermsTypeTabulator portfolioReturnTermsTypeTabulator, PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.observationTermsTypeTabulator = observationTermsTypeTabulator;
			this.valuationDatesTypeTabulator = valuationDatesTypeTabulator;
			this.paymentDatesTypeTabulator = paymentDatesTypeTabulator;
			this.underlierTypeTabulator = underlierTypeTabulator;
			this.fxFeatureTypeTabulator = fxFeatureTypeTabulator;
			this.returnTermsTypeTabulator = returnTermsTypeTabulator;
			this.portfolioReturnTermsTypeTabulator = portfolioReturnTermsTypeTabulator;
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
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
			this.observationTermsField = new FieldImpl(
				"observationTerms",
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
			this.paymentDatesField = new FieldImpl(
				"paymentDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fxFeatureField = new FieldImpl(
				"fxFeature",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.returnTermsField = new FieldImpl(
				"returnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.portfolioReturnTermsField = new FieldImpl(
				"portfolioReturnTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialValuationPriceField = new FieldImpl(
				"initialValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interimValuationPriceField = new FieldImpl(
				"interimValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalValuationPriceField = new FieldImpl(
				"finalValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PerformancePayout input) {
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
			FieldValue observationTerms = Optional.ofNullable(input.getObservationTerms())
				.map(x -> new NestedFieldValueImpl(observationTermsField, Optional.of(observationTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationTermsField, Optional.empty()));
			FieldValue valuationDates = Optional.ofNullable(input.getValuationDates())
				.map(x -> new NestedFieldValueImpl(valuationDatesField, Optional.of(valuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationDatesField, Optional.empty()));
			FieldValue paymentDates = Optional.ofNullable(input.getPaymentDates())
				.map(x -> new NestedFieldValueImpl(paymentDatesField, Optional.of(paymentDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDatesField, Optional.empty()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(underlierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue fxFeature = Optional.ofNullable(input.getFxFeature())
				.map(x -> x.stream()
					.map(_x -> fxFeatureTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(fxFeatureField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(fxFeatureField, Optional.empty()));
			FieldValue returnTerms = Optional.ofNullable(input.getReturnTerms())
				.map(x -> new NestedFieldValueImpl(returnTermsField, Optional.of(returnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(returnTermsField, Optional.empty()));
			FieldValue portfolioReturnTerms = Optional.ofNullable(input.getPortfolioReturnTerms())
				.map(x -> x.stream()
					.map(_x -> portfolioReturnTermsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(portfolioReturnTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(portfolioReturnTermsField, Optional.empty()));
			FieldValue initialValuationPrice = Optional.ofNullable(input.getInitialValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.empty()));
			FieldValue interimValuationPrice = Optional.ofNullable(input.getInterimValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.empty()));
			FieldValue finalValuationPrice = Optional.ofNullable(input.getFinalValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				observationTerms,
				valuationDates,
				paymentDates,
				underlier,
				fxFeature,
				returnTerms,
				portfolioReturnTerms,
				initialValuationPrice,
				interimValuationPrice,
				finalValuationPrice
			);
		}
	}
}
