package cdm.product.template.tabulator;

import cdm.product.template.ExerciseProcedure;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ExerciseProcedureTypeTabulator.Impl.class)
public interface ExerciseProcedureTypeTabulator extends Tabulator<ExerciseProcedure> {
	@Singleton
	class Impl implements ExerciseProcedureTypeTabulator {
		private final Field manualExerciseField;
		private final Field automaticExerciseField;
		private final Field followUpConfirmationField;
		private final Field limitedRightToConfirmField;
		private final Field splitTicketField;

		private final ManualExerciseTypeTabulator manualExerciseTypeTabulator;
		private final AutomaticExerciseTypeTabulator automaticExerciseTypeTabulator;

		@Inject
		public Impl(ManualExerciseTypeTabulator manualExerciseTypeTabulator, AutomaticExerciseTypeTabulator automaticExerciseTypeTabulator) {
			this.manualExerciseTypeTabulator = manualExerciseTypeTabulator;
			this.automaticExerciseTypeTabulator = automaticExerciseTypeTabulator;
			this.manualExerciseField = new FieldImpl(
				"manualExercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.automaticExerciseField = new FieldImpl(
				"automaticExercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.followUpConfirmationField = new FieldImpl(
				"followUpConfirmation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.limitedRightToConfirmField = new FieldImpl(
				"limitedRightToConfirm",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.splitTicketField = new FieldImpl(
				"splitTicket",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExerciseProcedure input) {
			FieldValue manualExercise = Optional.ofNullable(input.getManualExercise())
				.map(x -> new NestedFieldValueImpl(manualExerciseField, Optional.of(manualExerciseTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(manualExerciseField, Optional.empty()));
			FieldValue automaticExercise = Optional.ofNullable(input.getAutomaticExercise())
				.map(x -> new NestedFieldValueImpl(automaticExerciseField, Optional.of(automaticExerciseTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(automaticExerciseField, Optional.empty()));
			FieldValue followUpConfirmation = new FieldValueImpl(followUpConfirmationField, Optional.ofNullable(input.getFollowUpConfirmation()));
			FieldValue limitedRightToConfirm = new FieldValueImpl(limitedRightToConfirmField, Optional.ofNullable(input.getLimitedRightToConfirm()));
			FieldValue splitTicket = new FieldValueImpl(splitTicketField, Optional.ofNullable(input.getSplitTicket()));
			return Arrays.asList(
				manualExercise,
				automaticExercise,
				followUpConfirmation,
				limitedRightToConfirm,
				splitTicket
			);
		}
	}
}
