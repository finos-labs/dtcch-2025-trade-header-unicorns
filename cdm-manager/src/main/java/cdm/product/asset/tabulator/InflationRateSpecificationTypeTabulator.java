package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.base.math.tabulator.RoundingTypeTabulator;
import cdm.observable.asset.calculatedrate.tabulator.FallbackRateParametersTypeTabulator;
import cdm.observable.asset.calculatedrate.tabulator.FloatingRateCalculationParametersTypeTabulator;
import cdm.observable.asset.tabulator.InterestRateIndexTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.InflationRateSpecification;
import cdm.product.common.schedule.tabulator.RateScheduleTypeTabulator;
import cdm.product.template.tabulator.StrikeScheduleTypeTabulator;
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


@ImplementedBy(InflationRateSpecificationTypeTabulator.Impl.class)
public interface InflationRateSpecificationTypeTabulator extends Tabulator<InflationRateSpecification> {
	@Singleton
	class Impl implements InflationRateSpecificationTypeTabulator {
		private final Field rateOptionField;
		private final Field spreadScheduleField;
		private final Field capRateScheduleField;
		private final Field floorRateScheduleField;
		private final Field floatingRateMultiplierScheduleField;
		private final Field rateTreatmentField;
		private final Field calculationParametersField;
		private final Field fallbackRateField;
		private final Field initialRateField;
		private final Field finalRateRoundingField;
		private final Field averagingMethodField;
		private final Field negativeInterestRateTreatmentField;
		private final Field inflationLagField;
		private final Field indexSourceField;
		private final Field mainPublicationField;
		private final Field interpolationMethodField;
		private final Field initialIndexLevelField;
		private final Field fallbackBondApplicableField;
		private final Field calculationMethodField;
		private final Field calculationStyleField;
		private final Field finalPrincipalExchangeCalculationField;

		private final InterestRateIndexTypeTabulator interestRateIndexTypeTabulator;
		private final SpreadScheduleTypeTabulator spreadScheduleTypeTabulator;
		private final StrikeScheduleTypeTabulator strikeScheduleTypeTabulator;
		private final RateScheduleTypeTabulator rateScheduleTypeTabulator;
		private final FloatingRateCalculationParametersTypeTabulator floatingRateCalculationParametersTypeTabulator;
		private final FallbackRateParametersTypeTabulator fallbackRateParametersTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;
		private final RoundingTypeTabulator roundingTypeTabulator;
		private final OffsetTypeTabulator offsetTypeTabulator;

