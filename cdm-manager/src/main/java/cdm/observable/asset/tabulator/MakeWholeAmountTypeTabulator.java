package cdm.observable.asset.tabulator;

import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.observable.asset.MakeWholeAmount;
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


@ImplementedBy(MakeWholeAmountTypeTabulator.Impl.class)
public interface MakeWholeAmountTypeTabulator extends Tabulator<MakeWholeAmount> {
	@Singleton
	class Impl implements MakeWholeAmountTypeTabulator {
		private final Field floatingRateIndexField;
		private final Field indexTenorField;
		private final Field spreadField;
		private final Field sideField;
		private final Field interpolationMethodField;
		private final Field earlyCallDateField;

		private final PeriodTypeTabulator periodTypeTabulator;

		@Inject
		public Impl(PeriodTypeTabulator periodTypeTabulator) {
			this.periodTypeTabulator = periodTypeTabulator;
			this.floatingRateIndexField = new FieldImpl(
				"floatingRateIndex",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexTenorField = new FieldImpl(
				"indexTenor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadField = new FieldImpl(
				"spread",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sideField = new FieldImpl(
				"side",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.interpolationMethodField = new FieldImpl(
				"interpolationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.earlyCallDateField = new FieldImpl(
				"earlyCallDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MakeWholeAmount input) {
			FieldValue floatingRateIndex = new FieldValueImpl(floatingRateIndexField, Optional.ofNullable(input.getFloatingRateIndex()));
			FieldValue indexTenor = Optional.ofNullable(input.getIndexTenor())
				.map(x -> new NestedFieldValueImpl(indexTenorField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indexTenorField, Optional.empty()));
			FieldValue spread = new FieldValueImpl(spreadField, Optional.ofNullable(input.getSpread()));
			FieldValue side = new FieldValueImpl(sideField, Optional.ofNullable(input.getSide()));
			FieldValue interpolationMethod = new FieldValueImpl(interpolationMethodField, Optional.ofNullable(input.getInterpolationMethod()));
			FieldValue earlyCallDate = new FieldValueImpl(earlyCallDateField, Optional.ofNullable(input.getEarlyCallDate())
				.map(x -> x.getValue()));
			return Arrays.asList(
				floatingRateIndex,
				indexTenor,
				spread,
				side,
				interpolationMethod,
				earlyCallDate
			);
		}
	}
}
