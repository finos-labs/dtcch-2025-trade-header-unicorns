package cdm.product.template.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.template.MultipleExercise;
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


@ImplementedBy(MultipleExerciseTypeTabulator.Impl.class)
public interface MultipleExerciseTypeTabulator extends Tabulator<MultipleExercise> {
	@Singleton
	class Impl implements MultipleExerciseTypeTabulator {
		private final Field notionaReferenceField;
		private final Field integralMultipleAmountField;
		private final Field minimumNotionalAmountField;
		private final Field minimumNumberOfOptionsField;
		private final Field maximumNotionalAmountField;
		private final Field maximumNumberOfOptionsField;

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
			this.maximumNotionalAmountField = new FieldImpl(
				"maximumNotionalAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maximumNumberOfOptionsField = new FieldImpl(
				"maximumNumberOfOptions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MultipleExercise input) {
			FieldValue notionaReference = Optional.ofNullable(input.getNotionaReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(notionaReferenceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notionaReferenceField, Optional.empty()));
			FieldValue integralMultipleAmount = new FieldValueImpl(integralMultipleAmountField, Optional.ofNullable(input.getIntegralMultipleAmount()));
			FieldValue minimumNotionalAmount = new FieldValueImpl(minimumNotionalAmountField, Optional.ofNullable(input.getMinimumNotionalAmount()));
			FieldValue minimumNumberOfOptions = new FieldValueImpl(minimumNumberOfOptionsField, Optional.ofNullable(input.getMinimumNumberOfOptions()));
			FieldValue maximumNotionalAmount = new FieldValueImpl(maximumNotionalAmountField, Optional.ofNullable(input.getMaximumNotionalAmount()));
			FieldValue maximumNumberOfOptions = new FieldValueImpl(maximumNumberOfOptionsField, Optional.ofNullable(input.getMaximumNumberOfOptions()));
			return Arrays.asList(
				notionaReference,
				integralMultipleAmount,
				minimumNotionalAmount,
				minimumNumberOfOptions,
				maximumNotionalAmount,
				maximumNumberOfOptions
			);
		}
	}
}
