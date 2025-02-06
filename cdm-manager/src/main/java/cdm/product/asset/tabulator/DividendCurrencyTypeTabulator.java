package cdm.product.asset.tabulator;

import cdm.product.asset.DividendCurrency;
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


@ImplementedBy(DividendCurrencyTypeTabulator.Impl.class)
public interface DividendCurrencyTypeTabulator extends Tabulator<DividendCurrency> {
	@Singleton
	class Impl implements DividendCurrencyTypeTabulator {
		private final Field currencyField;
		private final Field determinationMethodField;
		private final Field currencyReferenceField;

		public Impl() {
			this.currencyField = new FieldImpl(
				"currency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.determinationMethodField = new FieldImpl(
				"determinationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyReferenceField = new FieldImpl(
				"currencyReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendCurrency input) {
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			FieldValue determinationMethod = new FieldValueImpl(determinationMethodField, Optional.ofNullable(input.getDeterminationMethod()));
			FieldValue currencyReference = new FieldValueImpl(currencyReferenceField, Optional.ofNullable(input.getCurrencyReference())
				.map(x -> x.getValue()));
			return Arrays.asList(
				currency,
				determinationMethod,
				currencyReference
			);
		}
	}
}
