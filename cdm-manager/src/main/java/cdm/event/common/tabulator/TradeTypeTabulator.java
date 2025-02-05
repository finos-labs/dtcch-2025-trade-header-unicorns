package cdm.event.common.tabulator;

import cdm.base.datetime.tabulator.TimeZoneTypeTabulator;
import cdm.base.staticdata.party.tabulator.AccountTypeTabulator;
import cdm.base.staticdata.party.tabulator.AncillaryPartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.CounterpartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.common.Trade;
import cdm.product.collateral.tabulator.CollateralTypeTabulator;
import cdm.product.template.tabulator.NonTransferableProductTypeTabulator;
import cdm.product.template.tabulator.TradeLotTypeTabulator;
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


@ImplementedBy(TradeTypeTabulator.Impl.class)
public interface TradeTypeTabulator extends Tabulator<Trade> {
	@Singleton
	class Impl implements TradeTypeTabulator {
		private final Field productField;
		private final Field tradeLotField;
		private final Field counterpartyField;
		private final Field ancillaryPartyField;
		private final Field adjustmentField;
		private final Field tradeIdentifierField;
		private final Field tradeDateField;
		private final Field tradeTimeField;
		private final Field partyField;
		private final Field partyRoleField;
		private final Field executionDetailsField;
		private final Field contractDetailsField;
		private final Field clearedDateField;
		private final Field collateralField;
		private final Field accountField;

		private final NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator;
		private final TradeLotTypeTabulator tradeLotTypeTabulator;
		private final CounterpartyTypeTabulator counterpartyTypeTabulator;
		private final AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator;
		private final TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator;
		private final TimeZoneTypeTabulator timeZoneTypeTabulator;
		private final PartyTypeTabulator partyTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final ExecutionDetailsTypeTabulator executionDetailsTypeTabulator;
		private final ContractDetailsTypeTabulator contractDetailsTypeTabulator;
		private final CollateralTypeTabulator collateralTypeTabulator;
		private final AccountTypeTabulator accountTypeTabulator;

		@Inject
		public Impl(NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator, TradeLotTypeTabulator tradeLotTypeTabulator, CounterpartyTypeTabulator counterpartyTypeTabulator, AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator, TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator, TimeZoneTypeTabulator timeZoneTypeTabulator, PartyTypeTabulator partyTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, ExecutionDetailsTypeTabulator executionDetailsTypeTabulator, ContractDetailsTypeTabulator contractDetailsTypeTabulator, CollateralTypeTabulator collateralTypeTabulator, AccountTypeTabulator accountTypeTabulator) {
			this.nonTransferableProductTypeTabulator = nonTransferableProductTypeTabulator;
			this.tradeLotTypeTabulator = tradeLotTypeTabulator;
			this.counterpartyTypeTabulator = counterpartyTypeTabulator;
			this.ancillaryPartyTypeTabulator = ancillaryPartyTypeTabulator;
			this.tradeIdentifierTypeTabulator = tradeIdentifierTypeTabulator;
			this.timeZoneTypeTabulator = timeZoneTypeTabulator;
			this.partyTypeTabulator = partyTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.executionDetailsTypeTabulator = executionDetailsTypeTabulator;
			this.contractDetailsTypeTabulator = contractDetailsTypeTabulator;
			this.collateralTypeTabulator = collateralTypeTabulator;
			this.accountTypeTabulator = accountTypeTabulator;
			this.productField = new FieldImpl(
				"product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeLotField = new FieldImpl(
				"tradeLot",
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
			this.adjustmentField = new FieldImpl(
				"adjustment",
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
			this.executionDetailsField = new FieldImpl(
				"executionDetails",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contractDetailsField = new FieldImpl(
				"contractDetails",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.clearedDateField = new FieldImpl(
				"clearedDate",
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
			this.accountField = new FieldImpl(
				"account",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Trade input) {
			FieldValue product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(productField, Optional.of(nonTransferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(productField, Optional.empty()));
			FieldValue tradeLot = Optional.ofNullable(input.getTradeLot())
				.map(x -> x.stream()
					.map(_x -> tradeLotTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeLotField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeLotField, Optional.empty()));
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
			FieldValue adjustment = new FieldValueImpl(adjustmentField, Optional.ofNullable(input.getAdjustment()));
			FieldValue tradeIdentifier = Optional.ofNullable(input.getTradeIdentifier())
				.map(x -> x.stream()
					.map(_x -> tradeIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeIdentifierField, Optional.empty()));
			FieldValue tradeDate = new FieldValueImpl(tradeDateField, Optional.ofNullable(input.getTradeDate())
				.map(x -> x.getValue()));
			FieldValue tradeTime = Optional.ofNullable(input.getTradeTime())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(tradeTimeField, Optional.of(timeZoneTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(tradeTimeField, Optional.empty()));
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
			FieldValue executionDetails = Optional.ofNullable(input.getExecutionDetails())
				.map(x -> new NestedFieldValueImpl(executionDetailsField, Optional.of(executionDetailsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executionDetailsField, Optional.empty()));
			FieldValue contractDetails = Optional.ofNullable(input.getContractDetails())
				.map(x -> new NestedFieldValueImpl(contractDetailsField, Optional.of(contractDetailsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(contractDetailsField, Optional.empty()));
			FieldValue clearedDate = new FieldValueImpl(clearedDateField, Optional.ofNullable(input.getClearedDate()));
			FieldValue collateral = Optional.ofNullable(input.getCollateral())
				.map(x -> new NestedFieldValueImpl(collateralField, Optional.of(collateralTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralField, Optional.empty()));
			FieldValue account = Optional.ofNullable(input.getAccount())
				.map(x -> x.stream()
					.map(_x -> accountTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(accountField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(accountField, Optional.empty()));
			return Arrays.asList(
				product,
				tradeLot,
				counterparty,
				ancillaryParty,
				adjustment,
				tradeIdentifier,
				tradeDate,
				tradeTime,
				party,
				partyRole,
				executionDetails,
				contractDetails,
				clearedDate,
				collateral,
				account
			);
		}
	}
}
