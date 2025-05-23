package cdm.base.staticdata.asset.common.validation;

import cdm.base.staticdata.asset.common.AssetIdentifier;
import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.EquityTypeEnum;
import cdm.base.staticdata.asset.common.FundProductTypeEnum;
import cdm.base.staticdata.asset.common.InstrumentTypeEnum;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.party.LegalEntity;
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

public class SecurityValidator implements Validator<Security> {

	private List<ComparisonResult> getComparisonResults(Security o) {
		return Lists.<ComparisonResult>newArrayList(
				checkCardinality("identifier", (List<? extends AssetIdentifier>) o.getIdentifier() == null ? 0 : o.getIdentifier().size(), 1, 0), 
				checkCardinality("isExchangeListed", (Boolean) o.getIsExchangeListed() != null ? 1 : 0, 0, 1), 
				checkCardinality("exchange", (LegalEntity) o.getExchange() != null ? 1 : 0, 0, 1), 
				checkCardinality("instrumentType", (InstrumentTypeEnum) o.getInstrumentType() != null ? 1 : 0, 1, 1), 
				checkCardinality("debtType", (DebtType) o.getDebtType() != null ? 1 : 0, 0, 1), 
				checkCardinality("equityType", (EquityTypeEnum) o.getEquityType() != null ? 1 : 0, 0, 1), 
				checkCardinality("fundType", (FundProductTypeEnum) o.getFundType() != null ? 1 : 0, 0, 1)
			);
	}

	@Override
	public ValidationResult<Security> validate(RosettaPath path, Security o) {
		String error = getComparisonResults(o)
			.stream()
			.filter(res -> !res.get())
			.map(res -> res.getError())
			.collect(joining("; "));

		if (!isNullOrEmpty(error)) {
			return failure("Security", ValidationType.CARDINALITY, "Security", path, "", error);
		}
		return success("Security", ValidationType.CARDINALITY, "Security", path, "");
	}

	@Override
	public List<ValidationResult<?>> getValidationResults(RosettaPath path, Security o) {
		return getComparisonResults(o)
			.stream()
			.map(res -> {
				if (!isNullOrEmpty(res.getError())) {
					return failure("Security", ValidationType.CARDINALITY, "Security", path, "", res.getError());
				}
				return success("Security", ValidationType.CARDINALITY, "Security", path, "");
			})
			.collect(toList());
	}

}
