package cdm.observable.asset.tabulator;

import cdm.observable.asset.SettlementRateOption;
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


@ImplementedBy(SettlementRateOptionTypeTabulator.Impl.class)
public interface SettlementRateOptionTypeTabulator extends Tabulator<SettlementRateOption> {
	@Singleton
	class Impl implements SettlementRateOptionTypeTabulator {
		private final Field settlementRateOptionField;
		private final Field priceSourceDisruptionField;

		private final PriceSourceDisruptionTypeTabulator priceSourceDisruptionTypeTabulator;

		@Inject
		public Impl(PriceSourceDisruptionTypeTabulator priceSourceDisruptionTypeTabulator) {
			this.priceSourceDisruptionTypeTabulator = priceSourceDisruptionTypeTabulator;
			this.settlementRateOptionField = new FieldImpl(
				"settlementRateOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceSourceDisruptionField = new FieldImpl(
				"priceSourceDisruption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettlementRateOption input) {
			FieldValue settlementRateOption = new FieldValueImpl(settlementRateOptionField, Optional.ofNullable(input.getSettlementRateOption())
				.map(x -> x.getValue()));
			FieldValue priceSourceDisruption = Optional.ofNullable(input.getPriceSourceDisruption())
				.map(x -> new NestedFieldValueImpl(priceSourceDisruptionField, Optional.of(priceSourceDisruptionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceSourceDisruptionField, Optional.empty()));
			return Arrays.asList(
				settlementRateOption,
				priceSourceDisruption
			);
		}
	}
}
