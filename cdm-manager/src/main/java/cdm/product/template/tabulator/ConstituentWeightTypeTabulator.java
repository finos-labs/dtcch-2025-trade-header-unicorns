package cdm.product.template.tabulator;

import cdm.product.template.ConstituentWeight;
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


@ImplementedBy(ConstituentWeightTypeTabulator.Impl.class)
public interface ConstituentWeightTypeTabulator extends Tabulator<ConstituentWeight> {
	@Singleton
	class Impl implements ConstituentWeightTypeTabulator {
		private final Field openUnitsField;
		private final Field basketPercentageField;

		public Impl() {
			this.openUnitsField = new FieldImpl(
				"openUnits",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.basketPercentageField = new FieldImpl(
				"basketPercentage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ConstituentWeight input) {
			FieldValue openUnits = new FieldValueImpl(openUnitsField, Optional.ofNullable(input.getOpenUnits()));
			FieldValue basketPercentage = new FieldValueImpl(basketPercentageField, Optional.ofNullable(input.getBasketPercentage()));
			return Arrays.asList(
				openUnits,
				basketPercentage
			);
		}
	}
}
