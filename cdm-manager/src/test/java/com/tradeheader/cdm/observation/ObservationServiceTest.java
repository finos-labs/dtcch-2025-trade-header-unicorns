package com.tradeheader.cdm.observation;

import cdm.enrichment.common.InputWrapper;
import cdm.enrichment.common.functions.Enrich_CorporateActionAffectedTrade;
import cdm.event.workflow.WorkflowStep;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tradeheader.cdm.LambdaRequest;
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

class ObservationServiceTest {

    private ObservationService mockObservationService;
    private Enrich_CorporateActionAffectedTrade mockFunc;

    @BeforeEach
    void setUp() {
        // Mock the dependency
        mockFunc = Mockito.mock(Enrich_CorporateActionAffectedTrade.class);

        // Create a custom Guice module to inject the mock
        Injector injector = Guice.createInjector(new CdmRuntimeModule(), new AbstractModule() {
            @Override
            protected void configure() {
                bind(Enrich_CorporateActionAffectedTrade.class).toInstance(mockFunc);
            }
        });

        // Initialize ObservationService with the custom injector
        mockObservationService = new ObservationService(injector);
    }

    @Test
    void runRecord_Success() {
        // Arrange: Create a mock InputWrapper and WorkflowStep
        InputWrapper wrapper = InputWrapper.builder().build(); // Assume a valid constructor
        WorkflowStep acceptedStep = WorkflowStep.builder().build();

        // Mock enriched WorkflowStep response
        when(mockFunc.evaluate(any(InputWrapper.class), any(WorkflowStep.class))).thenReturn(acceptedStep);

        // Act: Call runRecord()
        WorkflowStep response = mockObservationService.runRecord(wrapper, acceptedStep);

        // Assert: Check response values
        assertNotNull(response);
        verify(mockFunc, times(1)).evaluate(wrapper, acceptedStep);
    }

    @Test
    void runRecord_WithJsonExpectation() throws IOException {
        // Arrange: Load input request from JSON
        LambdaRequest request = ResourceUtils.getObject(LambdaRequest.class, "com/tradeheader/cdm/requests/observation-lambdarequest-1.json");

        InputWrapper wrapper = ResourceUtils.toObject(InputWrapper.class, (Map<String, Object>) request.getPayload().get("wrapper"));
        WorkflowStep stepRequest = ResourceUtils.toObject(WorkflowStep.class, (Map<String, Object>) request.getPayload().get("acceptedStep"));
        WorkflowStep acceptedStep = ResourceUtils.resolveReferences(stepRequest);

        // Load expected response from JSON
        WorkflowStep expectedResponse = ResourceUtils.getObject(WorkflowStep.class, "com/tradeheader/cdm/requests/observation-expected-response-1.json");

        // Act: Execute runRecord()
        ObservationService localService = new ObservationService(Guice.createInjector(new CdmRuntimeModule()));
        WorkflowStep actualResponse = localService.runRecord(wrapper, acceptedStep);

        // Convert to JSON for assertion
        Map<String, Object> actualJson = ResourceUtils.asMap(actualResponse);
        Map<String, Object> expectedJson = ResourceUtils.asMap(expectedResponse);

        //System.out.println("Actual Response: " + ResourceUtils.serialiseAsJson(actualJson));

        // Assert: Ensure actual response matches expected JSON
        assertEquals(expectedJson, actualJson, "The actual response does not match the expected response.");
    }
}