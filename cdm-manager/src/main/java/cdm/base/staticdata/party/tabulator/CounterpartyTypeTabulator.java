package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.Counterparty;
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


@ImplementedBy(CounterpartyTypeTabulator.Impl.class)
public interface CounterpartyTypeTabulator extends Tabulator<Counterparty> {
	@Singleton
	class Impl implements CounterpartyTypeTabulator {
		private final Field roleField;
		private final Field partyReferenceField;

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
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Counterparty input) {
			FieldValue role = new FieldValueImpl(roleField, Optional.ofNullable(input.getRole()));
			FieldValue partyReference = Optional.ofNullable(input.getPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(partyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyReferenceField, Optional.empty()));
			return Arrays.asList(
				role,
				partyReference
			);
		}
	}
}
