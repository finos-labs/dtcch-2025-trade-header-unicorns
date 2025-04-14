package cdm.product.template.tabulator;

import cdm.observable.event.tabulator.TriggerEventTypeTabulator;
import cdm.product.template.Knock;
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


@ImplementedBy(KnockTypeTabulator.Impl.class)
public interface KnockTypeTabulator extends Tabulator<Knock> {
	@Singleton
	class Impl implements KnockTypeTabulator {
		private final Field knockInField;
		private final Field knockOutField;

		private final TriggerEventTypeTabulator triggerEventTypeTabulator;

		@Inject
		public Impl(TriggerEventTypeTabulator triggerEventTypeTabulator) {
			this.triggerEventTypeTabulator = triggerEventTypeTabulator;
			this.knockInField = new FieldImpl(
				"knockIn",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.knockOutField = new FieldImpl(
				"knockOut",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Knock input) {
			FieldValue knockIn = Optional.ofNullable(input.getKnockIn())
				.map(x -> new NestedFieldValueImpl(knockInField, Optional.of(triggerEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(knockInField, Optional.empty()));
			FieldValue knockOut = Optional.ofNullable(input.getKnockOut())
				.map(x -> new NestedFieldValueImpl(knockOutField, Optional.of(triggerEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(knockOutField, Optional.empty()));
			return Arrays.asList(
				knockIn,
				knockOut
			);
		}
	}
}
