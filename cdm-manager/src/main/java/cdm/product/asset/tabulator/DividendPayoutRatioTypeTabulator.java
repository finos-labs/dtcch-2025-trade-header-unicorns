package cdm.product.asset.tabulator;

import cdm.observable.asset.tabulator.BasketConstituentTypeTabulator;
import cdm.product.asset.DividendPayoutRatio;
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


@ImplementedBy(DividendPayoutRatioTypeTabulator.Impl.class)
public interface DividendPayoutRatioTypeTabulator extends Tabulator<DividendPayoutRatio> {
	@Singleton
	class Impl implements DividendPayoutRatioTypeTabulator {
		private final Field totalRatioField;
		private final Field cashRatioField;
		private final Field nonCashRatioField;
		private final Field basketConstituentField;

		private final BasketConstituentTypeTabulator basketConstituentTypeTabulator;

		@Inject
		public Impl(BasketConstituentTypeTabulator basketConstituentTypeTabulator) {
			this.basketConstituentTypeTabulator = basketConstituentTypeTabulator;
			this.totalRatioField = new FieldImpl(
				"totalRatio",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashRatioField = new FieldImpl(
				"cashRatio",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nonCashRatioField = new FieldImpl(
				"nonCashRatio",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.basketConstituentField = new FieldImpl(
				"basketConstituent",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendPayoutRatio input) {
			FieldValue totalRatio = new FieldValueImpl(totalRatioField, Optional.ofNullable(input.getTotalRatio()));
			FieldValue cashRatio = new FieldValueImpl(cashRatioField, Optional.ofNullable(input.getCashRatio()));
			FieldValue nonCashRatio = new FieldValueImpl(nonCashRatioField, Optional.ofNullable(input.getNonCashRatio()));
			FieldValue basketConstituent = Optional.ofNullable(input.getBasketConstituent())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(basketConstituentField, Optional.of(basketConstituentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(basketConstituentField, Optional.empty()));
			return Arrays.asList(
				totalRatio,
				cashRatio,
				nonCashRatio,
				basketConstituent
			);
		}
	}
}
