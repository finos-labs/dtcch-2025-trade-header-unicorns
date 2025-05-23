package cdm.product.common.schedule.validation;

import cdm.product.common.schedule.AveragingObservationList;
import cdm.product.common.schedule.WeightedAveragingObservation;
import com.google.common.collect.Lists;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.rosetta.model.lib.expression.ExpressionOperators.checkCardinality;
import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class AveragingObservationListValidator implements Validator<AveragingObservationList> {

	private List<ComparisonResult> getComparisonResults(AveragingObservationList o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("averagingObservation", (List<? extends WeightedAveragingObservation>) o.getAveragingObservation() == null ? 0 : o.getAveragingObservation().size(), 1, 0)
			);
	}

	@Override
	public ValidationResult<AveragingObservationList> validate(RosettaPath path, AveragingObservationList o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AveragingObservationList", ValidationType.CARDINALITY, "AveragingObservationList", path, "", error);
		}
		return success("AveragingObservationList", ValidationType.CARDINALITY, "AveragingObservationList", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AveragingObservationList o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AveragingObservationList", ValidationType.CARDINALITY, "AveragingObservationList", path, "", res.getError());
				}
				return success("AveragingObservationList", ValidationType.CARDINALITY, "AveragingObservationList", path, "");
			})
			.collect(toList());
	}

}
