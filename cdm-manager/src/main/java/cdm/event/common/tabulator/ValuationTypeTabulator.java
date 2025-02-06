package cdm.event.common.tabulator;

import cdm.event.common.Valuation;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
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


@ImplementedBy(ValuationTypeTabulator.Impl.class)
public interface ValuationTypeTabulator extends Tabulator<Valuation> {
	@Singleton
	class Impl implements ValuationTypeTabulator {
		private final Field amountField;
		private final Field timestampField;
		private final Field methodField;
		private final Field sourceField;
		private final Field deltaField;
		private final Field valuationTimingField;
		private final Field priceComponentField;

		private final MoneyTypeTabulator moneyTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.amountField = new FieldImpl(
				"amount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.timestampField = new FieldImpl(
				"timestamp",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.methodField = new FieldImpl(
				"method",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sourceField = new FieldImpl(
				"source",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deltaField = new FieldImpl(
				"delta",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationTimingField = new FieldImpl(
				"valuationTiming",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceComponentField = new FieldImpl(
				"priceComponent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Valuation input) {
			FieldValue amount = Optional.ofNullable(input.getAmount())
				.map(x -> new NestedFieldValueImpl(amountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(amountField, Optional.empty()));
			FieldValue timestamp = new FieldValueImpl(timestampField, Optional.ofNullable(input.getTimestamp()));
			FieldValue method = new FieldValueImpl(methodField, Optional.ofNullable(input.getMethod()));
			FieldValue source = new FieldValueImpl(sourceField, Optional.ofNullable(input.getSource()));
			FieldValue delta = new FieldValueImpl(deltaField, Optional.ofNullable(input.getDelta()));
			FieldValue valuationTiming = new FieldValueImpl(valuationTimingField, Optional.ofNullable(input.getValuationTiming()));
			FieldValue priceComponent = Optional.ofNullable(input.getPriceComponent())
				.map(x -> new NestedFieldValueImpl(priceComponentField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceComponentField, Optional.empty()));
			return Arrays.asList(
				amount,
				timestamp,
				method,
				source,
				delta,
				valuationTiming,
				priceComponent
			);
		}
	}
}
