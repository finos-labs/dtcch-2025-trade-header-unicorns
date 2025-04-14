package cdm.event.workflow.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifiedListTypeTabulator;
import cdm.event.common.tabulator.InstructionTypeTabulator;
import cdm.event.workflow.EventInstruction;
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


@ImplementedBy(EventInstructionTypeTabulator.Impl.class)
public interface EventInstructionTypeTabulator extends Tabulator<EventInstruction> {
	@Singleton
	class Impl implements EventInstructionTypeTabulator {
		private final Field intentField;
		private final Field corporateActionIntentField;
		private final Field eventDateField;
		private final Field effectiveDateField;
		private final Field packageInformationField;
		private final Field instructionField;

		private final IdentifiedListTypeTabulator identifiedListTypeTabulator;
		private final InstructionTypeTabulator instructionTypeTabulator;

		@Inject
		public Impl(IdentifiedListTypeTabulator identifiedListTypeTabulator, InstructionTypeTabulator instructionTypeTabulator) {
			this.identifiedListTypeTabulator = identifiedListTypeTabulator;
			this.instructionTypeTabulator = instructionTypeTabulator;
			this.intentField = new FieldImpl(
				"intent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.corporateActionIntentField = new FieldImpl(
				"corporateActionIntent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.eventDateField = new FieldImpl(
				"eventDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.packageInformationField = new FieldImpl(
				"packageInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.instructionField = new FieldImpl(
				"instruction",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EventInstruction input) {
			FieldValue intent = new FieldValueImpl(intentField, Optional.ofNullable(input.getIntent()));
			FieldValue corporateActionIntent = new FieldValueImpl(corporateActionIntentField, Optional.ofNullable(input.getCorporateActionIntent()));
			FieldValue eventDate = new FieldValueImpl(eventDateField, Optional.ofNullable(input.getEventDate()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			FieldValue packageInformation = Optional.ofNullable(input.getPackageInformation())
				.map(x -> new NestedFieldValueImpl(packageInformationField, Optional.of(identifiedListTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(packageInformationField, Optional.empty()));
			FieldValue instruction = Optional.ofNullable(input.getInstruction())
				.map(x -> x.stream()
					.map(_x -> instructionTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(instructionField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(instructionField, Optional.empty()));
			return Arrays.asList(
				intent,
				corporateActionIntent,
				eventDate,
				effectiveDate,
				packageInformation,
				instruction
			);
		}
	}
}
