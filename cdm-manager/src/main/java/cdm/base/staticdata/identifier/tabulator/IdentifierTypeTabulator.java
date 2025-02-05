package cdm.base.staticdata.identifier.tabulator;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
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


@ImplementedBy(IdentifierTypeTabulator.Impl.class)
public interface IdentifierTypeTabulator extends Tabulator<Identifier> {
	@Singleton
	class Impl implements IdentifierTypeTabulator {
		private final Field issuerReferenceField;
		private final Field issuerField;
		private final Field assignedIdentifierField;

		private final PartyTypeTabulator partyTypeTabulator;
		private final AssignedIdentifierTypeTabulator assignedIdentifierTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator, AssignedIdentifierTypeTabulator assignedIdentifierTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.assignedIdentifierTypeTabulator = assignedIdentifierTypeTabulator;
			this.issuerReferenceField = new FieldImpl(
				"issuerReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.issuerField = new FieldImpl(
				"issuer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.assignedIdentifierField = new FieldImpl(
				"assignedIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Identifier input) {
			FieldValue issuerReference = Optional.ofNullable(input.getIssuerReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(issuerReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(issuerReferenceField, Optional.empty()));
			FieldValue issuer = new FieldValueImpl(issuerField, Optional.ofNullable(input.getIssuer())
				.map(x -> x.getValue()));
			FieldValue assignedIdentifier = Optional.ofNullable(input.getAssignedIdentifier())
				.map(x -> x.stream()
					.map(_x -> assignedIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(assignedIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(assignedIdentifierField, Optional.empty()));
			return Arrays.asList(
				issuerReference,
				issuer,
				assignedIdentifier
			);
		}
	}
}
