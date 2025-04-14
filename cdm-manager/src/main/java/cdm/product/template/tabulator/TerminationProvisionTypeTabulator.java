package cdm.product.template.tabulator;

import cdm.product.template.TerminationProvision;
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


@ImplementedBy(TerminationProvisionTypeTabulator.Impl.class)
public interface TerminationProvisionTypeTabulator extends Tabulator<TerminationProvision> {
	@Singleton
	class Impl implements TerminationProvisionTypeTabulator {
		private final Field cancelableProvisionField;
		private final Field earlyTerminationProvisionField;
		private final Field evergreenProvisionField;
		private final Field extendibleProvisionField;

		private final CancelableProvisionTypeTabulator cancelableProvisionTypeTabulator;
		private final EarlyTerminationProvisionTypeTabulator earlyTerminationProvisionTypeTabulator;
		private final EvergreenProvisionTypeTabulator evergreenProvisionTypeTabulator;
		private final ExtendibleProvisionTypeTabulator extendibleProvisionTypeTabulator;

		@Inject
		public Impl(CancelableProvisionTypeTabulator cancelableProvisionTypeTabulator, EarlyTerminationProvisionTypeTabulator earlyTerminationProvisionTypeTabulator, EvergreenProvisionTypeTabulator evergreenProvisionTypeTabulator, ExtendibleProvisionTypeTabulator extendibleProvisionTypeTabulator) {
			this.cancelableProvisionTypeTabulator = cancelableProvisionTypeTabulator;
			this.earlyTerminationProvisionTypeTabulator = earlyTerminationProvisionTypeTabulator;
			this.evergreenProvisionTypeTabulator = evergreenProvisionTypeTabulator;
			this.extendibleProvisionTypeTabulator = extendibleProvisionTypeTabulator;
			this.cancelableProvisionField = new FieldImpl(
				"cancelableProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.earlyTerminationProvisionField = new FieldImpl(
				"earlyTerminationProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.evergreenProvisionField = new FieldImpl(
				"evergreenProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.extendibleProvisionField = new FieldImpl(
				"extendibleProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TerminationProvision input) {
			FieldValue cancelableProvision = Optional.ofNullable(input.getCancelableProvision())
				.map(x -> new NestedFieldValueImpl(cancelableProvisionField, Optional.of(cancelableProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cancelableProvisionField, Optional.empty()));
			FieldValue earlyTerminationProvision = Optional.ofNullable(input.getEarlyTerminationProvision())
				.map(x -> new NestedFieldValueImpl(earlyTerminationProvisionField, Optional.of(earlyTerminationProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(earlyTerminationProvisionField, Optional.empty()));
			FieldValue evergreenProvision = Optional.ofNullable(input.getEvergreenProvision())
				.map(x -> new NestedFieldValueImpl(evergreenProvisionField, Optional.of(evergreenProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(evergreenProvisionField, Optional.empty()));
			FieldValue extendibleProvision = Optional.ofNullable(input.getExtendibleProvision())
				.map(x -> new NestedFieldValueImpl(extendibleProvisionField, Optional.of(extendibleProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extendibleProvisionField, Optional.empty()));
			return Arrays.asList(
				cancelableProvision,
				earlyTerminationProvision,
				evergreenProvision,
				extendibleProvision
			);
		}
	}
}
