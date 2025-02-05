package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.ClosedState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(ClosedStateTypeTabulator.Impl.class)
public interface ClosedStateTypeTabulator extends Tabulator<ClosedState> {
	@Singleton
	class Impl implements ClosedStateTypeTabulator {
		private final Field stateField;
		private final Field activityDateField;
		private final Field effectiveDateField;
		private final Field lastPaymentDateField;

		public Impl() {
			this.stateField = new FieldImpl(
				"state",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.activityDateField = new FieldImpl(
				"activityDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lastPaymentDateField = new FieldImpl(
				"lastPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ClosedState input) {
			FieldValue state = new FieldValueImpl(stateField, Optional.ofNullable(input.getState()));
			FieldValue activityDate = new FieldValueImpl(activityDateField, Optional.ofNullable(input.getActivityDate()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			FieldValue lastPaymentDate = new FieldValueImpl(lastPaymentDateField, Optional.ofNullable(input.getLastPaymentDate()));
			return Arrays.asList(
				state,
				activityDate,
				effectiveDate,
				lastPaymentDate
			);
		}
	}
}
