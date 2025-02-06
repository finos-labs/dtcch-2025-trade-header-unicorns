package cdm.base.datetime.tabulator;

import cdm.base.datetime.DateList;
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


@ImplementedBy(DateListTypeTabulator.Impl.class)
public interface DateListTypeTabulator extends Tabulator<DateList> {
	@Singleton
	class Impl implements DateListTypeTabulator {
		private final Field dateField;

		public Impl() {
			this.dateField = new FieldImpl(
				"date",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DateList input) {
			FieldValue date = new FieldValueImpl(dateField, Optional.ofNullable(input.getDate()));
			return Arrays.asList(
				date
			);
		}
	}
}
