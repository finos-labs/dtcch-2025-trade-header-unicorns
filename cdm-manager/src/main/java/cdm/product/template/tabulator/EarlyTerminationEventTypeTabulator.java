package cdm.product.template.tabulator;

import cdm.product.template.EarlyTerminationEvent;
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


@ImplementedBy(EarlyTerminationEventTypeTabulator.Impl.class)
public interface EarlyTerminationEventTypeTabulator extends Tabulator<EarlyTerminationEvent> {
	@Singleton
	class Impl implements EarlyTerminationEventTypeTabulator {
		private final Field adjustedExerciseDateField;
		private final Field adjustedEarlyTerminationDateField;
		private final Field adjustedCashSettlementValuationDateField;
		private final Field adjustedCashSettlementPaymentDateField;
		private final Field adjustedExerciseFeePaymentDateField;

		public Impl() {
			this.adjustedExerciseDateField = new FieldImpl(
				"adjustedExerciseDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
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
			this.adjustedExerciseFeePaymentDateField = new FieldImpl(
				"adjustedExerciseFeePaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EarlyTerminationEvent input) {
			FieldValue adjustedExerciseDate = new FieldValueImpl(adjustedExerciseDateField, Optional.ofNullable(input.getAdjustedExerciseDate()));
			FieldValue adjustedEarlyTerminationDate = new FieldValueImpl(adjustedEarlyTerminationDateField, Optional.ofNullable(input.getAdjustedEarlyTerminationDate()));
			FieldValue adjustedCashSettlementValuationDate = new FieldValueImpl(adjustedCashSettlementValuationDateField, Optional.ofNullable(input.getAdjustedCashSettlementValuationDate()));
			FieldValue adjustedCashSettlementPaymentDate = new FieldValueImpl(adjustedCashSettlementPaymentDateField, Optional.ofNullable(input.getAdjustedCashSettlementPaymentDate()));
			FieldValue adjustedExerciseFeePaymentDate = new FieldValueImpl(adjustedExerciseFeePaymentDateField, Optional.ofNullable(input.getAdjustedExerciseFeePaymentDate()));
			return Arrays.asList(
				adjustedExerciseDate,
				adjustedEarlyTerminationDate,
				adjustedCashSettlementValuationDate,
				adjustedCashSettlementPaymentDate,
				adjustedExerciseFeePaymentDate
			);
		}
	}
}
