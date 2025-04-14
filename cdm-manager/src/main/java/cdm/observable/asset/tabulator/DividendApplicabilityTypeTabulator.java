package cdm.observable.asset.tabulator;

import cdm.observable.asset.DividendApplicability;
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


@ImplementedBy(DividendApplicabilityTypeTabulator.Impl.class)
public interface DividendApplicabilityTypeTabulator extends Tabulator<DividendApplicability> {
	@Singleton
	class Impl implements DividendApplicabilityTypeTabulator {
		private final Field optionsExchangeDividendsField;
		private final Field additionalDividendsField;
		private final Field allDividendsField;

		public Impl() {
			this.optionsExchangeDividendsField = new FieldImpl(
				"optionsExchangeDividends",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalDividendsField = new FieldImpl(
				"additionalDividends",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.allDividendsField = new FieldImpl(
				"allDividends",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendApplicability input) {
			FieldValue optionsExchangeDividends = new FieldValueImpl(optionsExchangeDividendsField, Optional.ofNullable(input.getOptionsExchangeDividends()));
			FieldValue additionalDividends = new FieldValueImpl(additionalDividendsField, Optional.ofNullable(input.getAdditionalDividends()));
			FieldValue allDividends = new FieldValueImpl(allDividendsField, Optional.ofNullable(input.getAllDividends()));
			return Arrays.asList(
				optionsExchangeDividends,
				additionalDividends,
				allDividends
			);
		}
	}
}
