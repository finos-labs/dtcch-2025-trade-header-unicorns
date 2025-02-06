package cdm.product.common.schedule.tabulator;

import cdm.product.common.schedule.ObservationDate;
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


@ImplementedBy(ObservationDateTypeTabulator.Impl.class)
public interface ObservationDateTypeTabulator extends Tabulator<ObservationDate> {
	@Singleton
	class Impl implements ObservationDateTypeTabulator {
		private final Field unadjustedDateField;
		private final Field adjustedDateField;
		private final Field weightField;
		private final Field observationReferenceField;

		public Impl() {
			this.unadjustedDateField = new FieldImpl(
				"unadjustedDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedDateField = new FieldImpl(
				"adjustedDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.weightField = new FieldImpl(
				"weight",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationReferenceField = new FieldImpl(
				"observationReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationDate input) {
			FieldValue unadjustedDate = new FieldValueImpl(unadjustedDateField, Optional.ofNullable(input.getUnadjustedDate()));
			FieldValue adjustedDate = new FieldValueImpl(adjustedDateField, Optional.ofNullable(input.getAdjustedDate()));
			FieldValue weight = new FieldValueImpl(weightField, Optional.ofNullable(input.getWeight()));
			FieldValue observationReference = new FieldValueImpl(observationReferenceField, Optional.ofNullable(input.getObservationReference()));
			return Arrays.asList(
				unadjustedDate,
				adjustedDate,
				weight,
				observationReference
			);
		}
	}
}
