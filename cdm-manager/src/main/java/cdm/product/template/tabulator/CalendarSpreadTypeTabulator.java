package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.product.template.CalendarSpread;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CalendarSpreadTypeTabulator.Impl.class)
public interface CalendarSpreadTypeTabulator extends Tabulator<CalendarSpread> {
	@Singleton
	class Impl implements CalendarSpreadTypeTabulator {
		private final Field expirationDateTwoField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.expirationDateTwoField = new FieldImpl(
				"expirationDateTwo",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalendarSpread input) {
			FieldValue expirationDateTwo = Optional.ofNullable(input.getExpirationDateTwo())
				.map(x -> new NestedFieldValueImpl(expirationDateTwoField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(expirationDateTwoField, Optional.empty()));
			return Arrays.asList(
				expirationDateTwo
			);
		}
	}
}
