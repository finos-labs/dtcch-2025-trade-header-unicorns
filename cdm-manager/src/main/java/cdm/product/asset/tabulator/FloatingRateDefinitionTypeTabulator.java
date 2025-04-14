package cdm.product.asset.tabulator;

import cdm.observable.asset.tabulator.RateObservationTypeTabulator;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.template.tabulator.StrikeTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(FloatingRateDefinitionTypeTabulator.Impl.class)
public interface FloatingRateDefinitionTypeTabulator extends Tabulator<FloatingRateDefinition> {
	@Singleton
	class Impl implements FloatingRateDefinitionTypeTabulator {
		private final Field calculatedRateField;
		private final Field rateObservationField;
		private final Field floatingRateMultiplierField;
		private final Field spreadField;
		private final Field capRateField;
		private final Field floorRateField;

		private final RateObservationTypeTabulator rateObservationTypeTabulator;
		private final StrikeTypeTabulator strikeTypeTabulator;

		@Inject
		public Impl(RateObservationTypeTabulator rateObservationTypeTabulator, StrikeTypeTabulator strikeTypeTabulator) {
			this.rateObservationTypeTabulator = rateObservationTypeTabulator;
			this.strikeTypeTabulator = strikeTypeTabulator;
			this.calculatedRateField = new FieldImpl(
				"calculatedRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateObservationField = new FieldImpl(
				"rateObservation",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingRateMultiplierField = new FieldImpl(
				"floatingRateMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadField = new FieldImpl(
				"spread",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.capRateField = new FieldImpl(
				"capRate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floorRateField = new FieldImpl(
				"floorRate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FloatingRateDefinition input) {
			FieldValue calculatedRate = new FieldValueImpl(calculatedRateField, Optional.ofNullable(input.getCalculatedRate()));
			FieldValue rateObservation = Optional.ofNullable(input.getRateObservation())
				.map(x -> x.stream()
					.map(_x -> rateObservationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(rateObservationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(rateObservationField, Optional.empty()));
			FieldValue floatingRateMultiplier = new FieldValueImpl(floatingRateMultiplierField, Optional.ofNullable(input.getFloatingRateMultiplier()));
			FieldValue spread = new FieldValueImpl(spreadField, Optional.ofNullable(input.getSpread()));
			FieldValue capRate = Optional.ofNullable(input.getCapRate())
				.map(x -> x.stream()
					.map(_x -> strikeTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(capRateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(capRateField, Optional.empty()));
			FieldValue floorRate = Optional.ofNullable(input.getFloorRate())
				.map(x -> x.stream()
					.map(_x -> strikeTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(floorRateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(floorRateField, Optional.empty()));
			return Arrays.asList(
				calculatedRate,
				rateObservation,
				floatingRateMultiplier,
				spread,
				capRate,
				floorRate
			);
		}
	}
}
