package cdm.product.common.settlement;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.Quantity;
import cdm.base.math.Quantity.QuantityBuilder;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.asset.FutureValueAmount;
import cdm.product.asset.FutureValueAmount.FutureValueAmountBuilder;
import cdm.product.common.settlement.QuantityMultiplier;
import cdm.product.common.settlement.QuantityMultiplier.QuantityMultiplierBuilder;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.ResolvablePriceQuantity.ResolvablePriceQuantityBuilder;
import cdm.product.common.settlement.ResolvablePriceQuantity.ResolvablePriceQuantityBuilderImpl;
import cdm.product.common.settlement.ResolvablePriceQuantity.ResolvablePriceQuantityImpl;
import cdm.product.common.settlement.meta.ResolvablePriceQuantityMeta;
import cdm.product.common.settlement.metafields.ReferenceWithMetaResolvablePriceQuantity;
import cdm.product.common.settlement.metafields.ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder;
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
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.model.metafields.MetaFields.MetaFieldsBuilder;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Generic class to specify the quantity for different payout legs in a contractual product, when that quantity can vary across payout legs or across time. A resolvable quantity can always be resolved into a single quantity from the quantity notation which has a corresponding asset identifier. In addition to the base case, where quantity is directly specified as a number as part of the quantity notation, the other use cases are: (i) quantity based on some pre-defined schedule (eg amortising notional), (ii) quantity based on some pre-defined events (eg resetting cross-currency notional), or quantity set as reference to another quantity (eg equity notional as no. securities x price).
 * @version ${project.version}
 */
@RosettaDataType(value="ResolvablePriceQuantity", builder=ResolvablePriceQuantity.ResolvablePriceQuantityBuilderImpl.class, version="${project.version}")
@RuneDataType(value="ResolvablePriceQuantity", model="Common Domain Model", builder=ResolvablePriceQuantity.ResolvablePriceQuantityBuilderImpl.class, version="${project.version}")
public interface ResolvablePriceQuantity extends RosettaModelObject, GlobalKey {

	ResolvablePriceQuantityMeta metaData = new ResolvablePriceQuantityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A product&#39;s quantity as a single, non-negative amount.  When specified as part of a product definition, this quantity attribute would not be set.  Instead it is specified on the quantity notation along with an asset identifier matching this payout&#39;s asset identifier.  This allows the quantity to be resolved for a payout leg, which can then be specified here for convenience during data processing.  There needs to be at least one resolvable quantity across payout legs of a product to define an anchor that other payout quantities can refer to.  This attribute is ignored when mapping existing FpML messages.
	 */
	Quantity getResolvedQuantity();
	/**
	 * A payout&#39;s quantity specified as a schedule, which may also contain a single value if that quantity is constant. There can only be a single quantity schedule applicable to a payout: e.g. the notional for an interest rate leg. The quantity must be specified outside of the payout in a PriceQuantity object and only referenced inside the payout using an address.
	 */
	ReferenceWithMetaNonNegativeQuantitySchedule getQuantitySchedule();
	/**
	 * Reference quantity when resolvable quantity is defined as relative to another (resolvable) quantity. A resolvable quantity needs to contain either an absolute quantity or a reference to another (resolvable) quantity. This requirement is captured by a choice rule on the class.
	 */
	ReferenceWithMetaResolvablePriceQuantity getQuantityReference();
	/**
	 * Quantity multiplier is specified on top of a reference quantity and is used as a multiplying factor when resolving the quantity. A quantity multiplier can only exist when the resolvable quantity specifies a reference quantity.
	 */
	QuantityMultiplier getQuantityMultiplier();
	/**
	 * Whether the quantity is resettable
	 */
	Boolean getReset();
	/**
	 * The future value notional is specific to BRL CDI swaps, and is specified alongside the notional amount. The value is calculated as follows: Future Value Notional = Notional Amount * (1 + Fixed Rate) ^ (Fixed Rate Day Count Fraction). The currency should always match that expressed in the notional schedule. The value date should match the adjusted termination date.
	 */
	FutureValueAmount getFutureValueNotional();
	/**
	 * A payout&#39;s price specified as a schedule, which may also contain a single value if that price is constant. There may be multiple prices specified for a single payout: e.g. a floating interest rate leg may specify a spread, a cap and/or floor and a multiplier. The price must be specified outside of the payout in a PriceQuantity object and only referenced inside the payout using an address.
	 */
	List<? extends ReferenceWithMetaPriceSchedule> getPriceSchedule();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ResolvablePriceQuantity build();
	
