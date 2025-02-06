package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
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


@ImplementedBy(PartyReferencePayerReceiverTypeTabulator.Impl.class)
public interface PartyReferencePayerReceiverTypeTabulator extends Tabulator<PartyReferencePayerReceiver> {
	@Singleton
	class Impl implements PartyReferencePayerReceiverTypeTabulator {
		private final Field payerPartyReferenceField;
		private final Field payerAccountReferenceField;
		private final Field receiverPartyReferenceField;
		private final Field receiverAccountReferenceField;

		private final PartyTypeTabulator partyTypeTabulator;
		private final AccountTypeTabulator accountTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator, AccountTypeTabulator accountTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.accountTypeTabulator = accountTypeTabulator;
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
		}

		@Override
		public List<FieldValue> tabulate(PartyReferencePayerReceiver input) {
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
			return Arrays.asList(
				payerPartyReference,
				payerAccountReference,
				receiverPartyReference,
				receiverAccountReference
			);
		}
	}
}
