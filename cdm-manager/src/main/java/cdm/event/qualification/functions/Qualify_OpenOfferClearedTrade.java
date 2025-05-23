package cdm.event.qualification.functions;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRoleEnum;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.ExecutionInstruction;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeState;
import cdm.event.common.functions.FilterOpenTradeStates;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_OpenOfferClearedTrade.Qualify_OpenOfferClearedTradeDefault.class)
public abstract class Qualify_OpenOfferClearedTrade implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterOpenTradeStates filterOpenTradeStates;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperC<? extends ReferenceWithMetaTradeState> beforeTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent);

	public static class Qualify_OpenOfferClearedTradeDefault extends Qualify_OpenOfferClearedTrade {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", _businessEvent -> _businessEvent.getIntent()), MapperS.of(EventIntentEnum.CLEARING), CardinalityOperator.All).and(areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).resultCount()), MapperS.of(2), CardinalityOperator.All)).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<ExecutionInstruction>map("getExecution", primitiveInstruction -> primitiveInstruction.getExecution()))).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<ContractFormationInstruction>map("getContractFormation", primitiveInstruction -> primitiveInstruction.getContractFormation()))).and(areEqual(MapperS.of(openTradeStates(businessEvent).resultCount()), MapperS.of(2), CardinalityOperator.All)).and(areEqual(openTradeStates(businessEvent)
				.mapItem(item -> notEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), beforeTradeState(businessEvent).<TradeState>map("Type coercion", referenceWithMetaTradeState -> referenceWithMetaTradeState.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), CardinalityOperator.Any).and(notEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()), beforeTradeState(businessEvent).<TradeState>map("Type coercion", referenceWithMetaTradeState -> referenceWithMetaTradeState.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeIdentifier>mapC("getTradeIdentifier", trade -> trade.getTradeIdentifier()), CardinalityOperator.Any)).and(contains(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<PartyRole>mapC("getPartyRole", trade -> trade.getPartyRole()).<PartyRoleEnum>map("getRole", partyRole -> partyRole.getRole()), MapperS.of(PartyRoleEnum.CLEARING_ORGANIZATION))).asMapper()), MapperS.of(true), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends ReferenceWithMetaTradeState> beforeTradeState(BusinessEvent businessEvent) {
			return MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore());
		}
		
		@Override
		protected MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterOpenTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
