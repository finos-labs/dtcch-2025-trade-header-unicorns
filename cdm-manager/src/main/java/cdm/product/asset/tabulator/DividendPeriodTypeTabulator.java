package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.BusinessDayAdjustmentsTypeTabulator;
import cdm.observable.asset.tabulator.BasketConstituentTypeTabulator;
import cdm.product.asset.DividendPeriod;
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


@ImplementedBy(DividendPeriodTypeTabulator.Impl.class)
public interface DividendPeriodTypeTabulator extends Tabulator<DividendPeriod> {
	@Singleton
	class Impl implements DividendPeriodTypeTabulator {
		private final Field startDateField;
		private final Field endDateField;
		private final Field dateAdjustmentsField;
		private final Field basketConstituentField;
		private final Field dividendPaymentDateField;
		private final Field dividendValuationDateField;

		private final DividendPaymentDateTypeTabulator dividendPaymentDateTypeTabulator;
		private final BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator;
		private final BasketConstituentTypeTabulator basketConstituentTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(DividendPaymentDateTypeTabulator dividendPaymentDateTypeTabulator, BusinessDayAdjustmentsTypeTabulator businessDayAdjustmentsTypeTabulator, BasketConstituentTypeTabulator basketConstituentTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.dividendPaymentDateTypeTabulator = dividendPaymentDateTypeTabulator;
			this.businessDayAdjustmentsTypeTabulator = businessDayAdjustmentsTypeTabulator;
			this.basketConstituentTypeTabulator = basketConstituentTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.startDateField = new FieldImpl(
				"startDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.endDateField = new FieldImpl(
				"endDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dateAdjustmentsField = new FieldImpl(
				"dateAdjustments",
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
			this.dividendPaymentDateField = new FieldImpl(
				"dividendPaymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendValuationDateField = new FieldImpl(
				"dividendValuationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendPeriod input) {
			FieldValue startDate = Optional.ofNullable(input.getStartDate())
				.map(x -> new NestedFieldValueImpl(startDateField, Optional.of(dividendPaymentDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(startDateField, Optional.empty()));
			FieldValue endDate = Optional.ofNullable(input.getEndDate())
				.map(x -> new NestedFieldValueImpl(endDateField, Optional.of(dividendPaymentDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(endDateField, Optional.empty()));
			FieldValue dateAdjustments = Optional.ofNullable(input.getDateAdjustments())
				.map(x -> new NestedFieldValueImpl(dateAdjustmentsField, Optional.of(businessDayAdjustmentsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dateAdjustmentsField, Optional.empty()));
			FieldValue basketConstituent = Optional.ofNullable(input.getBasketConstituent())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(basketConstituentField, Optional.of(basketConstituentTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(basketConstituentField, Optional.empty()));
			FieldValue dividendPaymentDate = Optional.ofNullable(input.getDividendPaymentDate())
				.map(x -> new NestedFieldValueImpl(dividendPaymentDateField, Optional.of(dividendPaymentDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendPaymentDateField, Optional.empty()));
			FieldValue dividendValuationDate = Optional.ofNullable(input.getDividendValuationDate())
				.map(x -> new NestedFieldValueImpl(dividendValuationDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendValuationDateField, Optional.empty()));
			return Arrays.asList(
				startDate,
				endDate,
				dateAdjustments,
				basketConstituent,
				dividendPaymentDate,
				dividendValuationDate
			);
		}
	}
}
