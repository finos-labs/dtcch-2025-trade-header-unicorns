package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.ContractualMatrix;
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


@ImplementedBy(ContractualMatrixTypeTabulator.Impl.class)
public interface ContractualMatrixTypeTabulator extends Tabulator<ContractualMatrix> {
	@Singleton
	class Impl implements ContractualMatrixTypeTabulator {
		private final Field matrixTypeField;
		private final Field matrixTermField;

		public Impl() {
			this.matrixTypeField = new FieldImpl(
				"matrixType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.matrixTermField = new FieldImpl(
				"matrixTerm",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ContractualMatrix input) {
			FieldValue matrixType = new FieldValueImpl(matrixTypeField, Optional.ofNullable(input.getMatrixType())
				.map(x -> x.getValue()));
			FieldValue matrixTerm = new FieldValueImpl(matrixTermField, Optional.ofNullable(input.getMatrixTerm())
				.map(x -> x.getValue()));
			return Arrays.asList(
				matrixType,
				matrixTerm
			);
		}
	}
}
