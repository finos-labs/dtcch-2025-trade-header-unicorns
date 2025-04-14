package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.PhysicalSettlementTerms;
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


@ImplementedBy(PhysicalSettlementTermsTypeTabulator.Impl.class)
public interface PhysicalSettlementTermsTypeTabulator extends Tabulator<PhysicalSettlementTerms> {
	@Singleton
	class Impl implements PhysicalSettlementTermsTypeTabulator {
		private final Field clearedPhysicalSettlementField;
		private final Field predeterminedClearingOrganizationPartyField;
		private final Field physicalSettlementPeriodField;
		private final Field deliverableObligationsField;
		private final Field escrowField;
		private final Field sixtyBusinessDaySettlementCapField;

		private final PhysicalSettlementPeriodTypeTabulator physicalSettlementPeriodTypeTabulator;
		private final DeliverableObligationsTypeTabulator deliverableObligationsTypeTabulator;

		@Inject
		public Impl(PhysicalSettlementPeriodTypeTabulator physicalSettlementPeriodTypeTabulator, DeliverableObligationsTypeTabulator deliverableObligationsTypeTabulator) {
			this.physicalSettlementPeriodTypeTabulator = physicalSettlementPeriodTypeTabulator;
			this.deliverableObligationsTypeTabulator = deliverableObligationsTypeTabulator;
			this.clearedPhysicalSettlementField = new FieldImpl(
				"clearedPhysicalSettlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.predeterminedClearingOrganizationPartyField = new FieldImpl(
				"predeterminedClearingOrganizationParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.physicalSettlementPeriodField = new FieldImpl(
				"physicalSettlementPeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliverableObligationsField = new FieldImpl(
				"deliverableObligations",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.escrowField = new FieldImpl(
				"escrow",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sixtyBusinessDaySettlementCapField = new FieldImpl(
				"sixtyBusinessDaySettlementCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PhysicalSettlementTerms input) {
			FieldValue clearedPhysicalSettlement = new FieldValueImpl(clearedPhysicalSettlementField, Optional.ofNullable(input.getClearedPhysicalSettlement()));
			FieldValue predeterminedClearingOrganizationParty = new FieldValueImpl(predeterminedClearingOrganizationPartyField, Optional.ofNullable(input.getPredeterminedClearingOrganizationParty()));
			FieldValue physicalSettlementPeriod = Optional.ofNullable(input.getPhysicalSettlementPeriod())
				.map(x -> new NestedFieldValueImpl(physicalSettlementPeriodField, Optional.of(physicalSettlementPeriodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(physicalSettlementPeriodField, Optional.empty()));
			FieldValue deliverableObligations = Optional.ofNullable(input.getDeliverableObligations())
				.map(x -> new NestedFieldValueImpl(deliverableObligationsField, Optional.of(deliverableObligationsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(deliverableObligationsField, Optional.empty()));
			FieldValue escrow = new FieldValueImpl(escrowField, Optional.ofNullable(input.getEscrow()));
			FieldValue sixtyBusinessDaySettlementCap = new FieldValueImpl(sixtyBusinessDaySettlementCapField, Optional.ofNullable(input.getSixtyBusinessDaySettlementCap()));
			return Arrays.asList(
				clearedPhysicalSettlement,
				predeterminedClearingOrganizationParty,
				physicalSettlementPeriod,
				deliverableObligations,
				escrow,
				sixtyBusinessDaySettlementCap
			);
		}
	}
}
