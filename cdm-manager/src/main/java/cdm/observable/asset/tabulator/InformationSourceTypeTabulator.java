package cdm.observable.asset.tabulator;

import cdm.observable.asset.InformationSource;
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


@ImplementedBy(InformationSourceTypeTabulator.Impl.class)
public interface InformationSourceTypeTabulator extends Tabulator<InformationSource> {
	@Singleton
	class Impl implements InformationSourceTypeTabulator {
		private final Field sourceProviderField;
		private final Field sourcePageField;
		private final Field sourcePageHeadingField;

		public Impl() {
			this.sourceProviderField = new FieldImpl(
				"sourceProvider",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sourcePageField = new FieldImpl(
				"sourcePage",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sourcePageHeadingField = new FieldImpl(
				"sourcePageHeading",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(InformationSource input) {
			FieldValue sourceProvider = new FieldValueImpl(sourceProviderField, Optional.ofNullable(input.getSourceProvider())
				.map(x -> x.getValue()));
			FieldValue sourcePage = new FieldValueImpl(sourcePageField, Optional.ofNullable(input.getSourcePage())
				.map(x -> x.getValue()));
			FieldValue sourcePageHeading = new FieldValueImpl(sourcePageHeadingField, Optional.ofNullable(input.getSourcePageHeading()));
			return Arrays.asList(
				sourceProvider,
				sourcePage,
				sourcePageHeading
			);
		}
	}
}
