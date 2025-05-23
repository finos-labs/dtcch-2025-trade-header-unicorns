package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Instrument;
import cdm.base.staticdata.asset.common.validation.InstrumentTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.InstrumentValidator;
import cdm.base.staticdata.asset.common.validation.exists.InstrumentOnlyExistsValidator;
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
@RosettaMeta(model=Instrument.class)
public class InstrumentMeta implements RosettaMetaData<Instrument> {

	@Override
	public List<Validator<? super Instrument>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.<cdm.base.staticdata.asset.common.Instrument>create(cdm.base.staticdata.asset.common.validation.datarule.InstrumentChoice.class)
		);
	}
	
	@Override
	public List<Function<? super Instrument, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Instrument> validator() {
		return new InstrumentValidator();
	}

	@Override
	public Validator<? super Instrument> typeFormatValidator() {
		return new InstrumentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Instrument, Set<String>> onlyExistsValidator() {
		return new InstrumentOnlyExistsValidator();
	}
}
