package cdm.product.asset.tabulator;

import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.asset.ReferenceInformation;
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


@ImplementedBy(ReferenceInformationTypeTabulator.Impl.class)
public interface ReferenceInformationTypeTabulator extends Tabulator<ReferenceInformation> {
	@Singleton
	class Impl implements ReferenceInformationTypeTabulator {
		private final Field referenceEntityField;
		private final Field referenceObligationField;
		private final Field noReferenceObligationField;
		private final Field unknownReferenceObligationField;
		private final Field allGuaranteesField;
		private final Field referencePriceField;
		private final Field referencePolicyField;
		private final Field securedListField;

		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final ReferenceObligationTypeTabulator referenceObligationTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(LegalEntityTypeTabulator legalEntityTypeTabulator, ReferenceObligationTypeTabulator referenceObligationTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.referenceObligationTypeTabulator = referenceObligationTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.referenceEntityField = new FieldImpl(
				"referenceEntity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceObligationField = new FieldImpl(
				"referenceObligation",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noReferenceObligationField = new FieldImpl(
				"noReferenceObligation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unknownReferenceObligationField = new FieldImpl(
				"unknownReferenceObligation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.allGuaranteesField = new FieldImpl(
				"allGuarantees",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referencePriceField = new FieldImpl(
				"referencePrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referencePolicyField = new FieldImpl(
				"referencePolicy",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.securedListField = new FieldImpl(
				"securedList",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferenceInformation input) {
			FieldValue referenceEntity = Optional.ofNullable(input.getReferenceEntity())
				.map(x -> new NestedFieldValueImpl(referenceEntityField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceEntityField, Optional.empty()));
			FieldValue referenceObligation = Optional.ofNullable(input.getReferenceObligation())
				.map(x -> x.stream()
					.map(_x -> referenceObligationTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(referenceObligationField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(referenceObligationField, Optional.empty()));
			FieldValue noReferenceObligation = new FieldValueImpl(noReferenceObligationField, Optional.ofNullable(input.getNoReferenceObligation()));
			FieldValue unknownReferenceObligation = new FieldValueImpl(unknownReferenceObligationField, Optional.ofNullable(input.getUnknownReferenceObligation()));
			FieldValue allGuarantees = new FieldValueImpl(allGuaranteesField, Optional.ofNullable(input.getAllGuarantees()));
			FieldValue referencePrice = Optional.ofNullable(input.getReferencePrice())
				.map(x -> new NestedFieldValueImpl(referencePriceField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referencePriceField, Optional.empty()));
			FieldValue referencePolicy = new FieldValueImpl(referencePolicyField, Optional.ofNullable(input.getReferencePolicy()));
			FieldValue securedList = new FieldValueImpl(securedListField, Optional.ofNullable(input.getSecuredList()));
			return Arrays.asList(
				referenceEntity,
				referenceObligation,
				noReferenceObligation,
				unknownReferenceObligation,
				allGuarantees,
				referencePrice,
				referencePolicy,
				securedList
			);
		}
	}
}
