package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.DateRangeTypeTabulator;
import cdm.product.asset.tabulator.CalculationScheduleDeliveryPeriodsTypeTabulator;
import cdm.product.template.SchedulePeriod;
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


@ImplementedBy(SchedulePeriodTypeTabulator.Impl.class)
public interface SchedulePeriodTypeTabulator extends Tabulator<SchedulePeriod> {
	@Singleton
	class Impl implements SchedulePeriodTypeTabulator {
		private final Field calculationPeriodField;
		private final Field paymentDateField;
		private final Field fixingPeriodField;
		private final Field deliveryPeriodField;

		private final DateRangeTypeTabulator dateRangeTypeTabulator;
		private final CalculationScheduleDeliveryPeriodsTypeTabulator calculationScheduleDeliveryPeriodsTypeTabulator;

		@Inject
		public Impl(DateRangeTypeTabulator dateRangeTypeTabulator, CalculationScheduleDeliveryPeriodsTypeTabulator calculationScheduleDeliveryPeriodsTypeTabulator) {
			this.dateRangeTypeTabulator = dateRangeTypeTabulator;
			this.calculationScheduleDeliveryPeriodsTypeTabulator = calculationScheduleDeliveryPeriodsTypeTabulator;
			this.calculationPeriodField = new FieldImpl(
				"calculationPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDateField = new FieldImpl(
				"paymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixingPeriodField = new FieldImpl(
				"fixingPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryPeriodField = new FieldImpl(
				"deliveryPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SchedulePeriod input) {
			FieldValue calculationPeriod = Optional.ofNullable(input.getCalculationPeriod())
				.map(x -> new NestedFieldValueImpl(calculationPeriodField, Optional.of(dateRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodField, Optional.empty()));
			FieldValue paymentDate = new FieldValueImpl(paymentDateField, Optional.ofNullable(input.getPaymentDate()));
			FieldValue fixingPeriod = Optional.ofNullable(input.getFixingPeriod())
				.map(x -> new NestedFieldValueImpl(fixingPeriodField, Optional.of(dateRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixingPeriodField, Optional.empty()));
			FieldValue deliveryPeriod = Optional.ofNullable(input.getDeliveryPeriod())
				.map(x -> new NestedFieldValueImpl(deliveryPeriodField, Optional.of(calculationScheduleDeliveryPeriodsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryPeriodField, Optional.empty()));
			return Arrays.asList(
				calculationPeriod,
				paymentDate,
				fixingPeriod,
				deliveryPeriod
			);
		}
	}
}
