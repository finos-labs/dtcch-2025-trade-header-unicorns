package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.LegalAgreementTypeEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the legal agreement type.
 * @version ${project.version}
 */
@RosettaEnum("LegalAgreementTypeEnum")
public enum LegalAgreementTypeEnum {

	/**
	 * A Broker Confirmation.
	 */
	@RosettaEnumValue(value = "BrokerConfirmation") 
	BROKER_CONFIRMATION("BrokerConfirmation", null),
	
	/**
	 * A Transaction Confirmation.
	 */
	@RosettaEnumValue(value = "Confirmation") 
	CONFIRMATION("Confirmation", null),
	
	/**
	 * A Credit Support Agreement.
	 */
	@RosettaEnumValue(value = "CreditSupportAgreement") 
	CREDIT_SUPPORT_AGREEMENT("CreditSupportAgreement", null),
	
	/**
	 * A Master Agreement.
	 */
	@RosettaEnumValue(value = "MasterAgreement") 
	MASTER_AGREEMENT("MasterAgreement", null),
	
	/**
	 * A Master Confirmation.
	 */
	@RosettaEnumValue(value = "MasterConfirmation") 
	MASTER_CONFIRMATION("MasterConfirmation", null),
	
	/**
	 * A Security Agreement related to a Collateral Transfer Agreement (CTA).
	 */
	@RosettaEnumValue(value = "SecurityAgreement") 
	SECURITY_AGREEMENT("SecurityAgreement", null),
	
	/**
	 * Another type of agreement.
	 */
	@RosettaEnumValue(value = "Other") 
	OTHER("Other", null)
;
	private static Map<String, LegalAgreementTypeEnum> values;
	static {
        Map<String, LegalAgreementTypeEnum> map = new ConcurrentHashMap<>();
		for (LegalAgreementTypeEnum instance : LegalAgreementTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	LegalAgreementTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static LegalAgreementTypeEnum fromDisplayName(String name) {
		LegalAgreementTypeEnum value = values.get(name);
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
