package cdm.product.template.tabulator;

import cdm.product.template.MandatoryEarlyTerminationAdjustedDates;
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


@ImplementedBy(MandatoryEarlyTerminationAdjustedDatesTypeTabulator.Impl.class)
public interface MandatoryEarlyTerminationAdjustedDatesTypeTabulator extends Tabulator<MandatoryEarlyTerminationAdjustedDates> {
	@Singleton
	class Impl implements MandatoryEarlyTerminationAdjustedDatesTypeTabulator {
		private final Field adjustedEarlyTerminationDateField;
		private final Field adjustedCashSettlementValuationDateField;
		private final Field adjustedCashSettlementPaymentDateField;

		public Impl() {
			this.adjustedEarlyTerminationDateField = new FieldImpl(
				"adjustedEarlyTerminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedCashSettlementValuationDateField = new FieldImpl(
				"adjustedCashSettlementValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedCashSettlementPaymentDateField = new FieldImpl(
				"adjustedCashSettlementPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MandatoryEarlyTerminationAdjustedDates input) {
			FieldValue adjustedEarlyTerminationDate = new FieldValueImpl(adjustedEarlyTerminationDateField, Optional.ofNullable(input.getAdjustedEarlyTerminationDate()));
			FieldValue adjustedCashSettlementValuationDate = new FieldValueImpl(adjustedCashSettlementValuationDateField, Optional.ofNullable(input.getAdjustedCashSettlementValuationDate()));
			FieldValue adjustedCashSettlementPaymentDate = new FieldValueImpl(adjustedCashSettlementPaymentDateField, Optional.ofNullable(input.getAdjustedCashSettlementPaymentDate()));
			return Arrays.asList(
				adjustedEarlyTerminationDate,
				adjustedCashSettlementValuationDate,
				adjustedCashSettlementPaymentDate
			);
		}
	}
}
