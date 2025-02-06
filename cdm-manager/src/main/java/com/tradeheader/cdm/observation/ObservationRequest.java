package com.tradeheader.cdm.observation;

import cdm.enrichment.common.InputWrapper;
import cdm.event.workflow.WorkflowStep;

public class ObservationRequest {

    private InputWrapper wrapper;
    private WorkflowStep acceptedStep;

    public ObservationRequest(){}

    public ObservationRequest(InputWrapper wrapper, WorkflowStep acceptedStep) {
        this.wrapper = wrapper;
        this.acceptedStep = acceptedStep;
    }

    public InputWrapper getWrapper() {
        return wrapper;
    }

    public WorkflowStep getAcceptedStep() {
        return acceptedStep;
    }

    public void setWrapper(InputWrapper wrapper) {
        this.wrapper = wrapper;
    }

    public void setAcceptedStep(WorkflowStep acceptedStep) {
        this.acceptedStep = acceptedStep;
    }
}
