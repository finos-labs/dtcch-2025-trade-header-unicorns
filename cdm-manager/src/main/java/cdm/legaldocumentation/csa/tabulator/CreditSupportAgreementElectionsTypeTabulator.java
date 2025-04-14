package cdm.legaldocumentation.csa.tabulator;

import cdm.legaldocumentation.csa.CreditSupportAgreementElections;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(CreditSupportAgreementElectionsTypeTabulator.Impl.class)
public interface CreditSupportAgreementElectionsTypeTabulator extends Tabulator<CreditSupportAgreementElections> {
	@Singleton
	class Impl implements CreditSupportAgreementElectionsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(CreditSupportAgreementElections input) {
			return Arrays.asList(
			);
		}
	}
}
