package cdm.observable.asset.meta;

import cdm.observable.asset.Index;
import cdm.observable.asset.validation.IndexTypeFormatValidator;
import cdm.observable.asset.validation.IndexValidator;
import cdm.observable.asset.validation.exists.IndexOnlyExistsValidator;
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
@RosettaMeta(model=Index.class)
public class IndexMeta implements RosettaMetaData<Index> {

	@Override
	public List<Validator<? super Index>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.<cdm.observable.asset.Index>create(cdm.observable.asset.validation.datarule.IndexChoice.class)
		);
	}
	
	@Override
	public List<Function<? super Index, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Index> validator() {
		return new IndexValidator();
	}

	@Override
	public Validator<? super Index> typeFormatValidator() {
		return new IndexTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Index, Set<String>> onlyExistsValidator() {
		return new IndexOnlyExistsValidator();
	}
}
