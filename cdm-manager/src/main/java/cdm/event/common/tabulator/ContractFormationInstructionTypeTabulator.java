package cdm.event.common.tabulator;

import cdm.event.common.ContractFormationInstruction;
import cdm.legaldocumentation.common.tabulator.LegalAgreementTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ContractFormationInstructionTypeTabulator.Impl.class)
public interface ContractFormationInstructionTypeTabulator extends Tabulator<ContractFormationInstruction> {
	@Singleton
	class Impl implements ContractFormationInstructionTypeTabulator {
		private final Field legalAgreementField;

		private final LegalAgreementTypeTabulator legalAgreementTypeTabulator;

		@Inject
		public Impl(LegalAgreementTypeTabulator legalAgreementTypeTabulator) {
			this.legalAgreementTypeTabulator = legalAgreementTypeTabulator;
			this.legalAgreementField = new FieldImpl(
				"legalAgreement",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ContractFormationInstruction input) {
			FieldValue legalAgreement = Optional.ofNullable(input.getLegalAgreement())
				.map(x -> x.stream()
					.map(_x -> legalAgreementTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(legalAgreementField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(legalAgreementField, Optional.empty()));
			return Arrays.asList(
				legalAgreement
			);
		}
	}
}
