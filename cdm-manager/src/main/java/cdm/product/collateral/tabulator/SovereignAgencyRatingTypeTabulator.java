package cdm.product.collateral.tabulator;

import cdm.product.collateral.SovereignAgencyRating;
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


@ImplementedBy(SovereignAgencyRatingTypeTabulator.Impl.class)
public interface SovereignAgencyRatingTypeTabulator extends Tabulator<SovereignAgencyRating> {
	@Singleton
	class Impl implements SovereignAgencyRatingTypeTabulator {
		private final Field sovereignAgencyRatingField;

		private final AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator;

		@Inject
		public Impl(AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator) {
			this.agencyRatingCriteriaTypeTabulator = agencyRatingCriteriaTypeTabulator;
			this.sovereignAgencyRatingField = new FieldImpl(
				"sovereignAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SovereignAgencyRating input) {
			FieldValue sovereignAgencyRating = Optional.ofNullable(input.getSovereignAgencyRating())
				.map(x -> new NestedFieldValueImpl(sovereignAgencyRatingField, Optional.of(agencyRatingCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(sovereignAgencyRatingField, Optional.empty()));
			return Arrays.asList(
				sovereignAgencyRating
			);
		}
	}
}
