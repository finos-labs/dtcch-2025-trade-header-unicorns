package cdm.product.collateral.tabulator;

import cdm.product.collateral.CollateralValuationTreatment;
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


@ImplementedBy(CollateralValuationTreatmentTypeTabulator.Impl.class)
public interface CollateralValuationTreatmentTypeTabulator extends Tabulator<CollateralValuationTreatment> {
	@Singleton
	class Impl implements CollateralValuationTreatmentTypeTabulator {
		private final Field haircutPercentageField;
		private final Field marginPercentageField;
		private final Field fxHaircutPercentageField;
		private final Field additionalHaircutPercentageField;

		public Impl() {
			this.haircutPercentageField = new FieldImpl(
				"haircutPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.marginPercentageField = new FieldImpl(
				"marginPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.fxHaircutPercentageField = new FieldImpl(
				"fxHaircutPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalHaircutPercentageField = new FieldImpl(
				"additionalHaircutPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralValuationTreatment input) {
			FieldValue haircutPercentage = new FieldValueImpl(haircutPercentageField, Optional.ofNullable(input.getHaircutPercentage()));
			FieldValue marginPercentage = new FieldValueImpl(marginPercentageField, Optional.ofNullable(input.getMarginPercentage()));
			FieldValue fxHaircutPercentage = new FieldValueImpl(fxHaircutPercentageField, Optional.ofNullable(input.getFxHaircutPercentage()));
			FieldValue additionalHaircutPercentage = new FieldValueImpl(additionalHaircutPercentageField, Optional.ofNullable(input.getAdditionalHaircutPercentage()));
			return Arrays.asList(
				haircutPercentage,
				marginPercentage,
				fxHaircutPercentage,
				additionalHaircutPercentage
			);
		}
	}
}
