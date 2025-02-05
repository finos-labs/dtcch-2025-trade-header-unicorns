package cdm.enrichment.common.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.enrichment.common.InputWrapper;
import cdm.event.common.CorporateAction;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStep.WorkflowStepBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Enrich_CorporateActionAffectedTrade.Enrich_CorporateActionAffectedTradeDefault.class)
public abstract class Enrich_CorporateActionAffectedTrade implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param inputData 
	* @param openWorkflowStep 
	* @return updatedWorkflowStep 
	*/
	public WorkflowStep evaluate(InputWrapper inputData, WorkflowStep openWorkflowStep) {
		WorkflowStep.WorkflowStepBuilder updatedWorkflowStepBuilder = doEvaluate(inputData, openWorkflowStep);
		
		final WorkflowStep updatedWorkflowStep;
		if (updatedWorkflowStepBuilder == null) {
			updatedWorkflowStep = null;
		} else {
			updatedWorkflowStep = updatedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, updatedWorkflowStep);
		}
		
		return updatedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(InputWrapper inputData, WorkflowStep openWorkflowStep);

	public static class Enrich_CorporateActionAffectedTradeDefault extends Enrich_CorporateActionAffectedTrade {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(InputWrapper inputData, WorkflowStep openWorkflowStep) {
			WorkflowStep.WorkflowStepBuilder updatedWorkflowStep = WorkflowStep.builder();
			return assignOutput(updatedWorkflowStep, inputData, openWorkflowStep);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder updatedWorkflowStep, InputWrapper inputData, WorkflowStep openWorkflowStep) {
			updatedWorkflowStep = toBuilder(openWorkflowStep);
			
			updatedWorkflowStep
				.getOrCreateProposedEvent()
				.getOrCreateInstruction(0)
				.getOrCreatePrimitiveInstruction()
				.getOrCreateObservation()
				.getOrCreateObservationEvent()
				.setCorporateAction(MapperS.of(inputData).<CorporateAction>map("getCorporateAction", inputWrapper -> inputWrapper.getCorporateAction()).get());
			
			updatedWorkflowStep
				.setEventIdentifier(MapperS.of(inputData).<Identifier>mapC("getEventIdentifier", inputWrapper -> inputWrapper.getEventIdentifier()).getMulti());
			
			return Optional.ofNullable(updatedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
