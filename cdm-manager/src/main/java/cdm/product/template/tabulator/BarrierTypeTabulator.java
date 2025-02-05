package cdm.product.template.tabulator;

import cdm.observable.event.tabulator.TriggerEventTypeTabulator;
import cdm.product.template.Barrier;
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


@ImplementedBy(BarrierTypeTabulator.Impl.class)
public interface BarrierTypeTabulator extends Tabulator<Barrier> {
	@Singleton
	class Impl implements BarrierTypeTabulator {
		private final Field barrierCapField;
		private final Field barrierFloorField;

		private final TriggerEventTypeTabulator triggerEventTypeTabulator;

		@Inject
		public Impl(TriggerEventTypeTabulator triggerEventTypeTabulator) {
			this.triggerEventTypeTabulator = triggerEventTypeTabulator;
			this.barrierCapField = new FieldImpl(
				"barrierCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.barrierFloorField = new FieldImpl(
				"barrierFloor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Barrier input) {
			FieldValue barrierCap = Optional.ofNullable(input.getBarrierCap())
				.map(x -> new NestedFieldValueImpl(barrierCapField, Optional.of(triggerEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(barrierCapField, Optional.empty()));
			FieldValue barrierFloor = Optional.ofNullable(input.getBarrierFloor())
				.map(x -> new NestedFieldValueImpl(barrierFloorField, Optional.of(triggerEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(barrierFloorField, Optional.empty()));
			return Arrays.asList(
				barrierCap,
				barrierFloor
			);
		}
	}
}
