package com.tradeheader.cdm.execution;

import cdm.event.workflow.WorkflowStep;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradeheader.cdm.utils.ResourceUtils;

import java.util.Map;

@JsonDeserialize(builder = ExecutionResponse.ExecutionResponseBuilder.class)
public class ExecutionResponse {

    private final Map<String, Object> acceptedStep;

    private ExecutionResponse (Map<String, Object> acceptedStep) {
        this.acceptedStep = acceptedStep;
    }

    public Map<String, Object> getAcceptedStep() {
        return acceptedStep;
    }

    public static class ExecutionResponseBuilder {
        private WorkflowStep acceptedStep;

        public ExecutionResponseBuilder withAcceptedStep(WorkflowStep acceptedStep) {
            this.acceptedStep = acceptedStep;
            return this;
        }

        public ExecutionResponse build() {
            return new ExecutionResponse(ResourceUtils.asMap(acceptedStep));
        }
    }
}
