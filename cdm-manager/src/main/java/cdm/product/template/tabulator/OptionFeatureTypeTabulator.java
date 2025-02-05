package cdm.product.template.tabulator;

import cdm.product.template.OptionFeature;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(OptionFeatureTypeTabulator.Impl.class)
public interface OptionFeatureTypeTabulator extends Tabulator<OptionFeature> {
	@Singleton
	class Impl implements OptionFeatureTypeTabulator {
		private final Field fxFeatureField;
		private final Field strategyFeatureField;
		private final Field averagingFeatureField;
		private final Field barrierField;
		private final Field knockField;
		private final Field passThroughField;

		private final FxFeatureTypeTabulator fxFeatureTypeTabulator;
		private final StrategyFeatureTypeTabulator strategyFeatureTypeTabulator;
		private final AveragingCalculationTypeTabulator averagingCalculationTypeTabulator;
		private final BarrierTypeTabulator barrierTypeTabulator;
		private final KnockTypeTabulator knockTypeTabulator;
		private final PassThroughTypeTabulator passThroughTypeTabulator;

		@Inject
		public Impl(FxFeatureTypeTabulator fxFeatureTypeTabulator, StrategyFeatureTypeTabulator strategyFeatureTypeTabulator, AveragingCalculationTypeTabulator averagingCalculationTypeTabulator, BarrierTypeTabulator barrierTypeTabulator, KnockTypeTabulator knockTypeTabulator, PassThroughTypeTabulator passThroughTypeTabulator) {
			this.fxFeatureTypeTabulator = fxFeatureTypeTabulator;
			this.strategyFeatureTypeTabulator = strategyFeatureTypeTabulator;
			this.averagingCalculationTypeTabulator = averagingCalculationTypeTabulator;
			this.barrierTypeTabulator = barrierTypeTabulator;
			this.knockTypeTabulator = knockTypeTabulator;
			this.passThroughTypeTabulator = passThroughTypeTabulator;
			this.fxFeatureField = new FieldImpl(
				"fxFeature",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.strategyFeatureField = new FieldImpl(
				"strategyFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingFeatureField = new FieldImpl(
				"averagingFeature",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.barrierField = new FieldImpl(
				"barrier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.knockField = new FieldImpl(
				"knock",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.passThroughField = new FieldImpl(
				"passThrough",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OptionFeature input) {
			FieldValue fxFeature = Optional.ofNullable(input.getFxFeature())
				.map(x -> x.stream()
					.map(_x -> fxFeatureTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(fxFeatureField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(fxFeatureField, Optional.empty()));
			FieldValue strategyFeature = Optional.ofNullable(input.getStrategyFeature())
				.map(x -> new NestedFieldValueImpl(strategyFeatureField, Optional.of(strategyFeatureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(strategyFeatureField, Optional.empty()));
			FieldValue averagingFeature = Optional.ofNullable(input.getAveragingFeature())
				.map(x -> new NestedFieldValueImpl(averagingFeatureField, Optional.of(averagingCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingFeatureField, Optional.empty()));
			FieldValue barrier = Optional.ofNullable(input.getBarrier())
				.map(x -> new NestedFieldValueImpl(barrierField, Optional.of(barrierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(barrierField, Optional.empty()));
			FieldValue knock = Optional.ofNullable(input.getKnock())
				.map(x -> new NestedFieldValueImpl(knockField, Optional.of(knockTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(knockField, Optional.empty()));
			FieldValue passThrough = Optional.ofNullable(input.getPassThrough())
				.map(x -> new NestedFieldValueImpl(passThroughField, Optional.of(passThroughTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(passThroughField, Optional.empty()));
			return Arrays.asList(
				fxFeature,
				strategyFeature,
				averagingFeature,
				barrier,
				knock,
				passThrough
			);
		}
	}
}
