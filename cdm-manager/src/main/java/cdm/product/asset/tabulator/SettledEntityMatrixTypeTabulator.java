package cdm.product.asset.tabulator;

import cdm.product.asset.SettledEntityMatrix;
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


@ImplementedBy(SettledEntityMatrixTypeTabulator.Impl.class)
public interface SettledEntityMatrixTypeTabulator extends Tabulator<SettledEntityMatrix> {
	@Singleton
	class Impl implements SettledEntityMatrixTypeTabulator {
		private final Field matrixSourceField;
		private final Field publicationDateField;

		public Impl() {
			this.matrixSourceField = new FieldImpl(
				"matrixSource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.publicationDateField = new FieldImpl(
				"publicationDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(SettledEntityMatrix input) {
			FieldValue matrixSource = new FieldValueImpl(matrixSourceField, Optional.ofNullable(input.getMatrixSource())
				.map(x -> x.getValue()));
			FieldValue publicationDate = new FieldValueImpl(publicationDateField, Optional.ofNullable(input.getPublicationDate()));
			return Arrays.asList(
				matrixSource,
				publicationDate
			);
		}
	}
}
