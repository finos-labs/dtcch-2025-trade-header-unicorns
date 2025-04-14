package cdm.base.staticdata.asset.common.tabulator;

import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
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


@ImplementedBy(RegionalGovernmentIssuerTypeTypeTabulator.Impl.class)
public interface RegionalGovernmentIssuerTypeTypeTabulator extends Tabulator<RegionalGovernmentIssuerType> {
	@Singleton
	class Impl implements RegionalGovernmentIssuerTypeTypeTabulator {
		private final Field sovereignRecourseField;

		public Impl() {
			this.sovereignRecourseField = new FieldImpl(
				"sovereignRecourse",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(RegionalGovernmentIssuerType input) {
			FieldValue sovereignRecourse = new FieldValueImpl(sovereignRecourseField, Optional.ofNullable(input.getSovereignRecourse()));
			return Arrays.asList(
				sovereignRecourse
			);
		}
	}
}
