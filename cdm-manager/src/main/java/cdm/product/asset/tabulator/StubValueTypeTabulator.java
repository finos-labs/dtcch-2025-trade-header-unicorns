package cdm.product.asset.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.asset.StubValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(StubValueTypeTabulator.Impl.class)
public interface StubValueTypeTabulator extends Tabulator<StubValue> {
	@Singleton
	class Impl implements StubValueTypeTabulator {
		private final Field floatingRateField;
		private final Field stubRateField;
		private final Field stubAmountField;

		private final StubFloatingRateTypeTabulator stubFloatingRateTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(StubFloatingRateTypeTabulator stubFloatingRateTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.stubFloatingRateTypeTabulator = stubFloatingRateTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.floatingRateField = new FieldImpl(
				"floatingRate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stubRateField = new FieldImpl(
				"stubRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stubAmountField = new FieldImpl(
				"stubAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StubValue input) {
			FieldValue floatingRate = Optional.ofNullable(input.getFloatingRate())
				.map(x -> x.stream()
					.map(_x -> stubFloatingRateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(floatingRateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(floatingRateField, Optional.empty()));
			FieldValue stubRate = new FieldValueImpl(stubRateField, Optional.ofNullable(input.getStubRate()));
			FieldValue stubAmount = Optional.ofNullable(input.getStubAmount())
				.map(x -> new NestedFieldValueImpl(stubAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(stubAmountField, Optional.empty()));
			return Arrays.asList(
				floatingRate,
				stubRate,
				stubAmount
			);
		}
	}
}
