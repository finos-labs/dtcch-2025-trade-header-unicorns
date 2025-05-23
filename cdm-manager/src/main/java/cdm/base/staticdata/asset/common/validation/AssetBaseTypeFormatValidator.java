package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.AssetBase;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AssetBaseTypeFormatValidator implements Validator<AssetBase> {

	private List<ComparisonResult> getComparisonResults(AssetBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<AssetBase> validate(RosettaPath path, AssetBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AssetBase", ValidationType.TYPE_FORMAT, "AssetBase", path, "", error);
		}
		return success("AssetBase", ValidationType.TYPE_FORMAT, "AssetBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AssetBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AssetBase", ValidationType.TYPE_FORMAT, "AssetBase", path, "", res.getError());
				}
				return success("AssetBase", ValidationType.TYPE_FORMAT, "AssetBase", path, "");
			})
			.collect(toList());
	}

}
