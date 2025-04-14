package cdm.product.asset.tabulator;

import cdm.product.asset.VolatilityCapFloor;
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


@ImplementedBy(VolatilityCapFloorTypeTabulator.Impl.class)
public interface VolatilityCapFloorTypeTabulator extends Tabulator<VolatilityCapFloor> {
	@Singleton
	class Impl implements VolatilityCapFloorTypeTabulator {
		private final Field applicableField;
		private final Field totalVolatilityCapField;
		private final Field volatilityCapFactorField;

		public Impl() {
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.totalVolatilityCapField = new FieldImpl(
				"totalVolatilityCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.volatilityCapFactorField = new FieldImpl(
				"volatilityCapFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(VolatilityCapFloor input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue totalVolatilityCap = new FieldValueImpl(totalVolatilityCapField, Optional.ofNullable(input.getTotalVolatilityCap()));
			FieldValue volatilityCapFactor = new FieldValueImpl(volatilityCapFactorField, Optional.ofNullable(input.getVolatilityCapFactor()));
			return Arrays.asList(
				applicable,
				totalVolatilityCap,
				volatilityCapFactor
			);
		}
	}
}
