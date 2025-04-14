package cdm.product.asset.tabulator;

import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.product.asset.ReferencePair;
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


@ImplementedBy(ReferencePairTypeTabulator.Impl.class)
public interface ReferencePairTypeTabulator extends Tabulator<ReferencePair> {
	@Singleton
	class Impl implements ReferencePairTypeTabulator {
		private final Field referenceEntityField;
		private final Field referenceObligationField;
		private final Field noReferenceObligationField;
		private final Field entityTypeField;

		private final LegalEntityTypeTabulator legalEntityTypeTabulator;
		private final ReferenceObligationTypeTabulator referenceObligationTypeTabulator;

		@Inject
		public Impl(LegalEntityTypeTabulator legalEntityTypeTabulator, ReferenceObligationTypeTabulator referenceObligationTypeTabulator) {
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.referenceObligationTypeTabulator = referenceObligationTypeTabulator;
			this.referenceEntityField = new FieldImpl(
				"referenceEntity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceObligationField = new FieldImpl(
				"referenceObligation",
				false,
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
			this.entityTypeField = new FieldImpl(
				"entityType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferencePair input) {
			FieldValue referenceEntity = Optional.ofNullable(input.getReferenceEntity())
				.map(x -> new NestedFieldValueImpl(referenceEntityField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceEntityField, Optional.empty()));
			FieldValue referenceObligation = Optional.ofNullable(input.getReferenceObligation())
				.map(x -> new NestedFieldValueImpl(referenceObligationField, Optional.of(referenceObligationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceObligationField, Optional.empty()));
			FieldValue noReferenceObligation = new FieldValueImpl(noReferenceObligationField, Optional.ofNullable(input.getNoReferenceObligation()));
			FieldValue entityType = new FieldValueImpl(entityTypeField, Optional.ofNullable(input.getEntityType())
				.map(x -> x.getValue()));
			return Arrays.asList(
				referenceEntity,
				referenceObligation,
				noReferenceObligation,
				entityType
			);
		}
	}
}
