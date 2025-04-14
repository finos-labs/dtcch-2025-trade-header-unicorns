package cdm.event.workflow.tabulator;

import cdm.event.workflow.CreditLimitUtilisationPosition;
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


@ImplementedBy(CreditLimitUtilisationPositionTypeTabulator.Impl.class)
public interface CreditLimitUtilisationPositionTypeTabulator extends Tabulator<CreditLimitUtilisationPosition> {
	@Singleton
	class Impl implements CreditLimitUtilisationPositionTypeTabulator {
		private final Field shortPositionField;
		private final Field longPositionField;
		private final Field globalField;

		public Impl() {
			this.shortPositionField = new FieldImpl(
				"shortPosition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.longPositionField = new FieldImpl(
				"longPosition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.globalField = new FieldImpl(
				"global",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditLimitUtilisationPosition input) {
			FieldValue shortPosition = new FieldValueImpl(shortPositionField, Optional.ofNullable(input.getShortPosition()));
			FieldValue longPosition = new FieldValueImpl(longPositionField, Optional.ofNullable(input.getLongPosition()));
			FieldValue global = new FieldValueImpl(globalField, Optional.ofNullable(input.getGlobal()));
			return Arrays.asList(
				shortPosition,
				longPosition,
				global
			);
		}
	}
}
