package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.Clause;
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


@ImplementedBy(ClauseTypeTabulator.Impl.class)
public interface ClauseTypeTabulator extends Tabulator<Clause> {
	@Singleton
	class Impl implements ClauseTypeTabulator {
		private final Field identifierField;
		private final Field termsField;
		private final Field subcomponentsField;

		private final ClauseTypeTabulator clauseTypeTabulator;

		@Inject
		public Impl(ClauseTypeTabulator clauseTypeTabulator) {
			this.clauseTypeTabulator = clauseTypeTabulator;
			this.identifierField = new FieldImpl(
				"identifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.termsField = new FieldImpl(
				"terms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.subcomponentsField = new FieldImpl(
				"subcomponents",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Clause input) {
			FieldValue identifier = new FieldValueImpl(identifierField, Optional.ofNullable(input.getIdentifier()));
			FieldValue terms = new FieldValueImpl(termsField, Optional.ofNullable(input.getTerms()));
			FieldValue subcomponents = Optional.ofNullable(input.getSubcomponents())
				.map(x -> x.stream()
					.map(_x -> clauseTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(subcomponentsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(subcomponentsField, Optional.empty()));
			return Arrays.asList(
				identifier,
				terms,
				subcomponents
			);
		}
	}
}
