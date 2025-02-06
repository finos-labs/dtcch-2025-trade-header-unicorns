package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.Representations;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(RepresentationsTypeTabulator.Impl.class)
public interface RepresentationsTypeTabulator extends Tabulator<Representations> {
	@Singleton
	class Impl implements RepresentationsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(Representations input) {
			return Arrays.asList(
			);
		}
	}
}
