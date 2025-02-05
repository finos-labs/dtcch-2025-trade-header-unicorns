package cdm.base.datetime.tabulator;

import cdm.base.datetime.AdjustableOrRelativeDates;
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


@ImplementedBy(AdjustableOrRelativeDatesTypeTabulator.Impl.class)
public interface AdjustableOrRelativeDatesTypeTabulator extends Tabulator<AdjustableOrRelativeDates> {
	@Singleton
	class Impl implements AdjustableOrRelativeDatesTypeTabulator {
		private final Field adjustableDatesField;
		private final Field relativeDatesField;

		private final AdjustableDatesTypeTabulator adjustableDatesTypeTabulator;
		private final RelativeDatesTypeTabulator relativeDatesTypeTabulator;

		@Inject
		public Impl(AdjustableDatesTypeTabulator adjustableDatesTypeTabulator, RelativeDatesTypeTabulator relativeDatesTypeTabulator) {
			this.adjustableDatesTypeTabulator = adjustableDatesTypeTabulator;
			this.relativeDatesTypeTabulator = relativeDatesTypeTabulator;
			this.adjustableDatesField = new FieldImpl(
				"adjustableDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relativeDatesField = new FieldImpl(
				"relativeDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AdjustableOrRelativeDates input) {
			FieldValue adjustableDates = Optional.ofNullable(input.getAdjustableDates())
				.map(x -> new NestedFieldValueImpl(adjustableDatesField, Optional.of(adjustableDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(adjustableDatesField, Optional.empty()));
			FieldValue relativeDates = Optional.ofNullable(input.getRelativeDates())
				.map(x -> new NestedFieldValueImpl(relativeDatesField, Optional.of(relativeDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relativeDatesField, Optional.empty()));
			return Arrays.asList(
				adjustableDates,
				relativeDates
			);
		}
	}
}
