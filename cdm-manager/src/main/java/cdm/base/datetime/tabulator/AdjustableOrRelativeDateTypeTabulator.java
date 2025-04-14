package cdm.base.datetime.tabulator;

import cdm.base.datetime.AdjustableOrRelativeDate;
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


@ImplementedBy(AdjustableOrRelativeDateTypeTabulator.Impl.class)
public interface AdjustableOrRelativeDateTypeTabulator extends Tabulator<AdjustableOrRelativeDate> {
	@Singleton
	class Impl implements AdjustableOrRelativeDateTypeTabulator {
		private final Field adjustableDateField;
		private final Field relativeDateField;

		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;
		private final AdjustedRelativeDateOffsetTypeTabulator adjustedRelativeDateOffsetTypeTabulator;

		@Inject
		public Impl(AdjustableDateTypeTabulator adjustableDateTypeTabulator, AdjustedRelativeDateOffsetTypeTabulator adjustedRelativeDateOffsetTypeTabulator) {
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.adjustedRelativeDateOffsetTypeTabulator = adjustedRelativeDateOffsetTypeTabulator;
			this.adjustableDateField = new FieldImpl(
				"adjustableDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relativeDateField = new FieldImpl(
				"relativeDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AdjustableOrRelativeDate input) {
			FieldValue adjustableDate = Optional.ofNullable(input.getAdjustableDate())
				.map(x -> new NestedFieldValueImpl(adjustableDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(adjustableDateField, Optional.empty()));
			FieldValue relativeDate = Optional.ofNullable(input.getRelativeDate())
				.map(x -> new NestedFieldValueImpl(relativeDateField, Optional.of(adjustedRelativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relativeDateField, Optional.empty()));
			return Arrays.asList(
				adjustableDate,
				relativeDate
			);
		}
	}
}
