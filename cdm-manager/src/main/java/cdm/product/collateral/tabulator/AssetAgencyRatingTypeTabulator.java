package cdm.product.collateral.tabulator;

import cdm.product.collateral.AssetAgencyRating;
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


@ImplementedBy(AssetAgencyRatingTypeTabulator.Impl.class)
public interface AssetAgencyRatingTypeTabulator extends Tabulator<AssetAgencyRating> {
	@Singleton
	class Impl implements AssetAgencyRatingTypeTabulator {
		private final Field assetAgencyRatingField;

		private final AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator;

		@Inject
		public Impl(AgencyRatingCriteriaTypeTabulator agencyRatingCriteriaTypeTabulator) {
			this.agencyRatingCriteriaTypeTabulator = agencyRatingCriteriaTypeTabulator;
			this.assetAgencyRatingField = new FieldImpl(
				"assetAgencyRating",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetAgencyRating input) {
			FieldValue assetAgencyRating = Optional.ofNullable(input.getAssetAgencyRating())
				.map(x -> new NestedFieldValueImpl(assetAgencyRatingField, Optional.of(agencyRatingCriteriaTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(assetAgencyRatingField, Optional.empty()));
			return Arrays.asList(
				assetAgencyRating
			);
		}
	}
}
