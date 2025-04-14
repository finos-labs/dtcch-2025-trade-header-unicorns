package cdm.product.asset.tabulator;

import cdm.observable.asset.tabulator.CreditIndexTypeTabulator;
import cdm.product.asset.GeneralTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(GeneralTermsTypeTabulator.Impl.class)
public interface GeneralTermsTypeTabulator extends Tabulator<GeneralTerms> {
	@Singleton
	class Impl implements GeneralTermsTypeTabulator {
		private final Field referenceInformationField;
		private final Field indexReferenceInformationField;
		private final Field basketReferenceInformationField;
		private final Field additionalTermField;
		private final Field substitutionField;
		private final Field modifiedEquityDeliveryField;

		private final ReferenceInformationTypeTabulator referenceInformationTypeTabulator;
		private final CreditIndexTypeTabulator creditIndexTypeTabulator;
		private final BasketReferenceInformationTypeTabulator basketReferenceInformationTypeTabulator;

		@Inject
		public Impl(ReferenceInformationTypeTabulator referenceInformationTypeTabulator, CreditIndexTypeTabulator creditIndexTypeTabulator, BasketReferenceInformationTypeTabulator basketReferenceInformationTypeTabulator) {
			this.referenceInformationTypeTabulator = referenceInformationTypeTabulator;
			this.creditIndexTypeTabulator = creditIndexTypeTabulator;
			this.basketReferenceInformationTypeTabulator = basketReferenceInformationTypeTabulator;
			this.referenceInformationField = new FieldImpl(
				"referenceInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexReferenceInformationField = new FieldImpl(
				"indexReferenceInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.basketReferenceInformationField = new FieldImpl(
				"basketReferenceInformation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalTermField = new FieldImpl(
				"additionalTerm",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.substitutionField = new FieldImpl(
				"substitution",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.modifiedEquityDeliveryField = new FieldImpl(
				"modifiedEquityDelivery",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(GeneralTerms input) {
			FieldValue referenceInformation = Optional.ofNullable(input.getReferenceInformation())
				.map(x -> new NestedFieldValueImpl(referenceInformationField, Optional.of(referenceInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceInformationField, Optional.empty()));
			FieldValue indexReferenceInformation = Optional.ofNullable(input.getIndexReferenceInformation())
				.map(x -> new NestedFieldValueImpl(indexReferenceInformationField, Optional.of(creditIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indexReferenceInformationField, Optional.empty()));
			FieldValue basketReferenceInformation = Optional.ofNullable(input.getBasketReferenceInformation())
				.map(x -> new NestedFieldValueImpl(basketReferenceInformationField, Optional.of(basketReferenceInformationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(basketReferenceInformationField, Optional.empty()));
			FieldValue additionalTerm = new FieldValueImpl(additionalTermField, Optional.ofNullable(input.getAdditionalTerm())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue substitution = new FieldValueImpl(substitutionField, Optional.ofNullable(input.getSubstitution()));
			FieldValue modifiedEquityDelivery = new FieldValueImpl(modifiedEquityDeliveryField, Optional.ofNullable(input.getModifiedEquityDelivery()));
			return Arrays.asList(
				referenceInformation,
				indexReferenceInformation,
				basketReferenceInformation,
				additionalTerm,
				substitution,
				modifiedEquityDelivery
			);
		}
	}
}
