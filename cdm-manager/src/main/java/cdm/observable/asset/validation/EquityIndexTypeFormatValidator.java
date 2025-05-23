package cdm.observable.asset.validation;

import cdm.observable.asset.EquityIndex;
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

public class EquityIndexTypeFormatValidator implements Validator<EquityIndex> {

	private List<ComparisonResult> getComparisonResults(EquityIndex o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<EquityIndex> validate(RosettaPath path, EquityIndex o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("EquityIndex", ValidationType.TYPE_FORMAT, "EquityIndex", path, "", error);
		}
		return success("EquityIndex", ValidationType.TYPE_FORMAT, "EquityIndex", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, EquityIndex o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("EquityIndex", ValidationType.TYPE_FORMAT, "EquityIndex", path, "", res.getError());
				}
				return success("EquityIndex", ValidationType.TYPE_FORMAT, "EquityIndex", path, "");
			})
			.collect(toList());
	}

}
