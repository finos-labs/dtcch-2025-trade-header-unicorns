package cdm.observable.asset.tabulator;

import cdm.observable.asset.PremiumExpression;
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


@ImplementedBy(PremiumExpressionTypeTabulator.Impl.class)
public interface PremiumExpressionTypeTabulator extends Tabulator<PremiumExpression> {
	@Singleton
	class Impl implements PremiumExpressionTypeTabulator {
		private final Field premiumTypeField;
		private final Field pricePerOptionField;
		private final Field percentageOfNotionalField;

		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.premiumTypeField = new FieldImpl(
				"premiumType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.pricePerOptionField = new FieldImpl(
				"pricePerOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.percentageOfNotionalField = new FieldImpl(
				"percentageOfNotional",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PremiumExpression input) {
			FieldValue premiumType = new FieldValueImpl(premiumTypeField, Optional.ofNullable(input.getPremiumType()));
			FieldValue pricePerOption = Optional.ofNullable(input.getPricePerOption())
				.map(x -> new NestedFieldValueImpl(pricePerOptionField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(pricePerOptionField, Optional.empty()));
			FieldValue percentageOfNotional = new FieldValueImpl(percentageOfNotionalField, Optional.ofNullable(input.getPercentageOfNotional()));
			return Arrays.asList(
				premiumType,
				pricePerOption,
				percentageOfNotional
			);
		}
	}
}
