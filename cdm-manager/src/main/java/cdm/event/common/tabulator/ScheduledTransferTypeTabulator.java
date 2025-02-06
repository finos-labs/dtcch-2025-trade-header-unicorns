package cdm.event.common.tabulator;

import cdm.event.common.ScheduledTransfer;
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


@ImplementedBy(ScheduledTransferTypeTabulator.Impl.class)
public interface ScheduledTransferTypeTabulator extends Tabulator<ScheduledTransfer> {
	@Singleton
	class Impl implements ScheduledTransferTypeTabulator {
		private final Field transferTypeField;
		private final Field corporateActionTransferTypeField;

		public Impl() {
			this.transferTypeField = new FieldImpl(
				"transferType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.corporateActionTransferTypeField = new FieldImpl(
				"corporateActionTransferType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ScheduledTransfer input) {
			FieldValue transferType = new FieldValueImpl(transferTypeField, Optional.ofNullable(input.getTransferType()));
			FieldValue corporateActionTransferType = new FieldValueImpl(corporateActionTransferTypeField, Optional.ofNullable(input.getCorporateActionTransferType()));
			return Arrays.asList(
				transferType,
				corporateActionTransferType
			);
		}
	}
}
