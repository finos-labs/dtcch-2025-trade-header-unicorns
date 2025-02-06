package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.PersonIdentifier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(PersonIdentifierTypeTabulator.Impl.class)
public interface PersonIdentifierTypeTabulator extends Tabulator<PersonIdentifier> {
	@Singleton
	class Impl implements PersonIdentifierTypeTabulator {
		private final Field identifierField;
		private final Field identifierTypeField;
		private final Field countryField;

		public Impl() {
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.identifierTypeField = new FieldImpl(
				"identifierType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.countryField = new FieldImpl(
				"country",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PersonIdentifier input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier())
				.map(x -> x.getValue()));
			FieldValue identifierType = new FieldValueImpl(identifierTypeField, Optional.ofNullable(input.getIdentifierType()));
			FieldValue country = new FieldValueImpl(countryField, Optional.ofNullable(input.getCountry())
				.map(x -> x.getValue()));
			return Arrays.asList(
				identifier,
				identifierType,
				country
			);
		}
	}
}
