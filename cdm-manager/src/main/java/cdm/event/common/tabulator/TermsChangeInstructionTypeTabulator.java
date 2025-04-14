package cdm.event.common.tabulator;

import cdm.base.staticdata.party.tabulator.AncillaryPartyTypeTabulator;
import cdm.event.common.TermsChangeInstruction;
import cdm.product.template.tabulator.NonTransferableProductTypeTabulator;
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


@ImplementedBy(TermsChangeInstructionTypeTabulator.Impl.class)
public interface TermsChangeInstructionTypeTabulator extends Tabulator<TermsChangeInstruction> {
	@Singleton
	class Impl implements TermsChangeInstructionTypeTabulator {
		private final Field productField;
		private final Field ancillaryPartyField;
		private final Field adjustmentField;

		private final NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator;
		private final AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator;

		@Inject
		public Impl(NonTransferableProductTypeTabulator nonTransferableProductTypeTabulator, AncillaryPartyTypeTabulator ancillaryPartyTypeTabulator) {
			this.nonTransferableProductTypeTabulator = nonTransferableProductTypeTabulator;
			this.ancillaryPartyTypeTabulator = ancillaryPartyTypeTabulator;
			this.productField = new FieldImpl(
				"product",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.ancillaryPartyField = new FieldImpl(
				"ancillaryParty",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.adjustmentField = new FieldImpl(
				"adjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(TermsChangeInstruction input) {
			FieldValue product = Optional.ofNullable(input.getProduct())
				.map(x -> new NestedFieldValueImpl(productField, Optional.of(nonTransferableProductTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(productField, Optional.empty()));
			FieldValue ancillaryParty = Optional.ofNullable(input.getAncillaryParty())
				.map(x -> x.stream()
					.map(_x -> ancillaryPartyTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(ancillaryPartyField, Optional.empty()));
			FieldValue adjustment = new FieldValueImpl(adjustmentField, Optional.ofNullable(input.getAdjustment()));
			return Arrays.asList(
				product,
				ancillaryParty,
				adjustment
			);
		}
	}
}
