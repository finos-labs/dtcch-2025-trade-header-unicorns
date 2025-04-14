package cdm.product.common.settlement.tabulator;

import cdm.product.common.settlement.SettlementTerms;
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


@ImplementedBy(SettlementTermsTypeTabulator.Impl.class)
public interface SettlementTermsTypeTabulator extends Tabulator<SettlementTerms> {
	@Singleton
	class Impl implements SettlementTermsTypeTabulator {
		private final Field settlementTypeField;
		private final Field transferSettlementTypeField;
		private final Field settlementCurrencyField;
		private final Field settlementDateField;
		private final Field settlementCentreField;
		private final Field settlementProvisionField;
		private final Field standardSettlementStyleField;
		private final Field cashSettlementTermsField;
		private final Field physicalSettlementTermsField;

		private final SettlementDateTypeTabulator settlementDateTypeTabulator;
		private final SettlementProvisionTypeTabulator settlementProvisionTypeTabulator;
		private final CashSettlementTermsTypeTabulator cashSettlementTermsTypeTabulator;
		private final PhysicalSettlementTermsTypeTabulator physicalSettlementTermsTypeTabulator;

		@Inject
		public Impl(SettlementDateTypeTabulator settlementDateTypeTabulator, SettlementProvisionTypeTabulator settlementProvisionTypeTabulator, CashSettlementTermsTypeTabulator cashSettlementTermsTypeTabulator, PhysicalSettlementTermsTypeTabulator physicalSettlementTermsTypeTabulator) {
			this.settlementDateTypeTabulator = settlementDateTypeTabulator;
			this.settlementProvisionTypeTabulator = settlementProvisionTypeTabulator;
			this.cashSettlementTermsTypeTabulator = cashSettlementTermsTypeTabulator;
			this.physicalSettlementTermsTypeTabulator = physicalSettlementTermsTypeTabulator;
			this.settlementTypeField = new FieldImpl(
				"settlementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferSettlementTypeField = new FieldImpl(
				"transferSettlementType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementCurrencyField = new FieldImpl(
				"settlementCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementDateField = new FieldImpl(
				"settlementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementCentreField = new FieldImpl(
				"settlementCentre",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementProvisionField = new FieldImpl(
				"settlementProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.standardSettlementStyleField = new FieldImpl(
				"standardSettlementStyle",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementTermsField = new FieldImpl(
				"cashSettlementTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.physicalSettlementTermsField = new FieldImpl(
				"physicalSettlementTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettlementTerms input) {
			FieldValue settlementType = new FieldValueImpl(settlementTypeField, Optional.ofNullable(input.getSettlementType()));
			FieldValue transferSettlementType = new FieldValueImpl(transferSettlementTypeField, Optional.ofNullable(input.getTransferSettlementType()));
			FieldValue settlementCurrency = new FieldValueImpl(settlementCurrencyField, Optional.ofNullable(input.getSettlementCurrency())
				.map(x -> x.getValue()));
			FieldValue settlementDate = Optional.ofNullable(input.getSettlementDate())
				.map(x -> new NestedFieldValueImpl(settlementDateField, Optional.of(settlementDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementDateField, Optional.empty()));
			FieldValue settlementCentre = new FieldValueImpl(settlementCentreField, Optional.ofNullable(input.getSettlementCentre()));
			FieldValue settlementProvision = Optional.ofNullable(input.getSettlementProvision())
				.map(x -> new NestedFieldValueImpl(settlementProvisionField, Optional.of(settlementProvisionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementProvisionField, Optional.empty()));
			FieldValue standardSettlementStyle = new FieldValueImpl(standardSettlementStyleField, Optional.ofNullable(input.getStandardSettlementStyle()));
			FieldValue cashSettlementTerms = Optional.ofNullable(input.getCashSettlementTerms())
				.map(x -> x.stream()
					.map(_x -> cashSettlementTermsTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(cashSettlementTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(cashSettlementTermsField, Optional.empty()));
			FieldValue physicalSettlementTerms = Optional.ofNullable(input.getPhysicalSettlementTerms())
				.map(x -> new NestedFieldValueImpl(physicalSettlementTermsField, Optional.of(physicalSettlementTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(physicalSettlementTermsField, Optional.empty()));
			return Arrays.asList(
				settlementType,
				transferSettlementType,
				settlementCurrency,
				settlementDate,
				settlementCentre,
				settlementProvision,
				standardSettlementStyle,
				cashSettlementTerms,
				physicalSettlementTerms
			);
		}
	}
}
