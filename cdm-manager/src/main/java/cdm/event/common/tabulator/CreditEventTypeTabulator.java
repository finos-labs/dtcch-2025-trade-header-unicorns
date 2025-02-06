package cdm.event.common.tabulator;

import cdm.event.common.CreditEvent;
import cdm.legaldocumentation.common.tabulator.ResourceTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.tabulator.ReferenceInformationTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CreditEventTypeTabulator.Impl.class)
public interface CreditEventTypeTabulator extends Tabulator<CreditEvent> {
	@Singleton
	class Impl implements CreditEventTypeTabulator {
		private final Field creditEventTypeField;
		private final Field eventDeterminationDateField;
		private final Field auctionDateField;
		private final Field finalPriceField;
		private final Field recoveryPercentField;
		private final Field publiclyAvailableInformationField;
		private final Field referenceInformationField;

		private final PriceTypeTabulator priceTypeTabulator;
		private final ResourceTypeTabulator resourceTypeTabulator;
		private final ReferenceInformationTypeTabulator referenceInformationTypeTabulator;

		@Inject
		public Impl(PriceTypeTabulator priceTypeTabulator, ResourceTypeTabulator resourceTypeTabulator, ReferenceInformationTypeTabulator referenceInformationTypeTabulator) {
			this.priceTypeTabulator = priceTypeTabulator;
			this.resourceTypeTabulator = resourceTypeTabulator;
			this.referenceInformationTypeTabulator = referenceInformationTypeTabulator;
			this.creditEventTypeField = new FieldImpl(
				"creditEventType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.eventDeterminationDateField = new FieldImpl(
				"eventDeterminationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.auctionDateField = new FieldImpl(
				"auctionDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPriceField = new FieldImpl(
				"finalPrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.recoveryPercentField = new FieldImpl(
				"recoveryPercent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publiclyAvailableInformationField = new FieldImpl(
				"publiclyAvailableInformation",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceInformationField = new FieldImpl(
				"referenceInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CreditEvent input) {
			FieldValue creditEventType = new FieldValueImpl(creditEventTypeField, Optional.ofNullable(input.getCreditEventType()));
			FieldValue eventDeterminationDate = new FieldValueImpl(eventDeterminationDateField, Optional.ofNullable(input.getEventDeterminationDate()));
			FieldValue auctionDate = new FieldValueImpl(auctionDateField, Optional.ofNullable(input.getAuctionDate()));
			FieldValue finalPrice = Optional.ofNullable(input.getFinalPrice())
				.map(x -> new NestedFieldValueImpl(finalPriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalPriceField, Optional.empty()));
			FieldValue recoveryPercent = new FieldValueImpl(recoveryPercentField, Optional.ofNullable(input.getRecoveryPercent()));
			FieldValue publiclyAvailableInformation = Optional.ofNullable(input.getPubliclyAvailableInformation())
				.map(x -> x.stream()
					.map(_x -> resourceTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(publiclyAvailableInformationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(publiclyAvailableInformationField, Optional.empty()));
			FieldValue referenceInformation = Optional.ofNullable(input.getReferenceInformation())
				.map(x -> new NestedFieldValueImpl(referenceInformationField, Optional.of(referenceInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceInformationField, Optional.empty()));
			return Arrays.asList(
				creditEventType,
				eventDeterminationDate,
				auctionDate,
				finalPrice,
				recoveryPercent,
				publiclyAvailableInformation,
				referenceInformation
			);
		}
	}
}
