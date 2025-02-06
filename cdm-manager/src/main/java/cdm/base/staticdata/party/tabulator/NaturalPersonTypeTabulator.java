package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.NaturalPerson;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(NaturalPersonTypeTabulator.Impl.class)
public interface NaturalPersonTypeTabulator extends Tabulator<NaturalPerson> {
	@Singleton
	class Impl implements NaturalPersonTypeTabulator {
		private final Field personIdField;
		private final Field honorificField;
		private final Field firstNameField;
		private final Field middleNameField;
		private final Field initialField;
		private final Field surnameField;
		private final Field suffixField;
		private final Field dateOfBirthField;
		private final Field contactInformationField;

		private final PersonIdentifierTypeTabulator personIdentifierTypeTabulator;
		private final ContactInformationTypeTabulator contactInformationTypeTabulator;

		@Inject
		public Impl(PersonIdentifierTypeTabulator personIdentifierTypeTabulator, ContactInformationTypeTabulator contactInformationTypeTabulator) {
			this.personIdentifierTypeTabulator = personIdentifierTypeTabulator;
			this.contactInformationTypeTabulator = contactInformationTypeTabulator;
			this.personIdField = new FieldImpl(
				"personId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.honorificField = new FieldImpl(
				"honorific",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstNameField = new FieldImpl(
				"firstName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.middleNameField = new FieldImpl(
				"middleName",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialField = new FieldImpl(
				"initial",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.surnameField = new FieldImpl(
				"surname",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.suffixField = new FieldImpl(
				"suffix",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateOfBirthField = new FieldImpl(
				"dateOfBirth",
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
		public List<FieldValue> tabulate(NaturalPerson input) {
			FieldValue personId = Optional.ofNullable(input.getPersonId())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> personIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(personIdField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(personIdField, Optional.empty()));
			FieldValue honorific = new FieldValueImpl(honorificField, Optional.ofNullable(input.getHonorific()));
			FieldValue firstName = new FieldValueImpl(firstNameField, Optional.ofNullable(input.getFirstName()));
			FieldValue middleName = new FieldValueImpl(middleNameField, Optional.ofNullable(input.getMiddleName()));
			FieldValue initial = new FieldValueImpl(initialField, Optional.ofNullable(input.getInitial()));
			FieldValue surname = new FieldValueImpl(surnameField, Optional.ofNullable(input.getSurname()));
			FieldValue suffix = new FieldValueImpl(suffixField, Optional.ofNullable(input.getSuffix()));
			FieldValue dateOfBirth = new FieldValueImpl(dateOfBirthField, Optional.ofNullable(input.getDateOfBirth()));
			FieldValue contactInformation = Optional.ofNullable(input.getContactInformation())
				.map(x -> new NestedFieldValueImpl(contactInformationField, Optional.of(contactInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contactInformationField, Optional.empty()));
			return Arrays.asList(
				personId,
				honorific,
				firstName,
				middleName,
				initial,
				surname,
				suffix,
				dateOfBirth,
				contactInformation
			);
		}
	}
}
