package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.AncillaryParty;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AncillaryPartyTypeTabulator.Impl.class)
public interface AncillaryPartyTypeTabulator extends Tabulator<AncillaryParty> {
	@Singleton
	class Impl implements AncillaryPartyTypeTabulator {
		private final Field roleField;
		private final Field partyReferenceField;
		private final Field onBehalfOfField;

		private final PartyTypeTabulator partyTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.roleField = new FieldImpl(
				"role",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyReferenceField = new FieldImpl(
				"partyReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.onBehalfOfField = new FieldImpl(
				"onBehalfOf",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AncillaryParty input) {
			FieldValue role = new FieldValueImpl(roleField, Optional.ofNullable(input.getRole()));
			FieldValue partyReference = Optional.ofNullable(input.getPartyReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> partyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyReferenceField, Optional.empty()));
			FieldValue onBehalfOf = new FieldValueImpl(onBehalfOfField, Optional.ofNullable(input.getOnBehalfOf()));
			return Arrays.asList(
				role,
				partyReference,
				onBehalfOf
			);
		}
	}
}
