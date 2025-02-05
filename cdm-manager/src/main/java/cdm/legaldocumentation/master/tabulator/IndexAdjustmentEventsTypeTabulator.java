package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.IndexAdjustmentEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import java.util.Arrays;
import java.util.List;
import javax.inject.Singleton;


@ImplementedBy(IndexAdjustmentEventsTypeTabulator.Impl.class)
public interface IndexAdjustmentEventsTypeTabulator extends Tabulator<IndexAdjustmentEvents> {
	@Singleton
	class Impl implements IndexAdjustmentEventsTypeTabulator {

		public Impl() {
		}

		@Override
		public List<FieldValue> tabulate(IndexAdjustmentEvents input) {
			return Arrays.asList(
			);
		}
	}
}
