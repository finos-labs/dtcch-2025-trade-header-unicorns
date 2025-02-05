package cdm.product.template.tabulator;

import cdm.product.template.ManualExercise;
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


@ImplementedBy(ManualExerciseTypeTabulator.Impl.class)
public interface ManualExerciseTypeTabulator extends Tabulator<ManualExercise> {
	@Singleton
	class Impl implements ManualExerciseTypeTabulator {
		private final Field exerciseNoticeField;
		private final Field fallbackExerciseField;

		private final ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator;

		@Inject
		public Impl(ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator) {
			this.exerciseNoticeTypeTabulator = exerciseNoticeTypeTabulator;
			this.exerciseNoticeField = new FieldImpl(
				"exerciseNotice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fallbackExerciseField = new FieldImpl(
				"fallbackExercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ManualExercise input) {
			FieldValue exerciseNotice = Optional.ofNullable(input.getExerciseNotice())
				.map(x -> new NestedFieldValueImpl(exerciseNoticeField, Optional.of(exerciseNoticeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseNoticeField, Optional.empty()));
			FieldValue fallbackExercise = new FieldValueImpl(fallbackExerciseField, Optional.ofNullable(input.getFallbackExercise()));
			return Arrays.asList(
				exerciseNotice,
				fallbackExercise
			);
		}
	}
}
