package cdm.product.common.settlement.tabulator;

import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.base.math.tabulator.QuantityTypeTabulator;
import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.product.asset.tabulator.FutureValueAmountTypeTabulator;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ResolvablePriceQuantityTypeTabulator.Impl.class)
public interface ResolvablePriceQuantityTypeTabulator extends Tabulator<ResolvablePriceQuantity> {
	@Singleton
	class Impl implements ResolvablePriceQuantityTypeTabulator {
		private final Field resolvedQuantityField;
		private final Field quantityScheduleField;
		private final Field quantityReferenceField;
		private final Field quantityMultiplierField;
		private final Field resetField;
		private final Field futureValueNotionalField;
		private final Field priceScheduleField;

		private final QuantityTypeTabulator quantityTypeTabulator;
		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;
		private final ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator;
		private final QuantityMultiplierTypeTabulator quantityMultiplierTypeTabulator;
		private final FutureValueAmountTypeTabulator futureValueAmountTypeTabulator;
		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(QuantityTypeTabulator quantityTypeTabulator, NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator, ResolvablePriceQuantityTypeTabulator resolvablePriceQuantityTypeTabulator, QuantityMultiplierTypeTabulator quantityMultiplierTypeTabulator, FutureValueAmountTypeTabulator futureValueAmountTypeTabulator, PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.quantityTypeTabulator = quantityTypeTabulator;
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.resolvablePriceQuantityTypeTabulator = resolvablePriceQuantityTypeTabulator;
			this.quantityMultiplierTypeTabulator = quantityMultiplierTypeTabulator;
			this.futureValueAmountTypeTabulator = futureValueAmountTypeTabulator;
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.resolvedQuantityField = new FieldImpl(
				"resolvedQuantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityScheduleField = new FieldImpl(
				"quantitySchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityReferenceField = new FieldImpl(
				"quantityReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityMultiplierField = new FieldImpl(
				"quantityMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetField = new FieldImpl(
				"reset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.futureValueNotionalField = new FieldImpl(
				"futureValueNotional",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceScheduleField = new FieldImpl(
				"priceSchedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ResolvablePriceQuantity input) {
			FieldValue resolvedQuantity = Optional.ofNullable(input.getResolvedQuantity())
				.map(x -> new NestedFieldValueImpl(resolvedQuantityField, Optional.of(quantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resolvedQuantityField, Optional.empty()));
			FieldValue quantitySchedule = Optional.ofNullable(input.getQuantitySchedule())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(quantityScheduleField, Optional.of(nonNegativeQuantityScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityScheduleField, Optional.empty()));
			FieldValue quantityReference = Optional.ofNullable(input.getQuantityReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(quantityReferenceField, Optional.of(resolvablePriceQuantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityReferenceField, Optional.empty()));
			FieldValue quantityMultiplier = Optional.ofNullable(input.getQuantityMultiplier())
				.map(x -> new NestedFieldValueImpl(quantityMultiplierField, Optional.of(quantityMultiplierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityMultiplierField, Optional.empty()));
			FieldValue reset = new FieldValueImpl(resetField, Optional.ofNullable(input.getReset()));
			FieldValue futureValueNotional = Optional.ofNullable(input.getFutureValueNotional())
				.map(x -> new NestedFieldValueImpl(futureValueNotionalField, Optional.of(futureValueAmountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(futureValueNotionalField, Optional.empty()));
			FieldValue priceSchedule = Optional.ofNullable(input.getPriceSchedule())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceScheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceScheduleField, Optional.empty()));
			return Arrays.asList(
				resolvedQuantity,
				quantitySchedule,
				quantityReference,
				quantityMultiplier,
				reset,
				futureValueNotional,
				priceSchedule
			);
		}
	}
}
