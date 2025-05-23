package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.event.common.BusinessEvent;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.product.asset.InterestRatePayout;
import cdm.product.template.EconomicTerms;
import cdm.product.template.NonTransferableProduct;
import cdm.product.template.Payout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.math.BigDecimal;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Adjustment.Qualify_AdjustmentDefault.class)
public abstract class Qualify_Adjustment implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected ExtractAfterTrade extractAfterTrade;
	@Inject protected ExtractBeforeEconomicTerms extractBeforeEconomicTerms;
	@Inject protected ExtractBeforeTrade extractBeforeTrade;
	@Inject protected ExtractOpenEconomicTerms extractOpenEconomicTerms;
	@Inject protected ExtractTradeCollateralPrice extractTradeCollateralPrice;
	@Inject protected ExtractTradeCollateralQuantity extractTradeCollateralQuantity;
	@Inject protected ExtractTradePurchasePrice extractTradePurchasePrice;

	/**
	* @param businessEvent 
	* @return is_event 
	*/
	@Override
	public Boolean evaluate(BusinessEvent businessEvent) {
		Boolean is_event = doEvaluate(businessEvent);
		
		return is_event;
	}

	protected abstract Boolean doEvaluate(BusinessEvent businessEvent);

	protected abstract MapperS<? extends Trade> beforeTrade(BusinessEvent businessEvent);

	protected abstract MapperS<? extends Trade> afterTrade(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradePurchasePrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradePurchasePrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradeCollateralQuantity(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradeCollateralQuantity(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> beforeTradeCollateralPrice(BusinessEvent businessEvent);

	protected abstract MapperC<BigDecimal> afterTradeCollateralPrice(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent);

	protected abstract MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent);

	public static class Qualify_AdjustmentDefault extends Qualify_Adjustment {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<NonTransferableProduct>map("getProduct", trade -> trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<InterestRatePayout>map("getInterestRatePayout", payout -> payout.getInterestRatePayout())).and(areEqual(openEconomicTerms(businessEvent).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()), beforeEconomicterms(businessEvent).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()), CardinalityOperator.All)).and(exists(beforeTradePurchasePrice(businessEvent))).and(exists(afterTradePurchasePrice(businessEvent))).and(areEqual(afterTradePurchasePrice(businessEvent), beforeTradePurchasePrice(businessEvent), CardinalityOperator.All)).and(notEqual(beforeTradeCollateralQuantity(businessEvent), afterTradeCollateralQuantity(businessEvent), CardinalityOperator.Any)).and(notEqual(beforeTradeCollateralPrice(businessEvent), afterTradeCollateralPrice(businessEvent), CardinalityOperator.Any)).and(exists(beforeEconomicterms(businessEvent))).and(exists(openEconomicTerms(businessEvent))).and(areEqual(openEconomicTerms(businessEvent).<AdjustableOrRelativeDate>map("getTerminationDate", economicTerms -> economicTerms.getTerminationDate()), beforeEconomicterms(businessEvent).<AdjustableOrRelativeDate>map("getTerminationDate", economicTerms -> economicTerms.getTerminationDate()), CardinalityOperator.All)).and(notEqual(openEconomicTerms(businessEvent).<AdjustableOrRelativeDate>map("getEffectiveDate", economicTerms -> economicTerms.getEffectiveDate()), beforeEconomicterms(businessEvent).<AdjustableOrRelativeDate>map("getEffectiveDate", economicTerms -> economicTerms.getEffectiveDate()), CardinalityOperator.Any)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends Trade> beforeTrade(BusinessEvent businessEvent) {
			return MapperS.of(extractBeforeTrade.evaluate(businessEvent));
		}
		
		@Override
		protected MapperS<? extends Trade> afterTrade(BusinessEvent businessEvent) {
			return MapperS.of(extractAfterTrade.evaluate(businessEvent));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradePurchasePrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradePurchasePrice.evaluate(beforeTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradePurchasePrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradePurchasePrice.evaluate(afterTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradeCollateralQuantity(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralQuantity.evaluate(beforeTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradeCollateralQuantity(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralQuantity.evaluate(afterTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> beforeTradeCollateralPrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralPrice.evaluate(beforeTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperC<BigDecimal> afterTradeCollateralPrice(BusinessEvent businessEvent) {
			return MapperC.<BigDecimal>of(extractTradeCollateralPrice.evaluate(afterTrade(businessEvent).get()));
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> beforeEconomicterms(BusinessEvent businessEvent) {
			return MapperS.of(extractBeforeEconomicTerms.evaluate(businessEvent));
		}
		
		@Override
		protected MapperS<? extends EconomicTerms> openEconomicTerms(BusinessEvent businessEvent) {
			return MapperS.of(extractOpenEconomicTerms.evaluate(businessEvent));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
