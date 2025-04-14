package cdm.product.collateral.tabulator;

import cdm.product.collateral.EligibleCollateralCriteria;
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


@ImplementedBy(EligibleCollateralCriteriaTypeTabulator.Impl.class)
public interface EligibleCollateralCriteriaTypeTabulator extends Tabulator<EligibleCollateralCriteria> {
	@Singleton
	class Impl implements EligibleCollateralCriteriaTypeTabulator {
		private final Field collateralCriteriaField;
		private final Field appliesToField;
		private final Field restrictToField;
		private final Field ratingPriorityResolutionField;
		private final Field treatmentField;

		private final CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator;
		private final CollateralTreatmentTypeTabulator collateralTreatmentTypeTabulator;

		@Inject
		public Impl(CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator, CollateralTreatmentTypeTabulator collateralTreatmentTypeTabulator) {
			this.collateralCriteriaTypeTabulator = collateralCriteriaTypeTabulator;
			this.collateralTreatmentTypeTabulator = collateralTreatmentTypeTabulator;
			this.collateralCriteriaField = new FieldImpl(
				"collateralCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.appliesToField = new FieldImpl(
				"appliesTo",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.restrictToField = new FieldImpl(
				"restrictTo",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ratingPriorityResolutionField = new FieldImpl(
				"ratingPriorityResolution",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.treatmentField = new FieldImpl(
				"treatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EligibleCollateralCriteria input) {
			FieldValue collateralCriteria = Optional.ofNullable(input.getCollateralCriteria())
				.map(x -> new NestedFieldValueImpl(collateralCriteriaField, Optional.of(collateralCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralCriteriaField, Optional.empty()));
			FieldValue appliesTo = new FieldValueImpl(appliesToField, Optional.ofNullable(input.getAppliesTo()));
			FieldValue restrictTo = new FieldValueImpl(restrictToField, Optional.ofNullable(input.getRestrictTo()));
			FieldValue ratingPriorityResolution = new FieldValueImpl(ratingPriorityResolutionField, Optional.ofNullable(input.getRatingPriorityResolution()));
			FieldValue treatment = Optional.ofNullable(input.getTreatment())
				.map(x -> new NestedFieldValueImpl(treatmentField, Optional.of(collateralTreatmentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(treatmentField, Optional.empty()));
			return Arrays.asList(
				collateralCriteria,
				appliesTo,
				restrictTo,
				ratingPriorityResolution,
				treatment
			);
		}
	}
}
