package cdm.product.asset.tabulator;

import cdm.product.asset.InterestShortFall;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(InterestShortFallTypeTabulator.Impl.class)
public interface InterestShortFallTypeTabulator extends Tabulator<InterestShortFall> {
	@Singleton
	class Impl implements InterestShortFallTypeTabulator {
		private final Field interestShortfallCapField;
		private final Field compoundingField;
		private final Field rateSourceField;

		public Impl() {
			this.interestShortfallCapField = new FieldImpl(
				"interestShortfallCap",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.compoundingField = new FieldImpl(
				"compounding",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateSourceField = new FieldImpl(
				"rateSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InterestShortFall input) {
			FieldValue interestShortfallCap = new FieldValueImpl(interestShortfallCapField, Optional.ofNullable(input.getInterestShortfallCap()));
			FieldValue compounding = new FieldValueImpl(compoundingField, Optional.ofNullable(input.getCompounding()));
			FieldValue rateSource = new FieldValueImpl(rateSourceField, Optional.ofNullable(input.getRateSource())
				.map(x -> x.getValue()));
			return Arrays.asList(
				interestShortfallCap,
				compounding,
				rateSource
			);
		}
	}
}
