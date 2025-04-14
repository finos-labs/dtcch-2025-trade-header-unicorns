package cdm.event.common.tabulator;

import cdm.event.common.CorporateAction;
import cdm.product.template.tabulator.UnderlierTypeTabulator;
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


@ImplementedBy(CorporateActionTypeTabulator.Impl.class)
public interface CorporateActionTypeTabulator extends Tabulator<CorporateAction> {
	@Singleton
	class Impl implements CorporateActionTypeTabulator {
		private final Field corporateActionTypeField;
		private final Field exDateField;
		private final Field payDateField;
		private final Field underlierField;

		private final UnderlierTypeTabulator underlierTypeTabulator;

		@Inject
		public Impl(UnderlierTypeTabulator underlierTypeTabulator) {
			this.underlierTypeTabulator = underlierTypeTabulator;
			this.corporateActionTypeField = new FieldImpl(
				"corporateActionType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exDateField = new FieldImpl(
				"exDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payDateField = new FieldImpl(
				"payDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.underlierField = new FieldImpl(
				"underlier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CorporateAction input) {
			FieldValue corporateActionType = new FieldValueImpl(corporateActionTypeField, Optional.ofNullable(input.getCorporateActionType()));
			FieldValue exDate = new FieldValueImpl(exDateField, Optional.ofNullable(input.getExDate()));
			FieldValue payDate = new FieldValueImpl(payDateField, Optional.ofNullable(input.getPayDate()));
			FieldValue underlier = Optional.ofNullable(input.getUnderlier())
				.map(x -> new NestedFieldValueImpl(underlierField, Optional.of(underlierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(underlierField, Optional.empty()));
			return Arrays.asList(
				corporateActionType,
				exDate,
				payDate,
				underlier
			);
		}
	}
}
