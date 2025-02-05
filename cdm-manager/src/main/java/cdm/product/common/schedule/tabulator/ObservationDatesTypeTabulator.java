package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.PeriodicDatesTypeTabulator;
import cdm.product.common.schedule.ObservationDates;
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


@ImplementedBy(ObservationDatesTypeTabulator.Impl.class)
public interface ObservationDatesTypeTabulator extends Tabulator<ObservationDates> {
	@Singleton
	class Impl implements ObservationDatesTypeTabulator {
		private final Field observationScheduleField;
		private final Field periodicScheduleField;
		private final Field parametricDatesField;

		private final ObservationScheduleTypeTabulator observationScheduleTypeTabulator;
		private final PeriodicDatesTypeTabulator periodicDatesTypeTabulator;
		private final ParametricDatesTypeTabulator parametricDatesTypeTabulator;

		@Inject
		public Impl(ObservationScheduleTypeTabulator observationScheduleTypeTabulator, PeriodicDatesTypeTabulator periodicDatesTypeTabulator, ParametricDatesTypeTabulator parametricDatesTypeTabulator) {
			this.observationScheduleTypeTabulator = observationScheduleTypeTabulator;
			this.periodicDatesTypeTabulator = periodicDatesTypeTabulator;
			this.parametricDatesTypeTabulator = parametricDatesTypeTabulator;
			this.observationScheduleField = new FieldImpl(
				"observationSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodicScheduleField = new FieldImpl(
				"periodicSchedule",
				false,
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
		public List<FieldValue> tabulate(ObservationDates input) {
			FieldValue observationSchedule = Optional.ofNullable(input.getObservationSchedule())
				.map(x -> new NestedFieldValueImpl(observationScheduleField, Optional.of(observationScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationScheduleField, Optional.empty()));
			FieldValue periodicSchedule = Optional.ofNullable(input.getPeriodicSchedule())
				.map(x -> new NestedFieldValueImpl(periodicScheduleField, Optional.of(periodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodicScheduleField, Optional.empty()));
			FieldValue parametricDates = Optional.ofNullable(input.getParametricDates())
				.map(x -> new NestedFieldValueImpl(parametricDatesField, Optional.of(parametricDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(parametricDatesField, Optional.empty()));
			return Arrays.asList(
				observationSchedule,
				periodicSchedule,
				parametricDates
			);
		}
	}
}
