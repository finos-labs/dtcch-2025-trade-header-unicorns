package cdm.observable.asset.validation.exists;

import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.ValuationDates;
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

public class ValuationDatesOnlyExistsValidator implements ValidatorWithArg<ValuationDates, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ValuationDates> ValidationResult<ValuationDates> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("initialValuationDate", ExistenceChecker.isSet((PerformanceValuationDates) o.getInitialValuationDate()))
				.put("interimValuationDate", ExistenceChecker.isSet((PerformanceValuationDates) o.getInterimValuationDate()))
				.put("finalValuationDate", ExistenceChecker.isSet((PerformanceValuationDates) o.getFinalValuationDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ValuationDates", ValidationType.ONLY_EXISTS, "ValuationDates", path, "");
		}
		return failure("ValuationDates", ValidationType.ONLY_EXISTS, "ValuationDates", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
