package cdm.product.collateral.tabulator;

import cdm.base.staticdata.party.tabulator.AccountTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.product.collateral.IndependentAmount;
import cdm.product.common.settlement.tabulator.PaymentDetailTypeTabulator;
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


@ImplementedBy(IndependentAmountTypeTabulator.Impl.class)
public interface IndependentAmountTypeTabulator extends Tabulator<IndependentAmount> {
	@Singleton
	class Impl implements IndependentAmountTypeTabulator {
		private final Field payerPartyReferenceField;
		private final Field payerAccountReferenceField;
		private final Field receiverPartyReferenceField;
		private final Field receiverAccountReferenceField;
		private final Field paymentDetailField;

		private final PartyTypeTabulator partyTypeTabulator;
		private final AccountTypeTabulator accountTypeTabulator;
		private final PaymentDetailTypeTabulator paymentDetailTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator, AccountTypeTabulator accountTypeTabulator, PaymentDetailTypeTabulator paymentDetailTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.accountTypeTabulator = accountTypeTabulator;
			this.paymentDetailTypeTabulator = paymentDetailTypeTabulator;
			this.payerPartyReferenceField = new FieldImpl(
				"payerPartyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payerAccountReferenceField = new FieldImpl(
				"payerAccountReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.receiverPartyReferenceField = new FieldImpl(
				"receiverPartyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.receiverAccountReferenceField = new FieldImpl(
				"receiverAccountReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDetailField = new FieldImpl(
				"paymentDetail",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IndependentAmount input) {
			FieldValue payerPartyReference = Optional.ofNullable(input.getPayerPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(payerPartyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerPartyReferenceField, Optional.empty()));
			FieldValue payerAccountReference = Optional.ofNullable(input.getPayerAccountReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(payerAccountReferenceField, Optional.of(accountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerAccountReferenceField, Optional.empty()));
			FieldValue receiverPartyReference = Optional.ofNullable(input.getReceiverPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(receiverPartyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(receiverPartyReferenceField, Optional.empty()));
			FieldValue receiverAccountReference = Optional.ofNullable(input.getReceiverAccountReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(receiverAccountReferenceField, Optional.of(accountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(receiverAccountReferenceField, Optional.empty()));
			FieldValue paymentDetail = Optional.ofNullable(input.getPaymentDetail())
				.map(x -> x.stream()
					.map(_x -> paymentDetailTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(paymentDetailField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(paymentDetailField, Optional.empty()));
			return Arrays.asList(
				payerPartyReference,
				payerAccountReference,
				receiverPartyReference,
				receiverAccountReference,
				paymentDetail
			);
		}
	}
}
