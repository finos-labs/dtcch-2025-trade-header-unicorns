package cdm.legaldocumentation.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRole.PartyRoleBuilder;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
import cdm.legaldocumentation.common.AgreementTerms;
import cdm.legaldocumentation.common.AgreementTerms.AgreementTermsBuilder;
import cdm.legaldocumentation.common.LegalAgreement;
import cdm.legaldocumentation.common.LegalAgreement.LegalAgreementBuilder;
import cdm.legaldocumentation.common.LegalAgreement.LegalAgreementBuilderImpl;
import cdm.legaldocumentation.common.LegalAgreement.LegalAgreementImpl;
import cdm.legaldocumentation.common.LegalAgreementBase;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseBuilder;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseBuilderImpl;
import cdm.legaldocumentation.common.LegalAgreementBase.LegalAgreementBaseImpl;
import cdm.legaldocumentation.common.LegalAgreementIdentification;
import cdm.legaldocumentation.common.LegalAgreementIdentification.LegalAgreementIdentificationBuilder;
import cdm.legaldocumentation.common.Resource;
import cdm.legaldocumentation.common.Resource.ResourceBuilder;
import cdm.legaldocumentation.common.UmbrellaAgreement;
import cdm.legaldocumentation.common.UmbrellaAgreement.UmbrellaAgreementBuilder;
import cdm.legaldocumentation.common.meta.LegalAgreementMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.model.metafields.MetaFields.MetaFieldsBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * The specification of a legal agreement between two parties, being negotiated or having been executed. This includes the baseline information and the optional specialised elections
 * @version ${project.version}
 */
@RosettaDataType(value="LegalAgreement", builder=LegalAgreement.LegalAgreementBuilderImpl.class, version="${project.version}")
@RuneDataType(value="LegalAgreement", model="Common Domain Model", builder=LegalAgreement.LegalAgreementBuilderImpl.class, version="${project.version}")
public interface LegalAgreement extends LegalAgreementBase, GlobalKey {

	LegalAgreementMeta metaData = new LegalAgreementMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specification of the content of the legal agreement.
	 */
	AgreementTerms getAgreementTerms();
	/**
	 * Specifies the agreement(s) that govern the agreement, either as a reference to such agreements when specified as part of the CDM, or through identification of some of the key terms of those agreements, such as the type of agreement, the publisher, the vintage, the agreement identifier and the agreement date.
	 */
	List<? extends LegalAgreement> getRelatedAgreements();
	/**
	 * The determination of whether Umbrella Agreement terms are applicable (True) or Not Applicable (False).
	 */
	UmbrellaAgreement getUmbrellaAgreement();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	LegalAgreement build();
	
	LegalAgreement.LegalAgreementBuilder toBuilder();
	
