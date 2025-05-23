package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TermsChangeInstruction.TermsChangeInstructionBuilder;
import cdm.product.template.NonTransferableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_SubstitutionInstruction.Create_SubstitutionInstructionDefault.class)
public abstract class Create_SubstitutionInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected Create_EffectiveOrTerminationDateTermChangeInstruction create_EffectiveOrTerminationDateTermChangeInstruction;

	/**
	* @param product The original contractual product to be used as the basis of the new trade.
	* @param effectiveDate The effective date of the substitution.
	* @param newCollateralPortfolio New collateral portfolio to substitute for the original collateral.
	* @return termsChangeInstruction 
	*/
	public TermsChangeInstruction evaluate(NonTransferableProduct product, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio) {
		TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstructionBuilder = doEvaluate(product, effectiveDate, newCollateralPortfolio);
		
		final TermsChangeInstruction termsChangeInstruction;
		if (termsChangeInstructionBuilder == null) {
			termsChangeInstruction = null;
		} else {
			termsChangeInstruction = termsChangeInstructionBuilder.build();
			objectValidator.validate(TermsChangeInstruction.class, termsChangeInstruction);
		}
		
		return termsChangeInstruction;
	}

	protected abstract TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(NonTransferableProduct product, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio);

	public static class Create_SubstitutionInstructionDefault extends Create_SubstitutionInstruction {
		@Override
		protected TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(NonTransferableProduct product, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio) {
			TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction = TermsChangeInstruction.builder();
			return assignOutput(termsChangeInstruction, product, effectiveDate, newCollateralPortfolio);
		}
		
		protected TermsChangeInstruction.TermsChangeInstructionBuilder assignOutput(TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction, NonTransferableProduct product, AdjustableOrRelativeDate effectiveDate, CollateralPortfolio newCollateralPortfolio) {
			termsChangeInstruction = toBuilder(create_EffectiveOrTerminationDateTermChangeInstruction.evaluate(product, effectiveDate, null));
			
			termsChangeInstruction
				.getOrCreateProduct()
				.getOrCreateEconomicTerms()
				.getOrCreateCollateral()
				.setCollateralPortfolioValue((newCollateralPortfolio == null ? Collections.<CollateralPortfolio>emptyList() : Collections.singletonList(newCollateralPortfolio)));
			
			return Optional.ofNullable(termsChangeInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
