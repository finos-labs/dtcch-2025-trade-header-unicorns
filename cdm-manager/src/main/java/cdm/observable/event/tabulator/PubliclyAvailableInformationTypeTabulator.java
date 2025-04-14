package cdm.observable.event.tabulator;

import cdm.observable.event.PubliclyAvailableInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(PubliclyAvailableInformationTypeTabulator.Impl.class)
public interface PubliclyAvailableInformationTypeTabulator extends Tabulator<PubliclyAvailableInformation> {
	@Singleton
	class Impl implements PubliclyAvailableInformationTypeTabulator {
		private final Field standardPublicSourcesField;
		private final Field publicSourceField;
		private final Field specifiedNumberField;

		public Impl() {
			this.standardPublicSourcesField = new FieldImpl(
				"standardPublicSources",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publicSourceField = new FieldImpl(
				"publicSource",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.specifiedNumberField = new FieldImpl(
				"specifiedNumber",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PubliclyAvailableInformation input) {
			FieldValue standardPublicSources = new FieldValueImpl(standardPublicSourcesField, Optional.ofNullable(input.getStandardPublicSources()));
			FieldValue publicSource = new FieldValueImpl(publicSourceField, Optional.ofNullable(input.getPublicSource()));
			FieldValue specifiedNumber = new FieldValueImpl(specifiedNumberField, Optional.ofNullable(input.getSpecifiedNumber()));
			return Arrays.asList(
				standardPublicSources,
				publicSource,
				specifiedNumber
			);
		}
	}
}
