package cdm.event.workflow.tabulator;

import cdm.event.workflow.LimitApplicableExtended;
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


@ImplementedBy(LimitApplicableExtendedTypeTabulator.Impl.class)
public interface LimitApplicableExtendedTypeTabulator extends Tabulator<LimitApplicableExtended> {
	@Singleton
	class Impl implements LimitApplicableExtendedTypeTabulator {
		private final Field limitTypeField;
		private final Field clipSizeField;
		private final Field amountUtilizedField;
		private final Field utilizationField;
		private final Field amountRemainingField;
		private final Field currencyField;
		private final Field velocityField;
		private final Field limitLevelField;
		private final Field limitAmountField;
		private final Field limitImpactDueToTradeField;

		private final CreditLimitUtilisationTypeTabulator creditLimitUtilisationTypeTabulator;
		private final VelocityTypeTabulator velocityTypeTabulator;

		@Inject
		public Impl(CreditLimitUtilisationTypeTabulator creditLimitUtilisationTypeTabulator, VelocityTypeTabulator velocityTypeTabulator) {
			this.creditLimitUtilisationTypeTabulator = creditLimitUtilisationTypeTabulator;
			this.velocityTypeTabulator = velocityTypeTabulator;
			this.limitTypeField = new FieldImpl(
				"limitType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.clipSizeField = new FieldImpl(
				"clipSize",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.amountUtilizedField = new FieldImpl(
				"amountUtilized",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.utilizationField = new FieldImpl(
				"utilization",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.amountRemainingField = new FieldImpl(
				"amountRemaining",
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
			this.velocityField = new FieldImpl(
				"velocity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.limitLevelField = new FieldImpl(
				"limitLevel",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.limitAmountField = new FieldImpl(
				"limitAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.limitImpactDueToTradeField = new FieldImpl(
				"limitImpactDueToTrade",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(LimitApplicableExtended input) {
			FieldValue limitType = new FieldValueImpl(limitTypeField, Optional.ofNullable(input.getLimitType())
				.map(x -> x.getValue()));
			FieldValue clipSize = new FieldValueImpl(clipSizeField, Optional.ofNullable(input.getClipSize()));
			FieldValue amountUtilized = new FieldValueImpl(amountUtilizedField, Optional.ofNullable(input.getAmountUtilized()));
			FieldValue utilization = Optional.ofNullable(input.getUtilization())
				.map(x -> new NestedFieldValueImpl(utilizationField, Optional.of(creditLimitUtilisationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(utilizationField, Optional.empty()));
			FieldValue amountRemaining = new FieldValueImpl(amountRemainingField, Optional.ofNullable(input.getAmountRemaining()));
			FieldValue currency = new FieldValueImpl(currencyField, Optional.ofNullable(input.getCurrency())
				.map(x -> x.getValue()));
			FieldValue velocity = Optional.ofNullable(input.getVelocity())
				.map(x -> new NestedFieldValueImpl(velocityField, Optional.of(velocityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(velocityField, Optional.empty()));
			FieldValue limitLevel = new FieldValueImpl(limitLevelField, Optional.ofNullable(input.getLimitLevel())
				.map(x -> x.getValue()));
			FieldValue limitAmount = new FieldValueImpl(limitAmountField, Optional.ofNullable(input.getLimitAmount()));
			FieldValue limitImpactDueToTrade = new FieldValueImpl(limitImpactDueToTradeField, Optional.ofNullable(input.getLimitImpactDueToTrade()));
			return Arrays.asList(
				limitType,
				clipSize,
				amountUtilized,
				utilization,
				amountRemaining,
				currency,
				velocity,
				limitLevel,
				limitAmount,
				limitImpactDueToTrade
			);
		}
	}
}
