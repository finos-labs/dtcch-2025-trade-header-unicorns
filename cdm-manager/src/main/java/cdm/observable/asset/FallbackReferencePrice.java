package cdm.observable.asset;

import cdm.observable.asset.CalculationAgent;
import cdm.observable.asset.CalculationAgent.CalculationAgentBuilder;
import cdm.observable.asset.FallbackReferencePrice;
import cdm.observable.asset.FallbackReferencePrice.FallbackReferencePriceBuilder;
import cdm.observable.asset.FallbackReferencePrice.FallbackReferencePriceBuilderImpl;
import cdm.observable.asset.FallbackReferencePrice.FallbackReferencePriceImpl;
import cdm.observable.asset.SettlementRateOptionEnum;
import cdm.observable.asset.ValuationPostponement;
import cdm.observable.asset.ValuationPostponement.ValuationPostponementBuilder;
import cdm.observable.asset.meta.FallbackReferencePriceMeta;
import cdm.observable.asset.metafields.FieldWithMetaSettlementRateOptionEnum;
import cdm.observable.asset.metafields.FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.annotations.RuneAttribute;
import com.rosetta.model.lib.annotations.RuneDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * The method, prioritised by the order it is listed in this element, to get a replacement rate for the disrupted settlement rate option.
 * @version ${project.version}
 */
@RosettaDataType(value="FallbackReferencePrice", builder=FallbackReferencePrice.FallbackReferencePriceBuilderImpl.class, version="${project.version}")
@RuneDataType(value="FallbackReferencePrice", model="Common Domain Model", builder=FallbackReferencePrice.FallbackReferencePriceBuilderImpl.class, version="${project.version}")
public interface FallbackReferencePrice extends RosettaModelObject {

	FallbackReferencePriceMeta metaData = new FallbackReferencePriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies how long to wait to get a quote from a settlement rate option upon a price source disruption.
	 */
	ValuationPostponement getValuationPostponement();
	/**
	 * This settlement rate option will be used in its place.
	 */
	List<? extends FieldWithMetaSettlementRateOptionEnum> getFallBackSettlementRateOption();
	/**
	 * Request rate quotes from the market. This element is set as type Empty in FpML. When present, the FpML synonym is mapped to a value True in the CDM.
	 */
	Boolean getFallbackSurveyValuationPostponement();
	/**
	 * The calculation agent will decide the rate.
	 */
	CalculationAgent getCalculationAgentDetermination();

	/*********************** Build Methods  ***********************/
	FallbackReferencePrice build();
	
	FallbackReferencePrice.FallbackReferencePriceBuilder toBuilder();
	
