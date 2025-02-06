package cdm.base.datetime.tabulator;

import cdm.base.datetime.TimeZone;
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


@ImplementedBy(TimeZoneTypeTabulator.Impl.class)
public interface TimeZoneTypeTabulator extends Tabulator<TimeZone> {
	@Singleton
	class Impl implements TimeZoneTypeTabulator {
		private final Field timeField;
		private final Field locationField;

		public Impl() {
			this.timeField = new FieldImpl(
				"time",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.locationField = new FieldImpl(
				"location",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TimeZone input) {
			FieldValue time = new FieldValueImpl(timeField, Optional.ofNullable(input.getTime()));
			FieldValue location = new FieldValueImpl(locationField, Optional.ofNullable(input.getLocation())
				.map(x -> x.getValue()));
			return Arrays.asList(
				time,
				location
			);
		}
	}
}
