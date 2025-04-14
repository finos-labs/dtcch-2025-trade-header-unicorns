package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.ProductIdentifier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(ProductIdentifierTypeTabulator.Impl.class)
public interface ProductIdentifierTypeTabulator extends Tabulator<ProductIdentifier> {
	@Singleton
	class Impl implements ProductIdentifierTypeTabulator {
		private final Field identifierField;
		private final Field sourceField;

		public Impl() {
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sourceField = new FieldImpl(
				"source",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ProductIdentifier input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier())
				.map(x -> x.getValue()));
			FieldValue source = new FieldValueImpl(sourceField, Optional.ofNullable(input.getSource()));
			return Arrays.asList(
				identifier,
				source
			);
		}
	}
}
