package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.base.staticdata.party.BusinessUnit;
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


@ImplementedBy(BusinessUnitTypeTabulator.Impl.class)
public interface BusinessUnitTypeTabulator extends Tabulator<BusinessUnit> {
	@Singleton
	class Impl implements BusinessUnitTypeTabulator {
		private final Field nameField;
		private final Field identifierField;
		private final Field contactInformationField;

		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final ContactInformationTypeTabulator contactInformationTypeTabulator;

		@Inject
		public Impl(IdentifierTypeTabulator identifierTypeTabulator, ContactInformationTypeTabulator contactInformationTypeTabulator) {
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.contactInformationTypeTabulator = contactInformationTypeTabulator;
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.identifierField = new FieldImpl(
				"identifier",
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
		public List<FieldValue> tabulate(BusinessUnit input) {
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName()));
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> new NestedFieldValueImpl(identifierField, Optional.of(identifierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue contactInformation = Optional.ofNullable(input.getContactInformation())
				.map(x -> new NestedFieldValueImpl(contactInformationField, Optional.of(contactInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contactInformationField, Optional.empty()));
			return Arrays.asList(
				name,
				identifier,
				contactInformation
			);
		}
	}
}
