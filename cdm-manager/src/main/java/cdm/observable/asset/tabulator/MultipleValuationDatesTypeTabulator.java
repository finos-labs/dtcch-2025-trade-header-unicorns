package cdm.observable.asset.tabulator;

import cdm.observable.asset.MultipleValuationDates;
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


@ImplementedBy(MultipleValuationDatesTypeTabulator.Impl.class)
public interface MultipleValuationDatesTypeTabulator extends Tabulator<MultipleValuationDates> {
	@Singleton
	class Impl implements MultipleValuationDatesTypeTabulator {
		private final Field businessDaysField;
		private final Field businessDaysThereafterField;
		private final Field numberValuationDatesField;

		public Impl() {
			this.businessDaysField = new FieldImpl(
				"businessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessDaysThereafterField = new FieldImpl(
				"businessDaysThereafter",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.numberValuationDatesField = new FieldImpl(
				"numberValuationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MultipleValuationDates input) {
			FieldValue businessDays = new FieldValueImpl(businessDaysField, Optional.ofNullable(input.getBusinessDays()));
			FieldValue businessDaysThereafter = new FieldValueImpl(businessDaysThereafterField, Optional.ofNullable(input.getBusinessDaysThereafter()));
			FieldValue numberValuationDates = new FieldValueImpl(numberValuationDatesField, Optional.ofNullable(input.getNumberValuationDates()));
			return Arrays.asList(
				businessDays,
				businessDaysThereafter,
				numberValuationDates
			);
		}
	}
}
