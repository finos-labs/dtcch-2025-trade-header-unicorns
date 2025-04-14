package cdm.observable.asset.tabulator;

import cdm.observable.asset.CashPrice;
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


@ImplementedBy(CashPriceTypeTabulator.Impl.class)
public interface CashPriceTypeTabulator extends Tabulator<CashPrice> {
	@Singleton
	class Impl implements CashPriceTypeTabulator {
		private final Field cashPriceTypeField;
		private final Field premiumExpressionField;
		private final Field feeTypeField;

		private final PremiumExpressionTypeTabulator premiumExpressionTypeTabulator;

		@Inject
		public Impl(PremiumExpressionTypeTabulator premiumExpressionTypeTabulator) {
			this.premiumExpressionTypeTabulator = premiumExpressionTypeTabulator;
			this.cashPriceTypeField = new FieldImpl(
				"cashPriceType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.premiumExpressionField = new FieldImpl(
				"premiumExpression",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.feeTypeField = new FieldImpl(
				"feeType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CashPrice input) {
			FieldValue cashPriceType = new FieldValueImpl(cashPriceTypeField, Optional.ofNullable(input.getCashPriceType()));
			FieldValue premiumExpression = Optional.ofNullable(input.getPremiumExpression())
				.map(x -> new NestedFieldValueImpl(premiumExpressionField, Optional.of(premiumExpressionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(premiumExpressionField, Optional.empty()));
			FieldValue feeType = new FieldValueImpl(feeTypeField, Optional.ofNullable(input.getFeeType()));
			return Arrays.asList(
				cashPriceType,
				premiumExpression,
				feeType
			);
		}
	}
}