		@Inject
		public Impl(InterestRateIndexTypeTabulator interestRateIndexTypeTabulator, SpreadScheduleTypeTabulator spreadScheduleTypeTabulator, StrikeScheduleTypeTabulator strikeScheduleTypeTabulator, RateScheduleTypeTabulator rateScheduleTypeTabulator, FloatingRateCalculationParametersTypeTabulator floatingRateCalculationParametersTypeTabulator, FallbackRateParametersTypeTabulator fallbackRateParametersTypeTabulator, PriceTypeTabulator priceTypeTabulator, RoundingTypeTabulator roundingTypeTabulator, OffsetTypeTabulator offsetTypeTabulator) {
			this.interestRateIndexTypeTabulator = interestRateIndexTypeTabulator;
			this.spreadScheduleTypeTabulator = spreadScheduleTypeTabulator;
			this.strikeScheduleTypeTabulator = strikeScheduleTypeTabulator;
			this.rateScheduleTypeTabulator = rateScheduleTypeTabulator;
			this.floatingRateCalculationParametersTypeTabulator = floatingRateCalculationParametersTypeTabulator;
			this.fallbackRateParametersTypeTabulator = fallbackRateParametersTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.roundingTypeTabulator = roundingTypeTabulator;
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.rateOptionField = new FieldImpl(
				"rateOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadScheduleField = new FieldImpl(
				"spreadSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.capRateScheduleField = new FieldImpl(
				"capRateSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floorRateScheduleField = new FieldImpl(
				"floorRateSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingRateMultiplierScheduleField = new FieldImpl(
				"floatingRateMultiplierSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateTreatmentField = new FieldImpl(
				"rateTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationParametersField = new FieldImpl(
				"calculationParameters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fallbackRateField = new FieldImpl(
				"fallbackRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialRateField = new FieldImpl(
				"initialRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalRateRoundingField = new FieldImpl(
				"finalRateRounding",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingMethodField = new FieldImpl(
				"averagingMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.negativeInterestRateTreatmentField = new FieldImpl(
				"negativeInterestRateTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.inflationLagField = new FieldImpl(
				"inflationLag",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexSourceField = new FieldImpl(
				"indexSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mainPublicationField = new FieldImpl(
				"mainPublication",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interpolationMethodField = new FieldImpl(
				"interpolationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialIndexLevelField = new FieldImpl(
				"initialIndexLevel",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fallbackBondApplicableField = new FieldImpl(
				"fallbackBondApplicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationMethodField = new FieldImpl(
				"calculationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationStyleField = new FieldImpl(
				"calculationStyle",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPrincipalExchangeCalculationField = new FieldImpl(
				"finalPrincipalExchangeCalculation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InflationRateSpecification input) {
			FieldValue rateOption = Optional.ofNullable(input.getRateOption())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(rateOptionField, Optional.of(interestRateIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rateOptionField, Optional.empty()));
			FieldValue spreadSchedule = Optional.ofNullable(input.getSpreadSchedule())
				.map(x -> new NestedFieldValueImpl(spreadScheduleField, Optional.of(spreadScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(spreadScheduleField, Optional.empty()));
			FieldValue capRateSchedule = Optional.ofNullable(input.getCapRateSchedule())
				.map(x -> new NestedFieldValueImpl(capRateScheduleField, Optional.of(strikeScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(capRateScheduleField, Optional.empty()));
			FieldValue floorRateSchedule = Optional.ofNullable(input.getFloorRateSchedule())
				.map(x -> new NestedFieldValueImpl(floorRateScheduleField, Optional.of(strikeScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floorRateScheduleField, Optional.empty()));
			FieldValue floatingRateMultiplierSchedule = Optional.ofNullable(input.getFloatingRateMultiplierSchedule())
				.map(x -> new NestedFieldValueImpl(floatingRateMultiplierScheduleField, Optional.of(rateScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floatingRateMultiplierScheduleField, Optional.empty()));
			FieldValue rateTreatment = new FieldValueImpl(rateTreatmentField, Optional.ofNullable(input.getRateTreatment()));
			FieldValue calculationParameters = Optional.ofNullable(input.getCalculationParameters())
				.map(x -> new NestedFieldValueImpl(calculationParametersField, Optional.of(floatingRateCalculationParametersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationParametersField, Optional.empty()));
			FieldValue fallbackRate = Optional.ofNullable(input.getFallbackRate())
				.map(x -> new NestedFieldValueImpl(fallbackRateField, Optional.of(fallbackRateParametersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fallbackRateField, Optional.empty()));
			FieldValue initialRate = Optional.ofNullable(input.getInitialRate())
				.map(x -> new NestedFieldValueImpl(initialRateField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialRateField, Optional.empty()));
			FieldValue finalRateRounding = Optional.ofNullable(input.getFinalRateRounding())
				.map(x -> new NestedFieldValueImpl(finalRateRoundingField, Optional.of(roundingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalRateRoundingField, Optional.empty()));
			FieldValue averagingMethod = new FieldValueImpl(averagingMethodField, Optional.ofNullable(input.getAveragingMethod()));
			FieldValue negativeInterestRateTreatment = new FieldValueImpl(negativeInterestRateTreatmentField, Optional.ofNullable(input.getNegativeInterestRateTreatment()));
			FieldValue inflationLag = Optional.ofNullable(input.getInflationLag())
				.map(x -> new NestedFieldValueImpl(inflationLagField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(inflationLagField, Optional.empty()));
			FieldValue indexSource = new FieldValueImpl(indexSourceField, Optional.ofNullable(input.getIndexSource())
				.map(x -> x.getValue()));
			FieldValue mainPublication = new FieldValueImpl(mainPublicationField, Optional.ofNullable(input.getMainPublication())
				.map(x -> x.getValue()));
			FieldValue interpolationMethod = new FieldValueImpl(interpolationMethodField, Optional.ofNullable(input.getInterpolationMethod())
				.map(x -> x.getValue()));
			FieldValue initialIndexLevel = new FieldValueImpl(initialIndexLevelField, Optional.ofNullable(input.getInitialIndexLevel()));
			FieldValue fallbackBondApplicable = new FieldValueImpl(fallbackBondApplicableField, Optional.ofNullable(input.getFallbackBondApplicable()));
			FieldValue calculationMethod = new FieldValueImpl(calculationMethodField, Optional.ofNullable(input.getCalculationMethod()));
			FieldValue calculationStyle = new FieldValueImpl(calculationStyleField, Optional.ofNullable(input.getCalculationStyle()));
			FieldValue finalPrincipalExchangeCalculation = new FieldValueImpl(finalPrincipalExchangeCalculationField, Optional.ofNullable(input.getFinalPrincipalExchangeCalculation()));
			return Arrays.asList(
				rateOption,
				spreadSchedule,
				capRateSchedule,
				floorRateSchedule,
				floatingRateMultiplierSchedule,
				rateTreatment,
				calculationParameters,
				fallbackRate,
				initialRate,
				finalRateRounding,
				averagingMethod,
				negativeInterestRateTreatment,
				inflationLag,
				indexSource,
				mainPublication,
				interpolationMethod,
				initialIndexLevel,
				fallbackBondApplicable,
				calculationMethod,
				calculationStyle,
				finalPrincipalExchangeCalculation
			);
		}
	}
}
