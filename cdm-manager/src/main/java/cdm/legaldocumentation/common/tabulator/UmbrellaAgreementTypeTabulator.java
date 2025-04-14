package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.UmbrellaAgreement;
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


@ImplementedBy(UmbrellaAgreementTypeTabulator.Impl.class)
public interface UmbrellaAgreementTypeTabulator extends Tabulator<UmbrellaAgreement> {
	@Singleton
	class Impl implements UmbrellaAgreementTypeTabulator {
		private final Field isApplicableField;
		private final Field languageField;
		private final Field partiesField;

		private final UmbrellaAgreementEntityTypeTabulator umbrellaAgreementEntityTypeTabulator;

		@Inject
		public Impl(UmbrellaAgreementEntityTypeTabulator umbrellaAgreementEntityTypeTabulator) {
			this.umbrellaAgreementEntityTypeTabulator = umbrellaAgreementEntityTypeTabulator;
			this.isApplicableField = new FieldImpl(
				"isApplicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.languageField = new FieldImpl(
				"language",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partiesField = new FieldImpl(
				"parties",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(UmbrellaAgreement input) {
			FieldValue isApplicable = new FieldValueImpl(isApplicableField, Optional.ofNullable(input.getIsApplicable()));
			FieldValue language = new FieldValueImpl(languageField, Optional.ofNullable(input.getLanguage()));
			FieldValue parties = Optional.ofNullable(input.getParties())
				.map(x -> x.stream()
					.map(_x -> umbrellaAgreementEntityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partiesField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partiesField, Optional.empty()));
			return Arrays.asList(
				isApplicable,
				language,
				parties
			);
		}
	}
}
