package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.UnderlierSubstitutionProvision;
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


@ImplementedBy(UnderlierSubstitutionProvisionTypeTabulator.Impl.class)
public interface UnderlierSubstitutionProvisionTypeTabulator extends Tabulator<UnderlierSubstitutionProvision> {
	@Singleton
	class Impl implements UnderlierSubstitutionProvisionTypeTabulator {
		private final Field whoMaySubstituteField;
		private final Field substitutionBeSpokeTermsField;
		private final Field substitutionTriggerEventsField;
		private final Field disputingPartyField;

		private final ClauseTypeTabulator clauseTypeTabulator;
		private final ExtraordinaryEventsTypeTabulator extraordinaryEventsTypeTabulator;

		@Inject
		public Impl(ClauseTypeTabulator clauseTypeTabulator, ExtraordinaryEventsTypeTabulator extraordinaryEventsTypeTabulator) {
			this.clauseTypeTabulator = clauseTypeTabulator;
			this.extraordinaryEventsTypeTabulator = extraordinaryEventsTypeTabulator;
			this.whoMaySubstituteField = new FieldImpl(
				"whoMaySubstitute",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.substitutionBeSpokeTermsField = new FieldImpl(
				"substitutionBeSpokeTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.substitutionTriggerEventsField = new FieldImpl(
				"substitutionTriggerEvents",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.disputingPartyField = new FieldImpl(
				"disputingParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(UnderlierSubstitutionProvision input) {
			FieldValue whoMaySubstitute = new FieldValueImpl(whoMaySubstituteField, Optional.ofNullable(input.getWhoMaySubstitute()));
			FieldValue substitutionBeSpokeTerms = Optional.ofNullable(input.getSubstitutionBeSpokeTerms())
				.map(x -> x.stream()
					.map(_x -> clauseTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(substitutionBeSpokeTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(substitutionBeSpokeTermsField, Optional.empty()));
			FieldValue substitutionTriggerEvents = Optional.ofNullable(input.getSubstitutionTriggerEvents())
				.map(x -> x.stream()
					.map(_x -> extraordinaryEventsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(substitutionTriggerEventsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(substitutionTriggerEventsField, Optional.empty()));
			FieldValue disputingParty = new FieldValueImpl(disputingPartyField, Optional.ofNullable(input.getDisputingParty()));
			return Arrays.asList(
				whoMaySubstitute,
				substitutionBeSpokeTerms,
				substitutionTriggerEvents,
				disputingParty
			);
		}
	}
}
