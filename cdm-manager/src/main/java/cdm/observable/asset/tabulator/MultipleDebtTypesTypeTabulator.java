package cdm.observable.asset.tabulator;

import cdm.observable.asset.MultipleDebtTypes;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;


@ImplementedBy(MultipleDebtTypesTypeTabulator.Impl.class)
public interface MultipleDebtTypesTypeTabulator extends Tabulator<MultipleDebtTypes> {
	@Singleton
	class Impl implements MultipleDebtTypesTypeTabulator {
		private final Field conditionField;
		private final Field debtTypeField;

		public Impl() {
			this.conditionField = new FieldImpl(
				"condition",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtTypeField = new FieldImpl(
				"debtType",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MultipleDebtTypes input) {
			FieldValue condition = new FieldValueImpl(conditionField, Optional.ofNullable(input.getCondition()));
			FieldValue debtType = new FieldValueImpl(debtTypeField, Optional.ofNullable(input.getDebtType())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				condition,
				debtType
			);
		}
	}
}
