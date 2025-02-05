package cdm.observable.asset.tabulator;

import cdm.observable.asset.ValuationPostponement;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(ValuationPostponementTypeTabulator.Impl.class)
public interface ValuationPostponementTypeTabulator extends Tabulator<ValuationPostponement> {
	@Singleton
	class Impl implements ValuationPostponementTypeTabulator {
		private final Field maximumDaysOfPostponementField;

		public Impl() {
			this.maximumDaysOfPostponementField = new FieldImpl(
				"maximumDaysOfPostponement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationPostponement input) {
			FieldValue maximumDaysOfPostponement = new FieldValueImpl(maximumDaysOfPostponementField, Optional.ofNullable(input.getMaximumDaysOfPostponement()));
			return Arrays.asList(
				maximumDaysOfPostponement
			);
		}
	}
}
