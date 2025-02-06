package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.PaymentRule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PaymentRuleTypeTabulator.Impl.class)
public interface PaymentRuleTypeTabulator extends Tabulator<PaymentRule> {
	@Singleton
	class Impl implements PaymentRuleTypeTabulator {
		private final Field percentageRuleField;

		private final PercentageRuleTypeTabulator percentageRuleTypeTabulator;

		@Inject
		public Impl(PercentageRuleTypeTabulator percentageRuleTypeTabulator) {
			this.percentageRuleTypeTabulator = percentageRuleTypeTabulator;
			this.percentageRuleField = new FieldImpl(
				"percentageRule",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PaymentRule input) {
			FieldValue percentageRule = Optional.ofNullable(input.getPercentageRule())
				.map(x -> new NestedFieldValueImpl(percentageRuleField, Optional.of(percentageRuleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(percentageRuleField, Optional.empty()));
			return Arrays.asList(
				percentageRule
			);
		}
	}
}
