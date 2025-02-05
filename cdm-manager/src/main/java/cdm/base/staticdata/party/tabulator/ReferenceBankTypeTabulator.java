package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.ReferenceBank;
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


@ImplementedBy(ReferenceBankTypeTabulator.Impl.class)
public interface ReferenceBankTypeTabulator extends Tabulator<ReferenceBank> {
	@Singleton
	class Impl implements ReferenceBankTypeTabulator {
		private final Field referenceBankIdField;
		private final Field referenceBankNameField;

		public Impl() {
			this.referenceBankIdField = new FieldImpl(
				"referenceBankId",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceBankNameField = new FieldImpl(
				"referenceBankName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferenceBank input) {
			FieldValue referenceBankId = new FieldValueImpl(referenceBankIdField, Optional.ofNullable(input.getReferenceBankId())
				.map(x -> x.getValue()));
			FieldValue referenceBankName = new FieldValueImpl(referenceBankNameField, Optional.ofNullable(input.getReferenceBankName()));
			return Arrays.asList(
				referenceBankId,
				referenceBankName
			);
		}
	}
}
