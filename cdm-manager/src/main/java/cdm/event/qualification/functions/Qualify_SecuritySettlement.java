package cdm.event.qualification.functions;

import cdm.base.math.NonNegativeQuantity;
import cdm.base.math.UnitType;
import cdm.base.staticdata.asset.common.Asset;
import cdm.base.staticdata.asset.common.Instrument;
import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.event.common.BusinessEvent;
import cdm.event.common.TradeState;
import cdm.event.common.Transfer;
import cdm.event.common.TransferState;
import cdm.event.common.functions.FilterCashTransfers;
import cdm.event.common.functions.FilterSecurityTransfers;
import cdm.event.common.functions.TransfersForDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_SecuritySettlement.Qualify_SecuritySettlementDefault.class)
public abstract class Qualify_SecuritySettlement implements RosettaFunction,IQualifyFunctionExtension<BusinessEvent> {
	
	// RosettaFunction dependencies
	//
	@Inject protected FilterCashTransfers filterCashTransfers;
	@Inject protected FilterSecurityTransfers filterSecurityTransfers;
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

	protected abstract MapperC<? extends Transfer> transfers(BusinessEvent businessEvent);

	public static class Qualify_SecuritySettlementDefault extends Qualify_SecuritySettlement {
		@Override
		protected Boolean doEvaluate(BusinessEvent businessEvent) {
			Boolean is_event = null;
			return assignOutput(is_event, businessEvent);
		}
		
		protected Boolean assignOutput(Boolean is_event, BusinessEvent businessEvent) {
			is_event = exists(transfers(businessEvent).<Asset>map("getAsset", transfer -> transfer.getAsset()).<Instrument>map("getInstrument", asset -> asset.getInstrument()).<Security>map("getSecurity", instrument -> instrument.getSecurity())).and(exists(transfers(businessEvent).<NonNegativeQuantity>map("getQuantity", transfer -> transfer.getQuantity()).<UnitType>map("getUnit", nonNegativeQuantity -> nonNegativeQuantity.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()))).and(areEqual(MapperS.of(MapperC.of(filterCashTransfers.evaluate(transfers(businessEvent).getMulti())).get()).<PartyReferencePayerReceiver>map("getPayerReceiver", transfer -> transfer.getPayerReceiver()).<ReferenceWithMetaParty>map("getPayerPartyReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getPayerPartyReference()).<Party>map("Type coercion", referenceWithMetaParty0 -> referenceWithMetaParty0 == null ? null : referenceWithMetaParty0.getValue()), MapperS.of(MapperC.of(filterSecurityTransfers.evaluate(transfers(businessEvent).getMulti())).get()).<PartyReferencePayerReceiver>map("getPayerReceiver", transfer -> transfer.getPayerReceiver()).<ReferenceWithMetaParty>map("getReceiverPartyReference", partyReferencePayerReceiver -> partyReferencePayerReceiver.getReceiverPartyReference()).<Party>map("Type coercion", referenceWithMetaParty1 -> referenceWithMetaParty1 == null ? null : referenceWithMetaParty1.getValue()), CardinalityOperator.All)).get();
			
			return is_event;
		}
		
		@Override
		protected MapperC<? extends Transfer> transfers(BusinessEvent businessEvent) {
			return MapperC.<Transfer>of(transfersForDate.evaluate(MapperS.of(businessEvent).<TradeState>mapC("getAfter", _businessEvent -> _businessEvent.getAfter()).<TransferState>mapC("getTransferHistory", tradeState -> tradeState.getTransferHistory()).<Transfer>map("getTransfer", transferState -> transferState.getTransfer()).getMulti(), MapperS.of(businessEvent).<Date>map("getEventDate", _businessEvent -> _businessEvent.getEventDate()).get()));
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
