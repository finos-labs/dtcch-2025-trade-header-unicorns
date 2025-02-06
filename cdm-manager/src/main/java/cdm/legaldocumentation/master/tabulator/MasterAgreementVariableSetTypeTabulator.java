package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.MasterAgreementVariableSet;
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


@ImplementedBy(MasterAgreementVariableSetTypeTabulator.Impl.class)
public interface MasterAgreementVariableSetTypeTabulator extends Tabulator<MasterAgreementVariableSet> {
	@Singleton
	class Impl implements MasterAgreementVariableSetTypeTabulator {
		private final Field variableSetField;
		private final Field nameField;
		private final Field valueField;

		private final MasterAgreementVariableSetTypeTabulator masterAgreementVariableSetTypeTabulator;

		@Inject
		public Impl(MasterAgreementVariableSetTypeTabulator masterAgreementVariableSetTypeTabulator) {
			this.masterAgreementVariableSetTypeTabulator = masterAgreementVariableSetTypeTabulator;
			this.variableSetField = new FieldImpl(
				"variableSet",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valueField = new FieldImpl(
				"value",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MasterAgreementVariableSet input) {
			FieldValue variableSet = Optional.ofNullable(input.getVariableSet())
				.map(x -> x.stream()
					.map(_x -> masterAgreementVariableSetTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(variableSetField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(variableSetField, Optional.empty()));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName()));
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			return Arrays.asList(
				variableSet,
				name,
				value
			);
		}
	}
}
