package cdm.event.position.tabulator;

import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.common.tabulator.ContractDetailsTypeTabulator;
import cdm.event.common.tabulator.ExecutionDetailsTypeTabulator;
import cdm.event.common.tabulator.PositionIdentifierTypeTabulator;
import cdm.event.common.tabulator.TradeStateTypeTabulator;
import cdm.event.position.CounterpartyPosition;
import cdm.product.collateral.tabulator.CollateralTypeTabulator;
import cdm.product.template.tabulator.TradableProductTypeTabulator;
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


@ImplementedBy(CounterpartyPositionTypeTabulator.Impl.class)
public interface CounterpartyPositionTypeTabulator extends Tabulator<CounterpartyPosition> {
	@Singleton
	class Impl implements CounterpartyPositionTypeTabulator {
		private final Field contractDetailsField;
		private final Field executionDetailsField;
		private final Field collateralField;
		private final Field positionIdentifierField;
		private final Field openDateTimeField;
		private final Field tradeReferenceField;
		private final Field partyField;
		private final Field partyRoleField;
		private final Field positionBaseField;

		private final ContractDetailsTypeTabulator contractDetailsTypeTabulator;
		private final ExecutionDetailsTypeTabulator executionDetailsTypeTabulator;
		private final CollateralTypeTabulator collateralTypeTabulator;
		private final PositionIdentifierTypeTabulator positionIdentifierTypeTabulator;
		private final TradeStateTypeTabulator tradeStateTypeTabulator;
		private final PartyTypeTabulator partyTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final TradableProductTypeTabulator tradableProductTypeTabulator;

		@Inject
		public Impl(ContractDetailsTypeTabulator contractDetailsTypeTabulator, ExecutionDetailsTypeTabulator executionDetailsTypeTabulator, CollateralTypeTabulator collateralTypeTabulator, PositionIdentifierTypeTabulator positionIdentifierTypeTabulator, TradeStateTypeTabulator tradeStateTypeTabulator, PartyTypeTabulator partyTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, TradableProductTypeTabulator tradableProductTypeTabulator) {
			this.contractDetailsTypeTabulator = contractDetailsTypeTabulator;
			this.executionDetailsTypeTabulator = executionDetailsTypeTabulator;
			this.collateralTypeTabulator = collateralTypeTabulator;
			this.positionIdentifierTypeTabulator = positionIdentifierTypeTabulator;
			this.tradeStateTypeTabulator = tradeStateTypeTabulator;
			this.partyTypeTabulator = partyTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.tradableProductTypeTabulator = tradableProductTypeTabulator;
			this.contractDetailsField = new FieldImpl(
				"contractDetails",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.executionDetailsField = new FieldImpl(
				"executionDetails",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralField = new FieldImpl(
				"collateral",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.positionIdentifierField = new FieldImpl(
				"positionIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.openDateTimeField = new FieldImpl(
				"openDateTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeReferenceField = new FieldImpl(
				"tradeReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyField = new FieldImpl(
				"party",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyRoleField = new FieldImpl(
				"partyRole",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.positionBaseField = new FieldImpl(
				"positionBase",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CounterpartyPosition input) {
			FieldValue contractDetails = Optional.ofNullable(input.getContractDetails())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(contractDetailsField, Optional.of(contractDetailsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contractDetailsField, Optional.empty()));
			FieldValue executionDetails = Optional.ofNullable(input.getExecutionDetails())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(executionDetailsField, Optional.of(executionDetailsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executionDetailsField, Optional.empty()));
			FieldValue collateral = Optional.ofNullable(input.getCollateral())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(collateralField, Optional.of(collateralTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralField, Optional.empty()));
			FieldValue positionIdentifier = Optional.ofNullable(input.getPositionIdentifier())
				.map(x -> x.stream()
					.map(_x -> positionIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(positionIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(positionIdentifierField, Optional.empty()));
			FieldValue openDateTime = new FieldValueImpl(openDateTimeField, Optional.ofNullable(input.getOpenDateTime()));
			FieldValue tradeReference = Optional.ofNullable(input.getTradeReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> tradeStateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeReferenceField, Optional.empty()));
			FieldValue party = Optional.ofNullable(input.getParty())
				.map(x -> x.stream()
					.map(_x -> partyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyField, Optional.empty()));
			FieldValue partyRole = Optional.ofNullable(input.getPartyRole())
				.map(x -> x.stream()
					.map(_x -> partyRoleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyRoleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyRoleField, Optional.empty()));
			FieldValue positionBase = Optional.ofNullable(input.getPositionBase())
				.map(x -> new NestedFieldValueImpl(positionBaseField, Optional.of(tradableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(positionBaseField, Optional.empty()));
			return Arrays.asList(
				contractDetails,
				executionDetails,
				collateral,
				positionIdentifier,
				openDateTime,
				tradeReference,
				party,
				partyRole,
				positionBase
			);
		}
	}
}
