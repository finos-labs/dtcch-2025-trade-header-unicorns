package cdm.observable.asset.tabulator;

import cdm.observable.asset.SingleValuationDate;
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


@ImplementedBy(SingleValuationDateTypeTabulator.Impl.class)
public interface SingleValuationDateTypeTabulator extends Tabulator<SingleValuationDate> {
	@Singleton
	class Impl implements SingleValuationDateTypeTabulator {
		private final Field businessDaysField;

		public Impl() {
			this.businessDaysField = new FieldImpl(
				"businessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SingleValuationDate input) {
			FieldValue businessDays = new FieldValueImpl(businessDaysField, Optional.ofNullable(input.getBusinessDays()));
			return Arrays.asList(
				businessDays
			);
		}
	}
}
