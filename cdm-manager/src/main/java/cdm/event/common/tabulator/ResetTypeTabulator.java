package cdm.event.common.tabulator;

import cdm.event.common.Reset;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.observable.event.tabulator.ObservationTypeTabulator;
import cdm.product.template.tabulator.AveragingCalculationTypeTabulator;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ResetTypeTabulator.Impl.class)
public interface ResetTypeTabulator extends Tabulator<Reset> {
	@Singleton
	class Impl implements ResetTypeTabulator {
		private final Field resetValueField;
		private final Field resetDateField;
		private final Field rateRecordDateField;
		private final Field observationsField;
		private final Field averagingMethodologyField;

		private final PriceTypeTabulator priceTypeTabulator;
		private final ObservationTypeTabulator observationTypeTabulator;
		private final AveragingCalculationTypeTabulator averagingCalculationTypeTabulator;

		@Inject
		public Impl(PriceTypeTabulator priceTypeTabulator, ObservationTypeTabulator observationTypeTabulator, AveragingCalculationTypeTabulator averagingCalculationTypeTabulator) {
			this.priceTypeTabulator = priceTypeTabulator;
			this.observationTypeTabulator = observationTypeTabulator;
			this.averagingCalculationTypeTabulator = averagingCalculationTypeTabulator;
			this.resetValueField = new FieldImpl(
				"resetValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetDateField = new FieldImpl(
				"resetDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateRecordDateField = new FieldImpl(
				"rateRecordDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationsField = new FieldImpl(
				"observations",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.averagingMethodologyField = new FieldImpl(
				"averagingMethodology",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Reset input) {
			FieldValue resetValue = Optional.ofNullable(input.getResetValue())
				.map(x -> new NestedFieldValueImpl(resetValueField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetValueField, Optional.empty()));
			FieldValue resetDate = new FieldValueImpl(resetDateField, Optional.ofNullable(input.getResetDate()));
			FieldValue rateRecordDate = new FieldValueImpl(rateRecordDateField, Optional.ofNullable(input.getRateRecordDate()));
			FieldValue observations = Optional.ofNullable(input.getObservations())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> observationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(observationsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(observationsField, Optional.empty()));
			FieldValue averagingMethodology = Optional.ofNullable(input.getAveragingMethodology())
				.map(x -> new NestedFieldValueImpl(averagingMethodologyField, Optional.of(averagingCalculationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(averagingMethodologyField, Optional.empty()));
			return Arrays.asList(
				resetValue,
				resetDate,
				rateRecordDate,
				observations,
				averagingMethodology
			);
		}
	}
}
