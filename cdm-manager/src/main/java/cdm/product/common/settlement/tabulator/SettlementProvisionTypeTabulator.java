package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.SettlementProvision;
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


@ImplementedBy(SettlementProvisionTypeTabulator.Impl.class)
public interface SettlementProvisionTypeTabulator extends Tabulator<SettlementProvision> {
	@Singleton
	class Impl implements SettlementProvisionTypeTabulator {
		private final Field shapingProvisionsField;

		private final ShapingProvisionTypeTabulator shapingProvisionTypeTabulator;

		@Inject
		public Impl(ShapingProvisionTypeTabulator shapingProvisionTypeTabulator) {
			this.shapingProvisionTypeTabulator = shapingProvisionTypeTabulator;
			this.shapingProvisionsField = new FieldImpl(
				"shapingProvisions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettlementProvision input) {
			FieldValue shapingProvisions = Optional.ofNullable(input.getShapingProvisions())
				.map(x -> new NestedFieldValueImpl(shapingProvisionsField, Optional.of(shapingProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(shapingProvisionsField, Optional.empty()));
			return Arrays.asList(
				shapingProvisions
			);
		}
	}
}
