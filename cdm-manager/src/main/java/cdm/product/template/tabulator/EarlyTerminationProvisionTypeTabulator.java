package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.product.template.EarlyTerminationProvision;
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


@ImplementedBy(EarlyTerminationProvisionTypeTabulator.Impl.class)
public interface EarlyTerminationProvisionTypeTabulator extends Tabulator<EarlyTerminationProvision> {
	@Singleton
	class Impl implements EarlyTerminationProvisionTypeTabulator {
		private final Field mandatoryEarlyTerminationField;
		private final Field mandatoryEarlyTerminationDateTenorField;
		private final Field optionalEarlyTerminationField;
		private final Field optionalEarlyTerminationParametersField;

		private final MandatoryEarlyTerminationTypeTabulator mandatoryEarlyTerminationTypeTabulator;
		private final PeriodTypeTabulator periodTypeTabulator;
		private final OptionalEarlyTerminationTypeTabulator optionalEarlyTerminationTypeTabulator;
		private final ExercisePeriodTypeTabulator exercisePeriodTypeTabulator;

		@Inject
		public Impl(MandatoryEarlyTerminationTypeTabulator mandatoryEarlyTerminationTypeTabulator, PeriodTypeTabulator periodTypeTabulator, OptionalEarlyTerminationTypeTabulator optionalEarlyTerminationTypeTabulator, ExercisePeriodTypeTabulator exercisePeriodTypeTabulator) {
			this.mandatoryEarlyTerminationTypeTabulator = mandatoryEarlyTerminationTypeTabulator;
			this.periodTypeTabulator = periodTypeTabulator;
			this.optionalEarlyTerminationTypeTabulator = optionalEarlyTerminationTypeTabulator;
			this.exercisePeriodTypeTabulator = exercisePeriodTypeTabulator;
			this.mandatoryEarlyTerminationField = new FieldImpl(
				"mandatoryEarlyTermination",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mandatoryEarlyTerminationDateTenorField = new FieldImpl(
				"mandatoryEarlyTerminationDateTenor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionalEarlyTerminationField = new FieldImpl(
				"optionalEarlyTermination",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionalEarlyTerminationParametersField = new FieldImpl(
				"optionalEarlyTerminationParameters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EarlyTerminationProvision input) {
			FieldValue mandatoryEarlyTermination = Optional.ofNullable(input.getMandatoryEarlyTermination())
				.map(x -> new NestedFieldValueImpl(mandatoryEarlyTerminationField, Optional.of(mandatoryEarlyTerminationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(mandatoryEarlyTerminationField, Optional.empty()));
			FieldValue mandatoryEarlyTerminationDateTenor = Optional.ofNullable(input.getMandatoryEarlyTerminationDateTenor())
				.map(x -> new NestedFieldValueImpl(mandatoryEarlyTerminationDateTenorField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(mandatoryEarlyTerminationDateTenorField, Optional.empty()));
			FieldValue optionalEarlyTermination = Optional.ofNullable(input.getOptionalEarlyTermination())
				.map(x -> new NestedFieldValueImpl(optionalEarlyTerminationField, Optional.of(optionalEarlyTerminationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(optionalEarlyTerminationField, Optional.empty()));
			FieldValue optionalEarlyTerminationParameters = Optional.ofNullable(input.getOptionalEarlyTerminationParameters())
				.map(x -> new NestedFieldValueImpl(optionalEarlyTerminationParametersField, Optional.of(exercisePeriodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(optionalEarlyTerminationParametersField, Optional.empty()));
			return Arrays.asList(
				mandatoryEarlyTermination,
				mandatoryEarlyTerminationDateTenor,
				optionalEarlyTermination,
				optionalEarlyTerminationParameters
			);
		}
	}
}
