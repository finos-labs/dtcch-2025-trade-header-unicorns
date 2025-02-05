package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.observable.asset.tabulator.FxRateTypeTabulator;
import cdm.observable.asset.tabulator.FxSpotRateSourceTypeTabulator;
import cdm.product.template.Quanto;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(QuantoTypeTabulator.Impl.class)
public interface QuantoTypeTabulator extends Tabulator<Quanto> {
	@Singleton
	class Impl implements QuantoTypeTabulator {
		private final Field fxRateField;
		private final Field fxSpotRateSourceField;
		private final Field fixingTimeField;

		private final FxRateTypeTabulator fxRateTypeTabulator;
		private final FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;

		@Inject
		public Impl(FxRateTypeTabulator fxRateTypeTabulator, FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator) {
			this.fxRateTypeTabulator = fxRateTypeTabulator;
			this.fxSpotRateSourceTypeTabulator = fxSpotRateSourceTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.fxRateField = new FieldImpl(
				"fxRate",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fxSpotRateSourceField = new FieldImpl(
				"fxSpotRateSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fixingTimeField = new FieldImpl(
				"fixingTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Quanto input) {
			FieldValue fxRate = Optional.ofNullable(input.getFxRate())
				.map(x -> x.stream()
					.map(_x -> fxRateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(fxRateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(fxRateField, Optional.empty()));
			FieldValue fxSpotRateSource = Optional.ofNullable(input.getFxSpotRateSource())
				.map(x -> new NestedFieldValueImpl(fxSpotRateSourceField, Optional.of(fxSpotRateSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxSpotRateSourceField, Optional.empty()));
			FieldValue fixingTime = Optional.ofNullable(input.getFixingTime())
				.map(x -> new NestedFieldValueImpl(fixingTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixingTimeField, Optional.empty()));
			return Arrays.asList(
				fxRate,
				fxSpotRateSource,
				fixingTime
			);
		}
	}
}
