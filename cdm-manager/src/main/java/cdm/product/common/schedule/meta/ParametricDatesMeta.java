package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ParametricDates;
import cdm.product.common.schedule.validation.ParametricDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.ParametricDatesValidator;
import cdm.product.common.schedule.validation.exists.ParametricDatesOnlyExistsValidator;
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
@RosettaMeta(model=ParametricDates.class)
public class ParametricDatesMeta implements RosettaMetaData<ParametricDates> {

	@Override
	public List<Validator<? super ParametricDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.<cdm.product.common.schedule.ParametricDates>create(cdm.product.common.schedule.validation.datarule.ParametricDatesParametricDatesChoice.class),
			factory.<cdm.product.common.schedule.ParametricDates>create(cdm.product.common.schedule.validation.datarule.ParametricDatesDayOfWeekMethod.class)
		);
	}
	
	@Override
	public List<Function<? super ParametricDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ParametricDates> validator() {
		return new ParametricDatesValidator();
	}

	@Override
	public Validator<? super ParametricDates> typeFormatValidator() {
		return new ParametricDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ParametricDates, Set<String>> onlyExistsValidator() {
		return new ParametricDatesOnlyExistsValidator();
	}
}
