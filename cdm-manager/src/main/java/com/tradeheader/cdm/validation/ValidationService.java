package com.tradeheader.cdm.validation;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.regnosys.rosetta.common.validation.RosettaTypeValidator;
import com.regnosys.rosetta.common.validation.ValidationReport;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.validation.ValidationResult;
import org.finos.cdm.CdmRuntimeModule;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidationService {
    
    @Inject
    RosettaTypeValidator processor;
    
    public ValidationService() {
        this(Guice.createInjector(new CdmRuntimeModule()));
    }

    public ValidationService(Injector injector) {
        injector.injectMembers(this);
    }
    
    public <T extends RosettaModelObject> ValidationResponse validate (T payload, Class<T> clazz) {
        ValidationReport report = processor.runProcessStep(clazz, payload);
        List<String> errors = report.getValidationResults().stream().filter(Predicate.not(ValidationResult::isSuccess)).map(ValidationResult::toString).collect(Collectors.toList());
        return new ValidationResponse.ValidationResponseBuilder().withSuccess(errors.isEmpty()).withValidationErrors(errors).build();
    }
    
    
}
