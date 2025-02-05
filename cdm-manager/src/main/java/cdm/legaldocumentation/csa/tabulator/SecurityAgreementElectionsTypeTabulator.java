package cdm.legaldocumentation.csa.tabulator;

import cdm.legaldocumentation.csa.SecurityAgreementElections;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(SecurityAgreementElectionsTypeTabulator.Impl.class)
public interface SecurityAgreementElectionsTypeTabulator extends Tabulator<SecurityAgreementElections> {
	@Singleton
	class Impl implements SecurityAgreementElectionsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(SecurityAgreementElections input) {
			return Arrays.asList(
			);
		}
	}
}
