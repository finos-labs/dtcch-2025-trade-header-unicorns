package cdm.product.template.tabulator;

import cdm.base.staticdata.asset.common.tabulator.AssetTypeTabulator;
import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.AssetPayout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AssetPayoutTypeTabulator.Impl.class)
public interface AssetPayoutTypeTabulator extends Tabulator<AssetPayout> {
	@Singleton
	class Impl implements AssetPayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field assetLegField;
		private final Field underlierField;
		private final Field minimumFeeField;
		private final Field dividendTermsField;
		private final Field tradeTypeField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final AssetLegTypeTabulator assetLegTypeTabulator;
		private final AssetTypeTabulator assetTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;
		private final DividendTermsTypeTabulator dividendTermsTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, AssetLegTypeTabulator assetLegTypeTabulator, AssetTypeTabulator assetTypeTabulator, MoneyTypeTabulator moneyTypeTabulator, DividendTermsTypeTabulator dividendTermsTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.assetLegTypeTabulator = assetLegTypeTabulator;
			this.assetTypeTabulator = assetTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.dividendTermsTypeTabulator = dividendTermsTypeTabulator;
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
			this.assetLegField = new FieldImpl(
				"assetLeg",
				true,
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
			this.minimumFeeField = new FieldImpl(
				"minimumFee",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendTermsField = new FieldImpl(
				"dividendTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeTypeField = new FieldImpl(
				"tradeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetPayout input) {
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
			FieldValue assetLeg = Optional.ofNullable(input.getAssetLeg())
				.map(x -> x.stream()
					.map(_x -> assetLegTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(assetLegField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(assetLegField, Optional.empty()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(assetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue minimumFee = Optional.ofNullable(input.getMinimumFee())
				.map(x -> new NestedFieldValueImpl(minimumFeeField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(minimumFeeField, Optional.empty()));
			FieldValue dividendTerms = Optional.ofNullable(input.getDividendTerms())
				.map(x -> new NestedFieldValueImpl(dividendTermsField, Optional.of(dividendTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendTermsField, Optional.empty()));
			FieldValue tradeType = new FieldValueImpl(tradeTypeField, Optional.ofNullable(input.getTradeType()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				assetLeg,
				underlier,
				minimumFee,
				dividendTerms,
				tradeType
			);
		}
	}
}
