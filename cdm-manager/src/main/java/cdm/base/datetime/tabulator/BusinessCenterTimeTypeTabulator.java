package cdm.base.datetime.tabulator;

import cdm.base.datetime.BusinessCenterTime;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(BusinessCenterTimeTypeTabulator.Impl.class)
public interface BusinessCenterTimeTypeTabulator extends Tabulator<BusinessCenterTime> {
	@Singleton
	class Impl implements BusinessCenterTimeTypeTabulator {
		private final Field hourMinuteTimeField;
		private final Field businessCenterField;

		public Impl() {
			this.hourMinuteTimeField = new FieldImpl(
				"hourMinuteTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCenterField = new FieldImpl(
				"businessCenter",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BusinessCenterTime input) {
			FieldValue hourMinuteTime = new FieldValueImpl(hourMinuteTimeField, Optional.ofNullable(input.getHourMinuteTime()));
			FieldValue businessCenter = new FieldValueImpl(businessCenterField, Optional.ofNullable(input.getBusinessCenter())
				.map(x -> x.getValue()));
			return Arrays.asList(
				hourMinuteTime,
				businessCenter
			);
		}
	}
}
