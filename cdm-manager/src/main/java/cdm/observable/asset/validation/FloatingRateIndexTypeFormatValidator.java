package cdm.observable.asset.validation;

import cdm.observable.asset.FloatingRateIndex;
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

public class FloatingRateIndexTypeFormatValidator implements Validator<FloatingRateIndex> {

	private List<ComparisonResult> getComparisonResults(FloatingRateIndex o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<FloatingRateIndex> validate(RosettaPath path, FloatingRateIndex o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("FloatingRateIndex", ValidationType.TYPE_FORMAT, "FloatingRateIndex", path, "", error);
		}
		return success("FloatingRateIndex", ValidationType.TYPE_FORMAT, "FloatingRateIndex", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, FloatingRateIndex o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("FloatingRateIndex", ValidationType.TYPE_FORMAT, "FloatingRateIndex", path, "", res.getError());
				}
				return success("FloatingRateIndex", ValidationType.TYPE_FORMAT, "FloatingRateIndex", path, "");
			})
			.collect(toList());
	}

}
