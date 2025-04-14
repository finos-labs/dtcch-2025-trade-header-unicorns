package cdm.product.template.tabulator;

import cdm.product.template.CancellationEvent;
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


@ImplementedBy(CancellationEventTypeTabulator.Impl.class)
public interface CancellationEventTypeTabulator extends Tabulator<CancellationEvent> {
	@Singleton
	class Impl implements CancellationEventTypeTabulator {
		private final Field adjustedExerciseDateField;
		private final Field adjustedEarlyTerminationDateField;

		public Impl() {
			this.adjustedExerciseDateField = new FieldImpl(
				"adjustedExerciseDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedEarlyTerminationDateField = new FieldImpl(
				"adjustedEarlyTerminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CancellationEvent input) {
			FieldValue adjustedExerciseDate = new FieldValueImpl(adjustedExerciseDateField, Optional.ofNullable(input.getAdjustedExerciseDate()));
			FieldValue adjustedEarlyTerminationDate = new FieldValueImpl(adjustedEarlyTerminationDateField, Optional.ofNullable(input.getAdjustedEarlyTerminationDate()));
			return Arrays.asList(
				adjustedExerciseDate,
				adjustedEarlyTerminationDate
			);
		}
	}
}
