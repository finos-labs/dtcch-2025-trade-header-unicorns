package cdm.event.common.validation.datarule;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.event.common.ContractDetails;
import cdm.event.common.Trade;
import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.ContractualDefinitionsEnum;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.metafields.FieldWithMetaContractualDefinitionsEnum;
import cdm.observable.asset.CreditIndex;
import cdm.observable.event.CreditEventNotice;
import cdm.observable.event.CreditEvents;
import cdm.observable.event.Restructuring;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ProtectionTerms;
import cdm.product.asset.ReferenceInformation;
import cdm.product.template.EconomicTerms;
import cdm.product.template.NonTransferableProduct;
import cdm.product.template.Payout;
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
@RosettaDataRule("TradeFpML_cd_19")
@ImplementedBy(TradeFpMLCd19.Default.class)
public interface TradeFpMLCd19 extends Validator<Trade> {
	
	String NAME = "TradeFpML_cd_19";
	String DEFINITION = "if contractDetails -> documentation -> legalAgreementIdentification -> agreementName -> contractualDefinitionsType any = ContractualDefinitionsEnum -> ISDA1999CreditDerivatives then product -> economicTerms -> payout -> CreditDefaultPayout -> protectionTerms -> creditEvents -> creditEventNotice -> businessCenter is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> protectionTerms -> creditEvents -> restructuring -> multipleHolderObligation is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> protectionTerms -> creditEvents -> restructuring -> multipleCreditEventNotices is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> generalTerms -> referenceInformation -> allGuarantees is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> generalTerms -> indexReferenceInformation is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> generalTerms -> substitution is absent and product -> economicTerms -> payout -> CreditDefaultPayout -> generalTerms -> modifiedEquityDelivery is absent";
	
	ValidationResult<Trade> validate(RosettaPath path, Trade trade);
	
	class Default implements TradeFpMLCd19 {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			ComparisonResult result = executeDataRule(trade);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Trade", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Trade trade) {
			try {
				if (areEqual(MapperS.of(trade).<ContractDetails>map("getContractDetails", _trade -> _trade.getContractDetails()).<LegalAgreement>mapC("getDocumentation", contractDetails -> contractDetails.getDocumentation()).<LegalAgreementIdentification>map("getLegalAgreementIdentification", legalAgreement -> legalAgreement.getLegalAgreementIdentification()).<AgreementName>map("getAgreementName", legalAgreementIdentification -> legalAgreementIdentification.getAgreementName()).<FieldWithMetaContractualDefinitionsEnum>mapC("getContractualDefinitionsType", agreementName -> agreementName.getContractualDefinitionsType()).<ContractualDefinitionsEnum>map("Type coercion", fieldWithMetaContractualDefinitionsEnum -> fieldWithMetaContractualDefinitionsEnum.getValue()), MapperS.of(ContractualDefinitionsEnum.ISDA_1999_CREDIT_DERIVATIVES), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<ProtectionTerms>mapC("getProtectionTerms", creditDefaultPayout -> creditDefaultPayout.getProtectionTerms()).<CreditEvents>map("getCreditEvents", protectionTerms -> protectionTerms.getCreditEvents()).<CreditEventNotice>map("getCreditEventNotice", creditEvents -> creditEvents.getCreditEventNotice()).<BusinessCenterEnum>map("getBusinessCenter", creditEventNotice -> creditEventNotice.getBusinessCenter())).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<ProtectionTerms>mapC("getProtectionTerms", creditDefaultPayout -> creditDefaultPayout.getProtectionTerms()).<CreditEvents>map("getCreditEvents", protectionTerms -> protectionTerms.getCreditEvents()).<Restructuring>map("getRestructuring", creditEvents -> creditEvents.getRestructuring()).<Boolean>map("getMultipleHolderObligation", restructuring -> restructuring.getMultipleHolderObligation()))).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<ProtectionTerms>mapC("getProtectionTerms", creditDefaultPayout -> creditDefaultPayout.getProtectionTerms()).<CreditEvents>map("getCreditEvents", protectionTerms -> protectionTerms.getCreditEvents()).<Restructuring>map("getRestructuring", creditEvents -> creditEvents.getRestructuring()).<Boolean>map("getMultipleCreditEventNotices", restructuring -> restructuring.getMultipleCreditEventNotices()))).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<ReferenceInformation>map("getReferenceInformation", generalTerms -> generalTerms.getReferenceInformation()).<Boolean>map("getAllGuarantees", referenceInformation -> referenceInformation.getAllGuarantees()))).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<CreditIndex>map("getIndexReferenceInformation", generalTerms -> generalTerms.getIndexReferenceInformation()))).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<Boolean>map("getSubstitution", generalTerms -> generalTerms.getSubstitution()))).and(notExists(MapperS.of(trade).<NonTransferableProduct>map("getProduct", _trade -> _trade.getProduct()).<EconomicTerms>map("getEconomicTerms", nonTransferableProduct -> nonTransferableProduct.getEconomicTerms()).<Payout>mapC("getPayout", economicTerms -> economicTerms.getPayout()).<CreditDefaultPayout>map("getCreditDefaultPayout", payout -> payout.getCreditDefaultPayout()).<GeneralTerms>map("getGeneralTerms", creditDefaultPayout -> creditDefaultPayout.getGeneralTerms()).<Boolean>map("getModifiedEquityDelivery", generalTerms -> generalTerms.getModifiedEquityDelivery())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TradeFpMLCd19 {
	
		@Override
		public ValidationResult<Trade> validate(RosettaPath path, Trade trade) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Trade", path, DEFINITION);
		}
	}
}
