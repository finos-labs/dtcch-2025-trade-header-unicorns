package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.QuantityTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.CalculationScheduleDeliveryPeriods;
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


@ImplementedBy(CalculationScheduleDeliveryPeriodsTypeTabulator.Impl.class)
public interface CalculationScheduleDeliveryPeriodsTypeTabulator extends Tabulator<CalculationScheduleDeliveryPeriods> {
	@Singleton
	class Impl implements CalculationScheduleDeliveryPeriodsTypeTabulator {
		private final Field profileField;
		private final Field startDateField;
		private final Field endDateField;
		private final Field deliveryCapacityField;
		private final Field priceTimeIntervalQuantityField;

		private final AssetDeliveryProfileTypeTabulator assetDeliveryProfileTypeTabulator;
		private final QuantityTypeTabulator quantityTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(AssetDeliveryProfileTypeTabulator assetDeliveryProfileTypeTabulator, QuantityTypeTabulator quantityTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.assetDeliveryProfileTypeTabulator = assetDeliveryProfileTypeTabulator;
			this.quantityTypeTabulator = quantityTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.profileField = new FieldImpl(
				"profile",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.startDateField = new FieldImpl(
				"startDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.endDateField = new FieldImpl(
				"endDate",
				false,
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
		public List<FieldValue> tabulate(CalculationScheduleDeliveryPeriods input) {
			FieldValue profile = Optional.ofNullable(input.getProfile())
				.map(x -> x.stream()
					.map(_x -> assetDeliveryProfileTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(profileField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(profileField, Optional.empty()));
			FieldValue startDate = new FieldValueImpl(startDateField, Optional.ofNullable(input.getStartDate()));
			FieldValue endDate = new FieldValueImpl(endDateField, Optional.ofNullable(input.getEndDate()));
			FieldValue deliveryCapacity = Optional.ofNullable(input.getDeliveryCapacity())
				.map(x -> new NestedFieldValueImpl(deliveryCapacityField, Optional.of(quantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryCapacityField, Optional.empty()));
			FieldValue priceTimeIntervalQuantity = Optional.ofNullable(input.getPriceTimeIntervalQuantity())
				.map(x -> new NestedFieldValueImpl(priceTimeIntervalQuantityField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceTimeIntervalQuantityField, Optional.empty()));
			return Arrays.asList(
				profile,
				startDate,
				endDate,
				deliveryCapacity,
				priceTimeIntervalQuantity
			);
		}
	}
}
