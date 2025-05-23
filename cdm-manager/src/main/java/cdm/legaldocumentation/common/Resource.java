package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.Resource;
import cdm.legaldocumentation.common.Resource.ResourceBuilder;
import cdm.legaldocumentation.common.Resource.ResourceBuilderImpl;
import cdm.legaldocumentation.common.Resource.ResourceImpl;
import cdm.legaldocumentation.common.ResourceLength;
import cdm.legaldocumentation.common.ResourceLength.ResourceLengthBuilder;
import cdm.legaldocumentation.common.ResourceTypeEnum;
import cdm.legaldocumentation.common.meta.ResourceMeta;
import cdm.legaldocumentation.common.metafields.FieldWithMetaResourceTypeEnum;
import cdm.legaldocumentation.common.metafields.FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Describes the resource that contains the media representation of a business event (i.e used for stating the Publicly Available Information). For example, can describe a file or a URL that represents the event. This type is an extended version of a type defined by RIXML (www.rixml.org).  Rosetta restricts the FpML implementation by not providing the ability to associated a document in hexadecimalBinary or base64Binary until such time that actual use cases will come up.
 * @version ${project.version}
 */
@RosettaDataType(value="Resource", builder=Resource.ResourceBuilderImpl.class, version="${project.version}")
@RuneDataType(value="Resource", model="Common Domain Model", builder=Resource.ResourceBuilderImpl.class, version="${project.version}")
public interface Resource extends RosettaModelObject {

	ResourceMeta metaData = new ResourceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The unique identifier of the resource within the event. FpML specifies this element of type resourceIdScheme but with no specified value.
	 */
	FieldWithMetaString getResourceId();
	/**
	 * A description of the type of the resource, e.g. a confirmation.
	 */
	FieldWithMetaResourceTypeEnum getResourceType();
	/**
	 * Indicates the language of the resource, described using the ISO 639-2/T Code.
	 */
	FieldWithMetaString getLanguage();
	/**
	 * Indicates the size of the resource in bytes. It could be used by the end user to estimate the download time and storage needs.
	 */
	BigDecimal getSizeInBytes();
	/**
	 * Indicates the length of the resource. For example, if the resource were a PDF file, the length would be in pages.
	 */
	ResourceLength getLength();
	/**
	 * Indicates the type of media used to store the content. mimeType is used to determine the software product(s) that can read the content. MIME Types are described in RFC 2046.
	 */
	FieldWithMetaString getMimeType();
	/**
	 * The name of the resource.  It is specified as a NormalizedString in FpML.
	 */
	String getName();
	/**
	 * Any additional comments that are deemed necessary. For example, which software version is required to open the document? Or, how does this resource relate to the others for this event?
	 */
	String getComments();
	/**
	 * Provides extra information as string. In case the extra information is in XML format, a CDATA section must be placed around the source message to prevent its interpretation as XML content.
	 */
	String getString();
	/**
	 * Indicates where the resource can be found, as a URL that references the information on a web server accessible to the message recipient.
	 */
	String getUrl();

	/*********************** Build Methods  ***********************/
	Resource build();
	
	Resource.ResourceBuilder toBuilder();
	
