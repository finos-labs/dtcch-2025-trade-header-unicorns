package cdm.event.common.tabulator;

import cdm.base.staticdata.identifier.tabulator.IdentifierTypeTabulator;
import cdm.event.common.CollateralPortfolio;
import cdm.legaldocumentation.common.tabulator.LegalAgreementTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(CollateralPortfolioTypeTabulator.Impl.class)
public interface CollateralPortfolioTypeTabulator extends Tabulator<CollateralPortfolio> {
	@Singleton
	class Impl implements CollateralPortfolioTypeTabulator {
		private final Field portfolioIdentifierField;
		private final Field collateralPositionField;
		private final Field collateralBalanceField;
		private final Field legalAgreementField;

		private final IdentifierTypeTabulator identifierTypeTabulator;
		private final CollateralPositionTypeTabulator collateralPositionTypeTabulator;
		private final CollateralBalanceTypeTabulator collateralBalanceTypeTabulator;
		private final LegalAgreementTypeTabulator legalAgreementTypeTabulator;

		@Inject
		public Impl(IdentifierTypeTabulator identifierTypeTabulator, CollateralPositionTypeTabulator collateralPositionTypeTabulator, CollateralBalanceTypeTabulator collateralBalanceTypeTabulator, LegalAgreementTypeTabulator legalAgreementTypeTabulator) {
			this.identifierTypeTabulator = identifierTypeTabulator;
			this.collateralPositionTypeTabulator = collateralPositionTypeTabulator;
			this.collateralBalanceTypeTabulator = collateralBalanceTypeTabulator;
			this.legalAgreementTypeTabulator = legalAgreementTypeTabulator;
			this.portfolioIdentifierField = new FieldImpl(
				"portfolioIdentifier",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralPositionField = new FieldImpl(
				"collateralPosition",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.collateralBalanceField = new FieldImpl(
				"collateralBalance",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.legalAgreementField = new FieldImpl(
				"legalAgreement",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(CollateralPortfolio input) {
			FieldValue portfolioIdentifier = Optional.ofNullable(input.getPortfolioIdentifier())
				.map(x -> new NestedFieldValueImpl(portfolioIdentifierField, Optional.of(identifierTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(portfolioIdentifierField, Optional.empty()));
			FieldValue collateralPosition = Optional.ofNullable(input.getCollateralPosition())
				.map(x -> x.stream()
					.map(_x -> collateralPositionTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(collateralPositionField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(collateralPositionField, Optional.empty()));
			FieldValue collateralBalance = Optional.ofNullable(input.getCollateralBalance())
				.map(x -> x.stream()
					.map(_x -> collateralBalanceTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(collateralBalanceField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(collateralBalanceField, Optional.empty()));
			FieldValue legalAgreement = Optional.ofNullable(input.getLegalAgreement())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(legalAgreementField, Optional.of(legalAgreementTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(legalAgreementField, Optional.empty()));
			return Arrays.asList(
				portfolioIdentifier,
				collateralPosition,
				collateralBalance,
				legalAgreement
			);
		}
	}
}
