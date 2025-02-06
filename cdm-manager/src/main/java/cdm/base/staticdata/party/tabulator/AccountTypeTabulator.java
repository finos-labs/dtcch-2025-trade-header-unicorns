package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.Account;
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


@ImplementedBy(AccountTypeTabulator.Impl.class)
public interface AccountTypeTabulator extends Tabulator<Account> {
	@Singleton
	class Impl implements AccountTypeTabulator {
		private final Field partyReferenceField;
		private final Field accountNumberField;
		private final Field accountNameField;
		private final Field accountTypeField;
		private final Field accountBeneficiaryField;
		private final Field servicingPartyField;

		private final PartyTypeTabulator partyTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.partyReferenceField = new FieldImpl(
				"partyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountNumberField = new FieldImpl(
				"accountNumber",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountNameField = new FieldImpl(
				"accountName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountTypeField = new FieldImpl(
				"accountType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountBeneficiaryField = new FieldImpl(
				"accountBeneficiary",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.servicingPartyField = new FieldImpl(
				"servicingParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Account input) {
			FieldValue partyReference = Optional.ofNullable(input.getPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(partyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyReferenceField, Optional.empty()));
			FieldValue accountNumber = new FieldValueImpl(accountNumberField, Optional.ofNullable(input.getAccountNumber())
				.map(x -> x.getValue()));
			FieldValue accountName = new FieldValueImpl(accountNameField, Optional.ofNullable(input.getAccountName())
				.map(x -> x.getValue()));
			FieldValue accountType = new FieldValueImpl(accountTypeField, Optional.ofNullable(input.getAccountType())
				.map(x -> x.getValue()));
			FieldValue accountBeneficiary = Optional.ofNullable(input.getAccountBeneficiary())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(accountBeneficiaryField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(accountBeneficiaryField, Optional.empty()));
			FieldValue servicingParty = Optional.ofNullable(input.getServicingParty())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(servicingPartyField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(servicingPartyField, Optional.empty()));
			return Arrays.asList(
				partyReference,
				accountNumber,
				accountName,
				accountType,
				accountBeneficiary,
				servicingParty
			);
		}
	}
}
