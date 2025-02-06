package cdm.event.common.tabulator;

import cdm.base.staticdata.party.tabulator.AncillaryPartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.CounterpartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.event.common.PartyChangeInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PartyChangeInstructionTypeTabulator.Impl.class)
public interface PartyChangeInstructionTypeTabulator extends Tabulator<PartyChangeInstruction> {
	@Singleton
	class Impl implements PartyChangeInstructionTypeTabulator {
		private final Field counterpartyField;
		private final Field ancillaryPartyField;
		private final Field partyRoleField;
		private final Field tradeIdField;

		private final CounterpartyTypeTabulator counterpartyTypeTabulator;
		private final AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator;

		@Inject
		public Impl(CounterpartyTypeTabulator counterpartyTypeTabulator, AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator) {
			this.counterpartyTypeTabulator = counterpartyTypeTabulator;
			this.ancillaryPartyTypeTabulator = ancillaryPartyTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.tradeIdentifierTypeTabulator = tradeIdentifierTypeTabulator;
			this.counterpartyField = new FieldImpl(
				"counterparty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ancillaryPartyField = new FieldImpl(
				"ancillaryParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyRoleField = new FieldImpl(
				"partyRole",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeIdField = new FieldImpl(
				"tradeId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PartyChangeInstruction input) {
			FieldValue counterparty = Optional.ofNullable(input.getCounterparty())
				.map(x -> new NestedFieldValueImpl(counterpartyField, Optional.of(counterpartyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(counterpartyField, Optional.empty()));
			FieldValue ancillaryParty = Optional.ofNullable(input.getAncillaryParty())
				.map(x -> new NestedFieldValueImpl(ancillaryPartyField, Optional.of(ancillaryPartyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ancillaryPartyField, Optional.empty()));
			FieldValue partyRole = Optional.ofNullable(input.getPartyRole())
				.map(x -> new NestedFieldValueImpl(partyRoleField, Optional.of(partyRoleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyRoleField, Optional.empty()));
			FieldValue tradeId = Optional.ofNullable(input.getTradeId())
				.map(x -> x.stream()
					.map(_x -> tradeIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeIdField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeIdField, Optional.empty()));
			return Arrays.asList(
				counterparty,
				ancillaryParty,
				partyRole,
				tradeId
			);
		}
	}
}
