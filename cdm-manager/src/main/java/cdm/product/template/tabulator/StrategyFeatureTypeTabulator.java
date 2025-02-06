package cdm.product.template.tabulator;

import cdm.product.template.StrategyFeature;
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


@ImplementedBy(StrategyFeatureTypeTabulator.Impl.class)
public interface StrategyFeatureTypeTabulator extends Tabulator<StrategyFeature> {
	@Singleton
	class Impl implements StrategyFeatureTypeTabulator {
		private final Field strikeSpreadField;
		private final Field calendarSpreadField;

		private final StrikeSpreadTypeTabulator strikeSpreadTypeTabulator;
		private final CalendarSpreadTypeTabulator calendarSpreadTypeTabulator;

		@Inject
		public Impl(StrikeSpreadTypeTabulator strikeSpreadTypeTabulator, CalendarSpreadTypeTabulator calendarSpreadTypeTabulator) {
			this.strikeSpreadTypeTabulator = strikeSpreadTypeTabulator;
			this.calendarSpreadTypeTabulator = calendarSpreadTypeTabulator;
			this.strikeSpreadField = new FieldImpl(
				"strikeSpread",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calendarSpreadField = new FieldImpl(
				"calendarSpread",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StrategyFeature input) {
			FieldValue strikeSpread = Optional.ofNullable(input.getStrikeSpread())
				.map(x -> new NestedFieldValueImpl(strikeSpreadField, Optional.of(strikeSpreadTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(strikeSpreadField, Optional.empty()));
			FieldValue calendarSpread = Optional.ofNullable(input.getCalendarSpread())
				.map(x -> new NestedFieldValueImpl(calendarSpreadField, Optional.of(calendarSpreadTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calendarSpreadField, Optional.empty()));
			return Arrays.asList(
				strikeSpread,
				calendarSpread
			);
		}
	}
}
