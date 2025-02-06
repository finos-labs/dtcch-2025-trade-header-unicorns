package cdm.product.template.tabulator;

import cdm.product.template.Product;
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


@ImplementedBy(ProductTypeTabulator.Impl.class)
public interface ProductTypeTabulator extends Tabulator<Product> {
	@Singleton
	class Impl implements ProductTypeTabulator {
		private final Field TransferableProductField;
		private final Field NonTransferableProductField;

		private final TransferableProductTypeTabulator transferableProductTypeTabulator;
		private final NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator;

		@Inject
		public Impl(TransferableProductTypeTabulator transferableProductTypeTabulator, NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator) {
			this.transferableProductTypeTabulator = transferableProductTypeTabulator;
			this.nonTransferableProductTypeTabulator = nonTransferableProductTypeTabulator;
			this.TransferableProductField = new FieldImpl(
				"TransferableProduct",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.NonTransferableProductField = new FieldImpl(
				"NonTransferableProduct",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Product input) {
			FieldValue TransferableProduct = Optional.ofNullable(input.getTransferableProduct())
				.map(x -> new NestedFieldValueImpl(TransferableProductField, Optional.of(transferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(TransferableProductField, Optional.empty()));
			FieldValue NonTransferableProduct = Optional.ofNullable(input.getNonTransferableProduct())
				.map(x -> new NestedFieldValueImpl(NonTransferableProductField, Optional.of(nonTransferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(NonTransferableProductField, Optional.empty()));
			return Arrays.asList(
				TransferableProduct,
				NonTransferableProduct
			);
		}
	}
}
