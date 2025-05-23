package cdm.observable.asset.validation;

import cdm.observable.asset.IndexBase;
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

public class IndexBaseTypeFormatValidator implements Validator<IndexBase> {

	private List<ComparisonResult> getComparisonResults(IndexBase o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<IndexBase> validate(RosettaPath path, IndexBase o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("IndexBase", ValidationType.TYPE_FORMAT, "IndexBase", path, "", error);
		}
		return success("IndexBase", ValidationType.TYPE_FORMAT, "IndexBase", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, IndexBase o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("IndexBase", ValidationType.TYPE_FORMAT, "IndexBase", path, "", res.getError());
				}
				return success("IndexBase", ValidationType.TYPE_FORMAT, "IndexBase", path, "");
			})
			.collect(toList());
	}

}
