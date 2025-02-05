package cdm.product.asset.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.observable.asset.tabulator.TransactedPriceTypeTabulator;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.common.settlement.tabulator.PrincipalPaymentsTypeTabulator;
import cdm.product.common.settlement.tabulator.ResolvablePriceQuantityTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CreditDefaultPayoutTypeTabulator.Impl.class)
public interface CreditDefaultPayoutTypeTabulator extends Tabulator<CreditDefaultPayout> {
	@Singleton
	class Impl implements CreditDefaultPayoutTypeTabulator {
		private final Field payerReceiverField;
		private final Field priceQuantityField;
		private final Field principalPaymentField;
		private final Field settlementTermsField;
		private final Field generalTermsField;
		private final Field protectionTermsField;
		private final Field transactedPriceField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final GeneralTermsTypeTabulator generalTermsTypeTabulator;
		private final ProtectionTermsTypeTabulator protectionTermsTypeTabulator;
		private final TransactedPriceTypeTabulator transactedPriceTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, PrincipalPaymentsTypeTabulator principalPaymentsTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, GeneralTermsTypeTabulator generalTermsTypeTabulator, ProtectionTermsTypeTabulator protectionTermsTypeTabulator, TransactedPriceTypeTabulator transactedPriceTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.principalPaymentsTypeTabulator = principalPaymentsTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.generalTermsTypeTabulator = generalTermsTypeTabulator;
			this.protectionTermsTypeTabulator = protectionTermsTypeTabulator;
			this.transactedPriceTypeTabulator = transactedPriceTypeTabulator;
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
			this.generalTermsField = new FieldImpl(
				"generalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.protectionTermsField = new FieldImpl(
				"protectionTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transactedPriceField = new FieldImpl(
				"transactedPrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditDefaultPayout input) {
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
			FieldValue generalTerms = Optional.ofNullable(input.getGeneralTerms())
				.map(x -> new NestedFieldValueImpl(generalTermsField, Optional.of(generalTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(generalTermsField, Optional.empty()));
			FieldValue protectionTerms = Optional.ofNullable(input.getProtectionTerms())
				.map(x -> x.stream()
					.map(_x -> protectionTermsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(protectionTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(protectionTermsField, Optional.empty()));
			FieldValue transactedPrice = Optional.ofNullable(input.getTransactedPrice())
				.map(x -> new NestedFieldValueImpl(transactedPriceField, Optional.of(transactedPriceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(transactedPriceField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				priceQuantity,
				principalPayment,
				settlementTerms,
				generalTerms,
				protectionTerms,
				transactedPrice
			);
		}
	}
}
