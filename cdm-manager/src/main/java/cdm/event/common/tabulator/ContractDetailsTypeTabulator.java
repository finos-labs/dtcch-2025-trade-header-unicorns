package cdm.event.common.tabulator;

import cdm.event.common.ContractDetails;
import cdm.legaldocumentation.common.tabulator.LegalAgreementTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ContractDetailsTypeTabulator.Impl.class)
public interface ContractDetailsTypeTabulator extends Tabulator<ContractDetails> {
	@Singleton
	class Impl implements ContractDetailsTypeTabulator {
		private final Field documentationField;
		private final Field governingLawField;

		private final LegalAgreementTypeTabulator legalAgreementTypeTabulator;

		@Inject
		public Impl(LegalAgreementTypeTabulator legalAgreementTypeTabulator) {
			this.legalAgreementTypeTabulator = legalAgreementTypeTabulator;
			this.documentationField = new FieldImpl(
				"documentation",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.governingLawField = new FieldImpl(
				"governingLaw",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ContractDetails input) {
			FieldValue documentation = Optional.ofNullable(input.getDocumentation())
				.map(x -> x.stream()
					.map(_x -> legalAgreementTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(documentationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(documentationField, Optional.empty()));
			FieldValue governingLaw = new FieldValueImpl(governingLawField, Optional.ofNullable(input.getGoverningLaw())
				.map(x -> x.getValue()));
			return Arrays.asList(
				documentation,
				governingLaw
			);
		}
	}
}
