package cdm.base.datetime.tabulator;

import cdm.base.datetime.BusinessCenters;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(BusinessCentersTypeTabulator.Impl.class)
public interface BusinessCentersTypeTabulator extends Tabulator<BusinessCenters> {
	@Singleton
	class Impl implements BusinessCentersTypeTabulator {
		private final Field businessCenterField;
		private final Field commodityBusinessCalendarField;
		private final Field businessCentersReferenceField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.businessCenterField = new FieldImpl(
				"businessCenter",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commodityBusinessCalendarField = new FieldImpl(
				"commodityBusinessCalendar",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCentersReferenceField = new FieldImpl(
				"businessCentersReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BusinessCenters input) {
			FieldValue businessCenter = new FieldValueImpl(businessCenterField, Optional.ofNullable(input.getBusinessCenter())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue commodityBusinessCalendar = new FieldValueImpl(commodityBusinessCalendarField, Optional.ofNullable(input.getCommodityBusinessCalendar())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue businessCentersReference = Optional.ofNullable(input.getBusinessCentersReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(businessCentersReferenceField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(businessCentersReferenceField, Optional.empty()));
			return Arrays.asList(
				businessCenter,
				commodityBusinessCalendar,
				businessCentersReference
			);
		}
	}
}
