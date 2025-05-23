package cdm.margin.schedule.validation;

import cdm.margin.schedule.StandardizedSchedule;
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

public class StandardizedScheduleTypeFormatValidator implements Validator<StandardizedSchedule> {

	private List<ComparisonResult> getComparisonResults(StandardizedSchedule o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<StandardizedSchedule> validate(RosettaPath path, StandardizedSchedule o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("StandardizedSchedule", ValidationType.TYPE_FORMAT, "StandardizedSchedule", path, "", error);
		}
		return success("StandardizedSchedule", ValidationType.TYPE_FORMAT, "StandardizedSchedule", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, StandardizedSchedule o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("StandardizedSchedule", ValidationType.TYPE_FORMAT, "StandardizedSchedule", path, "", res.getError());
				}
				return success("StandardizedSchedule", ValidationType.TYPE_FORMAT, "StandardizedSchedule", path, "");
			})
			.collect(toList());
	}

}
