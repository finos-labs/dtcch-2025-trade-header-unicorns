package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.CollateralTaxonomyValue;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;


@ImplementedBy(CollateralTaxonomyValueTypeTabulator.Impl.class)
public interface CollateralTaxonomyValueTypeTabulator extends Tabulator<CollateralTaxonomyValue> {
	@Singleton
	class Impl implements CollateralTaxonomyValueTypeTabulator {
		private final Field eu_EMIR_EligibleCollateralField;
		private final Field uk_EMIR_EligibleCollateralField;
		private final Field us_CFTC_PR_EligibleCollateralField;
		private final Field nonEnumeratedTaxonomyValueField;

		public Impl() {
			this.eu_EMIR_EligibleCollateralField = new FieldImpl(
				"eu_EMIR_EligibleCollateral",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.uk_EMIR_EligibleCollateralField = new FieldImpl(
				"uk_EMIR_EligibleCollateral",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.us_CFTC_PR_EligibleCollateralField = new FieldImpl(
				"us_CFTC_PR_EligibleCollateral",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nonEnumeratedTaxonomyValueField = new FieldImpl(
				"nonEnumeratedTaxonomyValue",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralTaxonomyValue input) {
			FieldValue eu_EMIR_EligibleCollateral = new FieldValueImpl(eu_EMIR_EligibleCollateralField, Optional.ofNullable(input.getEu_EMIR_EligibleCollateral()));
			FieldValue uk_EMIR_EligibleCollateral = new FieldValueImpl(uk_EMIR_EligibleCollateralField, Optional.ofNullable(input.getUk_EMIR_EligibleCollateral()));
			FieldValue us_CFTC_PR_EligibleCollateral = new FieldValueImpl(us_CFTC_PR_EligibleCollateralField, Optional.ofNullable(input.getUs_CFTC_PR_EligibleCollateral()));
			FieldValue nonEnumeratedTaxonomyValue = new FieldValueImpl(nonEnumeratedTaxonomyValueField, Optional.ofNullable(input.getNonEnumeratedTaxonomyValue())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				eu_EMIR_EligibleCollateral,
				uk_EMIR_EligibleCollateral,
				us_CFTC_PR_EligibleCollateral,
				nonEnumeratedTaxonomyValue
			);
		}
	}
}
