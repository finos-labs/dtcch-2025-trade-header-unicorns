package cdm.product.collateral.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.product.collateral.SubstitutionProvisions;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(SubstitutionProvisionsTypeTabulator.Impl.class)
public interface SubstitutionProvisionsTypeTabulator extends Tabulator<SubstitutionProvisions> {
	@Singleton
	class Impl implements SubstitutionProvisionsTypeTabulator {
		private final Field numberOfSubstitutionsAllowedField;
		private final Field noticeDeadlinePeriodField;
		private final Field noticeDeadlineDateTimeField;

		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.numberOfSubstitutionsAllowedField = new FieldImpl(
				"numberOfSubstitutionsAllowed",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noticeDeadlinePeriodField = new FieldImpl(
				"noticeDeadlinePeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noticeDeadlineDateTimeField = new FieldImpl(
				"noticeDeadlineDateTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SubstitutionProvisions input) {
			FieldValue numberOfSubstitutionsAllowed = new FieldValueImpl(numberOfSubstitutionsAllowedField, Optional.ofNullable(input.getNumberOfSubstitutionsAllowed()));
			FieldValue noticeDeadlinePeriod = Optional.ofNullable(input.getNoticeDeadlinePeriod())
				.map(x -> new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.empty()));
			FieldValue noticeDeadlineDateTime = new FieldValueImpl(noticeDeadlineDateTimeField, Optional.ofNullable(input.getNoticeDeadlineDateTime()));
			return Arrays.asList(
				numberOfSubstitutionsAllowed,
				noticeDeadlinePeriod,
				noticeDeadlineDateTime
			);
		}
	}
}
