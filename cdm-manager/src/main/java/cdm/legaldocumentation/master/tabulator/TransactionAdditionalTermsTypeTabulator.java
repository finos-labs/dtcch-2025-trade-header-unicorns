package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.TransactionAdditionalTerms;
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


@ImplementedBy(TransactionAdditionalTermsTypeTabulator.Impl.class)
public interface TransactionAdditionalTermsTypeTabulator extends Tabulator<TransactionAdditionalTerms> {
	@Singleton
	class Impl implements TransactionAdditionalTermsTypeTabulator {
		private final Field equityAdditionalTermsField;
		private final Field foreignExchangeAdditionalTermsField;
		private final Field commoditiesAdditionalTermsField;
		private final Field creditAdditionalTermsField;
		private final Field interestRateAdditionalTermsField;
		private final Field digitalAssetAdditionalTermsField;

		private final EquityAdditionalTermsTypeTabulator equityAdditionalTermsTypeTabulator;
		private final FxAdditionalTermsTypeTabulator fxAdditionalTermsTypeTabulator;

		@Inject
		public Impl(EquityAdditionalTermsTypeTabulator equityAdditionalTermsTypeTabulator, FxAdditionalTermsTypeTabulator fxAdditionalTermsTypeTabulator) {
			this.equityAdditionalTermsTypeTabulator = equityAdditionalTermsTypeTabulator;
			this.fxAdditionalTermsTypeTabulator = fxAdditionalTermsTypeTabulator;
			this.equityAdditionalTermsField = new FieldImpl(
				"equityAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.foreignExchangeAdditionalTermsField = new FieldImpl(
				"foreignExchangeAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.commoditiesAdditionalTermsField = new FieldImpl(
				"commoditiesAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.creditAdditionalTermsField = new FieldImpl(
				"creditAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interestRateAdditionalTermsField = new FieldImpl(
				"interestRateAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.digitalAssetAdditionalTermsField = new FieldImpl(
				"digitalAssetAdditionalTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TransactionAdditionalTerms input) {
			FieldValue equityAdditionalTerms = Optional.ofNullable(input.getEquityAdditionalTerms())
				.map(x -> new NestedFieldValueImpl(equityAdditionalTermsField, Optional.of(equityAdditionalTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(equityAdditionalTermsField, Optional.empty()));
			FieldValue foreignExchangeAdditionalTerms = Optional.ofNullable(input.getForeignExchangeAdditionalTerms())
				.map(x -> new NestedFieldValueImpl(foreignExchangeAdditionalTermsField, Optional.of(fxAdditionalTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(foreignExchangeAdditionalTermsField, Optional.empty()));
			FieldValue commoditiesAdditionalTerms = new FieldValueImpl(commoditiesAdditionalTermsField, Optional.ofNullable(input.getCommoditiesAdditionalTerms()));
			FieldValue creditAdditionalTerms = new FieldValueImpl(creditAdditionalTermsField, Optional.ofNullable(input.getCreditAdditionalTerms()));
			FieldValue interestRateAdditionalTerms = new FieldValueImpl(interestRateAdditionalTermsField, Optional.ofNullable(input.getInterestRateAdditionalTerms()));
			FieldValue digitalAssetAdditionalTerms = new FieldValueImpl(digitalAssetAdditionalTermsField, Optional.ofNullable(input.getDigitalAssetAdditionalTerms()));
			return Arrays.asList(
				equityAdditionalTerms,
				foreignExchangeAdditionalTerms,
				commoditiesAdditionalTerms,
				creditAdditionalTerms,
				interestRateAdditionalTerms,
				digitalAssetAdditionalTerms
			);
		}
	}
}
