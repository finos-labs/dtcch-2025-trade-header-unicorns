package cdm.base.datetime.tabulator;

import cdm.base.datetime.PeriodRange;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PeriodRangeTypeTabulator.Impl.class)
public interface PeriodRangeTypeTabulator extends Tabulator<PeriodRange> {
	@Singleton
	class Impl implements PeriodRangeTypeTabulator {
		private final Field lowerBoundField;
		private final Field upperBoundField;

		private final PeriodBoundTypeTabulator periodBoundTypeTabulator;

		@Inject
		public Impl(PeriodBoundTypeTabulator periodBoundTypeTabulator) {
			this.periodBoundTypeTabulator = periodBoundTypeTabulator;
			this.lowerBoundField = new FieldImpl(
				"lowerBound",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.upperBoundField = new FieldImpl(
				"upperBound",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PeriodRange input) {
			FieldValue lowerBound = Optional.ofNullable(input.getLowerBound())
				.map(x -> new NestedFieldValueImpl(lowerBoundField, Optional.of(periodBoundTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lowerBoundField, Optional.empty()));
			FieldValue upperBound = Optional.ofNullable(input.getUpperBound())
				.map(x -> new NestedFieldValueImpl(upperBoundField, Optional.of(periodBoundTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(upperBoundField, Optional.empty()));
			return Arrays.asList(
				lowerBound,
				upperBound
			);
		}
	}
}
