package cdm.product.template.tabulator;

import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.observable.asset.tabulator.ObservableTypeTabulator;
import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.product.asset.tabulator.CorrelationReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.DividendReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.PriceReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.VarianceReturnTermsTypeTabulator;
import cdm.product.asset.tabulator.VolatilityReturnTermsTypeTabulator;
import cdm.product.template.PortfolioReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PortfolioReturnTermsTypeTabulator.Impl.class)
public interface PortfolioReturnTermsTypeTabulator extends Tabulator<PortfolioReturnTerms> {
	@Singleton
	class Impl implements PortfolioReturnTermsTypeTabulator {
		private final Field priceReturnTermsField;
		private final Field dividendReturnTermsField;
		private final Field varianceReturnTermsField;
		private final Field volatilityReturnTermsField;
		private final Field correlationReturnTermsField;
		private final Field payerReceiverField;
		private final Field underlierField;
		private final Field quantityField;
		private final Field initialValuationPriceField;
		private final Field interimValuationPriceField;
		private final Field finalValuationPriceField;

		private final PriceReturnTermsTypeTabulator priceReturnTermsTypeTabulator;
		private final DividendReturnTermsTypeTabulator dividendReturnTermsTypeTabulator;
		private final VarianceReturnTermsTypeTabulator varianceReturnTermsTypeTabulator;
		private final VolatilityReturnTermsTypeTabulator volatilityReturnTermsTypeTabulator;
		private final CorrelationReturnTermsTypeTabulator correlationReturnTermsTypeTabulator;
		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;
		private final ObservableTypeTabulator observableTypeTabulator;
		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;
		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(PriceReturnTermsTypeTabulator priceReturnTermsTypeTabulator, DividendReturnTermsTypeTabulator dividendReturnTermsTypeTabulator, VarianceReturnTermsTypeTabulator varianceReturnTermsTypeTabulator, VolatilityReturnTermsTypeTabulator volatilityReturnTermsTypeTabulator, CorrelationReturnTermsTypeTabulator correlationReturnTermsTypeTabulator, PayerReceiverTypeTabulator payerReceiverTypeTabulator, ObservableTypeTabulator observableTypeTabulator, NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator, PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.priceReturnTermsTypeTabulator = priceReturnTermsTypeTabulator;
			this.dividendReturnTermsTypeTabulator = dividendReturnTermsTypeTabulator;
			this.varianceReturnTermsTypeTabulator = varianceReturnTermsTypeTabulator;
			this.volatilityReturnTermsTypeTabulator = volatilityReturnTermsTypeTabulator;
			this.correlationReturnTermsTypeTabulator = correlationReturnTermsTypeTabulator;
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.observableTypeTabulator = observableTypeTabulator;
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
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
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityField = new FieldImpl(
				"quantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialValuationPriceField = new FieldImpl(
				"initialValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interimValuationPriceField = new FieldImpl(
				"interimValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalValuationPriceField = new FieldImpl(
				"finalValuationPrice",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PortfolioReturnTerms input) {
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
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(payerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(observableTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			FieldValue quantity = Optional.ofNullable(input.getQuantity())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(quantityField, Optional.of(nonNegativeQuantityScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityField, Optional.empty()));
			FieldValue initialValuationPrice = Optional.ofNullable(input.getInitialValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(initialValuationPriceField, Optional.empty()));
			FieldValue interimValuationPrice = Optional.ofNullable(input.getInterimValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(interimValuationPriceField, Optional.empty()));
			FieldValue finalValuationPrice = Optional.ofNullable(input.getFinalValuationPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(finalValuationPriceField, Optional.empty()));
			return Arrays.asList(
				priceReturnTerms,
				dividendReturnTerms,
				varianceReturnTerms,
				volatilityReturnTerms,
				correlationReturnTerms,
				payerReceiver,
				underlier,
				quantity,
				initialValuationPrice,
				interimValuationPrice,
				finalValuationPrice
			);
		}
	}
}
