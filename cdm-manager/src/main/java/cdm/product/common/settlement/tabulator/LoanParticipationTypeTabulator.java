package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.LoanParticipation;
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


@ImplementedBy(LoanParticipationTypeTabulator.Impl.class)
public interface LoanParticipationTypeTabulator extends Tabulator<LoanParticipation> {
	@Singleton
	class Impl implements LoanParticipationTypeTabulator {
		private final Field applicableField;
		private final Field partialCashSettlementField;
		private final Field qualifyingParticipationSellerField;

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
			this.qualifyingParticipationSellerField = new FieldImpl(
				"qualifyingParticipationSeller",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(LoanParticipation input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue partialCashSettlement = new FieldValueImpl(partialCashSettlementField, Optional.ofNullable(input.getPartialCashSettlement()));
			FieldValue qualifyingParticipationSeller = new FieldValueImpl(qualifyingParticipationSellerField, Optional.ofNullable(input.getQualifyingParticipationSeller()));
			return Arrays.asList(
				applicable,
				partialCashSettlement,
				qualifyingParticipationSeller
			);
		}
	}
}
