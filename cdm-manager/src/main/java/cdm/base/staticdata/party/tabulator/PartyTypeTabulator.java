package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.Party;
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


@ImplementedBy(PartyTypeTabulator.Impl.class)
public interface PartyTypeTabulator extends Tabulator<Party> {
	@Singleton
	class Impl implements PartyTypeTabulator {
		private final Field partyIdField;
		private final Field nameField;
		private final Field businessUnitField;
		private final Field personField;
		private final Field personRoleField;
		private final Field accountField;
		private final Field contactInformationField;

		private final PartyIdentifierTypeTabulator partyIdentifierTypeTabulator;
		private final BusinessUnitTypeTabulator businessUnitTypeTabulator;
		private final NaturalPersonTypeTabulator naturalPersonTypeTabulator;
		private final NaturalPersonRoleTypeTabulator naturalPersonRoleTypeTabulator;
		private final AccountTypeTabulator accountTypeTabulator;
		private final ContactInformationTypeTabulator contactInformationTypeTabulator;

		@Inject
		public Impl(PartyIdentifierTypeTabulator partyIdentifierTypeTabulator, BusinessUnitTypeTabulator businessUnitTypeTabulator, NaturalPersonTypeTabulator naturalPersonTypeTabulator, NaturalPersonRoleTypeTabulator naturalPersonRoleTypeTabulator, AccountTypeTabulator accountTypeTabulator, ContactInformationTypeTabulator contactInformationTypeTabulator) {
			this.partyIdentifierTypeTabulator = partyIdentifierTypeTabulator;
			this.businessUnitTypeTabulator = businessUnitTypeTabulator;
			this.naturalPersonTypeTabulator = naturalPersonTypeTabulator;
			this.naturalPersonRoleTypeTabulator = naturalPersonRoleTypeTabulator;
			this.accountTypeTabulator = accountTypeTabulator;
			this.contactInformationTypeTabulator = contactInformationTypeTabulator;
			this.partyIdField = new FieldImpl(
				"partyId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessUnitField = new FieldImpl(
				"businessUnit",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.personField = new FieldImpl(
				"person",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.personRoleField = new FieldImpl(
				"personRole",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountField = new FieldImpl(
				"account",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contactInformationField = new FieldImpl(
				"contactInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Party input) {
			FieldValue partyId = Optional.ofNullable(input.getPartyId())
				.map(x -> x.stream()
					.map(_x -> partyIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyIdField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyIdField, Optional.empty()));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName())
				.map(x -> x.getValue()));
			FieldValue businessUnit = Optional.ofNullable(input.getBusinessUnit())
				.map(x -> x.stream()
					.map(_x -> businessUnitTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(businessUnitField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(businessUnitField, Optional.empty()));
			FieldValue person = Optional.ofNullable(input.getPerson())
				.map(x -> x.stream()
					.map(_x -> naturalPersonTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(personField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(personField, Optional.empty()));
			FieldValue personRole = Optional.ofNullable(input.getPersonRole())
				.map(x -> x.stream()
					.map(_x -> naturalPersonRoleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(personRoleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(personRoleField, Optional.empty()));
			FieldValue account = Optional.ofNullable(input.getAccount())
				.map(x -> new NestedFieldValueImpl(accountField, Optional.of(accountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(accountField, Optional.empty()));
			FieldValue contactInformation = Optional.ofNullable(input.getContactInformation())
				.map(x -> new NestedFieldValueImpl(contactInformationField, Optional.of(contactInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contactInformationField, Optional.empty()));
			return Arrays.asList(
				partyId,
				name,
				businessUnit,
				person,
				personRole,
				account,
				contactInformation
			);
		}
	}
}
