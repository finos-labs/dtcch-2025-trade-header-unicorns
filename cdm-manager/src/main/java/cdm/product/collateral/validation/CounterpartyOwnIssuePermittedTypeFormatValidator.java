package cdm.product.collateral.validation;

import cdm.product.collateral.CounterpartyOwnIssuePermitted;
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

public class CounterpartyOwnIssuePermittedTypeFormatValidator implements Validator<CounterpartyOwnIssuePermitted> {

	private List<ComparisonResult> getComparisonResults(CounterpartyOwnIssuePermitted o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<CounterpartyOwnIssuePermitted> validate(RosettaPath path, CounterpartyOwnIssuePermitted o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("CounterpartyOwnIssuePermitted", ValidationType.TYPE_FORMAT, "CounterpartyOwnIssuePermitted", path, "", error);
		}
		return success("CounterpartyOwnIssuePermitted", ValidationType.TYPE_FORMAT, "CounterpartyOwnIssuePermitted", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, CounterpartyOwnIssuePermitted o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("CounterpartyOwnIssuePermitted", ValidationType.TYPE_FORMAT, "CounterpartyOwnIssuePermitted", path, "", res.getError());
				}
				return success("CounterpartyOwnIssuePermitted", ValidationType.TYPE_FORMAT, "CounterpartyOwnIssuePermitted", path, "");
			})
			.collect(toList());
	}

}
