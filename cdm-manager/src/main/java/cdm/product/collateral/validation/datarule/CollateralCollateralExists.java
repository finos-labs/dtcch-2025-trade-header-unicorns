package cdm.product.collateral.validation.datarule;

import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.product.collateral.Collateral;
import cdm.product.collateral.IndependentAmount;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("CollateralCollateralExists")
@ImplementedBy(CollateralCollateralExists.Default.class)
public interface CollateralCollateralExists extends Validator<Collateral> {
	
	String NAME = "CollateralCollateralExists";
	String DEFINITION = "independentAmount exists or collateralPortfolio exists";
	
	ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral);
	
	class Default implements CollateralCollateralExists {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			ComparisonResult result = executeDataRule(collateral);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Collateral", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Collateral collateral) {
			try {
				return exists(MapperS.of(collateral).<IndependentAmount>map("getIndependentAmount", _collateral -> _collateral.getIndependentAmount())).or(exists(MapperS.of(collateral).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", _collateral -> _collateral.getCollateralPortfolio())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralCollateralExists {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
		}
	}
}
