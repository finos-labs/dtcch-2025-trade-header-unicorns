package cdm.event.common;

import cdm.event.common.PerformanceTransferTypeEnum;
import com.rosetta.model.lib.annotations.RosettaEnum;
import com.rosetta.model.lib.annotations.RosettaEnumValue;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * The enumerated values to specify the origin of a performance transfer
 * @version ${project.version}
 */
@RosettaEnum("PerformanceTransferTypeEnum")
public enum PerformanceTransferTypeEnum {

	@RosettaEnumValue(value = "Commodity") 
	COMMODITY("Commodity", null),
	
	@RosettaEnumValue(value = "Correlation") 
	CORRELATION("Correlation", null),
	
	@RosettaEnumValue(value = "Dividend") 
	DIVIDEND("Dividend", null),
	
	@RosettaEnumValue(value = "Equity") 
	EQUITY("Equity", null),
	
	@RosettaEnumValue(value = "Interest") 
	INTEREST("Interest", null),
	
	@RosettaEnumValue(value = "Volatility") 
	VOLATILITY("Volatility", null),
	
	@RosettaEnumValue(value = "Variance") 
	VARIANCE("Variance", null)
;
	private static Map<String, PerformanceTransferTypeEnum> values;
	static {
        Map<String, PerformanceTransferTypeEnum> map = new ConcurrentHashMap<>();
		for (PerformanceTransferTypeEnum instance : PerformanceTransferTypeEnum.values()) {
			map.put(instance.toDisplayString(), instance);
		}
		values = Collections.unmodifiableMap(map);
    }

	private final String rosettaName;
	private final String displayName;

	PerformanceTransferTypeEnum(String rosettaName, String displayName) {
		this.rosettaName = rosettaName;
		this.displayName = displayName;
	}

	public static PerformanceTransferTypeEnum fromDisplayName(String name) {
		PerformanceTransferTypeEnum value = values.get(name);
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
