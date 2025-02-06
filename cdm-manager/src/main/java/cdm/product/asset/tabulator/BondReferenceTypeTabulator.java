package cdm.product.asset.tabulator;

import cdm.base.staticdata.asset.common.tabulator.SecurityTypeTabulator;
import cdm.product.asset.BondReference;
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


@ImplementedBy(BondReferenceTypeTabulator.Impl.class)
public interface BondReferenceTypeTabulator extends Tabulator<BondReference> {
	@Singleton
	class Impl implements BondReferenceTypeTabulator {
		private final Field bondField;
		private final Field conditionPrecedentBondField;
		private final Field discrepancyClauseField;
		private final Field couponRateField;

		private final SecurityTypeTabulator securityTypeTabulator;
		private final FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator;

		@Inject
		public Impl(SecurityTypeTabulator securityTypeTabulator, FixedRateSpecificationTypeTabulator fixedRateSpecificationTypeTabulator) {
			this.securityTypeTabulator = securityTypeTabulator;
			this.fixedRateSpecificationTypeTabulator = fixedRateSpecificationTypeTabulator;
			this.bondField = new FieldImpl(
				"bond",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.conditionPrecedentBondField = new FieldImpl(
				"conditionPrecedentBond",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.discrepancyClauseField = new FieldImpl(
				"discrepancyClause",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.couponRateField = new FieldImpl(
				"couponRate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(BondReference input) {
			FieldValue bond = Optional.ofNullable(input.getBond())
				.map(x -> new NestedFieldValueImpl(bondField, Optional.of(securityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(bondField, Optional.empty()));
			FieldValue conditionPrecedentBond = new FieldValueImpl(conditionPrecedentBondField, Optional.ofNullable(input.getConditionPrecedentBond()));
			FieldValue discrepancyClause = new FieldValueImpl(discrepancyClauseField, Optional.ofNullable(input.getDiscrepancyClause()));
			FieldValue couponRate = Optional.ofNullable(input.getCouponRate())
				.map(x -> new NestedFieldValueImpl(couponRateField, Optional.of(fixedRateSpecificationTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(couponRateField, Optional.empty()));
			return Arrays.asList(
				bond,
				conditionPrecedentBond,
				discrepancyClause,
				couponRate
			);
		}
	}
}
