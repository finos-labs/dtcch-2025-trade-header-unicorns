package cdm.observable.asset.tabulator;

import cdm.base.staticdata.party.tabulator.AncillaryEntityTypeTabulator;
import cdm.base.staticdata.party.tabulator.ReferenceBanksTypeTabulator;
import cdm.observable.asset.ValuationSource;
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


@ImplementedBy(ValuationSourceTypeTabulator.Impl.class)
public interface ValuationSourceTypeTabulator extends Tabulator<ValuationSource> {
	@Singleton
	class Impl implements ValuationSourceTypeTabulator {
		private final Field quotedCurrencyPairField;
		private final Field informationSourceField;
		private final Field settlementRateOptionField;
		private final Field referenceBanksField;
		private final Field dealerOrCCPField;

		private final QuotedCurrencyPairTypeTabulator quotedCurrencyPairTypeTabulator;
		private final FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator;
		private final SettlementRateOptionTypeTabulator settlementRateOptionTypeTabulator;
		private final ReferenceBanksTypeTabulator referenceBanksTypeTabulator;
		private final AncillaryEntityTypeTabulator ancillaryEntityTypeTabulator;

		@Inject
		public Impl(QuotedCurrencyPairTypeTabulator quotedCurrencyPairTypeTabulator, FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator, SettlementRateOptionTypeTabulator settlementRateOptionTypeTabulator, ReferenceBanksTypeTabulator referenceBanksTypeTabulator, AncillaryEntityTypeTabulator ancillaryEntityTypeTabulator) {
			this.quotedCurrencyPairTypeTabulator = quotedCurrencyPairTypeTabulator;
			this.fxSpotRateSourceTypeTabulator = fxSpotRateSourceTypeTabulator;
			this.settlementRateOptionTypeTabulator = settlementRateOptionTypeTabulator;
			this.referenceBanksTypeTabulator = referenceBanksTypeTabulator;
			this.ancillaryEntityTypeTabulator = ancillaryEntityTypeTabulator;
			this.quotedCurrencyPairField = new FieldImpl(
				"quotedCurrencyPair",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.informationSourceField = new FieldImpl(
				"informationSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementRateOptionField = new FieldImpl(
				"settlementRateOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referenceBanksField = new FieldImpl(
				"referenceBanks",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dealerOrCCPField = new FieldImpl(
				"dealerOrCCP",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationSource input) {
			FieldValue quotedCurrencyPair = Optional.ofNullable(input.getQuotedCurrencyPair())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(quotedCurrencyPairField, Optional.of(quotedCurrencyPairTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quotedCurrencyPairField, Optional.empty()));
			FieldValue informationSource = Optional.ofNullable(input.getInformationSource())
				.map(x -> new NestedFieldValueImpl(informationSourceField, Optional.of(fxSpotRateSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(informationSourceField, Optional.empty()));
			FieldValue settlementRateOption = Optional.ofNullable(input.getSettlementRateOption())
				.map(x -> new NestedFieldValueImpl(settlementRateOptionField, Optional.of(settlementRateOptionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementRateOptionField, Optional.empty()));
			FieldValue referenceBanks = Optional.ofNullable(input.getReferenceBanks())
				.map(x -> new NestedFieldValueImpl(referenceBanksField, Optional.of(referenceBanksTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referenceBanksField, Optional.empty()));
			FieldValue dealerOrCCP = Optional.ofNullable(input.getDealerOrCCP())
				.map(x -> new NestedFieldValueImpl(dealerOrCCPField, Optional.of(ancillaryEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dealerOrCCPField, Optional.empty()));
			return Arrays.asList(
				quotedCurrencyPair,
				informationSource,
				settlementRateOption,
				referenceBanks,
				dealerOrCCP
			);
		}
	}
}
