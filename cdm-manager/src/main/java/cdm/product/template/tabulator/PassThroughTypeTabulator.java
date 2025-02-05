package cdm.product.template.tabulator;

import cdm.product.template.PassThrough;
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


@ImplementedBy(PassThroughTypeTabulator.Impl.class)
public interface PassThroughTypeTabulator extends Tabulator<PassThrough> {
	@Singleton
	class Impl implements PassThroughTypeTabulator {
		private final Field passThroughItemField;

		private final PassThroughItemTypeTabulator passThroughItemTypeTabulator;

		@Inject
		public Impl(PassThroughItemTypeTabulator passThroughItemTypeTabulator) {
			this.passThroughItemTypeTabulator = passThroughItemTypeTabulator;
			this.passThroughItemField = new FieldImpl(
				"passThroughItem",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PassThrough input) {
			FieldValue passThroughItem = Optional.ofNullable(input.getPassThroughItem())
				.map(x -> x.stream()
					.map(_x -> passThroughItemTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(passThroughItemField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(passThroughItemField, Optional.empty()));
			return Arrays.asList(
				passThroughItem
			);
		}
	}
}
