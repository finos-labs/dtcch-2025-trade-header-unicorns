package cdm.event.position.validation;

import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.event.position.Position;
import cdm.observable.asset.Money;
import cdm.observable.asset.PriceQuantity;
import cdm.product.template.Product;
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

public class PositionValidator implements Validator<Position> {

	private List<ComparisonResult> getComparisonResults(Position o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("priceQuantity", (List<? extends PriceQuantity>) o.getPriceQuantity() == null ? 0 : o.getPriceQuantity().size(), 1, 0), 
				checkCardinality("product", (Product) o.getProduct() != null ? 1 : 0, 1, 1), 
				checkCardinality("cashBalance", (Money) o.getCashBalance() != null ? 1 : 0, 0, 1), 
				checkCardinality("tradeReference", (ReferenceWithMetaTradeState) o.getTradeReference() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Position> validate(RosettaPath path, Position o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Position", ValidationType.CARDINALITY, "Position", path, "", error);
		}
		return success("Position", ValidationType.CARDINALITY, "Position", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Position o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Position", ValidationType.CARDINALITY, "Position", path, "", res.getError());
				}
				return success("Position", ValidationType.CARDINALITY, "Position", path, "");
			})
			.collect(toList());
	}

}
