package cdm.observable.asset.tabulator;

import cdm.observable.asset.ValuationDates;
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


@ImplementedBy(ValuationDatesTypeTabulator.Impl.class)
public interface ValuationDatesTypeTabulator extends Tabulator<ValuationDates> {
	@Singleton
	class Impl implements ValuationDatesTypeTabulator {
		private final Field initialValuationDateField;
		private final Field interimValuationDateField;
		private final Field finalValuationDateField;

		private final PerformanceValuationDatesTypeTabulator performanceValuationDatesTypeTabulator;

		@Inject
		public Impl(PerformanceValuationDatesTypeTabulator performanceValuationDatesTypeTabulator) {
			this.performanceValuationDatesTypeTabulator = performanceValuationDatesTypeTabulator;
			this.initialValuationDateField = new FieldImpl(
				"initialValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interimValuationDateField = new FieldImpl(
				"interimValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalValuationDateField = new FieldImpl(
				"finalValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationDates input) {
			FieldValue initialValuationDate = Optional.ofNullable(input.getInitialValuationDate())
				.map(x -> new NestedFieldValueImpl(initialValuationDateField, Optional.of(performanceValuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialValuationDateField, Optional.empty()));
			FieldValue interimValuationDate = Optional.ofNullable(input.getInterimValuationDate())
				.map(x -> new NestedFieldValueImpl(interimValuationDateField, Optional.of(performanceValuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(interimValuationDateField, Optional.empty()));
			FieldValue finalValuationDate = Optional.ofNullable(input.getFinalValuationDate())
				.map(x -> new NestedFieldValueImpl(finalValuationDateField, Optional.of(performanceValuationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalValuationDateField, Optional.empty()));
			return Arrays.asList(
				initialValuationDate,
				interimValuationDate,
				finalValuationDate
			);
		}
	}
}
