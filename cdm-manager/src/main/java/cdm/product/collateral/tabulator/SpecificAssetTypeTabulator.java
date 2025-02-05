package cdm.product.collateral.tabulator;

import cdm.base.staticdata.asset.common.tabulator.CashTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.CommodityTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.DigitalAssetTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.InstrumentTypeTabulator;
import cdm.product.collateral.SpecificAsset;
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


@ImplementedBy(SpecificAssetTypeTabulator.Impl.class)
public interface SpecificAssetTypeTabulator extends Tabulator<SpecificAsset> {
	@Singleton
	class Impl implements SpecificAssetTypeTabulator {
		private final Field CashField;
		private final Field CommodityField;
		private final Field DigitalAssetField;
		private final Field InstrumentField;

		private final CashTypeTabulator cashTypeTabulator;
		private final CommodityTypeTabulator commodityTypeTabulator;
		private final DigitalAssetTypeTabulator digitalAssetTypeTabulator;
		private final InstrumentTypeTabulator instrumentTypeTabulator;

		@Inject
		public Impl(CashTypeTabulator cashTypeTabulator, CommodityTypeTabulator commodityTypeTabulator, DigitalAssetTypeTabulator digitalAssetTypeTabulator, InstrumentTypeTabulator instrumentTypeTabulator) {
			this.cashTypeTabulator = cashTypeTabulator;
			this.commodityTypeTabulator = commodityTypeTabulator;
			this.digitalAssetTypeTabulator = digitalAssetTypeTabulator;
			this.instrumentTypeTabulator = instrumentTypeTabulator;
			this.CashField = new FieldImpl(
				"Cash",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.CommodityField = new FieldImpl(
				"Commodity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.DigitalAssetField = new FieldImpl(
				"DigitalAsset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.InstrumentField = new FieldImpl(
				"Instrument",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SpecificAsset input) {
			FieldValue Cash = Optional.ofNullable(input.getCash())
				.map(x -> new NestedFieldValueImpl(CashField, Optional.of(cashTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CashField, Optional.empty()));
			FieldValue Commodity = Optional.ofNullable(input.getCommodity())
				.map(x -> new NestedFieldValueImpl(CommodityField, Optional.of(commodityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CommodityField, Optional.empty()));
			FieldValue DigitalAsset = Optional.ofNullable(input.getDigitalAsset())
				.map(x -> new NestedFieldValueImpl(DigitalAssetField, Optional.of(digitalAssetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(DigitalAssetField, Optional.empty()));
			FieldValue Instrument = Optional.ofNullable(input.getInstrument())
				.map(x -> new NestedFieldValueImpl(InstrumentField, Optional.of(instrumentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(InstrumentField, Optional.empty()));
			return Arrays.asList(
				Cash,
				Commodity,
				DigitalAsset,
				Instrument
			);
		}
	}
}
