package cdm.base.staticdata.asset.rates.metafields;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
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
@RosettaDataType(value="FieldWithMetaFloatingRateIndexEnum", builder=FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilderImpl.class, version="0.0.0")
@RuneDataType(value="FieldWithMetaFloatingRateIndexEnum", model="Common Domain Model", builder=FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaFloatingRateIndexEnum extends RosettaModelObject, FieldWithMeta<FloatingRateIndexEnum>, GlobalKey {

	FieldWithMetaFloatingRateIndexEnumMeta metaData = new FieldWithMetaFloatingRateIndexEnumMeta();

	/*********************** Getter Methods  ***********************/
	FloatingRateIndexEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaFloatingRateIndexEnum build();
	
	FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder toBuilder();
	
	static FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder builder() {
		return new FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaFloatingRateIndexEnum> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends FieldWithMetaFloatingRateIndexEnum> getType() {
		return FieldWithMetaFloatingRateIndexEnum.class;
	}
	
	@Override
	default Class<FloatingRateIndexEnum> getValueType() {
		return FloatingRateIndexEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), FloatingRateIndexEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaFloatingRateIndexEnumBuilder extends FieldWithMetaFloatingRateIndexEnum, RosettaModelObjectBuilder, FieldWithMeta.FieldWithMetaBuilder<FloatingRateIndexEnum>, GlobalKey.GlobalKeyBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder setValue(FloatingRateIndexEnum value);
		FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), FloatingRateIndexEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaFloatingRateIndexEnum  ***********************/
	class FieldWithMetaFloatingRateIndexEnumImpl implements FieldWithMetaFloatingRateIndexEnum {
		private final FloatingRateIndexEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaFloatingRateIndexEnumImpl(FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public FloatingRateIndexEnum getValue() {
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
		public FieldWithMetaFloatingRateIndexEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder toBuilder() {
			FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaFloatingRateIndexEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaFloatingRateIndexEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaFloatingRateIndexEnum  ***********************/
	class FieldWithMetaFloatingRateIndexEnumBuilderImpl implements FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder {
	
		protected FloatingRateIndexEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public FloatingRateIndexEnum getValue() {
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
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder setValue(FloatingRateIndexEnum _value) {
			this.value = _value == null ? null : _value;
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaFloatingRateIndexEnum build() {
			return new FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder prune() {
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
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder o = (FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaFloatingRateIndexEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaFloatingRateIndexEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaFloatingRateIndexEnumMeta extends BasicRosettaMetaData<FieldWithMetaFloatingRateIndexEnum>{

}
