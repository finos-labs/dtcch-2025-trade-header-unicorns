package cdm.product.template.validation;

import cdm.product.template.SettlementPayout;
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

public class SettlementPayoutTypeFormatValidator implements Validator<SettlementPayout> {

	private List<ComparisonResult> getComparisonResults(SettlementPayout o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<SettlementPayout> validate(RosettaPath path, SettlementPayout o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("SettlementPayout", ValidationType.TYPE_FORMAT, "SettlementPayout", path, "", error);
		}
		return success("SettlementPayout", ValidationType.TYPE_FORMAT, "SettlementPayout", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, SettlementPayout o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("SettlementPayout", ValidationType.TYPE_FORMAT, "SettlementPayout", path, "", res.getError());
				}
				return success("SettlementPayout", ValidationType.TYPE_FORMAT, "SettlementPayout", path, "");
			})
			.collect(toList());
	}

}
