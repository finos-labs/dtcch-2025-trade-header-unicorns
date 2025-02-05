package cdm.event.workflow.tabulator;

import cdm.event.workflow.CreditLimitUtilisation;
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


@ImplementedBy(CreditLimitUtilisationTypeTabulator.Impl.class)
public interface CreditLimitUtilisationTypeTabulator extends Tabulator<CreditLimitUtilisation> {
	@Singleton
	class Impl implements CreditLimitUtilisationTypeTabulator {
		private final Field executedField;
		private final Field pendingField;

		private final CreditLimitUtilisationPositionTypeTabulator creditLimitUtilisationPositionTypeTabulator;

		@Inject
		public Impl(CreditLimitUtilisationPositionTypeTabulator creditLimitUtilisationPositionTypeTabulator) {
			this.creditLimitUtilisationPositionTypeTabulator = creditLimitUtilisationPositionTypeTabulator;
			this.executedField = new FieldImpl(
				"executed",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.pendingField = new FieldImpl(
				"pending",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditLimitUtilisation input) {
			FieldValue executed = Optional.ofNullable(input.getExecuted())
				.map(x -> new NestedFieldValueImpl(executedField, Optional.of(creditLimitUtilisationPositionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executedField, Optional.empty()));
			FieldValue pending = Optional.ofNullable(input.getPending())
				.map(x -> new NestedFieldValueImpl(pendingField, Optional.of(creditLimitUtilisationPositionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(pendingField, Optional.empty()));
			return Arrays.asList(
				executed,
				pending
			);
		}
	}
}
