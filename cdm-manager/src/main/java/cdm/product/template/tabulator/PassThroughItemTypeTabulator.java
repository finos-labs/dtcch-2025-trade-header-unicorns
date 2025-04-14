package cdm.product.template.tabulator;

import cdm.base.staticdata.party.tabulator.PayerReceiverTypeTabulator;
import cdm.product.template.PassThroughItem;
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


@ImplementedBy(PassThroughItemTypeTabulator.Impl.class)
public interface PassThroughItemTypeTabulator extends Tabulator<PassThroughItem> {
	@Singleton
	class Impl implements PassThroughItemTypeTabulator {
		private final Field payerReceiverField;
		private final Field passThroughPercentageField;

		private final PayerReceiverTypeTabulator payerReceiverTypeTabulator;

		@Inject
		public Impl(PayerReceiverTypeTabulator payerReceiverTypeTabulator) {
			this.payerReceiverTypeTabulator = payerReceiverTypeTabulator;
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.passThroughPercentageField = new FieldImpl(
				"passThroughPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PassThroughItem input) {
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(payerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue passThroughPercentage = new FieldValueImpl(passThroughPercentageField, Optional.ofNullable(input.getPassThroughPercentage()));
			return Arrays.asList(
				payerReceiver,
				passThroughPercentage
			);
		}
	}
}
