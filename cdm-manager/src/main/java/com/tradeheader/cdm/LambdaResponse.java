package com.tradeheader.cdm;

import cdm.event.workflow.WorkflowStep;
import com.tradeheader.cdm.execution.ExecutionResponse;
import com.tradeheader.cdm.utils.ResourceUtils;
import com.tradeheader.cdm.validation.ValidationResponse;

import java.util.Map;

public class LambdaResponse {

    private final LambdaAction action;
    private final boolean success;
    private final Map<String, Object> payload;
    private final String error;

    public static LambdaResponse error (String message) {
        return new LambdaResponseBuilder().withError(message).build();
    }

    public static LambdaResponse ok (ValidationResponse payload) {
        return new LambdaResponseBuilder().withValidationPayload(payload).build();
    }

    private LambdaResponse(LambdaAction action, boolean success, Map<String, Object> payload, String error) {
        this.action = action;
        this.success = success;
        this.payload = payload;
        this.error = error;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public String getError() {
        return error;
    }

    public LambdaAction getAction() {
        return action;
    }

    public static class LambdaResponseBuilder {
        private LambdaAction action;
        private boolean success;
        private Map<String, Object> payload;
        private String error;

        public LambdaResponseBuilder withValidationPayload(ValidationResponse payload) {
            this.success = true;
            this.action = LambdaAction.VALIDATION;
            this.payload = ResourceUtils.asMap(payload);
            return this;
        }

        public LambdaResponseBuilder withExecutionPayload(ExecutionResponse payload) {
            this.success = true;
            this.action = LambdaAction.EXECUTION;
            this.payload = ResourceUtils.asMap(payload);
            return this;
        }

        public LambdaResponseBuilder withObservationPayload(WorkflowStep payload) {
            this.success = true;
            this.action = LambdaAction.OBSERVATION;
            this.payload = ResourceUtils.asMap(payload);
            return this;
        }

        public LambdaResponseBuilder withError(String error) {
            this.success = false;
            this.error = error;
            return this;
        }

        public LambdaResponse build() {
            return new LambdaResponse(action, success, payload, error);
        }
    }
}