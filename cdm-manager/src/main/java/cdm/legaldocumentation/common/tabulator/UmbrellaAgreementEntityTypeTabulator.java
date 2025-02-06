package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Singleton;


@ImplementedBy(UmbrellaAgreementEntityTypeTabulator.Impl.class)
public interface UmbrellaAgreementEntityTypeTabulator extends Tabulator<UmbrellaAgreementEntity> {
	@Singleton
	class Impl implements UmbrellaAgreementEntityTypeTabulator {
		private final Field entityIdField;
		private final Field nameField;
		private final Field termsField;

		public Impl() {
			this.entityIdField = new FieldImpl(
				"entityId",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nameField = new FieldImpl(
				"name",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.termsField = new FieldImpl(
				"terms",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(UmbrellaAgreementEntity input) {
			FieldValue entityId = new FieldValueImpl(entityIdField, Optional.ofNullable(input.getEntityId())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName())
				.map(x -> x.getValue()));
			FieldValue terms = new FieldValueImpl(termsField, Optional.ofNullable(input.getTerms()));
			return Arrays.asList(
				entityId,
				name,
				terms
			);
		}
	}
}
