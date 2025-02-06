package cdm.base.math.tabulator;

import cdm.base.math.Rounding;
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


@ImplementedBy(RoundingTypeTabulator.Impl.class)
public interface RoundingTypeTabulator extends Tabulator<Rounding> {
	@Singleton
	class Impl implements RoundingTypeTabulator {
		private final Field roundingDirectionField;
		private final Field precisionField;

		public Impl() {
			this.roundingDirectionField = new FieldImpl(
				"roundingDirection",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.precisionField = new FieldImpl(
				"precision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Rounding input) {
			FieldValue roundingDirection = new FieldValueImpl(roundingDirectionField, Optional.ofNullable(input.getRoundingDirection()));
			FieldValue precision = new FieldValueImpl(precisionField, Optional.ofNullable(input.getPrecision()));
			return Arrays.asList(
				roundingDirection,
				precision
			);
		}
	}
}
