package cdm.observable.event.tabulator;

import cdm.base.datetime.tabulator.OffsetTypeTabulator;
import cdm.observable.event.GracePeriodExtension;
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


@ImplementedBy(GracePeriodExtensionTypeTabulator.Impl.class)
public interface GracePeriodExtensionTypeTabulator extends Tabulator<GracePeriodExtension> {
	@Singleton
	class Impl implements GracePeriodExtensionTypeTabulator {
		private final Field applicableField;
		private final Field gracePeriodField;

		private final OffsetTypeTabulator offsetTypeTabulator;

		@Inject
		public Impl(OffsetTypeTabulator offsetTypeTabulator) {
			this.offsetTypeTabulator = offsetTypeTabulator;
			this.applicableField = new FieldImpl(
				"applicable",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.gracePeriodField = new FieldImpl(
				"gracePeriod",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(GracePeriodExtension input) {
			FieldValue applicable = new FieldValueImpl(applicableField, Optional.ofNullable(input.getApplicable()));
			FieldValue gracePeriod = Optional.ofNullable(input.getGracePeriod())
				.map(x -> new NestedFieldValueImpl(gracePeriodField, Optional.of(offsetTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(gracePeriodField, Optional.empty()));
			return Arrays.asList(
				applicable,
				gracePeriod
			);
		}
	}
}
