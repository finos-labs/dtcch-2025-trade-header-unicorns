package cdm.product.asset.tabulator;

import cdm.base.staticdata.asset.common.tabulator.LoanTypeTabulator;
import cdm.base.staticdata.asset.common.tabulator.SecurityTypeTabulator;
import cdm.base.staticdata.party.tabulator.LegalEntityTypeTabulator;
import cdm.product.asset.ReferenceObligation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ReferenceObligationTypeTabulator.Impl.class)
public interface ReferenceObligationTypeTabulator extends Tabulator<ReferenceObligation> {
	@Singleton
	class Impl implements ReferenceObligationTypeTabulator {
		private final Field securityField;
		private final Field loanField;
		private final Field primaryObligorField;
		private final Field primaryObligorReferenceField;
		private final Field guarantorField;
		private final Field guarantorReferenceField;
		private final Field standardReferenceObligationField;

		private final SecurityTypeTabulator securityTypeTabulator;
		private final LoanTypeTabulator loanTypeTabulator;
		private final LegalEntityTypeTabulator legalEntityTypeTabulator;

		@Inject
		public Impl(SecurityTypeTabulator securityTypeTabulator, LoanTypeTabulator loanTypeTabulator, LegalEntityTypeTabulator legalEntityTypeTabulator) {
			this.securityTypeTabulator = securityTypeTabulator;
			this.loanTypeTabulator = loanTypeTabulator;
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.securityField = new FieldImpl(
				"security",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.loanField = new FieldImpl(
				"loan",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.primaryObligorField = new FieldImpl(
				"primaryObligor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.primaryObligorReferenceField = new FieldImpl(
				"primaryObligorReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.guarantorField = new FieldImpl(
				"guarantor",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.guarantorReferenceField = new FieldImpl(
				"guarantorReference",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.standardReferenceObligationField = new FieldImpl(
				"standardReferenceObligation",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ReferenceObligation input) {
			FieldValue security = Optional.ofNullable(input.getSecurity())
				.map(x -> new NestedFieldValueImpl(securityField, Optional.of(securityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(securityField, Optional.empty()));
			FieldValue loan = Optional.ofNullable(input.getLoan())
				.map(x -> new NestedFieldValueImpl(loanField, Optional.of(loanTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(loanField, Optional.empty()));
			FieldValue primaryObligor = Optional.ofNullable(input.getPrimaryObligor())
				.map(x -> new NestedFieldValueImpl(primaryObligorField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(primaryObligorField, Optional.empty()));
			FieldValue primaryObligorReference = Optional.ofNullable(input.getPrimaryObligorReference())
				.map(x -> x.getValue())
				.map(x -> new NestedFieldValueImpl(primaryObligorReferenceField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(primaryObligorReferenceField, Optional.empty()));
			FieldValue guarantor = Optional.ofNullable(input.getGuarantor())
				.map(x -> new NestedFieldValueImpl(guarantorField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(guarantorField, Optional.empty()));
			FieldValue guarantorReference = new FieldValueImpl(guarantorReferenceField, Optional.ofNullable(input.getGuarantorReference()));
			FieldValue standardReferenceObligation = new FieldValueImpl(standardReferenceObligationField, Optional.ofNullable(input.getStandardReferenceObligation()));
			return Arrays.asList(
				security,
				loan,
				primaryObligor,
				primaryObligorReference,
				guarantor,
				guarantorReference,
				standardReferenceObligation
			);
		}
	}
}
