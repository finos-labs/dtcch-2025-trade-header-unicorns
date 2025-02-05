package com.tradeheader.cdm;

import cdm.enrichment.common.InputWrapper;
import cdm.event.workflow.WorkflowStep;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tradeheader.cdm.execution.BusinessEventService;
import com.tradeheader.cdm.execution.ExecutionResponse;
import com.tradeheader.cdm.observation.ObservationRequest;
import com.tradeheader.cdm.observation.ObservationService;
import com.tradeheader.cdm.utils.ResourceUtils;
import com.tradeheader.cdm.validation.ValidationResponse;
import com.tradeheader.cdm.validation.ValidationService;
import org.finos.cdm.CdmRuntimeModule;

import java.io.IOException;
import java.util.Map;

public class LambdaHandler implements RequestHandler<LambdaRequest, LambdaResponse> {

    private static final Injector injector = Guice.createInjector(new CdmRuntimeModule());

    private static final ValidationService _validator = new ValidationService(injector);
    private static final BusinessEventService _eventProcessor = new BusinessEventService(injector);
    private static final ObservationService _observationRecorder = new ObservationService(injector);

    @Override
    public LambdaResponse handleRequest(LambdaRequest request, Context context) {

        LambdaResponse.LambdaResponseBuilder builder = new LambdaResponse.LambdaResponseBuilder();

        if (request == null || request.getAction() == null)
            return builder.withError("Invalid action null").build();

        Map<String, Object> payload = request.getPayload();
        switch (request.getAction()) {
            case VALIDATION:
                try {
                    ValidationResponse response = _validator.validate(ResourceUtils.toObject(WorkflowStep.class, payload), WorkflowStep.class);
                    builder.withValidationPayload(response);
                } catch (Exception e) {
                    builder.withError(String.format("Validation failed: %s", e.getMessage()));
                }
                break;

            case EXECUTION:
                try {
                    ExecutionResponse response = _eventProcessor.runEvent(ResourceUtils.toObject(WorkflowStep.class, payload));
                    builder.withExecutionPayload(response);
                } catch (Exception e) {
                    builder.withError(String.format("Execution failed: %s", e.getMessage()));
                }
                break;

            case OBSERVATION:
                ObservationRequest observation = null;
                try {
                    observation = ResourceUtils.toObject(ObservationRequest.class, payload);
                    InputWrapper wrapper = ResourceUtils.resolveReferences(observation.getWrapper());
                    WorkflowStep acceptedStep = ResourceUtils.resolveReferences(observation.getAcceptedStep());
                    WorkflowStep response = _observationRecorder.runRecord(wrapper, acceptedStep);
                    builder.withObservationPayload(response);
                } catch (Exception e) {
                    builder.withError(String.format("Observation recording failed: %s", e.getMessage()));
                }
                break;

            default:
                builder.withError("Invalid action " + request.getAction());
        }
        return builder.build();
    }
}