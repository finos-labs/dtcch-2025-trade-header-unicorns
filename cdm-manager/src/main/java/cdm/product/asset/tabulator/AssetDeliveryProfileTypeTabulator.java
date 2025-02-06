package cdm.product.asset.tabulator;

import cdm.product.asset.AssetDeliveryProfile;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(AssetDeliveryProfileTypeTabulator.Impl.class)
public interface AssetDeliveryProfileTypeTabulator extends Tabulator<AssetDeliveryProfile> {
	@Singleton
	class Impl implements AssetDeliveryProfileTypeTabulator {
		private final Field loadTypeField;
		private final Field blockField;
		private final Field bankHolidaysTreatmentField;

		private final AssetDeliveryProfileBlockTypeTabulator assetDeliveryProfileBlockTypeTabulator;

		@Inject
		public Impl(AssetDeliveryProfileBlockTypeTabulator assetDeliveryProfileBlockTypeTabulator) {
			this.assetDeliveryProfileBlockTypeTabulator = assetDeliveryProfileBlockTypeTabulator;
			this.loadTypeField = new FieldImpl(
				"loadType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.blockField = new FieldImpl(
				"block",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.bankHolidaysTreatmentField = new FieldImpl(
				"bankHolidaysTreatment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetDeliveryProfile input) {
			FieldValue loadType = new FieldValueImpl(loadTypeField, Optional.ofNullable(input.getLoadType()));
			FieldValue block = Optional.ofNullable(input.getBlock())
				.map(x -> x.stream()
					.map(_x -> assetDeliveryProfileBlockTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(blockField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(blockField, Optional.empty()));
			FieldValue bankHolidaysTreatment = new FieldValueImpl(bankHolidaysTreatmentField, Optional.ofNullable(input.getBankHolidaysTreatment()));
			return Arrays.asList(
				loadType,
				block,
				bankHolidaysTreatment
			);
		}
	}
}
