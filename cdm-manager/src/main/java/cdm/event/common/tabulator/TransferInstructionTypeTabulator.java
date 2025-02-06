package cdm.event.common.tabulator;

import cdm.event.common.TransferInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(TransferInstructionTypeTabulator.Impl.class)
public interface TransferInstructionTypeTabulator extends Tabulator<TransferInstruction> {
	@Singleton
	class Impl implements TransferInstructionTypeTabulator {
		private final Field transferStateField;

		private final TransferStateTypeTabulator transferStateTypeTabulator;

		@Inject
		public Impl(TransferStateTypeTabulator transferStateTypeTabulator) {
			this.transferStateTypeTabulator = transferStateTypeTabulator;
			this.transferStateField = new FieldImpl(
				"transferState",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TransferInstruction input) {
			FieldValue transferState = Optional.ofNullable(input.getTransferState())
				.map(x -> x.stream()
					.map(_x -> transferStateTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(transferStateField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(transferStateField, Optional.empty()));
			return Arrays.asList(
				transferState
			);
		}
	}
}
