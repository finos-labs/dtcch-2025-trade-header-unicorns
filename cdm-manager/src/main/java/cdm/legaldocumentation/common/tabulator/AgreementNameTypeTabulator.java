package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.AgreementName;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AgreementNameTypeTabulator.Impl.class)
public interface AgreementNameTypeTabulator extends Tabulator<AgreementName> {
	@Singleton
	class Impl implements AgreementNameTypeTabulator {
		private final Field agreementTypeField;
		private final Field creditSupportAgreementTypeField;
		private final Field creditSupportAgreementMarginTypeField;
		private final Field contractualDefinitionsTypeField;
		private final Field contractualTermsSupplementField;
		private final Field contractualMatrixField;
		private final Field masterAgreementTypeField;
		private final Field masterConfirmationTypeField;
		private final Field masterConfirmationAnnexTypeField;
		private final Field otherAgreementField;

		private final ContractualTermsSupplementTypeTabulator contractualTermsSupplementTypeTabulator;
		private final ContractualMatrixTypeTabulator contractualMatrixTypeTabulator;

		@Inject
		public Impl(ContractualTermsSupplementTypeTabulator contractualTermsSupplementTypeTabulator, ContractualMatrixTypeTabulator contractualMatrixTypeTabulator) {
			this.contractualTermsSupplementTypeTabulator = contractualTermsSupplementTypeTabulator;
			this.contractualMatrixTypeTabulator = contractualMatrixTypeTabulator;
			this.agreementTypeField = new FieldImpl(
				"agreementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditSupportAgreementTypeField = new FieldImpl(
				"creditSupportAgreementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditSupportAgreementMarginTypeField = new FieldImpl(
				"creditSupportAgreementMarginType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contractualDefinitionsTypeField = new FieldImpl(
				"contractualDefinitionsType",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contractualTermsSupplementField = new FieldImpl(
				"contractualTermsSupplement",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.contractualMatrixField = new FieldImpl(
				"contractualMatrix",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.masterAgreementTypeField = new FieldImpl(
				"masterAgreementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.masterConfirmationTypeField = new FieldImpl(
				"masterConfirmationType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.masterConfirmationAnnexTypeField = new FieldImpl(
				"masterConfirmationAnnexType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.otherAgreementField = new FieldImpl(
				"otherAgreement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AgreementName input) {
			FieldValue agreementType = new FieldValueImpl(agreementTypeField, Optional.ofNullable(input.getAgreementType()));
			FieldValue creditSupportAgreementType = new FieldValueImpl(creditSupportAgreementTypeField, Optional.ofNullable(input.getCreditSupportAgreementType())
				.map(x -> x.getValue()));
			FieldValue creditSupportAgreementMarginType = new FieldValueImpl(creditSupportAgreementMarginTypeField, Optional.ofNullable(input.getCreditSupportAgreementMarginType()));
			FieldValue contractualDefinitionsType = new FieldValueImpl(contractualDefinitionsTypeField, Optional.ofNullable(input.getContractualDefinitionsType())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue contractualTermsSupplement = Optional.ofNullable(input.getContractualTermsSupplement())
				.map(x -> x.stream()
					.map(_x -> contractualTermsSupplementTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(contractualTermsSupplementField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(contractualTermsSupplementField, Optional.empty()));
			FieldValue contractualMatrix = Optional.ofNullable(input.getContractualMatrix())
				.map(x -> x.stream()
					.map(_x -> contractualMatrixTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(contractualMatrixField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(contractualMatrixField, Optional.empty()));
			FieldValue masterAgreementType = new FieldValueImpl(masterAgreementTypeField, Optional.ofNullable(input.getMasterAgreementType())
				.map(x -> x.getValue()));
			FieldValue masterConfirmationType = new FieldValueImpl(masterConfirmationTypeField, Optional.ofNullable(input.getMasterConfirmationType())
				.map(x -> x.getValue()));
			FieldValue masterConfirmationAnnexType = new FieldValueImpl(masterConfirmationAnnexTypeField, Optional.ofNullable(input.getMasterConfirmationAnnexType())
				.map(x -> x.getValue()));
			FieldValue otherAgreement = new FieldValueImpl(otherAgreementField, Optional.ofNullable(input.getOtherAgreement()));
			return Arrays.asList(
				agreementType,
				creditSupportAgreementType,
				creditSupportAgreementMarginType,
				contractualDefinitionsType,
				contractualTermsSupplement,
				contractualMatrix,
				masterAgreementType,
				masterConfirmationType,
				masterConfirmationAnnexType,
				otherAgreement
			);
		}
	}
}
