package cdm.base.staticdata.party;

import cdm.base.staticdata.party.Account;
import cdm.base.staticdata.party.Account.AccountBuilder;
import cdm.base.staticdata.party.Account.AccountBuilderImpl;
import cdm.base.staticdata.party.Account.AccountImpl;
import cdm.base.staticdata.party.AccountTypeEnum;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.meta.AccountMeta;
import cdm.base.staticdata.party.metafields.FieldWithMetaAccountTypeEnum;
import cdm.base.staticdata.party.metafields.FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty;
import cdm.base.staticdata.party.metafields.ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder;
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
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.model.metafields.MetaFields.MetaFieldsBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to specify an account as an account number alongside, optionally. an account name, an account type, an account beneficiary and a servicing party.
 * @version ${project.version}
 */
@RosettaDataType(value="Account", builder=Account.AccountBuilderImpl.class, version="${project.version}")
@RuneDataType(value="Account", model="Common Domain Model", builder=Account.AccountBuilderImpl.class, version="${project.version}")
public interface Account extends RosettaModelObject, GlobalKey {

	AccountMeta metaData = new AccountMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A reference to the party to which the account refers to.
	 */
	ReferenceWithMetaParty getPartyReference();
	/**
	 * The account number.
	 */
	FieldWithMetaString getAccountNumber();
	/**
	 * The name by which the account is known.
	 */
	FieldWithMetaString getAccountName();
	/**
	 * The type of account, e.g. client, house.
	 */
	FieldWithMetaAccountTypeEnum getAccountType();
	/**
	 * A reference to the party beneficiary of the account.
	 */
	ReferenceWithMetaParty getAccountBeneficiary();
	/**
	 * The reference to the legal entity that services the account, i.e. in the books of which the account is held.
	 */
	ReferenceWithMetaParty getServicingParty();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	Account build();
	
	Account.AccountBuilder toBuilder();
	
