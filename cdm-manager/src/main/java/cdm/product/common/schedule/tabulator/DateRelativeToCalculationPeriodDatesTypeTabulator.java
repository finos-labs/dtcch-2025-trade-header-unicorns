package cdm.product.common.schedule.tabulator;

import cdm.product.common.schedule.DateRelativeToCalculationPeriodDates;
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


@ImplementedBy(DateRelativeToCalculationPeriodDatesTypeTabulator.Impl.class)
public interface DateRelativeToCalculationPeriodDatesTypeTabulator extends Tabulator<DateRelativeToCalculationPeriodDates> {
	@Singleton
	class Impl implements DateRelativeToCalculationPeriodDatesTypeTabulator {
		private final Field calculationPeriodDatesReferenceField;

		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;

		@Inject
		public Impl(CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator) {
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.calculationPeriodDatesReferenceField = new FieldImpl(
				"calculationPeriodDatesReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DateRelativeToCalculationPeriodDates input) {
			FieldValue calculationPeriodDatesReference = Optional.ofNullable(input.getCalculationPeriodDatesReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> calculationPeriodDatesTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.empty()));
			return Arrays.asList(
				calculationPeriodDatesReference
			);
		}
	}
}