	ResolvablePriceQuantity.ResolvablePriceQuantityBuilder toBuilder();
	
	static ResolvablePriceQuantity.ResolvablePriceQuantityBuilder builder() {
		return new ResolvablePriceQuantity.ResolvablePriceQuantityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ResolvablePriceQuantity> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends ResolvablePriceQuantity> getType() {
		return ResolvablePriceQuantity.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("resolvedQuantity"), processor, Quantity.class, getResolvedQuantity());
		processRosetta(path.newSubPath("quantitySchedule"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.class, getQuantitySchedule());
		processRosetta(path.newSubPath("quantityReference"), processor, ReferenceWithMetaResolvablePriceQuantity.class, getQuantityReference());
		processRosetta(path.newSubPath("quantityMultiplier"), processor, QuantityMultiplier.class, getQuantityMultiplier());
		processor.processBasic(path.newSubPath("reset"), Boolean.class, getReset(), this);
		processRosetta(path.newSubPath("futureValueNotional"), processor, FutureValueAmount.class, getFutureValueNotional());
		processRosetta(path.newSubPath("priceSchedule"), processor, ReferenceWithMetaPriceSchedule.class, getPriceSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ResolvablePriceQuantityBuilder extends ResolvablePriceQuantity, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder {
		Quantity.QuantityBuilder getOrCreateResolvedQuantity();
		@Override
		Quantity.QuantityBuilder getResolvedQuantity();
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantitySchedule();
		@Override
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getQuantitySchedule();
		ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder getOrCreateQuantityReference();
		@Override
		ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder getQuantityReference();
		QuantityMultiplier.QuantityMultiplierBuilder getOrCreateQuantityMultiplier();
		@Override
		QuantityMultiplier.QuantityMultiplierBuilder getQuantityMultiplier();
		FutureValueAmount.FutureValueAmountBuilder getOrCreateFutureValueNotional();
		@Override
		FutureValueAmount.FutureValueAmountBuilder getFutureValueNotional();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreatePriceSchedule(int _index);
		@Override
		List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getPriceSchedule();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		@Override
		MetaFields.MetaFieldsBuilder getMeta();
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setResolvedQuantity(Quantity resolvedQuantity);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantitySchedule(ReferenceWithMetaNonNegativeQuantitySchedule quantitySchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityScheduleValue(NonNegativeQuantitySchedule quantitySchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityReference(ReferenceWithMetaResolvablePriceQuantity quantityReference);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityReferenceValue(ResolvablePriceQuantity quantityReference);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityMultiplier(QuantityMultiplier quantityMultiplier);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setReset(Boolean reset);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setFutureValueNotional(FutureValueAmount futureValueNotional);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(ReferenceWithMetaPriceSchedule priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(ReferenceWithMetaPriceSchedule priceSchedule, int _idx);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(PriceSchedule priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(PriceSchedule priceSchedule, int _idx);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(List<? extends ReferenceWithMetaPriceSchedule> priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setPriceSchedule(List<? extends ReferenceWithMetaPriceSchedule> priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(List<? extends PriceSchedule> priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setPriceScheduleValue(List<? extends PriceSchedule> priceSchedule);
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("resolvedQuantity"), processor, Quantity.QuantityBuilder.class, getResolvedQuantity());
			processRosetta(path.newSubPath("quantitySchedule"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder.class, getQuantitySchedule());
			processRosetta(path.newSubPath("quantityReference"), processor, ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder.class, getQuantityReference());
			processRosetta(path.newSubPath("quantityMultiplier"), processor, QuantityMultiplier.QuantityMultiplierBuilder.class, getQuantityMultiplier());
			processor.processBasic(path.newSubPath("reset"), Boolean.class, getReset(), this);
			processRosetta(path.newSubPath("futureValueNotional"), processor, FutureValueAmount.FutureValueAmountBuilder.class, getFutureValueNotional());
			processRosetta(path.newSubPath("priceSchedule"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getPriceSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder prune();
	}

	/*********************** Immutable Implementation of ResolvablePriceQuantity  ***********************/
	class ResolvablePriceQuantityImpl implements ResolvablePriceQuantity {
		private final Quantity resolvedQuantity;
		private final ReferenceWithMetaNonNegativeQuantitySchedule quantitySchedule;
		private final ReferenceWithMetaResolvablePriceQuantity quantityReference;
		private final QuantityMultiplier quantityMultiplier;
		private final Boolean reset;
		private final FutureValueAmount futureValueNotional;
		private final List<? extends ReferenceWithMetaPriceSchedule> priceSchedule;
		private final MetaFields meta;
		
		protected ResolvablePriceQuantityImpl(ResolvablePriceQuantity.ResolvablePriceQuantityBuilder builder) {
			this.resolvedQuantity = ofNullable(builder.getResolvedQuantity()).map(f->f.build()).orElse(null);
			this.quantitySchedule = ofNullable(builder.getQuantitySchedule()).map(f->f.build()).orElse(null);
			this.quantityReference = ofNullable(builder.getQuantityReference()).map(f->f.build()).orElse(null);
			this.quantityMultiplier = ofNullable(builder.getQuantityMultiplier()).map(f->f.build()).orElse(null);
			this.reset = builder.getReset();
			this.futureValueNotional = ofNullable(builder.getFutureValueNotional()).map(f->f.build()).orElse(null);
			this.priceSchedule = ofNullable(builder.getPriceSchedule()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("resolvedQuantity")
		@RuneAttribute("resolvedQuantity")
		public Quantity getResolvedQuantity() {
			return resolvedQuantity;
		}
		
		@Override
		@RosettaAttribute("quantitySchedule")
		@RuneAttribute("quantitySchedule")
		public ReferenceWithMetaNonNegativeQuantitySchedule getQuantitySchedule() {
			return quantitySchedule;
		}
		
		@Override
		@RosettaAttribute("quantityReference")
		@RuneAttribute("quantityReference")
		public ReferenceWithMetaResolvablePriceQuantity getQuantityReference() {
			return quantityReference;
		}
		
		@Override
		@RosettaAttribute("quantityMultiplier")
		@RuneAttribute("quantityMultiplier")
		public QuantityMultiplier getQuantityMultiplier() {
			return quantityMultiplier;
		}
		
		@Override
		@RosettaAttribute("reset")
		@RuneAttribute("reset")
		public Boolean getReset() {
			return reset;
		}
		
		@Override
		@RosettaAttribute("futureValueNotional")
		@RuneAttribute("futureValueNotional")
		public FutureValueAmount getFutureValueNotional() {
			return futureValueNotional;
		}
		
		@Override
		@RosettaAttribute("priceSchedule")
		@RuneAttribute("priceSchedule")
		public List<? extends ReferenceWithMetaPriceSchedule> getPriceSchedule() {
			return priceSchedule;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ResolvablePriceQuantity build() {
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder toBuilder() {
			ResolvablePriceQuantity.ResolvablePriceQuantityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ResolvablePriceQuantity.ResolvablePriceQuantityBuilder builder) {
			ofNullable(getResolvedQuantity()).ifPresent(builder::setResolvedQuantity);
			ofNullable(getQuantitySchedule()).ifPresent(builder::setQuantitySchedule);
			ofNullable(getQuantityReference()).ifPresent(builder::setQuantityReference);
			ofNullable(getQuantityMultiplier()).ifPresent(builder::setQuantityMultiplier);
			ofNullable(getReset()).ifPresent(builder::setReset);
			ofNullable(getFutureValueNotional()).ifPresent(builder::setFutureValueNotional);
			ofNullable(getPriceSchedule()).ifPresent(builder::setPriceSchedule);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResolvablePriceQuantity _that = getType().cast(o);
		
			if (!Objects.equals(resolvedQuantity, _that.getResolvedQuantity())) return false;
			if (!Objects.equals(quantitySchedule, _that.getQuantitySchedule())) return false;
			if (!Objects.equals(quantityReference, _that.getQuantityReference())) return false;
			if (!Objects.equals(quantityMultiplier, _that.getQuantityMultiplier())) return false;
			if (!Objects.equals(reset, _that.getReset())) return false;
			if (!Objects.equals(futureValueNotional, _that.getFutureValueNotional())) return false;
			if (!ListEquals.listEquals(priceSchedule, _that.getPriceSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resolvedQuantity != null ? resolvedQuantity.hashCode() : 0);
			_result = 31 * _result + (quantitySchedule != null ? quantitySchedule.hashCode() : 0);
			_result = 31 * _result + (quantityReference != null ? quantityReference.hashCode() : 0);
			_result = 31 * _result + (quantityMultiplier != null ? quantityMultiplier.hashCode() : 0);
			_result = 31 * _result + (reset != null ? reset.hashCode() : 0);
			_result = 31 * _result + (futureValueNotional != null ? futureValueNotional.hashCode() : 0);
			_result = 31 * _result + (priceSchedule != null ? priceSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResolvablePriceQuantity {" +
				"resolvedQuantity=" + this.resolvedQuantity + ", " +
				"quantitySchedule=" + this.quantitySchedule + ", " +
				"quantityReference=" + this.quantityReference + ", " +
				"quantityMultiplier=" + this.quantityMultiplier + ", " +
				"reset=" + this.reset + ", " +
				"futureValueNotional=" + this.futureValueNotional + ", " +
				"priceSchedule=" + this.priceSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ResolvablePriceQuantity  ***********************/
	class ResolvablePriceQuantityBuilderImpl implements ResolvablePriceQuantity.ResolvablePriceQuantityBuilder {
	
		protected Quantity.QuantityBuilder resolvedQuantity;
		protected ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder quantitySchedule;
		protected ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder quantityReference;
		protected QuantityMultiplier.QuantityMultiplierBuilder quantityMultiplier;
		protected Boolean reset;
		protected FutureValueAmount.FutureValueAmountBuilder futureValueNotional;
		protected List<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> priceSchedule = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
		
		@Override
		@RosettaAttribute("resolvedQuantity")
		@RuneAttribute("resolvedQuantity")
		public Quantity.QuantityBuilder getResolvedQuantity() {
			return resolvedQuantity;
		}
		
		@Override
		public Quantity.QuantityBuilder getOrCreateResolvedQuantity() {
			Quantity.QuantityBuilder result;
			if (resolvedQuantity!=null) {
				result = resolvedQuantity;
			}
			else {
				result = resolvedQuantity = Quantity.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("quantitySchedule")
		@RuneAttribute("quantitySchedule")
		public ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getQuantitySchedule() {
			return quantitySchedule;
		}
		
		@Override
		public ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantitySchedule() {
			ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder result;
			if (quantitySchedule!=null) {
				result = quantitySchedule;
			}
			else {
				result = quantitySchedule = ReferenceWithMetaNonNegativeQuantitySchedule.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("quantityReference")
		@RuneAttribute("quantityReference")
		public ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder getQuantityReference() {
			return quantityReference;
		}
		
		@Override
		public ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder getOrCreateQuantityReference() {
			ReferenceWithMetaResolvablePriceQuantity.ReferenceWithMetaResolvablePriceQuantityBuilder result;
			if (quantityReference!=null) {
				result = quantityReference;
			}
			else {
				result = quantityReference = ReferenceWithMetaResolvablePriceQuantity.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("quantityMultiplier")
		@RuneAttribute("quantityMultiplier")
		public QuantityMultiplier.QuantityMultiplierBuilder getQuantityMultiplier() {
			return quantityMultiplier;
		}
		
		@Override
		public QuantityMultiplier.QuantityMultiplierBuilder getOrCreateQuantityMultiplier() {
			QuantityMultiplier.QuantityMultiplierBuilder result;
			if (quantityMultiplier!=null) {
				result = quantityMultiplier;
			}
			else {
				result = quantityMultiplier = QuantityMultiplier.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("reset")
		@RuneAttribute("reset")
		public Boolean getReset() {
			return reset;
		}
		
		@Override
		@RosettaAttribute("futureValueNotional")
		@RuneAttribute("futureValueNotional")
		public FutureValueAmount.FutureValueAmountBuilder getFutureValueNotional() {
			return futureValueNotional;
		}
		
		@Override
		public FutureValueAmount.FutureValueAmountBuilder getOrCreateFutureValueNotional() {
			FutureValueAmount.FutureValueAmountBuilder result;
			if (futureValueNotional!=null) {
				result = futureValueNotional;
			}
			else {
				result = futureValueNotional = FutureValueAmount.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("priceSchedule")
		@RuneAttribute("priceSchedule")
		public List<? extends ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder> getPriceSchedule() {
			return priceSchedule;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreatePriceSchedule(int _index) {
		
			if (priceSchedule==null) {
				this.priceSchedule = new ArrayList<>();
			}
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			return getIndex(priceSchedule, _index, () -> {
						ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder newPriceSchedule = ReferenceWithMetaPriceSchedule.builder();
						return newPriceSchedule;
					});
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
		@RosettaAttribute("resolvedQuantity")
		@RuneAttribute("resolvedQuantity")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setResolvedQuantity(Quantity _resolvedQuantity) {
			this.resolvedQuantity = _resolvedQuantity == null ? null : _resolvedQuantity.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("quantitySchedule")
		@RuneAttribute("quantitySchedule")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantitySchedule(ReferenceWithMetaNonNegativeQuantitySchedule _quantitySchedule) {
			this.quantitySchedule = _quantitySchedule == null ? null : _quantitySchedule.toBuilder();
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityScheduleValue(NonNegativeQuantitySchedule _quantitySchedule) {
			this.getOrCreateQuantitySchedule().setValue(_quantitySchedule);
			return this;
		}
		
		@Override
		@RosettaAttribute("quantityReference")
		@RuneAttribute("quantityReference")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityReference(ReferenceWithMetaResolvablePriceQuantity _quantityReference) {
			this.quantityReference = _quantityReference == null ? null : _quantityReference.toBuilder();
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityReferenceValue(ResolvablePriceQuantity _quantityReference) {
			this.getOrCreateQuantityReference().setValue(_quantityReference);
			return this;
		}
		
		@Override
		@RosettaAttribute("quantityMultiplier")
		@RuneAttribute("quantityMultiplier")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setQuantityMultiplier(QuantityMultiplier _quantityMultiplier) {
			this.quantityMultiplier = _quantityMultiplier == null ? null : _quantityMultiplier.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("reset")
		@RuneAttribute("reset")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setReset(Boolean _reset) {
			this.reset = _reset == null ? null : _reset;
			return this;
		}
		
		@Override
		@RosettaAttribute("futureValueNotional")
		@RuneAttribute("futureValueNotional")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setFutureValueNotional(FutureValueAmount _futureValueNotional) {
			this.futureValueNotional = _futureValueNotional == null ? null : _futureValueNotional.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("priceSchedule")
		@RuneAttribute("priceSchedule")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(ReferenceWithMetaPriceSchedule _priceSchedule) {
			if (_priceSchedule != null) {
				this.priceSchedule.add(_priceSchedule.toBuilder());
			}
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(ReferenceWithMetaPriceSchedule _priceSchedule, int _idx) {
			getIndex(this.priceSchedule, _idx, () -> _priceSchedule.toBuilder());
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(PriceSchedule _priceSchedule) {
			this.getOrCreatePriceSchedule(-1).setValue(_priceSchedule.toBuilder());
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(PriceSchedule _priceSchedule, int _idx) {
			this.getOrCreatePriceSchedule(_idx).setValue(_priceSchedule.toBuilder());
			return this;
		}
		
		@Override 
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceSchedule(List<? extends ReferenceWithMetaPriceSchedule> priceSchedules) {
			if (priceSchedules != null) {
				for (final ReferenceWithMetaPriceSchedule toAdd : priceSchedules) {
					this.priceSchedule.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("priceSchedule")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setPriceSchedule(List<? extends ReferenceWithMetaPriceSchedule> priceSchedules) {
			if (priceSchedules == null) {
				this.priceSchedule = new ArrayList<>();
			} else {
				this.priceSchedule = priceSchedules.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder addPriceScheduleValue(List<? extends PriceSchedule> priceSchedules) {
			if (priceSchedules != null) {
				for (final PriceSchedule toAdd : priceSchedules) {
					this.addPriceScheduleValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setPriceScheduleValue(List<? extends PriceSchedule> priceSchedules) {
			this.priceSchedule.clear();
			if (priceSchedules != null) {
				priceSchedules.forEach(this::addPriceScheduleValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		public ResolvablePriceQuantity build() {
			return new ResolvablePriceQuantity.ResolvablePriceQuantityImpl(this);
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder prune() {
			if (resolvedQuantity!=null && !resolvedQuantity.prune().hasData()) resolvedQuantity = null;
			if (quantitySchedule!=null && !quantitySchedule.prune().hasData()) quantitySchedule = null;
			if (quantityReference!=null && !quantityReference.prune().hasData()) quantityReference = null;
			if (quantityMultiplier!=null && !quantityMultiplier.prune().hasData()) quantityMultiplier = null;
			if (futureValueNotional!=null && !futureValueNotional.prune().hasData()) futureValueNotional = null;
			priceSchedule = priceSchedule.stream().filter(b->b!=null).<ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getResolvedQuantity()!=null && getResolvedQuantity().hasData()) return true;
			if (getQuantitySchedule()!=null && getQuantitySchedule().hasData()) return true;
			if (getQuantityReference()!=null && getQuantityReference().hasData()) return true;
			if (getQuantityMultiplier()!=null && getQuantityMultiplier().hasData()) return true;
			if (getReset()!=null) return true;
			if (getFutureValueNotional()!=null && getFutureValueNotional().hasData()) return true;
			if (getPriceSchedule()!=null && getPriceSchedule().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ResolvablePriceQuantity.ResolvablePriceQuantityBuilder o = (ResolvablePriceQuantity.ResolvablePriceQuantityBuilder) other;
			
			merger.mergeRosetta(getResolvedQuantity(), o.getResolvedQuantity(), this::setResolvedQuantity);
			merger.mergeRosetta(getQuantitySchedule(), o.getQuantitySchedule(), this::setQuantitySchedule);
			merger.mergeRosetta(getQuantityReference(), o.getQuantityReference(), this::setQuantityReference);
			merger.mergeRosetta(getQuantityMultiplier(), o.getQuantityMultiplier(), this::setQuantityMultiplier);
			merger.mergeRosetta(getFutureValueNotional(), o.getFutureValueNotional(), this::setFutureValueNotional);
			merger.mergeRosetta(getPriceSchedule(), o.getPriceSchedule(), this::getOrCreatePriceSchedule);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getReset(), o.getReset(), this::setReset);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResolvablePriceQuantity _that = getType().cast(o);
		
			if (!Objects.equals(resolvedQuantity, _that.getResolvedQuantity())) return false;
			if (!Objects.equals(quantitySchedule, _that.getQuantitySchedule())) return false;
			if (!Objects.equals(quantityReference, _that.getQuantityReference())) return false;
			if (!Objects.equals(quantityMultiplier, _that.getQuantityMultiplier())) return false;
			if (!Objects.equals(reset, _that.getReset())) return false;
			if (!Objects.equals(futureValueNotional, _that.getFutureValueNotional())) return false;
			if (!ListEquals.listEquals(priceSchedule, _that.getPriceSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (resolvedQuantity != null ? resolvedQuantity.hashCode() : 0);
			_result = 31 * _result + (quantitySchedule != null ? quantitySchedule.hashCode() : 0);
			_result = 31 * _result + (quantityReference != null ? quantityReference.hashCode() : 0);
			_result = 31 * _result + (quantityMultiplier != null ? quantityMultiplier.hashCode() : 0);
			_result = 31 * _result + (reset != null ? reset.hashCode() : 0);
			_result = 31 * _result + (futureValueNotional != null ? futureValueNotional.hashCode() : 0);
			_result = 31 * _result + (priceSchedule != null ? priceSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResolvablePriceQuantityBuilder {" +
				"resolvedQuantity=" + this.resolvedQuantity + ", " +
				"quantitySchedule=" + this.quantitySchedule + ", " +
				"quantityReference=" + this.quantityReference + ", " +
				"quantityMultiplier=" + this.quantityMultiplier + ", " +
				"reset=" + this.reset + ", " +
				"futureValueNotional=" + this.futureValueNotional + ", " +
				"priceSchedule=" + this.priceSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
