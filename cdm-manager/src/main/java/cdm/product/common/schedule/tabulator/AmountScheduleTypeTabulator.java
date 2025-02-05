package cdm.product.common.schedule.tabulator;

import cdm.base.math.tabulator.DatedValueTypeTabulator;
import cdm.product.common.schedule.AmountSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AmountScheduleTypeTabulator.Impl.class)
public interface AmountScheduleTypeTabulator extends Tabulator<AmountSchedule> {
	@Singleton
	class Impl implements AmountScheduleTypeTabulator {
		private final Field valueField;
		private final Field datedValueField;
		private final Field currencyField;

		private final DatedValueTypeTabulator datedValueTypeTabulator;

		@Inject
		public Impl(DatedValueTypeTabulator datedValueTypeTabulator) {
			this.datedValueTypeTabulator = datedValueTypeTabulator;
			this.valueField = new FieldImpl(
				"value",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.datedValueField = new FieldImpl(
				"datedValue",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyField = new FieldImpl(
				"currency",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AmountSchedule input) {
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue datedValue = Optional.ofNullable(input.getDatedValue())
				.map(x -> x.stream()
					.map(_x -> datedValueTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(datedValueField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(datedValueField, Optional.empty()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				value,
				datedValue,
				currency
			);
		}
	}
}
