package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.AssetType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AssetTypeTypeTabulator.Impl.class)
public interface AssetTypeTypeTabulator extends Tabulator<AssetType> {
	@Singleton
	class Impl implements AssetTypeTypeTabulator {
		private final Field assetTypeField;
		private final Field securityTypeField;
		private final Field debtTypeField;
		private final Field equityTypeField;
		private final Field fundTypeField;
		private final Field otherAssetTypeField;

		private final DebtTypeTypeTabulator debtTypeTypeTabulator;

		@Inject
		public Impl(DebtTypeTypeTabulator debtTypeTypeTabulator) {
			this.debtTypeTypeTabulator = debtTypeTypeTabulator;
			this.assetTypeField = new FieldImpl(
				"assetType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.securityTypeField = new FieldImpl(
				"securityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtTypeField = new FieldImpl(
				"debtType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.equityTypeField = new FieldImpl(
				"equityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fundTypeField = new FieldImpl(
				"fundType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.otherAssetTypeField = new FieldImpl(
				"otherAssetType",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetType input) {
			FieldValue assetType = new FieldValueImpl(assetTypeField, Optional.ofNullable(input.getAssetType()));
			FieldValue securityType = new FieldValueImpl(securityTypeField, Optional.ofNullable(input.getSecurityType()));
			FieldValue debtType = Optional.ofNullable(input.getDebtType())
				.map(x -> new NestedFieldValueImpl(debtTypeField, Optional.of(debtTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(debtTypeField, Optional.empty()));
			FieldValue equityType = new FieldValueImpl(equityTypeField, Optional.ofNullable(input.getEquityType()));
			FieldValue fundType = new FieldValueImpl(fundTypeField, Optional.ofNullable(input.getFundType()));
			FieldValue otherAssetType = new FieldValueImpl(otherAssetTypeField, Optional.ofNullable(input.getOtherAssetType()));
			return Arrays.asList(
				assetType,
				securityType,
				debtType,
				equityType,
				fundType,
				otherAssetType
			);
		}
	}
}
