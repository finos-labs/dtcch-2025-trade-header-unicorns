package cdm.product.asset.tabulator;

import cdm.observable.asset.tabulator.PriceScheduleTypeTabulator;
import cdm.product.asset.SpreadSchedule;
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


@ImplementedBy(SpreadScheduleTypeTabulator.Impl.class)
public interface SpreadScheduleTypeTabulator extends Tabulator<SpreadSchedule> {
	@Singleton
	class Impl implements SpreadScheduleTypeTabulator {
		private final Field priceField;
		private final Field spreadScheduleTypeField;

		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;

		@Inject
		public Impl(PriceScheduleTypeTabulator priceScheduleTypeTabulator) {
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.priceField = new FieldImpl(
				"price",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.spreadScheduleTypeField = new FieldImpl(
				"spreadScheduleType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SpreadSchedule input) {
			FieldValue price = Optional.ofNullable(input.getPrice())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(priceField, Optional.of(priceScheduleTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(priceField, Optional.empty()));
			FieldValue spreadScheduleType = new FieldValueImpl(spreadScheduleTypeField, Optional.ofNullable(input.getSpreadScheduleType())
				.map(x -> x.getValue()));
			return Arrays.asList(
				price,
				spreadScheduleType
			);
		}
	}
}
