package cdm.product.common.settlement.functions;

import cdm.base.math.QuantityChangeDirectionEnum;
import cdm.observable.asset.PriceQuantity;
import cdm.observable.asset.PriceQuantity.PriceQuantityBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;


@ImplementedBy(UpdateAmountForEachMatchingQuantity.UpdateAmountForEachMatchingQuantityDefault.class)
public abstract class UpdateAmountForEachMatchingQuantity implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param priceQuantity List of price quantities to update.
	* @param change 
	* @param direction 
	* @return updatedPriceQuantity List of price quantities with quantity amounts updated.
	*/
	public List<? extends PriceQuantity> evaluate(List<? extends PriceQuantity> priceQuantity, List<? extends PriceQuantity> change, QuantityChangeDirectionEnum direction) {
		List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantityBuilder = doEvaluate(priceQuantity, change, direction);
		
		final List<? extends PriceQuantity> updatedPriceQuantity;
		if (updatedPriceQuantityBuilder == null) {
			updatedPriceQuantity = null;
		} else {
			updatedPriceQuantity = updatedPriceQuantityBuilder.stream().map(PriceQuantity::build).collect(Collectors.toList());
			objectValidator.validate(PriceQuantity.class, updatedPriceQuantity);
		}
		
		return updatedPriceQuantity;
	}

	protected abstract List<PriceQuantity.PriceQuantityBuilder> doEvaluate(List<? extends PriceQuantity> priceQuantity, List<? extends PriceQuantity> change, QuantityChangeDirectionEnum direction);

	public static class UpdateAmountForEachMatchingQuantityDefault extends UpdateAmountForEachMatchingQuantity {
		@Override
		protected List<PriceQuantity.PriceQuantityBuilder> doEvaluate(List<? extends PriceQuantity> priceQuantity, List<? extends PriceQuantity> change, QuantityChangeDirectionEnum direction) {
			if (priceQuantity == null) {
				priceQuantity = Collections.emptyList();
			}
			if (change == null) {
				change = Collections.emptyList();
			}
			List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantity = new ArrayList<>();
			return assignOutput(updatedPriceQuantity, priceQuantity, change, direction);
		}
		
		protected List<PriceQuantity.PriceQuantityBuilder> assignOutput(List<PriceQuantity.PriceQuantityBuilder> updatedPriceQuantity, List<? extends PriceQuantity> priceQuantity, List<? extends PriceQuantity> change, QuantityChangeDirectionEnum direction) {
			return Optional.ofNullable(updatedPriceQuantity)
				.map(o -> o.stream().map(i -> i.prune()).collect(Collectors.toList()))
				.orElse(null);
		}
	}
}
