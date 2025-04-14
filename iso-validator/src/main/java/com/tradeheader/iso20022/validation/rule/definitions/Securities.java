package com.tradeheader.iso20022.validation.rule.definitions;

import com.handcoded.validation.Precondition;
import com.tradeheader.iso20022.meta.precondition.ISONamespacePrecondition;
import com.tradeheader.iso20022.meta.precondition.ISOPathValuePrecondition;
import com.tradeheader.iso20022.meta.precondition.ISOToolkitEnvelopeIdentifierPrecondition;
import com.tradeheader.iso20022.validation.constrain.IConstrain;
import com.tradeheader.iso20022.validation.constrain.ISOConstrainCondition;
import com.tradeheader.iso20022.validation.constrain.ISOConstrains;
import com.tradeheader.iso20022.validation.constrain.ISOUnitaryConstrain;
import com.tradeheader.iso20022.validation.rule.ISOComplexTypeRule;
public class Securities {


    private static final Precondition ISOToolkitEnvelopeNamespacePrecondition = new ISONamespacePrecondition("urn:tradeheader:xsd:toolkit:envelope");
    private static final Precondition IdentifierPrecondition_seev_031_002_15 = new ISOToolkitEnvelopeIdentifierPrecondition("securities_base_sr2025-seev.031.002.15");

