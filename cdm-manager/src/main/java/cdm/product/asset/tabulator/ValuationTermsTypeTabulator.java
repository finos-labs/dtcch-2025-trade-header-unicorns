package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.product.asset.ValuationTerms;
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


@ImplementedBy(ValuationTermsTypeTabulator.Impl.class)
public interface ValuationTermsTypeTabulator extends Tabulator<ValuationTerms> {
	@Singleton
	class Impl implements ValuationTermsTypeTabulator {
		private final Field futuresPriceValuationField;
		private final Field optionsPriceValuationField;
		private final Field numberOfValuationDatesField;
		private final Field dividendValuationDatesField;
		private final Field fPVFinalPriceElectionFallbackField;
		private final Field multipleExchangeIndexAnnexFallbackField;
		private final Field componentSecurityIndexAnnexFallbackField;

		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;

		@Inject
		public Impl(AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator) {
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
			this.futuresPriceValuationField = new FieldImpl(
				"futuresPriceValuation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.optionsPriceValuationField = new FieldImpl(
				"optionsPriceValuation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.numberOfValuationDatesField = new FieldImpl(
				"numberOfValuationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendValuationDatesField = new FieldImpl(
				"dividendValuationDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fPVFinalPriceElectionFallbackField = new FieldImpl(
				"fPVFinalPriceElectionFallback",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.multipleExchangeIndexAnnexFallbackField = new FieldImpl(
				"multipleExchangeIndexAnnexFallback",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.componentSecurityIndexAnnexFallbackField = new FieldImpl(
				"componentSecurityIndexAnnexFallback",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ValuationTerms input) {
			FieldValue futuresPriceValuation = new FieldValueImpl(futuresPriceValuationField, Optional.ofNullable(input.getFuturesPriceValuation()));
			FieldValue optionsPriceValuation = new FieldValueImpl(optionsPriceValuationField, Optional.ofNullable(input.getOptionsPriceValuation()));
			FieldValue numberOfValuationDates = new FieldValueImpl(numberOfValuationDatesField, Optional.ofNullable(input.getNumberOfValuationDates()));
			FieldValue dividendValuationDates = Optional.ofNullable(input.getDividendValuationDates())
				.map(x -> new NestedFieldValueImpl(dividendValuationDatesField, Optional.of(adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendValuationDatesField, Optional.empty()));
			FieldValue fPVFinalPriceElectionFallback = new FieldValueImpl(fPVFinalPriceElectionFallbackField, Optional.ofNullable(input.getFPVFinalPriceElectionFallback()));
			FieldValue multipleExchangeIndexAnnexFallback = new FieldValueImpl(multipleExchangeIndexAnnexFallbackField, Optional.ofNullable(input.getMultipleExchangeIndexAnnexFallback()));
			FieldValue componentSecurityIndexAnnexFallback = new FieldValueImpl(componentSecurityIndexAnnexFallbackField, Optional.ofNullable(input.getComponentSecurityIndexAnnexFallback()));
			return Arrays.asList(
				futuresPriceValuation,
				optionsPriceValuation,
				numberOfValuationDates,
				dividendValuationDates,
				fPVFinalPriceElectionFallback,
				multipleExchangeIndexAnnexFallback,
				componentSecurityIndexAnnexFallback
			);
		}
	}
}
