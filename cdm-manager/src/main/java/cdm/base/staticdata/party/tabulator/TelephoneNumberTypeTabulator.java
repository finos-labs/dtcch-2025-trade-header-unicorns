package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.TelephoneNumber;
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


@ImplementedBy(TelephoneNumberTypeTabulator.Impl.class)
public interface TelephoneNumberTypeTabulator extends Tabulator<TelephoneNumber> {
	@Singleton
	class Impl implements TelephoneNumberTypeTabulator {
		private final Field telephoneNumberTypeField;
		private final Field numberField;

		public Impl() {
			this.telephoneNumberTypeField = new FieldImpl(
				"telephoneNumberType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.numberField = new FieldImpl(
				"number",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TelephoneNumber input) {
			FieldValue telephoneNumberType = new FieldValueImpl(telephoneNumberTypeField, Optional.ofNullable(input.getTelephoneNumberType()));
			FieldValue number = new FieldValueImpl(numberField, Optional.ofNullable(input.getNumber()));
			return Arrays.asList(
				telephoneNumberType,
				number
			);
		}
	}
}
