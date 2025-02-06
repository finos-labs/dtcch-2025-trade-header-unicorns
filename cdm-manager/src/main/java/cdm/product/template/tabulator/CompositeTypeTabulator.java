package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.observable.asset.tabulator.FxSpotRateSourceTypeTabulator;
import cdm.product.template.Composite;
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


@ImplementedBy(CompositeTypeTabulator.Impl.class)
public interface CompositeTypeTabulator extends Tabulator<Composite> {
	@Singleton
	class Impl implements CompositeTypeTabulator {
		private final Field determinationMethodField;
		private final Field relativeDateField;
		private final Field fxSpotRateSourceField;
		private final Field fixingTimeField;

		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;

		@Inject
		public Impl(RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator) {
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.fxSpotRateSourceTypeTabulator = fxSpotRateSourceTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.determinationMethodField = new FieldImpl(
				"determinationMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relativeDateField = new FieldImpl(
				"relativeDate",
				false,
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
		public List<FieldValue> tabulate(Composite input) {
			FieldValue determinationMethod = new FieldValueImpl(determinationMethodField, Optional.ofNullable(input.getDeterminationMethod()));
			FieldValue relativeDate = Optional.ofNullable(input.getRelativeDate())
				.map(x -> new NestedFieldValueImpl(relativeDateField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(relativeDateField, Optional.empty()));
			FieldValue fxSpotRateSource = Optional.ofNullable(input.getFxSpotRateSource())
				.map(x -> new NestedFieldValueImpl(fxSpotRateSourceField, Optional.of(fxSpotRateSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxSpotRateSourceField, Optional.empty()));
			FieldValue fixingTime = Optional.ofNullable(input.getFixingTime())
				.map(x -> new NestedFieldValueImpl(fixingTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixingTimeField, Optional.empty()));
			return Arrays.asList(
				determinationMethod,
				relativeDate,
				fxSpotRateSource,
				fixingTime
			);
		}
	}
}
