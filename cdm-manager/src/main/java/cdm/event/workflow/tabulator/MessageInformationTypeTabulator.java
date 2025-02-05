package cdm.event.workflow.tabulator;

import cdm.event.workflow.MessageInformation;
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


@ImplementedBy(MessageInformationTypeTabulator.Impl.class)
public interface MessageInformationTypeTabulator extends Tabulator<MessageInformation> {
	@Singleton
	class Impl implements MessageInformationTypeTabulator {
		private final Field messageIdField;
		private final Field sentByField;
		private final Field sentToField;
		private final Field copyToField;

		public Impl() {
			this.messageIdField = new FieldImpl(
				"messageId",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sentByField = new FieldImpl(
				"sentBy",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.sentToField = new FieldImpl(
				"sentTo",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.copyToField = new FieldImpl(
				"copyTo",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(MessageInformation input) {
			FieldValue messageId = new FieldValueImpl(messageIdField, Optional.ofNullable(input.getMessageId())
				.map(x -> x.getValue()));
			FieldValue sentBy = new FieldValueImpl(sentByField, Optional.ofNullable(input.getSentBy())
				.map(x -> x.getValue()));
			FieldValue sentTo = new FieldValueImpl(sentToField, Optional.ofNullable(input.getSentTo())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			FieldValue copyTo = new FieldValueImpl(copyToField, Optional.ofNullable(input.getCopyTo())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.collect(Collectors.toList())));
			return Arrays.asList(
				messageId,
				sentBy,
				sentTo,
				copyTo
			);
		}
	}
}
