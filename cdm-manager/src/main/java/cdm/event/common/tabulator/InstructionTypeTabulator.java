package cdm.event.common.tabulator;

import cdm.event.common.Instruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(InstructionTypeTabulator.Impl.class)
public interface InstructionTypeTabulator extends Tabulator<Instruction> {
	@Singleton
	class Impl implements InstructionTypeTabulator {
		private final Field primitiveInstructionField;
		private final Field beforeField;

		private final PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator;
		private final TradeStateTypeTabulator tradeStateTypeTabulator;

		@Inject
		public Impl(PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator, TradeStateTypeTabulator tradeStateTypeTabulator) {
			this.primitiveInstructionTypeTabulator = primitiveInstructionTypeTabulator;
			this.tradeStateTypeTabulator = tradeStateTypeTabulator;
			this.primitiveInstructionField = new FieldImpl(
				"primitiveInstruction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.beforeField = new FieldImpl(
				"before",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Instruction input) {
			FieldValue primitiveInstruction = Optional.ofNullable(input.getPrimitiveInstruction())
				.map(x -> new NestedFieldValueImpl(primitiveInstructionField, Optional.of(primitiveInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(primitiveInstructionField, Optional.empty()));
			FieldValue before = Optional.ofNullable(input.getBefore())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(beforeField, Optional.of(tradeStateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(beforeField, Optional.empty()));
			return Arrays.asList(
				primitiveInstruction,
				before
			);
		}
	}
}
