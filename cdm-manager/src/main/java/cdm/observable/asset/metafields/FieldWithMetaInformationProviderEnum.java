package cdm.observable.asset.metafields;

import cdm.observable.asset.InformationProviderEnum;
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
@RosettaDataType(value="FieldWithMetaInformationProviderEnum", builder=FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilderImpl.class, version="0.0.0")
@RuneDataType(value="FieldWithMetaInformationProviderEnum", model="Common Domain Model", builder=FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaInformationProviderEnum extends RosettaModelObject, FieldWithMeta<InformationProviderEnum>, GlobalKey {

	FieldWithMetaInformationProviderEnumMeta metaData = new FieldWithMetaInformationProviderEnumMeta();

	/*********************** Getter Methods  ***********************/
	InformationProviderEnum getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaInformationProviderEnum build();
	
	FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder toBuilder();
	
	static FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder builder() {
		return new FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaInformationProviderEnum> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends FieldWithMetaInformationProviderEnum> getType() {
		return FieldWithMetaInformationProviderEnum.class;
	}
	
	@Override
	default Class<InformationProviderEnum> getValueType() {
		return InformationProviderEnum.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("value"), InformationProviderEnum.class, getValue(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaInformationProviderEnumBuilder extends FieldWithMetaInformationProviderEnum, RosettaModelObjectBuilder, FieldWithMeta.FieldWithMetaBuilder<InformationProviderEnum>, GlobalKey.GlobalKeyBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder setValue(InformationProviderEnum value);
		FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("value"), InformationProviderEnum.class, getValue(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaInformationProviderEnum  ***********************/
	class FieldWithMetaInformationProviderEnumImpl implements FieldWithMetaInformationProviderEnum {
		private final InformationProviderEnum value;
		private final MetaFields meta;
		
		protected FieldWithMetaInformationProviderEnumImpl(FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder builder) {
			this.value = builder.getValue();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public InformationProviderEnum getValue() {
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
		public FieldWithMetaInformationProviderEnum build() {
			return this;
		}
		
		@Override
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder toBuilder() {
			FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInformationProviderEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaInformationProviderEnum {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaInformationProviderEnum  ***********************/
	class FieldWithMetaInformationProviderEnumBuilderImpl implements FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder {
	
		protected InformationProviderEnum value;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("value")
		@RuneAttribute("@data")
		public InformationProviderEnum getValue() {
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
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder setValue(InformationProviderEnum _value) {
			this.value = _value == null ? null : _value;
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaInformationProviderEnum build() {
			return new FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumImpl(this);
		}
		
		@Override
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder prune() {
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
		public FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder o = (FieldWithMetaInformationProviderEnum.FieldWithMetaInformationProviderEnumBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getValue(), o.getValue(), this::setValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaInformationProviderEnum _that = getType().cast(o);
		
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
			return "FieldWithMetaInformationProviderEnumBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaInformationProviderEnumMeta extends BasicRosettaMetaData<FieldWithMetaInformationProviderEnum>{

}
