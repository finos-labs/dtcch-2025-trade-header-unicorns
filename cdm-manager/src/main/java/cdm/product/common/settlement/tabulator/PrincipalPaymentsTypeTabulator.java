package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.PrincipalPayments;
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


@ImplementedBy(PrincipalPaymentsTypeTabulator.Impl.class)
public interface PrincipalPaymentsTypeTabulator extends Tabulator<PrincipalPayments> {
	@Singleton
	class Impl implements PrincipalPaymentsTypeTabulator {
		private final Field initialPaymentField;
		private final Field finalPaymentField;
		private final Field intermediatePaymentField;
		private final Field varyingLegNotionalCurrencyField;
		private final Field principalPaymentScheduleField;

		private final PrincipalPaymentScheduleTypeTabulator principalPaymentScheduleTypeTabulator;

		@Inject
		public Impl(PrincipalPaymentScheduleTypeTabulator principalPaymentScheduleTypeTabulator) {
			this.principalPaymentScheduleTypeTabulator = principalPaymentScheduleTypeTabulator;
			this.initialPaymentField = new FieldImpl(
				"initialPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPaymentField = new FieldImpl(
				"finalPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.intermediatePaymentField = new FieldImpl(
				"intermediatePayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.varyingLegNotionalCurrencyField = new FieldImpl(
				"varyingLegNotionalCurrency",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.principalPaymentScheduleField = new FieldImpl(
				"principalPaymentSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PrincipalPayments input) {
			FieldValue initialPayment = new FieldValueImpl(initialPaymentField, Optional.ofNullable(input.getInitialPayment()));
			FieldValue finalPayment = new FieldValueImpl(finalPaymentField, Optional.ofNullable(input.getFinalPayment()));
			FieldValue intermediatePayment = new FieldValueImpl(intermediatePaymentField, Optional.ofNullable(input.getIntermediatePayment()));
			FieldValue varyingLegNotionalCurrency = new FieldValueImpl(varyingLegNotionalCurrencyField, Optional.ofNullable(input.getVaryingLegNotionalCurrency()));
			FieldValue principalPaymentSchedule = Optional.ofNullable(input.getPrincipalPaymentSchedule())
				.map(x -> new NestedFieldValueImpl(principalPaymentScheduleField, Optional.of(principalPaymentScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(principalPaymentScheduleField, Optional.empty()));
			return Arrays.asList(
				initialPayment,
				finalPayment,
				intermediatePayment,
				varyingLegNotionalCurrency,
				principalPaymentSchedule
			);
		}
	}
}
