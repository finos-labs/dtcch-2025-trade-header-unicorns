package cdm.base.datetime.tabulator;

import cdm.base.datetime.RelativeDates;
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


@ImplementedBy(RelativeDatesTypeTabulator.Impl.class)
public interface RelativeDatesTypeTabulator extends Tabulator<RelativeDates> {
	@Singleton
	class Impl implements RelativeDatesTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;
		private final Field dayTypeField;
		private final Field businessDayConventionField;
		private final Field businessCentersField;
		private final Field businessCentersReferenceField;
		private final Field dateRelativeToField;
		private final Field adjustedDateField;
		private final Field periodSkipField;
		private final Field scheduleBoundsField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;
		private final DateRangeTypeTabulator dateRangeTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator, DateRangeTypeTabulator dateRangeTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.dateRangeTypeTabulator = dateRangeTypeTabulator;
			this.periodMultiplierField = new FieldImpl(
				"periodMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayTypeField = new FieldImpl(
				"dayType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessDayConventionField = new FieldImpl(
				"businessDayConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCentersField = new FieldImpl(
				"businessCenters",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCentersReferenceField = new FieldImpl(
				"businessCentersReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateRelativeToField = new FieldImpl(
				"dateRelativeTo",
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
			this.periodSkipField = new FieldImpl(
				"periodSkip",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.scheduleBoundsField = new FieldImpl(
				"scheduleBounds",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(RelativeDates input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			FieldValue dayType = new FieldValueImpl(dayTypeField, Optional.ofNullable(input.getDayType()));
			FieldValue businessDayConvention = new FieldValueImpl(businessDayConventionField, Optional.ofNullable(input.getBusinessDayConvention()));
			FieldValue businessCenters = Optional.ofNullable(input.getBusinessCenters())
				.map(x -> new NestedFieldValueImpl(businessCentersField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersField, Optional.empty()));
			FieldValue businessCentersReference = Optional.ofNullable(input.getBusinessCentersReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(businessCentersReferenceField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersReferenceField, Optional.empty()));
			FieldValue dateRelativeTo = new FieldValueImpl(dateRelativeToField, Optional.ofNullable(input.getDateRelativeTo())
				.map(x -> x.getValue()));
			FieldValue adjustedDate = new FieldValueImpl(adjustedDateField, Optional.ofNullable(input.getAdjustedDate()));
			FieldValue periodSkip = new FieldValueImpl(periodSkipField, Optional.ofNullable(input.getPeriodSkip()));
			FieldValue scheduleBounds = Optional.ofNullable(input.getScheduleBounds())
				.map(x -> new NestedFieldValueImpl(scheduleBoundsField, Optional.of(dateRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduleBoundsField, Optional.empty()));
			return Arrays.asList(
				periodMultiplier,
				period,
				dayType,
				businessDayConvention,
				businessCenters,
				businessCentersReference,
				dateRelativeTo,
				adjustedDate,
				periodSkip,
				scheduleBounds
			);
		}
	}
}
