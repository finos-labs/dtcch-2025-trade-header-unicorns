package cdm.base.math.tabulator;

import cdm.base.math.MoneyBound;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
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


@ImplementedBy(MoneyBoundTypeTabulator.Impl.class)
public interface MoneyBoundTypeTabulator extends Tabulator<MoneyBound> {
	@Singleton
	class Impl implements MoneyBoundTypeTabulator {
		private final Field moneyField;
		private final Field inclusiveField;

		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.moneyField = new FieldImpl(
				"money",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.inclusiveField = new FieldImpl(
				"inclusive",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MoneyBound input) {
			FieldValue money = Optional.ofNullable(input.getMoney())
				.map(x -> new NestedFieldValueImpl(moneyField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(moneyField, Optional.empty()));
			FieldValue inclusive = new FieldValueImpl(inclusiveField, Optional.ofNullable(input.getInclusive()));
			return Arrays.asList(
				money,
				inclusive
			);
		}
	}
}
