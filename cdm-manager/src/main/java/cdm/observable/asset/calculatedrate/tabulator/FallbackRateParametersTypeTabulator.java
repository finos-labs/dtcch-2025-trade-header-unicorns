package cdm.observable.asset.calculatedrate.tabulator;

import cdm.observable.asset.calculatedrate.FallbackRateParameters;
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


@ImplementedBy(FallbackRateParametersTypeTabulator.Impl.class)
public interface FallbackRateParametersTypeTabulator extends Tabulator<FallbackRateParameters> {
	@Singleton
	class Impl implements FallbackRateParametersTypeTabulator {
		private final Field floatingRateIndexField;
		private final Field effectiveDateField;
		private final Field calculationParametersField;
		private final Field spreadAdjustmentField;

		private final FloatingRateCalculationParametersTypeTabulator floatingRateCalculationParametersTypeTabulator;

		@Inject
		public Impl(FloatingRateCalculationParametersTypeTabulator floatingRateCalculationParametersTypeTabulator) {
			this.floatingRateCalculationParametersTypeTabulator = floatingRateCalculationParametersTypeTabulator;
			this.floatingRateIndexField = new FieldImpl(
				"floatingRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationParametersField = new FieldImpl(
				"calculationParameters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadAdjustmentField = new FieldImpl(
				"spreadAdjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FallbackRateParameters input) {
			FieldValue floatingRateIndex = new FieldValueImpl(floatingRateIndexField, Optional.ofNullable(input.getFloatingRateIndex()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			FieldValue calculationParameters = Optional.ofNullable(input.getCalculationParameters())
				.map(x -> new NestedFieldValueImpl(calculationParametersField, Optional.of(floatingRateCalculationParametersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationParametersField, Optional.empty()));
			FieldValue spreadAdjustment = new FieldValueImpl(spreadAdjustmentField, Optional.ofNullable(input.getSpreadAdjustment()));
			return Arrays.asList(
				floatingRateIndex,
				effectiveDate,
				calculationParameters,
				spreadAdjustment
			);
		}
	}
}