	static FallbackReferencePrice.FallbackReferencePriceBuilder builder() {
		return new FallbackReferencePrice.FallbackReferencePriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FallbackReferencePrice> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends FallbackReferencePrice> getType() {
		return FallbackReferencePrice.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("valuationPostponement"), processor, ValuationPostponement.class, getValuationPostponement());
		processRosetta(path.newSubPath("fallBackSettlementRateOption"), processor, FieldWithMetaSettlementRateOptionEnum.class, getFallBackSettlementRateOption());
		processor.processBasic(path.newSubPath("fallbackSurveyValuationPostponement"), Boolean.class, getFallbackSurveyValuationPostponement(), this);
		processRosetta(path.newSubPath("calculationAgentDetermination"), processor, CalculationAgent.class, getCalculationAgentDetermination());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FallbackReferencePriceBuilder extends FallbackReferencePrice, RosettaModelObjectBuilder {
		ValuationPostponement.ValuationPostponementBuilder getOrCreateValuationPostponement();
		@Override
		ValuationPostponement.ValuationPostponementBuilder getValuationPostponement();
		FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getOrCreateFallBackSettlementRateOption(int _index);
		@Override
		List<? extends FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder> getFallBackSettlementRateOption();
		CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgentDetermination();
		@Override
		CalculationAgent.CalculationAgentBuilder getCalculationAgentDetermination();
		FallbackReferencePrice.FallbackReferencePriceBuilder setValuationPostponement(ValuationPostponement valuationPostponement);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(FieldWithMetaSettlementRateOptionEnum fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(FieldWithMetaSettlementRateOptionEnum fallBackSettlementRateOption, int _idx);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(SettlementRateOptionEnum fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(SettlementRateOptionEnum fallBackSettlementRateOption, int _idx);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(List<? extends FieldWithMetaSettlementRateOptionEnum> fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder setFallBackSettlementRateOption(List<? extends FieldWithMetaSettlementRateOptionEnum> fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(List<? extends SettlementRateOptionEnum> fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder setFallBackSettlementRateOptionValue(List<? extends SettlementRateOptionEnum> fallBackSettlementRateOption);
		FallbackReferencePrice.FallbackReferencePriceBuilder setFallbackSurveyValuationPostponement(Boolean fallbackSurveyValuationPostponement);
		FallbackReferencePrice.FallbackReferencePriceBuilder setCalculationAgentDetermination(CalculationAgent calculationAgentDetermination);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("valuationPostponement"), processor, ValuationPostponement.ValuationPostponementBuilder.class, getValuationPostponement());
			processRosetta(path.newSubPath("fallBackSettlementRateOption"), processor, FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder.class, getFallBackSettlementRateOption());
			processor.processBasic(path.newSubPath("fallbackSurveyValuationPostponement"), Boolean.class, getFallbackSurveyValuationPostponement(), this);
			processRosetta(path.newSubPath("calculationAgentDetermination"), processor, CalculationAgent.CalculationAgentBuilder.class, getCalculationAgentDetermination());
		}
		

		FallbackReferencePrice.FallbackReferencePriceBuilder prune();
	}

	/*********************** Immutable Implementation of FallbackReferencePrice  ***********************/
	class FallbackReferencePriceImpl implements FallbackReferencePrice {
		private final ValuationPostponement valuationPostponement;
		private final List<? extends FieldWithMetaSettlementRateOptionEnum> fallBackSettlementRateOption;
		private final Boolean fallbackSurveyValuationPostponement;
		private final CalculationAgent calculationAgentDetermination;
		
		protected FallbackReferencePriceImpl(FallbackReferencePrice.FallbackReferencePriceBuilder builder) {
			this.valuationPostponement = ofNullable(builder.getValuationPostponement()).map(f->f.build()).orElse(null);
			this.fallBackSettlementRateOption = ofNullable(builder.getFallBackSettlementRateOption()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.fallbackSurveyValuationPostponement = builder.getFallbackSurveyValuationPostponement();
			this.calculationAgentDetermination = ofNullable(builder.getCalculationAgentDetermination()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("valuationPostponement")
		@RuneAttribute("valuationPostponement")
		public ValuationPostponement getValuationPostponement() {
			return valuationPostponement;
		}
		
		@Override
		@RosettaAttribute("fallBackSettlementRateOption")
		@RuneAttribute("fallBackSettlementRateOption")
		public List<? extends FieldWithMetaSettlementRateOptionEnum> getFallBackSettlementRateOption() {
			return fallBackSettlementRateOption;
		}
		
		@Override
		@RosettaAttribute("fallbackSurveyValuationPostponement")
		@RuneAttribute("fallbackSurveyValuationPostponement")
		public Boolean getFallbackSurveyValuationPostponement() {
			return fallbackSurveyValuationPostponement;
		}
		
		@Override
		@RosettaAttribute("calculationAgentDetermination")
		@RuneAttribute("calculationAgentDetermination")
		public CalculationAgent getCalculationAgentDetermination() {
			return calculationAgentDetermination;
		}
		
		@Override
		public FallbackReferencePrice build() {
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder toBuilder() {
			FallbackReferencePrice.FallbackReferencePriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FallbackReferencePrice.FallbackReferencePriceBuilder builder) {
			ofNullable(getValuationPostponement()).ifPresent(builder::setValuationPostponement);
			ofNullable(getFallBackSettlementRateOption()).ifPresent(builder::setFallBackSettlementRateOption);
			ofNullable(getFallbackSurveyValuationPostponement()).ifPresent(builder::setFallbackSurveyValuationPostponement);
			ofNullable(getCalculationAgentDetermination()).ifPresent(builder::setCalculationAgentDetermination);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FallbackReferencePrice _that = getType().cast(o);
		
			if (!Objects.equals(valuationPostponement, _that.getValuationPostponement())) return false;
			if (!ListEquals.listEquals(fallBackSettlementRateOption, _that.getFallBackSettlementRateOption())) return false;
			if (!Objects.equals(fallbackSurveyValuationPostponement, _that.getFallbackSurveyValuationPostponement())) return false;
			if (!Objects.equals(calculationAgentDetermination, _that.getCalculationAgentDetermination())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationPostponement != null ? valuationPostponement.hashCode() : 0);
			_result = 31 * _result + (fallBackSettlementRateOption != null ? fallBackSettlementRateOption.hashCode() : 0);
			_result = 31 * _result + (fallbackSurveyValuationPostponement != null ? fallbackSurveyValuationPostponement.hashCode() : 0);
			_result = 31 * _result + (calculationAgentDetermination != null ? calculationAgentDetermination.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FallbackReferencePrice {" +
				"valuationPostponement=" + this.valuationPostponement + ", " +
				"fallBackSettlementRateOption=" + this.fallBackSettlementRateOption + ", " +
				"fallbackSurveyValuationPostponement=" + this.fallbackSurveyValuationPostponement + ", " +
				"calculationAgentDetermination=" + this.calculationAgentDetermination +
			'}';
		}
	}

	/*********************** Builder Implementation of FallbackReferencePrice  ***********************/
	class FallbackReferencePriceBuilderImpl implements FallbackReferencePrice.FallbackReferencePriceBuilder {
	
		protected ValuationPostponement.ValuationPostponementBuilder valuationPostponement;
		protected List<FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder> fallBackSettlementRateOption = new ArrayList<>();
		protected Boolean fallbackSurveyValuationPostponement;
		protected CalculationAgent.CalculationAgentBuilder calculationAgentDetermination;
		
		@Override
		@RosettaAttribute("valuationPostponement")
		@RuneAttribute("valuationPostponement")
		public ValuationPostponement.ValuationPostponementBuilder getValuationPostponement() {
			return valuationPostponement;
		}
		
		@Override
		public ValuationPostponement.ValuationPostponementBuilder getOrCreateValuationPostponement() {
			ValuationPostponement.ValuationPostponementBuilder result;
			if (valuationPostponement!=null) {
				result = valuationPostponement;
			}
			else {
				result = valuationPostponement = ValuationPostponement.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("fallBackSettlementRateOption")
		@RuneAttribute("fallBackSettlementRateOption")
		public List<? extends FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder> getFallBackSettlementRateOption() {
			return fallBackSettlementRateOption;
		}
		
		@Override
		public FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder getOrCreateFallBackSettlementRateOption(int _index) {
		
			if (fallBackSettlementRateOption==null) {
				this.fallBackSettlementRateOption = new ArrayList<>();
			}
			FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder result;
			return getIndex(fallBackSettlementRateOption, _index, () -> {
						FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder newFallBackSettlementRateOption = FieldWithMetaSettlementRateOptionEnum.builder();
						return newFallBackSettlementRateOption;
					});
		}
		
		@Override
		@RosettaAttribute("fallbackSurveyValuationPostponement")
		@RuneAttribute("fallbackSurveyValuationPostponement")
		public Boolean getFallbackSurveyValuationPostponement() {
			return fallbackSurveyValuationPostponement;
		}
		
		@Override
		@RosettaAttribute("calculationAgentDetermination")
		@RuneAttribute("calculationAgentDetermination")
		public CalculationAgent.CalculationAgentBuilder getCalculationAgentDetermination() {
			return calculationAgentDetermination;
		}
		
		@Override
		public CalculationAgent.CalculationAgentBuilder getOrCreateCalculationAgentDetermination() {
			CalculationAgent.CalculationAgentBuilder result;
			if (calculationAgentDetermination!=null) {
				result = calculationAgentDetermination;
			}
			else {
				result = calculationAgentDetermination = CalculationAgent.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("valuationPostponement")
		@RuneAttribute("valuationPostponement")
		public FallbackReferencePrice.FallbackReferencePriceBuilder setValuationPostponement(ValuationPostponement _valuationPostponement) {
			this.valuationPostponement = _valuationPostponement == null ? null : _valuationPostponement.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("fallBackSettlementRateOption")
		@RuneAttribute("fallBackSettlementRateOption")
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(FieldWithMetaSettlementRateOptionEnum _fallBackSettlementRateOption) {
			if (_fallBackSettlementRateOption != null) {
				this.fallBackSettlementRateOption.add(_fallBackSettlementRateOption.toBuilder());
			}
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(FieldWithMetaSettlementRateOptionEnum _fallBackSettlementRateOption, int _idx) {
			getIndex(this.fallBackSettlementRateOption, _idx, () -> _fallBackSettlementRateOption.toBuilder());
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(SettlementRateOptionEnum _fallBackSettlementRateOption) {
			this.getOrCreateFallBackSettlementRateOption(-1).setValue(_fallBackSettlementRateOption);
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(SettlementRateOptionEnum _fallBackSettlementRateOption, int _idx) {
			this.getOrCreateFallBackSettlementRateOption(_idx).setValue(_fallBackSettlementRateOption);
			return this;
		}
		
		@Override 
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOption(List<? extends FieldWithMetaSettlementRateOptionEnum> fallBackSettlementRateOptions) {
			if (fallBackSettlementRateOptions != null) {
				for (final FieldWithMetaSettlementRateOptionEnum toAdd : fallBackSettlementRateOptions) {
					this.fallBackSettlementRateOption.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("fallBackSettlementRateOption")
		public FallbackReferencePrice.FallbackReferencePriceBuilder setFallBackSettlementRateOption(List<? extends FieldWithMetaSettlementRateOptionEnum> fallBackSettlementRateOptions) {
			if (fallBackSettlementRateOptions == null) {
				this.fallBackSettlementRateOption = new ArrayList<>();
			} else {
				this.fallBackSettlementRateOption = fallBackSettlementRateOptions.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder addFallBackSettlementRateOptionValue(List<? extends SettlementRateOptionEnum> fallBackSettlementRateOptions) {
			if (fallBackSettlementRateOptions != null) {
				for (final SettlementRateOptionEnum toAdd : fallBackSettlementRateOptions) {
					this.addFallBackSettlementRateOptionValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder setFallBackSettlementRateOptionValue(List<? extends SettlementRateOptionEnum> fallBackSettlementRateOptions) {
			this.fallBackSettlementRateOption.clear();
			if (fallBackSettlementRateOptions != null) {
				fallBackSettlementRateOptions.forEach(this::addFallBackSettlementRateOptionValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("fallbackSurveyValuationPostponement")
		@RuneAttribute("fallbackSurveyValuationPostponement")
		public FallbackReferencePrice.FallbackReferencePriceBuilder setFallbackSurveyValuationPostponement(Boolean _fallbackSurveyValuationPostponement) {
			this.fallbackSurveyValuationPostponement = _fallbackSurveyValuationPostponement == null ? null : _fallbackSurveyValuationPostponement;
			return this;
		}
		
		@Override
		@RosettaAttribute("calculationAgentDetermination")
		@RuneAttribute("calculationAgentDetermination")
		public FallbackReferencePrice.FallbackReferencePriceBuilder setCalculationAgentDetermination(CalculationAgent _calculationAgentDetermination) {
			this.calculationAgentDetermination = _calculationAgentDetermination == null ? null : _calculationAgentDetermination.toBuilder();
			return this;
		}
		
		@Override
		public FallbackReferencePrice build() {
			return new FallbackReferencePrice.FallbackReferencePriceImpl(this);
		}
		
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder prune() {
			if (valuationPostponement!=null && !valuationPostponement.prune().hasData()) valuationPostponement = null;
			fallBackSettlementRateOption = fallBackSettlementRateOption.stream().filter(b->b!=null).<FieldWithMetaSettlementRateOptionEnum.FieldWithMetaSettlementRateOptionEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (calculationAgentDetermination!=null && !calculationAgentDetermination.prune().hasData()) calculationAgentDetermination = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValuationPostponement()!=null && getValuationPostponement().hasData()) return true;
			if (getFallBackSettlementRateOption()!=null && !getFallBackSettlementRateOption().isEmpty()) return true;
			if (getFallbackSurveyValuationPostponement()!=null) return true;
			if (getCalculationAgentDetermination()!=null && getCalculationAgentDetermination().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FallbackReferencePrice.FallbackReferencePriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FallbackReferencePrice.FallbackReferencePriceBuilder o = (FallbackReferencePrice.FallbackReferencePriceBuilder) other;
			
			merger.mergeRosetta(getValuationPostponement(), o.getValuationPostponement(), this::setValuationPostponement);
			merger.mergeRosetta(getFallBackSettlementRateOption(), o.getFallBackSettlementRateOption(), this::getOrCreateFallBackSettlementRateOption);
			merger.mergeRosetta(getCalculationAgentDetermination(), o.getCalculationAgentDetermination(), this::setCalculationAgentDetermination);
			
			merger.mergeBasic(getFallbackSurveyValuationPostponement(), o.getFallbackSurveyValuationPostponement(), this::setFallbackSurveyValuationPostponement);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FallbackReferencePrice _that = getType().cast(o);
		
			if (!Objects.equals(valuationPostponement, _that.getValuationPostponement())) return false;
			if (!ListEquals.listEquals(fallBackSettlementRateOption, _that.getFallBackSettlementRateOption())) return false;
			if (!Objects.equals(fallbackSurveyValuationPostponement, _that.getFallbackSurveyValuationPostponement())) return false;
			if (!Objects.equals(calculationAgentDetermination, _that.getCalculationAgentDetermination())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (valuationPostponement != null ? valuationPostponement.hashCode() : 0);
			_result = 31 * _result + (fallBackSettlementRateOption != null ? fallBackSettlementRateOption.hashCode() : 0);
			_result = 31 * _result + (fallbackSurveyValuationPostponement != null ? fallbackSurveyValuationPostponement.hashCode() : 0);
			_result = 31 * _result + (calculationAgentDetermination != null ? calculationAgentDetermination.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FallbackReferencePriceBuilder {" +
				"valuationPostponement=" + this.valuationPostponement + ", " +
				"fallBackSettlementRateOption=" + this.fallBackSettlementRateOption + ", " +
				"fallbackSurveyValuationPostponement=" + this.fallbackSurveyValuationPostponement + ", " +
				"calculationAgentDetermination=" + this.calculationAgentDetermination +
			'}';
		}
	}
}
