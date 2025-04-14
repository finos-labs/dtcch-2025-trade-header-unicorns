package cdm.product.asset.tabulator;

import cdm.base.math.tabulator.QuantityTypeTabulator;
import cdm.base.staticdata.identifier.tabulator.LocationIdentifierTypeTabulator;
import cdm.product.asset.AssetDeliveryInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AssetDeliveryInformationTypeTabulator.Impl.class)
public interface AssetDeliveryInformationTypeTabulator extends Tabulator<AssetDeliveryInformation> {
	@Singleton
	class Impl implements AssetDeliveryInformationTypeTabulator {
		private final Field periodsField;
		private final Field locationField;
		private final Field deliveryCapacityField;

		private final AssetDeliveryPeriodsTypeTabulator assetDeliveryPeriodsTypeTabulator;
		private final LocationIdentifierTypeTabulator locationIdentifierTypeTabulator;
		private final QuantityTypeTabulator quantityTypeTabulator;

		@Inject
		public Impl(AssetDeliveryPeriodsTypeTabulator assetDeliveryPeriodsTypeTabulator, LocationIdentifierTypeTabulator locationIdentifierTypeTabulator, QuantityTypeTabulator quantityTypeTabulator) {
			this.assetDeliveryPeriodsTypeTabulator = assetDeliveryPeriodsTypeTabulator;
			this.locationIdentifierTypeTabulator = locationIdentifierTypeTabulator;
			this.quantityTypeTabulator = quantityTypeTabulator;
			this.periodsField = new FieldImpl(
				"periods",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.locationField = new FieldImpl(
				"location",
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
		}

		@Override
		public List<FieldValue> tabulate(AssetDeliveryInformation input) {
			FieldValue periods = Optional.ofNullable(input.getPeriods())
				.map(x -> new NestedFieldValueImpl(periodsField, Optional.of(assetDeliveryPeriodsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(periodsField, Optional.empty()));
			FieldValue location = Optional.ofNullable(input.getLocation())
				.map(x -> x.stream()
					.map(_x -> locationIdentifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(locationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(locationField, Optional.empty()));
			FieldValue deliveryCapacity = Optional.ofNullable(input.getDeliveryCapacity())
				.map(x -> new NestedFieldValueImpl(deliveryCapacityField, Optional.of(quantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryCapacityField, Optional.empty()));
			return Arrays.asList(
				periods,
				location,
				deliveryCapacity
			);
		}
	}
}
