package cdm.event.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.Party.PartyBuilder;
import cdm.base.staticdata.party.PartyRole;
import cdm.base.staticdata.party.PartyRole.PartyRoleBuilder;
import cdm.event.common.CollateralBalance;
import cdm.event.common.CollateralBalance.CollateralBalanceBuilder;
import cdm.event.common.CollateralPortfolio;
import cdm.event.common.MarginCallBase;
import cdm.event.common.MarginCallBase.MarginCallBaseBuilder;
import cdm.event.common.MarginCallBase.MarginCallBaseBuilderImpl;
import cdm.event.common.MarginCallBase.MarginCallBaseImpl;
import cdm.event.common.MarginCallExposure;
import cdm.event.common.MarginCallExposure.MarginCallExposureBuilder;
import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.MarginCallInstructionType.MarginCallInstructionTypeBuilder;
import cdm.event.common.MarginCallIssuance;
import cdm.event.common.MarginCallIssuance.MarginCallIssuanceBuilder;
import cdm.event.common.MarginCallIssuance.MarginCallIssuanceBuilderImpl;
import cdm.event.common.MarginCallIssuance.MarginCallIssuanceImpl;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
import cdm.event.common.meta.MarginCallIssuanceMeta;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder;
import cdm.legaldocumentation.common.AgreementName;
import cdm.legaldocumentation.common.AgreementName.AgreementNameBuilder;
import cdm.observable.asset.Money;
import cdm.observable.asset.Money.MoneyBuilder;
import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents common attributes required for a Margin Call Issuance under a legal agreement such as a credit support document or equivalent.
 * @version ${project.version}
 */
@RosettaDataType(value="MarginCallIssuance", builder=MarginCallIssuance.MarginCallIssuanceBuilderImpl.class, version="${project.version}")
@RuneDataType(value="MarginCallIssuance", model="Common Domain Model", builder=MarginCallIssuance.MarginCallIssuanceBuilderImpl.class, version="${project.version}")
public interface MarginCallIssuance extends MarginCallBase {

	MarginCallIssuanceMeta metaData = new MarginCallIssuanceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the amount of margin being called for which accounts for margin calculation inclusive of exposure, independent amount,threshold,collateral balance, MTA, rounding increments (in base currency detailed in supporting collateral agreement).
	 */
	Money getCallAmountInBaseCurrency();
	/**
	 * Specifies the details to describe or identify non-cash collateral eligible assets for recall purposes.
	 */
	List<? extends EligibleCollateralCriteria> getRecallNonCashCollateralDescription();

	/*********************** Build Methods  ***********************/
	MarginCallIssuance build();
	
	MarginCallIssuance.MarginCallIssuanceBuilder toBuilder();
	
