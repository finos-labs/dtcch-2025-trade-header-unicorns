package cdm.event.workflow.tabulator;

import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.workflow.PartyCustomisedWorkflow;
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
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PartyCustomisedWorkflowTypeTabulator.Impl.class)
public interface PartyCustomisedWorkflowTypeTabulator extends Tabulator<PartyCustomisedWorkflow> {
	@Singleton
	class Impl implements PartyCustomisedWorkflowTypeTabulator {
		private final Field partyReferenceField;
		private final Field partyNameField;
		private final Field customisedWorkflowField;

		private final PartyTypeTabulator partyTypeTabulator;
		private final CustomisedWorkflowTypeTabulator customisedWorkflowTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator, CustomisedWorkflowTypeTabulator customisedWorkflowTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.customisedWorkflowTypeTabulator = customisedWorkflowTypeTabulator;
			this.partyReferenceField = new FieldImpl(
				"partyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyNameField = new FieldImpl(
				"partyName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.customisedWorkflowField = new FieldImpl(
				"customisedWorkflow",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PartyCustomisedWorkflow input) {
			FieldValue partyReference = Optional.ofNullable(input.getPartyReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(partyReferenceField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyReferenceField, Optional.empty()));
			FieldValue partyName = new FieldValueImpl(partyNameField, Optional.ofNullable(input.getPartyName()));
			FieldValue customisedWorkflow = Optional.ofNullable(input.getCustomisedWorkflow())
				.map(x -> x.stream()
					.map(_x -> customisedWorkflowTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(customisedWorkflowField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(customisedWorkflowField, Optional.empty()));
			return Arrays.asList(
				partyReference,
				partyName,
				customisedWorkflow
			);
		}
	}
}
