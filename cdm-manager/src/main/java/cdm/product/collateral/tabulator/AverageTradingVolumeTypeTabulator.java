package cdm.product.collateral.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.product.collateral.AverageTradingVolume;
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


@ImplementedBy(AverageTradingVolumeTypeTabulator.Impl.class)
public interface AverageTradingVolumeTypeTabulator extends Tabulator<AverageTradingVolume> {
	@Singleton
	class Impl implements AverageTradingVolumeTypeTabulator {
		private final Field periodField;
		private final Field methodologyField;

		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.methodologyField = new FieldImpl(
				"methodology",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AverageTradingVolume input) {
			FieldValue period = Optional.ofNullable(input.getPeriod())
				.map(x -> new NestedFieldValueImpl(periodField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodField, Optional.empty()));
			FieldValue methodology = new FieldValueImpl(methodologyField, Optional.ofNullable(input.getMethodology()));
			return Arrays.asList(
				period,
				methodology
			);
		}
	}
}
