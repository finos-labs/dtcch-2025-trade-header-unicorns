package cdm.product.asset.tabulator;

import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.product.asset.DividendDateReference;
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


@ImplementedBy(DividendDateReferenceTypeTabulator.Impl.class)
public interface DividendDateReferenceTypeTabulator extends Tabulator<DividendDateReference> {
	@Singleton
	class Impl implements DividendDateReferenceTypeTabulator {
		private final Field dateReferenceField;
		private final Field paymentDateOffsetField;

		private final OffsetTypeTabulator offsetTypeTabulator;

		@Inject
		public Impl(OffsetTypeTabulator offsetTypeTabulator) {
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.dateReferenceField = new FieldImpl(
				"dateReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDateOffsetField = new FieldImpl(
				"paymentDateOffset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DividendDateReference input) {
			FieldValue dateReference = new FieldValueImpl(dateReferenceField, Optional.ofNullable(input.getDateReference()));
			FieldValue paymentDateOffset = Optional.ofNullable(input.getPaymentDateOffset())
				.map(x -> new NestedFieldValueImpl(paymentDateOffsetField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDateOffsetField, Optional.empty()));
			return Arrays.asList(
				dateReference,
				paymentDateOffset
			);
		}
	}
}
