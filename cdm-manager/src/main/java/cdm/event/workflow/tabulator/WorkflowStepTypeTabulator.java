package cdm.event.workflow.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.base.staticdata.party.tabulator.AccountTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyTypeTabulator;
import cdm.event.common.tabulator.BusinessEventTypeTabulator;
import cdm.event.common.tabulator.CounterpartyPositionBusinessEventTypeTabulator;
import cdm.event.common.tabulator.LineageTypeTabulator;
import cdm.event.workflow.WorkflowStep;
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


@ImplementedBy(WorkflowStepTypeTabulator.Impl.class)
public interface WorkflowStepTypeTabulator extends Tabulator<WorkflowStep> {
	@Singleton
	class Impl implements WorkflowStepTypeTabulator {
		private final Field businessEventField;
		private final Field counterpartyPositionBusinessEventField;
		private final Field proposedEventField;
		private final Field rejectedField;
		private final Field approvalField;
		private final Field previousWorkflowStepField;
		private final Field nextEventField;
		private final Field messageInformationField;
		private final Field timestampField;
		private final Field eventIdentifierField;
		private final Field actionField;
		private final Field partyField;
		private final Field accountField;
		private final Field lineageField;
		private final Field creditLimitInformationField;
		private final Field workflowStateField;

		private final BusinessEventTypeTabulator businessEventTypeTabulator;
		private final CounterpartyPositionBusinessEventTypeTabulator counterpartyPositionBusinessEventTypeTabulator;
		private final EventInstructionTypeTabulator eventInstructionTypeTabulator;
		private final WorkflowStepApprovalTypeTabulator workflowStepApprovalTypeTabulator;
		private final WorkflowStepTypeTabulator workflowStepTypeTabulator;
		private final MessageInformationTypeTabulator messageInformationTypeTabulator;
		private final EventTimestampTypeTabulator eventTimestampTypeTabulator;
		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final PartyTypeTabulator partyTypeTabulator;
		private final AccountTypeTabulator accountTypeTabulator;
		private final LineageTypeTabulator lineageTypeTabulator;
		private final CreditLimitInformationTypeTabulator creditLimitInformationTypeTabulator;
		private final WorkflowStateTypeTabulator workflowStateTypeTabulator;

