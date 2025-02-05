package cdm.product.template.tabulator;

import cdm.product.template.FxFeature;
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


@ImplementedBy(FxFeatureTypeTabulator.Impl.class)
public interface FxFeatureTypeTabulator extends Tabulator<FxFeature> {
	@Singleton
	class Impl implements FxFeatureTypeTabulator {
		private final Field referenceCurrencyField;
		private final Field compositeField;
		private final Field quantoField;
		private final Field crossCurrencyField;

		private final CompositeTypeTabulator compositeTypeTabulator;
		private final QuantoTypeTabulator quantoTypeTabulator;

		@Inject
		public Impl(CompositeTypeTabulator compositeTypeTabulator, QuantoTypeTabulator quantoTypeTabulator) {
			this.compositeTypeTabulator = compositeTypeTabulator;
			this.quantoTypeTabulator = quantoTypeTabulator;
			this.referenceCurrencyField = new FieldImpl(
				"referenceCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.compositeField = new FieldImpl(
				"composite",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantoField = new FieldImpl(
				"quanto",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.crossCurrencyField = new FieldImpl(
				"crossCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxFeature input) {
			FieldValue referenceCurrency = new FieldValueImpl(referenceCurrencyField, Optional.ofNullable(input.getReferenceCurrency())
				.map(x -> x.getValue()));
			FieldValue composite = Optional.ofNullable(input.getComposite())
				.map(x -> new NestedFieldValueImpl(compositeField, Optional.of(compositeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(compositeField, Optional.empty()));
			FieldValue quanto = Optional.ofNullable(input.getQuanto())
				.map(x -> new NestedFieldValueImpl(quantoField, Optional.of(quantoTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantoField, Optional.empty()));
			FieldValue crossCurrency = Optional.ofNullable(input.getCrossCurrency())
				.map(x -> new NestedFieldValueImpl(crossCurrencyField, Optional.of(compositeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(crossCurrencyField, Optional.empty()));
			return Arrays.asList(
				referenceCurrency,
				composite,
				quanto,
				crossCurrency
			);
		}
	}
}
