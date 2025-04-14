package cdm.observable.asset.tabulator;

import cdm.observable.asset.CreditNotation;
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


@ImplementedBy(CreditNotationTypeTabulator.Impl.class)
public interface CreditNotationTypeTabulator extends Tabulator<CreditNotation> {
	@Singleton
	class Impl implements CreditNotationTypeTabulator {
		private final Field agencyField;
		private final Field notationField;
		private final Field scaleField;
		private final Field debtField;
		private final Field outlookField;
		private final Field creditWatchField;

		private final CreditRatingDebtTypeTabulator creditRatingDebtTypeTabulator;

		@Inject
		public Impl(CreditRatingDebtTypeTabulator creditRatingDebtTypeTabulator) {
			this.creditRatingDebtTypeTabulator = creditRatingDebtTypeTabulator;
			this.agencyField = new FieldImpl(
				"agency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.notationField = new FieldImpl(
				"notation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.scaleField = new FieldImpl(
				"scale",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.debtField = new FieldImpl(
				"debt",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.outlookField = new FieldImpl(
				"outlook",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditWatchField = new FieldImpl(
				"creditWatch",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditNotation input) {
			FieldValue agency = new FieldValueImpl(agencyField, Optional.ofNullable(input.getAgency()));
			FieldValue notation = new FieldValueImpl(notationField, Optional.ofNullable(input.getNotation())
				.map(x -> x.getValue()));
			FieldValue scale = new FieldValueImpl(scaleField, Optional.ofNullable(input.getScale())
				.map(x -> x.getValue()));
			FieldValue debt = Optional.ofNullable(input.getDebt())
				.map(x -> new NestedFieldValueImpl(debtField, Optional.of(creditRatingDebtTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(debtField, Optional.empty()));
			FieldValue outlook = new FieldValueImpl(outlookField, Optional.ofNullable(input.getOutlook()));
			FieldValue creditWatch = new FieldValueImpl(creditWatchField, Optional.ofNullable(input.getCreditWatch()));
			return Arrays.asList(
				agency,
				notation,
				scale,
				debt,
				outlook,
				creditWatch
			);
		}
	}
}
