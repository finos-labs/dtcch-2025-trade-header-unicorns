package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.product.template.AssetLeg;
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


@ImplementedBy(AssetLegTypeTabulator.Impl.class)
public interface AssetLegTypeTabulator extends Tabulator<AssetLeg> {
	@Singleton
	class Impl implements AssetLegTypeTabulator {
		private final Field settlementDateField;
		private final Field deliveryMethodField;

		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.settlementDateField = new FieldImpl(
				"settlementDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.deliveryMethodField = new FieldImpl(
				"deliveryMethod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AssetLeg input) {
			FieldValue settlementDate = Optional.ofNullable(input.getSettlementDate())
				.map(x -> new NestedFieldValueImpl(settlementDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(settlementDateField, Optional.empty()));
			FieldValue deliveryMethod = new FieldValueImpl(deliveryMethodField, Optional.ofNullable(input.getDeliveryMethod()));
			return Arrays.asList(
				settlementDate,
				deliveryMethod
			);
		}
	}
}
