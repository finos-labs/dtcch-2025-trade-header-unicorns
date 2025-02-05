package cdm.product.template.tabulator;

import cdm.product.asset.tabulator.CorrelationReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.DividendReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.PriceReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.VarianceReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.VolatilityReturnTermsTypeTabulator;
import cdm.product.template.ReturnTerms;
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


@ImplementedBy(ReturnTermsTypeTabulator.Impl.class)
public interface ReturnTermsTypeTabulator extends Tabulator<ReturnTerms> {
	@Singleton
	class Impl implements ReturnTermsTypeTabulator {
		private final Field priceReturnTermsField;
		private final Field dividendReturnTermsField;
		private final Field varianceReturnTermsField;
		private final Field volatilityReturnTermsField;
		private final Field correlationReturnTermsField;

		private final PriceReturnTermsTypeTabulator priceReturnTermsTypeTabulator;
		private final DividendReturnTermsTypeTabulator dividendReturnTermsTypeTabulator;
		private final VarianceReturnTermsTypeTabulator varianceReturnTermsTypeTabulator;
		private final VolatilityReturnTermsTypeTabulator volatilityReturnTermsTypeTabulator;
		private final CorrelationReturnTermsTypeTabulator correlationReturnTermsTypeTabulator;

		@Inject
		public Impl(PriceReturnTermsTypeTabulator priceReturnTermsTypeTabulator, DividendReturnTermsTypeTabulator dividendReturnTermsTypeTabulator, VarianceReturnTermsTypeTabulator varianceReturnTermsTypeTabulator, VolatilityReturnTermsTypeTabulator volatilityReturnTermsTypeTabulator, CorrelationReturnTermsTypeTabulator correlationReturnTermsTypeTabulator) {
			this.priceReturnTermsTypeTabulator = priceReturnTermsTypeTabulator;
			this.dividendReturnTermsTypeTabulator = dividendReturnTermsTypeTabulator;
			this.varianceReturnTermsTypeTabulator = varianceReturnTermsTypeTabulator;
			this.volatilityReturnTermsTypeTabulator = volatilityReturnTermsTypeTabulator;
			this.correlationReturnTermsTypeTabulator = correlationReturnTermsTypeTabulator;
			this.priceReturnTermsField = new FieldImpl(
				"priceReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendReturnTermsField = new FieldImpl(
				"dividendReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.varianceReturnTermsField = new FieldImpl(
				"varianceReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.volatilityReturnTermsField = new FieldImpl(
				"volatilityReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.correlationReturnTermsField = new FieldImpl(
				"correlationReturnTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReturnTerms input) {
			FieldValue priceReturnTerms = Optional.ofNullable(input.getPriceReturnTerms())
				.map(x -> new NestedFieldValueImpl(priceReturnTermsField, Optional.of(priceReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceReturnTermsField, Optional.empty()));
			FieldValue dividendReturnTerms = Optional.ofNullable(input.getDividendReturnTerms())
				.map(x -> new NestedFieldValueImpl(dividendReturnTermsField, Optional.of(dividendReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendReturnTermsField, Optional.empty()));
			FieldValue varianceReturnTerms = Optional.ofNullable(input.getVarianceReturnTerms())
				.map(x -> new NestedFieldValueImpl(varianceReturnTermsField, Optional.of(varianceReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(varianceReturnTermsField, Optional.empty()));
			FieldValue volatilityReturnTerms = Optional.ofNullable(input.getVolatilityReturnTerms())
				.map(x -> new NestedFieldValueImpl(volatilityReturnTermsField, Optional.of(volatilityReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(volatilityReturnTermsField, Optional.empty()));
			FieldValue correlationReturnTerms = Optional.ofNullable(input.getCorrelationReturnTerms())
				.map(x -> new NestedFieldValueImpl(correlationReturnTermsField, Optional.of(correlationReturnTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(correlationReturnTermsField, Optional.empty()));
			return Arrays.asList(
				priceReturnTerms,
				dividendReturnTerms,
				varianceReturnTerms,
				volatilityReturnTerms,
				correlationReturnTerms
			);
		}
	}
}
