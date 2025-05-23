package cdm.observable.asset;

import cdm.observable.asset.CreditRatingAgencyEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import com.rosetta.model.lib.annotations.RosettaSynonym;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Represents the enumerated values to specify the rating agencies.
 * @version ${project.version}
 */
@RosettaEnum("CreditRatingAgencyEnum")
public enum CreditRatingAgencyEnum {

	/**
	 * A. M. Best
	 */
	@RosettaEnumValue(value = "AMBest") 
	AM_BEST("AMBest", null),
	
	/**
	 * Canadian Bond Rating Service
	 */
	@RosettaEnumValue(value = "CBRS") 
	CBRS("CBRS", null),
	
	/**
	 * Dominion Bond Rating Service
	 */
	@RosettaSynonym(value = "DBRS", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "DBRS") 
	DBRS("DBRS", null),
	
	/**
	 * Fitch
	 */
	@RosettaSynonym(value = "FITCH", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "Fitch") 
	FITCH("Fitch", null),
	
	/**
	 * Japan Credit Rating Agency, Ltd.
	 */
	@RosettaEnumValue(value = "Japanagency") 
	JAPANAGENCY("Japanagency", null),
	
	/**
	 * Moody&#39;s
	 */
	@RosettaSynonym(value = "MOODYS", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "Moodys") 
	MOODYS("Moodys", null),
	
	/**
	 * Rating And Investment Information, Inc.
	 */
	@RosettaEnumValue(value = "RatingAndInvestmentInformation") 
	RATING_AND_INVESTMENT_INFORMATION("RatingAndInvestmentInformation", null),
	
	/**
	 * Standard And Poor&#39;s
	 */
	@RosettaSynonym(value = "STANDARD_POORS", source = "AcadiaSoft_AM_1_0")
	@RosettaEnumValue(value = "StandardAndPoors") 
	STANDARD_AND_POORS("StandardAndPoors", null)
;
	private static Map<String, CreditRatingAgencyEnum> values;
	static {
        Map<String, CreditRatingAgencyEnum> map = new ConcurrentHashMap<>();
		for (CreditRatingAgencyEnum instance : CreditRatingAgencyEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	CreditRatingAgencyEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static CreditRatingAgencyEnum fromDisplayName(String name) {
		CreditRatingAgencyEnum value = values.get(name);
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
