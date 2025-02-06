package cdm.base.datetime.tabulator;

import cdm.base.datetime.AdjustableOrAdjustedOrRelativeDate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AdjustableOrAdjustedOrRelativeDateTypeTabulator.Impl.class)
public interface AdjustableOrAdjustedOrRelativeDateTypeTabulator extends Tabulator<AdjustableOrAdjustedOrRelativeDate> {
	@Singleton
	class Impl implements AdjustableOrAdjustedOrRelativeDateTypeTabulator {
		private final Field unadjustedDateField;
		private final Field dateAdjustmentsField;
		private final Field adjustedDateField;
		private final Field relativeDateField;

		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;

		@Inject
		public Impl(BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator) {
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.unadjustedDateField = new FieldImpl(
				"unadjustedDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateAdjustmentsField = new FieldImpl(
				"dateAdjustments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedDateField = new FieldImpl(
				"adjustedDate",
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
		public List<FieldValue> tabulate(AdjustableOrAdjustedOrRelativeDate input) {
			FieldValue unadjustedDate = new FieldValueImpl(unadjustedDateField, Optional.ofNullable(input.getUnadjustedDate()));
			FieldValue dateAdjustments = Optional.ofNullable(input.getDateAdjustments())
				.map(x -> new NestedFieldValueImpl(dateAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateAdjustmentsField, Optional.empty()));
			FieldValue adjustedDate = new FieldValueImpl(adjustedDateField, Optional.ofNullable(input.getAdjustedDate())
				.map(x -> x.getValue()));
			FieldValue relativeDate = Optional.ofNullable(input.getRelativeDate())
				.map(x -> new NestedFieldValueImpl(relativeDateField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relativeDateField, Optional.empty()));
			return Arrays.asList(
				unadjustedDate,
				dateAdjustments,
				adjustedDate,
				relativeDate
			);
		}
	}
}
