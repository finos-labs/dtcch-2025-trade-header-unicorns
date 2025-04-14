package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.LegalAgreementIdentification;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(LegalAgreementIdentificationTypeTabulator.Impl.class)
public interface LegalAgreementIdentificationTypeTabulator extends Tabulator<LegalAgreementIdentification> {
	@Singleton
	class Impl implements LegalAgreementIdentificationTypeTabulator {
		private final Field governingLawField;
		private final Field agreementNameField;
		private final Field publisherField;
		private final Field vintageField;

		private final AgreementNameTypeTabulator agreementNameTypeTabulator;

		@Inject
		public Impl(AgreementNameTypeTabulator agreementNameTypeTabulator) {
			this.agreementNameTypeTabulator = agreementNameTypeTabulator;
			this.governingLawField = new FieldImpl(
				"governingLaw",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.agreementNameField = new FieldImpl(
				"agreementName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publisherField = new FieldImpl(
				"publisher",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.vintageField = new FieldImpl(
				"vintage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(LegalAgreementIdentification input) {
			FieldValue governingLaw = new FieldValueImpl(governingLawField, Optional.ofNullable(input.getGoverningLaw()));
			FieldValue agreementName = Optional.ofNullable(input.getAgreementName())
				.map(x -> new NestedFieldValueImpl(agreementNameField, Optional.of(agreementNameTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(agreementNameField, Optional.empty()));
			FieldValue publisher = new FieldValueImpl(publisherField, Optional.ofNullable(input.getPublisher()));
			FieldValue vintage = new FieldValueImpl(vintageField, Optional.ofNullable(input.getVintage()));
			return Arrays.asList(
				governingLaw,
				agreementName,
				publisher,
				vintage
			);
		}
	}
}
