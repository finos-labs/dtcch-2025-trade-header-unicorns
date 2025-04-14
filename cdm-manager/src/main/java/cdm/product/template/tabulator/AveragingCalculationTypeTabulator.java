package cdm.product.template.tabulator;

import cdm.base.math.tabulator.AveragingCalculationMethodTypeTabulator;
import cdm.base.math.tabulator.RoundingTypeTabulator;
import cdm.product.template.AveragingCalculation;
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


@ImplementedBy(AveragingCalculationTypeTabulator.Impl.class)
public interface AveragingCalculationTypeTabulator extends Tabulator<AveragingCalculation> {
	@Singleton
	class Impl implements AveragingCalculationTypeTabulator {
		private final Field averagingMethodField;
		private final Field precisionField;

		private final AveragingCalculationMethodTypeTabulator averagingCalculationMethodTypeTabulator;
		private final RoundingTypeTabulator roundingTypeTabulator;

		@Inject
		public Impl(AveragingCalculationMethodTypeTabulator averagingCalculationMethodTypeTabulator, RoundingTypeTabulator roundingTypeTabulator) {
			this.averagingCalculationMethodTypeTabulator = averagingCalculationMethodTypeTabulator;
			this.roundingTypeTabulator = roundingTypeTabulator;
			this.averagingMethodField = new FieldImpl(
				"averagingMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.precisionField = new FieldImpl(
				"precision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AveragingCalculation input) {
			FieldValue averagingMethod = Optional.ofNullable(input.getAveragingMethod())
				.map(x -> new NestedFieldValueImpl(averagingMethodField, Optional.of(averagingCalculationMethodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingMethodField, Optional.empty()));
			FieldValue precision = Optional.ofNullable(input.getPrecision())
				.map(x -> new NestedFieldValueImpl(precisionField, Optional.of(roundingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(precisionField, Optional.empty()));
			return Arrays.asList(
				averagingMethod,
				precision
			);
		}
	}
}
