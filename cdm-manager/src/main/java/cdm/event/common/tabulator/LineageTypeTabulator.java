package cdm.event.common.tabulator;

import cdm.event.common.Lineage;
import cdm.event.position.tabulator.PortfolioStateTypeTabulator;
import cdm.event.workflow.tabulator.WorkflowStepTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(LineageTypeTabulator.Impl.class)
public interface LineageTypeTabulator extends Tabulator<Lineage> {
	@Singleton
	class Impl implements LineageTypeTabulator {
		private final Field tradeReferenceField;
		private final Field eventReferenceField;
		private final Field portfolioStateReferenceField;

		private final TradeTypeTabulator tradeTypeTabulator;
		private final WorkflowStepTypeTabulator workflowStepTypeTabulator;
		private final PortfolioStateTypeTabulator portfolioStateTypeTabulator;

		@Inject
		public Impl(TradeTypeTabulator tradeTypeTabulator, WorkflowStepTypeTabulator workflowStepTypeTabulator, PortfolioStateTypeTabulator portfolioStateTypeTabulator) {
			this.tradeTypeTabulator = tradeTypeTabulator;
			this.workflowStepTypeTabulator = workflowStepTypeTabulator;
			this.portfolioStateTypeTabulator = portfolioStateTypeTabulator;
			this.tradeReferenceField = new FieldImpl(
				"tradeReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.eventReferenceField = new FieldImpl(
				"eventReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.portfolioStateReferenceField = new FieldImpl(
				"portfolioStateReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Lineage input) {
			FieldValue tradeReference = Optional.ofNullable(input.getTradeReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> tradeTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(tradeReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(tradeReferenceField, Optional.empty()));
			FieldValue eventReference = Optional.ofNullable(input.getEventReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> workflowStepTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(eventReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(eventReferenceField, Optional.empty()));
			FieldValue portfolioStateReference = Optional.ofNullable(input.getPortfolioStateReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> portfolioStateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(portfolioStateReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(portfolioStateReferenceField, Optional.empty()));
			return Arrays.asList(
				tradeReference,
				eventReference,
				portfolioStateReference
			);
		}
	}
}
