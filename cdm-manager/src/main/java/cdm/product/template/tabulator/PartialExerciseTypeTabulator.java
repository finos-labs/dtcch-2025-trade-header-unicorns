package cdm.product.template.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.template.PartialExercise;
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


@ImplementedBy(PartialExerciseTypeTabulator.Impl.class)
public interface PartialExerciseTypeTabulator extends Tabulator<PartialExercise> {
	@Singleton
	class Impl implements PartialExerciseTypeTabulator {
		private final Field notionaReferenceField;
		private final Field integralMultipleAmountField;
		private final Field minimumNotionalAmountField;
		private final Field minimumNumberOfOptionsField;

		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.notionaReferenceField = new FieldImpl(
				"notionaReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.integralMultipleAmountField = new FieldImpl(
				"integralMultipleAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.minimumNotionalAmountField = new FieldImpl(
				"minimumNotionalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.minimumNumberOfOptionsField = new FieldImpl(
				"minimumNumberOfOptions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PartialExercise input) {
			FieldValue notionaReference = Optional.ofNullable(input.getNotionaReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(notionaReferenceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notionaReferenceField, Optional.empty()));
			FieldValue integralMultipleAmount = new FieldValueImpl(integralMultipleAmountField, Optional.ofNullable(input.getIntegralMultipleAmount()));
			FieldValue minimumNotionalAmount = new FieldValueImpl(minimumNotionalAmountField, Optional.ofNullable(input.getMinimumNotionalAmount()));
			FieldValue minimumNumberOfOptions = new FieldValueImpl(minimumNumberOfOptionsField, Optional.ofNullable(input.getMinimumNumberOfOptions()));
			return Arrays.asList(
				notionaReference,
				integralMultipleAmount,
				minimumNotionalAmount,
				minimumNumberOfOptions
			);
		}
	}
}
