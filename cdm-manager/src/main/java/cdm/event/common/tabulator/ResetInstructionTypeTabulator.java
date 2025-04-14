package cdm.event.common.tabulator;

import cdm.event.common.ResetInstruction;
import cdm.product.template.tabulator.PayoutTypeTabulator;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ResetInstructionTypeTabulator.Impl.class)
public interface ResetInstructionTypeTabulator extends Tabulator<ResetInstruction> {
	@Singleton
	class Impl implements ResetInstructionTypeTabulator {
		private final Field payoutField;
		private final Field rateRecordDateField;
		private final Field resetDateField;

		private final PayoutTypeTabulator payoutTypeTabulator;

		@Inject
		public Impl(PayoutTypeTabulator payoutTypeTabulator) {
			this.payoutTypeTabulator = payoutTypeTabulator;
			this.payoutField = new FieldImpl(
				"payout",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.rateRecordDateField = new FieldImpl(
				"rateRecordDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.resetDateField = new FieldImpl(
				"resetDate",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ResetInstruction input) {
			FieldValue payout = Optional.ofNullable(input.getPayout())
				.map(x -> x.stream()
					.map(_x -> _x.getValue())
					.filter(Objects::nonNull)
					.map(_x -> payoutTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(payoutField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(payoutField, Optional.empty()));
			FieldValue rateRecordDate = new FieldValueImpl(rateRecordDateField, Optional.ofNullable(input.getRateRecordDate()));
			FieldValue resetDate = new FieldValueImpl(resetDateField, Optional.ofNullable(input.getResetDate()));
			return Arrays.asList(
				payout,
				rateRecordDate,
				resetDate
			);
		}
	}
}
