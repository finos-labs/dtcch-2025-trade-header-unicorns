package cdm.product.collateral.validation.exists;

import cdm.product.collateral.ListingExchange;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class ListingExchangeOnlyExistsValidator implements ValidatorWithArg<ListingExchange, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ListingExchange> ValidationResult<ListingExchange> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("exchange", ExistenceChecker.isSet((List<? extends FieldWithMetaString>) o.getExchange()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ListingExchange", ValidationType.ONLY_EXISTS, "ListingExchange", path, "");
		}
		return failure("ListingExchange", ValidationType.ONLY_EXISTS, "ListingExchange", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
