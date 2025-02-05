package cdm.product.common.settlement.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.settlement.ShapingProvision;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ShapingProvisionTypeTabulator.Impl.class)
public interface ShapingProvisionTypeTabulator extends Tabulator<ShapingProvision> {
	@Singleton
	class Impl implements ShapingProvisionTypeTabulator {
		private final Field shapeScheduleField;

		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.shapeScheduleField = new FieldImpl(
				"shapeSchedule",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ShapingProvision input) {
			FieldValue shapeSchedule = Optional.ofNullable(input.getShapeSchedule())
				.map(x -> x.stream()
					.map(_x -> moneyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(shapeScheduleField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(shapeScheduleField, Optional.empty()));
			return Arrays.asList(
				shapeSchedule
			);
		}
	}
}
