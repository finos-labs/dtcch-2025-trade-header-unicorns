package cdm.product.template.validation.exists;

import cdm.product.template.NonTransferableProduct;
import cdm.product.template.Product;
import cdm.product.template.TransferableProduct;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ProductOnlyExistsValidator implements ValidatorWithArg<Product, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends Product> ValidationResult<Product> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("TransferableProduct", ExistenceChecker.isSet((TransferableProduct) o.getTransferableProduct()))
				.put("NonTransferableProduct", ExistenceChecker.isSet((NonTransferableProduct) o.getNonTransferableProduct()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("Product", ValidationType.ONLY_EXISTS, "Product", path, "");
		}
		return failure("Product", ValidationType.ONLY_EXISTS, "Product", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
