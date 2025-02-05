package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.product.asset.DividendPaymentDate;
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


@ImplementedBy(DividendPaymentDateTypeTabulator.Impl.class)
public interface DividendPaymentDateTypeTabulator extends Tabulator<DividendPaymentDate> {
	@Singleton
	class Impl implements DividendPaymentDateTypeTabulator {
		private final Field dividendDateReferenceField;
		private final Field dividendDateField;

		private final DividendDateReferenceTypeTabulator dividendDateReferenceTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(DividendDateReferenceTypeTabulator dividendDateReferenceTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.dividendDateReferenceTypeTabulator = dividendDateReferenceTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.dividendDateReferenceField = new FieldImpl(
				"dividendDateReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.dividendDateField = new FieldImpl(
				"dividendDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendPaymentDate input) {
			FieldValue dividendDateReference = Optional.ofNullable(input.getDividendDateReference())
				.map(x -> new NestedFieldValueImpl(dividendDateReferenceField, Optional.of(dividendDateReferenceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendDateReferenceField, Optional.empty()));
			FieldValue dividendDate = Optional.ofNullable(input.getDividendDate())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(dividendDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(dividendDateField, Optional.empty()));
			return Arrays.asList(
				dividendDateReference,
				dividendDate
			);
		}
	}
}
