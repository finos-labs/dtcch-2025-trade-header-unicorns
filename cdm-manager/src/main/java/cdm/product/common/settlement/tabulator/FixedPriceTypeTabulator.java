package cdm.product.common.settlement.tabulator;

import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.product.common.settlement.FixedPrice;
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


@ImplementedBy(FixedPriceTypeTabulator.Impl.class)
public interface FixedPriceTypeTabulator extends Tabulator<FixedPrice> {
	@Singleton
	class Impl implements FixedPriceTypeTabulator {
		private final Field priceField;

		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.priceField = new FieldImpl(
				"price",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FixedPrice input) {
			FieldValue price = Optional.ofNullable(input.getPrice())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(priceField, Optional.of(priceScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceField, Optional.empty()));
			return Arrays.asList(
				price
			);
		}
	}
}
