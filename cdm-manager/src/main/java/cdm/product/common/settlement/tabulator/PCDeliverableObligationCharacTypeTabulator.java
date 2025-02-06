package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.PCDeliverableObligationCharac;
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


@ImplementedBy(PCDeliverableObligationCharacTypeTabulator.Impl.class)
public interface PCDeliverableObligationCharacTypeTabulator extends Tabulator<PCDeliverableObligationCharac> {
	@Singleton
	class Impl implements PCDeliverableObligationCharacTypeTabulator {
		private final Field applicableField;
		private final Field partialCashSettlementField;

		public Impl() {
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partialCashSettlementField = new FieldImpl(
				"partialCashSettlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PCDeliverableObligationCharac input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue partialCashSettlement = new FieldValueImpl(partialCashSettlementField, Optional.ofNullable(input.getPartialCashSettlement()));
			return Arrays.asList(
				applicable,
				partialCashSettlement
			);
		}
	}
}
