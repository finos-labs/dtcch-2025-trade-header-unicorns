package cdm.event.common.meta;

import cdm.event.common.ObservationEvent;
import cdm.event.common.validation.ObservationEventTypeFormatValidator;
import cdm.event.common.validation.ObservationEventValidator;
import cdm.event.common.validation.exists.ObservationEventOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=ObservationEvent.class)
public class ObservationEventMeta implements RosettaMetaData<ObservationEvent> {

	@Override
	public List<Validator<? super ObservationEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.<cdm.event.common.ObservationEvent>create(cdm.event.common.validation.datarule.ObservationEventOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super ObservationEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationEvent> validator() {
		return new ObservationEventValidator();
	}

	@Override
	public Validator<? super ObservationEvent> typeFormatValidator() {
		return new ObservationEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationEvent, Set<String>> onlyExistsValidator() {
		return new ObservationEventOnlyExistsValidator();
	}
}
