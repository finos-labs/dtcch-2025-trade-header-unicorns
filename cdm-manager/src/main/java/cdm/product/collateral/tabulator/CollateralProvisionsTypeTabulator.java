package cdm.product.collateral.tabulator;

import cdm.product.collateral.CollateralProvisions;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CollateralProvisionsTypeTabulator.Impl.class)
public interface CollateralProvisionsTypeTabulator extends Tabulator<CollateralProvisions> {
	@Singleton
	class Impl implements CollateralProvisionsTypeTabulator {
		private final Field collateralTypeField;
		private final Field eligibleCollateralField;
		private final Field substitutionProvisionsField;

		private final EligibleCollateralCriteriaTypeTabulator eligibleCollateralCriteriaTypeTabulator;
		private final SubstitutionProvisionsTypeTabulator substitutionProvisionsTypeTabulator;

		@Inject
		public Impl(EligibleCollateralCriteriaTypeTabulator eligibleCollateralCriteriaTypeTabulator, SubstitutionProvisionsTypeTabulator substitutionProvisionsTypeTabulator) {
			this.eligibleCollateralCriteriaTypeTabulator = eligibleCollateralCriteriaTypeTabulator;
			this.substitutionProvisionsTypeTabulator = substitutionProvisionsTypeTabulator;
			this.collateralTypeField = new FieldImpl(
				"collateralType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.eligibleCollateralField = new FieldImpl(
				"eligibleCollateral",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.substitutionProvisionsField = new FieldImpl(
				"substitutionProvisions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralProvisions input) {
			FieldValue collateralType = new FieldValueImpl(collateralTypeField, Optional.ofNullable(input.getCollateralType()));
			FieldValue eligibleCollateral = Optional.ofNullable(input.getEligibleCollateral())
				.map(x -> x.stream()
					.map(_x -> eligibleCollateralCriteriaTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(eligibleCollateralField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(eligibleCollateralField, Optional.empty()));
			FieldValue substitutionProvisions = Optional.ofNullable(input.getSubstitutionProvisions())
				.map(x -> new NestedFieldValueImpl(substitutionProvisionsField, Optional.of(substitutionProvisionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(substitutionProvisionsField, Optional.empty()));
			return Arrays.asList(
				collateralType,
				eligibleCollateral,
				substitutionProvisions
			);
		}
	}
}
