package cdm.product.template.tabulator;

import cdm.product.template.CancelableProvisionAdjustedDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CancelableProvisionAdjustedDatesTypeTabulator.Impl.class)
public interface CancelableProvisionAdjustedDatesTypeTabulator extends Tabulator<CancelableProvisionAdjustedDates> {
	@Singleton
	class Impl implements CancelableProvisionAdjustedDatesTypeTabulator {
		private final Field cancellationEventField;

		private final CancellationEventTypeTabulator cancellationEventTypeTabulator;

		@Inject
		public Impl(CancellationEventTypeTabulator cancellationEventTypeTabulator) {
			this.cancellationEventTypeTabulator = cancellationEventTypeTabulator;
			this.cancellationEventField = new FieldImpl(
				"cancellationEvent",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CancelableProvisionAdjustedDates input) {
			FieldValue cancellationEvent = Optional.ofNullable(input.getCancellationEvent())
				.map(x -> x.stream()
					.map(_x -> cancellationEventTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(cancellationEventField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(cancellationEventField, Optional.empty()));
			return Arrays.asList(
				cancellationEvent
			);
		}
	}
}
