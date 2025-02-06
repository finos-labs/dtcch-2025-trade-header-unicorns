package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
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


@ImplementedBy(QuasiGovernmentIssuerTypeTypeTabulator.Impl.class)
public interface QuasiGovernmentIssuerTypeTypeTabulator extends Tabulator<QuasiGovernmentIssuerType> {
	@Singleton
	class Impl implements QuasiGovernmentIssuerTypeTypeTabulator {
		private final Field sovereignEntityField;
		private final Field sovereignRecourseField;

		public Impl() {
			this.sovereignEntityField = new FieldImpl(
				"sovereignEntity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sovereignRecourseField = new FieldImpl(
				"sovereignRecourse",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(QuasiGovernmentIssuerType input) {
			FieldValue sovereignEntity = new FieldValueImpl(sovereignEntityField, Optional.ofNullable(input.getSovereignEntity()));
			FieldValue sovereignRecourse = new FieldValueImpl(sovereignRecourseField, Optional.ofNullable(input.getSovereignRecourse()));
			return Arrays.asList(
				sovereignEntity,
				sovereignRecourse
			);
		}
	}
}
