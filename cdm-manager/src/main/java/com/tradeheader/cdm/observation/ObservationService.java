package com.tradeheader.cdm.observation;

import cdm.enrichment.common.InputWrapper;
import cdm.enrichment.common.functions.Enrich_CorporateActionAffectedTrade;
import cdm.event.workflow.WorkflowStep;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.finos.cdm.CdmRuntimeModule;

public class ObservationService {

    @Inject
    protected Enrich_CorporateActionAffectedTrade _func;

    public ObservationService() {
        this(Guice.createInjector(new CdmRuntimeModule()));
    }

    public ObservationService(Injector injector) {
        injector.injectMembers(this);
    }

    public WorkflowStep runRecord (InputWrapper wrapper, WorkflowStep acceptedStep) {
        return _func.evaluate(wrapper, acceptedStep);
    }
}
