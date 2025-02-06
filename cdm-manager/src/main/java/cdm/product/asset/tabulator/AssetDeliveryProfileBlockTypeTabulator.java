package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.QuantityTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.AssetDeliveryProfileBlock;
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


@ImplementedBy(AssetDeliveryProfileBlockTypeTabulator.Impl.class)
public interface AssetDeliveryProfileBlockTypeTabulator extends Tabulator<AssetDeliveryProfileBlock> {
	@Singleton
	class Impl implements AssetDeliveryProfileBlockTypeTabulator {
		private final Field startTimeField;
		private final Field endTimeField;
		private final Field dayOfWeekField;
		private final Field deliveryCapacityField;
		private final Field priceTimeIntervalQuantityField;

		private final QuantityTypeTabulator quantityTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(QuantityTypeTabulator quantityTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.quantityTypeTabulator = quantityTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.startTimeField = new FieldImpl(
				"startTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.endTimeField = new FieldImpl(
				"endTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayOfWeekField = new FieldImpl(
				"dayOfWeek",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryCapacityField = new FieldImpl(
				"deliveryCapacity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceTimeIntervalQuantityField = new FieldImpl(
				"priceTimeIntervalQuantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetDeliveryProfileBlock input) {
			FieldValue startTime = new FieldValueImpl(startTimeField, Optional.ofNullable(input.getStartTime()));
			FieldValue endTime = new FieldValueImpl(endTimeField, Optional.ofNullable(input.getEndTime()));
			FieldValue dayOfWeek = new FieldValueImpl(dayOfWeekField, Optional.ofNullable(input.getDayOfWeek()));
			FieldValue deliveryCapacity = Optional.ofNullable(input.getDeliveryCapacity())
				.map(x -> new NestedFieldValueImpl(deliveryCapacityField, Optional.of(quantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryCapacityField, Optional.empty()));
			FieldValue priceTimeIntervalQuantity = Optional.ofNullable(input.getPriceTimeIntervalQuantity())
				.map(x -> new NestedFieldValueImpl(priceTimeIntervalQuantityField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceTimeIntervalQuantityField, Optional.empty()));
			return Arrays.asList(
				startTime,
				endTime,
				dayOfWeek,
				deliveryCapacity,
				priceTimeIntervalQuantity
			);
		}
	}
}
