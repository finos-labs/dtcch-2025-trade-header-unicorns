package cdm.product.asset.tabulator;

import cdm.product.asset.CashflowRepresentation;
import cdm.product.common.schedule.tabulator.PaymentCalculationPeriodTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CashflowRepresentationTypeTabulator.Impl.class)
public interface CashflowRepresentationTypeTabulator extends Tabulator<CashflowRepresentation> {
	@Singleton
	class Impl implements CashflowRepresentationTypeTabulator {
		private final Field cashflowsMatchParametersField;
		private final Field paymentCalculationPeriodField;

		private final PaymentCalculationPeriodTypeTabulator paymentCalculationPeriodTypeTabulator;

		@Inject
		public Impl(PaymentCalculationPeriodTypeTabulator paymentCalculationPeriodTypeTabulator) {
			this.paymentCalculationPeriodTypeTabulator = paymentCalculationPeriodTypeTabulator;
			this.cashflowsMatchParametersField = new FieldImpl(
				"cashflowsMatchParameters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentCalculationPeriodField = new FieldImpl(
				"paymentCalculationPeriod",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CashflowRepresentation input) {
			FieldValue cashflowsMatchParameters = new FieldValueImpl(cashflowsMatchParametersField, Optional.ofNullable(input.getCashflowsMatchParameters()));
			FieldValue paymentCalculationPeriod = Optional.ofNullable(input.getPaymentCalculationPeriod())
				.map(x -> x.stream()
					.map(_x -> paymentCalculationPeriodTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(paymentCalculationPeriodField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(paymentCalculationPeriodField, Optional.empty()));
			return Arrays.asList(
				cashflowsMatchParameters,
				paymentCalculationPeriod
			);
		}
	}
}
