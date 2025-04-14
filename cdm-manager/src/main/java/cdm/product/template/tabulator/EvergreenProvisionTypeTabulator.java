package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.observable.asset.tabulator.PriceTypeTabulator;
import cdm.product.template.EvergreenProvision;
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


@ImplementedBy(EvergreenProvisionTypeTabulator.Impl.class)
public interface EvergreenProvisionTypeTabulator extends Tabulator<EvergreenProvision> {
	@Singleton
	class Impl implements EvergreenProvisionTypeTabulator {
		private final Field singlePartyOptionField;
		private final Field noticePeriodField;
		private final Field noticeDeadlinePeriodField;
		private final Field noticeDeadlineDateTimeField;
		private final Field extensionFrequencyField;
		private final Field finalPeriodFeeAdjustmentField;

		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;
		private final PriceTypeTabulator priceTypeTabulator;

		@Inject
		public Impl(PartyRoleTypeTabulator partyRoleTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator, PriceTypeTabulator priceTypeTabulator) {
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
			this.priceTypeTabulator = priceTypeTabulator;
			this.singlePartyOptionField = new FieldImpl(
				"singlePartyOption",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noticePeriodField = new FieldImpl(
				"noticePeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noticeDeadlinePeriodField = new FieldImpl(
				"noticeDeadlinePeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.noticeDeadlineDateTimeField = new FieldImpl(
				"noticeDeadlineDateTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.extensionFrequencyField = new FieldImpl(
				"extensionFrequency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalPeriodFeeAdjustmentField = new FieldImpl(
				"finalPeriodFeeAdjustment",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EvergreenProvision input) {
			FieldValue singlePartyOption = Optional.ofNullable(input.getSinglePartyOption())
				.map(x -> new NestedFieldValueImpl(singlePartyOptionField, Optional.of(partyRoleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(singlePartyOptionField, Optional.empty()));
			FieldValue noticePeriod = Optional.ofNullable(input.getNoticePeriod())
				.map(x -> new NestedFieldValueImpl(noticePeriodField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(noticePeriodField, Optional.empty()));
			FieldValue noticeDeadlinePeriod = Optional.ofNullable(input.getNoticeDeadlinePeriod())
				.map(x -> new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.empty()));
			FieldValue noticeDeadlineDateTime = new FieldValueImpl(noticeDeadlineDateTimeField, Optional.ofNullable(input.getNoticeDeadlineDateTime()));
			FieldValue extensionFrequency = Optional.ofNullable(input.getExtensionFrequency())
				.map(x -> new NestedFieldValueImpl(extensionFrequencyField, Optional.of(adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extensionFrequencyField, Optional.empty()));
			FieldValue finalPeriodFeeAdjustment = Optional.ofNullable(input.getFinalPeriodFeeAdjustment())
				.map(x -> new NestedFieldValueImpl(finalPeriodFeeAdjustmentField, Optional.of(priceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(finalPeriodFeeAdjustmentField, Optional.empty()));
			return Arrays.asList(
				singlePartyOption,
				noticePeriod,
				noticeDeadlinePeriod,
				noticeDeadlineDateTime,
				extensionFrequency,
				finalPeriodFeeAdjustment
			);
		}
	}
}
