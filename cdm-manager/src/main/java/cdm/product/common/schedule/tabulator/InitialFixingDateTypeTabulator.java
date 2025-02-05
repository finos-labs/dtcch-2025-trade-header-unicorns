package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.product.common.schedule.InitialFixingDate;
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


@ImplementedBy(InitialFixingDateTypeTabulator.Impl.class)
public interface InitialFixingDateTypeTabulator extends Tabulator<InitialFixingDate> {
	@Singleton
	class Impl implements InitialFixingDateTypeTabulator {
		private final Field relativeDateOffsetField;
		private final Field initialFixingDateField;

		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;

		@Inject
		public Impl(RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator) {
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.relativeDateOffsetField = new FieldImpl(
				"relativeDateOffset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialFixingDateField = new FieldImpl(
				"initialFixingDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InitialFixingDate input) {
			FieldValue relativeDateOffset = Optional.ofNullable(input.getRelativeDateOffset())
				.map(x -> new NestedFieldValueImpl(relativeDateOffsetField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relativeDateOffsetField, Optional.empty()));
			FieldValue initialFixingDate = new FieldValueImpl(initialFixingDateField, Optional.ofNullable(input.getInitialFixingDate()));
			return Arrays.asList(
				relativeDateOffset,
				initialFixingDate
			);
		}
	}
}
