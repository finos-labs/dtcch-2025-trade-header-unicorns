package cdm.product.template.tabulator;

import cdm.product.template.ExtendibleProvisionAdjustedDates;
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


@ImplementedBy(ExtendibleProvisionAdjustedDatesTypeTabulator.Impl.class)
public interface ExtendibleProvisionAdjustedDatesTypeTabulator extends Tabulator<ExtendibleProvisionAdjustedDates> {
	@Singleton
	class Impl implements ExtendibleProvisionAdjustedDatesTypeTabulator {
		private final Field extensionEventField;

		private final ExtensionEventTypeTabulator extensionEventTypeTabulator;

		@Inject
		public Impl(ExtensionEventTypeTabulator extensionEventTypeTabulator) {
			this.extensionEventTypeTabulator = extensionEventTypeTabulator;
			this.extensionEventField = new FieldImpl(
				"extensionEvent",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExtendibleProvisionAdjustedDates input) {
			FieldValue extensionEvent = Optional.ofNullable(input.getExtensionEvent())
				.map(x -> x.stream()
					.map(_x -> extensionEventTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(extensionEventField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(extensionEventField, Optional.empty()));
			return Arrays.asList(
				extensionEvent
			);
		}
	}
}
