package cdm.product.template.tabulator;

import cdm.product.template.ExtensionEvent;
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


@ImplementedBy(ExtensionEventTypeTabulator.Impl.class)
public interface ExtensionEventTypeTabulator extends Tabulator<ExtensionEvent> {
	@Singleton
	class Impl implements ExtensionEventTypeTabulator {
		private final Field adjustedExerciseDateField;
		private final Field adjustedExtendedTerminationDateField;

		public Impl() {
			this.adjustedExerciseDateField = new FieldImpl(
				"adjustedExerciseDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedExtendedTerminationDateField = new FieldImpl(
				"adjustedExtendedTerminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExtensionEvent input) {
			FieldValue adjustedExerciseDate = new FieldValueImpl(adjustedExerciseDateField, Optional.ofNullable(input.getAdjustedExerciseDate()));
			FieldValue adjustedExtendedTerminationDate = new FieldValueImpl(adjustedExtendedTerminationDateField, Optional.ofNullable(input.getAdjustedExtendedTerminationDate()));
			return Arrays.asList(
				adjustedExerciseDate,
				adjustedExtendedTerminationDate
			);
		}
	}
}
