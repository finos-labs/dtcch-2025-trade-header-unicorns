package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.asset.tabulator.AssetDeliveryInformationTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.SettlementPayout;
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


@ImplementedBy(SettlementPayoutTypeTabulator.Impl.class)
public interface SettlementPayoutTypeTabulator extends Tabulator<SettlementPayout> {
	@Singleton
	class Impl implements SettlementPayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field underlierField;
		private final Field deliveryTermField;
		private final Field deliveryField;
		private final Field scheduleField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final UnderlierTypeTabulator underlierTypeTabulator;
		private final AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator;
		private final CalculationScheduleTypeTabulator calculationScheduleTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, UnderlierTypeTabulator underlierTypeTabulator, AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator, CalculationScheduleTypeTabulator calculationScheduleTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.underlierTypeTabulator = underlierTypeTabulator;
			this.assetDeliveryInformationTypeTabulator = assetDeliveryInformationTypeTabulator;
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
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryTermField = new FieldImpl(
				"deliveryTerm",
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
			this.scheduleField = new FieldImpl(
				"schedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettlementPayout input) {
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
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(underlierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue deliveryTerm = new FieldValueImpl(deliveryTermField, Optional.ofNullable(input.getDeliveryTerm()));
			FieldValue delivery = Optional.ofNullable(input.getDelivery())
				.map(x -> new NestedFieldValueImpl(deliveryField, Optional.of(assetDeliveryInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryField, Optional.empty()));
			FieldValue schedule = Optional.ofNullable(input.getSchedule())
				.map(x -> new NestedFieldValueImpl(scheduleField, Optional.of(calculationScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduleField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				underlier,
				deliveryTerm,
				delivery,
				schedule
			);
		}
	}
}
