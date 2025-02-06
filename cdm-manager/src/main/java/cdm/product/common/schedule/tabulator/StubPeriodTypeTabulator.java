package cdm.product.common.schedule.tabulator;

import cdm.product.asset.tabulator.StubValueTypeTabulator;
import cdm.product.common.schedule.StubPeriod;
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


@ImplementedBy(StubPeriodTypeTabulator.Impl.class)
public interface StubPeriodTypeTabulator extends Tabulator<StubPeriod> {
	@Singleton
	class Impl implements StubPeriodTypeTabulator {
		private final Field calculationPeriodDatesReferenceField;
		private final Field initialStubField;
		private final Field finalStubField;

		private final CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator;
		private final StubValueTypeTabulator stubValueTypeTabulator;

		@Inject
		public Impl(CalculationPeriodDatesTypeTabulator calculationPeriodDatesTypeTabulator, StubValueTypeTabulator stubValueTypeTabulator) {
			this.calculationPeriodDatesTypeTabulator = calculationPeriodDatesTypeTabulator;
			this.stubValueTypeTabulator = stubValueTypeTabulator;
			this.calculationPeriodDatesReferenceField = new FieldImpl(
				"calculationPeriodDatesReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialStubField = new FieldImpl(
				"initialStub",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalStubField = new FieldImpl(
				"finalStub",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(StubPeriod input) {
			FieldValue calculationPeriodDatesReference = Optional.ofNullable(input.getCalculationPeriodDatesReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.of(calculationPeriodDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(calculationPeriodDatesReferenceField, Optional.empty()));
			FieldValue initialStub = Optional.ofNullable(input.getInitialStub())
				.map(x -> new NestedFieldValueImpl(initialStubField, Optional.of(stubValueTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialStubField, Optional.empty()));
			FieldValue finalStub = Optional.ofNullable(input.getFinalStub())
				.map(x -> new NestedFieldValueImpl(finalStubField, Optional.of(stubValueTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalStubField, Optional.empty()));
			return Arrays.asList(
				calculationPeriodDatesReference,
				initialStub,
				finalStub
			);
		}
	}
}
