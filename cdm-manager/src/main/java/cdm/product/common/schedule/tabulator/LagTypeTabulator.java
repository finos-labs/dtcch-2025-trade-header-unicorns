package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.product.common.schedule.Lag;
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


@ImplementedBy(LagTypeTabulator.Impl.class)
public interface LagTypeTabulator extends Tabulator<Lag> {
	@Singleton
	class Impl implements LagTypeTabulator {
		private final Field lagDurationField;
		private final Field firstObservationDateOffsetField;

		private final OffsetTypeTabulator offsetTypeTabulator;

		@Inject
		public Impl(OffsetTypeTabulator offsetTypeTabulator) {
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.lagDurationField = new FieldImpl(
				"lagDuration",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.firstObservationDateOffsetField = new FieldImpl(
				"firstObservationDateOffset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Lag input) {
			FieldValue lagDuration = Optional.ofNullable(input.getLagDuration())
				.map(x -> new NestedFieldValueImpl(lagDurationField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lagDurationField, Optional.empty()));
			FieldValue firstObservationDateOffset = Optional.ofNullable(input.getFirstObservationDateOffset())
				.map(x -> new NestedFieldValueImpl(firstObservationDateOffsetField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(firstObservationDateOffsetField, Optional.empty()));
			return Arrays.asList(
				lagDuration,
				firstObservationDateOffset
			);
		}
	}
}
