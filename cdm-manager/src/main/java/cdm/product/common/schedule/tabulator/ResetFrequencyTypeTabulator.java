package cdm.product.common.schedule.tabulator;

import cdm.product.common.schedule.ResetFrequency;
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


@ImplementedBy(ResetFrequencyTypeTabulator.Impl.class)
public interface ResetFrequencyTypeTabulator extends Tabulator<ResetFrequency> {
	@Singleton
	class Impl implements ResetFrequencyTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;
		private final Field weeklyRollConventionField;

		public Impl() {
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
			this.weeklyRollConventionField = new FieldImpl(
				"weeklyRollConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ResetFrequency input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			FieldValue weeklyRollConvention = new FieldValueImpl(weeklyRollConventionField, Optional.ofNullable(input.getWeeklyRollConvention()));
			return Arrays.asList(
				periodMultiplier,
				period,
				weeklyRollConvention
			);
		}
	}
}
