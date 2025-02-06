package cdm.product.collateral.tabulator;

import cdm.product.collateral.AnyCriteria;
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


@ImplementedBy(AnyCriteriaTypeTabulator.Impl.class)
public interface AnyCriteriaTypeTabulator extends Tabulator<AnyCriteria> {
	@Singleton
	class Impl implements AnyCriteriaTypeTabulator {
		private final Field anyCriteriaField;

		private final CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator;

		@Inject
		public Impl(CollateralCriteriaTypeTabulator collateralCriteriaTypeTabulator) {
			this.collateralCriteriaTypeTabulator = collateralCriteriaTypeTabulator;
			this.anyCriteriaField = new FieldImpl(
				"anyCriteria",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AnyCriteria input) {
			FieldValue anyCriteria = Optional.ofNullable(input.getAnyCriteria())
				.map(x -> x.stream()
					.map(_x -> collateralCriteriaTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(anyCriteriaField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(anyCriteriaField, Optional.empty()));
			return Arrays.asList(
				anyCriteria
			);
		}
	}
}
