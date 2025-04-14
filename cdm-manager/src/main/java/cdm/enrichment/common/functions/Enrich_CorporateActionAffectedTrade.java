package cdm.enrichment.common.functions;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Party;
import cdm.enrichment.common.InputWrapper;
import cdm.event.common.ActionEnum;
import cdm.event.common.BusinessEvent;
import cdm.event.common.CorporateAction;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationInstruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.TradeState;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.WorkflowStep.WorkflowStepBuilder;
import cdm.event.workflow.WorkflowStepApproval;
import cdm.event.workflow.functions.Create_ProposedWorkflowStep;
import cdm.event.workflow.metafields.ReferenceWithMetaWorkflowStep;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Enrich_CorporateActionAffectedTrade.Enrich_CorporateActionAffectedTradeDefault.class)
public abstract class Enrich_CorporateActionAffectedTrade implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_ProposedWorkflowStep create_ProposedWorkflowStep;

	/**
	* @param inputData 
	* @param openAcceptedWorkflowStep 
	* @return proposedWorkflowStep 
	*/
	public WorkflowStep evaluate(InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep) {
		WorkflowStep.WorkflowStepBuilder proposedWorkflowStepBuilder = doEvaluate(inputData, openAcceptedWorkflowStep);
		
		final WorkflowStep proposedWorkflowStep;
		if (proposedWorkflowStepBuilder == null) {
			proposedWorkflowStep = null;
		} else {
			proposedWorkflowStep = proposedWorkflowStepBuilder.build();
			objectValidator.validate(WorkflowStep.class, proposedWorkflowStep);
		}
		
		return proposedWorkflowStep;
	}

	protected abstract WorkflowStep.WorkflowStepBuilder doEvaluate(InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep);

	protected abstract MapperS<? extends EventInstruction> eventInstructionCorp(InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep);

	public static class Enrich_CorporateActionAffectedTradeDefault extends Enrich_CorporateActionAffectedTrade {
		@Override
		protected WorkflowStep.WorkflowStepBuilder doEvaluate(InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep) {
			WorkflowStep.WorkflowStepBuilder proposedWorkflowStep = WorkflowStep.builder();
			return assignOutput(proposedWorkflowStep, inputData, openAcceptedWorkflowStep);
		}
		
		protected WorkflowStep.WorkflowStepBuilder assignOutput(WorkflowStep.WorkflowStepBuilder proposedWorkflowStep, InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep) {
			final ReferenceWithMetaWorkflowStep referenceWithMetaWorkflowStep = MapperS.of(openAcceptedWorkflowStep).<ReferenceWithMetaWorkflowStep>map("getPreviousWorkflowStep", workflowStep -> workflowStep.getPreviousWorkflowStep()).get();
			proposedWorkflowStep = toBuilder(create_ProposedWorkflowStep.evaluate(null, MapperS.of(openAcceptedWorkflowStep).<EventTimestamp>mapC("getTimestamp", workflowStep -> workflowStep.getTimestamp()).getMulti(), MapperS.of(inputData).<Identifier>mapC("getEventIdentifier", inputWrapper -> inputWrapper.getEventIdentifier()).getMulti(), MapperS.of(openAcceptedWorkflowStep).<Party>mapC("getParty", workflowStep -> workflowStep.getParty()).getMulti(), MapperS.of(openAcceptedWorkflowStep).<Account>mapC("getAccount", workflowStep -> workflowStep.getAccount()).getMulti(), (referenceWithMetaWorkflowStep == null ? null : referenceWithMetaWorkflowStep.getValue()), ActionEnum.NEW, eventInstructionCorp(inputData, openAcceptedWorkflowStep).get(), Collections.<WorkflowStepApproval>emptyList()));
			
			return Optional.ofNullable(proposedWorkflowStep)
				.map(o -> o.prune())
				.orElse(null);
		}
		
		@Override
		protected MapperS<? extends EventInstruction> eventInstructionCorp(InputWrapper inputData, WorkflowStep openAcceptedWorkflowStep) {
			final Instruction instruction = Instruction.builder()
				.setPrimitiveInstruction(PrimitiveInstruction.builder()
					.setObservation(ObservationInstruction.builder()
						.setObservationEvent(ObservationEvent.builder()
							.setCorporateAction(MapperS.of(inputData).<CorporateAction>map("getCorporateAction", inputWrapper -> inputWrapper.getCorporateAction()).get())
							.build())
						.build())
					.build())
				.setBeforeValue(MapperS.of(openAcceptedWorkflowStep).<BusinessEvent>map("getBusinessEvent", workflowStep -> workflowStep.getBusinessEvent()).<TradeState>mapC("getAfter", businessEvent -> businessEvent.getAfter()).get())
				.build();
			return MapperS.of(EventInstruction.builder()
				.setIntent(EventIntentEnum.CORPORATE_ACTION_ADJUSTMENT)
				.setCorporateActionIntent(MapperS.of(inputData).<CorporateAction>map("getCorporateAction", inputWrapper -> inputWrapper.getCorporateAction()).<CorporateActionTypeEnum>map("getCorporateActionType", corporateAction -> corporateAction.getCorporateActionType()).get())
				.setInstruction((instruction == null ? Collections.<Instruction>emptyList() : Collections.singletonList(instruction)))
				.build());
		}
	}
}
