package cdm.product.collateral.tabulator;

import cdm.base.math.tabulator.MoneyRangeTypeTabulator;
import cdm.base.math.tabulator.NumberRangeTypeTabulator;
import cdm.product.collateral.ConcentrationLimit;
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


@ImplementedBy(ConcentrationLimitTypeTabulator.Impl.class)
public interface ConcentrationLimitTypeTabulator extends Tabulator<ConcentrationLimit> {
	@Singleton
	class Impl implements ConcentrationLimitTypeTabulator {
		private final Field concentrationLimitCriteriaField;
		private final Field valueLimitField;
		private final Field percentageLimitField;

		private final ConcentrationLimitCriteriaTypeTabulator concentrationLimitCriteriaTypeTabulator;
		private final MoneyRangeTypeTabulator moneyRangeTypeTabulator;
		private final NumberRangeTypeTabulator numberRangeTypeTabulator;

		@Inject
		public Impl(ConcentrationLimitCriteriaTypeTabulator concentrationLimitCriteriaTypeTabulator, MoneyRangeTypeTabulator moneyRangeTypeTabulator, NumberRangeTypeTabulator numberRangeTypeTabulator) {
			this.concentrationLimitCriteriaTypeTabulator = concentrationLimitCriteriaTypeTabulator;
			this.moneyRangeTypeTabulator = moneyRangeTypeTabulator;
			this.numberRangeTypeTabulator = numberRangeTypeTabulator;
			this.concentrationLimitCriteriaField = new FieldImpl(
				"concentrationLimitCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valueLimitField = new FieldImpl(
				"valueLimit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.percentageLimitField = new FieldImpl(
				"percentageLimit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ConcentrationLimit input) {
			FieldValue concentrationLimitCriteria = Optional.ofNullable(input.getConcentrationLimitCriteria())
				.map(x -> new NestedFieldValueImpl(concentrationLimitCriteriaField, Optional.of(concentrationLimitCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(concentrationLimitCriteriaField, Optional.empty()));
			FieldValue valueLimit = Optional.ofNullable(input.getValueLimit())
				.map(x -> new NestedFieldValueImpl(valueLimitField, Optional.of(moneyRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valueLimitField, Optional.empty()));
			FieldValue percentageLimit = Optional.ofNullable(input.getPercentageLimit())
				.map(x -> new NestedFieldValueImpl(percentageLimitField, Optional.of(numberRangeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(percentageLimitField, Optional.empty()));
			return Arrays.asList(
				concentrationLimitCriteria,
				valueLimit,
				percentageLimit
			);
		}
	}
}
