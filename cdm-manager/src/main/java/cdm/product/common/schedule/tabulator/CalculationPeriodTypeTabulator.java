package cdm.product.common.schedule.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.asset.tabulator.FloatingRateDefinitionTypeTabulator;
import cdm.product.common.schedule.CalculationPeriod;
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


@ImplementedBy(CalculationPeriodTypeTabulator.Impl.class)
public interface CalculationPeriodTypeTabulator extends Tabulator<CalculationPeriod> {
	@Singleton
	class Impl implements CalculationPeriodTypeTabulator {
		private final Field adjustedStartDateField;
		private final Field adjustedEndDateField;
		private final Field unadjustedStartDateField;
		private final Field unadjustedEndDateField;
		private final Field calculationPeriodNumberOfDaysField;
		private final Field notionalAmountField;
		private final Field fxLinkedNotionalAmountField;
		private final Field floatingRateDefinitionField;
		private final Field fixedRateField;
		private final Field dayCountYearFractionField;
		private final Field forecastAmountField;
		private final Field forecastRateField;

		private final FxLinkedNotionalAmountTypeTabulator fxLinkedNotionalAmountTypeTabulator;
		private final FloatingRateDefinitionTypeTabulator floatingRateDefinitionTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(FxLinkedNotionalAmountTypeTabulator fxLinkedNotionalAmountTypeTabulator, FloatingRateDefinitionTypeTabulator floatingRateDefinitionTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.fxLinkedNotionalAmountTypeTabulator = fxLinkedNotionalAmountTypeTabulator;
			this.floatingRateDefinitionTypeTabulator = floatingRateDefinitionTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.adjustedStartDateField = new FieldImpl(
				"adjustedStartDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedEndDateField = new FieldImpl(
				"adjustedEndDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unadjustedStartDateField = new FieldImpl(
				"unadjustedStartDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unadjustedEndDateField = new FieldImpl(
				"unadjustedEndDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodNumberOfDaysField = new FieldImpl(
				"calculationPeriodNumberOfDays",
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
			this.fxLinkedNotionalAmountField = new FieldImpl(
				"fxLinkedNotionalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingRateDefinitionField = new FieldImpl(
				"floatingRateDefinition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixedRateField = new FieldImpl(
				"fixedRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayCountYearFractionField = new FieldImpl(
				"dayCountYearFraction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.forecastAmountField = new FieldImpl(
				"forecastAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.forecastRateField = new FieldImpl(
				"forecastRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalculationPeriod input) {
			FieldValue adjustedStartDate = new FieldValueImpl(adjustedStartDateField, Optional.ofNullable(input.getAdjustedStartDate()));
			FieldValue adjustedEndDate = new FieldValueImpl(adjustedEndDateField, Optional.ofNullable(input.getAdjustedEndDate()));
			FieldValue unadjustedStartDate = new FieldValueImpl(unadjustedStartDateField, Optional.ofNullable(input.getUnadjustedStartDate()));
			FieldValue unadjustedEndDate = new FieldValueImpl(unadjustedEndDateField, Optional.ofNullable(input.getUnadjustedEndDate()));
			FieldValue calculationPeriodNumberOfDays = new FieldValueImpl(calculationPeriodNumberOfDaysField, Optional.ofNullable(input.getCalculationPeriodNumberOfDays()));
			FieldValue notionalAmount = new FieldValueImpl(notionalAmountField, Optional.ofNullable(input.getNotionalAmount()));
			FieldValue fxLinkedNotionalAmount = Optional.ofNullable(input.getFxLinkedNotionalAmount())
				.map(x -> new NestedFieldValueImpl(fxLinkedNotionalAmountField, Optional.of(fxLinkedNotionalAmountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxLinkedNotionalAmountField, Optional.empty()));
			FieldValue floatingRateDefinition = Optional.ofNullable(input.getFloatingRateDefinition())
				.map(x -> new NestedFieldValueImpl(floatingRateDefinitionField, Optional.of(floatingRateDefinitionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floatingRateDefinitionField, Optional.empty()));
			FieldValue fixedRate = new FieldValueImpl(fixedRateField, Optional.ofNullable(input.getFixedRate()));
			FieldValue dayCountYearFraction = new FieldValueImpl(dayCountYearFractionField, Optional.ofNullable(input.getDayCountYearFraction()));
			FieldValue forecastAmount = Optional.ofNullable(input.getForecastAmount())
				.map(x -> new NestedFieldValueImpl(forecastAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(forecastAmountField, Optional.empty()));
			FieldValue forecastRate = new FieldValueImpl(forecastRateField, Optional.ofNullable(input.getForecastRate()));
			return Arrays.asList(
				adjustedStartDate,
				adjustedEndDate,
				unadjustedStartDate,
				unadjustedEndDate,
				calculationPeriodNumberOfDays,
				notionalAmount,
				fxLinkedNotionalAmount,
				floatingRateDefinition,
				fixedRate,
				dayCountYearFraction,
				forecastAmount,
				forecastRate
			);
		}
	}
}
