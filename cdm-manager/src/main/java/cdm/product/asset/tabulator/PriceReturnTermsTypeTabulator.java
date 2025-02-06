package cdm.product.asset.tabulator;

import cdm.product.asset.PriceReturnTerms;
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


@ImplementedBy(PriceReturnTermsTypeTabulator.Impl.class)
public interface PriceReturnTermsTypeTabulator extends Tabulator<PriceReturnTerms> {
	@Singleton
	class Impl implements PriceReturnTermsTypeTabulator {
		private final Field returnTypeField;
		private final Field conversionFactorField;
		private final Field performanceField;

		public Impl() {
			this.returnTypeField = new FieldImpl(
				"returnType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.conversionFactorField = new FieldImpl(
				"conversionFactor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.performanceField = new FieldImpl(
				"performance",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceReturnTerms input) {
			FieldValue returnType = new FieldValueImpl(returnTypeField, Optional.ofNullable(input.getReturnType()));
			FieldValue conversionFactor = new FieldValueImpl(conversionFactorField, Optional.ofNullable(input.getConversionFactor()));
			FieldValue performance = new FieldValueImpl(performanceField, Optional.ofNullable(input.getPerformance()));
			return Arrays.asList(
				returnType,
				conversionFactor,
				performance
			);
		}
	}
}
