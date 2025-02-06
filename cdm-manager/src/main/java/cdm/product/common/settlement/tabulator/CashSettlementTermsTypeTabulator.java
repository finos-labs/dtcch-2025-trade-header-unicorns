package cdm.product.common.settlement.tabulator;

import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
import cdm.observable.asset.tabulator.ValuationMethodTypeTabulator;
import cdm.product.common.settlement.CashSettlementTerms;
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


@ImplementedBy(CashSettlementTermsTypeTabulator.Impl.class)
public interface CashSettlementTermsTypeTabulator extends Tabulator<CashSettlementTerms> {
	@Singleton
	class Impl implements CashSettlementTermsTypeTabulator {
		private final Field cashSettlementMethodField;
		private final Field valuationMethodField;
		private final Field valuationDateField;
		private final Field valuationTimeField;
		private final Field cashSettlementAmountField;
		private final Field recoveryFactorField;
		private final Field fixedSettlementField;
		private final Field accruedInterestField;

		private final ValuationMethodTypeTabulator valuationMethodTypeTabulator;
		private final ValuationDateTypeTabulator valuationDateTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;
		private final MoneyTypeTabulator moneyTypeTabulator;

		@Inject
		public Impl(ValuationMethodTypeTabulator valuationMethodTypeTabulator, ValuationDateTypeTabulator valuationDateTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator, MoneyTypeTabulator moneyTypeTabulator) {
			this.valuationMethodTypeTabulator = valuationMethodTypeTabulator;
			this.valuationDateTypeTabulator = valuationDateTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.cashSettlementMethodField = new FieldImpl(
				"cashSettlementMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationMethodField = new FieldImpl(
				"valuationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationDateField = new FieldImpl(
				"valuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.valuationTimeField = new FieldImpl(
				"valuationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashSettlementAmountField = new FieldImpl(
				"cashSettlementAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.recoveryFactorField = new FieldImpl(
				"recoveryFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixedSettlementField = new FieldImpl(
				"fixedSettlement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.accruedInterestField = new FieldImpl(
				"accruedInterest",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CashSettlementTerms input) {
			FieldValue cashSettlementMethod = new FieldValueImpl(cashSettlementMethodField, Optional.ofNullable(input.getCashSettlementMethod()));
			FieldValue valuationMethod = Optional.ofNullable(input.getValuationMethod())
				.map(x -> new NestedFieldValueImpl(valuationMethodField, Optional.of(valuationMethodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationMethodField, Optional.empty()));
			FieldValue valuationDate = Optional.ofNullable(input.getValuationDate())
				.map(x -> new NestedFieldValueImpl(valuationDateField, Optional.of(valuationDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationDateField, Optional.empty()));
			FieldValue valuationTime = Optional.ofNullable(input.getValuationTime())
				.map(x -> new NestedFieldValueImpl(valuationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(valuationTimeField, Optional.empty()));
			FieldValue cashSettlementAmount = Optional.ofNullable(input.getCashSettlementAmount())
				.map(x -> new NestedFieldValueImpl(cashSettlementAmountField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashSettlementAmountField, Optional.empty()));
			FieldValue recoveryFactor = new FieldValueImpl(recoveryFactorField, Optional.ofNullable(input.getRecoveryFactor()));
			FieldValue fixedSettlement = new FieldValueImpl(fixedSettlementField, Optional.ofNullable(input.getFixedSettlement()));
			FieldValue accruedInterest = new FieldValueImpl(accruedInterestField, Optional.ofNullable(input.getAccruedInterest()));
			return Arrays.asList(
				cashSettlementMethod,
				valuationMethod,
				valuationDate,
				valuationTime,
				cashSettlementAmount,
				recoveryFactor,
				fixedSettlement,
				accruedInterest
			);
		}
	}
}
