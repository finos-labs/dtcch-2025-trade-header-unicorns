package cdm.product.asset.tabulator;

import cdm.product.asset.RateSpecification;
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


@ImplementedBy(RateSpecificationTypeTabulator.Impl.class)
public interface RateSpecificationTypeTabulator extends Tabulator<RateSpecification> {
	@Singleton
	class Impl implements RateSpecificationTypeTabulator {
		private final Field FixedRateSpecificationField;
		private final Field FloatingRateSpecificationField;
		private final Field InflationRateSpecificationField;

		private final FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator;
		private final FloatingRateSpecificationTypeTabulator floatingRateSpecificationTypeTabulator;
		private final InflationRateSpecificationTypeTabulator inflationRateSpecificationTypeTabulator;

		@Inject
		public Impl(FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator, FloatingRateSpecificationTypeTabulator floatingRateSpecificationTypeTabulator, InflationRateSpecificationTypeTabulator inflationRateSpecificationTypeTabulator) {
			this.fixedRateSpecificationTypeTabulator = fixedRateSpecificationTypeTabulator;
			this.floatingRateSpecificationTypeTabulator = floatingRateSpecificationTypeTabulator;
			this.inflationRateSpecificationTypeTabulator = inflationRateSpecificationTypeTabulator;
			this.FixedRateSpecificationField = new FieldImpl(
				"FixedRateSpecification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.FloatingRateSpecificationField = new FieldImpl(
				"FloatingRateSpecification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.InflationRateSpecificationField = new FieldImpl(
				"InflationRateSpecification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(RateSpecification input) {
			FieldValue FixedRateSpecification = Optional.ofNullable(input.getFixedRateSpecification())
				.map(x -> new NestedFieldValueImpl(FixedRateSpecificationField, Optional.of(fixedRateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(FixedRateSpecificationField, Optional.empty()));
			FieldValue FloatingRateSpecification = Optional.ofNullable(input.getFloatingRateSpecification())
				.map(x -> new NestedFieldValueImpl(FloatingRateSpecificationField, Optional.of(floatingRateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(FloatingRateSpecificationField, Optional.empty()));
			FieldValue InflationRateSpecification = Optional.ofNullable(input.getInflationRateSpecification())
				.map(x -> new NestedFieldValueImpl(InflationRateSpecificationField, Optional.of(inflationRateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(InflationRateSpecificationField, Optional.empty()));
			return Arrays.asList(
				FixedRateSpecification,
				FloatingRateSpecification,
				InflationRateSpecification
			);
		}
	}
}
