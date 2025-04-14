package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.BuyerSeller;
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


@ImplementedBy(BuyerSellerTypeTabulator.Impl.class)
public interface BuyerSellerTypeTabulator extends Tabulator<BuyerSeller> {
	@Singleton
	class Impl implements BuyerSellerTypeTabulator {
		private final Field buyerField;
		private final Field sellerField;

		public Impl() {
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
		public List<FieldValue> tabulate(BuyerSeller input) {
			FieldValue buyer = new FieldValueImpl(buyerField, Optional.ofNullable(input.getBuyer()));
			FieldValue seller = new FieldValueImpl(sellerField, Optional.ofNullable(input.getSeller()));
			return Arrays.asList(
				buyer,
				seller
			);
		}
	}
}
