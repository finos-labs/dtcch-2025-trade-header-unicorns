package com.tradeheader.cdm.execution;

import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.functions.Create_AcceptedWorkflowStepFromInstruction;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.finos.cdm.CdmRuntimeModule;

public class BusinessEventService {

    @Inject
    protected Create_AcceptedWorkflowStepFromInstruction _func;

    public BusinessEventService() {
        this(Guice.createInjector(new CdmRuntimeModule()));
    }

    public BusinessEventService(Injector injector) {
        injector.injectMembers(this);
    }

    public ExecutionResponse runEvent (WorkflowStep proposedEvent) {
        WorkflowStep accepted = _func.evaluate(proposedEvent);
        return new ExecutionResponse.ExecutionResponseBuilder().withAcceptedStep(accepted).build();
    }
}