    //Securities: seev.031.002.15
    /**
     * X00158 - CorporateActionNotificationV14_SafekeepingAccount1Rule
     * If AccountDetails/ForAllAccounts/IdentificationCode value is GENR (General) then any element in the list (MinimumNominalQuantity, MinimumExercisableQuantity,MinimumExercisableMultipleQuantity, ContractSize) must not be present in any occurrences of CorporateActionOptionDetails/SecuritiesMovementDetails/SecurityDetails;
     * @since 20240722
     * */
    private static final IConstrain IdentificationCode_GENR_Constrain = new ISOUnitaryConstrain("AcctDtls/ForAllAccts/IdCd", "GENR", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition SecurityDetails_List_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain(new String[]{"CorpActnOptnDtls/SctiesMvmntDtls/SctyDtls/MinNmnlQty", "CorpActnOptnDtls/SctiesMvmntDtls/SctyDtls/MinQtyToInst", "CorpActnOptnDtls/SctiesMvmntDtls/SctyDtls/MinMltplQtyToInst", "CorpActnOptnDtls/SctiesMvmntDtls/SctyDtls/CtrctSz"}, ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_SafekeepingAccount1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_SafekeepingAccount1Rule", "X00158", "Invalid message content for CorporateActionOptionDetails.", new String[]{"CorporateActionNotification002V15[*]"}, SecurityDetails_List_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(IdentificationCode_GENR_Constrain).build();

    /**
     * X00159 - CorporateActionNotificationV14_SafekeepingAccount2Rule
     * If AccountDetails/ForAllAccounts/IdentificationCode value is GENR (General) then AmountDetails must not be present in any occurrences of CorporateActionOptionDetails/CashMovementDetails.
     * @since 20240722
     * */
    private static final ISOConstrainCondition AmountDetails_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/CshMvmntDtls/AmtDtls", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_SafekeepingAccount2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_SafekeepingAccount2Rule", "X00159", "Invalid message content for CorporateActionOptionDetails.", new String[]{"CorporateActionNotification002V15[*]"}, AmountDetails_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(IdentificationCode_GENR_Constrain).build();

    /**
     * X00161 - CorporateActionNotificationV14_OtherEventRule
     * If CorporateActionGeneralInformation/EventType/Code is OTHR (Other) then at least one occurrence of AdditionalInformation/AdditionalText/AdditionalInformation must be present.
     * @since 20240722
     * */
    private static final IConstrain EventType_Code_OTHR_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd", "OTHR", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition AddtlTxt_AddtlInf_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("AddtlInf/AddtlTxt/AddtlInf", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_OtherEventRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_OtherEventRule", "X00161", "Invalid message content for AdditionalInformation. AdditionalInformation/additionalText must be present.", new String[]{"CorporateActionNotification002V15[*]"}, AddtlTxt_AddtlInf_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(EventType_Code_OTHR_Constrain).build();

    /**
     * X00162 - CorporateActionNotificationV14_IntermediateSecurity1Rule
     * If CorporateActionGeneralInformation/EventType/Code is RHDI (IntermediateSecuritiesDistribution), then IntermediateSecurity must be absent.
     * @since 20240722
     * */
    private static final IConstrain EventType_Code_RHDI_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd", "RHDI", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition IntermediateSecurity_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("IntrmdtScty", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_IntermediateSecurity1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_IntermediateSecurity1Rule", "X00162", "Invalid message contents for IntermediateSecurity. Must be absent.", new String[]{"CorporateActionNotification002V15[*]"}, IntermediateSecurity_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(EventType_Code_RHDI_Constrain).build();

    /**
     * X00163 - CorporateActionNotificationV14_IntermediateSecurity2Rule
     * If CorporateActionGeneralInformation/EventType/Code is RHTS (Rights Issue), then IntermediateSecurity must be present.
     * @since 20240722
     * */
    private static final IConstrain EventType_Code_RHTS_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd", "RHTS", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition IntermediateSecurity_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("IntrmdtScty", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_IntermediateSecurity2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_IntermediateSecurity2Rule", "X00163", "Invalid message contents for IntermediateSecurity. Must be absent.", new String[]{"CorporateActionNotification002V15[*]"}, IntermediateSecurity_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(EventType_Code_RHTS_Constrain).build();

    /**
     * X00164 - CorporateActionNotificationV14_NotificationIdentificationRule
     * If NotificationGeneralInformation/NotificationType is REPL (Replace) or RMDR (Reminder) then PreviousNotificationIdentification must be present
     * @since 20240722
     * */
    private static final Precondition Seev_031_EventType_Code_REPL_RMDR = new ISOPathValuePrecondition("/TlkitEnvlp/BizMsgEnvlp/Document/CorpActnNtfctn/NtfctnGnlInf/NtfctnTp", new String[]{"REPL","RMDR"});
    private static final ISOConstrainCondition PreviousNotificationIdentification_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("PrvsNtfctnId", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_NotificationIdentificationRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_NotificationIdentificationRule", "X00164", "Invalid message contents for NotificationIdentification", new String[]{"CorporateActionNotification002V15[*]"}, PreviousNotificationIdentification_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15,Seev_031_EventType_Code_REPL_RMDR})).build();

    /**
     * X00166 - CorporateActionNotificationV14_IntermediateSecuritiesDistribution1Rule
     * If CorporateActionGeneralInformation/EventType/Code is RHDI (IntermediateSecuritiesDistribution), then CorporateActionDetails/IntermediateSecuritiesDistributionType must be present.
     * @since 20240722
     * */
    private static final ISOConstrainCondition IntermediateSecuritiesDistributionType_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/IntrmdtSctiesDstrbtnTp", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_IntermediateSecuritiesDistribution1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_IntermediateSecuritiesDistribution1Rule", "X00166", "Invalid message contents for IntermediateSecuritiesDistributionType in CorporateActionDetails. Element must be present.", new String[]{"CorporateActionNotification002V15[*]"}, IntermediateSecuritiesDistributionType_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(EventType_Code_RHDI_Constrain).build();

    /**
     * X00301 - CorporateActionNotificationV14_InformationEventRule
     * If CorporateActionGeneralInformation/EventType/Code is INFO (Information), then IntermediateSecurity must be absent and no occurrences of CorporateActionOptionDetails may be present.
     * @since 20240722
     * */
    private static final IConstrain EventType_Code_INFO_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd", "INFO", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition IntermediateSecurity_CorporateActionOptionDetails_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain(new String[]{"CorpActnOptnDtls","IntrmdtScty"}, ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_InformationEventRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_InformationEventRule", "X00301", "Invalid message contents for IntermediateSecurity or CorporateActionOptionDetails. Must both be absent.", new String[]{"CorporateActionNotification002V15[*]"}, IntermediateSecurity_CorporateActionOptionDetails_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(EventType_Code_INFO_Constrain).build();

    /**
     * X00302 - CorporateActionNotificationV14_DeclaredRateRule
     * If CorporateActionOptionDetails/RateAndAmountDetails/IssuerDeclaredExchangeRate is present, then CorporateActionDetails/RateAndAmountDetails/DeclaredRate must be present.
     * @since 20240722
     * */
    private static final IConstrain IssuerDeclaredExchangeRate_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/RateAndAmtDtls/IssrDclrdXchgRate", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition DeclaredRate_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/RateAndAmtDtls/DclrdRate", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_DeclaredRateRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_DeclaredRateRule", "X00302", "Invalid message contents for DeclaredRate. It must be present.", new String[]{"CorporateActionNotification002V15[*]"}, DeclaredRate_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(IssuerDeclaredExchangeRate_Present_Constrain).build();

    /**
     * X00524 - CorporateActionNotificationV14_FirstBidIncrementPrice1Rule
     * If FirstBidIncrementPrice is present in CorporateActionDetails/PriceDetails, then FirstBidIncrementPrice is not allowed in any occurences of CorporateActionOptionDetails/PriceDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionDetails_FirstBidIncrementPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnDtls/PricDtls/FrstBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionOptionDetails_FirstBidIncrementPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/FrstBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_FirstBidIncrementPrice1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_FirstBidIncrementPrice1Rule", "X00524", "Invalid message contents for FirstBidIncrementPrice in one or more occurrences of CorporateActionOptiondetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionOptionDetails_FirstBidIncrementPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_FirstBidIncrementPrice_Present_Constrain).build();

    /**
     * X00525 - CorporateActionNotificationV14_FirstBidIncrementPrice2Rule
     * If FirstBidIncrementPrice is present in any occurrences of CorporateActionOptionDetails/PriceDetails, then FirstBidIncrementPrice is not allowed in CorporateActionDetails/PriceDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_FirstBidIncrementPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/FrstBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_FirstBidIncrementPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/PricDtls/FrstBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_FirstBidIncrementPrice2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_FirstBidIncrementPrice2Rule", "X00525", "Invalid message contents for FirstBidIncrementPrice in CorporateActionDetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_FirstBidIncrementPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_FirstBidIncrementPrice_Present_Constrain).build();

    /**
     * X00526 - CorporateActionNotificationV14_LastBidIncrementPrice1Rule
     * If LastBidIncrementPrice is present in CorporateActionDetails/PriceDetails, then LastBidIncrementPrice is not allowed in any occurences of CorporateActionOptionDetails/PriceDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionDetails_LastBidIncrementPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnDtls/PricDtls/LastBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionOptionDetails_LastBidIncrementPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/LastBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_LastBidIncrementPrice1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_LastBidIncrementPrice1Rule", "X00526", "Invalid message contents for LastBidIncrementPrice in one or more occurrences of CorporateActionOptiondetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionOptionDetails_LastBidIncrementPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_LastBidIncrementPrice_Present_Constrain).build();

    /**
     * X00527 - CorporateActionNotificationV14_LastBidIncrementPrice2Rule
     * If LastBidIncrementPrice is present in any occurrences of CorporateActionOptionDetails/PriceDetails, then LastBidIncrementPrice is not allowed in CorporateActionDetails/PriceDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_LastBidIncrementPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/LastBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_LastBidIncrementPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/PricDtls/LastBidIncrmtPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_LastBidIncrementPrice2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_LastBidIncrementPrice2Rule", "X00527", "Invalid message contents for LastBidIncrementPrice in CorporateActionDetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_LastBidIncrementPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_LastBidIncrementPrice_Present_Constrain).build();

    /**
     * X00528 - CorporateActionNotificationV14_FirstBidIncrementPriceEventType1Rule
     * If FirstBidIncrementPrice is present in CorporateActionDetails/PriceDetails, then CorporateActionGeneralInformation/EventType/Code value must be equal to either BIDS (Repurchase Offer) or DTCH (Dutch Auction) or TEND (Tender).
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain EventType_Code_BIDS_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd","BIDS", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOUnitaryConstrain EventType_Code_DTCH_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd","DTCH", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOUnitaryConstrain EventType_Code_TEND_Constrain = new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd","TEND", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition BIDS_Or_DTCH_Or_TEND_ConstrainCondition = new ISOConstrainCondition(EventType_Code_BIDS_Constrain).or(EventType_Code_DTCH_Constrain).or(EventType_Code_TEND_Constrain);
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_FirstBidIncrementPriceEventType1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_FirstBidIncrementPriceEventType1Rule", "X00528", "Invalid message contents for EventType code in CorporateActionGeneralInformation since FirstBidIncrementPrice is present.", new String[]{"CorporateActionNotification002V15[*]"}, BIDS_Or_DTCH_Or_TEND_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_FirstBidIncrementPrice_Present_Constrain).build();

    /**
     * X00529 - CorporateActionNotificationV14_LastBidIncrementPriceEventType1Rule
     * If LastBidIncrementPrice is present in CorporateActionDetails/PriceDetails, then CorporateActionGeneralInformation/EventType/Code value must be equal to either BIDS (Repurchase Offer) or DTCH (Dutch Auction) or TEND (Tender).
     * @since 20240722
     * */
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_LastBidIncrementPriceEventType1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_LastBidIncrementPriceEventType1Rule", "X00529", "Invalid message contents for EventType code in CorporateActionGeneralInformation since LastBidIncrementPrice is present.", new String[]{"CorporateActionNotification002V15[*]"}, BIDS_Or_DTCH_Or_TEND_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_LastBidIncrementPrice_Present_Constrain).build();

    /**
     * X00530 - CorporateActionNotificationV14_FirstBidIncrementPriceEventType2Rule
     * If FirstBidIncrementPrice is present in any occurrence of CorporateActionOptionDetails/PriceDetails, then CorporateActionGeneralInformation/EventType/Code value must be equal to either BIDS (Repurchase Offer) or DTCH (Dutch Auction) or TEND (Tender).
     * @since 20240722
     * */
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_FirstBidIncrementPriceEventType2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_FirstBidIncrementPriceEventType2Rule", "X00530", "Invalid message contents for EventType code in CorporateActionGeneralInformation since FirstBidIncrementPrice is present.", new String[]{"CorporateActionNotification002V15[*]"}, BIDS_Or_DTCH_Or_TEND_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_FirstBidIncrementPrice_Present_Constrain).build();

    /**
     * X00531 - CorporateActionNotificationV14_LastBidIncrementPriceEventType2Rule
     * If LastBidIncrementPrice is present in any occurrence of CorporateActionOptionDetails/PriceDetails, then CorporateActionGeneralInformation/EventType/Code value must be equal to either BIDS (Repurchase Offer) or DTCH (Dutch Auction) or TEND (Tender).
     * @since 20240722
     * */
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_LastBidIncrementPriceEventType2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_LastBidIncrementPriceEventType2Rule", "X00531", "Invalid message contents for EventType code in CorporateActionGeneralInformation since LastBidIncrementPrice is present.", new String[]{"CorporateActionNotification002V15[*]"}, BIDS_Or_DTCH_Or_TEND_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_LastBidIncrementPrice_Present_Constrain).build();

    /**
     * X00532 - CorporateActionNotificationV14_MinimumPrice1Rule
     * If MinimumPrice is present in CorporateActionDetails/PriceDetails, then MinimumPrice is not allowed in any occurences of CorporateActionOptionDetails/PriceDetails
     * @since 20240722
     * */
    private static final IConstrain CorporateActionDetails_MinimumPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnDtls/PricDtls/MinPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionOptionDetails_MinimumPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/MinPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_MinimumPrice1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_MinimumPrice1Rule", "X00532", "Invalid message contents for MinimumPrice in one or more occurrences of CorporateActionOptiondetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionOptionDetails_MinimumPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_MinimumPrice_Present_Constrain).build();

    /**
     * X00533 - CorporateActionNotificationV14_MinimumPrice2Rule
     * If MinimumPrice is present in any occurrences of CorporateActionOptionDetails/PriceDetails, then MinimumPrice is not allowed in CorporateActionDetails/PriceDetails
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_MinimumPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/MinPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_MinimumPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/PricDtls/MinPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_MinimumPrice2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_MinimumPrice2Rule", "X00533", "Invalid message contents for MinimumPrice in CorporateActionDetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_MinimumPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_MinimumPrice_Present_Constrain).build();

    /**
     * X00534 - CorporateActionNotificationV14_MaximumPrice1Rule
     * If MaximumPrice is present in CorporateActionDetails/PriceDetails, then MaximumPrice is not allowed in any occurences of CorporateActionOptionDetails/PriceDetails
     * @since 20240722
     * */
    private static final IConstrain CorporateActionDetails_MaximumPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnDtls/PricDtls/MaxPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionOptionDetails_MaximumPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/MaxPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_MaximumPrice1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_MaximumPrice1Rule", "X00534", "Invalid message contents for MaximumPrice in one or more occurrences of CorporateActionOptiondetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionOptionDetails_MaximumPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_MaximumPrice_Present_Constrain).build();

    /**
     * X00535 - CorporateActionNotificationV14_MaximumPrice2Rule
     * If MaximumPrice is present in any occurrences of CorporateActionOptionDetails/PriceDetails, then MaximumPrice is not allowed in CorporateActionDetails/PriceDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_MaximumPrice_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/PricDtls/MaxPric", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_MaximumPrice_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/PricDtls/MaxPric", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_MaximumPrice2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_MaximumPrice2Rule", "X00535", "Invalid message contents for MaximumPrice in CorporateActionDetails/PriceDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_MaximumPrice_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_MaximumPrice_Present_Constrain).build();

    /**
     * X00536 - CorporateActionNotificationV14_BidInterval1Rule
     * If BidInterval is present in CorporateActionDetails/RateAndAmountDetails, then BidInterval is not allowed in any occurences of CorporateActionOptionDetails/RateAndAmountDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionDetails_BidInterval_Present_Constrain = new ISOUnitaryConstrain("CorpActnDtls/RateAndAmtDtls/BidIntrvl", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionOptionDetails_BidInterval_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnOptnDtls/RateAndAmtDtls/BidIntrvl", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_BidInterval1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_BidInterval1Rule", "X00536", "Invalid message contents for BidInterval in one or more occurrences of CorporateActionOptionDetails/RateAndAmountDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionOptionDetails_BidInterval_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionDetails_BidInterval_Present_Constrain).build();

    /**
     * X00537 - CorporateActionNotificationV14_BidInterval2Rule
     * If BidInterval is present in any occurrences of CorporateActionOptionDetails/RateAndAmountDetails, then BidInterval is not allowed in CorporateActionDetails/RateAndAmountDetails.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_BidInterval_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/RateAndAmtDtls/BidIntrvl", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_BidInterval_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/RateAndAmtDtls/BidIntrvl", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_BidInterval2Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_BidInterval2Rule", "X00537", "Invalid message contents for BidInterval in CorporateActionDetails/RateAndAmountDetails. Element is not allowed.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_BidInterval_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_BidInterval_Present_Constrain).build();

    /**
     * X00487 - CorporateActionNotificationV14_BeneficialOwnerBreakdownRequestRule
     * If in at least one occurrence of CorporateActionOptionDetails, OptionType/Code value is BOBD (BeneficialOwnerBreakdownRequest), then CorporateActionGeneralInformation/EventType/Code value must be WTRC (Withholding Tax Relief Certification)
     * @since 20240722
     * */
    private static final IConstrain OptionType_Code_BOBD_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/OptnTp/Cd", "BOBD", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);
    private static final ISOConstrainCondition EventType_Code_WTRC_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd","WTRC", ISOUnitaryConstrain.ConstrainMode.ISEQUAL));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_BeneficialOwnerBreakdownRequestRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_BeneficialOwnerBreakdownRequestRule", "X00487", "Invalid message contents for CorporateActionGeneralInformation/EventType/Code. It must be WTRC.", new String[]{"CorporateActionNotification002V15[*]"}, EventType_Code_WTRC_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(OptionType_Code_BOBD_Constrain).build();

    /**
     * X00543 - CorporateActionNotificationV14_DissenterRights3Rule
     * If OfferType/Code value is equal to NDIS (DissenterRightsNotApplicable) or DISS (DissenterRights) in any occurrence of CorporateActionOptionDetails, then in CorporateActionDetails, OfferType/Code values NDIS (DissenterRightsNotApplicable) and DISS (DissenterRights) are not allowed
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain CorpActnOptnDtls_OfferType_Code_NDIS_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/OfferTp/Cd", "NDIS", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);  // algun NDIS
    private static final ISOUnitaryConstrain CorpActnOptnDtls_OfferType_Code_DISS_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/OfferTp/Cd","DISS", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);  // algun DISS
    private static final ISOConstrains CorpActnOptnDtls_OfferType_Code_DISS_NDIS_Constrain = new ISOConstrains(CorpActnOptnDtls_OfferType_Code_NDIS_Constrain).or(CorpActnOptnDtls_OfferType_Code_DISS_Constrain);

    private static final ISOUnitaryConstrain CorpActnDtls_OfferType_Code_Not_NDIS_Constrain = new ISOUnitaryConstrain("CorpActnDtls/OfferTp/Cd","NDIS", ISOUnitaryConstrain.ConstrainMode.ISNOTEQUAL);  // cap NDIS
    private static final ISOUnitaryConstrain CorpActnDtls_OfferType_Code_Not_DISS_Constrain = new ISOUnitaryConstrain("CorpActnDtls/OfferTp/Cd","DISS", ISOUnitaryConstrain.ConstrainMode.ISNOTEQUAL);  // cap DISS
    private static final ISOConstrainCondition CorpActnDtls_OfferType_Code_Not_NDIS_ConstrainCondition = new ISOConstrainCondition(CorpActnDtls_OfferType_Code_Not_NDIS_Constrain);
    private static final ISOConstrainCondition CorpActnDtls_OfferType_Code_Not_DISS_ConstrainCondition = new ISOConstrainCondition(CorpActnDtls_OfferType_Code_Not_DISS_Constrain);

    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_DissenterRights3Rule_1 = new ISOComplexTypeRule.Builder(
            "Seev_031_CorporateActionNotificationV14_DissenterRights3Rule_1", "X00543",
            "Invalid message contents for OfferType. NDIS or DISS code values cannot be present in both CorporateActionDetails and Option sequences.",
            new String[]{"CorporateActionNotification002V15[*]"}, CorpActnDtls_OfferType_Code_Not_NDIS_ConstrainCondition)
            .precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15}))
            .constrains(CorpActnOptnDtls_OfferType_Code_DISS_NDIS_Constrain).build();

    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_DissenterRights3Rule_2 = new ISOComplexTypeRule.Builder(
            "Seev_031_CorporateActionNotificationV14_DissenterRights3Rule_2", "X00543",
            "Invalid message contents for OfferType. NDIS or DISS code values cannot be present in both CorporateActionDetails and Option sequences.",
            new String[]{"CorporateActionNotification002V15[*]"}, CorpActnDtls_OfferType_Code_Not_DISS_ConstrainCondition)
            .precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15}))
            .constrains(CorpActnOptnDtls_OfferType_Code_DISS_NDIS_Constrain).build();

    /**
     * X00538 - CorporateActionNotificationV14_IncentivePremiumEventType1Rule
     * If Code value is equal to INCP (IncentivePremiumPayment) in any occurrence of CorporateActionDetails/AdditionalBusinessProcessIndicator, then CorporateActionGeneralInformation/EventType/Code value must be equal to CONS (Consent)
     * @since 20240722
     * */
    private static final IConstrain AdditionalBusinessProcessIndicator_Code_INCP_Constrain = new ISOUnitaryConstrain("CorpActnDtls/AddtlBizPrcInd/Cd", "INCP", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);
    private static final ISOConstrainCondition EventType_Code_CONS_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnGnlInf/EvtTp/Cd","CONS", ISOUnitaryConstrain.ConstrainMode.ISEQUAL));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_IncentivePremiumEventType1Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_IncentivePremiumEventType1Rule", "X00538", "Invalid message contents for EventType code in CorporateActionGeneralInformation since an Incentive Premium Payment is processed.", new String[]{"CorporateActionNotification002V15[*]"}, EventType_Code_CONS_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(AdditionalBusinessProcessIndicator_Code_INCP_Constrain).build();

    /**
     * X00168 - CorporateActionNotificationV14_PaymentDateRule
     * If at least one occurrence of CorporateActionOptionDetails/SecuritiesMovementDetails or at least one occurrence of CorporateActionOptionDetails/CashMovementDetails is present, then CorporateActionDetails/DateDetails/PaymentDate must be absent.
     * @since 20240722
     * */
    private static final IConstrain CorporateActionOptionDetails_SecuritiesMovementDetails_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/SctiesMvmntDtls", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final IConstrain CorporateActionOptionDetails_CashMovementDetails_Present_Constrain = new ISOUnitaryConstrain("CorpActnOptnDtls/CshMvmntDtls", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition CorporateActionDetails_PaymentDate_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("CorpActnDtls/DtDtls/PmtDt", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_PaymentDateRule_1 = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_PaymentDateRule_1", "X00168", "Invalid message contents for PaymentDate. It should be absent.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_PaymentDate_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_SecuritiesMovementDetails_Present_Constrain).build();
    public static final ISOComplexTypeRule Seev_031_CorporateActionNotificationV14_PaymentDateRule_2 = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionNotificationV14_PaymentDateRule_2", "X00168", "Invalid message contents for PaymentDate. It should be absent.", new String[]{"CorporateActionNotification002V15[*]"}, CorporateActionDetails_PaymentDate_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOptionDetails_CashMovementDetails_Present_Constrain).build();

    /**
     * X00192 - SecurityIdentification19_DescriptionPresenceRule
     * If Description is not present then either ISIN or at least one occurrence of OtherIdentification must be present.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain Description_Absent_Constrain = new ISOUnitaryConstrain("Desc", ISOUnitaryConstrain.ConstrainMode.ISABSENT);
    private static final ISOUnitaryConstrain ISIN_Present_Constrain = new ISOUnitaryConstrain("ISIN", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOUnitaryConstrain OthrId_Present_Constrain = new ISOUnitaryConstrain("OthrId", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition ISIN_OtherId_Present_ConstrainCondition = new ISOConstrainCondition(ISIN_Present_Constrain).or(OthrId_Present_Constrain);
    private static final ISOUnitaryConstrain OtherId_Present_Constrain = new ISOUnitaryConstrain("OthrId", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    public static final ISOComplexTypeRule Seev_031_SecurityIdentification19_DescriptionPresenceRule = new ISOComplexTypeRule.Builder("Seev_031_SecurityIdentification19_DescriptionPresenceRule", "X00192", "At least one identification must be present.", new String[]{"SecurityIdentification20[*]"}, ISIN_OtherId_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(Description_Absent_Constrain).build();

    /**
     * X00193 - SecurityIdentification19_OtherIdentificationPresenceRule
     * If OtherIdentification is not present then either ISIN or Description must be present.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain OtherId_Absent_Constrain = new ISOUnitaryConstrain("OthrId", ISOUnitaryConstrain.ConstrainMode.ISABSENT);
    private static final ISOUnitaryConstrain Description_Present_Constrain = new ISOUnitaryConstrain("Desc", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition ISIN_Description_Present_ConstrainCondition = new ISOConstrainCondition(ISIN_Present_Constrain).or(Description_Present_Constrain);
    public static final ISOComplexTypeRule Seev_031_SecurityIdentification19_OtherIdentificationPresenceRule = new ISOComplexTypeRule.Builder("Seev_031_SecurityIdentification19_OtherIdentificationPresenceRule", "X00193", "At least one identification must be present.", new String[]{"SecurityIdentification20[*]"}, ISIN_Description_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(OtherId_Absent_Constrain).build();

    /**
     * X00194 - SecurityIdentification19_ISINPresenceRule
     * If ISIN is not present then either Description or at least one occurrence of OtherIdentification must be present.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain ISIN_Absent_Constrain = new ISOUnitaryConstrain("ISIN", ISOUnitaryConstrain.ConstrainMode.ISABSENT);
    private static final ISOConstrainCondition OtherId_Description_Present_ConstrainCondition = new ISOConstrainCondition(OthrId_Present_Constrain).or(Description_Present_Constrain);
    public static final ISOComplexTypeRule Seev_031_SecurityIdentification19_ISINPresenceRule = new ISOComplexTypeRule.Builder("Seev_031_SecurityIdentification19_ISINPresenceRule", "X00194", "At least one identification must be present.", new String[]{"SecurityIdentification20[*]"}, OtherId_Description_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(ISIN_Absent_Constrain).build();

    /**
     * X00551 - AccountAndBalance55_SafekeepingAccountOrBlockChainAddress1Rule
     * Either SafekeepingAccount or BlockChainAddressOrWallet must be present but not both.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain BlockChainAddressOrWallet_Present_Constrain = new ISOUnitaryConstrain("BlckChainAdrOrWllt", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition SafekeepingAccount_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("SfkpgAcct", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    private static final ISOUnitaryConstrain SafekeepingAccount_Present_Constrain = new ISOUnitaryConstrain("SfkpgAcct", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    public static final ISOComplexTypeRule Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress1Rule = new ISOComplexTypeRule.Builder("Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress1Rule", "X00551", "Invalid message contents for SafekeepingAccount or BlockchainAddressOrWallet. Only one of both must be present.", new String[]{"AccountAndBalance65[*]"}, new ISOConstrainCondition(BlockChainAddressOrWallet_Present_Constrain).or(SafekeepingAccount_Present_Constrain)).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).build();

    /**
     * X00565 - AccountAndBalance55_SafekeepingAccountOrBlockChainAddress2Rule
     * If BlockChainAddressOrWallet is present then SafekeepingAccount must be absent.
     * @since 20240722
     * */
    public static final ISOComplexTypeRule Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress2Rule = new ISOComplexTypeRule.Builder("Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress2Rule", "X00565", "Invalid message contents for SafekeepingAccount or BlockchainAddressOrWallet. Only one of both must be present.", new String[]{"AccountAndBalance65[*]"}, SafekeepingAccount_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(BlockChainAddressOrWallet_Present_Constrain).build();

    /**
     * X00566 - AccountAndBalance55_SafekeepingAccountOrBlockChainAddress3Rule
     * If SafekeepingAccount is present, then BlockChainAddressOrWallet must be absent.
     * @since 20240722
     * */
    private static final ISOConstrainCondition BlockChainAddressOrWallet_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("BlckChainAdrOrWllt", ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress3Rule = new ISOComplexTypeRule.Builder("Seev_031_AccountAndBalance55_SafekeepingAccountOrBlockChainAddress3Rule", "X00566", "Invalid message contents for SafekeepingAccount or BlockchainAddressOrWallet. Only one of both must be present.", new String[]{"AccountAndBalance65[*]"}, BlockChainAddressOrWallet_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(SafekeepingAccount_Present_Constrain).build();

    /**
     * X00541 - DissenterRights1Rule
     * If OfferType/Code value is equal to DISS (DissenterRights), then OfferType/Code value equal to NDIS (DissenterRightsNotApplicable) is not allowed in any occurrence of OfferType.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain OfferType_Code_DISS_Constrain = new ISOUnitaryConstrain("OfferTp/Cd", "DISS", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);  //almenys un és DISS
    private static final ISOUnitaryConstrain OfferType_Code_Not_NDIS_Constrain = new ISOUnitaryConstrain("OfferTp/Cd", "NDIS", ISOUnitaryConstrain.ConstrainMode.ISNOTEQUAL); // cap d'ells és NDIS
    private static final ISOConstrainCondition OfferType_Code_NDIS_NotEqual_ConstrainCondition = new ISOConstrainCondition(OfferType_Code_Not_NDIS_Constrain); // cap d'ells és NDIS

    public static final ISOComplexTypeRule Seev_031_DissenterRights1Rule = new ISOComplexTypeRule.Builder("Seev_031_DissenterRights1Rule", "X00541", "Invalid message contents for OfferType. Either code value DISS or NDISS is allowed, not both.", new String[]{"CorporateAction87[*]", "CorporateActionOption247[*]"}, OfferType_Code_NDIS_NotEqual_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(OfferType_Code_DISS_Constrain).build();

    /**
     * X00562 - DissenterRights2Rule
     * If OfferType/Code value is equal to NDIS (DissenterRightsNotApplicable), then OfferType/Code value equal to DISS (DissenterRights) is not allowed in any occurrence of OfferType.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain OfferType_Code_NDIS_Constrain = new ISOUnitaryConstrain("OfferTp/Cd", "NDIS", ISOUnitaryConstrain.ConstrainMode.ISATLEASTONEEQUAL);  //almenys un és NDIS
    private static final ISOUnitaryConstrain OfferType_Code_Not_DISS_Constrain = new ISOUnitaryConstrain("OfferTp/Cd", "DISS", ISOUnitaryConstrain.ConstrainMode.ISNOTEQUAL); // cap d'ells és DISS
    private static final ISOConstrainCondition OfferType_Code_DISS_NotEqual_ConstrainCondition = new ISOConstrainCondition(OfferType_Code_Not_DISS_Constrain); // cap d'ells és DISS

    public static final ISOComplexTypeRule Seev_031_DissenterRights2Rule = new ISOComplexTypeRule.Builder("Seev_031_DissenterRights2Rule", "X00562", "Invalid message contents for OfferType. Either code value DISS or NDISS is allowed, not both.", new String[]{"CorporateAction87[*]", "CorporateActionOption247[*]"}, OfferType_Code_DISS_NotEqual_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(OfferType_Code_NDIS_Constrain).build();

    /**
     * X00176 - CorporateActionOption224_AdditionalTextRule
     * If OptionType/Code is OTHR (Other), then at least one occurrence of AdditionalInformation/AdditionalText/AdditionalInformation must be present
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain CorporateActionOption_OptionType_Code_OTHR_Constrain = new ISOUnitaryConstrain("OptnTp/Cd", "OTHR", ISOUnitaryConstrain.ConstrainMode.ISEQUAL);
    private static final ISOConstrainCondition CorporateActionOption_AdditionalInformation_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("AddtlInf/AddtlTxt/AddtlInf", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionOption224_AdditionalTextRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionOption224_AdditionalTextRule", "X00176", "Invalid message content for AdditionalInformation. AdditionalInformation/additionalText must be present.", new String[]{"CorporateActionOption247[*]"}, CorporateActionOption_AdditionalInformation_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOption_OptionType_Code_OTHR_Constrain).build();

    /**
     * X00417 - CorporateActionOption224_InstructCashAmountRule
     * If OptionFeatures/Code QCAS (InstructCashAmount) is not present, then any message elements in the list (PriceDetails/MaximumCashToInstruct, PriceDetails/MinimumCashToInstruct, PriceDetails/MinimumMutipleCashToInstruct) must not be present.
     * @since 20240722
     * */
    private static final ISOUnitaryConstrain CorporateActionOption_OptnFeatrs_Code_NOT_QCAS_Constrain = new ISOUnitaryConstrain("OptnFeatrs/Cd", "QCAS", ISOUnitaryConstrain.ConstrainMode.ISNOTEQUAL);
    private static final ISOConstrainCondition CorporateActionOption_PricDtls_Absent_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain(new String[]{"PricDtls/MaxCshToInst", "PricDtls/MinCshToInst", "PricDtls/MinMltplCshToInst"}, ISOUnitaryConstrain.ConstrainMode.ISABSENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionOption224_InstructCashAmountRule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionOption224_InstructCashAmountRule", "X00417", "Invalid message content for OptionFeatures QCAS (Instruct Cash Amount)", new String[]{"CorporateActionOption247[*]"}, CorporateActionOption_PricDtls_Absent_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(CorporateActionOption_OptnFeatrs_Code_NOT_QCAS_Constrain).build();

    /**
     * X00385 - CorporateActionRate104_SecondLevelTax3Rule
     * If at least one occurrence of SecondLevelTax is present, then at least one occurrence of WithholdingTaxRate must be present.
     * @since 20240722
     **/
    private static final ISOUnitaryConstrain SecondLevelTax_Present_Constrain = new ISOUnitaryConstrain("ScndLvlTax", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition WithholdingTaxRate_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("WhldgTaxRate", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CorporateActionRate104_SecondLevelTax3Rule = new ISOComplexTypeRule.Builder("Seev_031_CorporateActionRate104_SecondLevelTax3Rule", "X00385", "Invalid message contents for WithholdingTaxRate. It should be present.", new String[]{"CorporateActionRate136[*]"}, WithholdingTaxRate_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(SecondLevelTax_Present_Constrain).build();

    /**
     * X00544 - SecuritiesOption102_IncomeType1Rule
     * If at least one occurrence of OtherIncomeType is present, then IncomeType must be present.
     * @since 20240722
     **/
    private static final ISOUnitaryConstrain Securities_OtherIncomeType_Present_Constrain = new ISOUnitaryConstrain("OthrIncmTp", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition Securities_IncomeType_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("IncmTp", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_SecuritiesOption102_IncomeType1Rule = new ISOComplexTypeRule.Builder("Seev_031_SecuritiesOption102_IncomeType1Rule", "X00544", "Invalid message contents for IncomeType. Element must be present.", new String[]{"SecuritiesOption117[*]"}, Securities_IncomeType_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(Securities_OtherIncomeType_Present_Constrain).build();

    /**
     * X00545 - CashOption96_IncomeType2Rule
     * If at least one occurrence of OtherIncomeType is present, then IncomeType must be present.
     * @since 20240722
     **/
    private static final ISOUnitaryConstrain Cash_OtherIncomeType_Present_Constrain = new ISOUnitaryConstrain("OthrIncmTp", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition Cash_IncomeType_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("IncmTp", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_CashOption96_IncomeType2Rule = new ISOComplexTypeRule.Builder("Seev_031_CashOption96_IncomeType2Rule", "X00545", "Invalid message contents for IncomeType. Element must be present.", new String[]{"CashOption113[*]"}, Cash_IncomeType_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(Cash_OtherIncomeType_Present_Constrain).build();

    /**
     * X00388 - Rate36_SecondLevelTax3Rule
     * If at least one occurrence of SecondLevelTax is present, then at least one occurrence of WithholdingTaxRate must be present.
     * @since 20240722
     **/
    private static final ISOUnitaryConstrain Cash_SecondLevelTax_Present_Constrain = new ISOUnitaryConstrain("ScndLvlTax", ISOUnitaryConstrain.ConstrainMode.ISPRESENT);
    private static final ISOConstrainCondition Cash_WithholdingTaxRate_Present_ConstrainCondition = new ISOConstrainCondition(new ISOUnitaryConstrain("WhldgTaxRate", ISOUnitaryConstrain.ConstrainMode.ISPRESENT));
    public static final ISOComplexTypeRule Seev_031_Rate36_SecondLevelTax3Rule = new ISOComplexTypeRule.Builder("Seev_031_Rate36_SecondLevelTax3Rule", "X00388", "Invalid message contents for WithholdingTaxRate. It should be present.", new String[]{"Rate47[*]"}, Cash_WithholdingTaxRate_Present_ConstrainCondition).precondition(Precondition.and(new Precondition[]{ISOToolkitEnvelopeNamespacePrecondition, IdentifierPrecondition_seev_031_002_15})).constrains(Cash_SecondLevelTax_Present_Constrain).build();
}
