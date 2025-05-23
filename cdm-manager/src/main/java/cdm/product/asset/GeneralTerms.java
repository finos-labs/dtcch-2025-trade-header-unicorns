package cdm.product.asset;

import cdm.observable.asset.CreditIndex;
import cdm.observable.asset.CreditIndex.CreditIndexBuilder;
import cdm.product.asset.BasketReferenceInformation;
import cdm.product.asset.BasketReferenceInformation.BasketReferenceInformationBuilder;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.GeneralTerms.GeneralTermsBuilder;
import cdm.product.asset.GeneralTerms.GeneralTermsBuilderImpl;
import cdm.product.asset.GeneralTerms.GeneralTermsImpl;
import cdm.product.asset.ReferenceInformation;
import cdm.product.asset.ReferenceInformation.ReferenceInformationBuilder;
import cdm.product.asset.meta.GeneralTermsMeta;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 *  A class specifying a set of non-monetary terms for the Credit Derivative Transaction, including the buyer and seller and selected items from the ISDA 2014 Credit Definition article II, such as the reference obligation and related terms. The CDM GeneralTerms class corresponds to the FpML GeneralTerms complex type, except that the effectiveDate and scheduledTerminationDate have been positioned as part of the InterestRatePayout class in the CDM instead of in GeneralTerms.
 * @version ${project.version}
 */
@RosettaDataType(value="GeneralTerms", builder=GeneralTerms.GeneralTermsBuilderImpl.class, version="${project.version}")
@RuneDataType(value="GeneralTerms", model="Common Domain Model", builder=GeneralTerms.GeneralTermsBuilderImpl.class, version="${project.version}")
public interface GeneralTerms extends RosettaModelObject {

	GeneralTermsMeta metaData = new GeneralTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This attribute contains all the terms relevant to defining the reference entity and reference obligation(s).
	 */
	ReferenceInformation getReferenceInformation();
	/**
	 * This attribute contains all the terms relevant to the underlying Index.
	 */
	CreditIndex getIndexReferenceInformation();
	/**
	 * This attribute contains all the terms relevant to defining the Credit Default Swap Basket.
	 */
	BasketReferenceInformation getBasketReferenceInformation();
	/**
	 * This attribute is used for representing information contained in the Additional Terms field of the 2003 Master Credit Derivatives confirm.
	 */
	List<? extends FieldWithMetaString> getAdditionalTerm();
	/**
	 * Value of this attribute set to &#39;true&#39; indicates that substitution is applicable.
	 */
	Boolean getSubstitution();
	/**
	 * Value of this attribute set to &#39;true&#39; indicates that modified equity delivery is applicable.
	 */
	Boolean getModifiedEquityDelivery();

	/*********************** Build Methods  ***********************/
	GeneralTerms build();
	
	GeneralTerms.GeneralTermsBuilder toBuilder();
	
