package cdm.base.datetime.tabulator;

import cdm.base.datetime.PeriodBound;
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


@ImplementedBy(PeriodBoundTypeTabulator.Impl.class)
public interface PeriodBoundTypeTabulator extends Tabulator<PeriodBound> {
	@Singleton
	class Impl implements PeriodBoundTypeTabulator {
		private final Field periodField;
		private final Field inclusiveField;

		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.inclusiveField = new FieldImpl(
				"inclusive",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PeriodBound input) {
			FieldValue period = Optional.ofNullable(input.getPeriod())
				.map(x -> new NestedFieldValueImpl(periodField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodField, Optional.empty()));
			FieldValue inclusive = new FieldValueImpl(inclusiveField, Optional.ofNullable(input.getInclusive()));
			return Arrays.asList(
				period,
				inclusive
			);
		}
	}
}
