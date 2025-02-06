package cdm.observable.asset.tabulator;

import cdm.observable.asset.FxSpotRateSource;
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


@ImplementedBy(FxSpotRateSourceTypeTabulator.Impl.class)
public interface FxSpotRateSourceTypeTabulator extends Tabulator<FxSpotRateSource> {
	@Singleton
	class Impl implements FxSpotRateSourceTypeTabulator {
		private final Field primarySourceField;
		private final Field secondarySourceField;

		private final InformationSourceTypeTabulator informationSourceTypeTabulator;

		@Inject
		public Impl(InformationSourceTypeTabulator informationSourceTypeTabulator) {
			this.informationSourceTypeTabulator = informationSourceTypeTabulator;
			this.primarySourceField = new FieldImpl(
				"primarySource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.secondarySourceField = new FieldImpl(
				"secondarySource",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(FxSpotRateSource input) {
			FieldValue primarySource = Optional.ofNullable(input.getPrimarySource())
				.map(x -> new NestedFieldValueImpl(primarySourceField, Optional.of(informationSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(primarySourceField, Optional.empty()));
			FieldValue secondarySource = Optional.ofNullable(input.getSecondarySource())
				.map(x -> new NestedFieldValueImpl(secondarySourceField, Optional.of(informationSourceTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(secondarySourceField, Optional.empty()));
			return Arrays.asList(
				primarySource,
				secondarySource
			);
		}
	}
}