		@Inject
		public Impl(BusinessEventTypeTabulator businessEventTypeTabulator, CounterpartyPositionBusinessEventTypeTabulator counterpartyPositionBusinessEventTypeTabulator, EventInstructionTypeTabulator eventInstructionTypeTabulator, WorkflowStepApprovalTypeTabulator workflowStepApprovalTypeTabulator, WorkflowStepTypeTabulator workflowStepTypeTabulator, MessageInformationTypeTabulator messageInformationTypeTabulator, EventTimestampTypeTabulator eventTimestampTypeTabulator, IdentifierTypeTabulator identifierTypeTabulator, PartyTypeTabulator partyTypeTabulator, AccountTypeTabulator accountTypeTabulator, LineageTypeTabulator lineageTypeTabulator, CreditLimitInformationTypeTabulator creditLimitInformationTypeTabulator, WorkflowStateTypeTabulator workflowStateTypeTabulator) {
			this.businessEventTypeTabulator = businessEventTypeTabulator;
			this.counterpartyPositionBusinessEventTypeTabulator = counterpartyPositionBusinessEventTypeTabulator;
			this.eventInstructionTypeTabulator = eventInstructionTypeTabulator;
			this.workflowStepApprovalTypeTabulator = workflowStepApprovalTypeTabulator;
			this.workflowStepTypeTabulator = workflowStepTypeTabulator;
			this.messageInformationTypeTabulator = messageInformationTypeTabulator;
			this.eventTimestampTypeTabulator = eventTimestampTypeTabulator;
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.partyTypeTabulator = partyTypeTabulator;
			this.accountTypeTabulator = accountTypeTabulator;
			this.lineageTypeTabulator = lineageTypeTabulator;
			this.creditLimitInformationTypeTabulator = creditLimitInformationTypeTabulator;
			this.workflowStateTypeTabulator = workflowStateTypeTabulator;
			this.businessEventField = new FieldImpl(
				"businessEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.counterpartyPositionBusinessEventField = new FieldImpl(
				"counterpartyPositionBusinessEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.proposedEventField = new FieldImpl(
				"proposedEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rejectedField = new FieldImpl(
				"rejected",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.approvalField = new FieldImpl(
				"approval",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.previousWorkflowStepField = new FieldImpl(
				"previousWorkflowStep",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nextEventField = new FieldImpl(
				"nextEvent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.messageInformationField = new FieldImpl(
				"messageInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.timestampField = new FieldImpl(
				"timestamp",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.eventIdentifierField = new FieldImpl(
				"eventIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.actionField = new FieldImpl(
				"action",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.partyField = new FieldImpl(
				"party",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accountField = new FieldImpl(
				"account",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lineageField = new FieldImpl(
				"lineage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditLimitInformationField = new FieldImpl(
				"creditLimitInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.workflowStateField = new FieldImpl(
				"workflowState",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(WorkflowStep input) {
			FieldValue businessEvent = Optional.ofNullable(input.getBusinessEvent())
				.map(x -> new NestedFieldValueImpl(businessEventField, Optional.of(businessEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessEventField, Optional.empty()));
			FieldValue counterpartyPositionBusinessEvent = Optional.ofNullable(input.getCounterpartyPositionBusinessEvent())
				.map(x -> new NestedFieldValueImpl(counterpartyPositionBusinessEventField, Optional.of(counterpartyPositionBusinessEventTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(counterpartyPositionBusinessEventField, Optional.empty()));
			FieldValue proposedEvent = Optional.ofNullable(input.getProposedEvent())
				.map(x -> new NestedFieldValueImpl(proposedEventField, Optional.of(eventInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(proposedEventField, Optional.empty()));
			FieldValue rejected = new FieldValueImpl(rejectedField, Optional.ofNullable(input.getRejected()));
			FieldValue approval = Optional.ofNullable(input.getApproval())
				.map(x -> x.stream()
					.map(_x -> workflowStepApprovalTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(approvalField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(approvalField, Optional.empty()));
			FieldValue previousWorkflowStep = Optional.ofNullable(input.getPreviousWorkflowStep())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(previousWorkflowStepField, Optional.of(workflowStepTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(previousWorkflowStepField, Optional.empty()));
			FieldValue nextEvent = Optional.ofNullable(input.getNextEvent())
				.map(x -> new NestedFieldValueImpl(nextEventField, Optional.of(eventInstructionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(nextEventField, Optional.empty()));
			FieldValue messageInformation = Optional.ofNullable(input.getMessageInformation())
				.map(x -> new NestedFieldValueImpl(messageInformationField, Optional.of(messageInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(messageInformationField, Optional.empty()));
			FieldValue timestamp = Optional.ofNullable(input.getTimestamp())
				.map(x -> x.stream()
					.map(_x -> eventTimestampTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(timestampField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(timestampField, Optional.empty()));
			FieldValue eventIdentifier = Optional.ofNullable(input.getEventIdentifier())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(eventIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(eventIdentifierField, Optional.empty()));
			FieldValue action = new FieldValueImpl(actionField, Optional.ofNullable(input.getAction()));
			FieldValue party = Optional.ofNullable(input.getParty())
				.map(x -> x.stream()
					.map(_x -> partyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(partyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(partyField, Optional.empty()));
			FieldValue account = Optional.ofNullable(input.getAccount())
				.map(x -> x.stream()
					.map(_x -> accountTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(accountField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(accountField, Optional.empty()));
			FieldValue lineage = Optional.ofNullable(input.getLineage())
				.map(x -> new NestedFieldValueImpl(lineageField, Optional.of(lineageTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lineageField, Optional.empty()));
			FieldValue creditLimitInformation = Optional.ofNullable(input.getCreditLimitInformation())
				.map(x -> new NestedFieldValueImpl(creditLimitInformationField, Optional.of(creditLimitInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(creditLimitInformationField, Optional.empty()));
			FieldValue workflowState = Optional.ofNullable(input.getWorkflowState())
				.map(x -> new NestedFieldValueImpl(workflowStateField, Optional.of(workflowStateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(workflowStateField, Optional.empty()));
			return Arrays.asList(
				businessEvent,
				counterpartyPositionBusinessEvent,
				proposedEvent,
				rejected,
				approval,
				previousWorkflowStep,
				nextEvent,
				messageInformation,
				timestamp,
				eventIdentifier,
				action,
				party,
				account,
				lineage,
				creditLimitInformation,
				workflowState
			);
		}
	}
}
