package cdm.product.collateral.tabulator;

import cdm.product.collateral.ListingExchange;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;


@ImplementedBy(ListingExchangeTypeTabulator.Impl.class)
public interface ListingExchangeTypeTabulator extends Tabulator<ListingExchange> {
	@Singleton
	class Impl implements ListingExchangeTypeTabulator {
		private final Field exchangeField;

		public Impl() {
			this.exchangeField = new FieldImpl(
				"exchange",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ListingExchange input) {
			FieldValue exchange = new FieldValueImpl(exchangeField, Optional.ofNullable(input.getExchange())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				exchange
			);
		}
	}
}
