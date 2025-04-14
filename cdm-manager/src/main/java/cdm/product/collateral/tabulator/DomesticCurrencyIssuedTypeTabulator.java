package cdm.product.collateral.tabulator;

import cdm.product.collateral.DomesticCurrencyIssued;
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


@ImplementedBy(DomesticCurrencyIssuedTypeTabulator.Impl.class)
public interface DomesticCurrencyIssuedTypeTabulator extends Tabulator<DomesticCurrencyIssued> {
	@Singleton
	class Impl implements DomesticCurrencyIssuedTypeTabulator {
		private final Field domesticCurrencyIssuedField;

		public Impl() {
			this.domesticCurrencyIssuedField = new FieldImpl(
				"domesticCurrencyIssued",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DomesticCurrencyIssued input) {
			FieldValue domesticCurrencyIssued = new FieldValueImpl(domesticCurrencyIssuedField, Optional.ofNullable(input.getDomesticCurrencyIssued()));
			return Arrays.asList(
				domesticCurrencyIssued
			);
		}
	}
}
