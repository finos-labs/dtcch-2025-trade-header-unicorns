package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.ReferenceBanks;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ReferenceBanksTypeTabulator.Impl.class)
public interface ReferenceBanksTypeTabulator extends Tabulator<ReferenceBanks> {
	@Singleton
	class Impl implements ReferenceBanksTypeTabulator {
		private final Field referenceBankField;

		private final ReferenceBankTypeTabulator referenceBankTypeTabulator;

		@Inject
		public Impl(ReferenceBankTypeTabulator referenceBankTypeTabulator) {
			this.referenceBankTypeTabulator = referenceBankTypeTabulator;
			this.referenceBankField = new FieldImpl(
				"referenceBank",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferenceBanks input) {
			FieldValue referenceBank = Optional.ofNullable(input.getReferenceBank())
				.map(x -> x.stream()
					.map(_x -> referenceBankTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(referenceBankField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(referenceBankField, Optional.empty()));
			return Arrays.asList(
				referenceBank
			);
		}
	}
}
