package cdm.observable.asset.tabulator;

import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.AssetTypeTabulator;
import cdm.observable.asset.BasketConstituent;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(BasketConstituentTypeTabulator.Impl.class)
public interface BasketConstituentTypeTabulator extends Tabulator<BasketConstituent> {
	@Singleton
	class Impl implements BasketConstituentTypeTabulator {
		private final Field AssetField;
		private final Field BasketField;
		private final Field IndexField;
		private final Field quantityField;
		private final Field initialValuationPriceField;
		private final Field interimValuationPriceField;
		private final Field finalValuationPriceField;

		private final AssetTypeTabulator assetTypeTabulator;
		private final BasketTypeTabulator basketTypeTabulator;
		private final IndexTypeTabulator indexTypeTabulator;
		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;
		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(AssetTypeTabulator assetTypeTabulator, BasketTypeTabulator basketTypeTabulator, IndexTypeTabulator indexTypeTabulator, NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator, PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.assetTypeTabulator = assetTypeTabulator;
			this.basketTypeTabulator = basketTypeTabulator;
			this.indexTypeTabulator = indexTypeTabulator;
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.AssetField = new FieldImpl(
				"Asset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.BasketField = new FieldImpl(
				"Basket",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.IndexField = new FieldImpl(
				"Index",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityField = new FieldImpl(
				"quantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialValuationPriceField = new FieldImpl(
				"initialValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interimValuationPriceField = new FieldImpl(
				"interimValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalValuationPriceField = new FieldImpl(
				"finalValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BasketConstituent input) {
			FieldValue Asset = Optional.ofNullable(input.getAsset())
				.map(x -> new NestedFieldValueImpl(AssetField, Optional.of(assetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetField, Optional.empty()));
			FieldValue Basket = Optional.ofNullable(input.getBasket())
				.map(x -> new NestedFieldValueImpl(BasketField, Optional.of(basketTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(BasketField, Optional.empty()));
			FieldValue Index = Optional.ofNullable(input.getIndex())
				.map(x -> new NestedFieldValueImpl(IndexField, Optional.of(indexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IndexField, Optional.empty()));
			FieldValue quantity = Optional.ofNullable(input.getQuantity())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> nonNegativeQuantityScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(quantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(quantityField, Optional.empty()));
			FieldValue initialValuationPrice = Optional.ofNullable(input.getInitialValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.empty()));
			FieldValue interimValuationPrice = Optional.ofNullable(input.getInterimValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.empty()));
			FieldValue finalValuationPrice = Optional.ofNullable(input.getFinalValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.empty()));
			return Arrays.asList(
				Asset,
				Basket,
				Index,
				quantity,
				initialValuationPrice,
				interimValuationPrice,
				finalValuationPrice
			);
		}
	}
}
