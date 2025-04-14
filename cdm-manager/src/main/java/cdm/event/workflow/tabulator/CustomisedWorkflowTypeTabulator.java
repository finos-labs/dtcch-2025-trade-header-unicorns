package cdm.event.workflow.tabulator;

import cdm.event.workflow.CustomisedWorkflow;
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


@ImplementedBy(CustomisedWorkflowTypeTabulator.Impl.class)
public interface CustomisedWorkflowTypeTabulator extends Tabulator<CustomisedWorkflow> {
	@Singleton
	class Impl implements CustomisedWorkflowTypeTabulator {
		private final Field itemNameField;
		private final Field itemValueField;

		public Impl() {
			this.itemNameField = new FieldImpl(
				"itemName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.itemValueField = new FieldImpl(
				"itemValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CustomisedWorkflow input) {
			FieldValue itemName = new FieldValueImpl(itemNameField, Optional.ofNullable(input.getItemName()));
			FieldValue itemValue = new FieldValueImpl(itemValueField, Optional.ofNullable(input.getItemValue()));
			return Arrays.asList(
				itemName,
				itemValue
			);
		}
	}
}
