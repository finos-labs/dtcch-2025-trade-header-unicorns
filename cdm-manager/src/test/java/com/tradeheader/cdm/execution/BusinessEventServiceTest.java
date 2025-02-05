package com.tradeheader.cdm.execution;

import cdm.event.workflow.WorkflowStep;
import cdm.event.workflow.functions.Create_AcceptedWorkflowStepFromInstruction;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tradeheader.cdm.LambdaRequest;
import com.tradeheader.cdm.observation.ObservationService;
import com.tradeheader.cdm.utils.ResourceUtils;
import org.finos.cdm.CdmRuntimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BusinessEventServiceTest {

    private BusinessEventService businessEventService;
    private Create_AcceptedWorkflowStepFromInstruction mockFunc;

    @BeforeEach
    void setUp() {
        // Mock the dependency
        mockFunc = Mockito.mock(Create_AcceptedWorkflowStepFromInstruction.class);

        // Create a custom Guice module to inject the mock
        Injector injector = Guice.createInjector(new CdmRuntimeModule(), new AbstractModule() {
            @Override
            protected void configure() {
                bind(Create_AcceptedWorkflowStepFromInstruction.class).toInstance(mockFunc);
            }
        });

        // Initialize BusinessEventService with the custom injector
        businessEventService = new BusinessEventService(injector);
    }

    @Test
    void runEvent_Success() {
        // Arrange: Create a mock proposed WorkflowStep
        WorkflowStep proposedStep = WorkflowStep.builder().build();

        // Mock accepted WorkflowStep response
        WorkflowStep acceptedStep = WorkflowStep.builder().build();
        when(mockFunc.evaluate(any(WorkflowStep.class))).thenReturn(acceptedStep);

        // Act: Call runEvent()
        ExecutionResponse response = businessEventService.runEvent(proposedStep);

        // Assert: Check response values
        assertNotNull(response);
        assertNotNull(response.getAcceptedStep());
        verify(mockFunc, times(1)).evaluate(proposedStep);
    }

    @Test
    void runEvent_WithJsonExpectation() throws IOException {
        // Arrange: Load input request from JSON
        LambdaRequest request = ResourceUtils.getObject(LambdaRequest.class, "com/tradeheader/cdm/requests/execution-lambdarequest-1.json");
        WorkflowStep executionRequest = ResourceUtils.toObject(WorkflowStep.class, request.getPayload());
        WorkflowStep proposedRequest = ResourceUtils.resolveReferences(executionRequest);

        // Load expected response from JSON
        WorkflowStep expectedResponse = ResourceUtils.getObject(WorkflowStep.class, "com/tradeheader/cdm/requests/execution-expected-response-1.json");

        // Act: Execute runEvent()
        BusinessEventService localService = new BusinessEventService(Guice.createInjector(new CdmRuntimeModule()));
        ExecutionResponse actualResponse = localService.runEvent(proposedRequest);

        // Convert to JSON for assertion
        Map<String, Object> actualJson = actualResponse.getAcceptedStep();
        Map<String, Object> expectedJson = ResourceUtils.asMap(expectedResponse);

        System.out.println("Actual Response: " + ResourceUtils.serialiseAsJson(actualJson));

        // Assert: Ensure actual response matches expected JSON
        assertEquals(expectedJson, actualJson, "The actual response does not match the expected response.");
    }
}
