package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.base.math.tabulator.RoundingTypeTabulator;
import cdm.observable.asset.tabulator.FxSpotRateSourceTypeTabulator;
import cdm.product.common.schedule.ObservationTerms;
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


@ImplementedBy(ObservationTermsTypeTabulator.Impl.class)
public interface ObservationTermsTypeTabulator extends Tabulator<ObservationTerms> {
	@Singleton
	class Impl implements ObservationTermsTypeTabulator {
		private final Field observationTimeField;
		private final Field observationTimeTypeField;
		private final Field informationSourceField;
		private final Field precisionField;
		private final Field calculationPeriodDatesField;
		private final Field observationDatesField;
		private final Field numberOfObservationDatesField;

		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;
		private final FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator;
		private final RoundingTypeTabulator roundingTypeTabulator;
		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;
		private final ObservationDatesTypeTabulator observationDatesTypeTabulator;

		@Inject
		public Impl(BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator, FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator, RoundingTypeTabulator roundingTypeTabulator, CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator, ObservationDatesTypeTabulator observationDatesTypeTabulator) {
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.fxSpotRateSourceTypeTabulator = fxSpotRateSourceTypeTabulator;
			this.roundingTypeTabulator = roundingTypeTabulator;
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.observationDatesTypeTabulator = observationDatesTypeTabulator;
			this.observationTimeField = new FieldImpl(
				"observationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationTimeTypeField = new FieldImpl(
				"observationTimeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.informationSourceField = new FieldImpl(
				"informationSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.precisionField = new FieldImpl(
				"precision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationPeriodDatesField = new FieldImpl(
				"calculationPeriodDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationDatesField = new FieldImpl(
				"observationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.numberOfObservationDatesField = new FieldImpl(
				"numberOfObservationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationTerms input) {
			FieldValue observationTime = Optional.ofNullable(input.getObservationTime())
				.map(x -> new NestedFieldValueImpl(observationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationTimeField, Optional.empty()));
			FieldValue observationTimeType = new FieldValueImpl(observationTimeTypeField, Optional.ofNullable(input.getObservationTimeType()));
			FieldValue informationSource = Optional.ofNullable(input.getInformationSource())
				.map(x -> new NestedFieldValueImpl(informationSourceField, Optional.of(fxSpotRateSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(informationSourceField, Optional.empty()));
			FieldValue precision = Optional.ofNullable(input.getPrecision())
				.map(x -> new NestedFieldValueImpl(precisionField, Optional.of(roundingTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(precisionField, Optional.empty()));
			FieldValue calculationPeriodDates = Optional.ofNullable(input.getCalculationPeriodDates())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesField, Optional.of(calculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesField, Optional.empty()));
			FieldValue observationDates = Optional.ofNullable(input.getObservationDates())
				.map(x -> new NestedFieldValueImpl(observationDatesField, Optional.of(observationDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationDatesField, Optional.empty()));
			FieldValue numberOfObservationDates = new FieldValueImpl(numberOfObservationDatesField, Optional.ofNullable(input.getNumberOfObservationDates()));
			return Arrays.asList(
				observationTime,
				observationTimeType,
				informationSource,
				precision,
				calculationPeriodDates,
				observationDates,
				numberOfObservationDates
			);
		}
	}
}
