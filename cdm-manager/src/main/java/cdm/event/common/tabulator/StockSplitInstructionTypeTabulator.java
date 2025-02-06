package cdm.event.common.tabulator;

import cdm.event.common.StockSplitInstruction;
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


@ImplementedBy(StockSplitInstructionTypeTabulator.Impl.class)
public interface StockSplitInstructionTypeTabulator extends Tabulator<StockSplitInstruction> {
	@Singleton
	class Impl implements StockSplitInstructionTypeTabulator {
		private final Field adjustmentRatioField;
		private final Field effectiveDateField;

		public Impl() {
			this.adjustmentRatioField = new FieldImpl(
				"adjustmentRatio",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StockSplitInstruction input) {
			FieldValue adjustmentRatio = new FieldValueImpl(adjustmentRatioField, Optional.ofNullable(input.getAdjustmentRatio()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			return Arrays.asList(
				adjustmentRatio,
				effectiveDate
			);
		}
	}
}
