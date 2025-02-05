package cdm.product.asset.tabulator;

import cdm.product.asset.FloatingAmountProvisions;
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


@ImplementedBy(FloatingAmountProvisionsTypeTabulator.Impl.class)
public interface FloatingAmountProvisionsTypeTabulator extends Tabulator<FloatingAmountProvisions> {
	@Singleton
	class Impl implements FloatingAmountProvisionsTypeTabulator {
		private final Field wacCapInterestProvisionField;
		private final Field stepUpProvisionField;

		public Impl() {
			this.wacCapInterestProvisionField = new FieldImpl(
				"wacCapInterestProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stepUpProvisionField = new FieldImpl(
				"stepUpProvision",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FloatingAmountProvisions input) {
			FieldValue wacCapInterestProvision = new FieldValueImpl(wacCapInterestProvisionField, Optional.ofNullable(input.getWacCapInterestProvision()));
			FieldValue stepUpProvision = new FieldValueImpl(stepUpProvisionField, Optional.ofNullable(input.getStepUpProvision()));
			return Arrays.asList(
				wacCapInterestProvision,
				stepUpProvision
			);
		}
	}
}
