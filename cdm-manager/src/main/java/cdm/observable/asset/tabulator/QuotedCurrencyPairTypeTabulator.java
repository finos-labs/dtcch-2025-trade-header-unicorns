package cdm.observable.asset.tabulator;

import cdm.observable.asset.QuotedCurrencyPair;
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


@ImplementedBy(QuotedCurrencyPairTypeTabulator.Impl.class)
public interface QuotedCurrencyPairTypeTabulator extends Tabulator<QuotedCurrencyPair> {
	@Singleton
	class Impl implements QuotedCurrencyPairTypeTabulator {
		private final Field currency1Field;
		private final Field currency2Field;
		private final Field quoteBasisField;

		public Impl() {
			this.currency1Field = new FieldImpl(
				"currency1",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currency2Field = new FieldImpl(
				"currency2",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quoteBasisField = new FieldImpl(
				"quoteBasis",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(QuotedCurrencyPair input) {
			FieldValue currency1 = new FieldValueImpl(currency1Field, Optional.ofNullable(input.getCurrency1())
				.map(x -> x.getValue()));
			FieldValue currency2 = new FieldValueImpl(currency2Field, Optional.ofNullable(input.getCurrency2())
				.map(x -> x.getValue()));
			FieldValue quoteBasis = new FieldValueImpl(quoteBasisField, Optional.ofNullable(input.getQuoteBasis()));
			return Arrays.asList(
				currency1,
				currency2,
				quoteBasis
			);
		}
	}
}
