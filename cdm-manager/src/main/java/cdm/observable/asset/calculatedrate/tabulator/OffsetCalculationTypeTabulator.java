package cdm.observable.asset.calculatedrate.tabulator;

import cdm.observable.asset.calculatedrate.OffsetCalculation;
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


@ImplementedBy(OffsetCalculationTypeTabulator.Impl.class)
public interface OffsetCalculationTypeTabulator extends Tabulator<OffsetCalculation> {
	@Singleton
	class Impl implements OffsetCalculationTypeTabulator {
		private final Field offsetDaysField;

		public Impl() {
			this.offsetDaysField = new FieldImpl(
				"offsetDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(OffsetCalculation input) {
			FieldValue offsetDays = new FieldValueImpl(offsetDaysField, Optional.ofNullable(input.getOffsetDays()));
			return Arrays.asList(
				offsetDays
			);
		}
	}
}
