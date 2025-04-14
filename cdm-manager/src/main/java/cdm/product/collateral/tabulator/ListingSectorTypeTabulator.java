package cdm.product.collateral.tabulator;

import cdm.product.collateral.ListingSector;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;


@ImplementedBy(ListingSectorTypeTabulator.Impl.class)
public interface ListingSectorTypeTabulator extends Tabulator<ListingSector> {
	@Singleton
	class Impl implements ListingSectorTypeTabulator {
		private final Field sectorField;

		public Impl() {
			this.sectorField = new FieldImpl(
				"sector",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ListingSector input) {
			FieldValue sector = new FieldValueImpl(sectorField, Optional.ofNullable(input.getSector())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				sector
			);
		}
	}
}
