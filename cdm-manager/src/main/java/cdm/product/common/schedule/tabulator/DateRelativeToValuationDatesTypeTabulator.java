package cdm.product.common.schedule.tabulator;

import cdm.observable.asset.tabulator.PerformanceValuationDatesTypeTabulator;
import cdm.product.common.schedule.DateRelativeToValuationDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(DateRelativeToValuationDatesTypeTabulator.Impl.class)
public interface DateRelativeToValuationDatesTypeTabulator extends Tabulator<DateRelativeToValuationDates> {
	@Singleton
	class Impl implements DateRelativeToValuationDatesTypeTabulator {
		private final Field valuationDatesReferenceField;

		private final PerformanceValuationDatesTypeTabulator performanceValuationDatesTypeTabulator;

		@Inject
		public Impl(PerformanceValuationDatesTypeTabulator performanceValuationDatesTypeTabulator) {
			this.performanceValuationDatesTypeTabulator = performanceValuationDatesTypeTabulator;
			this.valuationDatesReferenceField = new FieldImpl(
				"valuationDatesReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DateRelativeToValuationDates input) {
			FieldValue valuationDatesReference = Optional.ofNullable(input.getValuationDatesReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> performanceValuationDatesTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(valuationDatesReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(valuationDatesReferenceField, Optional.empty()));
			return Arrays.asList(
				valuationDatesReference
			);
		}
	}
}
