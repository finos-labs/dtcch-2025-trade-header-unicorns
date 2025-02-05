package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.product.template.ExercisePeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ExercisePeriodTypeTabulator.Impl.class)
public interface ExercisePeriodTypeTabulator extends Tabulator<ExercisePeriod> {
	@Singleton
	class Impl implements ExercisePeriodTypeTabulator {
		private final Field earliestExerciseDateTenorField;
		private final Field exerciseFrequencyField;

		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.earliestExerciseDateTenorField = new FieldImpl(
				"earliestExerciseDateTenor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseFrequencyField = new FieldImpl(
				"exerciseFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExercisePeriod input) {
			FieldValue earliestExerciseDateTenor = Optional.ofNullable(input.getEarliestExerciseDateTenor())
				.map(x -> new NestedFieldValueImpl(earliestExerciseDateTenorField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(earliestExerciseDateTenorField, Optional.empty()));
			FieldValue exerciseFrequency = Optional.ofNullable(input.getExerciseFrequency())
				.map(x -> new NestedFieldValueImpl(exerciseFrequencyField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseFrequencyField, Optional.empty()));
			return Arrays.asList(
				earliestExerciseDateTenor,
				exerciseFrequency
			);
		}
	}
}
