package cdm.product.asset.tabulator;

import cdm.product.asset.DiscountingMethod;
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


@ImplementedBy(DiscountingMethodTypeTabulator.Impl.class)
public interface DiscountingMethodTypeTabulator extends Tabulator<DiscountingMethod> {
	@Singleton
	class Impl implements DiscountingMethodTypeTabulator {
		private final Field discountingTypeField;
		private final Field discountRateField;
		private final Field discountRateDayCountFractionField;

		public Impl() {
			this.discountingTypeField = new FieldImpl(
				"discountingType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discountRateField = new FieldImpl(
				"discountRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discountRateDayCountFractionField = new FieldImpl(
				"discountRateDayCountFraction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(DiscountingMethod input) {
			FieldValue discountingType = new FieldValueImpl(discountingTypeField, Optional.ofNullable(input.getDiscountingType()));
			FieldValue discountRate = new FieldValueImpl(discountRateField, Optional.ofNullable(input.getDiscountRate()));
			FieldValue discountRateDayCountFraction = new FieldValueImpl(discountRateDayCountFractionField, Optional.ofNullable(input.getDiscountRateDayCountFraction())
				.map(x -> x.getValue()));
			return Arrays.asList(
				discountingType,
				discountRate,
				discountRateDayCountFraction
			);
		}
	}
}
