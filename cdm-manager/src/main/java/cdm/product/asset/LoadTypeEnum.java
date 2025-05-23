package cdm.product.asset;

import cdm.product.asset.LoadTypeEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Specifies the load type of the delivery.
 * @version ${project.version}
 */
@RosettaEnum("LoadTypeEnum")
public enum LoadTypeEnum {

	/**
	 * Base load
	 */
	@RosettaEnumValue(value = "BaseLoad") 
	BASE_LOAD("BaseLoad", null),
	
	/**
	 * Peak load
	 */
	@RosettaEnumValue(value = "PeakLoad") 
	PEAK_LOAD("PeakLoad", null),
	
	/**
	 * Off-peak load
	 */
	@RosettaEnumValue(value = "OffPeak") 
	OFF_PEAK("OffPeak", null),
	
	/**
	 * Block Hours
	 */
	@RosettaEnumValue(value = "BlockHours") 
	BLOCK_HOURS("BlockHours", null),
	
	/**
	 * Shaped
	 */
	@RosettaEnumValue(value = "Shaped") 
	SHAPED("Shaped", null),
	
	/**
	 * Gas Day
	 */
	@RosettaEnumValue(value = "GasDay") 
	GAS_DAY("GasDay", null),
	
	/**
	 * Other
	 */
	@RosettaEnumValue(value = "Other") 
	OTHER("Other", null)
;
	private static Map<String, LoadTypeEnum> values;
	static {
        Map<String, LoadTypeEnum> map = new ConcurrentHashMap<>();
		for (LoadTypeEnum instance : LoadTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	LoadTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static LoadTypeEnum fromDisplayName(String name) {
		LoadTypeEnum value = values.get(name);
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
