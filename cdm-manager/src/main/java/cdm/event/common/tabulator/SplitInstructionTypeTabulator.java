package cdm.event.common.tabulator;

import cdm.event.common.SplitInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(SplitInstructionTypeTabulator.Impl.class)
public interface SplitInstructionTypeTabulator extends Tabulator<SplitInstruction> {
	@Singleton
	class Impl implements SplitInstructionTypeTabulator {
		private final Field breakdownField;

		private final PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator;

		@Inject
		public Impl(PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator) {
			this.primitiveInstructionTypeTabulator = primitiveInstructionTypeTabulator;
			this.breakdownField = new FieldImpl(
				"breakdown",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SplitInstruction input) {
			FieldValue breakdown = Optional.ofNullable(input.getBreakdown())
				.map(x -> x.stream()
					.map(_x -> primitiveInstructionTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(breakdownField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(breakdownField, Optional.empty()));
			return Arrays.asList(
				breakdown
			);
		}
	}
}
