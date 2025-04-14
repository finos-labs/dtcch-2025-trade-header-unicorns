package com.tradeheader.cdm.validation;

import cdm.event.workflow.WorkflowStep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.validation.RosettaTypeValidator;
import com.regnosys.rosetta.common.validation.ValidationReport;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.tradeheader.cdm.LambdaRequest;
import com.tradeheader.cdm.utils.ResourceUtils;
import org.finos.cdm.CdmRuntimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    private ValidationService mockValidationService;
    private RosettaTypeValidator mockProcessor;

    @BeforeEach
    void setUp() {
        // Mock RosettaTypeValidator
        mockProcessor = Mockito.mock(RosettaTypeValidator.class);

        // Create custom Guice module to inject the mock
        Injector injector = Guice.createInjector(new CdmRuntimeModule(), new AbstractModule() {
            @Override
            protected void configure() {
                bind(RosettaTypeValidator.class).toInstance(mockProcessor);
            }
        });

        // Initialize ValidationService with the custom injector
        mockValidationService = new ValidationService(injector);
    }

    @Test
    void validate_SuccessfulValidation() {
        // Arrange
        WorkflowStep mockPayload = WorkflowStep.builder().build();
        ValidationReport mockReport = new ValidationReport (mockPayload, Collections.emptyList()); // No errors

        when(mockProcessor.runProcessStep(any(), any())).thenReturn(mockReport);

        // Act
        ValidationResponse response = mockValidationService.validate(mockPayload, WorkflowStep.class);

        // Assert
        assertTrue(response.isSuccess());
        assertTrue(response.getValidationErrors().isEmpty());
        verify(mockProcessor, times(1)).runProcessStep(WorkflowStep.class, mockPayload);
    }

    @Test
    void validate_FailedValidation() throws JsonProcessingException {
        // Arrange
        WorkflowStep mockPayload = WorkflowStep.builder().build();

        ValidationResult<?> error = new ValidationResult.ModelValidationResult( "error", ValidationResult.ValidationType.CARDINALITY, "field", RosettaPath.valueOf("workflowStep"), "", Optional.of("Error message"));
        ValidationReport mockReport = new ValidationReport(mockPayload, new ArrayList<>(List.of(error)));

        when(mockProcessor.runProcessStep(eq(WorkflowStep.class), any())).thenReturn(mockReport);


        // Act
        ValidationResponse response = mockValidationService.validate(mockPayload, WorkflowStep.class);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals(1, response.getValidationErrors().size());
        List<String> errors = response.getValidationErrors();
        assertEquals("Validation FAILURE on [workflowStep] for [CARDINALITY] [error] because [Error message]", errors.get(0));
        verify(mockProcessor, times(1)).runProcessStep(WorkflowStep.class, mockPayload);
    }

    @Test
    void validate_WithJsonExpectation() throws IOException {
        // Arrange: Load request from JSON
        LambdaRequest request = ResourceUtils.getObject(LambdaRequest.class, "com/tradeheader/cdm/requests/validate-lambdarequest-1.json");
        WorkflowStep validationRequest = ResourceUtils.toObject(WorkflowStep.class, request.getPayload());

        // Act: Run validation
        ValidationService localValidationService = new ValidationService(Guice.createInjector(new CdmRuntimeModule()));
        ValidationResponse actualResponse = localValidationService.validate(validationRequest, WorkflowStep.class);

        // Assert: Compare with expected response
        ValidationResponse expectedResponse = ResourceUtils.getObject(ValidationResponse.class, "com/tradeheader/cdm/requests/validate-expected-response-1.json");

        Map<String, Object> actualJson = ResourceUtils.asMap(actualResponse);
        Map<String, Object> expectedJson = ResourceUtils.asMap(expectedResponse);

        //System.out.println(ResourceUtils.serialiseAsJson(actualJson));

        assertEquals(expectedJson, actualJson, "The actual response does not match the expected response.");
    }
}
