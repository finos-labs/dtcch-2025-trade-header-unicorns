package cdm.legaldocumentation.csa.tabulator;

import cdm.legaldocumentation.csa.CollateralTransferAgreementElections;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(CollateralTransferAgreementElectionsTypeTabulator.Impl.class)
public interface CollateralTransferAgreementElectionsTypeTabulator extends Tabulator<CollateralTransferAgreementElections> {
	@Singleton
	class Impl implements CollateralTransferAgreementElectionsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(CollateralTransferAgreementElections input) {
			return Arrays.asList(
			);
		}
	}
}
