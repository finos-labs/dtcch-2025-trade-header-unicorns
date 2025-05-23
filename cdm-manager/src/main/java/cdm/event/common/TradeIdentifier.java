package cdm.event.common;

import cdm.base.staticdata.identifier.AssignedIdentifier;
import cdm.base.staticdata.identifier.AssignedIdentifier.AssignedIdentifierBuilder;
import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilderImpl;
import cdm.base.staticdata.identifier.Identifier.IdentifierImpl;
import cdm.base.staticdata.identifier.TradeIdentifierTypeEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
import cdm.event.common.TradeIdentifier;
import cdm.event.common.TradeIdentifier.TradeIdentifierBuilder;
import cdm.event.common.TradeIdentifier.TradeIdentifierBuilderImpl;
import cdm.event.common.TradeIdentifier.TradeIdentifierImpl;
import cdm.event.common.meta.TradeIdentifierMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.annotations.RuneAttribute;
import com.rosetta.model.lib.annotations.RuneDataType;
import com.rosetta.model.lib.annotations.RuneMetaType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.model.metafields.MetaFields.MetaFieldsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines a trade identifier as a special case of the generic identifier type, that also includes the trade identifier class.
 * @version ${project.version}
 */
@RosettaDataType(value="TradeIdentifier", builder=TradeIdentifier.TradeIdentifierBuilderImpl.class, version="${project.version}")
@RuneDataType(value="TradeIdentifier", model="Common Domain Model", builder=TradeIdentifier.TradeIdentifierBuilderImpl.class, version="${project.version}")
public interface TradeIdentifier extends Identifier {

	TradeIdentifierMeta metaData = new TradeIdentifierMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The enumerated classification of the identifier. Optional as a trade identifier may be party-specific, in which case it may not correspond to any established classification.
	 */
	TradeIdentifierTypeEnum getIdentifierType();

	/*********************** Build Methods  ***********************/
	TradeIdentifier build();
	
	TradeIdentifier.TradeIdentifierBuilder toBuilder();
	
