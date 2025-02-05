package cdm.legaldocumentation.common.tabulator;

import cdm.base.staticdata.party.tabulator.CounterpartyTypeTabulator;
import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.contract.tabulator.AgreementTypeTabulator;
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


@ImplementedBy(AgreementTermsTypeTabulator.Impl.class)
public interface AgreementTermsTypeTabulator extends Tabulator<AgreementTerms> {
	@Singleton
	class Impl implements AgreementTermsTypeTabulator {
		private final Field agreementField;
		private final Field clauseLibraryField;
		private final Field counterpartyField;

		private final AgreementTypeTabulator agreementTypeTabulator;
		private final CounterpartyTypeTabulator counterpartyTypeTabulator;

		@Inject
		public Impl(AgreementTypeTabulator agreementTypeTabulator, CounterpartyTypeTabulator counterpartyTypeTabulator) {
			this.agreementTypeTabulator = agreementTypeTabulator;
			this.counterpartyTypeTabulator = counterpartyTypeTabulator;
			this.agreementField = new FieldImpl(
				"agreement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.clauseLibraryField = new FieldImpl(
				"clauseLibrary",
				false,
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
		}

		@Override
		public List<FieldValue> tabulate(AgreementTerms input) {
			FieldValue agreement = Optional.ofNullable(input.getAgreement())
				.map(x -> new NestedFieldValueImpl(agreementField, Optional.of(agreementTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(agreementField, Optional.empty()));
			FieldValue clauseLibrary = new FieldValueImpl(clauseLibraryField, Optional.ofNullable(input.getClauseLibrary()));
			FieldValue counterparty = Optional.ofNullable(input.getCounterparty())
				.map(x -> x.stream()
					.map(_x -> counterpartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(counterpartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(counterpartyField, Optional.empty()));
			return Arrays.asList(
				agreement,
				clauseLibrary,
				counterparty
			);
		}
	}
}
