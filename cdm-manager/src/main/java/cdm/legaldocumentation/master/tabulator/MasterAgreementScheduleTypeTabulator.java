package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.MasterAgreementSchedule;
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


@ImplementedBy(MasterAgreementScheduleTypeTabulator.Impl.class)
public interface MasterAgreementScheduleTypeTabulator extends Tabulator<MasterAgreementSchedule> {
	@Singleton
	class Impl implements MasterAgreementScheduleTypeTabulator {
		private final Field clauseField;

		private final MasterAgreementClauseTypeTabulator masterAgreementClauseTypeTabulator;

		@Inject
		public Impl(MasterAgreementClauseTypeTabulator masterAgreementClauseTypeTabulator) {
			this.masterAgreementClauseTypeTabulator = masterAgreementClauseTypeTabulator;
			this.clauseField = new FieldImpl(
				"clause",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MasterAgreementSchedule input) {
			FieldValue clause = Optional.ofNullable(input.getClause())
				.map(x -> x.stream()
					.map(_x -> masterAgreementClauseTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(clauseField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(clauseField, Optional.empty()));
			return Arrays.asList(
				clause
			);
		}
	}
}
