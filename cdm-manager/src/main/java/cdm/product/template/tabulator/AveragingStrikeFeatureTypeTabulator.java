package cdm.product.template.tabulator;

import cdm.product.common.schedule.tabulator.ObservationTermsTypeTabulator;
import cdm.product.template.AveragingStrikeFeature;
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


@ImplementedBy(AveragingStrikeFeatureTypeTabulator.Impl.class)
public interface AveragingStrikeFeatureTypeTabulator extends Tabulator<AveragingStrikeFeature> {
	@Singleton
	class Impl implements AveragingStrikeFeatureTypeTabulator {
		private final Field averagingCalculationField;
		private final Field observationTermsField;

		private final AveragingCalculationTypeTabulator averagingCalculationTypeTabulator;
		private final ObservationTermsTypeTabulator observationTermsTypeTabulator;

		@Inject
		public Impl(AveragingCalculationTypeTabulator averagingCalculationTypeTabulator, ObservationTermsTypeTabulator observationTermsTypeTabulator) {
			this.averagingCalculationTypeTabulator = averagingCalculationTypeTabulator;
			this.observationTermsTypeTabulator = observationTermsTypeTabulator;
			this.averagingCalculationField = new FieldImpl(
				"averagingCalculation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationTermsField = new FieldImpl(
				"observationTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AveragingStrikeFeature input) {
			FieldValue averagingCalculation = Optional.ofNullable(input.getAveragingCalculation())
				.map(x -> new NestedFieldValueImpl(averagingCalculationField, Optional.of(averagingCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingCalculationField, Optional.empty()));
			FieldValue observationTerms = Optional.ofNullable(input.getObservationTerms())
				.map(x -> new NestedFieldValueImpl(observationTermsField, Optional.of(observationTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationTermsField, Optional.empty()));
			return Arrays.asList(
				averagingCalculation,
				observationTerms
			);
		}
	}
}
