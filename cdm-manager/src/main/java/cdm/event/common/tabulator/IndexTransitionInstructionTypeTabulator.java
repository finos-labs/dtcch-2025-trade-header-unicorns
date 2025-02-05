package cdm.event.common.tabulator;

import cdm.event.common.IndexTransitionInstruction;
import cdm.observable.asset.tabulator.PriceQuantityTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(IndexTransitionInstructionTypeTabulator.Impl.class)
public interface IndexTransitionInstructionTypeTabulator extends Tabulator<IndexTransitionInstruction> {
	@Singleton
	class Impl implements IndexTransitionInstructionTypeTabulator {
		private final Field priceQuantityField;
		private final Field effectiveDateField;
		private final Field cashTransferField;

		private final PriceQuantityTypeTabulator priceQuantityTypeTabulator;
		private final TransferTypeTabulator transferTypeTabulator;

		@Inject
		public Impl(PriceQuantityTypeTabulator priceQuantityTypeTabulator, TransferTypeTabulator transferTypeTabulator) {
			this.priceQuantityTypeTabulator = priceQuantityTypeTabulator;
			this.transferTypeTabulator = transferTypeTabulator;
			this.priceQuantityField = new FieldImpl(
				"priceQuantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashTransferField = new FieldImpl(
				"cashTransfer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IndexTransitionInstruction input) {
			FieldValue priceQuantity = Optional.ofNullable(input.getPriceQuantity())
				.map(x -> x.stream()
					.map(_x -> priceQuantityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceQuantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceQuantityField, Optional.empty()));
			FieldValue effectiveDate = new FieldValueImpl(effectiveDateField, Optional.ofNullable(input.getEffectiveDate()));
			FieldValue cashTransfer = Optional.ofNullable(input.getCashTransfer())
				.map(x -> new NestedFieldValueImpl(cashTransferField, Optional.of(transferTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashTransferField, Optional.empty()));
			return Arrays.asList(
				priceQuantity,
				effectiveDate,
				cashTransfer
			);
		}
	}
}
