package cdm.event.qualification.functions;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.SplitInstruction;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.functions.FilterClosedTradeStates;
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

@ImplementedBy(Qualify_Allocation.Qualify_AllocationDefault.class)
public abstract class Qualify_Allocation implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterClosedTradeStates filterClosedTradeStates;
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

	protected abstract MapperS<? extends ReferenceWithMetaTradeState> beforeTradeState(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> closedTradeStates(BusinessEvent businessEvent);

	protected abstract MapperC<? extends TradeState> openTradeStates(BusinessEvent businessEvent);

	public static class Qualify_AllocationDefault extends Qualify_Allocation {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = areEqual(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", _businessEvent -> _businessEvent.getIntent()), MapperS.of(EventIntentEnum.ALLOCATION), CardinalityOperator.All).and(areEqual(MapperS.of(closedTradeStates(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(greaterThanEquals(MapperS.of(openTradeStates(businessEvent).resultCount()), MapperS.of(1), CardinalityOperator.All)).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<SplitInstruction>map("getSplit", primitiveInstruction -> primitiveInstruction.getSplit()))).and(areEqual(beforeTradeState(businessEvent).<TradeState>map("Type coercion", referenceWithMetaTradeState -> referenceWithMetaTradeState == null ? null : referenceWithMetaTradeState.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), MapperS.of(closedTradeStates(businessEvent).get()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), CardinalityOperator.All)).and(areEqual(openTradeStates(businessEvent)
				.mapItem(item -> notEqual(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), beforeTradeState(businessEvent).<TradeState>map("Type coercion", _referenceWithMetaTradeState -> _referenceWithMetaTradeState == null ? null : _referenceWithMetaTradeState.getValue()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<Counterparty>mapC("getCounterparty", trade -> trade.getCounterparty()).<ReferenceWithMetaParty>map("getPartyReference", counterparty -> counterparty.getPartyReference()).<Party>map("Type coercion", referenceWithMetaParty -> referenceWithMetaParty.getValue()), CardinalityOperator.Any).asMapper()), MapperS.of(true), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends ReferenceWithMetaTradeState> beforeTradeState(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).get());
		}
		
		@Override
		protected MapperC<? extends TradeState> closedTradeStates(BusinessEvent businessEvent) {
			return MapperC.<TradeState>of(filterClosedTradeStates.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti()));
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
