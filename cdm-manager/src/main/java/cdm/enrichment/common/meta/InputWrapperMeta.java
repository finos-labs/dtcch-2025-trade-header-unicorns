package cdm.enrichment.common.meta;

import cdm.enrichment.common.InputWrapper;
import cdm.enrichment.common.validation.InputWrapperTypeFormatValidator;
import cdm.enrichment.common.validation.InputWrapperValidator;
import cdm.enrichment.common.validation.exists.InputWrapperOnlyExistsValidator;
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
@RosettaMeta(model=InputWrapper.class)
public class InputWrapperMeta implements RosettaMetaData<InputWrapper> {

	@Override
	public List<Validator<? super InputWrapper>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InputWrapper, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InputWrapper> validator() {
		return new InputWrapperValidator();
	}

	@Override
	public Validator<? super InputWrapper> typeFormatValidator() {
		return new InputWrapperTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InputWrapper, Set<String>> onlyExistsValidator() {
		return new InputWrapperOnlyExistsValidator();
	}
}
