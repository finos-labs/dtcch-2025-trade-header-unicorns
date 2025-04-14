package com.tradeheader.cdm;

import cdm.enrichment.common.InputWrapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tradeheader.cdm.execution.BusinessEventService;
import com.tradeheader.cdm.execution.ExecutionResponse;
import com.tradeheader.cdm.observation.ObservationRequest;
import com.tradeheader.cdm.observation.ObservationService;
import com.tradeheader.cdm.utils.ResourceUtils;
import cdm.event.workflow.WorkflowStep;
import com.tradeheader.cdm.validation.ValidationResponse;
import com.tradeheader.cdm.validation.ValidationService;
import org.finos.cdm.CdmRuntimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LambdaHandlerTest {

    private LambdaHandler lambdaHandler;
    private ValidationService mockValidator;
    private BusinessEventService mockEventProcessor;
    private ObservationService mockObservationRecorder;
    private Context mockContext;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        mockValidator = Mockito.mock(ValidationService.class);
        mockEventProcessor = Mockito.mock(BusinessEventService.class);
        mockObservationRecorder = Mockito.mock(ObservationService.class);
        mockContext = Mockito.mock(Context.class);

        // Create custom Guice module to inject the mocks
        Injector injector = Guice.createInjector(new CdmRuntimeModule(), new AbstractModule() {
            @Override
            protected void configure() {
                bind(ValidationService.class).toInstance(mockValidator);
                bind(BusinessEventService.class).toInstance(mockEventProcessor);
                bind(ObservationService.class).toInstance(mockObservationRecorder);
            }
        });

        injector.injectMembers(this);

        // Initialize LambdaHandler with mocked dependencies
        lambdaHandler = new LambdaHandler();
    }

    //@Test
    void handleRequest_Validation_Success() {
        // Arrange
        WorkflowStep mockWorkflowStep = WorkflowStep.builder().build();
        ValidationResponse mockValidationResponse = new ValidationResponse.ValidationResponseBuilder()
                .withSuccess(true)
                .build();

        Map<String, Object> payload = ResourceUtils.asMap(mockWorkflowStep);
        LambdaRequest request = new LambdaRequest(LambdaAction.VALIDATION, payload);

        when(mockValidator.validate(any(WorkflowStep.class), eq(WorkflowStep.class)))
                .thenReturn(mockValidationResponse);

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertTrue(response.isSuccess());
        assertNotNull(response.getPayload());
        verify(mockValidator, times(1)).validate(any(WorkflowStep.class), eq(WorkflowStep.class));
    }

    //@Test
    void handleRequest_Execution_Success() {
        // Arrange
        WorkflowStep mockWorkflowStep = WorkflowStep.builder().build();
        ExecutionResponse mockExecutionResponse = new ExecutionResponse.ExecutionResponseBuilder()
                .withAcceptedStep(mockWorkflowStep)
                .build();

        Map<String, Object> payload = ResourceUtils.asMap(mockWorkflowStep);
        LambdaRequest request = new LambdaRequest(LambdaAction.EXECUTION, payload);

        when(mockEventProcessor.runEvent(any(WorkflowStep.class)))
                .thenReturn(mockExecutionResponse);

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertTrue(response.isSuccess());
        assertNotNull(response.getPayload());
        verify(mockEventProcessor, times(1)).runEvent(any(WorkflowStep.class));
    }

    //@Test
    void handleRequest_Observation_Success() {
        // Arrange
        InputWrapper mockWrapper = InputWrapper.builder().build(); // Assume this exists
        WorkflowStep mockAcceptedStep = WorkflowStep.builder().build();
        WorkflowStep mockResponseStep = WorkflowStep.builder().build();

        ObservationRequest observationRequest = new ObservationRequest(mockWrapper, mockAcceptedStep);
        Map<String, Object> payload = ResourceUtils.asMap(observationRequest);

        LambdaRequest request = new LambdaRequest(LambdaAction.OBSERVATION, payload);

        when(mockObservationRecorder.runRecord(any(InputWrapper.class), any(WorkflowStep.class)))
                .thenReturn(mockResponseStep);

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertTrue(response.isSuccess());
        assertNotNull(response.getPayload());
        verify(mockObservationRecorder, times(1)).runRecord(any(InputWrapper.class), any(WorkflowStep.class));
    }

    @Test
    void handleRequest_InvalidAction() {
        // Arrange
        Map<String, Object> payload = new HashMap<>();
        LambdaRequest request = new LambdaRequest(null, payload);

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertFalse(response.isSuccess());
        assertEquals("Invalid action null", response.getError());
    }

    //@Test
    void handleRequest_Validation_Failure() {
        // Arrange
        WorkflowStep mockWorkflowStep = WorkflowStep.builder().build();
        Map<String, Object> payload = ResourceUtils.asMap(mockWorkflowStep);
        LambdaRequest request = new LambdaRequest(LambdaAction.VALIDATION, payload);

        when(mockValidator.validate(any(WorkflowStep.class), eq(WorkflowStep.class)))
                .thenThrow(new RuntimeException("Validation failed"));

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertFalse(response.isSuccess());
        assertTrue(response.getError().contains("Validation failed"));
        verify(mockValidator, times(1)).validate(any(WorkflowStep.class), eq(WorkflowStep.class));
    }

    //@Test
    void handleRequest_Execution_Failure() {
        // Arrange
        WorkflowStep mockWorkflowStep = WorkflowStep.builder().build();
        Map<String, Object> payload = ResourceUtils.asMap(mockWorkflowStep);
        LambdaRequest request = new LambdaRequest(LambdaAction.EXECUTION, payload);

        when(mockEventProcessor.runEvent(any(WorkflowStep.class)))
                .thenThrow(new RuntimeException("Execution failed"));

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertFalse(response.isSuccess());
        assertTrue(response.getError().contains("Execution failed"));
        verify(mockEventProcessor, times(1)).runEvent(any(WorkflowStep.class));
    }

    @Test
    void handleRequest_Observation_Failure() {
        // Arrange
        Map<String, Object> payload = new HashMap<>();
        LambdaRequest request = new LambdaRequest(LambdaAction.OBSERVATION, payload);

        when(mockObservationRecorder.runRecord(any(InputWrapper.class), any(WorkflowStep.class)))
                .thenThrow(new RuntimeException("Observation recording failed"));

        // Act
        LambdaResponse response = lambdaHandler.handleRequest(request, mockContext);

        // Assert
        assertFalse(response.isSuccess());
        assertTrue(response.getError().contains("Observation recording failed"));
        verify(mockObservationRecorder, never()).runRecord(any(InputWrapper.class), any(WorkflowStep.class));
    }
}
