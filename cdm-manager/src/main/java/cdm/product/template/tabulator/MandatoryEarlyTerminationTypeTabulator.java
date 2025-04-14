package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableDateTypeTabulator;
import cdm.observable.asset.tabulator.CalculationAgentTypeTabulator;
import cdm.product.common.settlement.tabulator.SettlementTermsTypeTabulator;
import cdm.product.template.MandatoryEarlyTermination;
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


@ImplementedBy(MandatoryEarlyTerminationTypeTabulator.Impl.class)
public interface MandatoryEarlyTerminationTypeTabulator extends Tabulator<MandatoryEarlyTermination> {
	@Singleton
	class Impl implements MandatoryEarlyTerminationTypeTabulator {
		private final Field mandatoryEarlyTerminationDateField;
		private final Field calculationAgentField;
		private final Field cashSettlementField;
		private final Field mandatoryEarlyTerminationAdjustedDatesField;

		private final AdjustableDateTypeTabulator adjustableDateTypeTabulator;
		private final CalculationAgentTypeTabulator calculationAgentTypeTabulator;
		private final SettlementTermsTypeTabulator settlementTermsTypeTabulator;
		private final MandatoryEarlyTerminationAdjustedDatesTypeTabulator mandatoryEarlyTerminationAdjustedDatesTypeTabulator;

		@Inject
		public Impl(AdjustableDateTypeTabulator adjustableDateTypeTabulator, CalculationAgentTypeTabulator calculationAgentTypeTabulator, SettlementTermsTypeTabulator settlementTermsTypeTabulator, MandatoryEarlyTerminationAdjustedDatesTypeTabulator mandatoryEarlyTerminationAdjustedDatesTypeTabulator) {
			this.adjustableDateTypeTabulator = adjustableDateTypeTabulator;
			this.calculationAgentTypeTabulator = calculationAgentTypeTabulator;
			this.settlementTermsTypeTabulator = settlementTermsTypeTabulator;
			this.mandatoryEarlyTerminationAdjustedDatesTypeTabulator = mandatoryEarlyTerminationAdjustedDatesTypeTabulator;
			this.mandatoryEarlyTerminationDateField = new FieldImpl(
				"mandatoryEarlyTerminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationAgentField = new FieldImpl(
				"calculationAgent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementField = new FieldImpl(
				"cashSettlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mandatoryEarlyTerminationAdjustedDatesField = new FieldImpl(
				"mandatoryEarlyTerminationAdjustedDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MandatoryEarlyTermination input) {
			FieldValue mandatoryEarlyTerminationDate = Optional.ofNullable(input.getMandatoryEarlyTerminationDate())
				.map(x -> new NestedFieldValueImpl(mandatoryEarlyTerminationDateField, Optional.of(adjustableDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(mandatoryEarlyTerminationDateField, Optional.empty()));
			FieldValue calculationAgent = Optional.ofNullable(input.getCalculationAgent())
				.map(x -> new NestedFieldValueImpl(calculationAgentField, Optional.of(calculationAgentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationAgentField, Optional.empty()));
			FieldValue cashSettlement = Optional.ofNullable(input.getCashSettlement())
				.map(x -> new NestedFieldValueImpl(cashSettlementField, Optional.of(settlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashSettlementField, Optional.empty()));
			FieldValue mandatoryEarlyTerminationAdjustedDates = Optional.ofNullable(input.getMandatoryEarlyTerminationAdjustedDates())
				.map(x -> new NestedFieldValueImpl(mandatoryEarlyTerminationAdjustedDatesField, Optional.of(mandatoryEarlyTerminationAdjustedDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(mandatoryEarlyTerminationAdjustedDatesField, Optional.empty()));
			return Arrays.asList(
				mandatoryEarlyTerminationDate,
				calculationAgent,
				cashSettlement,
				mandatoryEarlyTerminationAdjustedDates
			);
		}
	}
}
