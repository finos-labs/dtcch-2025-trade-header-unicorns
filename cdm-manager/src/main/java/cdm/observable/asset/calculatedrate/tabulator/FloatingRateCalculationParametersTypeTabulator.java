package cdm.observable.asset.calculatedrate.tabulator;

import cdm.base.datetime.tabulator.BusinessCentersTypeTabulator;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(FloatingRateCalculationParametersTypeTabulator.Impl.class)
public interface FloatingRateCalculationParametersTypeTabulator extends Tabulator<FloatingRateCalculationParameters> {
	@Singleton
	class Impl implements FloatingRateCalculationParametersTypeTabulator {
		private final Field calculationMethodField;
		private final Field observationShiftCalculationField;
		private final Field lookbackCalculationField;
		private final Field lockoutCalculationField;
		private final Field applicableBusinessDaysField;
		private final Field observationParametersField;

		private final ObservationShiftCalculationTypeTabulator observationShiftCalculationTypeTabulator;
		private final OffsetCalculationTypeTabulator offsetCalculationTypeTabulator;
		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;
		private final ObservationParametersTypeTabulator observationParametersTypeTabulator;

		@Inject
		public Impl(ObservationShiftCalculationTypeTabulator observationShiftCalculationTypeTabulator, OffsetCalculationTypeTabulator offsetCalculationTypeTabulator, BusinessCentersTypeTabulator businessCentersTypeTabulator, ObservationParametersTypeTabulator observationParametersTypeTabulator) {
			this.observationShiftCalculationTypeTabulator = observationShiftCalculationTypeTabulator;
			this.offsetCalculationTypeTabulator = offsetCalculationTypeTabulator;
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.observationParametersTypeTabulator = observationParametersTypeTabulator;
			this.calculationMethodField = new FieldImpl(
				"calculationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationShiftCalculationField = new FieldImpl(
				"observationShiftCalculation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lookbackCalculationField = new FieldImpl(
				"lookbackCalculation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lockoutCalculationField = new FieldImpl(
				"lockoutCalculation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.applicableBusinessDaysField = new FieldImpl(
				"applicableBusinessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationParametersField = new FieldImpl(
				"observationParameters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FloatingRateCalculationParameters input) {
			FieldValue calculationMethod = new FieldValueImpl(calculationMethodField, Optional.ofNullable(input.getCalculationMethod()));
			FieldValue observationShiftCalculation = Optional.ofNullable(input.getObservationShiftCalculation())
				.map(x -> new NestedFieldValueImpl(observationShiftCalculationField, Optional.of(observationShiftCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationShiftCalculationField, Optional.empty()));
			FieldValue lookbackCalculation = Optional.ofNullable(input.getLookbackCalculation())
				.map(x -> new NestedFieldValueImpl(lookbackCalculationField, Optional.of(offsetCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lookbackCalculationField, Optional.empty()));
			FieldValue lockoutCalculation = Optional.ofNullable(input.getLockoutCalculation())
				.map(x -> new NestedFieldValueImpl(lockoutCalculationField, Optional.of(offsetCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lockoutCalculationField, Optional.empty()));
			FieldValue applicableBusinessDays = Optional.ofNullable(input.getApplicableBusinessDays())
				.map(x -> new NestedFieldValueImpl(applicableBusinessDaysField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(applicableBusinessDaysField, Optional.empty()));
			FieldValue observationParameters = Optional.ofNullable(input.getObservationParameters())
				.map(x -> new NestedFieldValueImpl(observationParametersField, Optional.of(observationParametersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationParametersField, Optional.empty()));
			return Arrays.asList(
				calculationMethod,
				observationShiftCalculation,
				lookbackCalculation,
				lockoutCalculation,
				applicableBusinessDays,
				observationParameters
			);
		}
	}
}
