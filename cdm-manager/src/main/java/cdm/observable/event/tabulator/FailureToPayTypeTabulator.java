package cdm.observable.event.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.observable.event.FailureToPay;
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


@ImplementedBy(FailureToPayTypeTabulator.Impl.class)
public interface FailureToPayTypeTabulator extends Tabulator<FailureToPay> {
	@Singleton
	class Impl implements FailureToPayTypeTabulator {
		private final Field applicableField;
		private final Field gracePeriodExtensionField;
		private final Field paymentRequirementField;

		private final GracePeriodExtensionTypeTabulator gracePeriodExtensionTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(GracePeriodExtensionTypeTabulator gracePeriodExtensionTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.gracePeriodExtensionTypeTabulator = gracePeriodExtensionTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.gracePeriodExtensionField = new FieldImpl(
				"gracePeriodExtension",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentRequirementField = new FieldImpl(
				"paymentRequirement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FailureToPay input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue gracePeriodExtension = Optional.ofNullable(input.getGracePeriodExtension())
				.map(x -> new NestedFieldValueImpl(gracePeriodExtensionField, Optional.of(gracePeriodExtensionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(gracePeriodExtensionField, Optional.empty()));
			FieldValue paymentRequirement = Optional.ofNullable(input.getPaymentRequirement())
				.map(x -> new NestedFieldValueImpl(paymentRequirementField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentRequirementField, Optional.empty()));
			return Arrays.asList(
				applicable,
				gracePeriodExtension,
				paymentRequirement
			);
		}
	}
}
