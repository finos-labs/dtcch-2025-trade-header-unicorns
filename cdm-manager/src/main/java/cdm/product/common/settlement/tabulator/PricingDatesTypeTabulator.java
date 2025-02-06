package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableDatesTypeTabulator;
import cdm.product.common.schedule.tabulator.ParametricDatesTypeTabulator;
import cdm.product.common.settlement.PricingDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PricingDatesTypeTabulator.Impl.class)
public interface PricingDatesTypeTabulator extends Tabulator<PricingDates> {
	@Singleton
	class Impl implements PricingDatesTypeTabulator {
		private final Field specifiedDatesField;
		private final Field parametricDatesField;

		private final AdjustableDatesTypeTabulator adjustableDatesTypeTabulator;
		private final ParametricDatesTypeTabulator parametricDatesTypeTabulator;

		@Inject
		public Impl(AdjustableDatesTypeTabulator adjustableDatesTypeTabulator, ParametricDatesTypeTabulator parametricDatesTypeTabulator) {
			this.adjustableDatesTypeTabulator = adjustableDatesTypeTabulator;
			this.parametricDatesTypeTabulator = parametricDatesTypeTabulator;
			this.specifiedDatesField = new FieldImpl(
				"specifiedDates",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.parametricDatesField = new FieldImpl(
				"parametricDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PricingDates input) {
			FieldValue specifiedDates = Optional.ofNullable(input.getSpecifiedDates())
				.map(x -> x.stream()
					.map(_x -> adjustableDatesTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(specifiedDatesField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(specifiedDatesField, Optional.empty()));
			FieldValue parametricDates = Optional.ofNullable(input.getParametricDates())
				.map(x -> new NestedFieldValueImpl(parametricDatesField, Optional.of(parametricDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(parametricDatesField, Optional.empty()));
			return Arrays.asList(
				specifiedDates,
				parametricDates
			);
		}
	}
}
