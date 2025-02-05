package cdm.product.common.settlement.tabulator;

import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.product.common.settlement.PercentageRule;
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


@ImplementedBy(PercentageRuleTypeTabulator.Impl.class)
public interface PercentageRuleTypeTabulator extends Tabulator<PercentageRule> {
	@Singleton
	class Impl implements PercentageRuleTypeTabulator {
		private final Field paymentPercentField;
		private final Field notionalAmountReferenceField;

		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.paymentPercentField = new FieldImpl(
				"paymentPercent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notionalAmountReferenceField = new FieldImpl(
				"notionalAmountReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PercentageRule input) {
			FieldValue paymentPercent = new FieldValueImpl(paymentPercentField, Optional.ofNullable(input.getPaymentPercent()));
			FieldValue notionalAmountReference = Optional.ofNullable(input.getNotionalAmountReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(notionalAmountReferenceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(notionalAmountReferenceField, Optional.empty()));
			return Arrays.asList(
				paymentPercent,
				notionalAmountReference
			);
		}
	}
}
