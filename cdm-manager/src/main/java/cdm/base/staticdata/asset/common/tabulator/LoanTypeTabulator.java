package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.Loan;
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


@ImplementedBy(LoanTypeTabulator.Impl.class)
public interface LoanTypeTabulator extends Tabulator<Loan> {
	@Singleton
	class Impl implements LoanTypeTabulator {
		private final Field identifierField;
		private final Field taxonomyField;
		private final Field isExchangeListedField;
		private final Field exchangeField;
		private final Field relatedExchangeField;
		private final Field instrumentTypeField;
		private final Field borrowerField;
		private final Field lienField;
		private final Field facilityTypeField;
		private final Field creditAgreementDateField;
		private final Field trancheField;

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
			this.borrowerField = new FieldImpl(
				"borrower",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lienField = new FieldImpl(
				"lien",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.facilityTypeField = new FieldImpl(
				"facilityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditAgreementDateField = new FieldImpl(
				"creditAgreementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.trancheField = new FieldImpl(
				"tranche",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Loan input) {
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
			FieldValue borrower = Optional.ofNullable(input.getBorrower())
				.map(x -> x.stream()
					.map(_x -> legalEntityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(borrowerField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(borrowerField, Optional.empty()));
			FieldValue lien = new FieldValueImpl(lienField, Optional.ofNullable(input.getLien())
				.map(x -> x.getValue()));
			FieldValue facilityType = new FieldValueImpl(facilityTypeField, Optional.ofNullable(input.getFacilityType())
				.map(x -> x.getValue()));
			FieldValue creditAgreementDate = new FieldValueImpl(creditAgreementDateField, Optional.ofNullable(input.getCreditAgreementDate()));
			FieldValue tranche = new FieldValueImpl(trancheField, Optional.ofNullable(input.getTranche())
				.map(x -> x.getValue()));
			return Arrays.asList(
				identifier,
				taxonomy,
				isExchangeListed,
				exchange,
				relatedExchange,
				instrumentType,
				borrower,
				lien,
				facilityType,
				creditAgreementDate,
				tranche
			);
		}
	}
}
