package cdm.product.template.tabulator;

import cdm.product.template.StrikeSpread;
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


@ImplementedBy(StrikeSpreadTypeTabulator.Impl.class)
public interface StrikeSpreadTypeTabulator extends Tabulator<StrikeSpread> {
	@Singleton
	class Impl implements StrikeSpreadTypeTabulator {
		private final Field upperStrikeField;
		private final Field upperStrikeNumberOfOptionsField;

		private final OptionStrikeTypeTabulator optionStrikeTypeTabulator;

		@Inject
		public Impl(OptionStrikeTypeTabulator optionStrikeTypeTabulator) {
			this.optionStrikeTypeTabulator = optionStrikeTypeTabulator;
			this.upperStrikeField = new FieldImpl(
				"upperStrike",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.upperStrikeNumberOfOptionsField = new FieldImpl(
				"upperStrikeNumberOfOptions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StrikeSpread input) {
			FieldValue upperStrike = Optional.ofNullable(input.getUpperStrike())
				.map(x -> new NestedFieldValueImpl(upperStrikeField, Optional.of(optionStrikeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(upperStrikeField, Optional.empty()));
			FieldValue upperStrikeNumberOfOptions = new FieldValueImpl(upperStrikeNumberOfOptionsField, Optional.ofNullable(input.getUpperStrikeNumberOfOptions()));
			return Arrays.asList(
				upperStrike,
				upperStrikeNumberOfOptions
			);
		}
	}
}
