package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.datetime.tabulator.AdjustableDateTypeTabulator;
import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.base.staticdata.asset.common.DeliveryDateParameters;
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


@ImplementedBy(DeliveryDateParametersTypeTabulator.Impl.class)
public interface DeliveryDateParametersTypeTabulator extends Tabulator<DeliveryDateParameters> {
	@Singleton
	class Impl implements DeliveryDateParametersTypeTabulator {
		private final Field deliveryNearbyField;
		private final Field deliveryDateField;
		private final Field deliveryDateRollConventionField;
		private final Field deliveryDateExpirationConventionField;

		private final OffsetTypeTabulator offsetTypeTabulator;
		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;

		@Inject
		public Impl(OffsetTypeTabulator offsetTypeTabulator, AdjustableDateTypeTabulator adjustableDateTypeTabulator) {
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.deliveryNearbyField = new FieldImpl(
				"deliveryNearby",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryDateField = new FieldImpl(
				"deliveryDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryDateRollConventionField = new FieldImpl(
				"deliveryDateRollConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryDateExpirationConventionField = new FieldImpl(
				"deliveryDateExpirationConvention",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DeliveryDateParameters input) {
			FieldValue deliveryNearby = Optional.ofNullable(input.getDeliveryNearby())
				.map(x -> new NestedFieldValueImpl(deliveryNearbyField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryNearbyField, Optional.empty()));
			FieldValue deliveryDate = Optional.ofNullable(input.getDeliveryDate())
				.map(x -> new NestedFieldValueImpl(deliveryDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryDateField, Optional.empty()));
			FieldValue deliveryDateRollConvention = Optional.ofNullable(input.getDeliveryDateRollConvention())
				.map(x -> new NestedFieldValueImpl(deliveryDateRollConventionField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryDateRollConventionField, Optional.empty()));
			FieldValue deliveryDateExpirationConvention = Optional.ofNullable(input.getDeliveryDateExpirationConvention())
				.map(x -> new NestedFieldValueImpl(deliveryDateExpirationConventionField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryDateExpirationConventionField, Optional.empty()));
			return Arrays.asList(
				deliveryNearby,
				deliveryDate,
				deliveryDateRollConvention,
				deliveryDateExpirationConvention
			);
		}
	}
}
