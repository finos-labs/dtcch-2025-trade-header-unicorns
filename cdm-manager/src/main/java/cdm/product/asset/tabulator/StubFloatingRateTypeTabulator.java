package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.base.math.tabulator.ScheduleTypeTabulator;
import cdm.product.asset.StubFloatingRate;
import cdm.product.template.tabulator.StrikeScheduleTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(StubFloatingRateTypeTabulator.Impl.class)
public interface StubFloatingRateTypeTabulator extends Tabulator<StubFloatingRate> {
	@Singleton
	class Impl implements StubFloatingRateTypeTabulator {
		private final Field floatingRateIndexField;
		private final Field indexTenorField;
		private final Field floatingRateMultiplierScheduleField;
		private final Field spreadScheduleField;
		private final Field rateTreatmentField;
		private final Field capRateScheduleField;
		private final Field floorRateScheduleField;

		private final PeriodTypeTabulator periodTypeTabulator;
		private final ScheduleTypeTabulator scheduleTypeTabulator;
		private final SpreadScheduleTypeTabulator spreadScheduleTypeTabulator;
		private final StrikeScheduleTypeTabulator strikeScheduleTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator, ScheduleTypeTabulator scheduleTypeTabulator, SpreadScheduleTypeTabulator spreadScheduleTypeTabulator, StrikeScheduleTypeTabulator strikeScheduleTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.scheduleTypeTabulator = scheduleTypeTabulator;
			this.spreadScheduleTypeTabulator = spreadScheduleTypeTabulator;
			this.strikeScheduleTypeTabulator = strikeScheduleTypeTabulator;
			this.floatingRateIndexField = new FieldImpl(
				"floatingRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexTenorField = new FieldImpl(
				"indexTenor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingRateMultiplierScheduleField = new FieldImpl(
				"floatingRateMultiplierSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadScheduleField = new FieldImpl(
				"spreadSchedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateTreatmentField = new FieldImpl(
				"rateTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.capRateScheduleField = new FieldImpl(
				"capRateSchedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floorRateScheduleField = new FieldImpl(
				"floorRateSchedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StubFloatingRate input) {
			FieldValue floatingRateIndex = new FieldValueImpl(floatingRateIndexField, Optional.ofNullable(input.getFloatingRateIndex()));
			FieldValue indexTenor = Optional.ofNullable(input.getIndexTenor())
				.map(x -> new NestedFieldValueImpl(indexTenorField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indexTenorField, Optional.empty()));
			FieldValue floatingRateMultiplierSchedule = Optional.ofNullable(input.getFloatingRateMultiplierSchedule())
				.map(x -> new NestedFieldValueImpl(floatingRateMultiplierScheduleField, Optional.of(scheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floatingRateMultiplierScheduleField, Optional.empty()));
			FieldValue spreadSchedule = Optional.ofNullable(input.getSpreadSchedule())
				.map(x -> x.stream()
					.map(_x -> spreadScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(spreadScheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(spreadScheduleField, Optional.empty()));
			FieldValue rateTreatment = new FieldValueImpl(rateTreatmentField, Optional.ofNullable(input.getRateTreatment()));
			FieldValue capRateSchedule = Optional.ofNullable(input.getCapRateSchedule())
				.map(x -> x.stream()
					.map(_x -> strikeScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(capRateScheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(capRateScheduleField, Optional.empty()));
			FieldValue floorRateSchedule = Optional.ofNullable(input.getFloorRateSchedule())
				.map(x -> x.stream()
					.map(_x -> strikeScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(floorRateScheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(floorRateScheduleField, Optional.empty()));
			return Arrays.asList(
				floatingRateIndex,
				indexTenor,
				floatingRateMultiplierSchedule,
				spreadSchedule,
				rateTreatment,
				capRateSchedule,
				floorRateSchedule
			);
		}
	}
}
