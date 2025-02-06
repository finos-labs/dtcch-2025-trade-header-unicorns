package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.DeterminationRolesAndTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(DeterminationRolesAndTermsTypeTabulator.Impl.class)
public interface DeterminationRolesAndTermsTypeTabulator extends Tabulator<DeterminationRolesAndTerms> {
	@Singleton
	class Impl implements DeterminationRolesAndTermsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(DeterminationRolesAndTerms input) {
			return Arrays.asList(
			);
		}
	}
}
