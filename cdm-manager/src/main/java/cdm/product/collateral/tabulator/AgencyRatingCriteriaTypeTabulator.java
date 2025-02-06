package cdm.product.collateral.tabulator;

import cdm.observable.asset.tabulator.CreditNotationTypeTabulator;
import cdm.product.collateral.AgencyRatingCriteria;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AgencyRatingCriteriaTypeTabulator.Impl.class)
public interface AgencyRatingCriteriaTypeTabulator extends Tabulator<AgencyRatingCriteria> {
	@Singleton
	class Impl implements AgencyRatingCriteriaTypeTabulator {
		private final Field creditNotationField;
		private final Field mismatchResolutionField;
		private final Field referenceAgencyField;
		private final Field boundaryField;

		private final CreditNotationTypeTabulator creditNotationTypeTabulator;

		@Inject
		public Impl(CreditNotationTypeTabulator creditNotationTypeTabulator) {
			this.creditNotationTypeTabulator = creditNotationTypeTabulator;
			this.creditNotationField = new FieldImpl(
				"creditNotation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mismatchResolutionField = new FieldImpl(
				"mismatchResolution",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceAgencyField = new FieldImpl(
				"referenceAgency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.boundaryField = new FieldImpl(
				"boundary",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AgencyRatingCriteria input) {
			FieldValue creditNotation = Optional.ofNullable(input.getCreditNotation())
				.map(x -> new NestedFieldValueImpl(creditNotationField, Optional.of(creditNotationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditNotationField, Optional.empty()));
			FieldValue mismatchResolution = new FieldValueImpl(mismatchResolutionField, Optional.ofNullable(input.getMismatchResolution()));
			FieldValue referenceAgency = new FieldValueImpl(referenceAgencyField, Optional.ofNullable(input.getReferenceAgency()));
			FieldValue boundary = new FieldValueImpl(boundaryField, Optional.ofNullable(input.getBoundary()));
			return Arrays.asList(
				creditNotation,
				mismatchResolution,
				referenceAgency,
				boundary
			);
		}
	}
}
