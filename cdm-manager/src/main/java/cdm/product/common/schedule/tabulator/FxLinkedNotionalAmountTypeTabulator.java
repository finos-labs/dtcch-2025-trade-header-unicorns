package cdm.product.common.schedule.tabulator;

import cdm.product.common.schedule.FxLinkedNotionalAmount;
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


@ImplementedBy(FxLinkedNotionalAmountTypeTabulator.Impl.class)
public interface FxLinkedNotionalAmountTypeTabulator extends Tabulator<FxLinkedNotionalAmount> {
	@Singleton
	class Impl implements FxLinkedNotionalAmountTypeTabulator {
		private final Field resetDateField;
		private final Field adjustedFxSpotFixingDateField;
		private final Field observedFxSpotRateField;
		private final Field notionalAmountField;

		public Impl() {
			this.resetDateField = new FieldImpl(
				"resetDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedFxSpotFixingDateField = new FieldImpl(
				"adjustedFxSpotFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observedFxSpotRateField = new FieldImpl(
				"observedFxSpotRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notionalAmountField = new FieldImpl(
				"notionalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxLinkedNotionalAmount input) {
			FieldValue resetDate = new FieldValueImpl(resetDateField, Optional.ofNullable(input.getResetDate()));
			FieldValue adjustedFxSpotFixingDate = new FieldValueImpl(adjustedFxSpotFixingDateField, Optional.ofNullable(input.getAdjustedFxSpotFixingDate()));
			FieldValue observedFxSpotRate = new FieldValueImpl(observedFxSpotRateField, Optional.ofNullable(input.getObservedFxSpotRate()));
			FieldValue notionalAmount = new FieldValueImpl(notionalAmountField, Optional.ofNullable(input.getNotionalAmount()));
			return Arrays.asList(
				resetDate,
				adjustedFxSpotFixingDate,
				observedFxSpotRate,
				notionalAmount
			);
		}
	}
}
