package cdm.product.asset.tabulator;

import cdm.product.asset.AssetDeliveryPeriods;
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


@ImplementedBy(AssetDeliveryPeriodsTypeTabulator.Impl.class)
public interface AssetDeliveryPeriodsTypeTabulator extends Tabulator<AssetDeliveryPeriods> {
	@Singleton
	class Impl implements AssetDeliveryPeriodsTypeTabulator {
		private final Field profileField;
		private final Field startDateField;
		private final Field endDateField;

		private final AssetDeliveryProfileTypeTabulator assetDeliveryProfileTypeTabulator;

		@Inject
		public Impl(AssetDeliveryProfileTypeTabulator assetDeliveryProfileTypeTabulator) {
			this.assetDeliveryProfileTypeTabulator = assetDeliveryProfileTypeTabulator;
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
		}

		@Override
		public List<FieldValue> tabulate(AssetDeliveryPeriods input) {
			FieldValue profile = Optional.ofNullable(input.getProfile())
				.map(x -> x.stream()
					.map(_x -> assetDeliveryProfileTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(profileField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(profileField, Optional.empty()));
			FieldValue startDate = new FieldValueImpl(startDateField, Optional.ofNullable(input.getStartDate()));
			FieldValue endDate = new FieldValueImpl(endDateField, Optional.ofNullable(input.getEndDate()));
			return Arrays.asList(
				profile,
				startDate,
				endDate
			);
		}
	}
}
