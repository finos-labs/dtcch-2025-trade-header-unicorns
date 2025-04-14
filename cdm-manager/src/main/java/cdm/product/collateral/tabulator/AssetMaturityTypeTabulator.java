package cdm.product.collateral.tabulator;

import cdm.base.datetime.tabulator.PeriodRangeTypeTabulator;
import cdm.product.collateral.AssetMaturity;
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


@ImplementedBy(AssetMaturityTypeTabulator.Impl.class)
public interface AssetMaturityTypeTabulator extends Tabulator<AssetMaturity> {
	@Singleton
	class Impl implements AssetMaturityTypeTabulator {
		private final Field maturityTypeField;
		private final Field maturityRangeField;

		private final PeriodRangeTypeTabulator periodRangeTypeTabulator;

		@Inject
		public Impl(PeriodRangeTypeTabulator periodRangeTypeTabulator) {
			this.periodRangeTypeTabulator = periodRangeTypeTabulator;
			this.maturityTypeField = new FieldImpl(
				"maturityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maturityRangeField = new FieldImpl(
				"maturityRange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetMaturity input) {
			FieldValue maturityType = new FieldValueImpl(maturityTypeField, Optional.ofNullable(input.getMaturityType()));
			FieldValue maturityRange = Optional.ofNullable(input.getMaturityRange())
				.map(x -> new NestedFieldValueImpl(maturityRangeField, Optional.of(periodRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(maturityRangeField, Optional.empty()));
			return Arrays.asList(
				maturityType,
				maturityRange
			);
		}
	}
}
