package cdm.event.workflow.tabulator;

import cdm.event.workflow.WorkflowState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(WorkflowStateTypeTabulator.Impl.class)
public interface WorkflowStateTypeTabulator extends Tabulator<WorkflowState> {
	@Singleton
	class Impl implements WorkflowStateTypeTabulator {
		private final Field workflowStatusField;
		private final Field commentField;
		private final Field partyCustomisedWorkflowField;
		private final Field warehouseIdentityField;

		private final PartyCustomisedWorkflowTypeTabulator partyCustomisedWorkflowTypeTabulator;

		@Inject
		public Impl(PartyCustomisedWorkflowTypeTabulator partyCustomisedWorkflowTypeTabulator) {
			this.partyCustomisedWorkflowTypeTabulator = partyCustomisedWorkflowTypeTabulator;
			this.workflowStatusField = new FieldImpl(
				"workflowStatus",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commentField = new FieldImpl(
				"comment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyCustomisedWorkflowField = new FieldImpl(
				"partyCustomisedWorkflow",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.warehouseIdentityField = new FieldImpl(
				"warehouseIdentity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(WorkflowState input) {
			FieldValue workflowStatus = new FieldValueImpl(workflowStatusField, Optional.ofNullable(input.getWorkflowStatus()));
			FieldValue comment = new FieldValueImpl(commentField, Optional.ofNullable(input.getComment()));
			FieldValue partyCustomisedWorkflow = Optional.ofNullable(input.getPartyCustomisedWorkflow())
				.map(x -> x.stream()
					.map(_x -> partyCustomisedWorkflowTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyCustomisedWorkflowField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyCustomisedWorkflowField, Optional.empty()));
			FieldValue warehouseIdentity = new FieldValueImpl(warehouseIdentityField, Optional.ofNullable(input.getWarehouseIdentity()));
			return Arrays.asList(
				workflowStatus,
				comment,
				partyCustomisedWorkflow,
				warehouseIdentity
			);
		}
	}
}