	static MarginCallIssuance.MarginCallIssuanceBuilder builder() {
		return new MarginCallIssuance.MarginCallIssuanceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends MarginCallIssuance> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends MarginCallIssuance> getType() {
		return MarginCallIssuance.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("instructionType"), processor, MarginCallInstructionType.class, getInstructionType());
		processRosetta(path.newSubPath("party"), processor, Party.class, getParty());
		processRosetta(path.newSubPath("partyRole"), processor, PartyRole.class, getPartyRole());
		processRosetta(path.newSubPath("clearingBroker"), processor, Party.class, getClearingBroker());
		processRosetta(path.newSubPath("callIdentifier"), processor, Identifier.class, getCallIdentifier());
		processRosetta(path.newSubPath("callAgreementType"), processor, AgreementName.class, getCallAgreementType());
		processRosetta(path.newSubPath("agreementMinimumTransferAmount"), processor, Money.class, getAgreementMinimumTransferAmount());
		processRosetta(path.newSubPath("agreementThreshold"), processor, Money.class, getAgreementThreshold());
		processRosetta(path.newSubPath("agreementRounding"), processor, Money.class, getAgreementRounding());
		processor.processBasic(path.newSubPath("regMarginType"), RegMarginTypeEnum.class, getRegMarginType(), this);
		processor.processBasic(path.newSubPath("regIMRole"), RegIMRoleEnum.class, getRegIMRole(), this);
		processRosetta(path.newSubPath("baseCurrencyExposure"), processor, MarginCallExposure.class, getBaseCurrencyExposure());
		processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.class, getCollateralPortfolio());
		processRosetta(path.newSubPath("independentAmountBalance"), processor, CollateralBalance.class, getIndependentAmountBalance());
		processRosetta(path.newSubPath("callAmountInBaseCurrency"), processor, Money.class, getCallAmountInBaseCurrency());
		processRosetta(path.newSubPath("recallNonCashCollateralDescription"), processor, EligibleCollateralCriteria.class, getRecallNonCashCollateralDescription());
	}
	

	/*********************** Builder Interface  ***********************/
	interface MarginCallIssuanceBuilder extends MarginCallIssuance, MarginCallBase.MarginCallBaseBuilder {
		Money.MoneyBuilder getOrCreateCallAmountInBaseCurrency();
		@Override
		Money.MoneyBuilder getCallAmountInBaseCurrency();
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateRecallNonCashCollateralDescription(int _index);
		@Override
		List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getRecallNonCashCollateralDescription();
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setInstructionType(MarginCallInstructionType instructionType);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addParty(Party party);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addParty(Party party, int _idx);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addParty(List<? extends Party> party);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setParty(List<? extends Party> party);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(PartyRole partyRole);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(PartyRole partyRole, int _idx);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(List<? extends PartyRole> partyRole);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setPartyRole(List<? extends PartyRole> partyRole);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setClearingBroker(Party clearingBroker);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setCallIdentifier(Identifier callIdentifier);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setCallAgreementType(AgreementName callAgreementType);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setAgreementMinimumTransferAmount(Money agreementMinimumTransferAmount);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setAgreementThreshold(Money agreementThreshold);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setAgreementRounding(Money agreementRounding);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setRegMarginType(RegMarginTypeEnum regMarginType);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setRegIMRole(RegIMRoleEnum regIMRole);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setBaseCurrencyExposure(MarginCallExposure baseCurrencyExposure);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio collateralPortfolio);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setCollateralPortfolioValue(CollateralPortfolio collateralPortfolio);
		@Override
		MarginCallIssuance.MarginCallIssuanceBuilder setIndependentAmountBalance(CollateralBalance independentAmountBalance);
		MarginCallIssuance.MarginCallIssuanceBuilder setCallAmountInBaseCurrency(Money callAmountInBaseCurrency);
		MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(EligibleCollateralCriteria recallNonCashCollateralDescription);
		MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(EligibleCollateralCriteria recallNonCashCollateralDescription, int _idx);
		MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(List<? extends EligibleCollateralCriteria> recallNonCashCollateralDescription);
		MarginCallIssuance.MarginCallIssuanceBuilder setRecallNonCashCollateralDescription(List<? extends EligibleCollateralCriteria> recallNonCashCollateralDescription);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("instructionType"), processor, MarginCallInstructionType.MarginCallInstructionTypeBuilder.class, getInstructionType());
			processRosetta(path.newSubPath("party"), processor, Party.PartyBuilder.class, getParty());
			processRosetta(path.newSubPath("partyRole"), processor, PartyRole.PartyRoleBuilder.class, getPartyRole());
			processRosetta(path.newSubPath("clearingBroker"), processor, Party.PartyBuilder.class, getClearingBroker());
			processRosetta(path.newSubPath("callIdentifier"), processor, Identifier.IdentifierBuilder.class, getCallIdentifier());
			processRosetta(path.newSubPath("callAgreementType"), processor, AgreementName.AgreementNameBuilder.class, getCallAgreementType());
			processRosetta(path.newSubPath("agreementMinimumTransferAmount"), processor, Money.MoneyBuilder.class, getAgreementMinimumTransferAmount());
			processRosetta(path.newSubPath("agreementThreshold"), processor, Money.MoneyBuilder.class, getAgreementThreshold());
			processRosetta(path.newSubPath("agreementRounding"), processor, Money.MoneyBuilder.class, getAgreementRounding());
			processor.processBasic(path.newSubPath("regMarginType"), RegMarginTypeEnum.class, getRegMarginType(), this);
			processor.processBasic(path.newSubPath("regIMRole"), RegIMRoleEnum.class, getRegIMRole(), this);
			processRosetta(path.newSubPath("baseCurrencyExposure"), processor, MarginCallExposure.MarginCallExposureBuilder.class, getBaseCurrencyExposure());
			processRosetta(path.newSubPath("collateralPortfolio"), processor, ReferenceWithMetaCollateralPortfolio.ReferenceWithMetaCollateralPortfolioBuilder.class, getCollateralPortfolio());
			processRosetta(path.newSubPath("independentAmountBalance"), processor, CollateralBalance.CollateralBalanceBuilder.class, getIndependentAmountBalance());
			processRosetta(path.newSubPath("callAmountInBaseCurrency"), processor, Money.MoneyBuilder.class, getCallAmountInBaseCurrency());
			processRosetta(path.newSubPath("recallNonCashCollateralDescription"), processor, EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder.class, getRecallNonCashCollateralDescription());
		}
		

		MarginCallIssuance.MarginCallIssuanceBuilder prune();
	}

	/*********************** Immutable Implementation of MarginCallIssuance  ***********************/
	class MarginCallIssuanceImpl extends MarginCallBase.MarginCallBaseImpl implements MarginCallIssuance {
		private final Money callAmountInBaseCurrency;
		private final List<? extends EligibleCollateralCriteria> recallNonCashCollateralDescription;
		
		protected MarginCallIssuanceImpl(MarginCallIssuance.MarginCallIssuanceBuilder builder) {
			super(builder);
			this.callAmountInBaseCurrency = ofNullable(builder.getCallAmountInBaseCurrency()).map(f->f.build()).orElse(null);
			this.recallNonCashCollateralDescription = ofNullable(builder.getRecallNonCashCollateralDescription()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("callAmountInBaseCurrency")
		@RuneAttribute("callAmountInBaseCurrency")
		public Money getCallAmountInBaseCurrency() {
			return callAmountInBaseCurrency;
		}
		
		@Override
		@RosettaAttribute("recallNonCashCollateralDescription")
		@RuneAttribute("recallNonCashCollateralDescription")
		public List<? extends EligibleCollateralCriteria> getRecallNonCashCollateralDescription() {
			return recallNonCashCollateralDescription;
		}
		
		@Override
		public MarginCallIssuance build() {
			return this;
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder toBuilder() {
			MarginCallIssuance.MarginCallIssuanceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(MarginCallIssuance.MarginCallIssuanceBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getCallAmountInBaseCurrency()).ifPresent(builder::setCallAmountInBaseCurrency);
			ofNullable(getRecallNonCashCollateralDescription()).ifPresent(builder::setRecallNonCashCollateralDescription);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MarginCallIssuance _that = getType().cast(o);
		
			if (!Objects.equals(callAmountInBaseCurrency, _that.getCallAmountInBaseCurrency())) return false;
			if (!ListEquals.listEquals(recallNonCashCollateralDescription, _that.getRecallNonCashCollateralDescription())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (callAmountInBaseCurrency != null ? callAmountInBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (recallNonCashCollateralDescription != null ? recallNonCashCollateralDescription.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallIssuance {" +
				"callAmountInBaseCurrency=" + this.callAmountInBaseCurrency + ", " +
				"recallNonCashCollateralDescription=" + this.recallNonCashCollateralDescription +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of MarginCallIssuance  ***********************/
	class MarginCallIssuanceBuilderImpl extends MarginCallBase.MarginCallBaseBuilderImpl implements MarginCallIssuance.MarginCallIssuanceBuilder {
	
		protected Money.MoneyBuilder callAmountInBaseCurrency;
		protected List<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> recallNonCashCollateralDescription = new ArrayList<>();
		
		@Override
		@RosettaAttribute("callAmountInBaseCurrency")
		@RuneAttribute("callAmountInBaseCurrency")
		public Money.MoneyBuilder getCallAmountInBaseCurrency() {
			return callAmountInBaseCurrency;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateCallAmountInBaseCurrency() {
			Money.MoneyBuilder result;
			if (callAmountInBaseCurrency!=null) {
				result = callAmountInBaseCurrency;
			}
			else {
				result = callAmountInBaseCurrency = Money.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("recallNonCashCollateralDescription")
		@RuneAttribute("recallNonCashCollateralDescription")
		public List<? extends EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder> getRecallNonCashCollateralDescription() {
			return recallNonCashCollateralDescription;
		}
		
		@Override
		public EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder getOrCreateRecallNonCashCollateralDescription(int _index) {
		
			if (recallNonCashCollateralDescription==null) {
				this.recallNonCashCollateralDescription = new ArrayList<>();
			}
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder result;
			return getIndex(recallNonCashCollateralDescription, _index, () -> {
						EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder newRecallNonCashCollateralDescription = EligibleCollateralCriteria.builder();
						return newRecallNonCashCollateralDescription;
					});
		}
		
		@Override
		@RosettaAttribute("instructionType")
		@RuneAttribute("instructionType")
		public MarginCallIssuance.MarginCallIssuanceBuilder setInstructionType(MarginCallInstructionType _instructionType) {
			this.instructionType = _instructionType == null ? null : _instructionType.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("party")
		@RuneAttribute("party")
		public MarginCallIssuance.MarginCallIssuanceBuilder addParty(Party _party) {
			if (_party != null) {
				this.party.add(_party.toBuilder());
			}
			return this;
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder addParty(Party _party, int _idx) {
			getIndex(this.party, _idx, () -> _party.toBuilder());
			return this;
		}
		
		@Override 
		public MarginCallIssuance.MarginCallIssuanceBuilder addParty(List<? extends Party> partys) {
			if (partys != null) {
				for (final Party toAdd : partys) {
					this.party.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("party")
		public MarginCallIssuance.MarginCallIssuanceBuilder setParty(List<? extends Party> partys) {
			if (partys == null) {
				this.party = new ArrayList<>();
			} else {
				this.party = partys.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("partyRole")
		@RuneAttribute("partyRole")
		public MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(PartyRole _partyRole) {
			if (_partyRole != null) {
				this.partyRole.add(_partyRole.toBuilder());
			}
			return this;
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(PartyRole _partyRole, int _idx) {
			getIndex(this.partyRole, _idx, () -> _partyRole.toBuilder());
			return this;
		}
		
		@Override 
		public MarginCallIssuance.MarginCallIssuanceBuilder addPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles != null) {
				for (final PartyRole toAdd : partyRoles) {
					this.partyRole.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("partyRole")
		public MarginCallIssuance.MarginCallIssuanceBuilder setPartyRole(List<? extends PartyRole> partyRoles) {
			if (partyRoles == null) {
				this.partyRole = new ArrayList<>();
			} else {
				this.partyRole = partyRoles.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("clearingBroker")
		@RuneAttribute("clearingBroker")
		public MarginCallIssuance.MarginCallIssuanceBuilder setClearingBroker(Party _clearingBroker) {
			this.clearingBroker = _clearingBroker == null ? null : _clearingBroker.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("callIdentifier")
		@RuneAttribute("callIdentifier")
		public MarginCallIssuance.MarginCallIssuanceBuilder setCallIdentifier(Identifier _callIdentifier) {
			this.callIdentifier = _callIdentifier == null ? null : _callIdentifier.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("callAgreementType")
		@RuneAttribute("callAgreementType")
		public MarginCallIssuance.MarginCallIssuanceBuilder setCallAgreementType(AgreementName _callAgreementType) {
			this.callAgreementType = _callAgreementType == null ? null : _callAgreementType.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("agreementMinimumTransferAmount")
		@RuneAttribute("agreementMinimumTransferAmount")
		public MarginCallIssuance.MarginCallIssuanceBuilder setAgreementMinimumTransferAmount(Money _agreementMinimumTransferAmount) {
			this.agreementMinimumTransferAmount = _agreementMinimumTransferAmount == null ? null : _agreementMinimumTransferAmount.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("agreementThreshold")
		@RuneAttribute("agreementThreshold")
		public MarginCallIssuance.MarginCallIssuanceBuilder setAgreementThreshold(Money _agreementThreshold) {
			this.agreementThreshold = _agreementThreshold == null ? null : _agreementThreshold.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("agreementRounding")
		@RuneAttribute("agreementRounding")
		public MarginCallIssuance.MarginCallIssuanceBuilder setAgreementRounding(Money _agreementRounding) {
			this.agreementRounding = _agreementRounding == null ? null : _agreementRounding.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("regMarginType")
		@RuneAttribute("regMarginType")
		public MarginCallIssuance.MarginCallIssuanceBuilder setRegMarginType(RegMarginTypeEnum _regMarginType) {
			this.regMarginType = _regMarginType == null ? null : _regMarginType;
			return this;
		}
		
		@Override
		@RosettaAttribute("regIMRole")
		@RuneAttribute("regIMRole")
		public MarginCallIssuance.MarginCallIssuanceBuilder setRegIMRole(RegIMRoleEnum _regIMRole) {
			this.regIMRole = _regIMRole == null ? null : _regIMRole;
			return this;
		}
		
		@Override
		@RosettaAttribute("baseCurrencyExposure")
		@RuneAttribute("baseCurrencyExposure")
		public MarginCallIssuance.MarginCallIssuanceBuilder setBaseCurrencyExposure(MarginCallExposure _baseCurrencyExposure) {
			this.baseCurrencyExposure = _baseCurrencyExposure == null ? null : _baseCurrencyExposure.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("collateralPortfolio")
		@RuneAttribute("collateralPortfolio")
		public MarginCallIssuance.MarginCallIssuanceBuilder setCollateralPortfolio(ReferenceWithMetaCollateralPortfolio _collateralPortfolio) {
			this.collateralPortfolio = _collateralPortfolio == null ? null : _collateralPortfolio.toBuilder();
			return this;
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder setCollateralPortfolioValue(CollateralPortfolio _collateralPortfolio) {
			this.getOrCreateCollateralPortfolio().setValue(_collateralPortfolio);
			return this;
		}
		
		@Override
		@RosettaAttribute("independentAmountBalance")
		@RuneAttribute("independentAmountBalance")
		public MarginCallIssuance.MarginCallIssuanceBuilder setIndependentAmountBalance(CollateralBalance _independentAmountBalance) {
			this.independentAmountBalance = _independentAmountBalance == null ? null : _independentAmountBalance.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("callAmountInBaseCurrency")
		@RuneAttribute("callAmountInBaseCurrency")
		public MarginCallIssuance.MarginCallIssuanceBuilder setCallAmountInBaseCurrency(Money _callAmountInBaseCurrency) {
			this.callAmountInBaseCurrency = _callAmountInBaseCurrency == null ? null : _callAmountInBaseCurrency.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("recallNonCashCollateralDescription")
		@RuneAttribute("recallNonCashCollateralDescription")
		public MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(EligibleCollateralCriteria _recallNonCashCollateralDescription) {
			if (_recallNonCashCollateralDescription != null) {
				this.recallNonCashCollateralDescription.add(_recallNonCashCollateralDescription.toBuilder());
			}
			return this;
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(EligibleCollateralCriteria _recallNonCashCollateralDescription, int _idx) {
			getIndex(this.recallNonCashCollateralDescription, _idx, () -> _recallNonCashCollateralDescription.toBuilder());
			return this;
		}
		
		@Override 
		public MarginCallIssuance.MarginCallIssuanceBuilder addRecallNonCashCollateralDescription(List<? extends EligibleCollateralCriteria> recallNonCashCollateralDescriptions) {
			if (recallNonCashCollateralDescriptions != null) {
				for (final EligibleCollateralCriteria toAdd : recallNonCashCollateralDescriptions) {
					this.recallNonCashCollateralDescription.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("recallNonCashCollateralDescription")
		public MarginCallIssuance.MarginCallIssuanceBuilder setRecallNonCashCollateralDescription(List<? extends EligibleCollateralCriteria> recallNonCashCollateralDescriptions) {
			if (recallNonCashCollateralDescriptions == null) {
				this.recallNonCashCollateralDescription = new ArrayList<>();
			} else {
				this.recallNonCashCollateralDescription = recallNonCashCollateralDescriptions.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public MarginCallIssuance build() {
			return new MarginCallIssuance.MarginCallIssuanceImpl(this);
		}
		
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder prune() {
			super.prune();
			if (callAmountInBaseCurrency!=null && !callAmountInBaseCurrency.prune().hasData()) callAmountInBaseCurrency = null;
			recallNonCashCollateralDescription = recallNonCashCollateralDescription.stream().filter(b->b!=null).<EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getCallAmountInBaseCurrency()!=null && getCallAmountInBaseCurrency().hasData()) return true;
			if (getRecallNonCashCollateralDescription()!=null && getRecallNonCashCollateralDescription().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public MarginCallIssuance.MarginCallIssuanceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			MarginCallIssuance.MarginCallIssuanceBuilder o = (MarginCallIssuance.MarginCallIssuanceBuilder) other;
			
			merger.mergeRosetta(getCallAmountInBaseCurrency(), o.getCallAmountInBaseCurrency(), this::setCallAmountInBaseCurrency);
			merger.mergeRosetta(getRecallNonCashCollateralDescription(), o.getRecallNonCashCollateralDescription(), this::getOrCreateRecallNonCashCollateralDescription);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			MarginCallIssuance _that = getType().cast(o);
		
			if (!Objects.equals(callAmountInBaseCurrency, _that.getCallAmountInBaseCurrency())) return false;
			if (!ListEquals.listEquals(recallNonCashCollateralDescription, _that.getRecallNonCashCollateralDescription())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (callAmountInBaseCurrency != null ? callAmountInBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (recallNonCashCollateralDescription != null ? recallNonCashCollateralDescription.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "MarginCallIssuanceBuilder {" +
				"callAmountInBaseCurrency=" + this.callAmountInBaseCurrency + ", " +
				"recallNonCashCollateralDescription=" + this.recallNonCashCollateralDescription +
			'}' + " " + super.toString();
		}
	}
}
