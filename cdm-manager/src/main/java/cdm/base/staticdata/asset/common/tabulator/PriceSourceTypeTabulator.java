package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.PriceSource;
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


@ImplementedBy(PriceSourceTypeTabulator.Impl.class)
public interface PriceSourceTypeTabulator extends Tabulator<PriceSource> {
	@Singleton
	class Impl implements PriceSourceTypeTabulator {
		private final Field pricePublisherField;
		private final Field priceSourceLocationField;
		private final Field priceSourceHeadingField;
		private final Field priceSourceTimeField;

		public Impl() {
			this.pricePublisherField = new FieldImpl(
				"pricePublisher",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceSourceLocationField = new FieldImpl(
				"priceSourceLocation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceSourceHeadingField = new FieldImpl(
				"priceSourceHeading",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceSourceTimeField = new FieldImpl(
				"priceSourceTime",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceSource input) {
			FieldValue pricePublisher = new FieldValueImpl(pricePublisherField, Optional.ofNullable(input.getPricePublisher())
				.map(x -> x.getValue()));
			FieldValue priceSourceLocation = new FieldValueImpl(priceSourceLocationField, Optional.ofNullable(input.getPriceSourceLocation()));
			FieldValue priceSourceHeading = new FieldValueImpl(priceSourceHeadingField, Optional.ofNullable(input.getPriceSourceHeading()));
			FieldValue priceSourceTime = new FieldValueImpl(priceSourceTimeField, Optional.ofNullable(input.getPriceSourceTime()));
			return Arrays.asList(
				pricePublisher,
				priceSourceLocation,
				priceSourceHeading,
				priceSourceTime
			);
		}
	}
}
