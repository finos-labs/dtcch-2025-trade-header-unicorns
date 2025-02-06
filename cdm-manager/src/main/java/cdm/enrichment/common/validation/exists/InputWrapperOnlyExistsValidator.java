package cdm.enrichment.common.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.enrichment.common.InputWrapper;
import cdm.event.common.CorporateAction;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class InputWrapperOnlyExistsValidator implements ValidatorWithArg<InputWrapper, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends InputWrapper> ValidationResult<InputWrapper> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("eventIdentifier", ExistenceChecker.isSet((List<? extends Identifier>) o.getEventIdentifier()))
				.put("corporateAction", ExistenceChecker.isSet((CorporateAction) o.getCorporateAction()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("InputWrapper", ValidationType.ONLY_EXISTS, "InputWrapper", path, "");
		}
		return failure("InputWrapper", ValidationType.ONLY_EXISTS, "InputWrapper", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
