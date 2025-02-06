package cdm.product.collateral.tabulator;

import cdm.product.collateral.ConcentrationLimitCriteria;
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


@ImplementedBy(ConcentrationLimitCriteriaTypeTabulator.Impl.class)
public interface ConcentrationLimitCriteriaTypeTabulator extends Tabulator<ConcentrationLimitCriteria> {
	@Singleton
	class Impl implements ConcentrationLimitCriteriaTypeTabulator {
		private final Field collateralCriteriaField;
		private final Field appliesToField;
		private final Field restrictToField;
		private final Field ratingPriorityResolutionField;
		private final Field concentrationLimitTypeField;
		private final Field averageTradingVolumeField;

		private final CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator;
		private final AverageTradingVolumeTypeTabulator averageTradingVolumeTypeTabulator;

		@Inject
		public Impl(CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator, AverageTradingVolumeTypeTabulator averageTradingVolumeTypeTabulator) {
			this.collateralCriteriaTypeTabulator = collateralCriteriaTypeTabulator;
			this.averageTradingVolumeTypeTabulator = averageTradingVolumeTypeTabulator;
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
			this.concentrationLimitTypeField = new FieldImpl(
				"concentrationLimitType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averageTradingVolumeField = new FieldImpl(
				"averageTradingVolume",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ConcentrationLimitCriteria input) {
			FieldValue collateralCriteria = Optional.ofNullable(input.getCollateralCriteria())
				.map(x -> new NestedFieldValueImpl(collateralCriteriaField, Optional.of(collateralCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralCriteriaField, Optional.empty()));
			FieldValue appliesTo = new FieldValueImpl(appliesToField, Optional.ofNullable(input.getAppliesTo()));
			FieldValue restrictTo = new FieldValueImpl(restrictToField, Optional.ofNullable(input.getRestrictTo()));
			FieldValue ratingPriorityResolution = new FieldValueImpl(ratingPriorityResolutionField, Optional.ofNullable(input.getRatingPriorityResolution()));
			FieldValue concentrationLimitType = new FieldValueImpl(concentrationLimitTypeField, Optional.ofNullable(input.getConcentrationLimitType()));
			FieldValue averageTradingVolume = Optional.ofNullable(input.getAverageTradingVolume())
				.map(x -> new NestedFieldValueImpl(averageTradingVolumeField, Optional.of(averageTradingVolumeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averageTradingVolumeField, Optional.empty()));
			return Arrays.asList(
				collateralCriteria,
				appliesTo,
				restrictTo,
				ratingPriorityResolution,
				concentrationLimitType,
				averageTradingVolume
			);
		}
	}
}
