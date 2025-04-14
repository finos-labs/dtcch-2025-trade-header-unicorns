package cdm.observable.event.tabulator;

import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.observable.event.Observation;
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


@ImplementedBy(ObservationTypeTabulator.Impl.class)
public interface ObservationTypeTabulator extends Tabulator<Observation> {
	@Singleton
	class Impl implements ObservationTypeTabulator {
		private final Field observedValueField;
		private final Field observationIdentifierField;

		private final PriceTypeTabulator priceTypeTabulator;
		private final ObservationIdentifierTypeTabulator observationIdentifierTypeTabulator;

		@Inject
		public Impl(PriceTypeTabulator priceTypeTabulator, ObservationIdentifierTypeTabulator observationIdentifierTypeTabulator) {
			this.priceTypeTabulator = priceTypeTabulator;
			this.observationIdentifierTypeTabulator = observationIdentifierTypeTabulator;
			this.observedValueField = new FieldImpl(
				"observedValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationIdentifierField = new FieldImpl(
				"observationIdentifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Observation input) {
			FieldValue observedValue = Optional.ofNullable(input.getObservedValue())
				.map(x -> new NestedFieldValueImpl(observedValueField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observedValueField, Optional.empty()));
			FieldValue observationIdentifier = Optional.ofNullable(input.getObservationIdentifier())
				.map(x -> new NestedFieldValueImpl(observationIdentifierField, Optional.of(observationIdentifierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationIdentifierField, Optional.empty()));
			return Arrays.asList(
				observedValue,
				observationIdentifier
			);
		}
	}
}
