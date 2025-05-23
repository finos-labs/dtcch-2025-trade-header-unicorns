package cdm.observable.asset.calculatedrate;

import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilderImpl;
import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsImpl;
import cdm.observable.asset.calculatedrate.meta.CalculatedRateObservationDatesAndWeightsMeta;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Type for reporting the observations dates and the corresponding weights going into a daily calculated rate
 * @version ${project.version}
 */
@RosettaDataType(value="CalculatedRateObservationDatesAndWeights", builder=CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilderImpl.class, version="${project.version}")
@RuneDataType(value="CalculatedRateObservationDatesAndWeights", model="Common Domain Model", builder=CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilderImpl.class, version="${project.version}")
public interface CalculatedRateObservationDatesAndWeights extends RosettaModelObject {

	CalculatedRateObservationDatesAndWeightsMeta metaData = new CalculatedRateObservationDatesAndWeightsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The observation date upon which the rate is observed.
	 */
	List<Date> getObservationDates();
	/**
	 * The corresponding weight for each date.
	 */
	List<BigDecimal> getWeights();

	/*********************** Build Methods  ***********************/
	CalculatedRateObservationDatesAndWeights build();
	
	CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder toBuilder();
	
	static CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder builder() {
		return new CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculatedRateObservationDatesAndWeights> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends CalculatedRateObservationDatesAndWeights> getType() {
		return CalculatedRateObservationDatesAndWeights.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("observationDates"), Date.class, getObservationDates(), this);
		processor.processBasic(path.newSubPath("weights"), BigDecimal.class, getWeights(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculatedRateObservationDatesAndWeightsBuilder extends CalculatedRateObservationDatesAndWeights, RosettaModelObjectBuilder {
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(Date observationDates);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(Date observationDates, int _idx);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(List<Date> observationDates);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder setObservationDates(List<Date> observationDates);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(BigDecimal weights);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(BigDecimal weights, int _idx);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(List<BigDecimal> weights);
		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder setWeights(List<BigDecimal> weights);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("observationDates"), Date.class, getObservationDates(), this);
			processor.processBasic(path.newSubPath("weights"), BigDecimal.class, getWeights(), this);
		}
		

		CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder prune();
	}

	/*********************** Immutable Implementation of CalculatedRateObservationDatesAndWeights  ***********************/
	class CalculatedRateObservationDatesAndWeightsImpl implements CalculatedRateObservationDatesAndWeights {
		private final List<Date> observationDates;
		private final List<BigDecimal> weights;
		
		protected CalculatedRateObservationDatesAndWeightsImpl(CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder builder) {
			this.observationDates = ofNullable(builder.getObservationDates()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.weights = ofNullable(builder.getWeights()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
		}
		
		@Override
		@RosettaAttribute("observationDates")
		@RuneAttribute("observationDates")
		public List<Date> getObservationDates() {
			return observationDates;
		}
		
		@Override
		@RosettaAttribute("weights")
		@RuneAttribute("weights")
		public List<BigDecimal> getWeights() {
			return weights;
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights build() {
			return this;
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder toBuilder() {
			CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder builder) {
			ofNullable(getObservationDates()).ifPresent(builder::setObservationDates);
			ofNullable(getWeights()).ifPresent(builder::setWeights);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculatedRateObservationDatesAndWeights _that = getType().cast(o);
		
			if (!ListEquals.listEquals(observationDates, _that.getObservationDates())) return false;
			if (!ListEquals.listEquals(weights, _that.getWeights())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (weights != null ? weights.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculatedRateObservationDatesAndWeights {" +
				"observationDates=" + this.observationDates + ", " +
				"weights=" + this.weights +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculatedRateObservationDatesAndWeights  ***********************/
	class CalculatedRateObservationDatesAndWeightsBuilderImpl implements CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder {
	
		protected List<Date> observationDates = new ArrayList<>();
		protected List<BigDecimal> weights = new ArrayList<>();
		
		@Override
		@RosettaAttribute("observationDates")
		@RuneAttribute("observationDates")
		public List<Date> getObservationDates() {
			return observationDates;
		}
		
		@Override
		@RosettaAttribute("weights")
		@RuneAttribute("weights")
		public List<BigDecimal> getWeights() {
			return weights;
		}
		
		@Override
		@RosettaAttribute("observationDates")
		@RuneAttribute("observationDates")
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(Date _observationDates) {
			if (_observationDates != null) {
				this.observationDates.add(_observationDates);
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(Date _observationDates, int _idx) {
			getIndex(this.observationDates, _idx, () -> _observationDates);
			return this;
		}
		
		@Override 
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addObservationDates(List<Date> observationDatess) {
			if (observationDatess != null) {
				for (final Date toAdd : observationDatess) {
					this.observationDates.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("observationDates")
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder setObservationDates(List<Date> observationDatess) {
			if (observationDatess == null) {
				this.observationDates = new ArrayList<>();
			} else {
				this.observationDates = observationDatess.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("weights")
		@RuneAttribute("weights")
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(BigDecimal _weights) {
			if (_weights != null) {
				this.weights.add(_weights);
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(BigDecimal _weights, int _idx) {
			getIndex(this.weights, _idx, () -> _weights);
			return this;
		}
		
		@Override 
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder addWeights(List<BigDecimal> weightss) {
			if (weightss != null) {
				for (final BigDecimal toAdd : weightss) {
					this.weights.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("weights")
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder setWeights(List<BigDecimal> weightss) {
			if (weightss == null) {
				this.weights = new ArrayList<>();
			} else {
				this.weights = weightss.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights build() {
			return new CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsImpl(this);
		}
		
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getObservationDates()!=null && !getObservationDates().isEmpty()) return true;
			if (getWeights()!=null && !getWeights().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder o = (CalculatedRateObservationDatesAndWeights.CalculatedRateObservationDatesAndWeightsBuilder) other;
			
			
			merger.mergeBasic(getObservationDates(), o.getObservationDates(), (Consumer<Date>) this::addObservationDates);
			merger.mergeBasic(getWeights(), o.getWeights(), (Consumer<BigDecimal>) this::addWeights);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculatedRateObservationDatesAndWeights _that = getType().cast(o);
		
			if (!ListEquals.listEquals(observationDates, _that.getObservationDates())) return false;
			if (!ListEquals.listEquals(weights, _that.getWeights())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (observationDates != null ? observationDates.hashCode() : 0);
			_result = 31 * _result + (weights != null ? weights.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculatedRateObservationDatesAndWeightsBuilder {" +
				"observationDates=" + this.observationDates + ", " +
				"weights=" + this.weights +
			'}';
		}
	}
}
