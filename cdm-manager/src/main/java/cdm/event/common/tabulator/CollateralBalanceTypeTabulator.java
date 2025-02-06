package cdm.event.common.tabulator;

import cdm.base.staticdata.party.tabulator.PartyReferencePayerReceiverTypeTabulator;
import cdm.event.common.CollateralBalance;
import cdm.observable.asset.tabulator.MoneyTypeTabulator;
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


@ImplementedBy(CollateralBalanceTypeTabulator.Impl.class)
public interface CollateralBalanceTypeTabulator extends Tabulator<CollateralBalance> {
	@Singleton
	class Impl implements CollateralBalanceTypeTabulator {
		private final Field collateralBalanceStatusField;
		private final Field haircutIndicatorField;
		private final Field amountBaseCurrencyField;
		private final Field payerReceiverField;

		private final MoneyTypeTabulator moneyTypeTabulator;
		private final PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator;

		@Inject
		public Impl(MoneyTypeTabulator moneyTypeTabulator, PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator) {
			this.moneyTypeTabulator = moneyTypeTabulator;
			this.partyReferencePayerReceiverTypeTabulator = partyReferencePayerReceiverTypeTabulator;
			this.collateralBalanceStatusField = new FieldImpl(
				"collateralBalanceStatus",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.haircutIndicatorField = new FieldImpl(
				"haircutIndicator",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.amountBaseCurrencyField = new FieldImpl(
				"amountBaseCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralBalance input) {
			FieldValue collateralBalanceStatus = new FieldValueImpl(collateralBalanceStatusField, Optional.ofNullable(input.getCollateralBalanceStatus()));
			FieldValue haircutIndicator = new FieldValueImpl(haircutIndicatorField, Optional.ofNullable(input.getHaircutIndicator()));
			FieldValue amountBaseCurrency = Optional.ofNullable(input.getAmountBaseCurrency())
				.map(x -> new NestedFieldValueImpl(amountBaseCurrencyField, Optional.of(moneyTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(amountBaseCurrencyField, Optional.empty()));
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(partyReferencePayerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			return Arrays.asList(
				collateralBalanceStatus,
				haircutIndicator,
				amountBaseCurrency,
				payerReceiver
			);
		}
	}
}
