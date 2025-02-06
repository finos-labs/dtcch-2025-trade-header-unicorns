package cdm.observable.asset.tabulator;

import cdm.base.staticdata.asset.common.tabulator.AssetTypeTabulator;
import cdm.observable.asset.Observable;
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


@ImplementedBy(ObservableTypeTabulator.Impl.class)
public interface ObservableTypeTabulator extends Tabulator<Observable> {
	@Singleton
	class Impl implements ObservableTypeTabulator {
		private final Field AssetField;
		private final Field BasketField;
		private final Field IndexField;

		private final AssetTypeTabulator assetTypeTabulator;
		private final BasketTypeTabulator basketTypeTabulator;
		private final IndexTypeTabulator indexTypeTabulator;

		@Inject
		public Impl(AssetTypeTabulator assetTypeTabulator, BasketTypeTabulator basketTypeTabulator, IndexTypeTabulator indexTypeTabulator) {
			this.assetTypeTabulator = assetTypeTabulator;
			this.basketTypeTabulator = basketTypeTabulator;
			this.indexTypeTabulator = indexTypeTabulator;
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
		}

		@Override
		public List<FieldValue> tabulate(Observable input) {
			FieldValue Asset = Optional.ofNullable(input.getAsset())
				.map(x -> new NestedFieldValueImpl(AssetField, Optional.of(assetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(AssetField, Optional.empty()));
			FieldValue Basket = Optional.ofNullable(input.getBasket())
				.map(x -> new NestedFieldValueImpl(BasketField, Optional.of(basketTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(BasketField, Optional.empty()));
			FieldValue Index = Optional.ofNullable(input.getIndex())
				.map(x -> new NestedFieldValueImpl(IndexField, Optional.of(indexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(IndexField, Optional.empty()));
			return Arrays.asList(
				Asset,
				Basket,
				Index
			);
		}
	}
}
