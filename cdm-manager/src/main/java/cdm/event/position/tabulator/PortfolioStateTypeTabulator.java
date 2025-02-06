package cdm.event.position.tabulator;

import cdm.event.common.tabulator.LineageTypeTabulator;
import cdm.event.position.PortfolioState;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PortfolioStateTypeTabulator.Impl.class)
public interface PortfolioStateTypeTabulator extends Tabulator<PortfolioState> {
	@Singleton
	class Impl implements PortfolioStateTypeTabulator {
		private final Field positionsField;
		private final Field lineageField;

		private final PositionTypeTabulator positionTypeTabulator;
		private final LineageTypeTabulator lineageTypeTabulator;

		@Inject
		public Impl(PositionTypeTabulator positionTypeTabulator, LineageTypeTabulator lineageTypeTabulator) {
			this.positionTypeTabulator = positionTypeTabulator;
			this.lineageTypeTabulator = lineageTypeTabulator;
			this.positionsField = new FieldImpl(
				"positions",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lineageField = new FieldImpl(
				"lineage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PortfolioState input) {
			FieldValue positions = Optional.ofNullable(input.getPositions())
				.map(x -> x.stream()
					.map(_x -> positionTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(positionsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(positionsField, Optional.empty()));
			FieldValue lineage = Optional.ofNullable(input.getLineage())
				.map(x -> new NestedFieldValueImpl(lineageField, Optional.of(lineageTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lineageField, Optional.empty()));
			return Arrays.asList(
				positions,
				lineage
			);
		}
	}
}
