package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.product.common.settlement.PrincipalPaymentSchedule;
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


@ImplementedBy(PrincipalPaymentScheduleTypeTabulator.Impl.class)
public interface PrincipalPaymentScheduleTypeTabulator extends Tabulator<PrincipalPaymentSchedule> {
	@Singleton
	class Impl implements PrincipalPaymentScheduleTypeTabulator {
		private final Field initialPrincipalPaymentField;
		private final Field intermediatePrincipalPaymentField;
		private final Field finalPrincipalPaymentField;

		private final PrincipalPaymentTypeTabulator principalPaymentTypeTabulator;
		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;

		@Inject
		public Impl(PrincipalPaymentTypeTabulator principalPaymentTypeTabulator, AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator) {
			this.principalPaymentTypeTabulator = principalPaymentTypeTabulator;
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
			this.initialPrincipalPaymentField = new FieldImpl(
				"initialPrincipalPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.intermediatePrincipalPaymentField = new FieldImpl(
				"intermediatePrincipalPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPrincipalPaymentField = new FieldImpl(
				"finalPrincipalPayment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PrincipalPaymentSchedule input) {
			FieldValue initialPrincipalPayment = Optional.ofNullable(input.getInitialPrincipalPayment())
				.map(x -> new NestedFieldValueImpl(initialPrincipalPaymentField, Optional.of(principalPaymentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialPrincipalPaymentField, Optional.empty()));
			FieldValue intermediatePrincipalPayment = Optional.ofNullable(input.getIntermediatePrincipalPayment())
				.map(x -> new NestedFieldValueImpl(intermediatePrincipalPaymentField, Optional.of(adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(intermediatePrincipalPaymentField, Optional.empty()));
			FieldValue finalPrincipalPayment = Optional.ofNullable(input.getFinalPrincipalPayment())
				.map(x -> new NestedFieldValueImpl(finalPrincipalPaymentField, Optional.of(principalPaymentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalPrincipalPaymentField, Optional.empty()));
			return Arrays.asList(
				initialPrincipalPayment,
				intermediatePrincipalPayment,
				finalPrincipalPayment
			);
		}
	}
}
