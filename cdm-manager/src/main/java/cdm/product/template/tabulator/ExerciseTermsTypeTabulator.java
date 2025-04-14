package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.AdjustableOrRelativeDatesTypeTabulator;
import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.product.template.ExerciseTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ExerciseTermsTypeTabulator.Impl.class)
public interface ExerciseTermsTypeTabulator extends Tabulator<ExerciseTerms> {
	@Singleton
	class Impl implements ExerciseTermsTypeTabulator {
		private final Field styleField;
		private final Field commencementDateField;
		private final Field exerciseDatesField;
		private final Field expirationDateField;
		private final Field relevantUnderlyingDateField;
		private final Field earliestExerciseTimeField;
		private final Field latestExerciseTimeField;
		private final Field expirationTimeField;
		private final Field expirationTimeTypeField;
		private final Field multipleExerciseField;
		private final Field exerciseFeeScheduleField;
		private final Field exerciseProcedureField;
		private final Field exerciseFeeField;
		private final Field partialExerciseField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;
		private final MultipleExerciseTypeTabulator multipleExerciseTypeTabulator;
		private final ExerciseFeeScheduleTypeTabulator exerciseFeeScheduleTypeTabulator;
		private final ExerciseProcedureTypeTabulator exerciseProcedureTypeTabulator;
		private final ExerciseFeeTypeTabulator exerciseFeeTypeTabulator;
		private final PartialExerciseTypeTabulator partialExerciseTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator, MultipleExerciseTypeTabulator multipleExerciseTypeTabulator, ExerciseFeeScheduleTypeTabulator exerciseFeeScheduleTypeTabulator, ExerciseProcedureTypeTabulator exerciseProcedureTypeTabulator, ExerciseFeeTypeTabulator exerciseFeeTypeTabulator, PartialExerciseTypeTabulator partialExerciseTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.adjustableOrRelativeDatesTypeTabulator = adjustableOrRelativeDatesTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.multipleExerciseTypeTabulator = multipleExerciseTypeTabulator;
			this.exerciseFeeScheduleTypeTabulator = exerciseFeeScheduleTypeTabulator;
			this.exerciseProcedureTypeTabulator = exerciseProcedureTypeTabulator;
			this.exerciseFeeTypeTabulator = exerciseFeeTypeTabulator;
			this.partialExerciseTypeTabulator = partialExerciseTypeTabulator;
			this.styleField = new FieldImpl(
				"style",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commencementDateField = new FieldImpl(
				"commencementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseDatesField = new FieldImpl(
				"exerciseDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.expirationDateField = new FieldImpl(
				"expirationDate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relevantUnderlyingDateField = new FieldImpl(
				"relevantUnderlyingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.earliestExerciseTimeField = new FieldImpl(
				"earliestExerciseTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.latestExerciseTimeField = new FieldImpl(
				"latestExerciseTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.expirationTimeField = new FieldImpl(
				"expirationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.expirationTimeTypeField = new FieldImpl(
				"expirationTimeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multipleExerciseField = new FieldImpl(
				"multipleExercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseFeeScheduleField = new FieldImpl(
				"exerciseFeeSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseProcedureField = new FieldImpl(
				"exerciseProcedure",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseFeeField = new FieldImpl(
				"exerciseFee",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partialExerciseField = new FieldImpl(
				"partialExercise",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExerciseTerms input) {
			FieldValue style = new FieldValueImpl(styleField, Optional.ofNullable(input.getStyle()));
			FieldValue commencementDate = Optional.ofNullable(input.getCommencementDate())
				.map(x -> new NestedFieldValueImpl(commencementDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(commencementDateField, Optional.empty()));
			FieldValue exerciseDates = Optional.ofNullable(input.getExerciseDates())
				.map(x -> new NestedFieldValueImpl(exerciseDatesField, Optional.of(adjustableOrRelativeDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseDatesField, Optional.empty()));
			FieldValue expirationDate = Optional.ofNullable(input.getExpirationDate())
				.map(x -> x.stream()
					.map(_x -> adjustableOrRelativeDateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(expirationDateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(expirationDateField, Optional.empty()));
			FieldValue relevantUnderlyingDate = Optional.ofNullable(input.getRelevantUnderlyingDate())
				.map(x -> new NestedFieldValueImpl(relevantUnderlyingDateField, Optional.of(adjustableOrRelativeDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relevantUnderlyingDateField, Optional.empty()));
			FieldValue earliestExerciseTime = Optional.ofNullable(input.getEarliestExerciseTime())
				.map(x -> new NestedFieldValueImpl(earliestExerciseTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(earliestExerciseTimeField, Optional.empty()));
			FieldValue latestExerciseTime = Optional.ofNullable(input.getLatestExerciseTime())
				.map(x -> new NestedFieldValueImpl(latestExerciseTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(latestExerciseTimeField, Optional.empty()));
			FieldValue expirationTime = Optional.ofNullable(input.getExpirationTime())
				.map(x -> new NestedFieldValueImpl(expirationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(expirationTimeField, Optional.empty()));
			FieldValue expirationTimeType = new FieldValueImpl(expirationTimeTypeField, Optional.ofNullable(input.getExpirationTimeType()));
			FieldValue multipleExercise = Optional.ofNullable(input.getMultipleExercise())
				.map(x -> new NestedFieldValueImpl(multipleExerciseField, Optional.of(multipleExerciseTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(multipleExerciseField, Optional.empty()));
			FieldValue exerciseFeeSchedule = Optional.ofNullable(input.getExerciseFeeSchedule())
				.map(x -> new NestedFieldValueImpl(exerciseFeeScheduleField, Optional.of(exerciseFeeScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseFeeScheduleField, Optional.empty()));
			FieldValue exerciseProcedure = Optional.ofNullable(input.getExerciseProcedure())
				.map(x -> new NestedFieldValueImpl(exerciseProcedureField, Optional.of(exerciseProcedureTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseProcedureField, Optional.empty()));
			FieldValue exerciseFee = Optional.ofNullable(input.getExerciseFee())
				.map(x -> new NestedFieldValueImpl(exerciseFeeField, Optional.of(exerciseFeeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseFeeField, Optional.empty()));
			FieldValue partialExercise = Optional.ofNullable(input.getPartialExercise())
				.map(x -> new NestedFieldValueImpl(partialExerciseField, Optional.of(partialExerciseTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partialExerciseField, Optional.empty()));
			return Arrays.asList(
				style,
				commencementDate,
				exerciseDates,
				expirationDate,
				relevantUnderlyingDate,
				earliestExerciseTime,
				latestExerciseTime,
				expirationTime,
				expirationTimeType,
				multipleExercise,
				exerciseFeeSchedule,
				exerciseProcedure,
				exerciseFee,
				partialExercise
			);
		}
	}
}
