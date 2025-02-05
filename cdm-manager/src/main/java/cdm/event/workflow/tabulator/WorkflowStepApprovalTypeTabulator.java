package cdm.event.workflow.tabulator;

import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.workflow.WorkflowStepApproval;
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


@ImplementedBy(WorkflowStepApprovalTypeTabulator.Impl.class)
public interface WorkflowStepApprovalTypeTabulator extends Tabulator<WorkflowStepApproval> {
	@Singleton
	class Impl implements WorkflowStepApprovalTypeTabulator {
		private final Field approvedField;
		private final Field partyField;
		private final Field rejectedReasonField;
		private final Field timestampField;

		private final PartyTypeTabulator partyTypeTabulator;
		private final EventTimestampTypeTabulator eventTimestampTypeTabulator;

		@Inject
		public Impl(PartyTypeTabulator partyTypeTabulator, EventTimestampTypeTabulator eventTimestampTypeTabulator) {
			this.partyTypeTabulator = partyTypeTabulator;
			this.eventTimestampTypeTabulator = eventTimestampTypeTabulator;
			this.approvedField = new FieldImpl(
				"approved",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyField = new FieldImpl(
				"party",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rejectedReasonField = new FieldImpl(
				"rejectedReason",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.timestampField = new FieldImpl(
				"timestamp",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(WorkflowStepApproval input) {
			FieldValue approved = new FieldValueImpl(approvedField, Optional.ofNullable(input.getApproved()));
			FieldValue party = Optional.ofNullable(input.getParty())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(partyField, Optional.of(partyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(partyField, Optional.empty()));
			FieldValue rejectedReason = new FieldValueImpl(rejectedReasonField, Optional.ofNullable(input.getRejectedReason()));
			FieldValue timestamp = Optional.ofNullable(input.getTimestamp())
				.map(x -> new NestedFieldValueImpl(timestampField, Optional.of(eventTimestampTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(timestampField, Optional.empty()));
			return Arrays.asList(
				approved,
				party,
				rejectedReason,
				timestamp
			);
		}
	}
}
