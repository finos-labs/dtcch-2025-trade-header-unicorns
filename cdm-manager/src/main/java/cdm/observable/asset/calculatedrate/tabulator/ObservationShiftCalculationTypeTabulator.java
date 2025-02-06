package cdm.observable.asset.calculatedrate.tabulator;

import cdm.base.datetime.tabulator.BusinessCentersTypeTabulator;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
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


@ImplementedBy(ObservationShiftCalculationTypeTabulator.Impl.class)
public interface ObservationShiftCalculationTypeTabulator extends Tabulator<ObservationShiftCalculation> {
	@Singleton
	class Impl implements ObservationShiftCalculationTypeTabulator {
		private final Field offsetDaysField;
		private final Field calculationBaseField;
		private final Field additionalBusinessDaysField;

		private final BusinessCentersTypeTabulator businessCentersTypeTabulator;

		@Inject
		public Impl(BusinessCentersTypeTabulator businessCentersTypeTabulator) {
			this.businessCentersTypeTabulator = businessCentersTypeTabulator;
			this.offsetDaysField = new FieldImpl(
				"offsetDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationBaseField = new FieldImpl(
				"calculationBase",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalBusinessDaysField = new FieldImpl(
				"additionalBusinessDays",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ObservationShiftCalculation input) {
			FieldValue offsetDays = new FieldValueImpl(offsetDaysField, Optional.ofNullable(input.getOffsetDays()));
			FieldValue calculationBase = new FieldValueImpl(calculationBaseField, Optional.ofNullable(input.getCalculationBase()));
			FieldValue additionalBusinessDays = Optional.ofNullable(input.getAdditionalBusinessDays())
				.map(x -> new NestedFieldValueImpl(additionalBusinessDaysField, Optional.of(businessCentersTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(additionalBusinessDaysField, Optional.empty()));
			return Arrays.asList(
				offsetDays,
				calculationBase,
				additionalBusinessDays
			);
		}
	}
}
