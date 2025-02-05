package cdm.observable.event.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyReferencePayerReceiverTypeTabulator;
import cdm.observable.event.FeaturePayment;
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


@ImplementedBy(FeaturePaymentTypeTabulator.Impl.class)
public interface FeaturePaymentTypeTabulator extends Tabulator<FeaturePayment> {
	@Singleton
	class Impl implements FeaturePaymentTypeTabulator {
		private final Field payerReceiverField;
		private final Field levelPercentageField;
		private final Field amountField;
		private final Field timeField;
		private final Field currencyField;
		private final Field paymentDateField;

		private final PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.partyReferencePayerReceiverTypeTabulator = partyReferencePayerReceiverTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.levelPercentageField = new FieldImpl(
				"levelPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.amountField = new FieldImpl(
				"amount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.timeField = new FieldImpl(
				"time",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.currencyField = new FieldImpl(
				"currency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.paymentDateField = new FieldImpl(
				"paymentDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FeaturePayment input) {
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(partyReferencePayerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue levelPercentage = new FieldValueImpl(levelPercentageField, Optional.ofNullable(input.getLevelPercentage()));
			FieldValue amount = new FieldValueImpl(amountField, Optional.ofNullable(input.getAmount()));
			FieldValue time = new FieldValueImpl(timeField, Optional.ofNullable(input.getTime()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			FieldValue paymentDate = Optional.ofNullable(input.getPaymentDate())
				.map(x -> new NestedFieldValueImpl(paymentDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(paymentDateField, Optional.empty()));
			return Arrays.asList(
				payerReceiver,
				levelPercentage,
				amount,
				time,
				currency,
				paymentDate
			);
		}
	}
}
