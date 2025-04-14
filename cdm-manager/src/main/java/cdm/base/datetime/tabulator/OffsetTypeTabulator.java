package cdm.base.datetime.tabulator;

import cdm.base.datetime.Offset;
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


@ImplementedBy(OffsetTypeTabulator.Impl.class)
public interface OffsetTypeTabulator extends Tabulator<Offset> {
	@Singleton
	class Impl implements OffsetTypeTabulator {
		private final Field periodMultiplierField;
		private final Field periodField;
		private final Field dayTypeField;

		public Impl() {
			this.periodMultiplierField = new FieldImpl(
				"periodMultiplier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.periodField = new FieldImpl(
				"period",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dayTypeField = new FieldImpl(
				"dayType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Offset input) {
			FieldValue periodMultiplier = new FieldValueImpl(periodMultiplierField, Optional.ofNullable(input.getPeriodMultiplier()));
			FieldValue period = new FieldValueImpl(periodField, Optional.ofNullable(input.getPeriod()));
			FieldValue dayType = new FieldValueImpl(dayTypeField, Optional.ofNullable(input.getDayType()));
			return Arrays.asList(
				periodMultiplier,
				period,
				dayType
			);
		}
	}
}
