package cdm.legaldocumentation.common.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.legaldocumentation.common.LegalAgreement;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(LegalAgreementTypeTabulator.Impl.class)
public interface LegalAgreementTypeTabulator extends Tabulator<LegalAgreement> {
	@Singleton
	class Impl implements LegalAgreementTypeTabulator {
		private final Field agreementDateField;
		private final Field effectiveDateField;
		private final Field identifierField;
		private final Field legalAgreementIdentificationField;
		private final Field contractualPartyField;
		private final Field otherPartyField;
		private final Field attachmentField;
		private final Field agreementTermsField;
		private final Field relatedAgreementsField;
		private final Field umbrellaAgreementField;

		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final LegalAgreementIdentificationTypeTabulator legalAgreementIdentificationTypeTabulator;
		private final PartyTypeTabulator partyTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final ResourceTypeTabulator resourceTypeTabulator;
		private final AgreementTermsTypeTabulator agreementTermsTypeTabulator;
		private final LegalAgreementTypeTabulator legalAgreementTypeTabulator;
		private final UmbrellaAgreementTypeTabulator umbrellaAgreementTypeTabulator;

		@Inject
		public Impl(IdentifierTypeTabulator identifierTypeTabulator, LegalAgreementIdentificationTypeTabulator legalAgreementIdentificationTypeTabulator, PartyTypeTabulator partyTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, ResourceTypeTabulator resourceTypeTabulator, AgreementTermsTypeTabulator agreementTermsTypeTabulator, LegalAgreementTypeTabulator legalAgreementTypeTabulator, UmbrellaAgreementTypeTabulator umbrellaAgreementTypeTabulator) {
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.legalAgreementIdentificationTypeTabulator = legalAgreementIdentificationTypeTabulator;
			this.partyTypeTabulator = partyTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.resourceTypeTabulator = resourceTypeTabulator;
			this.agreementTermsTypeTabulator = agreementTermsTypeTabulator;
			this.legalAgreementTypeTabulator = legalAgreementTypeTabulator;
			this.umbrellaAgreementTypeTabulator = umbrellaAgreementTypeTabulator;
			this.agreementDateField = new FieldImpl(
				"agreementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.identifierField = new FieldImpl(
				"identifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.legalAgreementIdentificationField = new FieldImpl(
				"legalAgreementIdentification",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contractualPartyField = new FieldImpl(
				"contractualParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.otherPartyField = new FieldImpl(
				"otherParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.attachmentField = new FieldImpl(
				"attachment",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.agreementTermsField = new FieldImpl(
				"agreementTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relatedAgreementsField = new FieldImpl(
				"relatedAgreements",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.umbrellaAgreementField = new FieldImpl(
				"umbrellaAgreement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(LegalAgreement input) {
			FieldValue agreementDate = new FieldValueImpl(agreementDateField, Optional.ofNullable(input.getAgreementDate()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(identifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue legalAgreementIdentification = Optional.ofNullable(input.getLegalAgreementIdentification())
				.map(x -> new NestedFieldValueImpl(legalAgreementIdentificationField, Optional.of(legalAgreementIdentificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(legalAgreementIdentificationField, Optional.empty()));
			FieldValue contractualParty = Optional.ofNullable(input.getContractualParty())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> partyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(contractualPartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(contractualPartyField, Optional.empty()));
			FieldValue otherParty = Optional.ofNullable(input.getOtherParty())
				.map(x -> x.stream()
					.map(_x -> partyRoleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(otherPartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(otherPartyField, Optional.empty()));
			FieldValue attachment = Optional.ofNullable(input.getAttachment())
				.map(x -> x.stream()
					.map(_x -> resourceTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(attachmentField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(attachmentField, Optional.empty()));
			FieldValue agreementTerms = Optional.ofNullable(input.getAgreementTerms())
				.map(x -> new NestedFieldValueImpl(agreementTermsField, Optional.of(agreementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(agreementTermsField, Optional.empty()));
			FieldValue relatedAgreements = Optional.ofNullable(input.getRelatedAgreements())
				.map(x -> x.stream()
					.map(_x -> legalAgreementTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(relatedAgreementsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(relatedAgreementsField, Optional.empty()));
			FieldValue umbrellaAgreement = Optional.ofNullable(input.getUmbrellaAgreement())
				.map(x -> new NestedFieldValueImpl(umbrellaAgreementField, Optional.of(umbrellaAgreementTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(umbrellaAgreementField, Optional.empty()));
			return Arrays.asList(
				agreementDate,
				effectiveDate,
				identifier,
				legalAgreementIdentification,
				contractualParty,
				otherParty,
				attachment,
				agreementTerms,
				relatedAgreements,
				umbrellaAgreement
			);
		}
	}
}