	static Resource.ResourceBuilder builder() {
		return new Resource.ResourceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Resource> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends Resource> getType() {
		return Resource.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("resourceId"), processor, FieldWithMetaString.class, getResourceId());
		processRosetta(path.newSubPath("resourceType"), processor, FieldWithMetaResourceTypeEnum.class, getResourceType());
		processRosetta(path.newSubPath("language"), processor, FieldWithMetaString.class, getLanguage());
		processor.processBasic(path.newSubPath("sizeInBytes"), BigDecimal.class, getSizeInBytes(), this);
		processRosetta(path.newSubPath("length"), processor, ResourceLength.class, getLength());
		processRosetta(path.newSubPath("mimeType"), processor, FieldWithMetaString.class, getMimeType());
		processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
		processor.processBasic(path.newSubPath("comments"), String.class, getComments(), this);
		processor.processBasic(path.newSubPath("string"), String.class, getString(), this);
		processor.processBasic(path.newSubPath("url"), String.class, getUrl(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ResourceBuilder extends Resource, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateResourceId();
		@Override
		FieldWithMetaString.FieldWithMetaStringBuilder getResourceId();
		FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder getOrCreateResourceType();
		@Override
		FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder getResourceType();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLanguage();
		@Override
		FieldWithMetaString.FieldWithMetaStringBuilder getLanguage();
		ResourceLength.ResourceLengthBuilder getOrCreateLength();
		@Override
		ResourceLength.ResourceLengthBuilder getLength();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMimeType();
		@Override
		FieldWithMetaString.FieldWithMetaStringBuilder getMimeType();
		Resource.ResourceBuilder setResourceId(FieldWithMetaString resourceId);
		Resource.ResourceBuilder setResourceIdValue(String resourceId);
		Resource.ResourceBuilder setResourceType(FieldWithMetaResourceTypeEnum resourceType);
		Resource.ResourceBuilder setResourceTypeValue(ResourceTypeEnum resourceType);
		Resource.ResourceBuilder setLanguage(FieldWithMetaString language);
		Resource.ResourceBuilder setLanguageValue(String language);
		Resource.ResourceBuilder setSizeInBytes(BigDecimal sizeInBytes);
		Resource.ResourceBuilder setLength(ResourceLength length);
		Resource.ResourceBuilder setMimeType(FieldWithMetaString mimeType);
		Resource.ResourceBuilder setMimeTypeValue(String mimeType);
		Resource.ResourceBuilder setName(String name);
		Resource.ResourceBuilder setComments(String comments);
		Resource.ResourceBuilder setString(String string);
		Resource.ResourceBuilder setUrl(String url);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("resourceId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getResourceId());
			processRosetta(path.newSubPath("resourceType"), processor, FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder.class, getResourceType());
			processRosetta(path.newSubPath("language"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getLanguage());
			processor.processBasic(path.newSubPath("sizeInBytes"), BigDecimal.class, getSizeInBytes(), this);
			processRosetta(path.newSubPath("length"), processor, ResourceLength.ResourceLengthBuilder.class, getLength());
			processRosetta(path.newSubPath("mimeType"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getMimeType());
			processor.processBasic(path.newSubPath("name"), String.class, getName(), this);
			processor.processBasic(path.newSubPath("comments"), String.class, getComments(), this);
			processor.processBasic(path.newSubPath("string"), String.class, getString(), this);
			processor.processBasic(path.newSubPath("url"), String.class, getUrl(), this);
		}
		

		Resource.ResourceBuilder prune();
	}

	/*********************** Immutable Implementation of Resource  ***********************/
	class ResourceImpl implements Resource {
		private final FieldWithMetaString resourceId;
		private final FieldWithMetaResourceTypeEnum resourceType;
		private final FieldWithMetaString language;
		private final BigDecimal sizeInBytes;
		private final ResourceLength length;
		private final FieldWithMetaString mimeType;
		private final String name;
		private final String comments;
		private final String string;
		private final String url;
		
		protected ResourceImpl(Resource.ResourceBuilder builder) {
			this.resourceId = ofNullable(builder.getResourceId()).map(f->f.build()).orElse(null);
			this.resourceType = ofNullable(builder.getResourceType()).map(f->f.build()).orElse(null);
			this.language = ofNullable(builder.getLanguage()).map(f->f.build()).orElse(null);
			this.sizeInBytes = builder.getSizeInBytes();
			this.length = ofNullable(builder.getLength()).map(f->f.build()).orElse(null);
			this.mimeType = ofNullable(builder.getMimeType()).map(f->f.build()).orElse(null);
			this.name = builder.getName();
			this.comments = builder.getComments();
			this.string = builder.getString();
			this.url = builder.getUrl();
		}
		
		@Override
		@RosettaAttribute("resourceId")
		@RuneAttribute("resourceId")
		public FieldWithMetaString getResourceId() {
			return resourceId;
		}
		
		@Override
		@RosettaAttribute("resourceType")
		@RuneAttribute("resourceType")
		public FieldWithMetaResourceTypeEnum getResourceType() {
			return resourceType;
		}
		
		@Override
		@RosettaAttribute("language")
		@RuneAttribute("language")
		public FieldWithMetaString getLanguage() {
			return language;
		}
		
		@Override
		@RosettaAttribute("sizeInBytes")
		@RuneAttribute("sizeInBytes")
		public BigDecimal getSizeInBytes() {
			return sizeInBytes;
		}
		
		@Override
		@RosettaAttribute("length")
		@RuneAttribute("length")
		public ResourceLength getLength() {
			return length;
		}
		
		@Override
		@RosettaAttribute("mimeType")
		@RuneAttribute("mimeType")
		public FieldWithMetaString getMimeType() {
			return mimeType;
		}
		
		@Override
		@RosettaAttribute("name")
		@RuneAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("comments")
		@RuneAttribute("comments")
		public String getComments() {
			return comments;
		}
		
		@Override
		@RosettaAttribute("string")
		@RuneAttribute("string")
		public String getString() {
			return string;
		}
		
		@Override
		@RosettaAttribute("url")
		@RuneAttribute("url")
		public String getUrl() {
			return url;
		}
		
		@Override
		public Resource build() {
			return this;
		}
		
		@Override
		public Resource.ResourceBuilder toBuilder() {
			Resource.ResourceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Resource.ResourceBuilder builder) {
			ofNullable(getResourceId()).ifPresent(builder::setResourceId);
			ofNullable(getResourceType()).ifPresent(builder::setResourceType);
			ofNullable(getLanguage()).ifPresent(builder::setLanguage);
			ofNullable(getSizeInBytes()).ifPresent(builder::setSizeInBytes);
			ofNullable(getLength()).ifPresent(builder::setLength);
			ofNullable(getMimeType()).ifPresent(builder::setMimeType);
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getComments()).ifPresent(builder::setComments);
			ofNullable(getString()).ifPresent(builder::setString);
			ofNullable(getUrl()).ifPresent(builder::setUrl);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Resource _that = getType().cast(o);
		
			if (!Objects.equals(resourceId, _that.getResourceId())) return false;
			if (!Objects.equals(resourceType, _that.getResourceType())) return false;
			if (!Objects.equals(language, _that.getLanguage())) return false;
			if (!Objects.equals(sizeInBytes, _that.getSizeInBytes())) return false;
			if (!Objects.equals(length, _that.getLength())) return false;
			if (!Objects.equals(mimeType, _that.getMimeType())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(comments, _that.getComments())) return false;
			if (!Objects.equals(string, _that.getString())) return false;
			if (!Objects.equals(url, _that.getUrl())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resourceId != null ? resourceId.hashCode() : 0);
			_result = 31 * _result + (resourceType != null ? resourceType.hashCode() : 0);
			_result = 31 * _result + (language != null ? language.hashCode() : 0);
			_result = 31 * _result + (sizeInBytes != null ? sizeInBytes.hashCode() : 0);
			_result = 31 * _result + (length != null ? length.hashCode() : 0);
			_result = 31 * _result + (mimeType != null ? mimeType.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (comments != null ? comments.hashCode() : 0);
			_result = 31 * _result + (string != null ? string.hashCode() : 0);
			_result = 31 * _result + (url != null ? url.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Resource {" +
				"resourceId=" + this.resourceId + ", " +
				"resourceType=" + this.resourceType + ", " +
				"language=" + this.language + ", " +
				"sizeInBytes=" + this.sizeInBytes + ", " +
				"length=" + this.length + ", " +
				"mimeType=" + this.mimeType + ", " +
				"name=" + this.name + ", " +
				"comments=" + this.comments + ", " +
				"string=" + this.string + ", " +
				"url=" + this.url +
			'}';
		}
	}

	/*********************** Builder Implementation of Resource  ***********************/
	class ResourceBuilderImpl implements Resource.ResourceBuilder {
	
		protected FieldWithMetaString.FieldWithMetaStringBuilder resourceId;
		protected FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder resourceType;
		protected FieldWithMetaString.FieldWithMetaStringBuilder language;
		protected BigDecimal sizeInBytes;
		protected ResourceLength.ResourceLengthBuilder length;
		protected FieldWithMetaString.FieldWithMetaStringBuilder mimeType;
		protected String name;
		protected String comments;
		protected String string;
		protected String url;
		
		@Override
		@RosettaAttribute("resourceId")
		@RuneAttribute("resourceId")
		public FieldWithMetaString.FieldWithMetaStringBuilder getResourceId() {
			return resourceId;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateResourceId() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (resourceId!=null) {
				result = resourceId;
			}
			else {
				result = resourceId = FieldWithMetaString.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("resourceType")
		@RuneAttribute("resourceType")
		public FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder getResourceType() {
			return resourceType;
		}
		
		@Override
		public FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder getOrCreateResourceType() {
			FieldWithMetaResourceTypeEnum.FieldWithMetaResourceTypeEnumBuilder result;
			if (resourceType!=null) {
				result = resourceType;
			}
			else {
				result = resourceType = FieldWithMetaResourceTypeEnum.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("language")
		@RuneAttribute("language")
		public FieldWithMetaString.FieldWithMetaStringBuilder getLanguage() {
			return language;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateLanguage() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (language!=null) {
				result = language;
			}
			else {
				result = language = FieldWithMetaString.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("sizeInBytes")
		@RuneAttribute("sizeInBytes")
		public BigDecimal getSizeInBytes() {
			return sizeInBytes;
		}
		
		@Override
		@RosettaAttribute("length")
		@RuneAttribute("length")
		public ResourceLength.ResourceLengthBuilder getLength() {
			return length;
		}
		
		@Override
		public ResourceLength.ResourceLengthBuilder getOrCreateLength() {
			ResourceLength.ResourceLengthBuilder result;
			if (length!=null) {
				result = length;
			}
			else {
				result = length = ResourceLength.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("mimeType")
		@RuneAttribute("mimeType")
		public FieldWithMetaString.FieldWithMetaStringBuilder getMimeType() {
			return mimeType;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateMimeType() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (mimeType!=null) {
				result = mimeType;
			}
			else {
				result = mimeType = FieldWithMetaString.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("name")
		@RuneAttribute("name")
		public String getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("comments")
		@RuneAttribute("comments")
		public String getComments() {
			return comments;
		}
		
		@Override
		@RosettaAttribute("string")
		@RuneAttribute("string")
		public String getString() {
			return string;
		}
		
		@Override
		@RosettaAttribute("url")
		@RuneAttribute("url")
		public String getUrl() {
			return url;
		}
		
		@Override
		@RosettaAttribute("resourceId")
		@RuneAttribute("resourceId")
		public Resource.ResourceBuilder setResourceId(FieldWithMetaString _resourceId) {
			this.resourceId = _resourceId == null ? null : _resourceId.toBuilder();
			return this;
		}
		
		@Override
		public Resource.ResourceBuilder setResourceIdValue(String _resourceId) {
			this.getOrCreateResourceId().setValue(_resourceId);
			return this;
		}
		
		@Override
		@RosettaAttribute("resourceType")
		@RuneAttribute("resourceType")
		public Resource.ResourceBuilder setResourceType(FieldWithMetaResourceTypeEnum _resourceType) {
			this.resourceType = _resourceType == null ? null : _resourceType.toBuilder();
			return this;
		}
		
		@Override
		public Resource.ResourceBuilder setResourceTypeValue(ResourceTypeEnum _resourceType) {
			this.getOrCreateResourceType().setValue(_resourceType);
			return this;
		}
		
		@Override
		@RosettaAttribute("language")
		@RuneAttribute("language")
		public Resource.ResourceBuilder setLanguage(FieldWithMetaString _language) {
			this.language = _language == null ? null : _language.toBuilder();
			return this;
		}
		
		@Override
		public Resource.ResourceBuilder setLanguageValue(String _language) {
			this.getOrCreateLanguage().setValue(_language);
			return this;
		}
		
		@Override
		@RosettaAttribute("sizeInBytes")
		@RuneAttribute("sizeInBytes")
		public Resource.ResourceBuilder setSizeInBytes(BigDecimal _sizeInBytes) {
			this.sizeInBytes = _sizeInBytes == null ? null : _sizeInBytes;
			return this;
		}
		
		@Override
		@RosettaAttribute("length")
		@RuneAttribute("length")
		public Resource.ResourceBuilder setLength(ResourceLength _length) {
			this.length = _length == null ? null : _length.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("mimeType")
		@RuneAttribute("mimeType")
		public Resource.ResourceBuilder setMimeType(FieldWithMetaString _mimeType) {
			this.mimeType = _mimeType == null ? null : _mimeType.toBuilder();
			return this;
		}
		
		@Override
		public Resource.ResourceBuilder setMimeTypeValue(String _mimeType) {
			this.getOrCreateMimeType().setValue(_mimeType);
			return this;
		}
		
		@Override
		@RosettaAttribute("name")
		@RuneAttribute("name")
		public Resource.ResourceBuilder setName(String _name) {
			this.name = _name == null ? null : _name;
			return this;
		}
		
		@Override
		@RosettaAttribute("comments")
		@RuneAttribute("comments")
		public Resource.ResourceBuilder setComments(String _comments) {
			this.comments = _comments == null ? null : _comments;
			return this;
		}
		
		@Override
		@RosettaAttribute("string")
		@RuneAttribute("string")
		public Resource.ResourceBuilder setString(String _string) {
			this.string = _string == null ? null : _string;
			return this;
		}
		
		@Override
		@RosettaAttribute("url")
		@RuneAttribute("url")
		public Resource.ResourceBuilder setUrl(String _url) {
			this.url = _url == null ? null : _url;
			return this;
		}
		
		@Override
		public Resource build() {
			return new Resource.ResourceImpl(this);
		}
		
		@Override
		public Resource.ResourceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Resource.ResourceBuilder prune() {
			if (resourceId!=null && !resourceId.prune().hasData()) resourceId = null;
			if (resourceType!=null && !resourceType.prune().hasData()) resourceType = null;
			if (language!=null && !language.prune().hasData()) language = null;
			if (length!=null && !length.prune().hasData()) length = null;
			if (mimeType!=null && !mimeType.prune().hasData()) mimeType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getResourceId()!=null) return true;
			if (getResourceType()!=null) return true;
			if (getLanguage()!=null) return true;
			if (getSizeInBytes()!=null) return true;
			if (getLength()!=null && getLength().hasData()) return true;
			if (getMimeType()!=null) return true;
			if (getName()!=null) return true;
			if (getComments()!=null) return true;
			if (getString()!=null) return true;
			if (getUrl()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Resource.ResourceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Resource.ResourceBuilder o = (Resource.ResourceBuilder) other;
			
			merger.mergeRosetta(getResourceId(), o.getResourceId(), this::setResourceId);
			merger.mergeRosetta(getResourceType(), o.getResourceType(), this::setResourceType);
			merger.mergeRosetta(getLanguage(), o.getLanguage(), this::setLanguage);
			merger.mergeRosetta(getLength(), o.getLength(), this::setLength);
			merger.mergeRosetta(getMimeType(), o.getMimeType(), this::setMimeType);
			
			merger.mergeBasic(getSizeInBytes(), o.getSizeInBytes(), this::setSizeInBytes);
			merger.mergeBasic(getName(), o.getName(), this::setName);
			merger.mergeBasic(getComments(), o.getComments(), this::setComments);
			merger.mergeBasic(getString(), o.getString(), this::setString);
			merger.mergeBasic(getUrl(), o.getUrl(), this::setUrl);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Resource _that = getType().cast(o);
		
			if (!Objects.equals(resourceId, _that.getResourceId())) return false;
			if (!Objects.equals(resourceType, _that.getResourceType())) return false;
			if (!Objects.equals(language, _that.getLanguage())) return false;
			if (!Objects.equals(sizeInBytes, _that.getSizeInBytes())) return false;
			if (!Objects.equals(length, _that.getLength())) return false;
			if (!Objects.equals(mimeType, _that.getMimeType())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(comments, _that.getComments())) return false;
			if (!Objects.equals(string, _that.getString())) return false;
			if (!Objects.equals(url, _that.getUrl())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resourceId != null ? resourceId.hashCode() : 0);
			_result = 31 * _result + (resourceType != null ? resourceType.hashCode() : 0);
			_result = 31 * _result + (language != null ? language.hashCode() : 0);
			_result = 31 * _result + (sizeInBytes != null ? sizeInBytes.hashCode() : 0);
			_result = 31 * _result + (length != null ? length.hashCode() : 0);
			_result = 31 * _result + (mimeType != null ? mimeType.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (comments != null ? comments.hashCode() : 0);
			_result = 31 * _result + (string != null ? string.hashCode() : 0);
			_result = 31 * _result + (url != null ? url.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResourceBuilder {" +
				"resourceId=" + this.resourceId + ", " +
				"resourceType=" + this.resourceType + ", " +
				"language=" + this.language + ", " +
				"sizeInBytes=" + this.sizeInBytes + ", " +
				"length=" + this.length + ", " +
				"mimeType=" + this.mimeType + ", " +
				"name=" + this.name + ", " +
				"comments=" + this.comments + ", " +
				"string=" + this.string + ", " +
				"url=" + this.url +
			'}';
		}
	}
}
