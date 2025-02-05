package cdm.event.common.tabulator;

import cdm.event.common.ObservationEvent;
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


@ImplementedBy(ObservationEventTypeTabulator.Impl.class)
public interface ObservationEventTypeTabulator extends Tabulator<ObservationEvent> {
	@Singleton
	class Impl implements ObservationEventTypeTabulator {
		private final Field creditEventField;
		private final Field corporateActionField;

		private final CreditEventTypeTabulator creditEventTypeTabulator;
		private final CorporateActionTypeTabulator corporateActionTypeTabulator;

		@Inject
		public Impl(CreditEventTypeTabulator creditEventTypeTabulator, CorporateActionTypeTabulator corporateActionTypeTabulator) {
			this.creditEventTypeTabulator = creditEventTypeTabulator;
			this.corporateActionTypeTabulator = corporateActionTypeTabulator;
			this.creditEventField = new FieldImpl(
				"creditEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.corporateActionField = new FieldImpl(
				"corporateAction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationEvent input) {
			FieldValue creditEvent = Optional.ofNullable(input.getCreditEvent())
				.map(x -> new NestedFieldValueImpl(creditEventField, Optional.of(creditEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditEventField, Optional.empty()));
			FieldValue corporateAction = Optional.ofNullable(input.getCorporateAction())
				.map(x -> new NestedFieldValueImpl(corporateActionField, Optional.of(corporateActionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(corporateActionField, Optional.empty()));
			return Arrays.asList(
				creditEvent,
				corporateAction
			);
		}
	}
}
