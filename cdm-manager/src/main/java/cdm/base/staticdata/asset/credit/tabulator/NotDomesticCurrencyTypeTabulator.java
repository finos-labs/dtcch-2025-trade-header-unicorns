package cdm.base.staticdata.asset.credit.tabulator;

import cdm.base.staticdata.asset.credit.NotDomesticCurrency;
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


@ImplementedBy(NotDomesticCurrencyTypeTabulator.Impl.class)
public interface NotDomesticCurrencyTypeTabulator extends Tabulator<NotDomesticCurrency> {
	@Singleton
	class Impl implements NotDomesticCurrencyTypeTabulator {
		private final Field applicableField;
		private final Field currencyField;

		public Impl() {
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyField = new FieldImpl(
				"currency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(NotDomesticCurrency input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			return Arrays.asList(
				applicable,
				currency
			);
		}
	}
}
