package cdm.product.template.tabulator;

import cdm.observable.asset.tabulator.ObservableTypeTabulator;
import cdm.product.template.Underlier;
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


@ImplementedBy(UnderlierTypeTabulator.Impl.class)
public interface UnderlierTypeTabulator extends Tabulator<Underlier> {
	@Singleton
	class Impl implements UnderlierTypeTabulator {
		private final Field ObservableField;
		private final Field ProductField;

		private final ObservableTypeTabulator observableTypeTabulator;
		private final ProductTypeTabulator productTypeTabulator;

		@Inject
		public Impl(ObservableTypeTabulator observableTypeTabulator, ProductTypeTabulator productTypeTabulator) {
			this.observableTypeTabulator = observableTypeTabulator;
			this.productTypeTabulator = productTypeTabulator;
			this.ObservableField = new FieldImpl(
				"Observable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ProductField = new FieldImpl(
				"Product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Underlier input) {
			FieldValue Observable = Optional.ofNullable(input.getObservable())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(ObservableField, Optional.of(observableTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ObservableField, Optional.empty()));
			FieldValue Product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(ProductField, Optional.of(productTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ProductField, Optional.empty()));
			return Arrays.asList(
				Observable,
				Product
			);
		}
	}
}
