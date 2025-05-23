package cdm.product.template.validation;

import cdm.product.template.NonTransferableProduct;
import cdm.product.template.Product;
import cdm.product.template.TransferableProduct;
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

public class ProductValidator implements Validator<Product> {

	private List<ComparisonResult> getComparisonResults(Product o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("TransferableProduct", (TransferableProduct) o.getTransferableProduct() != null ? 1 : 0, 0, 1), 
				checkCardinality("NonTransferableProduct", (NonTransferableProduct) o.getNonTransferableProduct() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Product> validate(RosettaPath path, Product o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Product", ValidationType.CARDINALITY, "Product", path, "", error);
		}
		return success("Product", ValidationType.CARDINALITY, "Product", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Product o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Product", ValidationType.CARDINALITY, "Product", path, "", res.getError());
				}
				return success("Product", ValidationType.CARDINALITY, "Product", path, "");
			})
			.collect(toList());
	}

}
