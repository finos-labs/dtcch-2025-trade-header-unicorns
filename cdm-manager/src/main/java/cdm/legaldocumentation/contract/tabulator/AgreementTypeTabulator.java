package cdm.legaldocumentation.contract.tabulator;

import cdm.legaldocumentation.contract.Agreement;
import cdm.legaldocumentation.csa.tabulator.CollateralTransferAgreementElectionsTypeTabulator;
import cdm.legaldocumentation.csa.tabulator.CreditSupportAgreementElectionsTypeTabulator;
import cdm.legaldocumentation.csa.tabulator.SecurityAgreementElectionsTypeTabulator;
import cdm.legaldocumentation.master.tabulator.MasterAgreementScheduleTypeTabulator;
import cdm.legaldocumentation.master.tabulator.TransactionAdditionalTermsTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AgreementTypeTabulator.Impl.class)
public interface AgreementTypeTabulator extends Tabulator<Agreement> {
	@Singleton
	class Impl implements AgreementTypeTabulator {
		private final Field creditSupportAgreementElectionsField;
		private final Field collateralTransferAgreementElectionsField;
		private final Field securityAgreementElectionsField;
		private final Field masterAgreementScheduleField;
		private final Field transactionAdditionalTermsField;

		private final CreditSupportAgreementElectionsTypeTabulator creditSupportAgreementElectionsTypeTabulator;
		private final CollateralTransferAgreementElectionsTypeTabulator collateralTransferAgreementElectionsTypeTabulator;
		private final SecurityAgreementElectionsTypeTabulator securityAgreementElectionsTypeTabulator;
		private final MasterAgreementScheduleTypeTabulator masterAgreementScheduleTypeTabulator;
		private final TransactionAdditionalTermsTypeTabulator transactionAdditionalTermsTypeTabulator;

		@Inject
		public Impl(CreditSupportAgreementElectionsTypeTabulator creditSupportAgreementElectionsTypeTabulator, CollateralTransferAgreementElectionsTypeTabulator collateralTransferAgreementElectionsTypeTabulator, SecurityAgreementElectionsTypeTabulator securityAgreementElectionsTypeTabulator, MasterAgreementScheduleTypeTabulator masterAgreementScheduleTypeTabulator, TransactionAdditionalTermsTypeTabulator transactionAdditionalTermsTypeTabulator) {
			this.creditSupportAgreementElectionsTypeTabulator = creditSupportAgreementElectionsTypeTabulator;
			this.collateralTransferAgreementElectionsTypeTabulator = collateralTransferAgreementElectionsTypeTabulator;
			this.securityAgreementElectionsTypeTabulator = securityAgreementElectionsTypeTabulator;
			this.masterAgreementScheduleTypeTabulator = masterAgreementScheduleTypeTabulator;
			this.transactionAdditionalTermsTypeTabulator = transactionAdditionalTermsTypeTabulator;
			this.creditSupportAgreementElectionsField = new FieldImpl(
				"creditSupportAgreementElections",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralTransferAgreementElectionsField = new FieldImpl(
				"collateralTransferAgreementElections",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.securityAgreementElectionsField = new FieldImpl(
				"securityAgreementElections",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.masterAgreementScheduleField = new FieldImpl(
				"masterAgreementSchedule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transactionAdditionalTermsField = new FieldImpl(
				"transactionAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Agreement input) {
			FieldValue creditSupportAgreementElections = Optional.ofNullable(input.getCreditSupportAgreementElections())
				.map(x -> new NestedFieldValueImpl(creditSupportAgreementElectionsField, Optional.of(creditSupportAgreementElectionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditSupportAgreementElectionsField, Optional.empty()));
			FieldValue collateralTransferAgreementElections = Optional.ofNullable(input.getCollateralTransferAgreementElections())
				.map(x -> new NestedFieldValueImpl(collateralTransferAgreementElectionsField, Optional.of(collateralTransferAgreementElectionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralTransferAgreementElectionsField, Optional.empty()));
			FieldValue securityAgreementElections = Optional.ofNullable(input.getSecurityAgreementElections())
				.map(x -> new NestedFieldValueImpl(securityAgreementElectionsField, Optional.of(securityAgreementElectionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(securityAgreementElectionsField, Optional.empty()));
			FieldValue masterAgreementSchedule = Optional.ofNullable(input.getMasterAgreementSchedule())
				.map(x -> new NestedFieldValueImpl(masterAgreementScheduleField, Optional.of(masterAgreementScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(masterAgreementScheduleField, Optional.empty()));
			FieldValue transactionAdditionalTerms = Optional.ofNullable(input.getTransactionAdditionalTerms())
				.map(x -> new NestedFieldValueImpl(transactionAdditionalTermsField, Optional.of(transactionAdditionalTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(transactionAdditionalTermsField, Optional.empty()));
			return Arrays.asList(
				creditSupportAgreementElections,
				collateralTransferAgreementElections,
				securityAgreementElections,
				masterAgreementSchedule,
				transactionAdditionalTerms
			);
		}
	}
}
