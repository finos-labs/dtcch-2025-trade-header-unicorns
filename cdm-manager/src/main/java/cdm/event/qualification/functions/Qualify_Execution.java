package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Execution.Qualify_ExecutionDefault.class)
public abstract class Qualify_Execution implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {

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

	protected abstract MapperS<? extends PrimitiveInstruction> primitiveInstruction(BusinessEvent businessEvent);

	public static class Qualify_ExecutionDefault extends Qualify_Execution {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = notExists(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", _businessEvent -> _businessEvent.getIntent())).and(onlyExists(primitiveInstruction(businessEvent), Arrays.asList("contractFormation", "execution", "exercise", "partyChange", "quantityChange", "reset", "split", "termsChange", "transfer", "indexTransition", "stockSplit", "observation", "valuation"), Arrays.asList("execution")).or(onlyExists(primitiveInstruction(businessEvent), Arrays.asList("contractFormation", "execution", "exercise", "partyChange", "quantityChange", "reset", "split", "termsChange", "transfer", "indexTransition", "stockSplit", "observation", "valuation"), Arrays.asList("execution", "transfer")))).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends PrimitiveInstruction> primitiveInstruction(BusinessEvent businessEvent) {
			return MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
