package cdm.base.datetime.tabulator;

import cdm.base.datetime.CalculationPeriodFrequency;
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


@ImplementedBy(CalculationPeriodFrequencyTypeTabulator.Impl.class)
public interface CalculationPeriodFrequencyTypeTabulator extends Tabulator<CalculationPeriodFrequency> {
	@Singleton
	class Impl implements CalculationPeriodFrequencyTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;
		private final Field rollConventionField;
		private final Field balanceOfFirstPeriodField;

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
			this.rollConventionField = new FieldImpl(
				"rollConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.balanceOfFirstPeriodField = new FieldImpl(
				"balanceOfFirstPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalculationPeriodFrequency input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			FieldValue rollConvention = new FieldValueImpl(rollConventionField, Optional.ofNullable(input.getRollConvention()));
			FieldValue balanceOfFirstPeriod = new FieldValueImpl(balanceOfFirstPeriodField, Optional.ofNullable(input.getBalanceOfFirstPeriod()));
			return Arrays.asList(
				periodMultiplier,
				period,
				rollConvention,
				balanceOfFirstPeriod
			);
		}
	}
}