	static LegalAgreement.LegalAgreementBuilder builder() {
		return new LegalAgreement.LegalAgreementBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LegalAgreement> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends LegalAgreement> getType() {
		return LegalAgreement.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("agreementDate"), Date.class, getAgreementDate(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processRosetta(path.newSubPath("identifier"), processor, Identifier.class, getIdentifier());
		processRosetta(path.newSubPath("legalAgreementIdentification"), processor, LegalAgreementIdentification.class, getLegalAgreementIdentification());
		processRosetta(path.newSubPath("contractualParty"), processor, ReferenceWithMetaParty.class, getContractualParty());
		processRosetta(path.newSubPath("otherParty"), processor, PartyRole.class, getOtherParty());
		processRosetta(path.newSubPath("attachment"), processor, Resource.class, getAttachment());
		processRosetta(path.newSubPath("agreementTerms"), processor, AgreementTerms.class, getAgreementTerms());
		processRosetta(path.newSubPath("relatedAgreements"), processor, LegalAgreement.class, getRelatedAgreements());
		processRosetta(path.newSubPath("umbrellaAgreement"), processor, UmbrellaAgreement.class, getUmbrellaAgreement());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LegalAgreementBuilder extends LegalAgreement, LegalAgreementBase.LegalAgreementBaseBuilder, GlobalKey.GlobalKeyBuilder {
		AgreementTerms.AgreementTermsBuilder getOrCreateAgreementTerms();
		@Override
		AgreementTerms.AgreementTermsBuilder getAgreementTerms();
		LegalAgreement.LegalAgreementBuilder getOrCreateRelatedAgreements(int _index);
		@Override
		List<? extends LegalAgreement.LegalAgreementBuilder> getRelatedAgreements();
		UmbrellaAgreement.UmbrellaAgreementBuilder getOrCreateUmbrellaAgreement();
		@Override
		UmbrellaAgreement.UmbrellaAgreementBuilder getUmbrellaAgreement();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		@Override
		LegalAgreement.LegalAgreementBuilder setAgreementDate(Date agreementDate);
		@Override
		LegalAgreement.LegalAgreementBuilder setEffectiveDate(Date effectiveDate);
		@Override
		LegalAgreement.LegalAgreementBuilder addIdentifier(Identifier identifier);
		@Override
		LegalAgreement.LegalAgreementBuilder addIdentifier(Identifier identifier, int _idx);
		@Override
		LegalAgreement.LegalAgreementBuilder addIdentifier(List<? extends Identifier> identifier);
		@Override
		LegalAgreement.LegalAgreementBuilder setIdentifier(List<? extends Identifier> identifier);
		@Override
		LegalAgreement.LegalAgreementBuilder setLegalAgreementIdentification(LegalAgreementIdentification legalAgreementIdentification);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualParty(ReferenceWithMetaParty contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualParty(ReferenceWithMetaParty contractualParty, int _idx);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualPartyValue(Party contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualPartyValue(Party contractualParty, int _idx);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualParty(List<? extends ReferenceWithMetaParty> contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder setContractualParty(List<? extends ReferenceWithMetaParty> contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addContractualPartyValue(List<? extends Party> contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder setContractualPartyValue(List<? extends Party> contractualParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addOtherParty(PartyRole otherParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addOtherParty(PartyRole otherParty, int _idx);
		@Override
		LegalAgreement.LegalAgreementBuilder addOtherParty(List<? extends PartyRole> otherParty);
		@Override
		LegalAgreement.LegalAgreementBuilder setOtherParty(List<? extends PartyRole> otherParty);
		@Override
		LegalAgreement.LegalAgreementBuilder addAttachment(Resource attachment);
		@Override
		LegalAgreement.LegalAgreementBuilder addAttachment(Resource attachment, int _idx);
		@Override
		LegalAgreement.LegalAgreementBuilder addAttachment(List<? extends Resource> attachment);
		@Override
		LegalAgreement.LegalAgreementBuilder setAttachment(List<? extends Resource> attachment);
		LegalAgreement.LegalAgreementBuilder setAgreementTerms(AgreementTerms agreementTerms);
		LegalAgreement.LegalAgreementBuilder addRelatedAgreements(LegalAgreement relatedAgreements);
		LegalAgreement.LegalAgreementBuilder addRelatedAgreements(LegalAgreement relatedAgreements, int _idx);
		LegalAgreement.LegalAgreementBuilder addRelatedAgreements(List<? extends LegalAgreement> relatedAgreements);
		LegalAgreement.LegalAgreementBuilder setRelatedAgreements(List<? extends LegalAgreement> relatedAgreements);
		LegalAgreement.LegalAgreementBuilder setUmbrellaAgreement(UmbrellaAgreement umbrellaAgreement);
		LegalAgreement.LegalAgreementBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("agreementDate"), Date.class, getAgreementDate(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processRosetta(path.newSubPath("identifier"), processor, Identifier.IdentifierBuilder.class, getIdentifier());
			processRosetta(path.newSubPath("legalAgreementIdentification"), processor, LegalAgreementIdentification.LegalAgreementIdentificationBuilder.class, getLegalAgreementIdentification());
			processRosetta(path.newSubPath("contractualParty"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getContractualParty());
			processRosetta(path.newSubPath("otherParty"), processor, PartyRole.PartyRoleBuilder.class, getOtherParty());
			processRosetta(path.newSubPath("attachment"), processor, Resource.ResourceBuilder.class, getAttachment());
			processRosetta(path.newSubPath("agreementTerms"), processor, AgreementTerms.AgreementTermsBuilder.class, getAgreementTerms());
			processRosetta(path.newSubPath("relatedAgreements"), processor, LegalAgreement.LegalAgreementBuilder.class, getRelatedAgreements());
			processRosetta(path.newSubPath("umbrellaAgreement"), processor, UmbrellaAgreement.UmbrellaAgreementBuilder.class, getUmbrellaAgreement());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		LegalAgreement.LegalAgreementBuilder prune();
	}

	/*********************** Immutable Implementation of LegalAgreement  ***********************/
	class LegalAgreementImpl extends LegalAgreementBase.LegalAgreementBaseImpl implements LegalAgreement {
		private final AgreementTerms agreementTerms;
		private final List<? extends LegalAgreement> relatedAgreements;
		private final UmbrellaAgreement umbrellaAgreement;
		private final MetaFields meta;
		
		protected LegalAgreementImpl(LegalAgreement.LegalAgreementBuilder builder) {
			super(builder);
			this.agreementTerms = ofNullable(builder.getAgreementTerms()).map(f->f.build()).orElse(null);
			this.relatedAgreements = ofNullable(builder.getRelatedAgreements()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.umbrellaAgreement = ofNullable(builder.getUmbrellaAgreement()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("agreementTerms")
		@RuneAttribute("agreementTerms")
		public AgreementTerms getAgreementTerms() {
			return agreementTerms;
		}
		
		@Override
		@RosettaAttribute("relatedAgreements")
		@RuneAttribute("relatedAgreements")
		public List<? extends LegalAgreement> getRelatedAgreements() {
			return relatedAgreements;
		}
		
		@Override
		@RosettaAttribute("umbrellaAgreement")
		@RuneAttribute("umbrellaAgreement")
		public UmbrellaAgreement getUmbrellaAgreement() {
			return umbrellaAgreement;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public LegalAgreement build() {
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder toBuilder() {
			LegalAgreement.LegalAgreementBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LegalAgreement.LegalAgreementBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getAgreementTerms()).ifPresent(builder::setAgreementTerms);
			ofNullable(getRelatedAgreements()).ifPresent(builder::setRelatedAgreements);
			ofNullable(getUmbrellaAgreement()).ifPresent(builder::setUmbrellaAgreement);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LegalAgreement _that = getType().cast(o);
		
			if (!Objects.equals(agreementTerms, _that.getAgreementTerms())) return false;
			if (!ListEquals.listEquals(relatedAgreements, _that.getRelatedAgreements())) return false;
			if (!Objects.equals(umbrellaAgreement, _that.getUmbrellaAgreement())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (agreementTerms != null ? agreementTerms.hashCode() : 0);
			_result = 31 * _result + (relatedAgreements != null ? relatedAgreements.hashCode() : 0);
			_result = 31 * _result + (umbrellaAgreement != null ? umbrellaAgreement.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreement {" +
				"agreementTerms=" + this.agreementTerms + ", " +
				"relatedAgreements=" + this.relatedAgreements + ", " +
				"umbrellaAgreement=" + this.umbrellaAgreement + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of LegalAgreement  ***********************/
	class LegalAgreementBuilderImpl extends LegalAgreementBase.LegalAgreementBaseBuilderImpl implements LegalAgreement.LegalAgreementBuilder {
	
		protected AgreementTerms.AgreementTermsBuilder agreementTerms;
		protected List<LegalAgreement.LegalAgreementBuilder> relatedAgreements = new ArrayList<>();
		protected UmbrellaAgreement.UmbrellaAgreementBuilder umbrellaAgreement;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("agreementTerms")
		@RuneAttribute("agreementTerms")
		public AgreementTerms.AgreementTermsBuilder getAgreementTerms() {
			return agreementTerms;
		}
		
		@Override
		public AgreementTerms.AgreementTermsBuilder getOrCreateAgreementTerms() {
			AgreementTerms.AgreementTermsBuilder result;
			if (agreementTerms!=null) {
				result = agreementTerms;
			}
			else {
				result = agreementTerms = AgreementTerms.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("relatedAgreements")
		@RuneAttribute("relatedAgreements")
		public List<? extends LegalAgreement.LegalAgreementBuilder> getRelatedAgreements() {
			return relatedAgreements;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder getOrCreateRelatedAgreements(int _index) {
		
			if (relatedAgreements==null) {
				this.relatedAgreements = new ArrayList<>();
			}
			LegalAgreement.LegalAgreementBuilder result;
			return getIndex(relatedAgreements, _index, () -> {
						LegalAgreement.LegalAgreementBuilder newRelatedAgreements = LegalAgreement.builder();
						return newRelatedAgreements;
					});
		}
		
		@Override
		@RosettaAttribute("umbrellaAgreement")
		@RuneAttribute("umbrellaAgreement")
		public UmbrellaAgreement.UmbrellaAgreementBuilder getUmbrellaAgreement() {
			return umbrellaAgreement;
		}
		
		@Override
		public UmbrellaAgreement.UmbrellaAgreementBuilder getOrCreateUmbrellaAgreement() {
			UmbrellaAgreement.UmbrellaAgreementBuilder result;
			if (umbrellaAgreement!=null) {
				result = umbrellaAgreement;
			}
			else {
				result = umbrellaAgreement = UmbrellaAgreement.builder();
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
		@RosettaAttribute("agreementDate")
		@RuneAttribute("agreementDate")
		public LegalAgreement.LegalAgreementBuilder setAgreementDate(Date _agreementDate) {
			this.agreementDate = _agreementDate == null ? null : _agreementDate;
			return this;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		@RuneAttribute("effectiveDate")
		public LegalAgreement.LegalAgreementBuilder setEffectiveDate(Date _effectiveDate) {
			this.effectiveDate = _effectiveDate == null ? null : _effectiveDate;
			return this;
		}
		
		@Override
		@RosettaAttribute("identifier")
		@RuneAttribute("identifier")
		public LegalAgreement.LegalAgreementBuilder addIdentifier(Identifier _identifier) {
			if (_identifier != null) {
				this.identifier.add(_identifier.toBuilder());
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addIdentifier(Identifier _identifier, int _idx) {
			getIndex(this.identifier, _idx, () -> _identifier.toBuilder());
			return this;
		}
		
		@Override 
		public LegalAgreement.LegalAgreementBuilder addIdentifier(List<? extends Identifier> identifiers) {
			if (identifiers != null) {
				for (final Identifier toAdd : identifiers) {
					this.identifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("identifier")
		public LegalAgreement.LegalAgreementBuilder setIdentifier(List<? extends Identifier> identifiers) {
			if (identifiers == null) {
				this.identifier = new ArrayList<>();
			} else {
				this.identifier = identifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("legalAgreementIdentification")
		@RuneAttribute("legalAgreementIdentification")
		public LegalAgreement.LegalAgreementBuilder setLegalAgreementIdentification(LegalAgreementIdentification _legalAgreementIdentification) {
			this.legalAgreementIdentification = _legalAgreementIdentification == null ? null : _legalAgreementIdentification.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("contractualParty")
		@RuneAttribute("contractualParty")
		public LegalAgreement.LegalAgreementBuilder addContractualParty(ReferenceWithMetaParty _contractualParty) {
			if (_contractualParty != null) {
				this.contractualParty.add(_contractualParty.toBuilder());
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addContractualParty(ReferenceWithMetaParty _contractualParty, int _idx) {
			getIndex(this.contractualParty, _idx, () -> _contractualParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addContractualPartyValue(Party _contractualParty) {
			this.getOrCreateContractualParty(-1).setValue(_contractualParty.toBuilder());
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addContractualPartyValue(Party _contractualParty, int _idx) {
			this.getOrCreateContractualParty(_idx).setValue(_contractualParty.toBuilder());
			return this;
		}
		
		@Override 
		public LegalAgreement.LegalAgreementBuilder addContractualParty(List<? extends ReferenceWithMetaParty> contractualPartys) {
			if (contractualPartys != null) {
				for (final ReferenceWithMetaParty toAdd : contractualPartys) {
					this.contractualParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("contractualParty")
		public LegalAgreement.LegalAgreementBuilder setContractualParty(List<? extends ReferenceWithMetaParty> contractualPartys) {
			if (contractualPartys == null) {
				this.contractualParty = new ArrayList<>();
			} else {
				this.contractualParty = contractualPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addContractualPartyValue(List<? extends Party> contractualPartys) {
			if (contractualPartys != null) {
				for (final Party toAdd : contractualPartys) {
					this.addContractualPartyValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder setContractualPartyValue(List<? extends Party> contractualPartys) {
			this.contractualParty.clear();
			if (contractualPartys != null) {
				contractualPartys.forEach(this::addContractualPartyValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("otherParty")
		@RuneAttribute("otherParty")
		public LegalAgreement.LegalAgreementBuilder addOtherParty(PartyRole _otherParty) {
			if (_otherParty != null) {
				this.otherParty.add(_otherParty.toBuilder());
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addOtherParty(PartyRole _otherParty, int _idx) {
			getIndex(this.otherParty, _idx, () -> _otherParty.toBuilder());
			return this;
		}
		
		@Override 
		public LegalAgreement.LegalAgreementBuilder addOtherParty(List<? extends PartyRole> otherPartys) {
			if (otherPartys != null) {
				for (final PartyRole toAdd : otherPartys) {
					this.otherParty.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("otherParty")
		public LegalAgreement.LegalAgreementBuilder setOtherParty(List<? extends PartyRole> otherPartys) {
			if (otherPartys == null) {
				this.otherParty = new ArrayList<>();
			} else {
				this.otherParty = otherPartys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("attachment")
		@RuneAttribute("attachment")
		public LegalAgreement.LegalAgreementBuilder addAttachment(Resource _attachment) {
			if (_attachment != null) {
				this.attachment.add(_attachment.toBuilder());
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addAttachment(Resource _attachment, int _idx) {
			getIndex(this.attachment, _idx, () -> _attachment.toBuilder());
			return this;
		}
		
		@Override 
		public LegalAgreement.LegalAgreementBuilder addAttachment(List<? extends Resource> attachments) {
			if (attachments != null) {
				for (final Resource toAdd : attachments) {
					this.attachment.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("attachment")
		public LegalAgreement.LegalAgreementBuilder setAttachment(List<? extends Resource> attachments) {
			if (attachments == null) {
				this.attachment = new ArrayList<>();
			} else {
				this.attachment = attachments.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("agreementTerms")
		@RuneAttribute("agreementTerms")
		public LegalAgreement.LegalAgreementBuilder setAgreementTerms(AgreementTerms _agreementTerms) {
			this.agreementTerms = _agreementTerms == null ? null : _agreementTerms.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("relatedAgreements")
		@RuneAttribute("relatedAgreements")
		public LegalAgreement.LegalAgreementBuilder addRelatedAgreements(LegalAgreement _relatedAgreements) {
			if (_relatedAgreements != null) {
				this.relatedAgreements.add(_relatedAgreements.toBuilder());
			}
			return this;
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder addRelatedAgreements(LegalAgreement _relatedAgreements, int _idx) {
			getIndex(this.relatedAgreements, _idx, () -> _relatedAgreements.toBuilder());
			return this;
		}
		
		@Override 
		public LegalAgreement.LegalAgreementBuilder addRelatedAgreements(List<? extends LegalAgreement> relatedAgreementss) {
			if (relatedAgreementss != null) {
				for (final LegalAgreement toAdd : relatedAgreementss) {
					this.relatedAgreements.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("relatedAgreements")
		public LegalAgreement.LegalAgreementBuilder setRelatedAgreements(List<? extends LegalAgreement> relatedAgreementss) {
			if (relatedAgreementss == null) {
				this.relatedAgreements = new ArrayList<>();
			} else {
				this.relatedAgreements = relatedAgreementss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("umbrellaAgreement")
		@RuneAttribute("umbrellaAgreement")
		public LegalAgreement.LegalAgreementBuilder setUmbrellaAgreement(UmbrellaAgreement _umbrellaAgreement) {
			this.umbrellaAgreement = _umbrellaAgreement == null ? null : _umbrellaAgreement.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public LegalAgreement.LegalAgreementBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public LegalAgreement build() {
			return new LegalAgreement.LegalAgreementImpl(this);
		}
		
		@Override
		public LegalAgreement.LegalAgreementBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreement.LegalAgreementBuilder prune() {
			super.prune();
			if (agreementTerms!=null && !agreementTerms.prune().hasData()) agreementTerms = null;
			relatedAgreements = relatedAgreements.stream().filter(b->b!=null).<LegalAgreement.LegalAgreementBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (umbrellaAgreement!=null && !umbrellaAgreement.prune().hasData()) umbrellaAgreement = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getAgreementTerms()!=null && getAgreementTerms().hasData()) return true;
			if (getRelatedAgreements()!=null && getRelatedAgreements().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getUmbrellaAgreement()!=null && getUmbrellaAgreement().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalAgreement.LegalAgreementBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			LegalAgreement.LegalAgreementBuilder o = (LegalAgreement.LegalAgreementBuilder) other;
			
			merger.mergeRosetta(getAgreementTerms(), o.getAgreementTerms(), this::setAgreementTerms);
			merger.mergeRosetta(getRelatedAgreements(), o.getRelatedAgreements(), this::getOrCreateRelatedAgreements);
			merger.mergeRosetta(getUmbrellaAgreement(), o.getUmbrellaAgreement(), this::setUmbrellaAgreement);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			LegalAgreement _that = getType().cast(o);
		
			if (!Objects.equals(agreementTerms, _that.getAgreementTerms())) return false;
			if (!ListEquals.listEquals(relatedAgreements, _that.getRelatedAgreements())) return false;
			if (!Objects.equals(umbrellaAgreement, _that.getUmbrellaAgreement())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (agreementTerms != null ? agreementTerms.hashCode() : 0);
			_result = 31 * _result + (relatedAgreements != null ? relatedAgreements.hashCode() : 0);
			_result = 31 * _result + (umbrellaAgreement != null ? umbrellaAgreement.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalAgreementBuilder {" +
				"agreementTerms=" + this.agreementTerms + ", " +
				"relatedAgreements=" + this.relatedAgreements + ", " +
				"umbrellaAgreement=" + this.umbrellaAgreement + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
