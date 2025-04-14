package cdm.product.asset.tabulator;

import cdm.product.asset.VarianceCapFloor;
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


@ImplementedBy(VarianceCapFloorTypeTabulator.Impl.class)
public interface VarianceCapFloorTypeTabulator extends Tabulator<VarianceCapFloor> {
	@Singleton
	class Impl implements VarianceCapFloorTypeTabulator {
		private final Field varianceCapField;
		private final Field unadjustedVarianceCapField;
		private final Field boundedVarianceField;

		private final BoundedVarianceTypeTabulator boundedVarianceTypeTabulator;

		@Inject
		public Impl(BoundedVarianceTypeTabulator boundedVarianceTypeTabulator) {
			this.boundedVarianceTypeTabulator = boundedVarianceTypeTabulator;
			this.varianceCapField = new FieldImpl(
				"varianceCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unadjustedVarianceCapField = new FieldImpl(
				"unadjustedVarianceCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.boundedVarianceField = new FieldImpl(
				"boundedVariance",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(VarianceCapFloor input) {
			FieldValue varianceCap = new FieldValueImpl(varianceCapField, Optional.ofNullable(input.getVarianceCap()));
			FieldValue unadjustedVarianceCap = new FieldValueImpl(unadjustedVarianceCapField, Optional.ofNullable(input.getUnadjustedVarianceCap()));
			FieldValue boundedVariance = Optional.ofNullable(input.getBoundedVariance())
				.map(x -> new NestedFieldValueImpl(boundedVarianceField, Optional.of(boundedVarianceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(boundedVarianceField, Optional.empty()));
			return Arrays.asList(
				varianceCap,
				unadjustedVarianceCap,
				boundedVariance
			);
		}
	}
}
