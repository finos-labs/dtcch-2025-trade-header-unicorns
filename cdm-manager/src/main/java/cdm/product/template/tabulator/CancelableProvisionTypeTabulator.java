package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.datetime.tabulator.AdjustableOrRelativeDatesTypeTabulator;
import cdm.base.datetime.tabulator.BusinessCenterTimeTypeTabulator;
import cdm.base.datetime.tabulator.PeriodTypeTabulator;
import cdm.event.common.tabulator.TransferTypeTabulator;
import cdm.product.common.schedule.tabulator.FinalCalculationPeriodDateAdjustmentTypeTabulator;
import cdm.product.template.CancelableProvision;
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


@ImplementedBy(CancelableProvisionTypeTabulator.Impl.class)
public interface CancelableProvisionTypeTabulator extends Tabulator<CancelableProvision> {
	@Singleton
	class Impl implements CancelableProvisionTypeTabulator {
		private final Field buyerField;
		private final Field sellerField;
		private final Field exerciseNoticeField;
		private final Field followUpConfirmationField;
		private final Field cancelableProvisionAdjustedDatesField;
		private final Field finalCalculationPeriodDateAdjustmentField;
		private final Field initialFeeField;
		private final Field callingPartyField;
		private final Field earliestDateField;
		private final Field expirationDateField;
		private final Field effectiveDateField;
		private final Field effectivePeriodField;
		private final Field earliestCancellationTimeField;
		private final Field latestCancelationTimeField;
		private final Field exerciseTermsField;

		private final ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator;
		private final CancelableProvisionAdjustedDatesTypeTabulator cancelableProvisionAdjustedDatesTypeTabulator;
		private final FinalCalculationPeriodDateAdjustmentTypeTabulator finalCalculationPeriodDateAdjustmentTypeTabulator;
		private final TransferTypeTabulator transferTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;
		private final AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator;
		private final PeriodTypeTabulator periodTypeTabulator;
		private final BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator;
		private final ExerciseTermsTypeTabulator exerciseTermsTypeTabulator;

