package cdm.product.common.settlement.tabulator;

import cdm.product.common.schedule.tabulator.FxLinkedNotionalScheduleTypeTabulator;
import cdm.product.common.settlement.QuantityMultiplier;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(QuantityMultiplierTypeTabulator.Impl.class)
public interface QuantityMultiplierTypeTabulator extends Tabulator<QuantityMultiplier> {
	@Singleton
	class Impl implements QuantityMultiplierTypeTabulator {
		private final Field fxLinkedNotionalScheduleField;
		private final Field multiplierValueField;

		private final FxLinkedNotionalScheduleTypeTabulator fxLinkedNotionalScheduleTypeTabulator;

		@Inject
		public Impl(FxLinkedNotionalScheduleTypeTabulator fxLinkedNotionalScheduleTypeTabulator) {
			this.fxLinkedNotionalScheduleTypeTabulator = fxLinkedNotionalScheduleTypeTabulator;
			this.fxLinkedNotionalScheduleField = new FieldImpl(
				"fxLinkedNotionalSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multiplierValueField = new FieldImpl(
				"multiplierValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(QuantityMultiplier input) {
			FieldValue fxLinkedNotionalSchedule = Optional.ofNullable(input.getFxLinkedNotionalSchedule())
				.map(x -> new NestedFieldValueImpl(fxLinkedNotionalScheduleField, Optional.of(fxLinkedNotionalScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxLinkedNotionalScheduleField, Optional.empty()));
			FieldValue multiplierValue = new FieldValueImpl(multiplierValueField, Optional.ofNullable(input.getMultiplierValue()));
			return Arrays.asList(
				fxLinkedNotionalSchedule,
				multiplierValue
			);
		}
	}
}
