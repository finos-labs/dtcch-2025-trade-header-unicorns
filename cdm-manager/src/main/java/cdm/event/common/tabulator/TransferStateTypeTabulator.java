package cdm.event.common.tabulator;

import cdm.event.common.TransferState;
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


@ImplementedBy(TransferStateTypeTabulator.Impl.class)
public interface TransferStateTypeTabulator extends Tabulator<TransferState> {
	@Singleton
	class Impl implements TransferStateTypeTabulator {
		private final Field transferField;
		private final Field transferStatusField;

		private final TransferTypeTabulator transferTypeTabulator;

		@Inject
		public Impl(TransferTypeTabulator transferTypeTabulator) {
			this.transferTypeTabulator = transferTypeTabulator;
			this.transferField = new FieldImpl(
				"transfer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferStatusField = new FieldImpl(
				"transferStatus",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TransferState input) {
			FieldValue transfer = Optional.ofNullable(input.getTransfer())
				.map(x -> new NestedFieldValueImpl(transferField, Optional.of(transferTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(transferField, Optional.empty()));
			FieldValue transferStatus = new FieldValueImpl(transferStatusField, Optional.ofNullable(input.getTransferStatus()));
			return Arrays.asList(
				transfer,
				transferStatus
			);
		}
	}
}
