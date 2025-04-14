package cdm.product.collateral.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.event.common.tabulator.CollateralPortfolioTypeTabulator;
import cdm.product.collateral.Collateral;
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


@ImplementedBy(CollateralTypeTabulator.Impl.class)
public interface CollateralTypeTabulator extends Tabulator<Collateral> {
	@Singleton
	class Impl implements CollateralTypeTabulator {
		private final Field independentAmountField;
		private final Field portfolioIdentifierField;
		private final Field collateralPortfolioField;
		private final Field collateralProvisionsField;

		private final IndependentAmountTypeTabulator independentAmountTypeTabulator;
		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final CollateralPortfolioTypeTabulator collateralPortfolioTypeTabulator;
		private final CollateralProvisionsTypeTabulator collateralProvisionsTypeTabulator;

		@Inject
		public Impl(IndependentAmountTypeTabulator independentAmountTypeTabulator, IdentifierTypeTabulator identifierTypeTabulator, CollateralPortfolioTypeTabulator collateralPortfolioTypeTabulator, CollateralProvisionsTypeTabulator collateralProvisionsTypeTabulator) {
			this.independentAmountTypeTabulator = independentAmountTypeTabulator;
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.collateralPortfolioTypeTabulator = collateralPortfolioTypeTabulator;
			this.collateralProvisionsTypeTabulator = collateralProvisionsTypeTabulator;
			this.independentAmountField = new FieldImpl(
				"independentAmount",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.portfolioIdentifierField = new FieldImpl(
				"portfolioIdentifier",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralPortfolioField = new FieldImpl(
				"collateralPortfolio",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralProvisionsField = new FieldImpl(
				"collateralProvisions",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Collateral input) {
			FieldValue independentAmount = Optional.ofNullable(input.getIndependentAmount())
				.map(x -> new NestedFieldValueImpl(independentAmountField, Optional.of(independentAmountTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(independentAmountField, Optional.empty()));
			FieldValue portfolioIdentifier = Optional.ofNullable(input.getPortfolioIdentifier())
				.map(x -> x.stream()
					.map(_x -> identifierTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(portfolioIdentifierField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(portfolioIdentifierField, Optional.empty()));
			FieldValue collateralPortfolio = Optional.ofNullable(input.getCollateralPortfolio())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> collateralPortfolioTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(collateralPortfolioField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(collateralPortfolioField, Optional.empty()));
			FieldValue collateralProvisions = Optional.ofNullable(input.getCollateralProvisions())
				.map(x -> new NestedFieldValueImpl(collateralProvisionsField, Optional.of(collateralProvisionsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(collateralProvisionsField, Optional.empty()));
			return Arrays.asList(
				independentAmount,
				portfolioIdentifier,
				collateralPortfolio,
				collateralProvisions
			);
		}
	}
}
