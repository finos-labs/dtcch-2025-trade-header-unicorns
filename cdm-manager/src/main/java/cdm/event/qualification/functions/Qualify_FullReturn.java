package cdm.event.qualification.functions;

import cdm.event.common.BusinessEvent;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.CollateralPosition;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.PrimitiveInstruction;
import cdm.event.common.QuantityChangeInstruction;
import cdm.event.common.State;
import cdm.event.common.Trade;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.functions.QuantityDecreasedToZero;
import cdm.event.common.functions.TransfersForDate;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaTradeState;
import cdm.legaldocumentation.common.ClosedState;
import cdm.legaldocumentation.common.ClosedStateEnum;
import cdm.product.collateral.Collateral;
import cdm.product.template.AssetPayout;
import cdm.product.template.EconomicTerms;
import cdm.product.template.NonTransferableProduct;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import cdm.product.template.util.ProductDeepPathUtil;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_FullReturn.Qualify_FullReturnDefault.class)
public abstract class Qualify_FullReturn implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected ProductDeepPathUtil productDeepPathUtil;
	@Inject protected QuantityDecreasedToZero quantityDecreasedToZero;
	@Inject protected TransfersForDate transfersForDate;

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

	protected abstract MapperS<? extends Transfer> transfer(BusinessEvent businessEvent);

	public static class Qualify_FullReturnDefault extends Qualify_FullReturn {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = notExists(MapperS.of(businessEvent).<EventIntentEnum>map("getIntent", _businessEvent -> _businessEvent.getIntent())).and(exists(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<Trade>map("getTrade", tradeState -> tradeState.getTrade()).<NonTransferableProduct>map("getProduct", trade -> trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Collateral>map("getCollateral", economicTerms -> economicTerms.getCollateral()).<ReferenceWithMetaCollateralPortfolio>mapC("getCollateralPortfolio", collateral -> collateral.getCollateralPortfolio()).<CollateralPortfolio>map("Type coercion", referenceWithMetaCollateralPortfolio -> referenceWithMetaCollateralPortfolio.getValue()).<CollateralPosition>mapC("getCollateralPosition", collateralPortfolio -> collateralPortfolio.getCollateralPosition()).<Product>map("getProduct", collateralPosition -> collateralPosition.getProduct()).<EconomicTerms>map("chooseEconomicTerms", product -> productDeepPathUtil.chooseEconomicTerms(product)).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<AssetPayout>map("getAssetPayout", payout -> payout.getAssetPayout()))).and(areEqual(MapperS.of(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).resultCount()), MapperS.of(1), CardinalityOperator.All).and(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<QuantityChangeInstruction>map("getQuantityChange", primitiveInstruction -> primitiveInstruction.getQuantityChange()))).or(exists(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<PrimitiveInstruction>map("getPrimitiveInstruction", instruction -> instruction.getPrimitiveInstruction()).<QuantityChangeInstruction>map("getQuantityChange", primitiveInstruction -> primitiveInstruction.getQuantityChange())).and(exists(transfer(businessEvent))))).and(areEqual(MapperS.of(quantityDecreasedToZero.evaluate(MapperS.of(businessEvent).<Instruction>mapC("getInstruction", _businessEvent -> _businessEvent.getInstruction()).<ReferenceWithMetaTradeState>map("getBefore", instruction -> instruction.getBefore()).<TradeState>map("Type coercion", referenceWithMetaTradeState -> referenceWithMetaTradeState.getValue()).getMulti(), MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).getMulti())), MapperS.of(true), CardinalityOperator.All)).and(areEqual(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<State>map("getState", tradeState -> tradeState.getState()).<ClosedState>map("getClosedState", state -> state.getClosedState()).<ClosedStateEnum>map("getState", closedState -> closedState.getState()), MapperS.of(ClosedStateEnum.TERMINATED), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperS<? extends Transfer> transfer(BusinessEvent businessEvent) {
			return MapperS.of(MapperC.of(transfersForDate.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<TransferState>mapC("getTransferHistory", tradeState -> tradeState.getTransferHistory()).<Transfer>map("getTransfer", transferState -> transferState.getTransfer()).getMulti(), MapperS.of(businessEvent).<Date>map("getEventDate", _businessEvent -> _businessEvent.getEventDate()).get())).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
