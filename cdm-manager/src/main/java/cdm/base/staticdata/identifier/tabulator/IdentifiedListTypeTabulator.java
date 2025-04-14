package cdm.base.staticdata.identifier.tabulator;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(IdentifiedListTypeTabulator.Impl.class)
public interface IdentifiedListTypeTabulator extends Tabulator<IdentifiedList> {
	@Singleton
	class Impl implements IdentifiedListTypeTabulator {
		private final Field listIdField;
		private final Field componentIdField;
		private final Field priceField;

		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(IdentifierTypeTabulator identifierTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.listIdField = new FieldImpl(
				"listId",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.componentIdField = new FieldImpl(
				"componentId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceField = new FieldImpl(
				"price",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IdentifiedList input) {
			FieldValue listId = Optional.ofNullable(input.getListId())
				.map(x -> new NestedFieldValueImpl(listIdField, Optional.of(identifierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(listIdField, Optional.empty()));
			FieldValue componentId = Optional.ofNullable(input.getComponentId())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(componentIdField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(componentIdField, Optional.empty()));
			FieldValue price = Optional.ofNullable(input.getPrice())
				.map(x -> new NestedFieldValueImpl(priceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceField, Optional.empty()));
			return Arrays.asList(
				listId,
				componentId,
				price
			);
		}
	}
}
