package cdm.event.common.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrAdjustedDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.event.common.ExerciseInstruction;
import cdm.product.template.tabulator.OptionPayoutTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ExerciseInstructionTypeTabulator.Impl.class)
public interface ExerciseInstructionTypeTabulator extends Tabulator<ExerciseInstruction> {
	@Singleton
	class Impl implements ExerciseInstructionTypeTabulator {
		private final Field exerciseQuantityField;
		private final Field exerciseOptionField;
		private final Field exerciseDateField;
		private final Field exerciseTimeField;
		private final Field replacementTradeIdentifierField;

		private final PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator;
		private final OptionPayoutTypeTabulator optionPayoutTypeTabulator;
		private final AdjustableOrAdjustedDateTypeTabulator adjustableOrAdjustedDateTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;
		private final TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator;

		@Inject
		public Impl(PrimitiveInstructionTypeTabulator primitiveInstructionTypeTabulator, OptionPayoutTypeTabulator optionPayoutTypeTabulator, AdjustableOrAdjustedDateTypeTabulator adjustableOrAdjustedDateTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator, TradeIdentifierTypeTabulator tradeIdentifierTypeTabulator) {
			this.primitiveInstructionTypeTabulator = primitiveInstructionTypeTabulator;
			this.optionPayoutTypeTabulator = optionPayoutTypeTabulator;
			this.adjustableOrAdjustedDateTypeTabulator = adjustableOrAdjustedDateTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.tradeIdentifierTypeTabulator = tradeIdentifierTypeTabulator;
			this.exerciseQuantityField = new FieldImpl(
				"exerciseQuantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseOptionField = new FieldImpl(
				"exerciseOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseDateField = new FieldImpl(
				"exerciseDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseTimeField = new FieldImpl(
				"exerciseTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.replacementTradeIdentifierField = new FieldImpl(
				"replacementTradeIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExerciseInstruction input) {
			FieldValue exerciseQuantity = Optional.ofNullable(input.getExerciseQuantity())
				.map(x -> new NestedFieldValueImpl(exerciseQuantityField, Optional.of(primitiveInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseQuantityField, Optional.empty()));
			FieldValue exerciseOption = Optional.ofNullable(input.getExerciseOption())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(exerciseOptionField, Optional.of(optionPayoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseOptionField, Optional.empty()));
			FieldValue exerciseDate = Optional.ofNullable(input.getExerciseDate())
				.map(x -> new NestedFieldValueImpl(exerciseDateField, Optional.of(adjustableOrAdjustedDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseDateField, Optional.empty()));
			FieldValue exerciseTime = Optional.ofNullable(input.getExerciseTime())
				.map(x -> new NestedFieldValueImpl(exerciseTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseTimeField, Optional.empty()));
			FieldValue replacementTradeIdentifier = Optional.ofNullable(input.getReplacementTradeIdentifier())
				.map(x -> x.stream()
					.map(_x -> tradeIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(replacementTradeIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(replacementTradeIdentifierField, Optional.empty()));
			return Arrays.asList(
				exerciseQuantity,
				exerciseOption,
				exerciseDate,
				exerciseTime,
				replacementTradeIdentifier
			);
		}
	}
}
