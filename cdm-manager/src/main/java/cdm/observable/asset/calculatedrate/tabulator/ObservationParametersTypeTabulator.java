package cdm.observable.asset.calculatedrate.tabulator;

import cdm.observable.asset.calculatedrate.ObservationParameters;
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


@ImplementedBy(ObservationParametersTypeTabulator.Impl.class)
public interface ObservationParametersTypeTabulator extends Tabulator<ObservationParameters> {
	@Singleton
	class Impl implements ObservationParametersTypeTabulator {
		private final Field observationCapRateField;
		private final Field observationFloorRateField;

		public Impl() {
			this.observationCapRateField = new FieldImpl(
				"observationCapRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationFloorRateField = new FieldImpl(
				"observationFloorRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationParameters input) {
			FieldValue observationCapRate = new FieldValueImpl(observationCapRateField, Optional.ofNullable(input.getObservationCapRate()));
			FieldValue observationFloorRate = new FieldValueImpl(observationFloorRateField, Optional.ofNullable(input.getObservationFloorRate()));
			return Arrays.asList(
				observationCapRate,
				observationFloorRate
			);
		}
	}
}
