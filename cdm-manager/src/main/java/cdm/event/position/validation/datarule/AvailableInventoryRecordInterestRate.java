package cdm.event.position.validation.datarule;

import cdm.event.position.AvailableInventoryRecord;
import cdm.observable.asset.Price;
import cdm.observable.asset.PriceTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("AvailableInventoryRecordInterestRate")
@ImplementedBy(AvailableInventoryRecordInterestRate.Default.class)
public interface AvailableInventoryRecordInterestRate extends Validator<AvailableInventoryRecord> {
	
	String NAME = "AvailableInventoryRecordInterestRate";
	String DEFINITION = "if interestRate exists then interestRate -> priceType = PriceTypeEnum -> InterestRate";
	
	ValidationResult<AvailableInventoryRecord> validate(RosettaPath path, AvailableInventoryRecord availableInventoryRecord);
	
	class Default implements AvailableInventoryRecordInterestRate {
	
		@Override
		public ValidationResult<AvailableInventoryRecord> validate(RosettaPath path, AvailableInventoryRecord availableInventoryRecord) {
			ComparisonResult result = executeDataRule(availableInventoryRecord);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AvailableInventoryRecord", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AvailableInventoryRecord", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AvailableInventoryRecord availableInventoryRecord) {
			try {
				if (exists(MapperS.of(availableInventoryRecord).<Price>map("getInterestRate", _availableInventoryRecord -> _availableInventoryRecord.getInterestRate())).getOrDefault(false)) {
					return areEqual(MapperS.of(availableInventoryRecord).<Price>map("getInterestRate", _availableInventoryRecord -> _availableInventoryRecord.getInterestRate()).<PriceTypeEnum>map("getPriceType", price -> price.getPriceType()), MapperS.of(PriceTypeEnum.INTEREST_RATE), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AvailableInventoryRecordInterestRate {
	
		@Override
		public ValidationResult<AvailableInventoryRecord> validate(RosettaPath path, AvailableInventoryRecord availableInventoryRecord) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AvailableInventoryRecord", path, DEFINITION);
		}
	}
}
