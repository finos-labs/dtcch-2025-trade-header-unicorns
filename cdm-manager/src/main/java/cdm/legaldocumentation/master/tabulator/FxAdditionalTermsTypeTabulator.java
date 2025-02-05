package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.FxAdditionalTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(FxAdditionalTermsTypeTabulator.Impl.class)
public interface FxAdditionalTermsTypeTabulator extends Tabulator<FxAdditionalTerms> {
	@Singleton
	class Impl implements FxAdditionalTermsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(FxAdditionalTerms input) {
			return Arrays.asList(
			);
		}
	}
}
