package cdm.observable.event.metafields;

import cdm.observable.event.Observation;
import cdm.observable.event.Observation.ObservationBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.annotations.RuneAttribute;
import com.rosetta.model.lib.annotations.RuneDataType;
import com.rosetta.model.lib.annotations.RuneMetaType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.Reference;
import com.rosetta.model.lib.meta.Reference.ReferenceBuilder;
import com.rosetta.model.lib.meta.ReferenceWithMeta;
import com.rosetta.model.lib.meta.ReferenceWithMeta.ReferenceWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.AttributeMeta;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="ReferenceWithMetaObservation", builder=ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilderImpl.class, version="0.0.0")
@RuneDataType(value="ReferenceWithMetaObservation", model="Common Domain Model", builder=ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilderImpl.class, version="0.0.0")
public interface ReferenceWithMetaObservation extends RosettaModelObject, ReferenceWithMeta<Observation> {

	ReferenceWithMetaObservationMeta metaData = new ReferenceWithMetaObservationMeta();

	/*********************** Getter Methods  ***********************/
	Observation getValue();
	String getGlobalReference();
	String getExternalReference();
	Reference getReference();

	/*********************** Build Methods  ***********************/
	ReferenceWithMetaObservation build();
	
	ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder toBuilder();
	
	static ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder builder() {
		return new ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferenceWithMetaObservation> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends ReferenceWithMetaObservation> getType() {
		return ReferenceWithMetaObservation.class;
	}
	
	@Override
	default Class<Observation> getValueType() {
		return Observation.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, Observation.class, getValue());
		processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
		processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
		processRosetta(path.newSubPath("reference"), processor, Reference.class, getReference());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferenceWithMetaObservationBuilder extends ReferenceWithMetaObservation, RosettaModelObjectBuilder, ReferenceWithMeta.ReferenceWithMetaBuilder<Observation> {
		Observation.ObservationBuilder getOrCreateValue();
		@Override
		Observation.ObservationBuilder getValue();
		Reference.ReferenceBuilder getOrCreateReference();
		@Override
		Reference.ReferenceBuilder getReference();
		ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setValue(Observation value);
		ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setGlobalReference(String globalReference);
		ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setExternalReference(String externalReference);
		ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setReference(Reference reference);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, Observation.ObservationBuilder.class, getValue());
			processor.processBasic(path.newSubPath("globalReference"), String.class, getGlobalReference(), this, AttributeMeta.META);
			processor.processBasic(path.newSubPath("externalReference"), String.class, getExternalReference(), this, AttributeMeta.META);
			processRosetta(path.newSubPath("reference"), processor, Reference.ReferenceBuilder.class, getReference());
		}
		

		ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder prune();
	}

	/*********************** Immutable Implementation of ReferenceWithMetaObservation  ***********************/
	class ReferenceWithMetaObservationImpl implements ReferenceWithMetaObservation {
		private final Observation value;
		private final String globalReference;
		private final String externalReference;
		private final Reference reference;
		
		protected ReferenceWithMetaObservationImpl(ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.globalReference = builder.getGlobalReference();
			this.externalReference = builder.getExternalReference();
			this.reference = ofNullable(builder.getReference()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public Observation getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("globalReference")
		@RuneAttribute("@ref")
		public String getGlobalReference() {
			return globalReference;
		}
		
		@Override
		@RosettaAttribute("externalReference")
		@RuneAttribute("@ref:external")
		public String getExternalReference() {
			return externalReference;
		}
		
		@Override
		@RosettaAttribute("address")
		@RuneAttribute("@ref:scoped")
		@RuneMetaType
		public Reference getReference() {
			return reference;
		}
		
		@Override
		public ReferenceWithMetaObservation build() {
			return this;
		}
		
		@Override
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder toBuilder() {
			ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getGlobalReference()).ifPresent(builder::setGlobalReference);
			ofNullable(getExternalReference()).ifPresent(builder::setExternalReference);
			ofNullable(getReference()).ifPresent(builder::setReference);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaObservation _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(globalReference, _that.getGlobalReference())) return false;
			if (!Objects.equals(externalReference, _that.getExternalReference())) return false;
			if (!Objects.equals(reference, _that.getReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (globalReference != null ? globalReference.hashCode() : 0);
			_result = 31 * _result + (externalReference != null ? externalReference.hashCode() : 0);
			_result = 31 * _result + (reference != null ? reference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceWithMetaObservation {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferenceWithMetaObservation  ***********************/
	class ReferenceWithMetaObservationBuilderImpl implements ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder {
	
		protected Observation.ObservationBuilder value;
		protected String globalReference;
		protected String externalReference;
		protected Reference.ReferenceBuilder reference;
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public Observation.ObservationBuilder getValue() {
			return value;
		}
		
		@Override
		public Observation.ObservationBuilder getOrCreateValue() {
			Observation.ObservationBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = Observation.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("globalReference")
		@RuneAttribute("@ref")
		public String getGlobalReference() {
			return globalReference;
		}
		
		@Override
		@RosettaAttribute("externalReference")
		@RuneAttribute("@ref:external")
		public String getExternalReference() {
			return externalReference;
		}
		
		@Override
		@RosettaAttribute("address")
		@RuneAttribute("@ref:scoped")
		@RuneMetaType
		public Reference.ReferenceBuilder getReference() {
			return reference;
		}
		
		@Override
		public Reference.ReferenceBuilder getOrCreateReference() {
			Reference.ReferenceBuilder result;
			if (reference!=null) {
				result = reference;
			}
			else {
				result = reference = Reference.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setValue(Observation _value) {
			this.value = _value == null ? null : _value.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("globalReference")
		@RuneAttribute("@ref")
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setGlobalReference(String _globalReference) {
			this.globalReference = _globalReference == null ? null : _globalReference;
			return this;
		}
		
		@Override
		@RosettaAttribute("externalReference")
		@RuneAttribute("@ref:external")
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setExternalReference(String _externalReference) {
			this.externalReference = _externalReference == null ? null : _externalReference;
			return this;
		}
		
		@Override
		@RosettaAttribute("address")
		@RuneAttribute("@ref:scoped")
		@RuneMetaType
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder setReference(Reference _reference) {
			this.reference = _reference == null ? null : _reference.toBuilder();
			return this;
		}
		
		@Override
		public ReferenceWithMetaObservation build() {
			return new ReferenceWithMetaObservation.ReferenceWithMetaObservationImpl(this);
		}
		
		@Override
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (reference!=null && !reference.prune().hasData()) reference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			if (getGlobalReference()!=null) return true;
			if (getExternalReference()!=null) return true;
			if (getReference()!=null && getReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder o = (ReferenceWithMetaObservation.ReferenceWithMetaObservationBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getReference(), o.getReference(), this::setReference);
			
			merger.mergeBasic(getGlobalReference(), o.getGlobalReference(), this::setGlobalReference);
			merger.mergeBasic(getExternalReference(), o.getExternalReference(), this::setExternalReference);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferenceWithMetaObservation _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(globalReference, _that.getGlobalReference())) return false;
			if (!Objects.equals(externalReference, _that.getExternalReference())) return false;
			if (!Objects.equals(reference, _that.getReference())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (globalReference != null ? globalReference.hashCode() : 0);
			_result = 31 * _result + (externalReference != null ? externalReference.hashCode() : 0);
			_result = 31 * _result + (reference != null ? reference.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferenceWithMetaObservationBuilder {" +
				"value=" + this.value + ", " +
				"globalReference=" + this.globalReference + ", " +
				"externalReference=" + this.externalReference + ", " +
				"reference=" + this.reference +
			'}';
		}
	}
}

class ReferenceWithMetaObservationMeta extends BasicRosettaMetaData<ReferenceWithMetaObservation>{

}
