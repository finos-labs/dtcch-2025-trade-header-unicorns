package cdm.product.asset.tabulator;

import cdm.base.staticdata.asset.credit.tabulator.ObligationsTypeTabulator;
import cdm.observable.event.tabulator.CreditEventsTypeTabulator;
import cdm.product.asset.ProtectionTerms;
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


@ImplementedBy(ProtectionTermsTypeTabulator.Impl.class)
public interface ProtectionTermsTypeTabulator extends Tabulator<ProtectionTerms> {
	@Singleton
	class Impl implements ProtectionTermsTypeTabulator {
		private final Field creditEventsField;
		private final Field obligationsField;
		private final Field floatingAmountEventsField;

		private final CreditEventsTypeTabulator creditEventsTypeTabulator;
		private final ObligationsTypeTabulator obligationsTypeTabulator;
		private final FloatingAmountEventsTypeTabulator floatingAmountEventsTypeTabulator;

		@Inject
		public Impl(CreditEventsTypeTabulator creditEventsTypeTabulator, ObligationsTypeTabulator obligationsTypeTabulator, FloatingAmountEventsTypeTabulator floatingAmountEventsTypeTabulator) {
			this.creditEventsTypeTabulator = creditEventsTypeTabulator;
			this.obligationsTypeTabulator = obligationsTypeTabulator;
			this.floatingAmountEventsTypeTabulator = floatingAmountEventsTypeTabulator;
			this.creditEventsField = new FieldImpl(
				"creditEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.obligationsField = new FieldImpl(
				"obligations",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.floatingAmountEventsField = new FieldImpl(
				"floatingAmountEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ProtectionTerms input) {
			FieldValue creditEvents = Optional.ofNullable(input.getCreditEvents())
				.map(x -> new NestedFieldValueImpl(creditEventsField, Optional.of(creditEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditEventsField, Optional.empty()));
			FieldValue obligations = Optional.ofNullable(input.getObligations())
				.map(x -> new NestedFieldValueImpl(obligationsField, Optional.of(obligationsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(obligationsField, Optional.empty()));
			FieldValue floatingAmountEvents = Optional.ofNullable(input.getFloatingAmountEvents())
				.map(x -> new NestedFieldValueImpl(floatingAmountEventsField, Optional.of(floatingAmountEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(floatingAmountEventsField, Optional.empty()));
			return Arrays.asList(
				creditEvents,
				obligations,
				floatingAmountEvents
			);
		}
	}
}
