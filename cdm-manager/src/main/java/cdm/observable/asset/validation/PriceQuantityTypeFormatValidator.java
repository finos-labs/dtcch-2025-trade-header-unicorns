package cdm.observable.asset.validation;

import cdm.observable.asset.PriceQuantity;
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

public class PriceQuantityTypeFormatValidator implements Validator<PriceQuantity> {

	private List<ComparisonResult> getComparisonResults(PriceQuantity o) {
		return Lists.<ComparisonResult>newArrayList(
			);
	}

	@Override
	public ValidationResult<PriceQuantity> validate(RosettaPath path, PriceQuantity o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("PriceQuantity", ValidationType.TYPE_FORMAT, "PriceQuantity", path, "", error);
		}
		return success("PriceQuantity", ValidationType.TYPE_FORMAT, "PriceQuantity", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, PriceQuantity o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("PriceQuantity", ValidationType.TYPE_FORMAT, "PriceQuantity", path, "", res.getError());
				}
				return success("PriceQuantity", ValidationType.TYPE_FORMAT, "PriceQuantity", path, "");
			})
			.collect(toList());
	}

}
