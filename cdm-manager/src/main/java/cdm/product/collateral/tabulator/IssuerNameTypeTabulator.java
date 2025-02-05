package cdm.product.collateral.tabulator;

import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.product.collateral.IssuerName;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(IssuerNameTypeTabulator.Impl.class)
public interface IssuerNameTypeTabulator extends Tabulator<IssuerName> {
	@Singleton
	class Impl implements IssuerNameTypeTabulator {
		private final Field issuerNameField;

		private final LegalEntityTypeTabulator legalEntityTypeTabulator;

		@Inject
		public Impl(LegalEntityTypeTabulator legalEntityTypeTabulator) {
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.issuerNameField = new FieldImpl(
				"issuerName",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IssuerName input) {
			FieldValue issuerName = Optional.ofNullable(input.getIssuerName())
				.map(x -> new NestedFieldValueImpl(issuerNameField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(issuerNameField, Optional.empty()));
			return Arrays.asList(
				issuerName
			);
		}
	}
}
