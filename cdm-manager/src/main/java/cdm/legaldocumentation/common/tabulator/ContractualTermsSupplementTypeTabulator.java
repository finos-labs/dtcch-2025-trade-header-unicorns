package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.ContractualTermsSupplement;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(ContractualTermsSupplementTypeTabulator.Impl.class)
public interface ContractualTermsSupplementTypeTabulator extends Tabulator<ContractualTermsSupplement> {
	@Singleton
	class Impl implements ContractualTermsSupplementTypeTabulator {
		private final Field contractualTermsSupplementTypeField;
		private final Field publicationDateField;

		public Impl() {
			this.contractualTermsSupplementTypeField = new FieldImpl(
				"contractualTermsSupplementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publicationDateField = new FieldImpl(
				"publicationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ContractualTermsSupplement input) {
			FieldValue contractualTermsSupplementType = new FieldValueImpl(contractualTermsSupplementTypeField, Optional.ofNullable(input.getContractualTermsSupplementType())
				.map(x -> x.getValue()));
			FieldValue publicationDate = new FieldValueImpl(publicationDateField, Optional.ofNullable(input.getPublicationDate()));
			return Arrays.asList(
				contractualTermsSupplementType,
				publicationDate
			);
		}
	}
}
