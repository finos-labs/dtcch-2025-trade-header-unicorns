package cdm.product.template.tabulator;

import cdm.product.template.ExerciseNotice;
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


@ImplementedBy(ExerciseNoticeTypeTabulator.Impl.class)
public interface ExerciseNoticeTypeTabulator extends Tabulator<ExerciseNotice> {
	@Singleton
	class Impl implements ExerciseNoticeTypeTabulator {
		private final Field exerciseNoticeGiverField;
		private final Field exerciseNoticeReceiverField;
		private final Field businessCenterField;

		public Impl() {
			this.exerciseNoticeGiverField = new FieldImpl(
				"exerciseNoticeGiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exerciseNoticeReceiverField = new FieldImpl(
				"exerciseNoticeReceiver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.businessCenterField = new FieldImpl(
				"businessCenter",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExerciseNotice input) {
			FieldValue exerciseNoticeGiver = new FieldValueImpl(exerciseNoticeGiverField, Optional.ofNullable(input.getExerciseNoticeGiver()));
			FieldValue exerciseNoticeReceiver = new FieldValueImpl(exerciseNoticeReceiverField, Optional.ofNullable(input.getExerciseNoticeReceiver()));
			FieldValue businessCenter = new FieldValueImpl(businessCenterField, Optional.ofNullable(input.getBusinessCenter())
				.map(x -> x.getValue()));
			return Arrays.asList(
				exerciseNoticeGiver,
				exerciseNoticeReceiver,
				businessCenter
			);
		}
	}
}
