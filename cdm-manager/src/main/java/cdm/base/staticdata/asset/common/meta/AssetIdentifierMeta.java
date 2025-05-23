package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.AssetIdentifier;
import cdm.base.staticdata.asset.common.validation.AssetIdentifierTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.AssetIdentifierValidator;
import cdm.base.staticdata.asset.common.validation.exists.AssetIdentifierOnlyExistsValidator;
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
@RosettaMeta(model=AssetIdentifier.class)
public class AssetIdentifierMeta implements RosettaMetaData<AssetIdentifier> {

	@Override
	public List<Validator<? super AssetIdentifier>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetIdentifier, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetIdentifier> validator() {
		return new AssetIdentifierValidator();
	}

	@Override
	public Validator<? super AssetIdentifier> typeFormatValidator() {
		return new AssetIdentifierTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetIdentifier, Set<String>> onlyExistsValidator() {
		return new AssetIdentifierOnlyExistsValidator();
	}
}
