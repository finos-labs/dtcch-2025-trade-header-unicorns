package cdm.observable.asset.tabulator;

import cdm.observable.asset.CalculationAgent;
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


@ImplementedBy(CalculationAgentTypeTabulator.Impl.class)
public interface CalculationAgentTypeTabulator extends Tabulator<CalculationAgent> {
	@Singleton
	class Impl implements CalculationAgentTypeTabulator {
		private final Field calculationAgentPartyField;
		private final Field calculationAgentPartyEnumField;
		private final Field calculationAgentBusinessCenterField;

		public Impl() {
			this.calculationAgentPartyField = new FieldImpl(
				"calculationAgentParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationAgentPartyEnumField = new FieldImpl(
				"calculationAgentPartyEnum",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationAgentBusinessCenterField = new FieldImpl(
				"calculationAgentBusinessCenter",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CalculationAgent input) {
			FieldValue calculationAgentParty = new FieldValueImpl(calculationAgentPartyField, Optional.ofNullable(input.getCalculationAgentParty()));
			FieldValue calculationAgentPartyEnum = new FieldValueImpl(calculationAgentPartyEnumField, Optional.ofNullable(input.getCalculationAgentPartyEnum()));
			FieldValue calculationAgentBusinessCenter = new FieldValueImpl(calculationAgentBusinessCenterField, Optional.ofNullable(input.getCalculationAgentBusinessCenter())
				.map(x -> x.getValue()));
			return Arrays.asList(
				calculationAgentParty,
				calculationAgentPartyEnum,
				calculationAgentBusinessCenter
			);
		}
	}
}
