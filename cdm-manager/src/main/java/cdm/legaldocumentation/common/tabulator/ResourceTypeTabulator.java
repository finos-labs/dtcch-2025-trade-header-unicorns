package cdm.legaldocumentation.common.tabulator;

import cdm.legaldocumentation.common.Resource;
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


@ImplementedBy(ResourceTypeTabulator.Impl.class)
public interface ResourceTypeTabulator extends Tabulator<Resource> {
	@Singleton
	class Impl implements ResourceTypeTabulator {
		private final Field resourceIdField;
		private final Field resourceTypeField;
		private final Field languageField;
		private final Field sizeInBytesField;
		private final Field lengthField;
		private final Field mimeTypeField;
		private final Field nameField;
		private final Field commentsField;
		private final Field stringField;
		private final Field urlField;

		private final ResourceLengthTypeTabulator resourceLengthTypeTabulator;

		@Inject
		public Impl(ResourceLengthTypeTabulator resourceLengthTypeTabulator) {
			this.resourceLengthTypeTabulator = resourceLengthTypeTabulator;
			this.resourceIdField = new FieldImpl(
				"resourceId",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resourceTypeField = new FieldImpl(
				"resourceType",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.languageField = new FieldImpl(
				"language",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sizeInBytesField = new FieldImpl(
				"sizeInBytes",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.lengthField = new FieldImpl(
				"length",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mimeTypeField = new FieldImpl(
				"mimeType",
				false,
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
			this.commentsField = new FieldImpl(
				"comments",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.stringField = new FieldImpl(
				"string",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.urlField = new FieldImpl(
				"url",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(Resource input) {
			FieldValue resourceId = new FieldValueImpl(resourceIdField, Optional.ofNullable(input.getResourceId())
				.map(x -> x.getValue()));
			FieldValue resourceType = new FieldValueImpl(resourceTypeField, Optional.ofNullable(input.getResourceType())
				.map(x -> x.getValue()));
			FieldValue language = new FieldValueImpl(languageField, Optional.ofNullable(input.getLanguage())
				.map(x -> x.getValue()));
			FieldValue sizeInBytes = new FieldValueImpl(sizeInBytesField, Optional.ofNullable(input.getSizeInBytes()));
			FieldValue length = Optional.ofNullable(input.getLength())
				.map(x -> new NestedFieldValueImpl(lengthField, Optional.of(resourceLengthTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(lengthField, Optional.empty()));
			FieldValue mimeType = new FieldValueImpl(mimeTypeField, Optional.ofNullable(input.getMimeType())
				.map(x -> x.getValue()));
			FieldValue name = new FieldValueImpl(nameField, Optional.ofNullable(input.getName()));
			FieldValue comments = new FieldValueImpl(commentsField, Optional.ofNullable(input.getComments()));
			FieldValue string = new FieldValueImpl(stringField, Optional.ofNullable(input.getString()));
			FieldValue url = new FieldValueImpl(urlField, Optional.ofNullable(input.getUrl()));
			return Arrays.asList(
				resourceId,
				resourceType,
				language,
				sizeInBytes,
				length,
				mimeType,
				name,
				comments,
				string,
				url
			);
		}
	}
}
