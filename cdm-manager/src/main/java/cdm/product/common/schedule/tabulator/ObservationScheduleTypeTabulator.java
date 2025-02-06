package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.product.common.schedule.ObservationSchedule;
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


@ImplementedBy(ObservationScheduleTypeTabulator.Impl.class)
public interface ObservationScheduleTypeTabulator extends Tabulator<ObservationSchedule> {
	@Singleton
	class Impl implements ObservationScheduleTypeTabulator {
		private final Field observationDateField;
		private final Field dateAdjustmentsField;

		private final ObservationDateTypeTabulator observationDateTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;

		@Inject
		public Impl(ObservationDateTypeTabulator observationDateTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator) {
			this.observationDateTypeTabulator = observationDateTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.observationDateField = new FieldImpl(
				"observationDate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateAdjustmentsField = new FieldImpl(
				"dateAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationSchedule input) {
			FieldValue observationDate = Optional.ofNullable(input.getObservationDate())
				.map(x -> x.stream()
					.map(_x -> observationDateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(observationDateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(observationDateField, Optional.empty()));
			FieldValue dateAdjustments = Optional.ofNullable(input.getDateAdjustments())
				.map(x -> new NestedFieldValueImpl(dateAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateAdjustmentsField, Optional.empty()));
			return Arrays.asList(
				observationDate,
				dateAdjustments
			);
		}
	}
}
