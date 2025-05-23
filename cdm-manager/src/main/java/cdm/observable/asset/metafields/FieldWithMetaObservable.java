package cdm.observable.asset.metafields;

import cdm.observable.asset.Observable;
import cdm.observable.asset.Observable.ObservableBuilder;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.annotations.RuneAttribute;
import com.rosetta.model.lib.annotations.RuneDataType;
import com.rosetta.model.lib.annotations.RuneMetaType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.FieldWithMeta;
import com.rosetta.model.lib.meta.FieldWithMeta.FieldWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.model.metafields.MetaFields.MetaFieldsBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="FieldWithMetaObservable", builder=FieldWithMetaObservable.FieldWithMetaObservableBuilderImpl.class, version="0.0.0")
@RuneDataType(value="FieldWithMetaObservable", model="Common Domain Model", builder=FieldWithMetaObservable.FieldWithMetaObservableBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaObservable extends RosettaModelObject, FieldWithMeta<Observable>, GlobalKey {

	FieldWithMetaObservableMeta metaData = new FieldWithMetaObservableMeta();

	/*********************** Getter Methods  ***********************/
	Observable getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaObservable build();
	
	FieldWithMetaObservable.FieldWithMetaObservableBuilder toBuilder();
	
	static FieldWithMetaObservable.FieldWithMetaObservableBuilder builder() {
		return new FieldWithMetaObservable.FieldWithMetaObservableBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaObservable> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends FieldWithMetaObservable> getType() {
		return FieldWithMetaObservable.class;
	}
	
	@Override
	default Class<Observable> getValueType() {
		return Observable.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, Observable.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaObservableBuilder extends FieldWithMetaObservable, RosettaModelObjectBuilder, FieldWithMeta.FieldWithMetaBuilder<Observable>, GlobalKey.GlobalKeyBuilder {
		Observable.ObservableBuilder getOrCreateValue();
		@Override
		Observable.ObservableBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaObservable.FieldWithMetaObservableBuilder setValue(Observable value);
		FieldWithMetaObservable.FieldWithMetaObservableBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, Observable.ObservableBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaObservable.FieldWithMetaObservableBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaObservable  ***********************/
	class FieldWithMetaObservableImpl implements FieldWithMetaObservable {
		private final Observable value;
		private final MetaFields meta;
		
		protected FieldWithMetaObservableImpl(FieldWithMetaObservable.FieldWithMetaObservableBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public Observable getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaObservable build() {
			return this;
		}
		
		@Override
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder toBuilder() {
			FieldWithMetaObservable.FieldWithMetaObservableBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaObservable.FieldWithMetaObservableBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaObservable _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaObservable {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaObservable  ***********************/
	class FieldWithMetaObservableBuilderImpl implements FieldWithMetaObservable.FieldWithMetaObservableBuilder {
	
		protected Observable.ObservableBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public Observable.ObservableBuilder getValue() {
			return value;
		}
		
		@Override
		public Observable.ObservableBuilder getOrCreateValue() {
			Observable.ObservableBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = Observable.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		@RuneMetaType
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder setValue(Observable _value) {
			this.value = _value == null ? null : _value.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaObservable build() {
			return new FieldWithMetaObservable.FieldWithMetaObservableImpl(this);
		}
		
		@Override
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaObservable.FieldWithMetaObservableBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaObservable.FieldWithMetaObservableBuilder o = (FieldWithMetaObservable.FieldWithMetaObservableBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaObservable _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaObservableBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaObservableMeta extends BasicRosettaMetaData<FieldWithMetaObservable>{

}
