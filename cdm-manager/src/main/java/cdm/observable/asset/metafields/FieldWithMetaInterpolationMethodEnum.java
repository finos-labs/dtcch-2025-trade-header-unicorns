package cdm.observable.asset.metafields;

import cdm.observable.asset.InterpolationMethodEnum;
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
@RosettaDataType(value="FieldWithMetaInterpolationMethodEnum", builder=FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilderImpl.class, version="0.0.0")
@RuneDataType(value="FieldWithMetaInterpolationMethodEnum", model="Common Domain Model", builder=FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaInterpolationMethodEnum extends RosettaModelObject, FieldWithMeta<InterpolationMethodEnum>, GlobalKey {

	FieldWithMetaInterpolationMethodEnumMeta metaData = new FieldWithMetaInterpolationMethodEnumMeta();

	/*********************** Getter Methods  ***********************/
	InterpolationMethodEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaInterpolationMethodEnum build();
	
	FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder toBuilder();
	
	static FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder builder() {
		return new FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaInterpolationMethodEnum> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends FieldWithMetaInterpolationMethodEnum> getType() {
		return FieldWithMetaInterpolationMethodEnum.class;
	}
	
	@Override
	default Class<InterpolationMethodEnum> getValueType() {
		return InterpolationMethodEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), InterpolationMethodEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaInterpolationMethodEnumBuilder extends FieldWithMetaInterpolationMethodEnum, RosettaModelObjectBuilder, FieldWithMeta.FieldWithMetaBuilder<InterpolationMethodEnum>, GlobalKey.GlobalKeyBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder setValue(InterpolationMethodEnum value);
		FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), InterpolationMethodEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaInterpolationMethodEnum  ***********************/
	class FieldWithMetaInterpolationMethodEnumImpl implements FieldWithMetaInterpolationMethodEnum {
		private final InterpolationMethodEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaInterpolationMethodEnumImpl(FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public InterpolationMethodEnum getValue() {
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
		public FieldWithMetaInterpolationMethodEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder toBuilder() {
			FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInterpolationMethodEnum _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaInterpolationMethodEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaInterpolationMethodEnum  ***********************/
	class FieldWithMetaInterpolationMethodEnumBuilderImpl implements FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder {
	
		protected InterpolationMethodEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public InterpolationMethodEnum getValue() {
			return value;
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
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder setValue(InterpolationMethodEnum _value) {
			this.value = _value == null ? null : _value;
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaInterpolationMethodEnum build() {
			return new FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder o = (FieldWithMetaInterpolationMethodEnum.FieldWithMetaInterpolationMethodEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInterpolationMethodEnum _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaInterpolationMethodEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaInterpolationMethodEnumMeta extends BasicRosettaMetaData<FieldWithMetaInterpolationMethodEnum>{

}
