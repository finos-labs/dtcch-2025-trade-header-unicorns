package cdm.product.asset.tabulator;

import cdm.product.asset.FloatingAmountEvents;
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


@ImplementedBy(FloatingAmountEventsTypeTabulator.Impl.class)
public interface FloatingAmountEventsTypeTabulator extends Tabulator<FloatingAmountEvents> {
	@Singleton
	class Impl implements FloatingAmountEventsTypeTabulator {
		private final Field failureToPayPrincipalField;
		private final Field interestShortfallField;
		private final Field writedownField;
		private final Field impliedWritedownField;
		private final Field floatingAmountProvisionsField;
		private final Field additionalFixedPaymentsField;

		private final InterestShortFallTypeTabulator interestShortFallTypeTabulator;
		private final FloatingAmountProvisionsTypeTabulator floatingAmountProvisionsTypeTabulator;
		private final AdditionalFixedPaymentsTypeTabulator additionalFixedPaymentsTypeTabulator;

		@Inject
		public Impl(InterestShortFallTypeTabulator interestShortFallTypeTabulator, FloatingAmountProvisionsTypeTabulator floatingAmountProvisionsTypeTabulator, AdditionalFixedPaymentsTypeTabulator additionalFixedPaymentsTypeTabulator) {
			this.interestShortFallTypeTabulator = interestShortFallTypeTabulator;
			this.floatingAmountProvisionsTypeTabulator = floatingAmountProvisionsTypeTabulator;
			this.additionalFixedPaymentsTypeTabulator = additionalFixedPaymentsTypeTabulator;
			this.failureToPayPrincipalField = new FieldImpl(
				"failureToPayPrincipal",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interestShortfallField = new FieldImpl(
				"interestShortfall",
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
			this.floatingAmountProvisionsField = new FieldImpl(
				"floatingAmountProvisions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalFixedPaymentsField = new FieldImpl(
				"additionalFixedPayments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FloatingAmountEvents input) {
			FieldValue failureToPayPrincipal = new FieldValueImpl(failureToPayPrincipalField, Optional.ofNullable(input.getFailureToPayPrincipal()));
			FieldValue interestShortfall = Optional.ofNullable(input.getInterestShortfall())
				.map(x -> new NestedFieldValueImpl(interestShortfallField, Optional.of(interestShortFallTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(interestShortfallField, Optional.empty()));
			FieldValue writedown = new FieldValueImpl(writedownField, Optional.ofNullable(input.getWritedown()));
			FieldValue impliedWritedown = new FieldValueImpl(impliedWritedownField, Optional.ofNullable(input.getImpliedWritedown()));
			FieldValue floatingAmountProvisions = Optional.ofNullable(input.getFloatingAmountProvisions())
				.map(x -> new NestedFieldValueImpl(floatingAmountProvisionsField, Optional.of(floatingAmountProvisionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floatingAmountProvisionsField, Optional.empty()));
			FieldValue additionalFixedPayments = Optional.ofNullable(input.getAdditionalFixedPayments())
				.map(x -> new NestedFieldValueImpl(additionalFixedPaymentsField, Optional.of(additionalFixedPaymentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(additionalFixedPaymentsField, Optional.empty()));
			return Arrays.asList(
				failureToPayPrincipal,
				interestShortfall,
				writedown,
				impliedWritedown,
				floatingAmountProvisions,
				additionalFixedPayments
			);
		}
	}
}
