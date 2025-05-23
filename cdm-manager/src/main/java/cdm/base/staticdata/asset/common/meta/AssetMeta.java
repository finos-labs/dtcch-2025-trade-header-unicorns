package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Asset;
import cdm.base.staticdata.asset.common.validation.AssetTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.AssetValidator;
import cdm.base.staticdata.asset.common.validation.exists.AssetOnlyExistsValidator;
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
@RosettaMeta(model=Asset.class)
public class AssetMeta implements RosettaMetaData<Asset> {

	@Override
	public List<Validator<? super Asset>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.<cdm.base.staticdata.asset.common.Asset>create(cdm.base.staticdata.asset.common.validation.datarule.AssetChoice.class)
		);
	}
	
	@Override
	public List<Function<? super Asset, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Asset> validator() {
		return new AssetValidator();
	}

	@Override
	public Validator<? super Asset> typeFormatValidator() {
		return new AssetTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Asset, Set<String>> onlyExistsValidator() {
		return new AssetOnlyExistsValidator();
	}
}
