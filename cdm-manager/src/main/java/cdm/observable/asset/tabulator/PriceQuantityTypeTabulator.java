package cdm.observable.asset.tabulator;

import cdm.base.datetime.tabulator.AdjustableOrRelativeDateTypeTabulator;
import cdm.base.math.tabulator.NonNegativeQuantityScheduleTypeTabulator;
import cdm.observable.asset.PriceQuantity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PriceQuantityTypeTabulator.Impl.class)
public interface PriceQuantityTypeTabulator extends Tabulator<PriceQuantity> {
	@Singleton
	class Impl implements PriceQuantityTypeTabulator {
		private final Field priceField;
		private final Field quantityField;
		private final Field observableField;
		private final Field effectiveDateField;

		private final PriceScheduleTypeTabulator priceScheduleTypeTabulator;
		private final NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator;
		private final ObservableTypeTabulator observableTypeTabulator;
		private final AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator;

		@Inject
		public Impl(PriceScheduleTypeTabulator priceScheduleTypeTabulator, NonNegativeQuantityScheduleTypeTabulator nonNegativeQuantityScheduleTypeTabulator, ObservableTypeTabulator observableTypeTabulator, AdjustableOrRelativeDateTypeTabulator adjustableOrRelativeDateTypeTabulator) {
			this.priceScheduleTypeTabulator = priceScheduleTypeTabulator;
			this.nonNegativeQuantityScheduleTypeTabulator = nonNegativeQuantityScheduleTypeTabulator;
			this.observableTypeTabulator = observableTypeTabulator;
			this.adjustableOrRelativeDateTypeTabulator = adjustableOrRelativeDateTypeTabulator;
			this.priceField = new FieldImpl(
				"price",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.quantityField = new FieldImpl(
				"quantity",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.observableField = new FieldImpl(
				"observable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.effectiveDateField = new FieldImpl(
				"effectiveDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceQuantity input) {
			FieldValue price = Optional.ofNullable(input.getPrice())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> priceScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(priceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(priceField, Optional.empty()));
			FieldValue quantity = Optional.ofNullable(input.getQuantity())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> nonNegativeQuantityScheduleTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(quantityField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(quantityField, Optional.empty()));
			FieldValue observable = Optional.ofNullable(input.getObservable())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(observableField, Optional.of(observableTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(observableField, Optional.empty()));
			FieldValue effectiveDate = Optional.ofNullable(input.getEffectiveDate())
				.map(x -> new NestedFieldValueImpl(effectiveDateField, Optional.of(adjustableOrRelativeDateTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(effectiveDateField, Optional.empty()));
			return Arrays.asList(
				price,
				quantity,
				observable,
				effectiveDate
			);
		}
	}
}
