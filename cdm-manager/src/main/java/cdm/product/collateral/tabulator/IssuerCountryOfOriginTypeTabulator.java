package cdm.product.collateral.tabulator;

import cdm.product.collateral.IssuerCountryOfOrigin;
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


@ImplementedBy(IssuerCountryOfOriginTypeTabulator.Impl.class)
public interface IssuerCountryOfOriginTypeTabulator extends Tabulator<IssuerCountryOfOrigin> {
	@Singleton
	class Impl implements IssuerCountryOfOriginTypeTabulator {
		private final Field issuerCountryOfOriginField;

		public Impl() {
			this.issuerCountryOfOriginField = new FieldImpl(
				"issuerCountryOfOrigin",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(IssuerCountryOfOrigin input) {
			FieldValue issuerCountryOfOrigin = new FieldValueImpl(issuerCountryOfOriginField, Optional.ofNullable(input.getIssuerCountryOfOrigin()));
			return Arrays.asList(
				issuerCountryOfOrigin
			);
		}
	}
}
