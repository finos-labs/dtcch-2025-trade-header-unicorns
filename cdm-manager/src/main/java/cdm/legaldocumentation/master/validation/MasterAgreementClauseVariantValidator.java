package cdm.legaldocumentation.master.validation;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
import cdm.legaldocumentation.master.MasterAgreementVariantIdentifierEnum;
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

public class MasterAgreementClauseVariantValidator implements Validator<MasterAgreementClauseVariant> {

	private List<ComparisonResult> getComparisonResults(MasterAgreementClauseVariant o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (MasterAgreementVariantIdentifierEnum) o.getIdentifier() != null ? 1 : 0, 1, 1), 
				checkCardinality("name", (String) o.getName() != null ? 1 : 0, 0, 1), 
				checkCardinality("counterparty", (List<CounterpartyRoleEnum>) o.getCounterparty() == null ? 0 : o.getCounterparty().size(), 0, 2)
			);
	}

	@Override
	public ValidationResult<MasterAgreementClauseVariant> validate(RosettaPath path, MasterAgreementClauseVariant o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("MasterAgreementClauseVariant", ValidationType.CARDINALITY, "MasterAgreementClauseVariant", path, "", error);
		}
		return success("MasterAgreementClauseVariant", ValidationType.CARDINALITY, "MasterAgreementClauseVariant", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, MasterAgreementClauseVariant o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("MasterAgreementClauseVariant", ValidationType.CARDINALITY, "MasterAgreementClauseVariant", path, "", res.getError());
				}
				return success("MasterAgreementClauseVariant", ValidationType.CARDINALITY, "MasterAgreementClauseVariant", path, "");
			})
			.collect(toList());
	}

}