	static Account.AccountBuilder builder() {
		return new Account.AccountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Account> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends Account> getType() {
		return Account.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.class, getPartyReference());
		processRosetta(path.newSubPath("accountNumber"), processor, FieldWithMetaString.class, getAccountNumber());
		processRosetta(path.newSubPath("accountName"), processor, FieldWithMetaString.class, getAccountName());
		processRosetta(path.newSubPath("accountType"), processor, FieldWithMetaAccountTypeEnum.class, getAccountType());
		processRosetta(path.newSubPath("accountBeneficiary"), processor, ReferenceWithMetaParty.class, getAccountBeneficiary());
		processRosetta(path.newSubPath("servicingParty"), processor, ReferenceWithMetaParty.class, getServicingParty());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AccountBuilder extends Account, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder {
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference();
		@Override
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAccountNumber();
		@Override
		FieldWithMetaString.FieldWithMetaStringBuilder getAccountNumber();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAccountName();
		@Override
		FieldWithMetaString.FieldWithMetaStringBuilder getAccountName();
		FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder getOrCreateAccountType();
		@Override
		FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder getAccountType();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateAccountBeneficiary();
		@Override
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getAccountBeneficiary();
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateServicingParty();
		@Override
		ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getServicingParty();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		Account.AccountBuilder setPartyReference(ReferenceWithMetaParty partyReference);
		Account.AccountBuilder setPartyReferenceValue(Party partyReference);
		Account.AccountBuilder setAccountNumber(FieldWithMetaString accountNumber);
		Account.AccountBuilder setAccountNumberValue(String accountNumber);
		Account.AccountBuilder setAccountName(FieldWithMetaString accountName);
		Account.AccountBuilder setAccountNameValue(String accountName);
		Account.AccountBuilder setAccountType(FieldWithMetaAccountTypeEnum accountType);
		Account.AccountBuilder setAccountTypeValue(AccountTypeEnum accountType);
		Account.AccountBuilder setAccountBeneficiary(ReferenceWithMetaParty accountBeneficiary);
		Account.AccountBuilder setAccountBeneficiaryValue(Party accountBeneficiary);
		Account.AccountBuilder setServicingParty(ReferenceWithMetaParty servicingParty);
		Account.AccountBuilder setServicingPartyValue(Party servicingParty);
		Account.AccountBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("partyReference"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getPartyReference());
			processRosetta(path.newSubPath("accountNumber"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getAccountNumber());
			processRosetta(path.newSubPath("accountName"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getAccountName());
			processRosetta(path.newSubPath("accountType"), processor, FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder.class, getAccountType());
			processRosetta(path.newSubPath("accountBeneficiary"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getAccountBeneficiary());
			processRosetta(path.newSubPath("servicingParty"), processor, ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder.class, getServicingParty());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		Account.AccountBuilder prune();
	}

	/*********************** Immutable Implementation of Account  ***********************/
	class AccountImpl implements Account {
		private final ReferenceWithMetaParty partyReference;
		private final FieldWithMetaString accountNumber;
		private final FieldWithMetaString accountName;
		private final FieldWithMetaAccountTypeEnum accountType;
		private final ReferenceWithMetaParty accountBeneficiary;
		private final ReferenceWithMetaParty servicingParty;
		private final MetaFields meta;
		
		protected AccountImpl(Account.AccountBuilder builder) {
			this.partyReference = ofNullable(builder.getPartyReference()).map(f->f.build()).orElse(null);
			this.accountNumber = ofNullable(builder.getAccountNumber()).map(f->f.build()).orElse(null);
			this.accountName = ofNullable(builder.getAccountName()).map(f->f.build()).orElse(null);
			this.accountType = ofNullable(builder.getAccountType()).map(f->f.build()).orElse(null);
			this.accountBeneficiary = ofNullable(builder.getAccountBeneficiary()).map(f->f.build()).orElse(null);
			this.servicingParty = ofNullable(builder.getServicingParty()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("partyReference")
		@RuneAttribute("partyReference")
		public ReferenceWithMetaParty getPartyReference() {
			return partyReference;
		}
		
		@Override
		@RosettaAttribute("accountNumber")
		@RuneAttribute("accountNumber")
		public FieldWithMetaString getAccountNumber() {
			return accountNumber;
		}
		
		@Override
		@RosettaAttribute("accountName")
		@RuneAttribute("accountName")
		public FieldWithMetaString getAccountName() {
			return accountName;
		}
		
		@Override
		@RosettaAttribute("accountType")
		@RuneAttribute("accountType")
		public FieldWithMetaAccountTypeEnum getAccountType() {
			return accountType;
		}
		
		@Override
		@RosettaAttribute("accountBeneficiary")
		@RuneAttribute("accountBeneficiary")
		public ReferenceWithMetaParty getAccountBeneficiary() {
			return accountBeneficiary;
		}
		
		@Override
		@RosettaAttribute("servicingParty")
		@RuneAttribute("servicingParty")
		public ReferenceWithMetaParty getServicingParty() {
			return servicingParty;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public Account build() {
			return this;
		}
		
		@Override
		public Account.AccountBuilder toBuilder() {
			Account.AccountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Account.AccountBuilder builder) {
			ofNullable(getPartyReference()).ifPresent(builder::setPartyReference);
			ofNullable(getAccountNumber()).ifPresent(builder::setAccountNumber);
			ofNullable(getAccountName()).ifPresent(builder::setAccountName);
			ofNullable(getAccountType()).ifPresent(builder::setAccountType);
			ofNullable(getAccountBeneficiary()).ifPresent(builder::setAccountBeneficiary);
			ofNullable(getServicingParty()).ifPresent(builder::setServicingParty);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Account _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(accountNumber, _that.getAccountNumber())) return false;
			if (!Objects.equals(accountName, _that.getAccountName())) return false;
			if (!Objects.equals(accountType, _that.getAccountType())) return false;
			if (!Objects.equals(accountBeneficiary, _that.getAccountBeneficiary())) return false;
			if (!Objects.equals(servicingParty, _that.getServicingParty())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (accountNumber != null ? accountNumber.hashCode() : 0);
			_result = 31 * _result + (accountName != null ? accountName.hashCode() : 0);
			_result = 31 * _result + (accountType != null ? accountType.hashCode() : 0);
			_result = 31 * _result + (accountBeneficiary != null ? accountBeneficiary.hashCode() : 0);
			_result = 31 * _result + (servicingParty != null ? servicingParty.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Account {" +
				"partyReference=" + this.partyReference + ", " +
				"accountNumber=" + this.accountNumber + ", " +
				"accountName=" + this.accountName + ", " +
				"accountType=" + this.accountType + ", " +
				"accountBeneficiary=" + this.accountBeneficiary + ", " +
				"servicingParty=" + this.servicingParty + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of Account  ***********************/
	class AccountBuilderImpl implements Account.AccountBuilder {
	
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder partyReference;
		protected FieldWithMetaString.FieldWithMetaStringBuilder accountNumber;
		protected FieldWithMetaString.FieldWithMetaStringBuilder accountName;
		protected FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder accountType;
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder accountBeneficiary;
		protected ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder servicingParty;
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("partyReference")
		@RuneAttribute("partyReference")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getPartyReference() {
			return partyReference;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreatePartyReference() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (partyReference!=null) {
				result = partyReference;
			}
			else {
				result = partyReference = ReferenceWithMetaParty.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("accountNumber")
		@RuneAttribute("accountNumber")
		public FieldWithMetaString.FieldWithMetaStringBuilder getAccountNumber() {
			return accountNumber;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAccountNumber() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (accountNumber!=null) {
				result = accountNumber;
			}
			else {
				result = accountNumber = FieldWithMetaString.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("accountName")
		@RuneAttribute("accountName")
		public FieldWithMetaString.FieldWithMetaStringBuilder getAccountName() {
			return accountName;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateAccountName() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (accountName!=null) {
				result = accountName;
			}
			else {
				result = accountName = FieldWithMetaString.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("accountType")
		@RuneAttribute("accountType")
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder getAccountType() {
			return accountType;
		}
		
		@Override
		public FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder getOrCreateAccountType() {
			FieldWithMetaAccountTypeEnum.FieldWithMetaAccountTypeEnumBuilder result;
			if (accountType!=null) {
				result = accountType;
			}
			else {
				result = accountType = FieldWithMetaAccountTypeEnum.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("accountBeneficiary")
		@RuneAttribute("accountBeneficiary")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getAccountBeneficiary() {
			return accountBeneficiary;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateAccountBeneficiary() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (accountBeneficiary!=null) {
				result = accountBeneficiary;
			}
			else {
				result = accountBeneficiary = ReferenceWithMetaParty.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("servicingParty")
		@RuneAttribute("servicingParty")
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getServicingParty() {
			return servicingParty;
		}
		
		@Override
		public ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder getOrCreateServicingParty() {
			ReferenceWithMetaParty.ReferenceWithMetaPartyBuilder result;
			if (servicingParty!=null) {
				result = servicingParty;
			}
			else {
				result = servicingParty = ReferenceWithMetaParty.builder();
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
		@RosettaAttribute("partyReference")
		@RuneAttribute("partyReference")
		public Account.AccountBuilder setPartyReference(ReferenceWithMetaParty _partyReference) {
			this.partyReference = _partyReference == null ? null : _partyReference.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setPartyReferenceValue(Party _partyReference) {
			this.getOrCreatePartyReference().setValue(_partyReference);
			return this;
		}
		
		@Override
		@RosettaAttribute("accountNumber")
		@RuneAttribute("accountNumber")
		public Account.AccountBuilder setAccountNumber(FieldWithMetaString _accountNumber) {
			this.accountNumber = _accountNumber == null ? null : _accountNumber.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setAccountNumberValue(String _accountNumber) {
			this.getOrCreateAccountNumber().setValue(_accountNumber);
			return this;
		}
		
		@Override
		@RosettaAttribute("accountName")
		@RuneAttribute("accountName")
		public Account.AccountBuilder setAccountName(FieldWithMetaString _accountName) {
			this.accountName = _accountName == null ? null : _accountName.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setAccountNameValue(String _accountName) {
			this.getOrCreateAccountName().setValue(_accountName);
			return this;
		}
		
		@Override
		@RosettaAttribute("accountType")
		@RuneAttribute("accountType")
		public Account.AccountBuilder setAccountType(FieldWithMetaAccountTypeEnum _accountType) {
			this.accountType = _accountType == null ? null : _accountType.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setAccountTypeValue(AccountTypeEnum _accountType) {
			this.getOrCreateAccountType().setValue(_accountType);
			return this;
		}
		
		@Override
		@RosettaAttribute("accountBeneficiary")
		@RuneAttribute("accountBeneficiary")
		public Account.AccountBuilder setAccountBeneficiary(ReferenceWithMetaParty _accountBeneficiary) {
			this.accountBeneficiary = _accountBeneficiary == null ? null : _accountBeneficiary.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setAccountBeneficiaryValue(Party _accountBeneficiary) {
			this.getOrCreateAccountBeneficiary().setValue(_accountBeneficiary);
			return this;
		}
		
		@Override
		@RosettaAttribute("servicingParty")
		@RuneAttribute("servicingParty")
		public Account.AccountBuilder setServicingParty(ReferenceWithMetaParty _servicingParty) {
			this.servicingParty = _servicingParty == null ? null : _servicingParty.toBuilder();
			return this;
		}
		
		@Override
		public Account.AccountBuilder setServicingPartyValue(Party _servicingParty) {
			this.getOrCreateServicingParty().setValue(_servicingParty);
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public Account.AccountBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public Account build() {
			return new Account.AccountImpl(this);
		}
		
		@Override
		public Account.AccountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Account.AccountBuilder prune() {
			if (partyReference!=null && !partyReference.prune().hasData()) partyReference = null;
			if (accountNumber!=null && !accountNumber.prune().hasData()) accountNumber = null;
			if (accountName!=null && !accountName.prune().hasData()) accountName = null;
			if (accountType!=null && !accountType.prune().hasData()) accountType = null;
			if (accountBeneficiary!=null && !accountBeneficiary.prune().hasData()) accountBeneficiary = null;
			if (servicingParty!=null && !servicingParty.prune().hasData()) servicingParty = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPartyReference()!=null && getPartyReference().hasData()) return true;
			if (getAccountNumber()!=null) return true;
			if (getAccountName()!=null) return true;
			if (getAccountType()!=null) return true;
			if (getAccountBeneficiary()!=null && getAccountBeneficiary().hasData()) return true;
			if (getServicingParty()!=null && getServicingParty().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Account.AccountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Account.AccountBuilder o = (Account.AccountBuilder) other;
			
			merger.mergeRosetta(getPartyReference(), o.getPartyReference(), this::setPartyReference);
			merger.mergeRosetta(getAccountNumber(), o.getAccountNumber(), this::setAccountNumber);
			merger.mergeRosetta(getAccountName(), o.getAccountName(), this::setAccountName);
			merger.mergeRosetta(getAccountType(), o.getAccountType(), this::setAccountType);
			merger.mergeRosetta(getAccountBeneficiary(), o.getAccountBeneficiary(), this::setAccountBeneficiary);
			merger.mergeRosetta(getServicingParty(), o.getServicingParty(), this::setServicingParty);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Account _that = getType().cast(o);
		
			if (!Objects.equals(partyReference, _that.getPartyReference())) return false;
			if (!Objects.equals(accountNumber, _that.getAccountNumber())) return false;
			if (!Objects.equals(accountName, _that.getAccountName())) return false;
			if (!Objects.equals(accountType, _that.getAccountType())) return false;
			if (!Objects.equals(accountBeneficiary, _that.getAccountBeneficiary())) return false;
			if (!Objects.equals(servicingParty, _that.getServicingParty())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (partyReference != null ? partyReference.hashCode() : 0);
			_result = 31 * _result + (accountNumber != null ? accountNumber.hashCode() : 0);
			_result = 31 * _result + (accountName != null ? accountName.hashCode() : 0);
			_result = 31 * _result + (accountType != null ? accountType.hashCode() : 0);
			_result = 31 * _result + (accountBeneficiary != null ? accountBeneficiary.hashCode() : 0);
			_result = 31 * _result + (servicingParty != null ? servicingParty.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AccountBuilder {" +
				"partyReference=" + this.partyReference + ", " +
				"accountNumber=" + this.accountNumber + ", " +
				"accountName=" + this.accountName + ", " +
				"accountType=" + this.accountType + ", " +
				"accountBeneficiary=" + this.accountBeneficiary + ", " +
				"servicingParty=" + this.servicingParty + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
