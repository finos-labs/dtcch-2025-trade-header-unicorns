package cdm.event.common.functions;

import cdm.base.math.CompareOp;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.template.TradeLot;
import cdm.product.template.functions.CompareTradeLot;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(QuantityIncreased.QuantityIncreasedDefault.class)
public abstract class QuantityIncreased implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected CompareTradeLot compareTradeLot;

	/**
	* @param before 
	* @param after 
	* @return result 
	*/
	public Boolean evaluate(TradeState before, List<? extends TradeState> after) {
		Boolean result = doEvaluate(before, after);
		
		return result;
	}

	protected abstract Boolean doEvaluate(TradeState before, List<? extends TradeState> after);

	public static class QuantityIncreasedDefault extends QuantityIncreased {
		@Override
		protected Boolean doEvaluate(TradeState before, List<? extends TradeState> after) {
			if (after == null) {
				after = Collections.emptyList();
			}
			Boolean result = null;
			return assignOutput(result, before, after);
		}
		
		protected Boolean assignOutput(Boolean result, TradeState before, List<? extends TradeState> after) {
			result = areEqual(MapperC.<TradeState>of(after)
				.mapItem(item -> areEqual(MapperS.of(compareTradeLot.evaluate(item.<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeLot>mapC("getTradeLot", trade -> trade.getTradeLot()).get(), CompareOp.GREATER_THAN, MapperS.of(before).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<TradeLot>mapC("getTradeLot", trade -> trade.getTradeLot()).get())), MapperS.of(true), CardinalityOperator.All).asMapper()), MapperS.of(true), CardinalityOperator.All).get();
			
			return result;
		}
	}
}
