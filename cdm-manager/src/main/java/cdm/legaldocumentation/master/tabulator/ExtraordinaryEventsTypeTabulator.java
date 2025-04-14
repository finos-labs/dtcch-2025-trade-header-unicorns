package cdm.legaldocumentation.master.tabulator;

import cdm.legaldocumentation.master.ExtraordinaryEvents;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.reports.Tabulator;
import com.rosetta.model.lib.reports.Tabulator.Field;
import com.rosetta.model.lib.reports.Tabulator.FieldImpl;
import com.rosetta.model.lib.reports.Tabulator.FieldValue;
import com.rosetta.model.lib.reports.Tabulator.FieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.MultiNestedFieldValueImpl;
import com.rosetta.model.lib.reports.Tabulator.NestedFieldValueImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;


@ImplementedBy(ExtraordinaryEventsTypeTabulator.Impl.class)
public interface ExtraordinaryEventsTypeTabulator extends Tabulator<ExtraordinaryEvents> {
	@Singleton
	class Impl implements ExtraordinaryEventsTypeTabulator {
		private final Field additionalBespokeTermsField;
		private final Field mergerEventsField;
		private final Field tenderOfferEventsField;
		private final Field compositionOfCombinedConsiderationField;
		private final Field indexAdjustmentEventsField;
		private final Field additionalDisruptionEventsField;
		private final Field failureToDeliverField;
		private final Field representationsField;
		private final Field nationalizationOrInsolvencyField;
		private final Field delistingField;

		private final ClauseTypeTabulator clauseTypeTabulator;
		private final EquityCorporateEventsTypeTabulator equityCorporateEventsTypeTabulator;
		private final IndexAdjustmentEventsTypeTabulator indexAdjustmentEventsTypeTabulator;
		private final AdditionalDisruptionEventsTypeTabulator additionalDisruptionEventsTypeTabulator;
		private final RepresentationsTypeTabulator representationsTypeTabulator;

		@Inject
		public Impl(ClauseTypeTabulator clauseTypeTabulator, EquityCorporateEventsTypeTabulator equityCorporateEventsTypeTabulator, IndexAdjustmentEventsTypeTabulator indexAdjustmentEventsTypeTabulator, AdditionalDisruptionEventsTypeTabulator additionalDisruptionEventsTypeTabulator, RepresentationsTypeTabulator representationsTypeTabulator) {
			this.clauseTypeTabulator = clauseTypeTabulator;
			this.equityCorporateEventsTypeTabulator = equityCorporateEventsTypeTabulator;
			this.indexAdjustmentEventsTypeTabulator = indexAdjustmentEventsTypeTabulator;
			this.additionalDisruptionEventsTypeTabulator = additionalDisruptionEventsTypeTabulator;
			this.representationsTypeTabulator = representationsTypeTabulator;
			this.additionalBespokeTermsField = new FieldImpl(
				"additionalBespokeTerms",
				true,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.mergerEventsField = new FieldImpl(
				"mergerEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.tenderOfferEventsField = new FieldImpl(
				"tenderOfferEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.compositionOfCombinedConsiderationField = new FieldImpl(
				"compositionOfCombinedConsideration",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.indexAdjustmentEventsField = new FieldImpl(
				"indexAdjustmentEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.additionalDisruptionEventsField = new FieldImpl(
				"additionalDisruptionEvents",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.failureToDeliverField = new FieldImpl(
				"failureToDeliver",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.representationsField = new FieldImpl(
				"representations",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.nationalizationOrInsolvencyField = new FieldImpl(
				"nationalizationOrInsolvency",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
			this.delistingField = new FieldImpl(
				"delisting",
				false,
				Optional.empty(),
				Optional.empty(),
				Arrays.asList()
			);
		}

		@Override
		public List<FieldValue> tabulate(ExtraordinaryEvents input) {
			FieldValue additionalBespokeTerms = Optional.ofNullable(input.getAdditionalBespokeTerms())
				.map(x -> x.stream()
					.map(_x -> clauseTypeTabulator.tabulate(_x))
					.collect(Collectors.toList()))
				.map(fieldValues -> new MultiNestedFieldValueImpl(additionalBespokeTermsField, Optional.of(fieldValues)))
				.orElse(new MultiNestedFieldValueImpl(additionalBespokeTermsField, Optional.empty()));
			FieldValue mergerEvents = Optional.ofNullable(input.getMergerEvents())
				.map(x -> new NestedFieldValueImpl(mergerEventsField, Optional.of(equityCorporateEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(mergerEventsField, Optional.empty()));
			FieldValue tenderOfferEvents = Optional.ofNullable(input.getTenderOfferEvents())
				.map(x -> new NestedFieldValueImpl(tenderOfferEventsField, Optional.of(equityCorporateEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(tenderOfferEventsField, Optional.empty()));
			FieldValue compositionOfCombinedConsideration = new FieldValueImpl(compositionOfCombinedConsiderationField, Optional.ofNullable(input.getCompositionOfCombinedConsideration()));
			FieldValue indexAdjustmentEvents = Optional.ofNullable(input.getIndexAdjustmentEvents())
				.map(x -> new NestedFieldValueImpl(indexAdjustmentEventsField, Optional.of(indexAdjustmentEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(indexAdjustmentEventsField, Optional.empty()));
			FieldValue additionalDisruptionEvents = Optional.ofNullable(input.getAdditionalDisruptionEvents())
				.map(x -> new NestedFieldValueImpl(additionalDisruptionEventsField, Optional.of(additionalDisruptionEventsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(additionalDisruptionEventsField, Optional.empty()));
			FieldValue failureToDeliver = new FieldValueImpl(failureToDeliverField, Optional.ofNullable(input.getFailureToDeliver()));
			FieldValue representations = Optional.ofNullable(input.getRepresentations())
				.map(x -> new NestedFieldValueImpl(representationsField, Optional.of(representationsTypeTabulator.tabulate(x))))
				.orElse(new NestedFieldValueImpl(representationsField, Optional.empty()));
			FieldValue nationalizationOrInsolvency = new FieldValueImpl(nationalizationOrInsolvencyField, Optional.ofNullable(input.getNationalizationOrInsolvency()));
			FieldValue delisting = new FieldValueImpl(delistingField, Optional.ofNullable(input.getDelisting()));
			return Arrays.asList(
				additionalBespokeTerms,
				mergerEvents,
				tenderOfferEvents,
				compositionOfCombinedConsideration,
				indexAdjustmentEvents,
				additionalDisruptionEvents,
				failureToDeliver,
				representations,
				nationalizationOrInsolvency,
				delisting
			);
		}
	}
}
