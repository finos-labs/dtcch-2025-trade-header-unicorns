package cdm.event.common.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrAdjustedOrRelativeDateTypeTabulator;
import cdm.base.math.tabulator.NonNegativeQuantityTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.AssetTypeTabulator;
import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyReferencePayerReceiverTypeTabulator;
import cdm.event.common.Transfer;
import cdm.product.template.tabulator.PayoutTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(TransferTypeTabulator.Impl.class)
public interface TransferTypeTabulator extends Tabulator<Transfer> {
	@Singleton
	class Impl implements TransferTypeTabulator {
		private final Field quantityField;
		private final Field assetField;
		private final Field settlementDateField;
		private final Field identifierField;
		private final Field payerReceiverField;
		private final Field settlementOriginField;
		private final Field resetOriginField;
		private final Field transferExpressionField;

		private final NonNegativeQuantityTypeTabulator nonNegativeQuantityTypeTabulator;
		private final AssetTypeTabulator assetTypeTabulator;
		private final AdjustableOrAdjustedOrRelativeDateTypeTabulator adjustableOrAdjustedOrRelativeDateTypeTabulator;
		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator;
		private final PayoutTypeTabulator payoutTypeTabulator;
		private final ResetTypeTabulator resetTypeTabulator;
		private final TransferExpressionTypeTabulator transferExpressionTypeTabulator;

		@Inject
		public Impl(NonNegativeQuantityTypeTabulator nonNegativeQuantityTypeTabulator, AssetTypeTabulator assetTypeTabulator, AdjustableOrAdjustedOrRelativeDateTypeTabulator adjustableOrAdjustedOrRelativeDateTypeTabulator, IdentifierTypeTabulator identifierTypeTabulator, PartyReferencePayerReceiverTypeTabulator partyReferencePayerReceiverTypeTabulator, PayoutTypeTabulator payoutTypeTabulator, ResetTypeTabulator resetTypeTabulator, TransferExpressionTypeTabulator transferExpressionTypeTabulator) {
			this.nonNegativeQuantityTypeTabulator = nonNegativeQuantityTypeTabulator;
			this.assetTypeTabulator = assetTypeTabulator;
			this.adjustableOrAdjustedOrRelativeDateTypeTabulator = adjustableOrAdjustedOrRelativeDateTypeTabulator;
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.partyReferencePayerReceiverTypeTabulator = partyReferencePayerReceiverTypeTabulator;
			this.payoutTypeTabulator = payoutTypeTabulator;
			this.resetTypeTabulator = resetTypeTabulator;
			this.transferExpressionTypeTabulator = transferExpressionTypeTabulator;
			this.quantityField = new FieldImpl(
				"quantity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.assetField = new FieldImpl(
				"asset",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementDateField = new FieldImpl(
				"settlementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.identifierField = new FieldImpl(
				"identifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.payerReceiverField = new FieldImpl(
				"payerReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.settlementOriginField = new FieldImpl(
				"settlementOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetOriginField = new FieldImpl(
				"resetOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.transferExpressionField = new FieldImpl(
				"transferExpression",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Transfer input) {
			FieldValue quantity = Optional.ofNullable(input.getQuantity())
				.map(x -> new NestedFieldValueImpl(quantityField, Optional.of(nonNegativeQuantityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(quantityField, Optional.empty()));
			FieldValue asset = Optional.ofNullable(input.getAsset())
				.map(x -> new NestedFieldValueImpl(assetField, Optional.of(assetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(assetField, Optional.empty()));
			FieldValue settlementDate = Optional.ofNullable(input.getSettlementDate())
				.map(x -> new NestedFieldValueImpl(settlementDateField, Optional.of(adjustableOrAdjustedOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementDateField, Optional.empty()));
			FieldValue identifier = Optional.ofNullable(input.getIdentifier())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(identifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(identifierField, Optional.empty()));
			FieldValue payerReceiver = Optional.ofNullable(input.getPayerReceiver())
				.map(x -> new NestedFieldValueImpl(payerReceiverField, Optional.of(partyReferencePayerReceiverTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(payerReceiverField, Optional.empty()));
			FieldValue settlementOrigin = Optional.ofNullable(input.getSettlementOrigin())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(settlementOriginField, Optional.of(payoutTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementOriginField, Optional.empty()));
			FieldValue resetOrigin = Optional.ofNullable(input.getResetOrigin())
				.map(x -> new NestedFieldValueImpl(resetOriginField, Optional.of(resetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(resetOriginField, Optional.empty()));
			FieldValue transferExpression = Optional.ofNullable(input.getTransferExpression())
				.map(x -> new NestedFieldValueImpl(transferExpressionField, Optional.of(transferExpressionTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(transferExpressionField, Optional.empty()));
			return Arrays.asList(
				quantity,
				asset,
				settlementDate,
				identifier,
				payerReceiver,
				settlementOrigin,
				resetOrigin,
				transferExpression
			);
		}
	}
}
