package cdm.event.common.tabulator;

import cdm.event.common.CollateralPosition;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.observable.asset.tabulator.PriceQuantityTypeTabulator;
import cdm.product.collateral.tabulator.CollateralTreatmentTypeTabulator;
import cdm.product.template.tabulator.ProductTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CollateralPositionTypeTabulator.Impl.class)
public interface CollateralPositionTypeTabulator extends Tabulator<CollateralPosition> {
	@Singleton
	class Impl implements CollateralPositionTypeTabulator {
		private final Field priceQuantityField;
		private final Field productField;
		private final Field cashBalanceField;
		private final Field tradeReferenceField;
		private final Field treatmentField;
		private final Field collateralPositionStatusField;

		private final PriceQuantityTypeTabulator priceQuantityTypeTabulator;
		private final ProductTypeTabulator productTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;
		private final TradeStateTypeTabulator tradeStateTypeTabulator;
		private final CollateralTreatmentTypeTabulator collateralTreatmentTypeTabulator;

		@Inject
		public Impl(PriceQuantityTypeTabulator priceQuantityTypeTabulator, ProductTypeTabulator productTypeTabulator, MoneyTypeTabulator moneyTypeTabulator, TradeStateTypeTabulator tradeStateTypeTabulator, CollateralTreatmentTypeTabulator collateralTreatmentTypeTabulator) {
			this.priceQuantityTypeTabulator = priceQuantityTypeTabulator;
			this.productTypeTabulator = productTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.tradeStateTypeTabulator = tradeStateTypeTabulator;
			this.collateralTreatmentTypeTabulator = collateralTreatmentTypeTabulator;
			this.priceQuantityField = new FieldImpl(
				"priceQuantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.productField = new FieldImpl(
				"product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashBalanceField = new FieldImpl(
				"cashBalance",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tradeReferenceField = new FieldImpl(
				"tradeReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.treatmentField = new FieldImpl(
				"treatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralPositionStatusField = new FieldImpl(
				"collateralPositionStatus",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralPosition input) {
			FieldValue priceQuantity = Optional.ofNullable(input.getPriceQuantity())
				.map(x -> x.stream()
					.map(_x -> priceQuantityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceQuantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceQuantityField, Optional.empty()));
			FieldValue product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(productField, Optional.of(productTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(productField, Optional.empty()));
			FieldValue cashBalance = Optional.ofNullable(input.getCashBalance())
				.map(x -> new NestedFieldValueImpl(cashBalanceField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashBalanceField, Optional.empty()));
			FieldValue tradeReference = Optional.ofNullable(input.getTradeReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(tradeReferenceField, Optional.of(tradeStateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(tradeReferenceField, Optional.empty()));
			FieldValue treatment = Optional.ofNullable(input.getTreatment())
				.map(x -> new NestedFieldValueImpl(treatmentField, Optional.of(collateralTreatmentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(treatmentField, Optional.empty()));
			FieldValue collateralPositionStatus = new FieldValueImpl(collateralPositionStatusField, Optional.ofNullable(input.getCollateralPositionStatus()));
			return Arrays.asList(
				priceQuantity,
				product,
				cashBalance,
				tradeReference,
				treatment,
				collateralPositionStatus
			);
		}
	}
}
