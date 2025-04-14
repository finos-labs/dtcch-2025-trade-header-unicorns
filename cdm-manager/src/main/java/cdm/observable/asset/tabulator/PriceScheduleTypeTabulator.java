package cdm.observable.asset.tabulator;

import cdm.base.math.tabulator.DatedValueTypeTabulator;
import cdm.base.math.tabulator.UnitTypeTypeTabulator;
import cdm.observable.asset.PriceSchedule;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(PriceScheduleTypeTabulator.Impl.class)
public interface PriceScheduleTypeTabulator extends Tabulator<PriceSchedule> {
	@Singleton
	class Impl implements PriceScheduleTypeTabulator {
		private final Field valueField;
		private final Field unitField;
		private final Field datedValueField;
		private final Field perUnitOfField;
		private final Field priceTypeField;
		private final Field priceExpressionField;
		private final Field compositeField;
		private final Field arithmeticOperatorField;
		private final Field cashPriceField;

		private final UnitTypeTypeTabulator unitTypeTypeTabulator;
		private final DatedValueTypeTabulator datedValueTypeTabulator;
		private final PriceCompositeTypeTabulator priceCompositeTypeTabulator;
		private final CashPriceTypeTabulator cashPriceTypeTabulator;

		@Inject
		public Impl(UnitTypeTypeTabulator unitTypeTypeTabulator, DatedValueTypeTabulator datedValueTypeTabulator, PriceCompositeTypeTabulator priceCompositeTypeTabulator, CashPriceTypeTabulator cashPriceTypeTabulator) {
			this.unitTypeTypeTabulator = unitTypeTypeTabulator;
			this.datedValueTypeTabulator = datedValueTypeTabulator;
			this.priceCompositeTypeTabulator = priceCompositeTypeTabulator;
			this.cashPriceTypeTabulator = cashPriceTypeTabulator;
			this.valueField = new FieldImpl(
				"value",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.unitField = new FieldImpl(
				"unit",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.datedValueField = new FieldImpl(
				"datedValue",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.perUnitOfField = new FieldImpl(
				"perUnitOf",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceTypeField = new FieldImpl(
				"priceType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.priceExpressionField = new FieldImpl(
				"priceExpression",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.compositeField = new FieldImpl(
				"composite",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.arithmeticOperatorField = new FieldImpl(
				"arithmeticOperator",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.cashPriceField = new FieldImpl(
				"cashPrice",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(PriceSchedule input) {
			FieldValue value = new FieldValueImpl(valueField, Optional.ofNullable(input.getValue()));
			FieldValue unit = Optional.ofNullable(input.getUnit())
				.map(x -> new NestedFieldValueImpl(unitField, Optional.of(unitTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(unitField, Optional.empty()));
			FieldValue datedValue = Optional.ofNullable(input.getDatedValue())
				.map(x -> x.stream()
					.map(_x -> datedValueTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(datedValueField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(datedValueField, Optional.empty()));
			FieldValue perUnitOf = Optional.ofNullable(input.getPerUnitOf())
				.map(x -> new NestedFieldValueImpl(perUnitOfField, Optional.of(unitTypeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(perUnitOfField, Optional.empty()));
			FieldValue priceType = new FieldValueImpl(priceTypeField, Optional.ofNullable(input.getPriceType()));
			FieldValue priceExpression = new FieldValueImpl(priceExpressionField, Optional.ofNullable(input.getPriceExpression()));
			FieldValue composite = Optional.ofNullable(input.getComposite())
				.map(x -> new NestedFieldValueImpl(compositeField, Optional.of(priceCompositeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(compositeField, Optional.empty()));
			FieldValue arithmeticOperator = new FieldValueImpl(arithmeticOperatorField, Optional.ofNullable(input.getArithmeticOperator()));
			FieldValue cashPrice = Optional.ofNullable(input.getCashPrice())
				.map(x -> new NestedFieldValueImpl(cashPriceField, Optional.of(cashPriceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(cashPriceField, Optional.empty()));
			return Arrays.asList(
				value,
				unit,
				datedValue,
				perUnitOf,
				priceType,
				priceExpression,
				composite,
				arithmeticOperator,
				cashPrice
			);
		}
	}
}
