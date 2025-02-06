package cdm.observable.asset.tabulator;

import cdm.observable.asset.FxRate;
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


@ImplementedBy(FxRateTypeTabulator.Impl.class)
public interface FxRateTypeTabulator extends Tabulator<FxRate> {
	@Singleton
	class Impl implements FxRateTypeTabulator {
		private final Field quotedCurrencyPairField;
		private final Field rateField;

		private final QuotedCurrencyPairTypeTabulator quotedCurrencyPairTypeTabulator;

		@Inject
		public Impl(QuotedCurrencyPairTypeTabulator quotedCurrencyPairTypeTabulator) {
			this.quotedCurrencyPairTypeTabulator = quotedCurrencyPairTypeTabulator;
			this.quotedCurrencyPairField = new FieldImpl(
				"quotedCurrencyPair",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateField = new FieldImpl(
				"rate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxRate input) {
			FieldValue quotedCurrencyPair = Optional.ofNullable(input.getQuotedCurrencyPair())
				.map(x -> new NestedFieldValueImpl(quotedCurrencyPairField, Optional.of(quotedCurrencyPairTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quotedCurrencyPairField, Optional.empty()));
			FieldValue rate = new FieldValueImpl(rateField, Optional.ofNullable(input.getRate()));
			return Arrays.asList(
				quotedCurrencyPair,
				rate
			);
		}
	}
}
