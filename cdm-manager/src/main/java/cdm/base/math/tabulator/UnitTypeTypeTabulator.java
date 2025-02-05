package cdm.base.math.tabulator;

import cdm.base.math.UnitType;
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


@ImplementedBy(UnitTypeTypeTabulator.Impl.class)
public interface UnitTypeTypeTabulator extends Tabulator<UnitType> {
	@Singleton
	class Impl implements UnitTypeTypeTabulator {
		private final Field capacityUnitField;
		private final Field weatherUnitField;
		private final Field financialUnitField;
		private final Field currencyField;

		public Impl() {
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
			this.financialUnitField = new FieldImpl(
				"financialUnit",
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
		public List<FieldValue> tabulate(UnitType input) {
			FieldValue capacityUnit = new FieldValueImpl(capacityUnitField, Optional.ofNullable(input.getCapacityUnit()));
			FieldValue weatherUnit = new FieldValueImpl(weatherUnitField, Optional.ofNullable(input.getWeatherUnit()));
			FieldValue financialUnit = new FieldValueImpl(financialUnitField, Optional.ofNullable(input.getFinancialUnit()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			return Arrays.asList(
				capacityUnit,
				weatherUnit,
				financialUnit,
				currency
			);
		}
	}
}
