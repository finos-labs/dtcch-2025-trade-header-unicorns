package cdm.product.template.tabulator;

import cdm.product.template.AutomaticExercise;
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


@ImplementedBy(AutomaticExerciseTypeTabulator.Impl.class)
public interface AutomaticExerciseTypeTabulator extends Tabulator<AutomaticExercise> {
	@Singleton
	class Impl implements AutomaticExerciseTypeTabulator {
		private final Field thresholdRateField;
		private final Field isApplicableField;

		public Impl() {
			this.thresholdRateField = new FieldImpl(
				"thresholdRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.isApplicableField = new FieldImpl(
				"isApplicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AutomaticExercise input) {
			FieldValue thresholdRate = new FieldValueImpl(thresholdRateField, Optional.ofNullable(input.getThresholdRate()));
			FieldValue isApplicable = new FieldValueImpl(isApplicableField, Optional.ofNullable(input.getIsApplicable()));
			return Arrays.asList(
				thresholdRate,
				isApplicable
			);
		}
	}
}
