package cdm.product.common.schedule.tabulator;

import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.observable.asset.tabulator.FxSpotRateSourceTypeTabulator;
import cdm.product.common.schedule.FxLinkedNotionalSchedule;
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


@ImplementedBy(FxLinkedNotionalScheduleTypeTabulator.Impl.class)
public interface FxLinkedNotionalScheduleTypeTabulator extends Tabulator<FxLinkedNotionalSchedule> {
	@Singleton
	class Impl implements FxLinkedNotionalScheduleTypeTabulator {
		private final Field varyingNotionalCurrencyField;
		private final Field varyingNotionalFixingDatesField;
		private final Field fxSpotRateSourceField;
		private final Field fixingTimeField;
		private final Field varyingNotionalInterimExchangePaymentDatesField;

		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;

		@Inject
		public Impl(RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, FxSpotRateSourceTypeTabulator fxSpotRateSourceTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator) {
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.fxSpotRateSourceTypeTabulator = fxSpotRateSourceTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.varyingNotionalCurrencyField = new FieldImpl(
				"varyingNotionalCurrency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.varyingNotionalFixingDatesField = new FieldImpl(
				"varyingNotionalFixingDates",
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
			this.varyingNotionalInterimExchangePaymentDatesField = new FieldImpl(
				"varyingNotionalInterimExchangePaymentDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxLinkedNotionalSchedule input) {
			FieldValue varyingNotionalCurrency = new FieldValueImpl(varyingNotionalCurrencyField, Optional.ofNullable(input.getVaryingNotionalCurrency())
				.map(x -> x.getValue()));
			FieldValue varyingNotionalFixingDates = Optional.ofNullable(input.getVaryingNotionalFixingDates())
				.map(x -> new NestedFieldValueImpl(varyingNotionalFixingDatesField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(varyingNotionalFixingDatesField, Optional.empty()));
			FieldValue fxSpotRateSource = Optional.ofNullable(input.getFxSpotRateSource())
				.map(x -> new NestedFieldValueImpl(fxSpotRateSourceField, Optional.of(fxSpotRateSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fxSpotRateSourceField, Optional.empty()));
			FieldValue fixingTime = Optional.ofNullable(input.getFixingTime())
				.map(x -> new NestedFieldValueImpl(fixingTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(fixingTimeField, Optional.empty()));
			FieldValue varyingNotionalInterimExchangePaymentDates = Optional.ofNullable(input.getVaryingNotionalInterimExchangePaymentDates())
				.map(x -> new NestedFieldValueImpl(varyingNotionalInterimExchangePaymentDatesField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(varyingNotionalInterimExchangePaymentDatesField, Optional.empty()));
			return Arrays.asList(
				varyingNotionalCurrency,
				varyingNotionalFixingDates,
				fxSpotRateSource,
				fixingTime,
				varyingNotionalInterimExchangePaymentDates
			);
		}
	}
}
