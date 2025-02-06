package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.NaturalPersonRole;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(NaturalPersonRoleTypeTabulator.Impl.class)
public interface NaturalPersonRoleTypeTabulator extends Tabulator<NaturalPersonRole> {
	@Singleton
	class Impl implements NaturalPersonRoleTypeTabulator {
		private final Field personReferenceField;
		private final Field roleField;

		private final NaturalPersonTypeTabulator naturalPersonTypeTabulator;

		@Inject
		public Impl(NaturalPersonTypeTabulator naturalPersonTypeTabulator) {
			this.naturalPersonTypeTabulator = naturalPersonTypeTabulator;
			this.personReferenceField = new FieldImpl(
				"personReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.roleField = new FieldImpl(
				"role",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NaturalPersonRole input) {
			FieldValue personReference = Optional.ofNullable(input.getPersonReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(personReferenceField, Optional.of(naturalPersonTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(personReferenceField, Optional.empty()));
			FieldValue role = new FieldValueImpl(roleField, Optional.ofNullable(input.getRole())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				personReference,
				role
			);
		}
	}
}
