package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.MasterAgreementClause;
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


@ImplementedBy(MasterAgreementClauseTypeTabulator.Impl.class)
public interface MasterAgreementClauseTypeTabulator extends Tabulator<MasterAgreementClause> {
	@Singleton
	class Impl implements MasterAgreementClauseTypeTabulator {
		private final Field identiferField;
		private final Field nameField;
		private final Field counterpartyField;
		private final Field otherPartyField;
		private final Field variantField;

		private final MasterAgreementClauseVariantTypeTabulator masterAgreementClauseVariantTypeTabulator;

		@Inject
		public Impl(MasterAgreementClauseVariantTypeTabulator masterAgreementClauseVariantTypeTabulator) {
			this.masterAgreementClauseVariantTypeTabulator = masterAgreementClauseVariantTypeTabulator;
			this.identiferField = new FieldImpl(
				"identifer",
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
			this.variantField = new FieldImpl(
				"variant",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MasterAgreementClause input) {
			FieldValue identifer = new FieldValueImpl(identiferField, Optional.ofNullable(input.getIdentifer()));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName()));
			FieldValue counterparty = new FieldValueImpl(counterpartyField, Optional.ofNullable(input.getCounterparty()));
			FieldValue otherParty = new FieldValueImpl(otherPartyField, Optional.ofNullable(input.getOtherParty()));
			FieldValue variant = Optional.ofNullable(input.getVariant())
				.map(x -> x.stream()
					.map(_x -> masterAgreementClauseVariantTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(variantField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(variantField, Optional.empty()));
			return Arrays.asList(
				identifer,
				name,
				counterparty,
				otherParty,
				variant
			);
		}
	}
}
