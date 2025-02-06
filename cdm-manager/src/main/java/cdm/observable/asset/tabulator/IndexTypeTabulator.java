package cdm.observable.asset.tabulator;

import cdm.observable.asset.Index;
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


@ImplementedBy(IndexTypeTabulator.Impl.class)
public interface IndexTypeTabulator extends Tabulator<Index> {
	@Singleton
	class Impl implements IndexTypeTabulator {
		private final Field CreditIndexField;
		private final Field EquityIndexField;
		private final Field InterestRateIndexField;
		private final Field ForeignExchangeRateIndexField;
		private final Field OtherIndexField;

		private final CreditIndexTypeTabulator creditIndexTypeTabulator;
		private final EquityIndexTypeTabulator equityIndexTypeTabulator;
		private final InterestRateIndexTypeTabulator interestRateIndexTypeTabulator;
		private final ForeignExchangeRateIndexTypeTabulator foreignExchangeRateIndexTypeTabulator;
		private final OtherIndexTypeTabulator otherIndexTypeTabulator;

		@Inject
		public Impl(CreditIndexTypeTabulator creditIndexTypeTabulator, EquityIndexTypeTabulator equityIndexTypeTabulator, InterestRateIndexTypeTabulator interestRateIndexTypeTabulator, ForeignExchangeRateIndexTypeTabulator foreignExchangeRateIndexTypeTabulator, OtherIndexTypeTabulator otherIndexTypeTabulator) {
			this.creditIndexTypeTabulator = creditIndexTypeTabulator;
			this.equityIndexTypeTabulator = equityIndexTypeTabulator;
			this.interestRateIndexTypeTabulator = interestRateIndexTypeTabulator;
			this.foreignExchangeRateIndexTypeTabulator = foreignExchangeRateIndexTypeTabulator;
			this.otherIndexTypeTabulator = otherIndexTypeTabulator;
			this.CreditIndexField = new FieldImpl(
				"CreditIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.EquityIndexField = new FieldImpl(
				"EquityIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.InterestRateIndexField = new FieldImpl(
				"InterestRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ForeignExchangeRateIndexField = new FieldImpl(
				"ForeignExchangeRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.OtherIndexField = new FieldImpl(
				"OtherIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Index input) {
			FieldValue CreditIndex = Optional.ofNullable(input.getCreditIndex())
				.map(x -> new NestedFieldValueImpl(CreditIndexField, Optional.of(creditIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(CreditIndexField, Optional.empty()));
			FieldValue EquityIndex = Optional.ofNullable(input.getEquityIndex())
				.map(x -> new NestedFieldValueImpl(EquityIndexField, Optional.of(equityIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(EquityIndexField, Optional.empty()));
			FieldValue InterestRateIndex = Optional.ofNullable(input.getInterestRateIndex())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(InterestRateIndexField, Optional.of(interestRateIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(InterestRateIndexField, Optional.empty()));
			FieldValue ForeignExchangeRateIndex = Optional.ofNullable(input.getForeignExchangeRateIndex())
				.map(x -> new NestedFieldValueImpl(ForeignExchangeRateIndexField, Optional.of(foreignExchangeRateIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ForeignExchangeRateIndexField, Optional.empty()));
			FieldValue OtherIndex = Optional.ofNullable(input.getOtherIndex())
				.map(x -> new NestedFieldValueImpl(OtherIndexField, Optional.of(otherIndexTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(OtherIndexField, Optional.empty()));
			return Arrays.asList(
				CreditIndex,
				EquityIndex,
				InterestRateIndex,
				ForeignExchangeRateIndex,
				OtherIndex
			);
		}
	}
}
