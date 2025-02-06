package cdm.base.staticdata.party.tabulator;

import cdm.base.staticdata.party.AncillaryEntity;
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


@ImplementedBy(AncillaryEntityTypeTabulator.Impl.class)
public interface AncillaryEntityTypeTabulator extends Tabulator<AncillaryEntity> {
	@Singleton
	class Impl implements AncillaryEntityTypeTabulator {
		private final Field ancillaryPartyField;
		private final Field legalEntityField;

		private final LegalEntityTypeTabulator legalEntityTypeTabulator;

		@Inject
		public Impl(LegalEntityTypeTabulator legalEntityTypeTabulator) {
			this.legalEntityTypeTabulator = legalEntityTypeTabulator;
			this.ancillaryPartyField = new FieldImpl(
				"ancillaryParty",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.legalEntityField = new FieldImpl(
				"legalEntity",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(AncillaryEntity input) {
			FieldValue ancillaryParty = new FieldValueImpl(ancillaryPartyField, Optional.ofNullable(input.getAncillaryParty()));
			FieldValue legalEntity = Optional.ofNullable(input.getLegalEntity())
				.map(x -> new NestedFieldValueImpl(legalEntityField, Optional.of(legalEntityTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(legalEntityField, Optional.empty()));
			return Arrays.asList(
				ancillaryParty,
				legalEntity
			);
		}
	}
}
