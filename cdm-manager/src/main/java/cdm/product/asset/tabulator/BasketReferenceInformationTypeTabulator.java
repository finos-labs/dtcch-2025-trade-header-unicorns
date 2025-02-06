package cdm.product.asset.tabulator;

import cdm.product.asset.BasketReferenceInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(BasketReferenceInformationTypeTabulator.Impl.class)
public interface BasketReferenceInformationTypeTabulator extends Tabulator<BasketReferenceInformation> {
	@Singleton
	class Impl implements BasketReferenceInformationTypeTabulator {
		private final Field basketNameField;
		private final Field basketIdField;
		private final Field referencePoolField;
		private final Field nthToDefaultField;
		private final Field mthToDefaultField;
		private final Field trancheField;

		private final ReferencePoolTypeTabulator referencePoolTypeTabulator;
		private final TrancheTypeTabulator trancheTypeTabulator;

		@Inject
		public Impl(ReferencePoolTypeTabulator referencePoolTypeTabulator, TrancheTypeTabulator trancheTypeTabulator) {
			this.referencePoolTypeTabulator = referencePoolTypeTabulator;
			this.trancheTypeTabulator = trancheTypeTabulator;
			this.basketNameField = new FieldImpl(
				"basketName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.basketIdField = new FieldImpl(
				"basketId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.referencePoolField = new FieldImpl(
				"referencePool",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nthToDefaultField = new FieldImpl(
				"nthToDefault",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mthToDefaultField = new FieldImpl(
				"mthToDefault",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.trancheField = new FieldImpl(
				"tranche",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BasketReferenceInformation input) {
			FieldValue basketName = new FieldValueImpl(basketNameField, Optional.ofNullable(input.getBasketName())
				.map(x -> x.getValue()));
			FieldValue basketId = new FieldValueImpl(basketIdField, Optional.ofNullable(input.getBasketId())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue referencePool = Optional.ofNullable(input.getReferencePool())
				.map(x -> new NestedFieldValueImpl(referencePoolField, Optional.of(referencePoolTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(referencePoolField, Optional.empty()));
			FieldValue nthToDefault = new FieldValueImpl(nthToDefaultField, Optional.ofNullable(input.getNthToDefault()));
			FieldValue mthToDefault = new FieldValueImpl(mthToDefaultField, Optional.ofNullable(input.getMthToDefault()));
			FieldValue tranche = Optional.ofNullable(input.getTranche())
				.map(x -> new NestedFieldValueImpl(trancheField, Optional.of(trancheTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(trancheField, Optional.empty()));
			return Arrays.asList(
				basketName,
				basketId,
				referencePool,
				nthToDefault,
				mthToDefault,
				tranche
			);
		}
	}
}
