package cdm.enrichment.common.validation;

import cdm.enrichment.common.InputWrapper;
import cdm.event.common.CorporateAction;
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

public class InputWrapperValidator implements Validator<InputWrapper> {

	private List<ComparisonResult> getComparisonResults(InputWrapper o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("corporateAction", (CorporateAction) o.getCorporateAction() != null ? 1 : 0, 1, 1)
			);
	}

	@Override
	public ValidationResult<InputWrapper> validate(RosettaPath path, InputWrapper o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("InputWrapper", ValidationType.CARDINALITY, "InputWrapper", path, "", error);
		}
		return success("InputWrapper", ValidationType.CARDINALITY, "InputWrapper", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, InputWrapper o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("InputWrapper", ValidationType.CARDINALITY, "InputWrapper", path, "", res.getError());
				}
				return success("InputWrapper", ValidationType.CARDINALITY, "InputWrapper", path, "");
			})
			.collect(toList());
	}

}
