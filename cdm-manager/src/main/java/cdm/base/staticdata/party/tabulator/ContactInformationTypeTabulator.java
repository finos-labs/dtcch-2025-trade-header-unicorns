package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.ContactInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ContactInformationTypeTabulator.Impl.class)
public interface ContactInformationTypeTabulator extends Tabulator<ContactInformation> {
	@Singleton
	class Impl implements ContactInformationTypeTabulator {
		private final Field telephoneField;
		private final Field addressField;
		private final Field emailField;
		private final Field webPageField;

		private final TelephoneNumberTypeTabulator telephoneNumberTypeTabulator;
		private final AddressTypeTabulator addressTypeTabulator;

		@Inject
		public Impl(TelephoneNumberTypeTabulator telephoneNumberTypeTabulator, AddressTypeTabulator addressTypeTabulator) {
			this.telephoneNumberTypeTabulator = telephoneNumberTypeTabulator;
			this.addressTypeTabulator = addressTypeTabulator;
			this.telephoneField = new FieldImpl(
				"telephone",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.addressField = new FieldImpl(
				"address",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.emailField = new FieldImpl(
				"email",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.webPageField = new FieldImpl(
				"webPage",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ContactInformation input) {
			FieldValue telephone = Optional.ofNullable(input.getTelephone())
				.map(x -> x.stream()
					.map(_x -> telephoneNumberTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(telephoneField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(telephoneField, Optional.empty()));
			FieldValue address = Optional.ofNullable(input.getAddress())
				.map(x -> x.stream()
					.map(_x -> addressTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(addressField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(addressField, Optional.empty()));
			FieldValue email = new FieldValueImpl(emailField, Optional.ofNullable(input.getEmail()));
			FieldValue webPage = new FieldValueImpl(webPageField, Optional.ofNullable(input.getWebPage()));
			return Arrays.asList(
				telephone,
				address,
				email,
				webPage
			);
		}
	}
}
