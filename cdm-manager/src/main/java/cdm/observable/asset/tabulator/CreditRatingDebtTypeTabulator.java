package cdm.observable.asset.tabulator;

import cdm.observable.asset.CreditRatingDebt;
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


@ImplementedBy(CreditRatingDebtTypeTabulator.Impl.class)
public interface CreditRatingDebtTypeTabulator extends Tabulator<CreditRatingDebt> {
	@Singleton
	class Impl implements CreditRatingDebtTypeTabulator {
		private final Field debtTypeField;
		private final Field debtTypesField;

		private final MultipleDebtTypesTypeTabulator multipleDebtTypesTypeTabulator;

		@Inject
		public Impl(MultipleDebtTypesTypeTabulator multipleDebtTypesTypeTabulator) {
			this.multipleDebtTypesTypeTabulator = multipleDebtTypesTypeTabulator;
			this.debtTypeField = new FieldImpl(
				"debtType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtTypesField = new FieldImpl(
				"debtTypes",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditRatingDebt input) {
			FieldValue debtType = new FieldValueImpl(debtTypeField, Optional.ofNullable(input.getDebtType())
				.map(x -> x.getValue()));
			FieldValue debtTypes = Optional.ofNullable(input.getDebtTypes())
				.map(x -> new NestedFieldValueImpl(debtTypesField, Optional.of(multipleDebtTypesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(debtTypesField, Optional.empty()));
			return Arrays.asList(
				debtType,
				debtTypes
			);
		}
	}
}
