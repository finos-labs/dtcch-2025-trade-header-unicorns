package cdm.product.collateral.tabulator;

import cdm.product.collateral.CollateralTreatment;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CollateralTreatmentTypeTabulator.Impl.class)
public interface CollateralTreatmentTypeTabulator extends Tabulator<CollateralTreatment> {
	@Singleton
	class Impl implements CollateralTreatmentTypeTabulator {
		private final Field valuationTreatmentField;
		private final Field concentrationLimitField;
		private final Field isIncludedField;

		private final CollateralValuationTreatmentTypeTabulator collateralValuationTreatmentTypeTabulator;
		private final ConcentrationLimitTypeTabulator concentrationLimitTypeTabulator;

		@Inject
		public Impl(CollateralValuationTreatmentTypeTabulator collateralValuationTreatmentTypeTabulator, ConcentrationLimitTypeTabulator concentrationLimitTypeTabulator) {
			this.collateralValuationTreatmentTypeTabulator = collateralValuationTreatmentTypeTabulator;
			this.concentrationLimitTypeTabulator = concentrationLimitTypeTabulator;
			this.valuationTreatmentField = new FieldImpl(
				"valuationTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.concentrationLimitField = new FieldImpl(
				"concentrationLimit",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.isIncludedField = new FieldImpl(
				"isIncluded",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralTreatment input) {
			FieldValue valuationTreatment = Optional.ofNullable(input.getValuationTreatment())
				.map(x -> new NestedFieldValueImpl(valuationTreatmentField, Optional.of(collateralValuationTreatmentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationTreatmentField, Optional.empty()));
			FieldValue concentrationLimit = Optional.ofNullable(input.getConcentrationLimit())
				.map(x -> x.stream()
					.map(_x -> concentrationLimitTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(concentrationLimitField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(concentrationLimitField, Optional.empty()));
			FieldValue isIncluded = new FieldValueImpl(isIncludedField, Optional.ofNullable(input.getIsIncluded()));
			return Arrays.asList(
				valuationTreatment,
				concentrationLimit,
				isIncluded
			);
		}
	}
}
