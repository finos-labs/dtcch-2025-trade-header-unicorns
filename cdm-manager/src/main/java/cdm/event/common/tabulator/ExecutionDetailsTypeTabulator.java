package cdm.event.common.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifiedListTypeTabulator;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.event.common.ExecutionDetails;
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


@ImplementedBy(ExecutionDetailsTypeTabulator.Impl.class)
public interface ExecutionDetailsTypeTabulator extends Tabulator<ExecutionDetails> {
	@Singleton
	class Impl implements ExecutionDetailsTypeTabulator {
		private final Field executionTypeField;
		private final Field executionVenueField;
		private final Field packageReferenceField;

		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final IdentifiedListTypeTabulator identifiedListTypeTabulator;

		@Inject
		public Impl(LegalEntityTypeTabulator legalEntityTypeTabulator, IdentifiedListTypeTabulator identifiedListTypeTabulator) {
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.identifiedListTypeTabulator = identifiedListTypeTabulator;
			this.executionTypeField = new FieldImpl(
				"executionType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.executionVenueField = new FieldImpl(
				"executionVenue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.packageReferenceField = new FieldImpl(
				"packageReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExecutionDetails input) {
			FieldValue executionType = new FieldValueImpl(executionTypeField, Optional.ofNullable(input.getExecutionType()));
			FieldValue executionVenue = Optional.ofNullable(input.getExecutionVenue())
				.map(x -> new NestedFieldValueImpl(executionVenueField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(executionVenueField, Optional.empty()));
			FieldValue packageReference = Optional.ofNullable(input.getPackageReference())
				.map(x -> new NestedFieldValueImpl(packageReferenceField, Optional.of(identifiedListTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(packageReferenceField, Optional.empty()));
			return Arrays.asList(
				executionType,
				executionVenue,
				packageReference
			);
		}
	}
}
