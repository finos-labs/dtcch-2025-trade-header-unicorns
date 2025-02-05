package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.Address;
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


@ImplementedBy(AddressTypeTabulator.Impl.class)
public interface AddressTypeTabulator extends Tabulator<Address> {
	@Singleton
	class Impl implements AddressTypeTabulator {
		private final Field streetField;
		private final Field cityField;
		private final Field stateField;
		private final Field countryField;
		private final Field postalCodeField;

		public Impl() {
			this.streetField = new FieldImpl(
				"street",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cityField = new FieldImpl(
				"city",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stateField = new FieldImpl(
				"state",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.countryField = new FieldImpl(
				"country",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.postalCodeField = new FieldImpl(
				"postalCode",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Address input) {
			FieldValue street = new FieldValueImpl(streetField, Optional.ofNullable(input.getStreet()));
			FieldValue city = new FieldValueImpl(cityField, Optional.ofNullable(input.getCity()));
			FieldValue state = new FieldValueImpl(stateField, Optional.ofNullable(input.getState()));
			FieldValue country = new FieldValueImpl(countryField, Optional.ofNullable(input.getCountry())
				.map(x -> x.getValue()));
			FieldValue postalCode = new FieldValueImpl(postalCodeField, Optional.ofNullable(input.getPostalCode()));
			return Arrays.asList(
				street,
				city,
				state,
				country,
				postalCode
			);
		}
	}
}
