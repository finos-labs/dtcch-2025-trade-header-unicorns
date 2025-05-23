package cdm.product.qualification.functions;

import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.SettlementTypeEnum;
import cdm.product.template.EconomicTerms;
import cdm.product.template.OptionPayout;
import cdm.product.template.Payout;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_Commodity_Option_Cash.Qualify_Commodity_Option_CashDefault.class)
public abstract class Qualify_Commodity_Option_Cash implements RosettaFunction {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_Commodity_Option qualify_Commodity_Option;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	public static class Qualify_Commodity_Option_CashDefault extends Qualify_Commodity_Option_Cash {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_Commodity_Option.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(MapperS.of(economicTerms).<Payout>mapC("getPayout", _economicTerms -> _economicTerms.getPayout()).<OptionPayout>map("getOptionPayout", payout -> payout.getOptionPayout()).get()).<SettlementTerms>map("getSettlementTerms", optionPayout -> optionPayout.getSettlementTerms()).<SettlementTypeEnum>map("getSettlementType", settlementTerms -> settlementTerms.getSettlementType()), MapperS.of(SettlementTypeEnum.CASH), CardinalityOperator.All)).get();
			
			return is_product;
		}
	}
}
