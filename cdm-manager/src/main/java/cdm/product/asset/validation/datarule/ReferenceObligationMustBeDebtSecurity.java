package cdm.product.asset.validation.datarule;

import cdm.base.staticdata.asset.common.InstrumentTypeEnum;
import cdm.base.staticdata.asset.common.Security;
import cdm.product.asset.ReferenceObligation;
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
@RosettaDataRule("ReferenceObligationMustBeDebtSecurity")
@ImplementedBy(ReferenceObligationMustBeDebtSecurity.Default.class)
public interface ReferenceObligationMustBeDebtSecurity extends Validator<ReferenceObligation> {
	
	String NAME = "ReferenceObligationMustBeDebtSecurity";
	String DEFINITION = "if security exists then security -> instrumentType = InstrumentTypeEnum -> Debt";
	
	ValidationResult<ReferenceObligation> validate(RosettaPath path, ReferenceObligation referenceObligation);
	
	class Default implements ReferenceObligationMustBeDebtSecurity {
	
		@Override
		public ValidationResult<ReferenceObligation> validate(RosettaPath path, ReferenceObligation referenceObligation) {
			ComparisonResult result = executeDataRule(referenceObligation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReferenceObligation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ReferenceObligation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ReferenceObligation referenceObligation) {
			try {
				if (exists(MapperS.of(referenceObligation).<Security>map("getSecurity", _referenceObligation -> _referenceObligation.getSecurity())).getOrDefault(false)) {
					return areEqual(MapperS.of(referenceObligation).<Security>map("getSecurity", _referenceObligation -> _referenceObligation.getSecurity()).<InstrumentTypeEnum>map("getInstrumentType", security -> security.getInstrumentType()), MapperS.of(InstrumentTypeEnum.DEBT), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ReferenceObligationMustBeDebtSecurity {
	
		@Override
		public ValidationResult<ReferenceObligation> validate(RosettaPath path, ReferenceObligation referenceObligation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ReferenceObligation", path, DEFINITION);
		}
	}
}
