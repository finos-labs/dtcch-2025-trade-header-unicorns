package cdm.product.asset.tabulator;

import cdm.product.asset.ReferencePool;
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


@ImplementedBy(ReferencePoolTypeTabulator.Impl.class)
public interface ReferencePoolTypeTabulator extends Tabulator<ReferencePool> {
	@Singleton
	class Impl implements ReferencePoolTypeTabulator {
		private final Field referencePoolItemField;

		private final ReferencePoolItemTypeTabulator referencePoolItemTypeTabulator;

		@Inject
		public Impl(ReferencePoolItemTypeTabulator referencePoolItemTypeTabulator) {
			this.referencePoolItemTypeTabulator = referencePoolItemTypeTabulator;
			this.referencePoolItemField = new FieldImpl(
				"referencePoolItem",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferencePool input) {
			FieldValue referencePoolItem = Optional.ofNullable(input.getReferencePoolItem())
				.map(x -> x.stream()
					.map(_x -> referencePoolItemTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(referencePoolItemField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(referencePoolItemField, Optional.empty()));
			return Arrays.asList(
				referencePoolItem
			);
		}
	}
}
