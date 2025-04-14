package cdm.product.asset.tabulator;

import cdm.product.asset.AdditionalFixedPayments;
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


@ImplementedBy(AdditionalFixedPaymentsTypeTabulator.Impl.class)
public interface AdditionalFixedPaymentsTypeTabulator extends Tabulator<AdditionalFixedPayments> {
	@Singleton
	class Impl implements AdditionalFixedPaymentsTypeTabulator {
		private final Field interestShortfallReimbursementField;
		private final Field principalShortfallReimbursementField;
		private final Field writedownReimbursementField;

		public Impl() {
			this.interestShortfallReimbursementField = new FieldImpl(
				"interestShortfallReimbursement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.principalShortfallReimbursementField = new FieldImpl(
				"principalShortfallReimbursement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.writedownReimbursementField = new FieldImpl(
				"writedownReimbursement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AdditionalFixedPayments input) {
			FieldValue interestShortfallReimbursement = new FieldValueImpl(interestShortfallReimbursementField, Optional.ofNullable(input.getInterestShortfallReimbursement()));
			FieldValue principalShortfallReimbursement = new FieldValueImpl(principalShortfallReimbursementField, Optional.ofNullable(input.getPrincipalShortfallReimbursement()));
			FieldValue writedownReimbursement = new FieldValueImpl(writedownReimbursementField, Optional.ofNullable(input.getWritedownReimbursement()));
			return Arrays.asList(
				interestShortfallReimbursement,
				principalShortfallReimbursement,
				writedownReimbursement
			);
		}
	}
}
