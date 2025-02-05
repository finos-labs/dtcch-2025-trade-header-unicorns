package cdm.base.math.tabulator;

import cdm.base.math.AveragingCalculationMethod;
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


@ImplementedBy(AveragingCalculationMethodTypeTabulator.Impl.class)
public interface AveragingCalculationMethodTypeTabulator extends Tabulator<AveragingCalculationMethod> {
	@Singleton
	class Impl implements AveragingCalculationMethodTypeTabulator {
		private final Field isWeightedField;
		private final Field calculationMethodField;

		public Impl() {
			this.isWeightedField = new FieldImpl(
				"isWeighted",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationMethodField = new FieldImpl(
				"calculationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AveragingCalculationMethod input) {
			FieldValue isWeighted = new FieldValueImpl(isWeightedField, Optional.ofNullable(input.getIsWeighted()));
			FieldValue calculationMethod = new FieldValueImpl(calculationMethodField, Optional.ofNullable(input.getCalculationMethod()));
			return Arrays.asList(
				isWeighted,
				calculationMethod
			);
		}
	}
}
