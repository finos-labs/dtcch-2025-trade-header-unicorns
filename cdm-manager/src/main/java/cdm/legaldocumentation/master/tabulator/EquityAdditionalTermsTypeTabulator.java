package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.EquityAdditionalTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(EquityAdditionalTermsTypeTabulator.Impl.class)
public interface EquityAdditionalTermsTypeTabulator extends Tabulator<EquityAdditionalTerms> {
	@Singleton
	class Impl implements EquityAdditionalTermsTypeTabulator {
		private final Field extraordinaryEventsField;
		private final Field determinationTermsField;
		private final Field substitutionProvisionField;

		private final ExtraordinaryEventsTypeTabulator extraordinaryEventsTypeTabulator;
		private final DeterminationRolesAndTermsTypeTabulator determinationRolesAndTermsTypeTabulator;
		private final UnderlierSubstitutionProvisionTypeTabulator underlierSubstitutionProvisionTypeTabulator;

		@Inject
		public Impl(ExtraordinaryEventsTypeTabulator extraordinaryEventsTypeTabulator, DeterminationRolesAndTermsTypeTabulator determinationRolesAndTermsTypeTabulator, UnderlierSubstitutionProvisionTypeTabulator underlierSubstitutionProvisionTypeTabulator) {
			this.extraordinaryEventsTypeTabulator = extraordinaryEventsTypeTabulator;
			this.determinationRolesAndTermsTypeTabulator = determinationRolesAndTermsTypeTabulator;
			this.underlierSubstitutionProvisionTypeTabulator = underlierSubstitutionProvisionTypeTabulator;
			this.extraordinaryEventsField = new FieldImpl(
				"extraordinaryEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.determinationTermsField = new FieldImpl(
				"determinationTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.substitutionProvisionField = new FieldImpl(
				"substitutionProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EquityAdditionalTerms input) {
			FieldValue extraordinaryEvents = Optional.ofNullable(input.getExtraordinaryEvents())
				.map(x -> new NestedFieldValueImpl(extraordinaryEventsField, Optional.of(extraordinaryEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extraordinaryEventsField, Optional.empty()));
			FieldValue determinationTerms = Optional.ofNullable(input.getDeterminationTerms())
				.map(x -> x.stream()
					.map(_x -> determinationRolesAndTermsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(determinationTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(determinationTermsField, Optional.empty()));
			FieldValue substitutionProvision = Optional.ofNullable(input.getSubstitutionProvision())
				.map(x -> new NestedFieldValueImpl(substitutionProvisionField, Optional.of(underlierSubstitutionProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(substitutionProvisionField, Optional.empty()));
			return Arrays.asList(
				extraordinaryEvents,
				determinationTerms,
				substitutionProvision
			);
		}
	}
}
