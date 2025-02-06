package cdm.product.asset.tabulator;

import cdm.product.asset.EquityUnderlierProvisions;
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


@ImplementedBy(EquityUnderlierProvisionsTypeTabulator.Impl.class)
public interface EquityUnderlierProvisionsTypeTabulator extends Tabulator<EquityUnderlierProvisions> {
	@Singleton
	class Impl implements EquityUnderlierProvisionsTypeTabulator {
		private final Field multipleExchangeIndexAnnexFallbackField;
		private final Field componentSecurityIndexAnnexFallbackField;
		private final Field localJurisdictionField;
		private final Field relevantJurisdictionField;

		public Impl() {
			this.multipleExchangeIndexAnnexFallbackField = new FieldImpl(
				"multipleExchangeIndexAnnexFallback",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.componentSecurityIndexAnnexFallbackField = new FieldImpl(
				"componentSecurityIndexAnnexFallback",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.localJurisdictionField = new FieldImpl(
				"localJurisdiction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.relevantJurisdictionField = new FieldImpl(
				"relevantJurisdiction",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(EquityUnderlierProvisions input) {
			FieldValue multipleExchangeIndexAnnexFallback = new FieldValueImpl(multipleExchangeIndexAnnexFallbackField, Optional.ofNullable(input.getMultipleExchangeIndexAnnexFallback()));
			FieldValue componentSecurityIndexAnnexFallback = new FieldValueImpl(componentSecurityIndexAnnexFallbackField, Optional.ofNullable(input.getComponentSecurityIndexAnnexFallback()));
			FieldValue localJurisdiction = new FieldValueImpl(localJurisdictionField, Optional.ofNullable(input.getLocalJurisdiction())
				.map(x -> x.getValue()));
			FieldValue relevantJurisdiction = new FieldValueImpl(relevantJurisdictionField, Optional.ofNullable(input.getRelevantJurisdiction())
				.map(x -> x.getValue()));
			return Arrays.asList(
				multipleExchangeIndexAnnexFallback,
				componentSecurityIndexAnnexFallback,
				localJurisdiction,
				relevantJurisdiction
			);
		}
	}
}
