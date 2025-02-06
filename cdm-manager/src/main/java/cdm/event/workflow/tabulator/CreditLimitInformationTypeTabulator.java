package cdm.event.workflow.tabulator;

import cdm.event.workflow.CreditLimitInformation;
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


@ImplementedBy(CreditLimitInformationTypeTabulator.Impl.class)
public interface CreditLimitInformationTypeTabulator extends Tabulator<CreditLimitInformation> {
	@Singleton
	class Impl implements CreditLimitInformationTypeTabulator {
		private final Field limitApplicableField;

		private final LimitApplicableExtendedTypeTabulator limitApplicableExtendedTypeTabulator;

		@Inject
		public Impl(LimitApplicableExtendedTypeTabulator limitApplicableExtendedTypeTabulator) {
			this.limitApplicableExtendedTypeTabulator = limitApplicableExtendedTypeTabulator;
			this.limitApplicableField = new FieldImpl(
				"limitApplicable",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditLimitInformation input) {
			FieldValue limitApplicable = Optional.ofNullable(input.getLimitApplicable())
				.map(x -> x.stream()
					.map(_x -> limitApplicableExtendedTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(limitApplicableField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(limitApplicableField, Optional.empty()));
			return Arrays.asList(
				limitApplicable
			);
		}
	}
}
