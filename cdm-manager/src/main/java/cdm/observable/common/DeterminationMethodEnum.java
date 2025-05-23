package cdm.observable.common;

import cdm.observable.common.DeterminationMethodEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the method according to which an amount or a date is determined.
 * @version ${project.version}
 *
 * Body ISDA
 * Corpus Scheme FpML_Coding_Scheme   
 * schemeLocation "http://www.fpml.org/coding-scheme/determination-method"
 *
 * Provision 
 *
 */
@RosettaEnum("DeterminationMethodEnum")
public enum DeterminationMethodEnum {

	/**
	 * Agreed separately between the parties.
	 */
	@RosettaEnumValue(value = "AgreedInitialPrice") 
	AGREED_INITIAL_PRICE("AgreedInitialPrice", null),
	
	/**
	 * As specified in Master Confirmation.
	 */
	@RosettaEnumValue(value = "AsSpecifiedInMasterConfirmation") 
	AS_SPECIFIED_IN_MASTER_CONFIRMATION("AsSpecifiedInMasterConfirmation", null),
	
	/**
	 * Determined by the Calculation Agent.
	 */
	@RosettaEnumValue(value = "CalculationAgent") 
	CALCULATION_AGENT("CalculationAgent", null),
	
	/**
	 * Official Closing Price.
	 */
	@RosettaEnumValue(value = "ClosingPrice") 
	CLOSING_PRICE("ClosingPrice", null),
	
	/**
	 * Determined by the Currency of Equity Dividends.
	 */
	@RosettaEnumValue(value = "DividendCurrency") 
	DIVIDEND_CURRENCY("DividendCurrency", null),
	
	/**
	 * The initial Index Level is the level of the Expiring Contract as provided in the Master Confirmation.
	 */
	@RosettaEnumValue(value = "ExpiringContractLevel") 
	EXPIRING_CONTRACT_LEVEL("ExpiringContractLevel", null),
	
	/**
	 * Determined by the Hedging Party.
	 */
	@RosettaEnumValue(value = "HedgeExecution") 
	HEDGE_EXECUTION("HedgeExecution", null),
	
	/**
	 * Issuer Payment Currency.
	 */
	@RosettaEnumValue(value = "IssuerPaymentCurrency") 
	ISSUER_PAYMENT_CURRENCY("IssuerPaymentCurrency", null),
	
	/**
	 * Net Asset Value.
	 */
	@RosettaEnumValue(value = "NAV") 
	NAV("NAV", null),
	
	/**
	 * Opening Price of the Market.
	 */
	@RosettaEnumValue(value = "OpenPrice") 
	OPEN_PRICE("OpenPrice", null),
	
	/**
	 * OSP Price.
	 */
	@RosettaEnumValue(value = "OSPPrice") 
	OSP_PRICE("OSPPrice", null),
	
	/**
	 * Settlement Currency.
	 */
	@RosettaEnumValue(value = "SettlementCurrency") 
	SETTLEMENT_CURRENCY("SettlementCurrency", null),
	
	/**
	 * Date on which the strike is determined in respect of a forward starting swap.
	 */
	@RosettaEnumValue(value = "StrikeDateDetermination") 
	STRIKE_DATE_DETERMINATION("StrikeDateDetermination", null),
	
	/**
	 * Official TWAP Price.
	 */
	@RosettaEnumValue(value = "TWAPPrice") 
	TWAP_PRICE("TWAPPrice", null),
	
	/**
	 * Price determined at valuation time.
	 */
	@RosettaEnumValue(value = "ValuationTime") 
	VALUATION_TIME("ValuationTime", null),
	
	/**
	 * Official VWAP Price.
	 */
	@RosettaEnumValue(value = "VWAPPrice") 
	VWAP_PRICE("VWAPPrice", null)
;
	private static Map<String, DeterminationMethodEnum> values;
	static {
        Map<String, DeterminationMethodEnum> map = new ConcurrentHashMap<>();
		for (DeterminationMethodEnum instance : DeterminationMethodEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	DeterminationMethodEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static DeterminationMethodEnum fromDisplayName(String name) {
		DeterminationMethodEnum value = values.get(name);
		if (value == null) {
			throw new IllegalArgumentException("No enum constant with display name \"" + name + "\".");
		}
		return value;
	}

	@Override
	public String toString() {
		return toDisplayString();
	}

	public String toDisplayString() {
		return displayName != null ?  displayName : rosettaName;
	}
}
