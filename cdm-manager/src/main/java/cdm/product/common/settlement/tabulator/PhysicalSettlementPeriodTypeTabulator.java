package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.PhysicalSettlementPeriod;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(PhysicalSettlementPeriodTypeTabulator.Impl.class)
public interface PhysicalSettlementPeriodTypeTabulator extends Tabulator<PhysicalSettlementPeriod> {
	@Singleton
	class Impl implements PhysicalSettlementPeriodTypeTabulator {
		private final Field businessDaysNotSpecifiedField;
		private final Field businessDaysField;
		private final Field maximumBusinessDaysField;

		public Impl() {
			this.businessDaysNotSpecifiedField = new FieldImpl(
				"businessDaysNotSpecified",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessDaysField = new FieldImpl(
				"businessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.maximumBusinessDaysField = new FieldImpl(
				"maximumBusinessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PhysicalSettlementPeriod input) {
			FieldValue businessDaysNotSpecified = new FieldValueImpl(businessDaysNotSpecifiedField, Optional.ofNullable(input.getBusinessDaysNotSpecified()));
			FieldValue businessDays = new FieldValueImpl(businessDaysField, Optional.ofNullable(input.getBusinessDays()));
			FieldValue maximumBusinessDays = new FieldValueImpl(maximumBusinessDaysField, Optional.ofNullable(input.getMaximumBusinessDays()));
			return Arrays.asList(
				businessDaysNotSpecified,
				businessDays,
				maximumBusinessDays
			);
		}
	}
}
