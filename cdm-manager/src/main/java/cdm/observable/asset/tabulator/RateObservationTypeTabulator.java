package cdm.observable.asset.tabulator;

import cdm.observable.asset.RateObservation;
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


@ImplementedBy(RateObservationTypeTabulator.Impl.class)
public interface RateObservationTypeTabulator extends Tabulator<RateObservation> {
	@Singleton
	class Impl implements RateObservationTypeTabulator {
		private final Field resetDateField;
		private final Field adjustedFixingDateField;
		private final Field observedRateField;
		private final Field treatedRateField;
		private final Field observationWeightField;
		private final Field rateReferenceField;
		private final Field forecastRateField;
		private final Field treatedForecastRateField;

		private final RateObservationTypeTabulator rateObservationTypeTabulator;

		@Inject
		public Impl(RateObservationTypeTabulator rateObservationTypeTabulator) {
			this.rateObservationTypeTabulator = rateObservationTypeTabulator;
			this.resetDateField = new FieldImpl(
				"resetDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustedFixingDateField = new FieldImpl(
				"adjustedFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observedRateField = new FieldImpl(
				"observedRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.treatedRateField = new FieldImpl(
				"treatedRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationWeightField = new FieldImpl(
				"observationWeight",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateReferenceField = new FieldImpl(
				"rateReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.forecastRateField = new FieldImpl(
				"forecastRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.treatedForecastRateField = new FieldImpl(
				"treatedForecastRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(RateObservation input) {
			FieldValue resetDate = new FieldValueImpl(resetDateField, Optional.ofNullable(input.getResetDate()));
			FieldValue adjustedFixingDate = new FieldValueImpl(adjustedFixingDateField, Optional.ofNullable(input.getAdjustedFixingDate()));
			FieldValue observedRate = new FieldValueImpl(observedRateField, Optional.ofNullable(input.getObservedRate()));
			FieldValue treatedRate = new FieldValueImpl(treatedRateField, Optional.ofNullable(input.getTreatedRate()));
			FieldValue observationWeight = new FieldValueImpl(observationWeightField, Optional.ofNullable(input.getObservationWeight()));
			FieldValue rateReference = Optional.ofNullable(input.getRateReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(rateReferenceField, Optional.of(rateObservationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(rateReferenceField, Optional.empty()));
			FieldValue forecastRate = new FieldValueImpl(forecastRateField, Optional.ofNullable(input.getForecastRate()));
			FieldValue treatedForecastRate = new FieldValueImpl(treatedForecastRateField, Optional.ofNullable(input.getTreatedForecastRate()));
			return Arrays.asList(
				resetDate,
				adjustedFixingDate,
				observedRate,
				treatedRate,
				observationWeight,
				rateReference,
				forecastRate,
				treatedForecastRate
			);
		}
	}
}
