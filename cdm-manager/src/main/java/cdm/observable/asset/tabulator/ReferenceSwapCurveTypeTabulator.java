package cdm.observable.asset.tabulator;

import cdm.observable.asset.ReferenceSwapCurve;
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


@ImplementedBy(ReferenceSwapCurveTypeTabulator.Impl.class)
public interface ReferenceSwapCurveTypeTabulator extends Tabulator<ReferenceSwapCurve> {
	@Singleton
	class Impl implements ReferenceSwapCurveTypeTabulator {
		private final Field swapUnwindValueField;
		private final Field makeWholeAmountField;

		private final SwapCurveValuationTypeTabulator swapCurveValuationTypeTabulator;
		private final MakeWholeAmountTypeTabulator makeWholeAmountTypeTabulator;

		@Inject
		public Impl(SwapCurveValuationTypeTabulator swapCurveValuationTypeTabulator, MakeWholeAmountTypeTabulator makeWholeAmountTypeTabulator) {
			this.swapCurveValuationTypeTabulator = swapCurveValuationTypeTabulator;
			this.makeWholeAmountTypeTabulator = makeWholeAmountTypeTabulator;
			this.swapUnwindValueField = new FieldImpl(
				"swapUnwindValue",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.makeWholeAmountField = new FieldImpl(
				"makeWholeAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferenceSwapCurve input) {
			FieldValue swapUnwindValue = Optional.ofNullable(input.getSwapUnwindValue())
				.map(x -> new NestedFieldValueImpl(swapUnwindValueField, Optional.of(swapCurveValuationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(swapUnwindValueField, Optional.empty()));
			FieldValue makeWholeAmount = Optional.ofNullable(input.getMakeWholeAmount())
				.map(x -> new NestedFieldValueImpl(makeWholeAmountField, Optional.of(makeWholeAmountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(makeWholeAmountField, Optional.empty()));
			return Arrays.asList(
				swapUnwindValue,
				makeWholeAmount
			);
		}
	}
}
