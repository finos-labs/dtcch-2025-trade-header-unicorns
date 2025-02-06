package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.product.asset.FutureValueAmount;
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


@ImplementedBy(FutureValueAmountTypeTabulator.Impl.class)
public interface FutureValueAmountTypeTabulator extends Tabulator<FutureValueAmount> {
	@Singleton
	class Impl implements FutureValueAmountTypeTabulator {
		private final Field quantityField;
		private final Field currencyField;
		private final Field calculationPeriodNumberOfDaysField;
		private final Field valueDateField;

		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;

		@Inject
		public Impl(NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator) {
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.quantityField = new FieldImpl(
				"quantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyField = new FieldImpl(
				"currency",
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
			this.valueDateField = new FieldImpl(
				"valueDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FutureValueAmount input) {
			FieldValue quantity = Optional.ofNullable(input.getQuantity())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(quantityField, Optional.of(nonNegativeQuantityScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityField, Optional.empty()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			FieldValue calculationPeriodNumberOfDays = new FieldValueImpl(calculationPeriodNumberOfDaysField, Optional.ofNullable(input.getCalculationPeriodNumberOfDays()));
			FieldValue valueDate = new FieldValueImpl(valueDateField, Optional.ofNullable(input.getValueDate()));
			return Arrays.asList(
				quantity,
				currency,
				calculationPeriodNumberOfDays,
				valueDate
			);
		}
	}
}