	static TradeIdentifier.TradeIdentifierBuilder builder() {
		return new TradeIdentifier.TradeIdentifierBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends TradeIdentifier> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends TradeIdentifier> getType() {
		return TradeIdentifier.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.class, getIssuerReference());
		processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.class, getIssuer());
		processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.class, getAssignedIdentifier());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("identifierType"), TradeIdentifierTypeEnum.class, getIdentifierType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TradeIdentifierBuilder extends TradeIdentifier, Identifier.IdentifierBuilder {
		@Override
		TradeIdentifier.TradeIdentifierBuilder setIssuerReference(ReferenceWithMetaParty issuerReference);
		@Override
		TradeIdentifier.TradeIdentifierBuilder setIssuerReferenceValue(Party issuerReference);
		@Override
		TradeIdentifier.TradeIdentifierBuilder setIssuer(FieldWithMetaString issuer);
		@Override
		TradeIdentifier.TradeIdentifierBuilder setIssuerValue(String issuer);
		@Override
		TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier);
		@Override
		TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(AssignedIdentifier assignedIdentifier, int _idx);
		@Override
		TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier);
		@Override
		TradeIdentifier.TradeIdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifier);
		@Override
		TradeIdentifier.TradeIdentifierBuilder setMeta(MetaFields meta);
		TradeIdentifier.TradeIdentifierBuilder setIdentifierType(TradeIdentifierTypeEnum identifierType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("issuerReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getIssuerReference());
			processRosetta(path.newSubPath("issuer"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getIssuer());
			processRosetta(path.newSubPath("assignedIdentifier"), processor, AssignedIdentifier.AssignedIdentifierBuilder.class, getAssignedIdentifier());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("identifierType"), TradeIdentifierTypeEnum.class, getIdentifierType(), this);
		}
		

		TradeIdentifier.TradeIdentifierBuilder prune();
	}

	/*********************** Immutable Implementation of TradeIdentifier  ***********************/
	class TradeIdentifierImpl extends Identifier.IdentifierImpl implements TradeIdentifier {
		private final TradeIdentifierTypeEnum identifierType;
		
		protected TradeIdentifierImpl(TradeIdentifier.TradeIdentifierBuilder builder) {
			super(builder);
			this.identifierType = builder.getIdentifierType();
		}
		
		@Override
		@RosettaAttribute("identifierType")
		@RuneAttribute("identifierType")
		public TradeIdentifierTypeEnum getIdentifierType() {
			return identifierType;
		}
		
		@Override
		public TradeIdentifier build() {
			return this;
		}
		
		@Override
		public TradeIdentifier.TradeIdentifierBuilder toBuilder() {
			TradeIdentifier.TradeIdentifierBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(TradeIdentifier.TradeIdentifierBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getIdentifierType()).ifPresent(builder::setIdentifierType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			TradeIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifierType, _that.getIdentifierType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (identifierType != null ? identifierType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradeIdentifier {" +
				"identifierType=" + this.identifierType +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of TradeIdentifier  ***********************/
	class TradeIdentifierBuilderImpl extends Identifier.IdentifierBuilderImpl implements TradeIdentifier.TradeIdentifierBuilder {
	
		protected TradeIdentifierTypeEnum identifierType;
		
		@Override
		@RosettaAttribute("identifierType")
		@RuneAttribute("identifierType")
		public TradeIdentifierTypeEnum getIdentifierType() {
			return identifierType;
		}
		
		@Override
		@RosettaAttribute("issuerReference")
		@RuneAttribute("issuerReference")
		public TradeIdentifier.TradeIdentifierBuilder setIssuerReference(ReferenceWithMetaParty _issuerReference) {
			this.issuerReference = _issuerReference == null ? null : _issuerReference.toBuilder();
			return this;
		}
		
		@Override
		public TradeIdentifier.TradeIdentifierBuilder setIssuerReferenceValue(Party _issuerReference) {
			this.getOrCreateIssuerReference().setValue(_issuerReference);
			return this;
		}
		
		@Override
		@RosettaAttribute("issuer")
		@RuneAttribute("issuer")
		public TradeIdentifier.TradeIdentifierBuilder setIssuer(FieldWithMetaString _issuer) {
			this.issuer = _issuer == null ? null : _issuer.toBuilder();
			return this;
		}
		
		@Override
		public TradeIdentifier.TradeIdentifierBuilder setIssuerValue(String _issuer) {
			this.getOrCreateIssuer().setValue(_issuer);
			return this;
		}
		
		@Override
		@RosettaAttribute("assignedIdentifier")
		@RuneAttribute("assignedIdentifier")
		public TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(AssignedIdentifier _assignedIdentifier) {
			if (_assignedIdentifier != null) {
				this.assignedIdentifier.add(_assignedIdentifier.toBuilder());
			}
			return this;
		}
		
		@Override
		public TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(AssignedIdentifier _assignedIdentifier, int _idx) {
			getIndex(this.assignedIdentifier, _idx, () -> _assignedIdentifier.toBuilder());
			return this;
		}
		
		@Override 
		public TradeIdentifier.TradeIdentifierBuilder addAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
			if (assignedIdentifiers != null) {
				for (final AssignedIdentifier toAdd : assignedIdentifiers) {
					this.assignedIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("assignedIdentifier")
		public TradeIdentifier.TradeIdentifierBuilder setAssignedIdentifier(List<? extends AssignedIdentifier> assignedIdentifiers) {
			if (assignedIdentifiers == null) {
				this.assignedIdentifier = new ArrayList<>();
			} else {
				this.assignedIdentifier = assignedIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public TradeIdentifier.TradeIdentifierBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("identifierType")
		@RuneAttribute("identifierType")
		public TradeIdentifier.TradeIdentifierBuilder setIdentifierType(TradeIdentifierTypeEnum _identifierType) {
			this.identifierType = _identifierType == null ? null : _identifierType;
			return this;
		}
		
		@Override
		public TradeIdentifier build() {
			return new TradeIdentifier.TradeIdentifierImpl(this);
		}
		
		@Override
		public TradeIdentifier.TradeIdentifierBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradeIdentifier.TradeIdentifierBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getIdentifierType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public TradeIdentifier.TradeIdentifierBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			TradeIdentifier.TradeIdentifierBuilder o = (TradeIdentifier.TradeIdentifierBuilder) other;
			
			
			merger.mergeBasic(getIdentifierType(), o.getIdentifierType(), this::setIdentifierType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			TradeIdentifier _that = getType().cast(o);
		
			if (!Objects.equals(identifierType, _that.getIdentifierType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (identifierType != null ? identifierType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TradeIdentifierBuilder {" +
				"identifierType=" + this.identifierType +
			'}' + " " + super.toString();
		}
	}
}
