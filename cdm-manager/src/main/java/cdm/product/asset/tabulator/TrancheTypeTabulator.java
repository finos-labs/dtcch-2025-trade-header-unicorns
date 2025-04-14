package cdm.product.asset.tabulator;

import cdm.product.asset.Tranche;
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


@ImplementedBy(TrancheTypeTabulator.Impl.class)
public interface TrancheTypeTabulator extends Tabulator<Tranche> {
	@Singleton
	class Impl implements TrancheTypeTabulator {
		private final Field attachmentPointField;
		private final Field exhaustionPointField;
		private final Field incurredRecoveryApplicableField;

		public Impl() {
			this.attachmentPointField = new FieldImpl(
				"attachmentPoint",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.exhaustionPointField = new FieldImpl(
				"exhaustionPoint",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.incurredRecoveryApplicableField = new FieldImpl(
				"incurredRecoveryApplicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Tranche input) {
			FieldValue attachmentPoint = new FieldValueImpl(attachmentPointField, Optional.ofNullable(input.getAttachmentPoint()));
			FieldValue exhaustionPoint = new FieldValueImpl(exhaustionPointField, Optional.ofNullable(input.getExhaustionPoint()));
			FieldValue incurredRecoveryApplicable = new FieldValueImpl(incurredRecoveryApplicableField, Optional.ofNullable(input.getIncurredRecoveryApplicable()));
			return Arrays.asList(
				attachmentPoint,
				exhaustionPoint,
				incurredRecoveryApplicable
			);
		}
	}
}
