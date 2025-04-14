package cdm.event.workflow.tabulator;

import cdm.event.workflow.EventTimestamp;
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


@ImplementedBy(EventTimestampTypeTabulator.Impl.class)
public interface EventTimestampTypeTabulator extends Tabulator<EventTimestamp> {
	@Singleton
	class Impl implements EventTimestampTypeTabulator {
		private final Field dateTimeField;
		private final Field qualificationField;

		public Impl() {
			this.dateTimeField = new FieldImpl(
				"dateTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.qualificationField = new FieldImpl(
				"qualification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EventTimestamp input) {
			FieldValue dateTime = new FieldValueImpl(dateTimeField, Optional.ofNullable(input.getDateTime()));
			FieldValue qualification = new FieldValueImpl(qualificationField, Optional.ofNullable(input.getQualification()));
			return Arrays.asList(
				dateTime,
				qualification
			);
		}
	}
}
