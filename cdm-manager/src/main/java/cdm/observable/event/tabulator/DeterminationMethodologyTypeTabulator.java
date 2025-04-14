package cdm.observable.event.tabulator;

import cdm.observable.event.DeterminationMethodology;
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


@ImplementedBy(DeterminationMethodologyTypeTabulator.Impl.class)
public interface DeterminationMethodologyTypeTabulator extends Tabulator<DeterminationMethodology> {
	@Singleton
	class Impl implements DeterminationMethodologyTypeTabulator {
		private final Field determinationMethodField;
		private final Field averagingMethodField;

		public Impl() {
			this.determinationMethodField = new FieldImpl(
				"determinationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingMethodField = new FieldImpl(
				"averagingMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DeterminationMethodology input) {
			FieldValue determinationMethod = new FieldValueImpl(determinationMethodField, Optional.ofNullable(input.getDeterminationMethod()));
			FieldValue averagingMethod = new FieldValueImpl(averagingMethodField, Optional.ofNullable(input.getAveragingMethod()));
			return Arrays.asList(
				determinationMethod,
				averagingMethod
			);
		}
	}
}
