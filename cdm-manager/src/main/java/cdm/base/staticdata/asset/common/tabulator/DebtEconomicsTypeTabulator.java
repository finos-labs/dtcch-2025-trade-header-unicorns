package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.DebtEconomics;
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


@ImplementedBy(DebtEconomicsTypeTabulator.Impl.class)
public interface DebtEconomicsTypeTabulator extends Tabulator<DebtEconomics> {
	@Singleton
	class Impl implements DebtEconomicsTypeTabulator {
		private final Field debtSeniorityField;
		private final Field debtInterestField;
		private final Field debtPrincipalField;

		public Impl() {
			this.debtSeniorityField = new FieldImpl(
				"debtSeniority",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtInterestField = new FieldImpl(
				"debtInterest",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtPrincipalField = new FieldImpl(
				"debtPrincipal",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DebtEconomics input) {
			FieldValue debtSeniority = new FieldValueImpl(debtSeniorityField, Optional.ofNullable(input.getDebtSeniority()));
			FieldValue debtInterest = new FieldValueImpl(debtInterestField, Optional.ofNullable(input.getDebtInterest()));
			FieldValue debtPrincipal = new FieldValueImpl(debtPrincipalField, Optional.ofNullable(input.getDebtPrincipal()));
			return Arrays.asList(
				debtSeniority,
				debtInterest,
				debtPrincipal
			);
		}
	}
}
