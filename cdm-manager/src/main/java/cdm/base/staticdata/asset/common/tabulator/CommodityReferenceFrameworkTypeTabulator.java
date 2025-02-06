package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
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


@ImplementedBy(CommodityReferenceFrameworkTypeTabulator.Impl.class)
public interface CommodityReferenceFrameworkTypeTabulator extends Tabulator<CommodityReferenceFramework> {
	@Singleton
	class Impl implements CommodityReferenceFrameworkTypeTabulator {
		private final Field commodityNameField;
		private final Field capacityUnitField;
		private final Field weatherUnitField;
		private final Field currencyField;

		public Impl() {
			this.commodityNameField = new FieldImpl(
				"commodityName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.capacityUnitField = new FieldImpl(
				"capacityUnit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.weatherUnitField = new FieldImpl(
				"weatherUnit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyField = new FieldImpl(
				"currency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CommodityReferenceFramework input) {
			FieldValue commodityName = new FieldValueImpl(commodityNameField, Optional.ofNullable(input.getCommodityName()));
			FieldValue capacityUnit = new FieldValueImpl(capacityUnitField, Optional.ofNullable(input.getCapacityUnit()));
			FieldValue weatherUnit = new FieldValueImpl(weatherUnitField, Optional.ofNullable(input.getWeatherUnit()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			return Arrays.asList(
				commodityName,
				capacityUnit,
				weatherUnit,
				currency
			);
		}
	}
}
