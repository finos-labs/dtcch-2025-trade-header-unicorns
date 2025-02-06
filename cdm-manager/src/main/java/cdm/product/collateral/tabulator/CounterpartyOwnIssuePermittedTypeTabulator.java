package cdm.product.collateral.tabulator;

import cdm.product.collateral.CounterpartyOwnIssuePermitted;
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


@ImplementedBy(CounterpartyOwnIssuePermittedTypeTabulator.Impl.class)
public interface CounterpartyOwnIssuePermittedTypeTabulator extends Tabulator<CounterpartyOwnIssuePermitted> {
	@Singleton
	class Impl implements CounterpartyOwnIssuePermittedTypeTabulator {
		private final Field counterpartyOwnIssuePermittedField;

		public Impl() {
			this.counterpartyOwnIssuePermittedField = new FieldImpl(
				"counterpartyOwnIssuePermitted",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CounterpartyOwnIssuePermitted input) {
			FieldValue counterpartyOwnIssuePermitted = new FieldValueImpl(counterpartyOwnIssuePermittedField, Optional.ofNullable(input.getCounterpartyOwnIssuePermitted()));
			return Arrays.asList(
				counterpartyOwnIssuePermitted
			);
		}
	}
}
