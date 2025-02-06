package cdm.event.common.tabulator;

import cdm.event.common.TransferExpression;
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


@ImplementedBy(TransferExpressionTypeTabulator.Impl.class)
public interface TransferExpressionTypeTabulator extends Tabulator<TransferExpression> {
	@Singleton
	class Impl implements TransferExpressionTypeTabulator {
		private final Field priceTransferField;
		private final Field scheduledTransferField;

		private final ScheduledTransferTypeTabulator scheduledTransferTypeTabulator;

		@Inject
		public Impl(ScheduledTransferTypeTabulator scheduledTransferTypeTabulator) {
			this.scheduledTransferTypeTabulator = scheduledTransferTypeTabulator;
			this.priceTransferField = new FieldImpl(
				"priceTransfer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.scheduledTransferField = new FieldImpl(
				"scheduledTransfer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TransferExpression input) {
			FieldValue priceTransfer = new FieldValueImpl(priceTransferField, Optional.ofNullable(input.getPriceTransfer()));
			FieldValue scheduledTransfer = Optional.ofNullable(input.getScheduledTransfer())
				.map(x -> new NestedFieldValueImpl(scheduledTransferField, Optional.of(scheduledTransferTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(scheduledTransferField, Optional.empty()));
			return Arrays.asList(
				priceTransfer,
				scheduledTransfer
			);
		}
	}
}
