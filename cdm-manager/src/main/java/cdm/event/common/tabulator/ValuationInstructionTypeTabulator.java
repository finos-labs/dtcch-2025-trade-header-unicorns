package cdm.event.common.tabulator;

import cdm.event.common.ValuationInstruction;
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


@ImplementedBy(ValuationInstructionTypeTabulator.Impl.class)
public interface ValuationInstructionTypeTabulator extends Tabulator<ValuationInstruction> {
	@Singleton
	class Impl implements ValuationInstructionTypeTabulator {
		private final Field valuationField;
		private final Field replaceField;

		private final ValuationTypeTabulator valuationTypeTabulator;

		@Inject
		public Impl(ValuationTypeTabulator valuationTypeTabulator) {
			this.valuationTypeTabulator = valuationTypeTabulator;
			this.valuationField = new FieldImpl(
				"valuation",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.replaceField = new FieldImpl(
				"replace",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationInstruction input) {
			FieldValue valuation = Optional.ofNullable(input.getValuation())
				.map(x -> x.stream()
					.map(_x -> valuationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(valuationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(valuationField, Optional.empty()));
			FieldValue replace = new FieldValueImpl(replaceField, Optional.ofNullable(input.getReplace()));
			return Arrays.asList(
				valuation,
				replace
			);
		}
	}
}
