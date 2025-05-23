package cdm.event.position.validation;

import cdm.event.position.AvailableInventory;
import cdm.event.position.AvailableInventoryTypeEnum;
import cdm.event.workflow.MessageInformation;
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

public class AvailableInventoryValidator implements Validator<AvailableInventory> {

	private List<ComparisonResult> getComparisonResults(AvailableInventory o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("availableInventoryType", (AvailableInventoryTypeEnum) o.getAvailableInventoryType() != null ? 1 : 0, 1, 1), 
				checkCardinality("messageInformation", (MessageInformation) o.getMessageInformation() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<AvailableInventory> validate(RosettaPath path, AvailableInventory o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("AvailableInventory", ValidationType.CARDINALITY, "AvailableInventory", path, "", error);
		}
		return success("AvailableInventory", ValidationType.CARDINALITY, "AvailableInventory", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, AvailableInventory o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("AvailableInventory", ValidationType.CARDINALITY, "AvailableInventory", path, "", res.getError());
				}
				return success("AvailableInventory", ValidationType.CARDINALITY, "AvailableInventory", path, "");
			})
			.collect(toList());
	}

}
