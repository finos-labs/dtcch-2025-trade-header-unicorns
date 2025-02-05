package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.ListedDerivative;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
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


@ImplementedBy(ListedDerivativeTypeTabulator.Impl.class)
public interface ListedDerivativeTypeTabulator extends Tabulator<ListedDerivative> {
	@Singleton
	class Impl implements ListedDerivativeTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field instrumentTypeField;
		private final Field deliveryTermField;
		private final Field optionTypeField;
		private final Field strikeField;

		private final AssetIdentifierTypeTabulator assetIdentifierTypeTabulator;
		private final TaxonomyTypeTabulator taxonomyTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;

		@Inject
		public Impl(AssetIdentifierTypeTabulator assetIdentifierTypeTabulator, TaxonomyTypeTabulator taxonomyTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator) {
			this.assetIdentifierTypeTabulator = assetIdentifierTypeTabulator;
			this.taxonomyTypeTabulator = taxonomyTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.identifierField = new FieldImpl(
				"identifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.taxonomyField = new FieldImpl(
				"taxonomy",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.isExchangeListedField = new FieldImpl(
				"isExchangeListed",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exchangeField = new FieldImpl(
				"exchange",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relatedExchangeField = new FieldImpl(
				"relatedExchange",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.instrumentTypeField = new FieldImpl(
				"instrumentType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryTermField = new FieldImpl(
				"deliveryTerm",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionTypeField = new FieldImpl(
				"optionType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.strikeField = new FieldImpl(
				"strike",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ListedDerivative input) {
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> x.stream()
					.map(_x -> assetIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(identifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue taxonomy = Optional.ofNullable(input.getTaxonomy())
				.map(x -> x.stream()
					.map(_x -> taxonomyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(taxonomyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(taxonomyField, Optional.empty()));
			FieldValue isExchangeListed = new FieldValueImpl(isExchangeListedField, Optional.ofNullable(input.getIsExchangeListed()));
			FieldValue exchange = Optional.ofNullable(input.getExchange())
				.map(x -> new NestedFieldValueImpl(exchangeField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exchangeField, Optional.empty()));
			FieldValue relatedExchange = Optional.ofNullable(input.getRelatedExchange())
				.map(x -> x.stream()
					.map(_x -> legalEntityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(relatedExchangeField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(relatedExchangeField, Optional.empty()));
			FieldValue instrumentType = new FieldValueImpl(instrumentTypeField, Optional.ofNullable(input.getInstrumentType()));
			FieldValue deliveryTerm = new FieldValueImpl(deliveryTermField, Optional.ofNullable(input.getDeliveryTerm()));
			FieldValue optionType = new FieldValueImpl(optionTypeField, Optional.ofNullable(input.getOptionType()));
			FieldValue strike = new FieldValueImpl(strikeField, Optional.ofNullable(input.getStrike()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				instrumentType,
				deliveryTerm,
				optionType,
				strike
			);
		}
	}
}
