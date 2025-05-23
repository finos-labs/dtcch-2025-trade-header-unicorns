package cdm.observable.common.functions;

import cdm.base.datetime.TimeZone;
import cdm.base.datetime.TimeZone.TimeZoneBuilder;
import cdm.base.staticdata.asset.common.AssetIdentifier;
import cdm.observable.common.DeterminationMethodEnum;
import cdm.observable.common.TimeTypeEnum;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(ResolveTimeZoneFromTimeType.ResolveTimeZoneFromTimeTypeDefault.class)
public abstract class ResolveTimeZoneFromTimeType implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param assetIdentifier 
	* @param timeType 
	* @param determinationMethod 
	* @return time 
	*/
	public TimeZone evaluate(AssetIdentifier assetIdentifier, TimeTypeEnum timeType, DeterminationMethodEnum determinationMethod) {
		TimeZone.TimeZoneBuilder timeBuilder = doEvaluate(assetIdentifier, timeType, determinationMethod);
		
		final TimeZone time;
		if (timeBuilder == null) {
			time = null;
		} else {
			time = timeBuilder.build();
			objectValidator.validate(TimeZone.class, time);
		}
		
		return time;
	}

	protected abstract TimeZone.TimeZoneBuilder doEvaluate(AssetIdentifier assetIdentifier, TimeTypeEnum timeType, DeterminationMethodEnum determinationMethod);

	public static class ResolveTimeZoneFromTimeTypeDefault extends ResolveTimeZoneFromTimeType {
		@Override
		protected TimeZone.TimeZoneBuilder doEvaluate(AssetIdentifier assetIdentifier, TimeTypeEnum timeType, DeterminationMethodEnum determinationMethod) {
			TimeZone.TimeZoneBuilder time = TimeZone.builder();
			return assignOutput(time, assetIdentifier, timeType, determinationMethod);
		}
		
		protected TimeZone.TimeZoneBuilder assignOutput(TimeZone.TimeZoneBuilder time, AssetIdentifier assetIdentifier, TimeTypeEnum timeType, DeterminationMethodEnum determinationMethod) {
			return Optional.ofNullable(time)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
