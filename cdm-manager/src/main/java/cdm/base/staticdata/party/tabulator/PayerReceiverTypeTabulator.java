package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.PayerReceiver;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;


@ImplementedBy(PayerReceiverTypeTabulator.Impl.class)
public interface PayerReceiverTypeTabulator extends Tabulator<PayerReceiver> {
	@Singleton
	class Impl implements PayerReceiverTypeTabulator {
		private final Field payerField;
		private final Field receiverField;

		public Impl() {
			this.payerField = new FieldImpl(
				"payer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.receiverField = new FieldImpl(
				"receiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PayerReceiver input) {
			FieldValue payer = new FieldValueImpl(payerField, Optional.ofNullable(input.getPayer()));
			FieldValue receiver = new FieldValueImpl(receiverField, Optional.ofNullable(input.getReceiver()));
			return Arrays.asList(
				payer,
				receiver
			);
		}
	}
}
