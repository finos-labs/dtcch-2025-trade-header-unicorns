package cdm.product.template.tabulator;

import cdm.product.template.Strike;
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


@ImplementedBy(StrikeTypeTabulator.Impl.class)
public interface StrikeTypeTabulator extends Tabulator<Strike> {
	@Singleton
	class Impl implements StrikeTypeTabulator {
		private final Field strikeRateField;
		private final Field buyerField;
		private final Field sellerField;

		public Impl() {
			this.strikeRateField = new FieldImpl(
				"strikeRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
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
		}

		@Override
		public List<FieldValue> tabulate(Strike input) {
			FieldValue strikeRate = new FieldValueImpl(strikeRateField, Optional.ofNullable(input.getStrikeRate()));
			FieldValue buyer = new FieldValueImpl(buyerField, Optional.ofNullable(input.getBuyer()));
			FieldValue seller = new FieldValueImpl(sellerField, Optional.ofNullable(input.getSeller()));
			return Arrays.asList(
				strikeRate,
				buyer,
				seller
			);
		}
	}
}
