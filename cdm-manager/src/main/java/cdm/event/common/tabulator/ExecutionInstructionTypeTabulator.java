package cdm.event.common.tabulator;

import cdm.base.datetime.tabulator.TimeZoneTypeTabulator;
import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.base.staticdata.party.tabulator.AncillaryPartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.CounterpartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.common.ExecutionInstruction;
import cdm.observable.asset.tabulator.PriceQuantityTypeTabulator;
import cdm.product.collateral.tabulator.CollateralTypeTabulator;
import cdm.product.template.tabulator.NonTransferableProductTypeTabulator;
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


@ImplementedBy(ExecutionInstructionTypeTabulator.Impl.class)
public interface ExecutionInstructionTypeTabulator extends Tabulator<ExecutionInstruction> {
	@Singleton
	class Impl implements ExecutionInstructionTypeTabulator {
		private final Field productField;
		private final Field priceQuantityField;
		private final Field counterpartyField;
		private final Field ancillaryPartyField;
		private final Field partiesField;
		private final Field partyRolesField;
		private final Field executionDetailsField;
		private final Field tradeDateField;
		private final Field tradeTimeField;
		private final Field tradeIdentifierField;
		private final Field collateralField;
		private final Field lotIdentifierField;

		private final NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator;
		private final PriceQuantityTypeTabulator priceQuantityTypeTabulator;
		private final CounterpartyTypeTabulator counterpartyTypeTabulator;
		private final AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator;
		private final PartyTypeTabulator partyTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final ExecutionDetailsTypeTabulator executionDetailsTypeTabulator;
		private final TimeZoneTypeTabulator timeZoneTypeTabulator;
		private final TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator;
		private final CollateralTypeTabulator collateralTypeTabulator;
		private final IdentifierTypeTabulator identifierTypeTabulator;

		@Inject
		public Impl(NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator, PriceQuantityTypeTabulator priceQuantityTypeTabulator, CounterpartyTypeTabulator counterpartyTypeTabulator, AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator, PartyTypeTabulator partyTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, ExecutionDetailsTypeTabulator executionDetailsTypeTabulator, TimeZoneTypeTabulator timeZoneTypeTabulator, TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator, CollateralTypeTabulator collateralTypeTabulator, IdentifierTypeTabulator identifierTypeTabulator) {
			this.nonTransferableProductTypeTabulator = nonTransferableProductTypeTabulator;
			this.priceQuantityTypeTabulator = priceQuantityTypeTabulator;
			this.counterpartyTypeTabulator = counterpartyTypeTabulator;
			this.ancillaryPartyTypeTabulator = ancillaryPartyTypeTabulator;
			this.partyTypeTabulator = partyTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.executionDetailsTypeTabulator = executionDetailsTypeTabulator;
			this.timeZoneTypeTabulator = timeZoneTypeTabulator;
			this.tradeIdentifierTypeTabulator = tradeIdentifierTypeTabulator;
			this.collateralTypeTabulator = collateralTypeTabulator;
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.productField = new FieldImpl(
				"product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceQuantityField = new FieldImpl(
				"priceQuantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.counterpartyField = new FieldImpl(
				"counterparty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ancillaryPartyField = new FieldImpl(
				"ancillaryParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partiesField = new FieldImpl(
				"parties",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyRolesField = new FieldImpl(
				"partyRoles",
				true,
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
			this.tradeDateField = new FieldImpl(
				"tradeDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeTimeField = new FieldImpl(
				"tradeTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeIdentifierField = new FieldImpl(
				"tradeIdentifier",
				true,
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
			this.lotIdentifierField = new FieldImpl(
				"lotIdentifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExecutionInstruction input) {
			FieldValue product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(productField, Optional.of(nonTransferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(productField, Optional.empty()));
			FieldValue priceQuantity = Optional.ofNullable(input.getPriceQuantity())
				.map(x -> x.stream()
					.map(_x -> priceQuantityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceQuantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceQuantityField, Optional.empty()));
			FieldValue counterparty = Optional.ofNullable(input.getCounterparty())
				.map(x -> x.stream()
					.map(_x -> counterpartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(counterpartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(counterpartyField, Optional.empty()));
			FieldValue ancillaryParty = Optional.ofNullable(input.getAncillaryParty())
				.map(x -> x.stream()
					.map(_x -> ancillaryPartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.empty()));
			FieldValue parties = Optional.ofNullable(input.getParties())
				.map(x -> x.stream()
					.map(_x -> partyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partiesField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partiesField, Optional.empty()));
			FieldValue partyRoles = Optional.ofNullable(input.getPartyRoles())
				.map(x -> x.stream()
					.map(_x -> partyRoleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyRolesField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyRolesField, Optional.empty()));
			FieldValue executionDetails = Optional.ofNullable(input.getExecutionDetails())
				.map(x -> new NestedFieldValueImpl(executionDetailsField, Optional.of(executionDetailsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executionDetailsField, Optional.empty()));
			FieldValue tradeDate = new FieldValueImpl(tradeDateField, Optional.ofNullable(input.getTradeDate())
				.map(x -> x.getValue()));
			FieldValue tradeTime = Optional.ofNullable(input.getTradeTime())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(tradeTimeField, Optional.of(timeZoneTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(tradeTimeField, Optional.empty()));
			FieldValue tradeIdentifier = Optional.ofNullable(input.getTradeIdentifier())
				.map(x -> x.stream()
					.map(_x -> tradeIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeIdentifierField, Optional.empty()));
			FieldValue collateral = Optional.ofNullable(input.getCollateral())
				.map(x -> new NestedFieldValueImpl(collateralField, Optional.of(collateralTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralField, Optional.empty()));
			FieldValue lotIdentifier = Optional.ofNullable(input.getLotIdentifier())
				.map(x -> new NestedFieldValueImpl(lotIdentifierField, Optional.of(identifierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lotIdentifierField, Optional.empty()));
			return Arrays.asList(
				product,
				priceQuantity,
				counterparty,
				ancillaryParty,
				parties,
				partyRoles,
				executionDetails,
				tradeDate,
				tradeTime,
				tradeIdentifier,
				collateral,
				lotIdentifier
			);
		}
	}
}
