package cdm.base.staticdata.asset.common;

import cdm.base.staticdata.asset.common.AssetIdTypeEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Extends product identifiers with additional identifier sources for Assets.
 * @version ${project.version}
 */
@RosettaEnum("AssetIdTypeEnum")
public enum AssetIdTypeEnum {

	/**
	 * Published by Bloomberg, the BBGID is a 12-digit alphanumeric randomly generated ID covering active and non-active securities.
	 */
	@RosettaEnumValue(value = "BBGID") 
	BBGID("BBGID", null),
	
	/**
	 * Published by Bloomberg as a short code to identify publicly trades shares of a particular stock on a specific exchange.
	 */
	@RosettaEnumValue(value = "BBGTICKER") 
	BBGTICKER("BBGTICKER", null),
	
	/**
	 * Derived from the Committee on Uniform Security Identification Procedures, CUSIPs are 9-character identifiers that capture an issue’s important differentiating characteristics for issuers and their financial instruments in the U.S. and Canada.
	 */
	@RosettaEnumValue(value = "CUSIP") 
	CUSIP("CUSIP", null),
	
	/**
	 * Issued under the guidelines of the Object Management Group, the Financial Instrument Global Identifier (FIGI) is a 12 character, alphanumeric, randomly generated ID covering hundreds of millions of active and inactive instruments. The identifier acts as a Uniform Resource Identifier (URI) to link to a set of metadata that uniquely and clearly describes the instrument.
	 */
	@RosettaEnumValue(value = "FIGI") 
	FIGI("FIGI", null),
	
	/**
	 * Issued by the International Swaps Dealers Association as a string representing a Commodity Reference Price used for purposes of determining a relevant price for an underlying commodity in an OTC derivatives contract.
	 */
	@RosettaEnumValue(value = "ISDACRP") 
	ISDACRP("ISDACRP", null),
	
	/**
	 * Issued by The International Securities Identification Number (ISIN) Organization, the ISIN is a 12-character alpha-numerical code used to uniformly identify a security for trading and settlement purposes. Securities with which ISINs can be used include debt securities, such as notes or bonds as well shares, such as common stock or shares of a fund, options, derivatives, and futures. The ISIN structure is defined in ISO 6166.
	 */
	@RosettaEnumValue(value = "ISIN") 
	ISIN("ISIN", null),
	
	/**
	 * The name of the product.
	 */
	@RosettaEnumValue(value = "Name") 
	NAME("Name", null),
	
	/**
	 * Issued by Refinitiv (formerly Reuters), the Reuters Instrument Codes(RIC) uniquely identifies financial instruments, including where they are traded.
	 */
	@RosettaEnumValue(value = "RIC") 
	RIC("RIC", null),
	
	/**
	 * Used when the source is not otherwise in this enumerated list because it is internal or other reasons.  The source can be identified in the scheme which is part of the identifier attribute.
	 */
	@RosettaEnumValue(value = "Other") 
	OTHER("Other", null),
	
	/**
	 * Issued by the French Société Interprofessionnelle pour la Compensation des Valeurs Mobilières (SICOVAM) to identify French securities listed on French stock exchanges.
	 */
	@RosettaEnumValue(value = "Sicovam") 
	SICOVAM("Sicovam", null),
	
	/**
	 * Assigned by the London Stock Exchange, the Stock Exchange Daily Official List (SEDOL) is a list of security identifiers used in the United Kingdom and Ireland for clearing purposes.  SEDOLs serve as the National Securities Identifying Number for all securities issued in the United Kingdom and are therefore part of the security&#39;s ISIN as well.
	 */
	@RosettaEnumValue(value = "SEDOL") 
	SEDOL("SEDOL", null),
	
	/**
	 * Assigned by the Derivatives Service Bureau Ltd (DSB), the Unique Product Identifier (UPI) is a unique code to describe an over-the-counter (OTC) derivatives product.  The UPI is used for identifying the product in transaction reporting data.
	 */
	@RosettaEnumValue(value = "UPI") 
	UPI("UPI", null),
	
	/**
	 * Issued by the Institute for the Issuance and Administration of Securities in Germany (Securities Information), the Wertpapierkennnummer (WKN, WPKN, WPK or simply Wert) consists of six digits or capital letters (excluding I and O), and no check digit. It is used to identify German securities.
	 */
	@RosettaEnumValue(value = "Wertpapier") 
	WERTPAPIER("Wertpapier", null),
	
	/**
	 * Used to identify the currency of a Cash Asset.
	 */
	@RosettaEnumValue(value = "CurrencyCode") 
	CURRENCY_CODE("CurrencyCode", null),
	
	/**
	 * The identifier follows the symbology set by the exchange which lists the asset.
	 */
	@RosettaEnumValue(value = "ExchangeCode") 
	EXCHANGE_CODE("ExchangeCode", null),
	
	/**
	 * The identifier follows the symbology set by the clearing house which clears the asset.
	 */
	@RosettaEnumValue(value = "ClearingCode") 
	CLEARING_CODE("ClearingCode", null)
;
	private static Map<String, AssetIdTypeEnum> values;
	static {
        Map<String, AssetIdTypeEnum> map = new ConcurrentHashMap<>();
		for (AssetIdTypeEnum instance : AssetIdTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	AssetIdTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static AssetIdTypeEnum fromDisplayName(String name) {
		AssetIdTypeEnum value = values.get(name);
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
