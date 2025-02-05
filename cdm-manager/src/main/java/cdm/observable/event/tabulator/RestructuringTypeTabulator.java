package cdm.observable.event.tabulator;

import cdm.observable.event.Restructuring;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(RestructuringTypeTabulator.Impl.class)
public interface RestructuringTypeTabulator extends Tabulator<Restructuring> {
	@Singleton
	class Impl implements RestructuringTypeTabulator {
		private final Field applicableField;
		private final Field restructuringTypeField;
		private final Field multipleHolderObligationField;
		private final Field multipleCreditEventNoticesField;

		public Impl() {
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.restructuringTypeField = new FieldImpl(
				"restructuringType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multipleHolderObligationField = new FieldImpl(
				"multipleHolderObligation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multipleCreditEventNoticesField = new FieldImpl(
				"multipleCreditEventNotices",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Restructuring input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue restructuringType = new FieldValueImpl(restructuringTypeField, Optional.ofNullable(input.getRestructuringType())
				.map(x -> x.getValue()));
			FieldValue multipleHolderObligation = new FieldValueImpl(multipleHolderObligationField, Optional.ofNullable(input.getMultipleHolderObligation()));
			FieldValue multipleCreditEventNotices = new FieldValueImpl(multipleCreditEventNoticesField, Optional.ofNullable(input.getMultipleCreditEventNotices()));
			return Arrays.asList(
				applicable,
				restructuringType,
				multipleHolderObligation,
				multipleCreditEventNotices
			);
		}
	}
}
