package cdm.product.common.schedule.tabulator;

import cdm.product.common.schedule.DateRelativeToPaymentDates;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(DateRelativeToPaymentDatesTypeTabulator.Impl.class)
public interface DateRelativeToPaymentDatesTypeTabulator extends Tabulator<DateRelativeToPaymentDates> {
	@Singleton
	class Impl implements DateRelativeToPaymentDatesTypeTabulator {
		private final Field paymentDatesReferenceField;

		private final PaymentDatesTypeTabulator paymentDatesTypeTabulator;

		@Inject
		public Impl(PaymentDatesTypeTabulator paymentDatesTypeTabulator) {
			this.paymentDatesTypeTabulator = paymentDatesTypeTabulator;
			this.paymentDatesReferenceField = new FieldImpl(
				"paymentDatesReference",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DateRelativeToPaymentDates input) {
			FieldValue paymentDatesReference = Optional.ofNullable(input.getPaymentDatesReference())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> paymentDatesTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(paymentDatesReferenceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(paymentDatesReferenceField, Optional.empty()));
			return Arrays.asList(
				paymentDatesReference
			);
		}
	}
}
