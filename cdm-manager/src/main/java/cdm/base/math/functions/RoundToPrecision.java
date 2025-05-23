package cdm.base.math.functions;

import cdm.base.math.RoundingDirectionEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(RoundToPrecision.RoundToPrecisionDefault.class)
public abstract class RoundToPrecision implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;

	/**
	* @param value The original (unrounded) number.
	* @param precision The number of decimal digits of precision.
	* @param roundingMode The method of rounding (up/down/nearest).
	* @param removeTrailingZeros Flag to specify whether to strip trailing zeros.
	* @return roundedValue The value to the desired precision
	*/
	public BigDecimal evaluate(BigDecimal value, Integer precision, RoundingDirectionEnum roundingMode, Boolean removeTrailingZeros) {
		// pre-conditions
		conditionValidator.validate(() -> greaterThanEquals(MapperS.of(precision), MapperS.of(0), CardinalityOperator.All),
			"The number of decimal digits of precision should be greater than or equal to zero.");
		
		BigDecimal roundedValue = doEvaluate(value, precision, roundingMode, removeTrailingZeros);
		
		return roundedValue;
	}

	protected abstract BigDecimal doEvaluate(BigDecimal value, Integer precision, RoundingDirectionEnum roundingMode, Boolean removeTrailingZeros);

	public static class RoundToPrecisionDefault extends RoundToPrecision {
		@Override
		protected BigDecimal doEvaluate(BigDecimal value, Integer precision, RoundingDirectionEnum roundingMode, Boolean removeTrailingZeros) {
			BigDecimal roundedValue = null;
			return assignOutput(roundedValue, value, precision, roundingMode, removeTrailingZeros);
		}
		
		protected BigDecimal assignOutput(BigDecimal roundedValue, BigDecimal value, Integer precision, RoundingDirectionEnum roundingMode, Boolean removeTrailingZeros) {
			return roundedValue;
		}
	}
}
