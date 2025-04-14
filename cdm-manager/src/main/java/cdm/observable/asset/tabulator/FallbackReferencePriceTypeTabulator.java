package cdm.observable.asset.tabulator;

import cdm.observable.asset.FallbackReferencePrice;
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


@ImplementedBy(FallbackReferencePriceTypeTabulator.Impl.class)
public interface FallbackReferencePriceTypeTabulator extends Tabulator<FallbackReferencePrice> {
	@Singleton
	class Impl implements FallbackReferencePriceTypeTabulator {
		private final Field valuationPostponementField;
		private final Field fallBackSettlementRateOptionField;
		private final Field fallbackSurveyValuationPostponementField;
		private final Field calculationAgentDeterminationField;

		private final ValuationPostponementTypeTabulator valuationPostponementTypeTabulator;
		private final CalculationAgentTypeTabulator calculationAgentTypeTabulator;

		@Inject
		public Impl(ValuationPostponementTypeTabulator valuationPostponementTypeTabulator, CalculationAgentTypeTabulator calculationAgentTypeTabulator) {
			this.valuationPostponementTypeTabulator = valuationPostponementTypeTabulator;
			this.calculationAgentTypeTabulator = calculationAgentTypeTabulator;
			this.valuationPostponementField = new FieldImpl(
				"valuationPostponement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fallBackSettlementRateOptionField = new FieldImpl(
				"fallBackSettlementRateOption",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fallbackSurveyValuationPostponementField = new FieldImpl(
				"fallbackSurveyValuationPostponement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.calculationAgentDeterminationField = new FieldImpl(
				"calculationAgentDetermination",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FallbackReferencePrice input) {
			FieldValue valuationPostponement = Optional.ofNullable(input.getValuationPostponement())
				.map(x -> new NestedFieldValueImpl(valuationPostponementField, Optional.of(valuationPostponementTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationPostponementField, Optional.empty()));
			FieldValue fallBackSettlementRateOption = new FieldValueImpl(fallBackSettlementRateOptionField, Optional.ofNullable(input.getFallBackSettlementRateOption())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue fallbackSurveyValuationPostponement = new FieldValueImpl(fallbackSurveyValuationPostponementField, Optional.ofNullable(input.getFallbackSurveyValuationPostponement()));
			FieldValue calculationAgentDetermination = Optional.ofNullable(input.getCalculationAgentDetermination())
				.map(x -> new NestedFieldValueImpl(calculationAgentDeterminationField, Optional.of(calculationAgentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationAgentDeterminationField, Optional.empty()));
			return Arrays.asList(
				valuationPostponement,
				fallBackSettlementRateOption,
				fallbackSurveyValuationPostponement,
				calculationAgentDetermination
			);
		}
	}
}
