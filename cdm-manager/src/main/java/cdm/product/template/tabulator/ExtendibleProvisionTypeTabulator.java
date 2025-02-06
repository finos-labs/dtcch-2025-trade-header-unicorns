package cdm.product.template.tabulator;

import cdm.base.datetime.tabulator.AdjustableRelativeOrPeriodicDatesTypeTabulator;
import cdm.base.datetime.tabulator.RelativeDateOffsetTypeTabulator;
import cdm.base.staticdata.party.tabulator.PartyRoleTypeTabulator;
import cdm.product.template.ExtendibleProvision;
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


@ImplementedBy(ExtendibleProvisionTypeTabulator.Impl.class)
public interface ExtendibleProvisionTypeTabulator extends Tabulator<ExtendibleProvision> {
	@Singleton
	class Impl implements ExtendibleProvisionTypeTabulator {
		private final Field buyerField;
		private final Field sellerField;
		private final Field exerciseNoticeField;
		private final Field followUpConfirmationField;
		private final Field extendibleProvisionAdjustedDatesField;
		private final Field callingPartyField;
		private final Field singlePartyOptionField;
		private final Field noticeDeadlinePeriodField;
		private final Field noticeDeadlineDateTimeField;
		private final Field extensionTermField;
		private final Field extensionPeriodField;
		private final Field exerciseTermsField;

		private final ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator;
		private final ExtendibleProvisionAdjustedDatesTypeTabulator extendibleProvisionAdjustedDatesTypeTabulator;
		private final PartyRoleTypeTabulator partyRoleTypeTabulator;
		private final RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator;
		private final AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator;
		private final ExerciseTermsTypeTabulator exerciseTermsTypeTabulator;

		@Inject
		public Impl(ExerciseNoticeTypeTabulator exerciseNoticeTypeTabulator, ExtendibleProvisionAdjustedDatesTypeTabulator extendibleProvisionAdjustedDatesTypeTabulator, PartyRoleTypeTabulator partyRoleTypeTabulator, RelativeDateOffsetTypeTabulator relativeDateOffsetTypeTabulator, AdjustableRelativeOrPeriodicDatesTypeTabulator adjustableRelativeOrPeriodicDatesTypeTabulator, ExerciseTermsTypeTabulator exerciseTermsTypeTabulator) {
			this.exerciseNoticeTypeTabulator = exerciseNoticeTypeTabulator;
			this.extendibleProvisionAdjustedDatesTypeTabulator = extendibleProvisionAdjustedDatesTypeTabulator;
			this.partyRoleTypeTabulator = partyRoleTypeTabulator;
			this.relativeDateOffsetTypeTabulator = relativeDateOffsetTypeTabulator;
			this.adjustableRelativeOrPeriodicDatesTypeTabulator = adjustableRelativeOrPeriodicDatesTypeTabulator;
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
			this.extendibleProvisionAdjustedDatesField = new FieldImpl(
				"extendibleProvisionAdjustedDates",
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
			this.singlePartyOptionField = new FieldImpl(
				"singlePartyOption",
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
			this.extensionTermField = new FieldImpl(
				"extensionTerm",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.extensionPeriodField = new FieldImpl(
				"extensionPeriod",
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
		public List<FieldValue> tabulate(ExtendibleProvision input) {
			FieldValue buyer = new FieldValueImpl(buyerField, Optional.ofNullable(input.getBuyer()));
			FieldValue seller = new FieldValueImpl(sellerField, Optional.ofNullable(input.getSeller()));
			FieldValue exerciseNotice = Optional.ofNullable(input.getExerciseNotice())
				.map(x -> new NestedFieldValueImpl(exerciseNoticeField, Optional.of(exerciseNoticeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseNoticeField, Optional.empty()));
			FieldValue followUpConfirmation = new FieldValueImpl(followUpConfirmationField, Optional.ofNullable(input.getFollowUpConfirmation()));
			FieldValue extendibleProvisionAdjustedDates = Optional.ofNullable(input.getExtendibleProvisionAdjustedDates())
				.map(x -> new NestedFieldValueImpl(extendibleProvisionAdjustedDatesField, Optional.of(extendibleProvisionAdjustedDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extendibleProvisionAdjustedDatesField, Optional.empty()));
			FieldValue callingParty = new FieldValueImpl(callingPartyField, Optional.ofNullable(input.getCallingParty()));
			FieldValue singlePartyOption = Optional.ofNullable(input.getSinglePartyOption())
				.map(x -> new NestedFieldValueImpl(singlePartyOptionField, Optional.of(partyRoleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(singlePartyOptionField, Optional.empty()));
			FieldValue noticeDeadlinePeriod = Optional.ofNullable(input.getNoticeDeadlinePeriod())
				.map(x -> new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(noticeDeadlinePeriodField, Optional.empty()));
			FieldValue noticeDeadlineDateTime = new FieldValueImpl(noticeDeadlineDateTimeField, Optional.ofNullable(input.getNoticeDeadlineDateTime()));
			FieldValue extensionTerm = Optional.ofNullable(input.getExtensionTerm())
				.map(x -> new NestedFieldValueImpl(extensionTermField, Optional.of(relativeDateOffsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extensionTermField, Optional.empty()));
			FieldValue extensionPeriod = Optional.ofNullable(input.getExtensionPeriod())
				.map(x -> new NestedFieldValueImpl(extensionPeriodField, Optional.of(adjustableRelativeOrPeriodicDatesTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(extensionPeriodField, Optional.empty()));
			FieldValue exerciseTerms = Optional.ofNullable(input.getExerciseTerms())
				.map(x -> new NestedFieldValueImpl(exerciseTermsField, Optional.of(exerciseTermsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(exerciseTermsField, Optional.empty()));
			return Arrays.asList(
				buyer,
				seller,
				exerciseNotice,
				followUpConfirmation,
				extendibleProvisionAdjustedDates,
				callingParty,
				singlePartyOption,
				noticeDeadlinePeriod,
				noticeDeadlineDateTime,
				extensionTerm,
				extensionPeriod,
				exerciseTerms
			);
		}
	}
}
