package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.BuyerSellerTypeTabulator;
import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.asset.tabulator.AssetDeliveryInformationTypeTabulator;
import cdm.product.common.schedule.tabulator.ObservationTermsTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.OptionPayout;
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


@ImplementedBy(OptionPayoutTypeTabulator.Impl.class)
public interface OptionPayoutTypeTabulator extends Tabulator<OptionPayout> {
	@Singleton
	class Impl implements OptionPayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field buyerSellerField;
		private final Field featureField;
		private final Field observationTermsField;
		private final Field scheduleField;
		private final Field deliveryField;
		private final Field underlierField;
		private final Field optionTypeField;
		private final Field exerciseTermsField;
		private final Field strikeField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final BuyerSellerTypeTabulator buyerSellerTypeTabulator;
		private final OptionFeatureTypeTabulator optionFeatureTypeTabulator;
		private final ObservationTermsTypeTabulator observationTermsTypeTabulator;
		private final CalculationScheduleTypeTabulator calculationScheduleTypeTabulator;
		private final AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator;
		private final UnderlierTypeTabulator underlierTypeTabulator;
		private final ExerciseTermsTypeTabulator exerciseTermsTypeTabulator;
		private final OptionStrikeTypeTabulator optionStrikeTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, BuyerSellerTypeTabulator buyerSellerTypeTabulator, OptionFeatureTypeTabulator optionFeatureTypeTabulator, ObservationTermsTypeTabulator observationTermsTypeTabulator, CalculationScheduleTypeTabulator calculationScheduleTypeTabulator, AssetDeliveryInformationTypeTabulator assetDeliveryInformationTypeTabulator, UnderlierTypeTabulator underlierTypeTabulator, ExerciseTermsTypeTabulator exerciseTermsTypeTabulator, OptionStrikeTypeTabulator optionStrikeTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.buyerSellerTypeTabulator = buyerSellerTypeTabulator;
			this.optionFeatureTypeTabulator = optionFeatureTypeTabulator;
			this.observationTermsTypeTabulator = observationTermsTypeTabulator;
			this.calculationScheduleTypeTabulator = calculationScheduleTypeTabulator;
			this.assetDeliveryInformationTypeTabulator = assetDeliveryInformationTypeTabulator;
			this.underlierTypeTabulator = underlierTypeTabulator;
			this.exerciseTermsTypeTabulator = exerciseTermsTypeTabulator;
			this.optionStrikeTypeTabulator = optionStrikeTypeTabulator;
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
			this.buyerSellerField = new FieldImpl(
				"buyerSeller",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.featureField = new FieldImpl(
				"feature",
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
			this.scheduleField = new FieldImpl(
				"schedule",
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
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionTypeField = new FieldImpl(
				"optionType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseTermsField = new FieldImpl(
				"exerciseTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.strikeField = new FieldImpl(
				"strike",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OptionPayout input) {
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
			FieldValue buyerSeller = Optional.ofNullable(input.getBuyerSeller())
				.map(x -> new NestedFieldValueImpl(buyerSellerField, Optional.of(buyerSellerTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(buyerSellerField, Optional.empty()));
			FieldValue feature = Optional.ofNullable(input.getFeature())
				.map(x -> new NestedFieldValueImpl(featureField, Optional.of(optionFeatureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(featureField, Optional.empty()));
			FieldValue observationTerms = Optional.ofNullable(input.getObservationTerms())
				.map(x -> new NestedFieldValueImpl(observationTermsField, Optional.of(observationTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationTermsField, Optional.empty()));
			FieldValue schedule = Optional.ofNullable(input.getSchedule())
				.map(x -> new NestedFieldValueImpl(scheduleField, Optional.of(calculationScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduleField, Optional.empty()));
			FieldValue delivery = Optional.ofNullable(input.getDelivery())
				.map(x -> new NestedFieldValueImpl(deliveryField, Optional.of(assetDeliveryInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryField, Optional.empty()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(underlierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue optionType = new FieldValueImpl(optionTypeField, Optional.ofNullable(input.getOptionType()));
			FieldValue exerciseTerms = Optional.ofNullable(input.getExerciseTerms())
				.map(x -> new NestedFieldValueImpl(exerciseTermsField, Optional.of(exerciseTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseTermsField, Optional.empty()));
			FieldValue strike = Optional.ofNullable(input.getStrike())
				.map(x -> new NestedFieldValueImpl(strikeField, Optional.of(optionStrikeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(strikeField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				buyerSeller,
				feature,
				observationTerms,
				schedule,
				delivery,
				underlier,
				optionType,
				exerciseTerms,
				strike
			);
		}
	}
}
