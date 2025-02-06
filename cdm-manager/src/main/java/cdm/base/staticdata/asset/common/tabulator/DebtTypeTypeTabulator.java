package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.DebtType;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(DebtTypeTypeTabulator.Impl.class)
public interface DebtTypeTypeTabulator extends Tabulator<DebtType> {
	@Singleton
	class Impl implements DebtTypeTypeTabulator {
		private final Field debtClassField;
		private final Field debtEconomicsField;

		private final DebtEconomicsTypeTabulator debtEconomicsTypeTabulator;

		@Inject
		public Impl(DebtEconomicsTypeTabulator debtEconomicsTypeTabulator) {
			this.debtEconomicsTypeTabulator = debtEconomicsTypeTabulator;
			this.debtClassField = new FieldImpl(
				"debtClass",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtEconomicsField = new FieldImpl(
				"debtEconomics",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DebtType input) {
			FieldValue debtClass = new FieldValueImpl(debtClassField, Optional.ofNullable(input.getDebtClass()));
			FieldValue debtEconomics = Optional.ofNullable(input.getDebtEconomics())
				.map(x -> x.stream()
					.map(_x -> debtEconomicsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(debtEconomicsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(debtEconomicsField, Optional.empty()));
			return Arrays.asList(
				debtClass,
				debtEconomics
			);
		}
	}
}
