package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.PartyRole;
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


@ImplementedBy(PartyRoleTypeTabulator.Impl.class)
public interface PartyRoleTypeTabulator extends Tabulator<PartyRole> {
	@Singleton
	class Impl implements PartyRoleTypeTabulator {
		private final Field partyReferenceField;
		private final Field roleField;
		private final Field ownershipPartyReferenceField;

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
			this.roleField = new FieldImpl(
				"role",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ownershipPartyReferenceField = new FieldImpl(
				"ownershipPartyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PartyRole input) {
			FieldValue partyReference = Optional.ofNullable(input.getPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(partyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyReferenceField, Optional.empty()));
			FieldValue role = new FieldValueImpl(roleField, Optional.ofNullable(input.getRole()));
			FieldValue ownershipPartyReference = Optional.ofNullable(input.getOwnershipPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(ownershipPartyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ownershipPartyReferenceField, Optional.empty()));
			return Arrays.asList(
				partyReference,
				role,
				ownershipPartyReference
			);
		}
	}
}
