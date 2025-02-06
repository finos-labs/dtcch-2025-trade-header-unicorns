package cdm.observable.event.tabulator;

import cdm.base.datetime.tabulator.TimeZoneTypeTabulator;
import cdm.observable.asset.tabulator.InformationSourceTypeTabulator;
import cdm.observable.asset.tabulator.ObservableTypeTabulator;
import cdm.observable.event.ObservationIdentifier;
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


@ImplementedBy(ObservationIdentifierTypeTabulator.Impl.class)
public interface ObservationIdentifierTypeTabulator extends Tabulator<ObservationIdentifier> {
	@Singleton
	class Impl implements ObservationIdentifierTypeTabulator {
		private final Field observableField;
		private final Field observationDateField;
		private final Field observationTimeField;
		private final Field informationSourceField;
		private final Field determinationMethodologyField;

		private final ObservableTypeTabulator observableTypeTabulator;
		private final TimeZoneTypeTabulator timeZoneTypeTabulator;
		private final InformationSourceTypeTabulator informationSourceTypeTabulator;
		private final DeterminationMethodologyTypeTabulator determinationMethodologyTypeTabulator;

		@Inject
		public Impl(ObservableTypeTabulator observableTypeTabulator, TimeZoneTypeTabulator timeZoneTypeTabulator, InformationSourceTypeTabulator informationSourceTypeTabulator, DeterminationMethodologyTypeTabulator determinationMethodologyTypeTabulator) {
			this.observableTypeTabulator = observableTypeTabulator;
			this.timeZoneTypeTabulator = timeZoneTypeTabulator;
			this.informationSourceTypeTabulator = informationSourceTypeTabulator;
			this.determinationMethodologyTypeTabulator = determinationMethodologyTypeTabulator;
			this.observableField = new FieldImpl(
				"observable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationDateField = new FieldImpl(
				"observationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observationTimeField = new FieldImpl(
				"observationTime",
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
			this.determinationMethodologyField = new FieldImpl(
				"determinationMethodology",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationIdentifier input) {
			FieldValue observable = Optional.ofNullable(input.getObservable())
				.map(x -> new NestedFieldValueImpl(observableField, Optional.of(observableTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observableField, Optional.empty()));
			FieldValue observationDate = new FieldValueImpl(observationDateField, Optional.ofNullable(input.getObservationDate()));
			FieldValue observationTime = Optional.ofNullable(input.getObservationTime())
				.map(x -> new NestedFieldValueImpl(observationTimeField, Optional.of(timeZoneTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observationTimeField, Optional.empty()));
			FieldValue informationSource = Optional.ofNullable(input.getInformationSource())
				.map(x -> new NestedFieldValueImpl(informationSourceField, Optional.of(informationSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(informationSourceField, Optional.empty()));
			FieldValue determinationMethodology = Optional.ofNullable(input.getDeterminationMethodology())
				.map(x -> new NestedFieldValueImpl(determinationMethodologyField, Optional.of(determinationMethodologyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(determinationMethodologyField, Optional.empty()));
			return Arrays.asList(
				observable,
				observationDate,
				observationTime,
				informationSource,
				determinationMethodology
			);
		}
	}
}
