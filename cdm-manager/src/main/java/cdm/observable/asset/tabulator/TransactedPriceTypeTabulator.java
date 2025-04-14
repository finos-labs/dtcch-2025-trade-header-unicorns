package cdm.observable.asset.tabulator;

import cdm.observable.asset.TransactedPrice;
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


@ImplementedBy(TransactedPriceTypeTabulator.Impl.class)
public interface TransactedPriceTypeTabulator extends Tabulator<TransactedPrice> {
	@Singleton
	class Impl implements TransactedPriceTypeTabulator {
		private final Field marketFixedRateField;
		private final Field initialPointsField;
		private final Field marketPriceField;
		private final Field quotationStyleField;

		public Impl() {
			this.marketFixedRateField = new FieldImpl(
				"marketFixedRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialPointsField = new FieldImpl(
				"initialPoints",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.marketPriceField = new FieldImpl(
				"marketPrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quotationStyleField = new FieldImpl(
				"quotationStyle",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TransactedPrice input) {
			FieldValue marketFixedRate = new FieldValueImpl(marketFixedRateField, Optional.ofNullable(input.getMarketFixedRate()));
			FieldValue initialPoints = new FieldValueImpl(initialPointsField, Optional.ofNullable(input.getInitialPoints()));
			FieldValue marketPrice = new FieldValueImpl(marketPriceField, Optional.ofNullable(input.getMarketPrice()));
			FieldValue quotationStyle = new FieldValueImpl(quotationStyleField, Optional.ofNullable(input.getQuotationStyle()));
			return Arrays.asList(
				marketFixedRate,
				initialPoints,
				marketPrice,
				quotationStyle
			);
		}
	}
}
