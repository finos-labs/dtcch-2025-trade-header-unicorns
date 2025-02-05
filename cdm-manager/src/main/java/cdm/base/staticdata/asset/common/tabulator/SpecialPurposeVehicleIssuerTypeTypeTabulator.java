package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
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


@ImplementedBy(SpecialPurposeVehicleIssuerTypeTypeTabulator.Impl.class)
public interface SpecialPurposeVehicleIssuerTypeTypeTabulator extends Tabulator<SpecialPurposeVehicleIssuerType> {
	@Singleton
	class Impl implements SpecialPurposeVehicleIssuerTypeTypeTabulator {
		private final Field creditRiskField;

		public Impl() {
			this.creditRiskField = new FieldImpl(
				"creditRisk",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SpecialPurposeVehicleIssuerType input) {
			FieldValue creditRisk = new FieldValueImpl(creditRiskField, Optional.ofNullable(input.getCreditRisk()));
			return Arrays.asList(
				creditRisk
			);
		}
	}
}
