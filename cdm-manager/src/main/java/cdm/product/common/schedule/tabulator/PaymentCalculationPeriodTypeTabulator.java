package cdm.product.common.schedule.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.schedule.PaymentCalculationPeriod;
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


@ImplementedBy(PaymentCalculationPeriodTypeTabulator.Impl.class)
public interface PaymentCalculationPeriodTypeTabulator extends Tabulator<PaymentCalculationPeriod> {
	@Singleton
	class Impl implements PaymentCalculationPeriodTypeTabulator {
		private final Field unadjustedPaymentDateField;
		private final Field adjustedPaymentDateField;
		private final Field calculationPeriodField;
		private final Field fixedPaymentAmountField;
		private final Field discountFactorField;
		private final Field forecastPaymentAmountField;
		private final Field presentValueAmountField;

		private final CalculationPeriodTypeTabulator calculationPeriodTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(CalculationPeriodTypeTabulator calculationPeriodTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.calculationPeriodTypeTabulator = calculationPeriodTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.unadjustedPaymentDateField = new FieldImpl(
				"unadjustedPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedPaymentDateField = new FieldImpl(
				"adjustedPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodField = new FieldImpl(
				"calculationPeriod",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixedPaymentAmountField = new FieldImpl(
				"fixedPaymentAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discountFactorField = new FieldImpl(
				"discountFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.forecastPaymentAmountField = new FieldImpl(
				"forecastPaymentAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.presentValueAmountField = new FieldImpl(
				"presentValueAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PaymentCalculationPeriod input) {
			FieldValue unadjustedPaymentDate = new FieldValueImpl(unadjustedPaymentDateField, Optional.ofNullable(input.getUnadjustedPaymentDate()));
			FieldValue adjustedPaymentDate = new FieldValueImpl(adjustedPaymentDateField, Optional.ofNullable(input.getAdjustedPaymentDate()));
			FieldValue calculationPeriod = Optional.ofNullable(input.getCalculationPeriod())
				.map(x -> x.stream()
					.map(_x -> calculationPeriodTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(calculationPeriodField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(calculationPeriodField, Optional.empty()));
			FieldValue fixedPaymentAmount = Optional.ofNullable(input.getFixedPaymentAmount())
				.map(x -> new NestedFieldValueImpl(fixedPaymentAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixedPaymentAmountField, Optional.empty()));
			FieldValue discountFactor = new FieldValueImpl(discountFactorField, Optional.ofNullable(input.getDiscountFactor()));
			FieldValue forecastPaymentAmount = Optional.ofNullable(input.getForecastPaymentAmount())
				.map(x -> new NestedFieldValueImpl(forecastPaymentAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(forecastPaymentAmountField, Optional.empty()));
			FieldValue presentValueAmount = Optional.ofNullable(input.getPresentValueAmount())
				.map(x -> new NestedFieldValueImpl(presentValueAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(presentValueAmountField, Optional.empty()));
			return Arrays.asList(
				unadjustedPaymentDate,
				adjustedPaymentDate,
				calculationPeriod,
				fixedPaymentAmount,
				discountFactor,
				forecastPaymentAmount,
				presentValueAmount
			);
		}
	}
}