		@Inject
		public Impl(ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator, CancelableProvisionAdjustedDatesTypeTabulator cancelableProvisionAdjustedDatesTypeTabulator, FinalCalculationPeriodDateAdjustmentTypeTabulator finalCalculationPeriodDateAdjustmentTypeTabulator, TransferTypeTabulator transferTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator, AdjustableOrRelativeDatesTypeTabulator adjustableOrRelativeDatesTypeTabulator, PeriodTypeTabulator periodTypeTabulator, BusinessCenterTimeTypeTabulator businessCenterTimeTypeTabulator, ExerciseTermsTypeTabulator exerciseTermsTypeTabulator) {
			this.exerciseNoticeTypeTabulator = exerciseNoticeTypeTabulator;
			this.cancelableProvisionAdjustedDatesTypeTabulator = cancelableProvisionAdjustedDatesTypeTabulator;
			this.finalCalculationPeriodDateAdjustmentTypeTabulator = finalCalculationPeriodDateAdjustmentTypeTabulator;
			this.transferTypeTabulator = transferTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.adjustableOrRelativeDatesTypeTabulator = adjustableOrRelativeDatesTypeTabulator;
			this.periodTypeTabulator = periodTypeTabulator;
			this.businessCenterTimeTypeTabulator = businessCenterTimeTypeTabulator;
			this.exerciseTermsTypeTabulator = exerciseTermsTypeTabulator;
			this.buyerField = new FieldImpl(
				"buyer",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sellerField = new FieldImpl(
				"seller",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseNoticeField = new FieldImpl(
				"exerciseNotice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.followUpConfirmationField = new FieldImpl(
				"followUpConfirmation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cancelableProvisionAdjustedDatesField = new FieldImpl(
				"cancelableProvisionAdjustedDates",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.finalCalculationPeriodDateAdjustmentField = new FieldImpl(
				"finalCalculationPeriodDateAdjustment",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.initialFeeField = new FieldImpl(
				"initialFee",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.callingPartyField = new FieldImpl(
				"callingParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.earliestDateField = new FieldImpl(
				"earliestDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.expirationDateField = new FieldImpl(
				"expirationDate",
				false,
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
			this.effectivePeriodField = new FieldImpl(
				"effectivePeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.earliestCancellationTimeField = new FieldImpl(
				"earliestCancellationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.latestCancelationTimeField = new FieldImpl(
				"latestCancelationTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseTermsField = new FieldImpl(
				"exerciseTerms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CancelableProvision input) {
			FieldValue buyer = new FieldValueImpl(buyerField, Optional.ofNullable(input.getBuyer()));
			FieldValue seller = new FieldValueImpl(sellerField, Optional.ofNullable(input.getSeller()));
			FieldValue exerciseNotice = Optional.ofNullable(input.getExerciseNotice())
				.map(x -> new NestedFieldValueImpl(exerciseNoticeField, Optional.of(exerciseNoticeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseNoticeField, Optional.empty()));
			FieldValue followUpConfirmation = new FieldValueImpl(followUpConfirmationField, Optional.ofNullable(input.getFollowUpConfirmation()));
			FieldValue cancelableProvisionAdjustedDates = Optional.ofNullable(input.getCancelableProvisionAdjustedDates())
				.map(x -> new NestedFieldValueImpl(cancelableProvisionAdjustedDatesField, Optional.of(cancelableProvisionAdjustedDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cancelableProvisionAdjustedDatesField, Optional.empty()));
			FieldValue finalCalculationPeriodDateAdjustment = Optional.ofNullable(input.getFinalCalculationPeriodDateAdjustment())
				.map(x -> x.stream()
					.map(_x -> finalCalculationPeriodDateAdjustmentTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(finalCalculationPeriodDateAdjustmentField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(finalCalculationPeriodDateAdjustmentField, Optional.empty()));
			FieldValue initialFee = Optional.ofNullable(input.getInitialFee())
				.map(x -> new NestedFieldValueImpl(initialFeeField, Optional.of(transferTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(initialFeeField, Optional.empty()));
			FieldValue callingParty = new FieldValueImpl(callingPartyField, Optional.ofNullable(input.getCallingParty()));
			FieldValue earliestDate = Optional.ofNullable(input.getEarliestDate())
				.map(x -> new NestedFieldValueImpl(earliestDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(earliestDateField, Optional.empty()));
			FieldValue expirationDate = Optional.ofNullable(input.getExpirationDate())
				.map(x -> new NestedFieldValueImpl(expirationDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(expirationDateField, Optional.empty()));
			FieldValue effectiveDate = Optional.ofNullable(input.getEffectiveDate())
				.map(x -> new NestedFieldValueImpl(effectiveDateField, Optional.of(adjustableOrRelativeDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(effectiveDateField, Optional.empty()));
			FieldValue effectivePeriod = Optional.ofNullable(input.getEffectivePeriod())
				.map(x -> new NestedFieldValueImpl(effectivePeriodField, Optional.of(periodTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(effectivePeriodField, Optional.empty()));
			FieldValue earliestCancellationTime = Optional.ofNullable(input.getEarliestCancellationTime())
				.map(x -> new NestedFieldValueImpl(earliestCancellationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(earliestCancellationTimeField, Optional.empty()));
			FieldValue latestCancelationTime = Optional.ofNullable(input.getLatestCancelationTime())
				.map(x -> new NestedFieldValueImpl(latestCancelationTimeField, Optional.of(businessCenterTimeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(latestCancelationTimeField, Optional.empty()));
			FieldValue exerciseTerms = Optional.ofNullable(input.getExerciseTerms())
				.map(x -> new NestedFieldValueImpl(exerciseTermsField, Optional.of(exerciseTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseTermsField, Optional.empty()));
			return Arrays.asList(
				buyer,
				seller,
				exerciseNotice,
				followUpConfirmation,
				cancelableProvisionAdjustedDates,
				finalCalculationPeriodDateAdjustment,
				initialFee,
				callingParty,
				earliestDate,
				expirationDate,
				effectiveDate,
				effectivePeriod,
				earliestCancellationTime,
				latestCancelationTime,
				exerciseTerms
			);
		}
	}
}
