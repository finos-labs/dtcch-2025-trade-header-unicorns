package cdm.observable.asset.tabulator;

import cdm.observable.asset.InterestRateIndex;
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


@ImplementedBy(InterestRateIndexTypeTabulator.Impl.class)
public interface InterestRateIndexTypeTabulator extends Tabulator<InterestRateIndex> {
	@Singleton
	class Impl implements InterestRateIndexTypeTabulator {
		private final Field FloatingRateIndexField;
		private final Field InflationIndexField;

		private final FloatingRateIndexTypeTabulator floatingRateIndexTypeTabulator;
		private final InflationIndexTypeTabulator inflationIndexTypeTabulator;

		@Inject
		public Impl(FloatingRateIndexTypeTabulator floatingRateIndexTypeTabulator, InflationIndexTypeTabulator inflationIndexTypeTabulator) {
			this.floatingRateIndexTypeTabulator = floatingRateIndexTypeTabulator;
			this.inflationIndexTypeTabulator = inflationIndexTypeTabulator;
			this.FloatingRateIndexField = new FieldImpl(
				"FloatingRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.InflationIndexField = new FieldImpl(
				"InflationIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InterestRateIndex input) {
			FieldValue FloatingRateIndex = Optional.ofNullable(input.getFloatingRateIndex())
				.map(x -> new NestedFieldValueImpl(FloatingRateIndexField, Optional.of(floatingRateIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(FloatingRateIndexField, Optional.empty()));
			FieldValue InflationIndex = Optional.ofNullable(input.getInflationIndex())
				.map(x -> new NestedFieldValueImpl(InflationIndexField, Optional.of(inflationIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(InflationIndexField, Optional.empty()));
			return Arrays.asList(
				FloatingRateIndex,
				InflationIndex
			);
		}
	}
}
