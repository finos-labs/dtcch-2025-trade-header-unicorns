package cdm.product.collateral.tabulator;

import cdm.product.collateral.AllCriteria;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AllCriteriaTypeTabulator.Impl.class)
public interface AllCriteriaTypeTabulator extends Tabulator<AllCriteria> {
	@Singleton
	class Impl implements AllCriteriaTypeTabulator {
		private final Field allCriteriaField;

		private final CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator;

		@Inject
		public Impl(CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator) {
			this.collateralCriteriaTypeTabulator = collateralCriteriaTypeTabulator;
			this.allCriteriaField = new FieldImpl(
				"allCriteria",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AllCriteria input) {
			FieldValue allCriteria = Optional.ofNullable(input.getAllCriteria())
				.map(x -> x.stream()
					.map(_x -> collateralCriteriaTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(allCriteriaField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(allCriteriaField, Optional.empty()));
			return Arrays.asList(
				allCriteria
			);
		}
	}
}
