package cdm.product.template.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.observable.asset.tabulator.PriceQuantityTypeTabulator;
import cdm.product.template.TradeLot;
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


@ImplementedBy(TradeLotTypeTabulator.Impl.class)
public interface TradeLotTypeTabulator extends Tabulator<TradeLot> {
	@Singleton
	class Impl implements TradeLotTypeTabulator {
		private final Field lotIdentifierField;
		private final Field priceQuantityField;

		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final PriceQuantityTypeTabulator priceQuantityTypeTabulator;

		@Inject
		public Impl(IdentifierTypeTabulator identifierTypeTabulator, PriceQuantityTypeTabulator priceQuantityTypeTabulator) {
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.priceQuantityTypeTabulator = priceQuantityTypeTabulator;
			this.lotIdentifierField = new FieldImpl(
				"lotIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceQuantityField = new FieldImpl(
				"priceQuantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TradeLot input) {
			FieldValue lotIdentifier = Optional.ofNullable(input.getLotIdentifier())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(lotIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(lotIdentifierField, Optional.empty()));
			FieldValue priceQuantity = Optional.ofNullable(input.getPriceQuantity())
				.map(x -> x.stream()
					.map(_x -> priceQuantityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceQuantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceQuantityField, Optional.empty()));
			return Arrays.asList(
				lotIdentifier,
				priceQuantity
			);
		}
	}
}
