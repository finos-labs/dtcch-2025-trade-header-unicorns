package cdm.base.staticdata.identifier.tabulator;

import cdm.base.staticdata.identifier.AssignedIdentifier;
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


@ImplementedBy(AssignedIdentifierTypeTabulator.Impl.class)
public interface AssignedIdentifierTypeTabulator extends Tabulator<AssignedIdentifier> {
	@Singleton
	class Impl implements AssignedIdentifierTypeTabulator {
		private final Field identifierField;
		private final Field versionField;

		public Impl() {
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.versionField = new FieldImpl(
				"version",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssignedIdentifier input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier())
				.map(x -> x.getValue()));
			FieldValue version = new FieldValueImpl(versionField, Optional.ofNullable(input.getVersion()));
			return Arrays.asList(
				identifier,
				version
			);
		}
	}
}
