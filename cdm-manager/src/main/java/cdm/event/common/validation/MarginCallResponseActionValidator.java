package cdm.event.common.validation;

import cdm.event.common.CollateralPosition;
import cdm.event.common.MarginCallActionEnum;
import cdm.event.common.MarginCallResponseAction;
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

public class MarginCallResponseActionValidator implements Validator<MarginCallResponseAction> {

	private List<ComparisonResult> getComparisonResults(MarginCallResponseAction o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("collateralPositionComponent", (List<? extends CollateralPosition>) o.getCollateralPositionComponent() == null ? 0 : o.getCollateralPositionComponent().size(), 1, 0), 
				checkCardinality("marginCallAction", (MarginCallActionEnum) o.getMarginCallAction() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<MarginCallResponseAction> validate(RosettaPath path, MarginCallResponseAction o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MarginCallResponseAction", ValidationType.CARDINALITY, "MarginCallResponseAction", path, "", error);
		}
		return success("MarginCallResponseAction", ValidationType.CARDINALITY, "MarginCallResponseAction", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MarginCallResponseAction o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MarginCallResponseAction", ValidationType.CARDINALITY, "MarginCallResponseAction", path, "", res.getError());
				}
				return success("MarginCallResponseAction", ValidationType.CARDINALITY, "MarginCallResponseAction", path, "");
			})
			.collect(toList());
	}

}
