package cdm.event.common.tabulator;

import cdm.event.common.State;
import cdm.legaldocumentation.common.tabulator.ClosedStateTypeTabulator;
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


@ImplementedBy(StateTypeTabulator.Impl.class)
public interface StateTypeTabulator extends Tabulator<State> {
	@Singleton
	class Impl implements StateTypeTabulator {
		private final Field closedStateField;
		private final Field positionStateField;

		private final ClosedStateTypeTabulator closedStateTypeTabulator;

		@Inject
		public Impl(ClosedStateTypeTabulator closedStateTypeTabulator) {
			this.closedStateTypeTabulator = closedStateTypeTabulator;
			this.closedStateField = new FieldImpl(
				"closedState",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.positionStateField = new FieldImpl(
				"positionState",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(State input) {
			FieldValue closedState = Optional.ofNullable(input.getClosedState())
				.map(x -> new NestedFieldValueImpl(closedStateField, Optional.of(closedStateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(closedStateField, Optional.empty()));
			FieldValue positionState = new FieldValueImpl(positionStateField, Optional.ofNullable(input.getPositionState()));
			return Arrays.asList(
				closedState,
				positionState
			);
		}
	}
}
