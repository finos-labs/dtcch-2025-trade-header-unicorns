package cdm.product.asset.tabulator;

import cdm.product.asset.BoundedVariance;
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


@ImplementedBy(BoundedVarianceTypeTabulator.Impl.class)
public interface BoundedVarianceTypeTabulator extends Tabulator<BoundedVariance> {
	@Singleton
	class Impl implements BoundedVarianceTypeTabulator {
		private final Field realisedVarianceMethodField;
		private final Field daysInRangeAdjustmentField;
		private final Field upperBarrierField;
		private final Field lowerBarrierField;

		public Impl() {
			this.realisedVarianceMethodField = new FieldImpl(
				"realisedVarianceMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.daysInRangeAdjustmentField = new FieldImpl(
				"daysInRangeAdjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.upperBarrierField = new FieldImpl(
				"upperBarrier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lowerBarrierField = new FieldImpl(
				"lowerBarrier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BoundedVariance input) {
			FieldValue realisedVarianceMethod = new FieldValueImpl(realisedVarianceMethodField, Optional.ofNullable(input.getRealisedVarianceMethod()));
			FieldValue daysInRangeAdjustment = new FieldValueImpl(daysInRangeAdjustmentField, Optional.ofNullable(input.getDaysInRangeAdjustment()));
			FieldValue upperBarrier = new FieldValueImpl(upperBarrierField, Optional.ofNullable(input.getUpperBarrier()));
			FieldValue lowerBarrier = new FieldValueImpl(lowerBarrierField, Optional.ofNullable(input.getLowerBarrier()));
			return Arrays.asList(
				realisedVarianceMethod,
				daysInRangeAdjustment,
				upperBarrier,
				lowerBarrier
			);
		}
	}
}
