package cdm.observable.asset.tabulator;

import cdm.observable.asset.PriceSourceDisruption;
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


@ImplementedBy(PriceSourceDisruptionTypeTabulator.Impl.class)
public interface PriceSourceDisruptionTypeTabulator extends Tabulator<PriceSourceDisruption> {
	@Singleton
	class Impl implements PriceSourceDisruptionTypeTabulator {
		private final Field fallbackReferencePriceField;

		private final FallbackReferencePriceTypeTabulator fallbackReferencePriceTypeTabulator;

		@Inject
		public Impl(FallbackReferencePriceTypeTabulator fallbackReferencePriceTypeTabulator) {
			this.fallbackReferencePriceTypeTabulator = fallbackReferencePriceTypeTabulator;
			this.fallbackReferencePriceField = new FieldImpl(
				"fallbackReferencePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceSourceDisruption input) {
			FieldValue fallbackReferencePrice = Optional.ofNullable(input.getFallbackReferencePrice())
				.map(x -> new NestedFieldValueImpl(fallbackReferencePriceField, Optional.of(fallbackReferencePriceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fallbackReferencePriceField, Optional.empty()));
			return Arrays.asList(
				fallbackReferencePrice
			);
		}
	}
}
