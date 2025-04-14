package cdm.product.asset.tabulator;

import cdm.product.asset.ReferencePoolItem;
import cdm.product.common.settlement.tabulator.CashSettlementTermsTypeTabulator;
import cdm.product.common.settlement.tabulator.PhysicalSettlementTermsTypeTabulator;
import cdm.product.template.tabulator.ConstituentWeightTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ReferencePoolItemTypeTabulator.Impl.class)
public interface ReferencePoolItemTypeTabulator extends Tabulator<ReferencePoolItem> {
	@Singleton
	class Impl implements ReferencePoolItemTypeTabulator {
		private final Field constituentWeightField;
		private final Field referencePairField;
		private final Field protectionTermsReferenceField;
		private final Field cashSettlementTermsReferenceField;
		private final Field physicalSettlementTermsReferenceField;

		private final ConstituentWeightTypeTabulator constituentWeightTypeTabulator;
		private final ReferencePairTypeTabulator referencePairTypeTabulator;
		private final ProtectionTermsTypeTabulator protectionTermsTypeTabulator;
		private final CashSettlementTermsTypeTabulator cashSettlementTermsTypeTabulator;
		private final PhysicalSettlementTermsTypeTabulator physicalSettlementTermsTypeTabulator;

		@Inject
		public Impl(ConstituentWeightTypeTabulator constituentWeightTypeTabulator, ReferencePairTypeTabulator referencePairTypeTabulator, ProtectionTermsTypeTabulator protectionTermsTypeTabulator, CashSettlementTermsTypeTabulator cashSettlementTermsTypeTabulator, PhysicalSettlementTermsTypeTabulator physicalSettlementTermsTypeTabulator) {
			this.constituentWeightTypeTabulator = constituentWeightTypeTabulator;
			this.referencePairTypeTabulator = referencePairTypeTabulator;
			this.protectionTermsTypeTabulator = protectionTermsTypeTabulator;
			this.cashSettlementTermsTypeTabulator = cashSettlementTermsTypeTabulator;
			this.physicalSettlementTermsTypeTabulator = physicalSettlementTermsTypeTabulator;
			this.constituentWeightField = new FieldImpl(
				"constituentWeight",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referencePairField = new FieldImpl(
				"referencePair",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.protectionTermsReferenceField = new FieldImpl(
				"protectionTermsReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementTermsReferenceField = new FieldImpl(
				"cashSettlementTermsReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.physicalSettlementTermsReferenceField = new FieldImpl(
				"physicalSettlementTermsReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferencePoolItem input) {
			FieldValue constituentWeight = Optional.ofNullable(input.getConstituentWeight())
				.map(x -> new NestedFieldValueImpl(constituentWeightField, Optional.of(constituentWeightTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(constituentWeightField, Optional.empty()));
			FieldValue referencePair = Optional.ofNullable(input.getReferencePair())
				.map(x -> new NestedFieldValueImpl(referencePairField, Optional.of(referencePairTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referencePairField, Optional.empty()));
			FieldValue protectionTermsReference = Optional.ofNullable(input.getProtectionTermsReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(protectionTermsReferenceField, Optional.of(protectionTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(protectionTermsReferenceField, Optional.empty()));
			FieldValue cashSettlementTermsReference = Optional.ofNullable(input.getCashSettlementTermsReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(cashSettlementTermsReferenceField, Optional.of(cashSettlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashSettlementTermsReferenceField, Optional.empty()));
			FieldValue physicalSettlementTermsReference = Optional.ofNullable(input.getPhysicalSettlementTermsReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(physicalSettlementTermsReferenceField, Optional.of(physicalSettlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(physicalSettlementTermsReferenceField, Optional.empty()));
			return Arrays.asList(
				constituentWeight,
				referencePair,
				protectionTermsReference,
				cashSettlementTermsReference,
				physicalSettlementTermsReference
			);
		}
	}
}
