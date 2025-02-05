package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.MasterAgreementClauseVariant;
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


@ImplementedBy(MasterAgreementClauseVariantTypeTabulator.Impl.class)
public interface MasterAgreementClauseVariantTypeTabulator extends Tabulator<MasterAgreementClauseVariant> {
	@Singleton
	class Impl implements MasterAgreementClauseVariantTypeTabulator {
		private final Field identifierField;
		private final Field nameField;
		private final Field counterpartyField;
		private final Field otherPartyField;
		private final Field variableSetField;

		private final MasterAgreementVariableSetTypeTabulator masterAgreementVariableSetTypeTabulator;

		@Inject
		public Impl(MasterAgreementVariableSetTypeTabulator masterAgreementVariableSetTypeTabulator) {
			this.masterAgreementVariableSetTypeTabulator = masterAgreementVariableSetTypeTabulator;
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nameField = new FieldImpl(
				"name",
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
			this.otherPartyField = new FieldImpl(
				"otherParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.variableSetField = new FieldImpl(
				"variableSet",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MasterAgreementClauseVariant input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier()));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName()));
			FieldValue counterparty = new FieldValueImpl(counterpartyField, Optional.ofNullable(input.getCounterparty()));
			FieldValue otherParty = new FieldValueImpl(otherPartyField, Optional.ofNullable(input.getOtherParty()));
			FieldValue variableSet = Optional.ofNullable(input.getVariableSet())
				.map(x -> x.stream()
					.map(_x -> masterAgreementVariableSetTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(variableSetField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(variableSetField, Optional.empty()));
			return Arrays.asList(
				identifier,
				name,
				counterparty,
				otherParty,
				variableSet
			);
		}
	}
}
