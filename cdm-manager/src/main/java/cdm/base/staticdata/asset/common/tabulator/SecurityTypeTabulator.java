package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.Security;
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


@ImplementedBy(SecurityTypeTabulator.Impl.class)
public interface SecurityTypeTabulator extends Tabulator<Security> {
	@Singleton
	class Impl implements SecurityTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field instrumentTypeField;
		private final Field debtTypeField;
		private final Field equityTypeField;
		private final Field fundTypeField;

		private final AssetIdentifierTypeTabulator assetIdentifierTypeTabulator;
		private final TaxonomyTypeTabulator taxonomyTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final DebtTypeTypeTabulator debtTypeTypeTabulator;

		@Inject
		public Impl(AssetIdentifierTypeTabulator assetIdentifierTypeTabulator, TaxonomyTypeTabulator taxonomyTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator, DebtTypeTypeTabulator debtTypeTypeTabulator) {
			this.assetIdentifierTypeTabulator = assetIdentifierTypeTabulator;
			this.taxonomyTypeTabulator = taxonomyTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.debtTypeTypeTabulator = debtTypeTypeTabulator;
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
			this.debtTypeField = new FieldImpl(
				"debtType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.equityTypeField = new FieldImpl(
				"equityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fundTypeField = new FieldImpl(
				"fundType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Security input) {
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
			FieldValue debtType = Optional.ofNullable(input.getDebtType())
				.map(x -> new NestedFieldValueImpl(debtTypeField, Optional.of(debtTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(debtTypeField, Optional.empty()));
			FieldValue equityType = new FieldValueImpl(equityTypeField, Optional.ofNullable(input.getEquityType()));
			FieldValue fundType = new FieldValueImpl(fundTypeField, Optional.ofNullable(input.getFundType()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				instrumentType,
				debtType,
				equityType,
				fundType
			);
		}
	}
}
