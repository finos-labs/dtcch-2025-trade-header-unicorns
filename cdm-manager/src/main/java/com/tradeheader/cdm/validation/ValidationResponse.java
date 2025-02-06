package com.tradeheader.cdm.validation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize(builder = ValidationResponse.ValidationResponseBuilder.class)
public class ValidationResponse {
    private final boolean success;
    private final List<String> validationErrors;

    private ValidationResponse(boolean success, List<String> validationErrors) {
        this.success = success;
        this.validationErrors = validationErrors;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public static class ValidationResponseBuilder {
        private boolean success;
        private List<String> validationErrors;

        public ValidationResponseBuilder() {
            validationErrors = new ArrayList<>();
        }

        // Setter for success flag
        public ValidationResponseBuilder withSuccess(boolean isSuccess) {
            this.success = isSuccess;
            return this;
        }

        // Method to add a single validation error
        public ValidationResponseBuilder addValidationError(String validationError) {
            this.validationErrors.add(validationError);
            return this;
        }

        // Method to set all validation errors at once
        public ValidationResponseBuilder withValidationErrors(List<String> validationErrors) {
            this.validationErrors = validationErrors;
            return this;
        }

        // Build method to construct the ServiceResponse
        public ValidationResponse build() {
            return new ValidationResponse(success, validationErrors);
        }
    }
}

