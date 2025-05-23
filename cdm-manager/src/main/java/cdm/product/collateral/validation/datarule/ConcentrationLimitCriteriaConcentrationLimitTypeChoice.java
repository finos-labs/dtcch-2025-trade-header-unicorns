package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.ConcentrationLimitCriteria;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("ConcentrationLimitCriteriaConcentrationLimitTypeChoice")
@ImplementedBy(ConcentrationLimitCriteriaConcentrationLimitTypeChoice.Default.class)
public interface ConcentrationLimitCriteriaConcentrationLimitTypeChoice extends Validator<ConcentrationLimitCriteria> {
	
	String NAME = "ConcentrationLimitCriteriaConcentrationLimitTypeChoice";
	String DEFINITION = "required choice concentrationLimitType, averageTradingVolume, collateralCriteria";
	
	ValidationResult<ConcentrationLimitCriteria> validate(RosettaPath path, ConcentrationLimitCriteria concentrationLimitCriteria);
	
	class Default implements ConcentrationLimitCriteriaConcentrationLimitTypeChoice {
	
		@Override
		public ValidationResult<ConcentrationLimitCriteria> validate(RosettaPath path, ConcentrationLimitCriteria concentrationLimitCriteria) {
			ComparisonResult result = executeDataRule(concentrationLimitCriteria);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimitCriteria", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ConcentrationLimitCriteria", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ConcentrationLimitCriteria concentrationLimitCriteria) {
			try {
				return choice(MapperS.of(concentrationLimitCriteria), Arrays.asList("concentrationLimitType", "averageTradingVolume", "collateralCriteria"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ConcentrationLimitCriteriaConcentrationLimitTypeChoice {
	
		@Override
		public ValidationResult<ConcentrationLimitCriteria> validate(RosettaPath path, ConcentrationLimitCriteria concentrationLimitCriteria) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ConcentrationLimitCriteria", path, DEFINITION);
		}
	}
}
