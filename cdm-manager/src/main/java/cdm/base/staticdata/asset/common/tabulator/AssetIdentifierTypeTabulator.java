package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.AssetIdentifier;
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


@ImplementedBy(AssetIdentifierTypeTabulator.Impl.class)
public interface AssetIdentifierTypeTabulator extends Tabulator<AssetIdentifier> {
	@Singleton
	class Impl implements AssetIdentifierTypeTabulator {
		private final Field identifierField;
		private final Field identifierTypeField;

		public Impl() {
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.identifierTypeField = new FieldImpl(
				"identifierType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetIdentifier input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier())
				.map(x -> x.getValue()));
			FieldValue identifierType = new FieldValueImpl(identifierTypeField, Optional.ofNullable(input.getIdentifierType()));
			return Arrays.asList(
				identifier,
				identifierType
			);
		}
	}
}
