package cdm.observable.event.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.observable.event.CreditEvents;
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


@ImplementedBy(CreditEventsTypeTabulator.Impl.class)
public interface CreditEventsTypeTabulator extends Tabulator<CreditEvents> {
	@Singleton
	class Impl implements CreditEventsTypeTabulator {
		private final Field bankruptcyField;
		private final Field failureToPayField;
		private final Field failureToPayPrincipalField;
		private final Field failureToPayInterestField;
		private final Field obligationDefaultField;
		private final Field obligationAccelerationField;
		private final Field repudiationMoratoriumField;
		private final Field restructuringField;
		private final Field governmentalInterventionField;
		private final Field distressedRatingsDowngradeField;
		private final Field maturityExtensionField;
		private final Field writedownField;
		private final Field impliedWritedownField;
		private final Field defaultRequirementField;
		private final Field creditEventNoticeField;

		private final FailureToPayTypeTabulator failureToPayTypeTabulator;
		private final RestructuringTypeTabulator restructuringTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;
		private final CreditEventNoticeTypeTabulator creditEventNoticeTypeTabulator;

		@Inject
		public Impl(FailureToPayTypeTabulator failureToPayTypeTabulator, RestructuringTypeTabulator restructuringTypeTabulator, MoneyTypeTabulator moneyTypeTabulator, CreditEventNoticeTypeTabulator creditEventNoticeTypeTabulator) {
			this.failureToPayTypeTabulator = failureToPayTypeTabulator;
			this.restructuringTypeTabulator = restructuringTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.creditEventNoticeTypeTabulator = creditEventNoticeTypeTabulator;
			this.bankruptcyField = new FieldImpl(
				"bankruptcy",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.failureToPayField = new FieldImpl(
				"failureToPay",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.failureToPayPrincipalField = new FieldImpl(
				"failureToPayPrincipal",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.failureToPayInterestField = new FieldImpl(
				"failureToPayInterest",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.obligationDefaultField = new FieldImpl(
				"obligationDefault",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.obligationAccelerationField = new FieldImpl(
				"obligationAcceleration",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.repudiationMoratoriumField = new FieldImpl(
				"repudiationMoratorium",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.restructuringField = new FieldImpl(
				"restructuring",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.governmentalInterventionField = new FieldImpl(
				"governmentalIntervention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.distressedRatingsDowngradeField = new FieldImpl(
				"distressedRatingsDowngrade",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maturityExtensionField = new FieldImpl(
				"maturityExtension",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.writedownField = new FieldImpl(
				"writedown",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.impliedWritedownField = new FieldImpl(
				"impliedWritedown",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.defaultRequirementField = new FieldImpl(
				"defaultRequirement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditEventNoticeField = new FieldImpl(
				"creditEventNotice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditEvents input) {
			FieldValue bankruptcy = new FieldValueImpl(bankruptcyField, Optional.ofNullable(input.getBankruptcy()));
			FieldValue failureToPay = Optional.ofNullable(input.getFailureToPay())
				.map(x -> new NestedFieldValueImpl(failureToPayField, Optional.of(failureToPayTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(failureToPayField, Optional.empty()));
			FieldValue failureToPayPrincipal = new FieldValueImpl(failureToPayPrincipalField, Optional.ofNullable(input.getFailureToPayPrincipal()));
			FieldValue failureToPayInterest = new FieldValueImpl(failureToPayInterestField, Optional.ofNullable(input.getFailureToPayInterest()));
			FieldValue obligationDefault = new FieldValueImpl(obligationDefaultField, Optional.ofNullable(input.getObligationDefault()));
			FieldValue obligationAcceleration = new FieldValueImpl(obligationAccelerationField, Optional.ofNullable(input.getObligationAcceleration()));
			FieldValue repudiationMoratorium = new FieldValueImpl(repudiationMoratoriumField, Optional.ofNullable(input.getRepudiationMoratorium()));
			FieldValue restructuring = Optional.ofNullable(input.getRestructuring())
				.map(x -> new NestedFieldValueImpl(restructuringField, Optional.of(restructuringTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(restructuringField, Optional.empty()));
			FieldValue governmentalIntervention = new FieldValueImpl(governmentalInterventionField, Optional.ofNullable(input.getGovernmentalIntervention()));
			FieldValue distressedRatingsDowngrade = new FieldValueImpl(distressedRatingsDowngradeField, Optional.ofNullable(input.getDistressedRatingsDowngrade()));
			FieldValue maturityExtension = new FieldValueImpl(maturityExtensionField, Optional.ofNullable(input.getMaturityExtension()));
			FieldValue writedown = new FieldValueImpl(writedownField, Optional.ofNullable(input.getWritedown()));
			FieldValue impliedWritedown = new FieldValueImpl(impliedWritedownField, Optional.ofNullable(input.getImpliedWritedown()));
			FieldValue defaultRequirement = Optional.ofNullable(input.getDefaultRequirement())
				.map(x -> new NestedFieldValueImpl(defaultRequirementField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(defaultRequirementField, Optional.empty()));
			FieldValue creditEventNotice = Optional.ofNullable(input.getCreditEventNotice())
				.map(x -> new NestedFieldValueImpl(creditEventNoticeField, Optional.of(creditEventNoticeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditEventNoticeField, Optional.empty()));
			return Arrays.asList(
				bankruptcy,
				failureToPay,
				failureToPayPrincipal,
				failureToPayInterest,
				obligationDefault,
				obligationAcceleration,
				repudiationMoratorium,
				restructuring,
				governmentalIntervention,
				distressedRatingsDowngrade,
				maturityExtension,
				writedown,
				impliedWritedown,
				defaultRequirement,
				creditEventNotice
			);
		}
	}
}
