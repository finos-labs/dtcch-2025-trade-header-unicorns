package cdm.base.datetime.tabulator;

import cdm.base.datetime.Period;
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


@ImplementedBy(PeriodTypeTabulator.Impl.class)
public interface PeriodTypeTabulator extends Tabulator<Period> {
	@Singleton
	class Impl implements PeriodTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;

		public Impl() {
			this.periodMultiplierField = new FieldImpl(
				"periodMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Period input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			return Arrays.asList(
				periodMultiplier,
				period
			);
		}
	}
}
