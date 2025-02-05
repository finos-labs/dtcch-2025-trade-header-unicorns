package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.EquityCorporateEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(EquityCorporateEventsTypeTabulator.Impl.class)
public interface EquityCorporateEventsTypeTabulator extends Tabulator<EquityCorporateEvents> {
	@Singleton
	class Impl implements EquityCorporateEventsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(EquityCorporateEvents input) {
			return Arrays.asList(
			);
		}
	}
}
