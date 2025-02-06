package cdm.product.collateral.tabulator;

import cdm.product.collateral.AssetCountryOfOrigin;
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


@ImplementedBy(AssetCountryOfOriginTypeTabulator.Impl.class)
public interface AssetCountryOfOriginTypeTabulator extends Tabulator<AssetCountryOfOrigin> {
	@Singleton
	class Impl implements AssetCountryOfOriginTypeTabulator {
		private final Field assetCountryOfOriginField;

		public Impl() {
			this.assetCountryOfOriginField = new FieldImpl(
				"assetCountryOfOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetCountryOfOrigin input) {
			FieldValue assetCountryOfOrigin = new FieldValueImpl(assetCountryOfOriginField, Optional.ofNullable(input.getAssetCountryOfOrigin()));
			return Arrays.asList(
				assetCountryOfOrigin
			);
		}
	}
}
