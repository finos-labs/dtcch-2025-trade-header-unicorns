package cdm.product.asset.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.asset.CommodityPayout;
import cdm.product.common.schedule.tabulator.CalculationPeriodDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.PaymentDatesTypeTabulator;
import cdm.product.common.settlement.tabulator.CommodityPriceReturnTermsTypeTabulator;
import cdm.product.common.settlement.tabulator.PricingDatesTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.tabulator.AveragingCalculationTypeTabulator;
import cdm.product.template.tabulator.CalculationScheduleTypeTabulator;
import cdm.product.template.tabulator.FxFeatureTypeTabulator;
import cdm.product.template.tabulator.UnderlierTypeTabulator;
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


@ImplementedBy(CommodityPayoutTypeTabulator.Impl.class)
public interface CommodityPayoutTypeTabulator extends Tabulator<CommodityPayout> {
	@Singleton
	class Impl implements CommodityPayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field averagingFeatureField;
		private final Field commodityPriceReturnTermsField;
		private final Field pricingDatesField;
		private final Field scheduleField;
		private final Field calculationPeriodDatesField;
		private final Field paymentDatesField;
		private final Field underlierField;
		private final Field fxFeatureField;
		private final Field deliveryField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final AveragingCalculationTypeTabulator averagingCalculationTypeTabulator;
		private final CommodityPriceReturnTermsTypeTabulator commodityPriceReturnTermsTypeTabulator;
		private final PricingDatesTypeTabulator pricingDatesTypeTabulator;
		private final CalculationScheduleTypeTabulator calculationScheduleTypeTabulator;
		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;
		private final PaymentDatesTypeTabulator paymentDatesTypeTabulator;
		private final UnderlierTypeTabulator underlierTypeTabulator;
		private final FxFeatureTypeTabulator fxFeatureTypeTabulator;
		private final AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, AveragingCalculationTypeTabulator averagingCalculationTypeTabulator, CommodityPriceReturnTermsTypeTabulator commodityPriceReturnTermsTypeTabulator, PricingDatesTypeTabulator pricingDatesTypeTabulator, CalculationScheduleTypeTabulator calculationScheduleTypeTabulator, CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator, PaymentDatesTypeTabulator paymentDatesTypeTabulator, UnderlierTypeTabulator underlierTypeTabulator, FxFeatureTypeTabulator fxFeatureTypeTabulator, AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.averagingCalculationTypeTabulator = averagingCalculationTypeTabulator;
			this.commodityPriceReturnTermsTypeTabulator = commodityPriceReturnTermsTypeTabulator;
			this.pricingDatesTypeTabulator = pricingDatesTypeTabulator;
			this.calculationScheduleTypeTabulator = calculationScheduleTypeTabulator;
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.paymentDatesTypeTabulator = paymentDatesTypeTabulator;
			this.underlierTypeTabulator = underlierTypeTabulator;
			this.fxFeatureTypeTabulator = fxFeatureTypeTabulator;
			this.assetDeliveryInformationTypeTabulator = assetDeliveryInformationTypeTabulator;
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
			this.averagingFeatureField = new FieldImpl(
				"averagingFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commodityPriceReturnTermsField = new FieldImpl(
				"commodityPriceReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.pricingDatesField = new FieldImpl(
				"pricingDates",
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
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fxFeatureField = new FieldImpl(
				"fxFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryField = new FieldImpl(
				"delivery",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CommodityPayout input) {
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
			FieldValue averagingFeature = Optional.ofNullable(input.getAveragingFeature())
				.map(x -> new NestedFieldValueImpl(averagingFeatureField, Optional.of(averagingCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingFeatureField, Optional.empty()));
			FieldValue commodityPriceReturnTerms = Optional.ofNullable(input.getCommodityPriceReturnTerms())
				.map(x -> new NestedFieldValueImpl(commodityPriceReturnTermsField, Optional.of(commodityPriceReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(commodityPriceReturnTermsField, Optional.empty()));
			FieldValue pricingDates = Optional.ofNullable(input.getPricingDates())
				.map(x -> new NestedFieldValueImpl(pricingDatesField, Optional.of(pricingDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(pricingDatesField, Optional.empty()));
			FieldValue schedule = Optional.ofNullable(input.getSchedule())
				.map(x -> new NestedFieldValueImpl(scheduleField, Optional.of(calculationScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduleField, Optional.empty()));
			FieldValue calculationPeriodDates = Optional.ofNullable(input.getCalculationPeriodDates())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesField, Optional.of(calculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesField, Optional.empty()));
			FieldValue paymentDates = Optional.ofNullable(input.getPaymentDates())
				.map(x -> new NestedFieldValueImpl(paymentDatesField, Optional.of(paymentDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDatesField, Optional.empty()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(underlierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue fxFeature = Optional.ofNullable(input.getFxFeature())
				.map(x -> new NestedFieldValueImpl(fxFeatureField, Optional.of(fxFeatureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxFeatureField, Optional.empty()));
			FieldValue delivery = Optional.ofNullable(input.getDelivery())
				.map(x -> new NestedFieldValueImpl(deliveryField, Optional.of(assetDeliveryInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				averagingFeature,
				commodityPriceReturnTerms,
				pricingDates,
				schedule,
				calculationPeriodDates,
				paymentDates,
				underlier,
				fxFeature,
				delivery
			);
		}
	}
}
