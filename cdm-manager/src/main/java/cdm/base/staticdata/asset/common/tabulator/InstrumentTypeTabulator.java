package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.Instrument;
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


@ImplementedBy(InstrumentTypeTabulator.Impl.class)
public interface InstrumentTypeTabulator extends Tabulator<Instrument> {
	@Singleton
	class Impl implements InstrumentTypeTabulator {
		private final Field ListedDerivativeField;
		private final Field LoanField;
		private final Field SecurityField;

		private final ListedDerivativeTypeTabulator listedDerivativeTypeTabulator;
		private final LoanTypeTabulator loanTypeTabulator;
		private final SecurityTypeTabulator securityTypeTabulator;

		@Inject
		public Impl(ListedDerivativeTypeTabulator listedDerivativeTypeTabulator, LoanTypeTabulator loanTypeTabulator, SecurityTypeTabulator securityTypeTabulator) {
			this.listedDerivativeTypeTabulator = listedDerivativeTypeTabulator;
			this.loanTypeTabulator = loanTypeTabulator;
			this.securityTypeTabulator = securityTypeTabulator;
			this.ListedDerivativeField = new FieldImpl(
				"ListedDerivative",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.LoanField = new FieldImpl(
				"Loan",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.SecurityField = new FieldImpl(
				"Security",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Instrument input) {
			FieldValue ListedDerivative = Optional.ofNullable(input.getListedDerivative())
				.map(x -> new NestedFieldValueImpl(ListedDerivativeField, Optional.of(listedDerivativeTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(ListedDerivativeField, Optional.empty()));
			FieldValue Loan = Optional.ofNullable(input.getLoan())
				.map(x -> new NestedFieldValueImpl(LoanField, Optional.of(loanTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(LoanField, Optional.empty()));
			FieldValue Security = Optional.ofNullable(input.getSecurity())
				.map(x -> new NestedFieldValueImpl(SecurityField, Optional.of(securityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(SecurityField, Optional.empty()));
			return Arrays.asList(
				ListedDerivative,
				Loan,
				Security
			);
		}
	}
}
