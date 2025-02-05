package cdm.product.collateral.tabulator;

import cdm.product.collateral.IssuerAgencyRating;
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


@ImplementedBy(IssuerAgencyRatingTypeTabulator.Impl.class)
public interface IssuerAgencyRatingTypeTabulator extends Tabulator<IssuerAgencyRating> {
	@Singleton
	class Impl implements IssuerAgencyRatingTypeTabulator {
		private final Field issuerAgencyRatingField;

		private final AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator;

		@Inject
		public Impl(AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator) {
			this.agencyRatingCriteriaTypeTabulator = agencyRatingCriteriaTypeTabulator;
			this.issuerAgencyRatingField = new FieldImpl(
				"issuerAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IssuerAgencyRating input) {
			FieldValue issuerAgencyRating = Optional.ofNullable(input.getIssuerAgencyRating())
				.map(x -> new NestedFieldValueImpl(issuerAgencyRatingField, Optional.of(agencyRatingCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(issuerAgencyRatingField, Optional.empty()));
			return Arrays.asList(
				issuerAgencyRating
			);
		}
	}
}