	static GeneralTerms.GeneralTermsBuilder builder() {
		return new GeneralTerms.GeneralTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends GeneralTerms> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends GeneralTerms> getType() {
		return GeneralTerms.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referenceInformation"), processor, ReferenceInformation.class, getReferenceInformation());
		processRosetta(path.newSubPath("indexReferenceInformation"), processor, CreditIndex.class, getIndexReferenceInformation());
		processRosetta(path.newSubPath("basketReferenceInformation"), processor, BasketReferenceInformation.class, getBasketReferenceInformation());
		processRosetta(path.newSubPath("additionalTerm"), processor, FieldWithMetaString.class, getAdditionalTerm());
		processor.processBasic(path.newSubPath("substitution"), Boolean.class, getSubstitution(), this);
		processor.processBasic(path.newSubPath("modifiedEquityDelivery"), Boolean.class, getModifiedEquityDelivery(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface GeneralTermsBuilder extends GeneralTerms, RosettaModelObjectBuilder {
		ReferenceInformation.ReferenceInformationBuilder getOrCreateReferenceInformation();
		@Override
		ReferenceInformation.ReferenceInformationBuilder getReferenceInformation();
		CreditIndex.CreditIndexBuilder getOrCreateIndexReferenceInformation();
		@Override
		CreditIndex.CreditIndexBuilder getIndexReferenceInformation();
		BasketReferenceInformation.BasketReferenceInformationBuilder getOrCreateBasketReferenceInformation();
		@Override
		BasketReferenceInformation.BasketReferenceInformationBuilder getBasketReferenceInformation();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAdditionalTerm(int _index);
		@Override
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getAdditionalTerm();
		GeneralTerms.GeneralTermsBuilder setReferenceInformation(ReferenceInformation referenceInformation);
		GeneralTerms.GeneralTermsBuilder setIndexReferenceInformation(CreditIndex indexReferenceInformation);
		GeneralTerms.GeneralTermsBuilder setBasketReferenceInformation(BasketReferenceInformation basketReferenceInformation);
		GeneralTerms.GeneralTermsBuilder addAdditionalTerm(FieldWithMetaString additionalTerm);
		GeneralTerms.GeneralTermsBuilder addAdditionalTerm(FieldWithMetaString additionalTerm, int _idx);
		GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(String additionalTerm);
		GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(String additionalTerm, int _idx);
		GeneralTerms.GeneralTermsBuilder addAdditionalTerm(List<? extends FieldWithMetaString> additionalTerm);
		GeneralTerms.GeneralTermsBuilder setAdditionalTerm(List<? extends FieldWithMetaString> additionalTerm);
		GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(List<? extends String> additionalTerm);
		GeneralTerms.GeneralTermsBuilder setAdditionalTermValue(List<? extends String> additionalTerm);
		GeneralTerms.GeneralTermsBuilder setSubstitution(Boolean substitution);
		GeneralTerms.GeneralTermsBuilder setModifiedEquityDelivery(Boolean modifiedEquityDelivery);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referenceInformation"), processor, ReferenceInformation.ReferenceInformationBuilder.class, getReferenceInformation());
			processRosetta(path.newSubPath("indexReferenceInformation"), processor, CreditIndex.CreditIndexBuilder.class, getIndexReferenceInformation());
			processRosetta(path.newSubPath("basketReferenceInformation"), processor, BasketReferenceInformation.BasketReferenceInformationBuilder.class, getBasketReferenceInformation());
			processRosetta(path.newSubPath("additionalTerm"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getAdditionalTerm());
			processor.processBasic(path.newSubPath("substitution"), Boolean.class, getSubstitution(), this);
			processor.processBasic(path.newSubPath("modifiedEquityDelivery"), Boolean.class, getModifiedEquityDelivery(), this);
		}
		

		GeneralTerms.GeneralTermsBuilder prune();
	}

	/*********************** Immutable Implementation of GeneralTerms  ***********************/
	class GeneralTermsImpl implements GeneralTerms {
		private final ReferenceInformation referenceInformation;
		private final CreditIndex indexReferenceInformation;
		private final BasketReferenceInformation basketReferenceInformation;
		private final List<? extends FieldWithMetaString> additionalTerm;
		private final Boolean substitution;
		private final Boolean modifiedEquityDelivery;
		
		protected GeneralTermsImpl(GeneralTerms.GeneralTermsBuilder builder) {
			this.referenceInformation = ofNullable(builder.getReferenceInformation()).map(f->f.build()).orElse(null);
			this.indexReferenceInformation = ofNullable(builder.getIndexReferenceInformation()).map(f->f.build()).orElse(null);
			this.basketReferenceInformation = ofNullable(builder.getBasketReferenceInformation()).map(f->f.build()).orElse(null);
			this.additionalTerm = ofNullable(builder.getAdditionalTerm()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.substitution = builder.getSubstitution();
			this.modifiedEquityDelivery = builder.getModifiedEquityDelivery();
		}
		
		@Override
		@RosettaAttribute("referenceInformation")
		@RuneAttribute("referenceInformation")
		public ReferenceInformation getReferenceInformation() {
			return referenceInformation;
		}
		
		@Override
		@RosettaAttribute("indexReferenceInformation")
		@RuneAttribute("indexReferenceInformation")
		public CreditIndex getIndexReferenceInformation() {
			return indexReferenceInformation;
		}
		
		@Override
		@RosettaAttribute("basketReferenceInformation")
		@RuneAttribute("basketReferenceInformation")
		public BasketReferenceInformation getBasketReferenceInformation() {
			return basketReferenceInformation;
		}
		
		@Override
		@RosettaAttribute("additionalTerm")
		@RuneAttribute("additionalTerm")
		public List<? extends FieldWithMetaString> getAdditionalTerm() {
			return additionalTerm;
		}
		
		@Override
		@RosettaAttribute("substitution")
		@RuneAttribute("substitution")
		public Boolean getSubstitution() {
			return substitution;
		}
		
		@Override
		@RosettaAttribute("modifiedEquityDelivery")
		@RuneAttribute("modifiedEquityDelivery")
		public Boolean getModifiedEquityDelivery() {
			return modifiedEquityDelivery;
		}
		
		@Override
		public GeneralTerms build() {
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder toBuilder() {
			GeneralTerms.GeneralTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(GeneralTerms.GeneralTermsBuilder builder) {
			ofNullable(getReferenceInformation()).ifPresent(builder::setReferenceInformation);
			ofNullable(getIndexReferenceInformation()).ifPresent(builder::setIndexReferenceInformation);
			ofNullable(getBasketReferenceInformation()).ifPresent(builder::setBasketReferenceInformation);
			ofNullable(getAdditionalTerm()).ifPresent(builder::setAdditionalTerm);
			ofNullable(getSubstitution()).ifPresent(builder::setSubstitution);
			ofNullable(getModifiedEquityDelivery()).ifPresent(builder::setModifiedEquityDelivery);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			GeneralTerms _that = getType().cast(o);
		
			if (!Objects.equals(referenceInformation, _that.getReferenceInformation())) return false;
			if (!Objects.equals(indexReferenceInformation, _that.getIndexReferenceInformation())) return false;
			if (!Objects.equals(basketReferenceInformation, _that.getBasketReferenceInformation())) return false;
			if (!ListEquals.listEquals(additionalTerm, _that.getAdditionalTerm())) return false;
			if (!Objects.equals(substitution, _that.getSubstitution())) return false;
			if (!Objects.equals(modifiedEquityDelivery, _that.getModifiedEquityDelivery())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceInformation != null ? referenceInformation.hashCode() : 0);
			_result = 31 * _result + (indexReferenceInformation != null ? indexReferenceInformation.hashCode() : 0);
			_result = 31 * _result + (basketReferenceInformation != null ? basketReferenceInformation.hashCode() : 0);
			_result = 31 * _result + (additionalTerm != null ? additionalTerm.hashCode() : 0);
			_result = 31 * _result + (substitution != null ? substitution.hashCode() : 0);
			_result = 31 * _result + (modifiedEquityDelivery != null ? modifiedEquityDelivery.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "GeneralTerms {" +
				"referenceInformation=" + this.referenceInformation + ", " +
				"indexReferenceInformation=" + this.indexReferenceInformation + ", " +
				"basketReferenceInformation=" + this.basketReferenceInformation + ", " +
				"additionalTerm=" + this.additionalTerm + ", " +
				"substitution=" + this.substitution + ", " +
				"modifiedEquityDelivery=" + this.modifiedEquityDelivery +
			'}';
		}
	}

	/*********************** Builder Implementation of GeneralTerms  ***********************/
	class GeneralTermsBuilderImpl implements GeneralTerms.GeneralTermsBuilder {
	
		protected ReferenceInformation.ReferenceInformationBuilder referenceInformation;
		protected CreditIndex.CreditIndexBuilder indexReferenceInformation;
		protected BasketReferenceInformation.BasketReferenceInformationBuilder basketReferenceInformation;
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> additionalTerm = new ArrayList<>();
		protected Boolean substitution;
		protected Boolean modifiedEquityDelivery;
		
		@Override
		@RosettaAttribute("referenceInformation")
		@RuneAttribute("referenceInformation")
		public ReferenceInformation.ReferenceInformationBuilder getReferenceInformation() {
			return referenceInformation;
		}
		
		@Override
		public ReferenceInformation.ReferenceInformationBuilder getOrCreateReferenceInformation() {
			ReferenceInformation.ReferenceInformationBuilder result;
			if (referenceInformation!=null) {
				result = referenceInformation;
			}
			else {
				result = referenceInformation = ReferenceInformation.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("indexReferenceInformation")
		@RuneAttribute("indexReferenceInformation")
		public CreditIndex.CreditIndexBuilder getIndexReferenceInformation() {
			return indexReferenceInformation;
		}
		
		@Override
		public CreditIndex.CreditIndexBuilder getOrCreateIndexReferenceInformation() {
			CreditIndex.CreditIndexBuilder result;
			if (indexReferenceInformation!=null) {
				result = indexReferenceInformation;
			}
			else {
				result = indexReferenceInformation = CreditIndex.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("basketReferenceInformation")
		@RuneAttribute("basketReferenceInformation")
		public BasketReferenceInformation.BasketReferenceInformationBuilder getBasketReferenceInformation() {
			return basketReferenceInformation;
		}
		
		@Override
		public BasketReferenceInformation.BasketReferenceInformationBuilder getOrCreateBasketReferenceInformation() {
			BasketReferenceInformation.BasketReferenceInformationBuilder result;
			if (basketReferenceInformation!=null) {
				result = basketReferenceInformation;
			}
			else {
				result = basketReferenceInformation = BasketReferenceInformation.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("additionalTerm")
		@RuneAttribute("additionalTerm")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getAdditionalTerm() {
			return additionalTerm;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAdditionalTerm(int _index) {
		
			if (additionalTerm==null) {
				this.additionalTerm = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(additionalTerm, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newAdditionalTerm = FieldWithMetaString.builder();
						return newAdditionalTerm;
					});
		}
		
		@Override
		@RosettaAttribute("substitution")
		@RuneAttribute("substitution")
		public Boolean getSubstitution() {
			return substitution;
		}
		
		@Override
		@RosettaAttribute("modifiedEquityDelivery")
		@RuneAttribute("modifiedEquityDelivery")
		public Boolean getModifiedEquityDelivery() {
			return modifiedEquityDelivery;
		}
		
		@Override
		@RosettaAttribute("referenceInformation")
		@RuneAttribute("referenceInformation")
		public GeneralTerms.GeneralTermsBuilder setReferenceInformation(ReferenceInformation _referenceInformation) {
			this.referenceInformation = _referenceInformation == null ? null : _referenceInformation.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("indexReferenceInformation")
		@RuneAttribute("indexReferenceInformation")
		public GeneralTerms.GeneralTermsBuilder setIndexReferenceInformation(CreditIndex _indexReferenceInformation) {
			this.indexReferenceInformation = _indexReferenceInformation == null ? null : _indexReferenceInformation.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("basketReferenceInformation")
		@RuneAttribute("basketReferenceInformation")
		public GeneralTerms.GeneralTermsBuilder setBasketReferenceInformation(BasketReferenceInformation _basketReferenceInformation) {
			this.basketReferenceInformation = _basketReferenceInformation == null ? null : _basketReferenceInformation.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("additionalTerm")
		@RuneAttribute("additionalTerm")
		public GeneralTerms.GeneralTermsBuilder addAdditionalTerm(FieldWithMetaString _additionalTerm) {
			if (_additionalTerm != null) {
				this.additionalTerm.add(_additionalTerm.toBuilder());
			}
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder addAdditionalTerm(FieldWithMetaString _additionalTerm, int _idx) {
			getIndex(this.additionalTerm, _idx, () -> _additionalTerm.toBuilder());
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(String _additionalTerm) {
			this.getOrCreateAdditionalTerm(-1).setValue(_additionalTerm);
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(String _additionalTerm, int _idx) {
			this.getOrCreateAdditionalTerm(_idx).setValue(_additionalTerm);
			return this;
		}
		
		@Override 
		public GeneralTerms.GeneralTermsBuilder addAdditionalTerm(List<? extends FieldWithMetaString> additionalTerms) {
			if (additionalTerms != null) {
				for (final FieldWithMetaString toAdd : additionalTerms) {
					this.additionalTerm.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("additionalTerm")
		public GeneralTerms.GeneralTermsBuilder setAdditionalTerm(List<? extends FieldWithMetaString> additionalTerms) {
			if (additionalTerms == null) {
				this.additionalTerm = new ArrayList<>();
			} else {
				this.additionalTerm = additionalTerms.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder addAdditionalTermValue(List<? extends String> additionalTerms) {
			if (additionalTerms != null) {
				for (final String toAdd : additionalTerms) {
					this.addAdditionalTermValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder setAdditionalTermValue(List<? extends String> additionalTerms) {
			this.additionalTerm.clear();
			if (additionalTerms != null) {
				additionalTerms.forEach(this::addAdditionalTermValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("substitution")
		@RuneAttribute("substitution")
		public GeneralTerms.GeneralTermsBuilder setSubstitution(Boolean _substitution) {
			this.substitution = _substitution == null ? null : _substitution;
			return this;
		}
		
		@Override
		@RosettaAttribute("modifiedEquityDelivery")
		@RuneAttribute("modifiedEquityDelivery")
		public GeneralTerms.GeneralTermsBuilder setModifiedEquityDelivery(Boolean _modifiedEquityDelivery) {
			this.modifiedEquityDelivery = _modifiedEquityDelivery == null ? null : _modifiedEquityDelivery;
			return this;
		}
		
		@Override
		public GeneralTerms build() {
			return new GeneralTerms.GeneralTermsImpl(this);
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public GeneralTerms.GeneralTermsBuilder prune() {
			if (referenceInformation!=null && !referenceInformation.prune().hasData()) referenceInformation = null;
			if (indexReferenceInformation!=null && !indexReferenceInformation.prune().hasData()) indexReferenceInformation = null;
			if (basketReferenceInformation!=null && !basketReferenceInformation.prune().hasData()) basketReferenceInformation = null;
			additionalTerm = additionalTerm.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferenceInformation()!=null && getReferenceInformation().hasData()) return true;
			if (getIndexReferenceInformation()!=null && getIndexReferenceInformation().hasData()) return true;
			if (getBasketReferenceInformation()!=null && getBasketReferenceInformation().hasData()) return true;
			if (getAdditionalTerm()!=null && !getAdditionalTerm().isEmpty()) return true;
			if (getSubstitution()!=null) return true;
			if (getModifiedEquityDelivery()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public GeneralTerms.GeneralTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			GeneralTerms.GeneralTermsBuilder o = (GeneralTerms.GeneralTermsBuilder) other;
			
			merger.mergeRosetta(getReferenceInformation(), o.getReferenceInformation(), this::setReferenceInformation);
			merger.mergeRosetta(getIndexReferenceInformation(), o.getIndexReferenceInformation(), this::setIndexReferenceInformation);
			merger.mergeRosetta(getBasketReferenceInformation(), o.getBasketReferenceInformation(), this::setBasketReferenceInformation);
			merger.mergeRosetta(getAdditionalTerm(), o.getAdditionalTerm(), this::getOrCreateAdditionalTerm);
			
			merger.mergeBasic(getSubstitution(), o.getSubstitution(), this::setSubstitution);
			merger.mergeBasic(getModifiedEquityDelivery(), o.getModifiedEquityDelivery(), this::setModifiedEquityDelivery);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			GeneralTerms _that = getType().cast(o);
		
			if (!Objects.equals(referenceInformation, _that.getReferenceInformation())) return false;
			if (!Objects.equals(indexReferenceInformation, _that.getIndexReferenceInformation())) return false;
			if (!Objects.equals(basketReferenceInformation, _that.getBasketReferenceInformation())) return false;
			if (!ListEquals.listEquals(additionalTerm, _that.getAdditionalTerm())) return false;
			if (!Objects.equals(substitution, _that.getSubstitution())) return false;
			if (!Objects.equals(modifiedEquityDelivery, _that.getModifiedEquityDelivery())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceInformation != null ? referenceInformation.hashCode() : 0);
			_result = 31 * _result + (indexReferenceInformation != null ? indexReferenceInformation.hashCode() : 0);
			_result = 31 * _result + (basketReferenceInformation != null ? basketReferenceInformation.hashCode() : 0);
			_result = 31 * _result + (additionalTerm != null ? additionalTerm.hashCode() : 0);
			_result = 31 * _result + (substitution != null ? substitution.hashCode() : 0);
			_result = 31 * _result + (modifiedEquityDelivery != null ? modifiedEquityDelivery.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "GeneralTermsBuilder {" +
				"referenceInformation=" + this.referenceInformation + ", " +
				"indexReferenceInformation=" + this.indexReferenceInformation + ", " +
				"basketReferenceInformation=" + this.basketReferenceInformation + ", " +
				"additionalTerm=" + this.additionalTerm + ", " +
				"substitution=" + this.substitution + ", " +
				"modifiedEquityDelivery=" + this.modifiedEquityDelivery +
			'}';
		}
	}
}
