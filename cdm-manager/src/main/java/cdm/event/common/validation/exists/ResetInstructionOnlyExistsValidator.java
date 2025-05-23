package cdm.event.common.validation.exists;

import cdm.event.common.ResetInstruction;
import cdm.product.template.metafields.ReferenceWithMetaPayout;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.records.Date;
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

public class ResetInstructionOnlyExistsValidator implements ValidatorWithArg<ResetInstruction, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends ResetInstruction> ValidationResult<ResetInstruction> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("payout", ExistenceChecker.isSet((List<? extends ReferenceWithMetaPayout>) o.getPayout()))
				.put("rateRecordDate", ExistenceChecker.isSet((Date) o.getRateRecordDate()))
				.put("resetDate", ExistenceChecker.isSet((Date) o.getResetDate()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("ResetInstruction", ValidationType.ONLY_EXISTS, "ResetInstruction", path, "");
		}
		return failure("ResetInstruction", ValidationType.ONLY_EXISTS, "ResetInstruction", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
