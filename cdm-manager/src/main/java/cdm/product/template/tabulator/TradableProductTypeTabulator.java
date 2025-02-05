package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.AncillaryPartyTypeTabulator;
import cdm.base.staticdata.party.tabulator.CounterpartyTypeTabulator;
import cdm.product.template.TradableProduct;
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


@ImplementedBy(TradableProductTypeTabulator.Impl.class)
public interface TradableProductTypeTabulator extends Tabulator<TradableProduct> {
	@Singleton
	class Impl implements TradableProductTypeTabulator {
		private final Field productField;
		private final Field tradeLotField;
		private final Field counterpartyField;
		private final Field ancillaryPartyField;
		private final Field adjustmentField;

		private final NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator;
		private final TradeLotTypeTabulator tradeLotTypeTabulator;
		private final CounterpartyTypeTabulator counterpartyTypeTabulator;
		private final AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator;

		@Inject
		public Impl(NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator, TradeLotTypeTabulator tradeLotTypeTabulator, CounterpartyTypeTabulator counterpartyTypeTabulator, AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator) {
			this.nonTransferableProductTypeTabulator = nonTransferableProductTypeTabulator;
			this.tradeLotTypeTabulator = tradeLotTypeTabulator;
			this.counterpartyTypeTabulator = counterpartyTypeTabulator;
			this.ancillaryPartyTypeTabulator = ancillaryPartyTypeTabulator;
			this.productField = new FieldImpl(
				"product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeLotField = new FieldImpl(
				"tradeLot",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.counterpartyField = new FieldImpl(
				"counterparty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ancillaryPartyField = new FieldImpl(
				"ancillaryParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustmentField = new FieldImpl(
				"adjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TradableProduct input) {
			FieldValue product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(productField, Optional.of(nonTransferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(productField, Optional.empty()));
			FieldValue tradeLot = Optional.ofNullable(input.getTradeLot())
				.map(x -> x.stream()
					.map(_x -> tradeLotTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeLotField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeLotField, Optional.empty()));
			FieldValue counterparty = Optional.ofNullable(input.getCounterparty())
				.map(x -> x.stream()
					.map(_x -> counterpartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(counterpartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(counterpartyField, Optional.empty()));
			FieldValue ancillaryParty = Optional.ofNullable(input.getAncillaryParty())
				.map(x -> x.stream()
					.map(_x -> ancillaryPartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.empty()));
			FieldValue adjustment = new FieldValueImpl(adjustmentField, Optional.ofNullable(input.getAdjustment()));
			return Arrays.asList(
				product,
				tradeLot,
				counterparty,
				ancillaryParty,
				adjustment
			);
		}
	}
}
