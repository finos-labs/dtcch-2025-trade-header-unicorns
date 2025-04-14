package cdm.base.math.tabulator;

import cdm.base.math.NumberRange;
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


@ImplementedBy(NumberRangeTypeTabulator.Impl.class)
public interface NumberRangeTypeTabulator extends Tabulator<NumberRange> {
	@Singleton
	class Impl implements NumberRangeTypeTabulator {
		private final Field lowerBoundField;
		private final Field upperBoundField;

		private final NumberBoundTypeTabulator numberBoundTypeTabulator;

		@Inject
		public Impl(NumberBoundTypeTabulator numberBoundTypeTabulator) {
			this.numberBoundTypeTabulator = numberBoundTypeTabulator;
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
		public List<FieldValue> tabulate(NumberRange input) {
			FieldValue lowerBound = Optional.ofNullable(input.getLowerBound())
				.map(x -> new NestedFieldValueImpl(lowerBoundField, Optional.of(numberBoundTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lowerBoundField, Optional.empty()));
			FieldValue upperBound = Optional.ofNullable(input.getUpperBound())
				.map(x -> new NestedFieldValueImpl(upperBoundField, Optional.of(numberBoundTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(upperBoundField, Optional.empty()));
			return Arrays.asList(
				lowerBound,
				upperBound
			);
		}
	}
}
