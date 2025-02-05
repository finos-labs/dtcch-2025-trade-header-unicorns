package cdm.observable.asset.tabulator;

import cdm.observable.asset.PriceComposite;
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


@ImplementedBy(PriceCompositeTypeTabulator.Impl.class)
public interface PriceCompositeTypeTabulator extends Tabulator<PriceComposite> {
	@Singleton
	class Impl implements PriceCompositeTypeTabulator {
		private final Field baseValueField;
		private final Field operandField;
		private final Field arithmeticOperatorField;
		private final Field operandTypeField;

		public Impl() {
			this.baseValueField = new FieldImpl(
				"baseValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.operandField = new FieldImpl(
				"operand",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.arithmeticOperatorField = new FieldImpl(
				"arithmeticOperator",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.operandTypeField = new FieldImpl(
				"operandType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceComposite input) {
			FieldValue baseValue = new FieldValueImpl(baseValueField, Optional.ofNullable(input.getBaseValue()));
			FieldValue operand = new FieldValueImpl(operandField, Optional.ofNullable(input.getOperand()));
			FieldValue arithmeticOperator = new FieldValueImpl(arithmeticOperatorField, Optional.ofNullable(input.getArithmeticOperator()));
			FieldValue operandType = new FieldValueImpl(operandTypeField, Optional.ofNullable(input.getOperandType()));
			return Arrays.asList(
				baseValue,
				operand,
				arithmeticOperator,
				operandType
			);
		}
	}
}
