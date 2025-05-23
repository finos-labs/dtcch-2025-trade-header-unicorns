package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.CorporateAction;
import cdm.event.common.Instruction;
import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeState;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_CorporateActionDetermined.Qualify_CorporateActionDeterminedDefault.class)
public abstract class Qualify_CorporateActionDetermined implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {

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

	public static class Qualify_CorporateActionDeterminedDefault extends Qualify_CorporateActionDetermined {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<ObservationInstruction>map("getObservation", primitiveInstruction -> primitiveInstruction.getObservation()).<ObservationEvent>map("getObservationEvent", observationInstruction -> observationInstruction.getObservationEvent()).<CorporateAction>map("getCorporateAction", observationEvent -> observationEvent.getCorporateAction())).or(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("Type coercion", referenceWithMetaTradeState -> referenceWithMetaTradeState.getValue()).<ObservationEvent>mapC("getObservationHistory", tradeState -> tradeState.getObservationHistory()).<CorporateAction>map("getCorporateAction", observationEvent -> observationEvent.getCorporateAction()))).or(exists(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<ObservationEvent>mapC("getObservationHistory", tradeState -> tradeState.getObservationHistory()).<CorporateAction>map("getCorporateAction", observationEvent -> observationEvent.getCorporateAction()))).get();
			
			return is_event;
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
