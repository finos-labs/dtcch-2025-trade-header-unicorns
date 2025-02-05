package cdm.product.collateral.tabulator;

import cdm.product.collateral.NegativeCriteria;
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


@ImplementedBy(NegativeCriteriaTypeTabulator.Impl.class)
public interface NegativeCriteriaTypeTabulator extends Tabulator<NegativeCriteria> {
	@Singleton
	class Impl implements NegativeCriteriaTypeTabulator {
		private final Field negativeCriteriaField;

		private final CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator;

		@Inject
		public Impl(CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator) {
			this.collateralCriteriaTypeTabulator = collateralCriteriaTypeTabulator;
			this.negativeCriteriaField = new FieldImpl(
				"negativeCriteria",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NegativeCriteria input) {
			FieldValue negativeCriteria = Optional.ofNullable(input.getNegativeCriteria())
				.map(x -> new NestedFieldValueImpl(negativeCriteriaField, Optional.of(collateralCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(negativeCriteriaField, Optional.empty()));
			return Arrays.asList(
				negativeCriteria
			);
		}
	}
}
