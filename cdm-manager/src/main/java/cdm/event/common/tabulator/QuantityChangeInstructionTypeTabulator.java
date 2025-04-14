package cdm.event.common.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.event.common.QuantityChangeInstruction;
import cdm.observable.asset.tabulator.PriceQuantityTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(QuantityChangeInstructionTypeTabulator.Impl.class)
public interface QuantityChangeInstructionTypeTabulator extends Tabulator<QuantityChangeInstruction> {
	@Singleton
	class Impl implements QuantityChangeInstructionTypeTabulator {
		private final Field changeField;
		private final Field directionField;
		private final Field lotIdentifierField;

		private final PriceQuantityTypeTabulator priceQuantityTypeTabulator;
		private final IdentifierTypeTabulator identifierTypeTabulator;

		@Inject
		public Impl(PriceQuantityTypeTabulator priceQuantityTypeTabulator, IdentifierTypeTabulator identifierTypeTabulator) {
			this.priceQuantityTypeTabulator = priceQuantityTypeTabulator;
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.changeField = new FieldImpl(
				"change",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.directionField = new FieldImpl(
				"direction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lotIdentifierField = new FieldImpl(
				"lotIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(QuantityChangeInstruction input) {
			FieldValue change = Optional.ofNullable(input.getChange())
				.map(x -> x.stream()
					.map(_x -> priceQuantityTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(changeField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(changeField, Optional.empty()));
			FieldValue direction = new FieldValueImpl(directionField, Optional.ofNullable(input.getDirection()));
			FieldValue lotIdentifier = Optional.ofNullable(input.getLotIdentifier())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(lotIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(lotIdentifierField, Optional.empty()));
			return Arrays.asList(
				change,
				direction,
				lotIdentifier
			);
		}
	}
}
