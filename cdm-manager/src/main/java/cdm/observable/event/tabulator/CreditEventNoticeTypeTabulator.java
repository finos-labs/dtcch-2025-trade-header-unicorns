package cdm.observable.event.tabulator;

import cdm.observable.event.CreditEventNotice;
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


@ImplementedBy(CreditEventNoticeTypeTabulator.Impl.class)
public interface CreditEventNoticeTypeTabulator extends Tabulator<CreditEventNotice> {
	@Singleton
	class Impl implements CreditEventNoticeTypeTabulator {
		private final Field notifyingPartyField;
		private final Field businessCenterField;
		private final Field publiclyAvailableInformationField;

		private final PubliclyAvailableInformationTypeTabulator publiclyAvailableInformationTypeTabulator;

		@Inject
		public Impl(PubliclyAvailableInformationTypeTabulator publiclyAvailableInformationTypeTabulator) {
			this.publiclyAvailableInformationTypeTabulator = publiclyAvailableInformationTypeTabulator;
			this.notifyingPartyField = new FieldImpl(
				"notifyingParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCenterField = new FieldImpl(
				"businessCenter",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publiclyAvailableInformationField = new FieldImpl(
				"publiclyAvailableInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditEventNotice input) {
			FieldValue notifyingParty = new FieldValueImpl(notifyingPartyField, Optional.ofNullable(input.getNotifyingParty()));
			FieldValue businessCenter = new FieldValueImpl(businessCenterField, Optional.ofNullable(input.getBusinessCenter()));
			FieldValue publiclyAvailableInformation = Optional.ofNullable(input.getPubliclyAvailableInformation())
				.map(x -> new NestedFieldValueImpl(publiclyAvailableInformationField, Optional.of(publiclyAvailableInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(publiclyAvailableInformationField, Optional.empty()));
			return Arrays.asList(
				notifyingParty,
				businessCenter,
				publiclyAvailableInformation
			);
		}
	}
}
