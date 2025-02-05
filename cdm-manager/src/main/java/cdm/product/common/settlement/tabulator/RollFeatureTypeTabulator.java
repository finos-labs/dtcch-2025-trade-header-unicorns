package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.product.common.settlement.RollFeature;
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


@ImplementedBy(RollFeatureTypeTabulator.Impl.class)
public interface RollFeatureTypeTabulator extends Tabulator<RollFeature> {
	@Singleton
	class Impl implements RollFeatureTypeTabulator {
		private final Field rollSourceCalendarField;
		private final Field deliveryDateRollConventionField;

		private final OffsetTypeTabulator offsetTypeTabulator;

		@Inject
		public Impl(OffsetTypeTabulator offsetTypeTabulator) {
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.rollSourceCalendarField = new FieldImpl(
				"rollSourceCalendar",
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
		}

		@Override
		public List<FieldValue> tabulate(RollFeature input) {
			FieldValue rollSourceCalendar = new FieldValueImpl(rollSourceCalendarField, Optional.ofNullable(input.getRollSourceCalendar()));
			FieldValue deliveryDateRollConvention = Optional.ofNullable(input.getDeliveryDateRollConvention())
				.map(x -> new NestedFieldValueImpl(deliveryDateRollConventionField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliveryDateRollConventionField, Optional.empty()));
			return Arrays.asList(
				rollSourceCalendar,
				deliveryDateRollConvention
			);
		}
	}
}
