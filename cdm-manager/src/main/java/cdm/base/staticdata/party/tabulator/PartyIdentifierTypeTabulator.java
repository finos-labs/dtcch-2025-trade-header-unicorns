package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.PartyIdentifier;
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


@ImplementedBy(PartyIdentifierTypeTabulator.Impl.class)
public interface PartyIdentifierTypeTabulator extends Tabulator<PartyIdentifier> {
	@Singleton
	class Impl implements PartyIdentifierTypeTabulator {
		private final Field identifierField;
		private final Field identifierTypeField;

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
		}

		@Override
		public List<FieldValue> tabulate(PartyIdentifier input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier())
				.map(x -> x.getValue()));
			FieldValue identifierType = new FieldValueImpl(identifierTypeField, Optional.ofNullable(input.getIdentifierType()));
			return Arrays.asList(
				identifier,
				identifierType
			);
		}
	}
}
