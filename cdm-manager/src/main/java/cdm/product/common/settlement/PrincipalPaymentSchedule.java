package cdm.product.common.settlement;

import cdm.base.datetime.AdjustableRelativeOrPeriodicDates;
import cdm.base.datetime.AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder;
import cdm.product.common.settlement.PrincipalPayment;
import cdm.product.common.settlement.PrincipalPayment.PrincipalPaymentBuilder;
import cdm.product.common.settlement.PrincipalPaymentSchedule;
import cdm.product.common.settlement.PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder;
import cdm.product.common.settlement.PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilderImpl;
import cdm.product.common.settlement.PrincipalPaymentSchedule.PrincipalPaymentScheduleImpl;
import cdm.product.common.settlement.meta.PrincipalPaymentScheduleMeta;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Describe dates schedules for Principal Exchanges and related role of the parties when known.
 * @version ${project.version}
 */
@RosettaDataType(value="PrincipalPaymentSchedule", builder=PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilderImpl.class, version="${project.version}")
@RuneDataType(value="PrincipalPaymentSchedule", model="Common Domain Model", builder=PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilderImpl.class, version="${project.version}")
public interface PrincipalPaymentSchedule extends RosettaModelObject {

	PrincipalPaymentScheduleMeta metaData = new PrincipalPaymentScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Principal Payment made at Trade inception.
	 */
	PrincipalPayment getInitialPrincipalPayment();
	/**
	 * Principal Payment as part of the Trade lifecycle e.g. as part of notional reset adjustements in a Cross Currency Swap with a varying notional leg.
	 */
	AdjustableRelativeOrPeriodicDates getIntermediatePrincipalPayment();
	/**
	 * Principal Payment at Trade maturity
	 */
	PrincipalPayment getFinalPrincipalPayment();

	/*********************** Build Methods  ***********************/
	PrincipalPaymentSchedule build();
	
	PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder toBuilder();
	
	static PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder builder() {
		return new PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PrincipalPaymentSchedule> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends PrincipalPaymentSchedule> getType() {
		return PrincipalPaymentSchedule.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("initialPrincipalPayment"), processor, PrincipalPayment.class, getInitialPrincipalPayment());
		processRosetta(path.newSubPath("intermediatePrincipalPayment"), processor, AdjustableRelativeOrPeriodicDates.class, getIntermediatePrincipalPayment());
		processRosetta(path.newSubPath("finalPrincipalPayment"), processor, PrincipalPayment.class, getFinalPrincipalPayment());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PrincipalPaymentScheduleBuilder extends PrincipalPaymentSchedule, RosettaModelObjectBuilder {
		PrincipalPayment.PrincipalPaymentBuilder getOrCreateInitialPrincipalPayment();
		@Override
		PrincipalPayment.PrincipalPaymentBuilder getInitialPrincipalPayment();
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateIntermediatePrincipalPayment();
		@Override
		AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getIntermediatePrincipalPayment();
		PrincipalPayment.PrincipalPaymentBuilder getOrCreateFinalPrincipalPayment();
		@Override
		PrincipalPayment.PrincipalPaymentBuilder getFinalPrincipalPayment();
		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setInitialPrincipalPayment(PrincipalPayment initialPrincipalPayment);
		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setIntermediatePrincipalPayment(AdjustableRelativeOrPeriodicDates intermediatePrincipalPayment);
		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setFinalPrincipalPayment(PrincipalPayment finalPrincipalPayment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("initialPrincipalPayment"), processor, PrincipalPayment.PrincipalPaymentBuilder.class, getInitialPrincipalPayment());
			processRosetta(path.newSubPath("intermediatePrincipalPayment"), processor, AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder.class, getIntermediatePrincipalPayment());
			processRosetta(path.newSubPath("finalPrincipalPayment"), processor, PrincipalPayment.PrincipalPaymentBuilder.class, getFinalPrincipalPayment());
		}
		

		PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of PrincipalPaymentSchedule  ***********************/
	class PrincipalPaymentScheduleImpl implements PrincipalPaymentSchedule {
		private final PrincipalPayment initialPrincipalPayment;
		private final AdjustableRelativeOrPeriodicDates intermediatePrincipalPayment;
		private final PrincipalPayment finalPrincipalPayment;
		
		protected PrincipalPaymentScheduleImpl(PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder builder) {
			this.initialPrincipalPayment = ofNullable(builder.getInitialPrincipalPayment()).map(f->f.build()).orElse(null);
			this.intermediatePrincipalPayment = ofNullable(builder.getIntermediatePrincipalPayment()).map(f->f.build()).orElse(null);
			this.finalPrincipalPayment = ofNullable(builder.getFinalPrincipalPayment()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("initialPrincipalPayment")
		@RuneAttribute("initialPrincipalPayment")
		public PrincipalPayment getInitialPrincipalPayment() {
			return initialPrincipalPayment;
		}
		
		@Override
		@RosettaAttribute("intermediatePrincipalPayment")
		@RuneAttribute("intermediatePrincipalPayment")
		public AdjustableRelativeOrPeriodicDates getIntermediatePrincipalPayment() {
			return intermediatePrincipalPayment;
		}
		
		@Override
		@RosettaAttribute("finalPrincipalPayment")
		@RuneAttribute("finalPrincipalPayment")
		public PrincipalPayment getFinalPrincipalPayment() {
			return finalPrincipalPayment;
		}
		
		@Override
		public PrincipalPaymentSchedule build() {
			return this;
		}
		
		@Override
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder toBuilder() {
			PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder builder) {
			ofNullable(getInitialPrincipalPayment()).ifPresent(builder::setInitialPrincipalPayment);
			ofNullable(getIntermediatePrincipalPayment()).ifPresent(builder::setIntermediatePrincipalPayment);
			ofNullable(getFinalPrincipalPayment()).ifPresent(builder::setFinalPrincipalPayment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPaymentSchedule _that = getType().cast(o);
		
			if (!Objects.equals(initialPrincipalPayment, _that.getInitialPrincipalPayment())) return false;
			if (!Objects.equals(intermediatePrincipalPayment, _that.getIntermediatePrincipalPayment())) return false;
			if (!Objects.equals(finalPrincipalPayment, _that.getFinalPrincipalPayment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialPrincipalPayment != null ? initialPrincipalPayment.hashCode() : 0);
			_result = 31 * _result + (intermediatePrincipalPayment != null ? intermediatePrincipalPayment.hashCode() : 0);
			_result = 31 * _result + (finalPrincipalPayment != null ? finalPrincipalPayment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPaymentSchedule {" +
				"initialPrincipalPayment=" + this.initialPrincipalPayment + ", " +
				"intermediatePrincipalPayment=" + this.intermediatePrincipalPayment + ", " +
				"finalPrincipalPayment=" + this.finalPrincipalPayment +
			'}';
		}
	}

	/*********************** Builder Implementation of PrincipalPaymentSchedule  ***********************/
	class PrincipalPaymentScheduleBuilderImpl implements PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder {
	
		protected PrincipalPayment.PrincipalPaymentBuilder initialPrincipalPayment;
		protected AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder intermediatePrincipalPayment;
		protected PrincipalPayment.PrincipalPaymentBuilder finalPrincipalPayment;
		
		@Override
		@RosettaAttribute("initialPrincipalPayment")
		@RuneAttribute("initialPrincipalPayment")
		public PrincipalPayment.PrincipalPaymentBuilder getInitialPrincipalPayment() {
			return initialPrincipalPayment;
		}
		
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder getOrCreateInitialPrincipalPayment() {
			PrincipalPayment.PrincipalPaymentBuilder result;
			if (initialPrincipalPayment!=null) {
				result = initialPrincipalPayment;
			}
			else {
				result = initialPrincipalPayment = PrincipalPayment.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("intermediatePrincipalPayment")
		@RuneAttribute("intermediatePrincipalPayment")
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getIntermediatePrincipalPayment() {
			return intermediatePrincipalPayment;
		}
		
		@Override
		public AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder getOrCreateIntermediatePrincipalPayment() {
			AdjustableRelativeOrPeriodicDates.AdjustableRelativeOrPeriodicDatesBuilder result;
			if (intermediatePrincipalPayment!=null) {
				result = intermediatePrincipalPayment;
			}
			else {
				result = intermediatePrincipalPayment = AdjustableRelativeOrPeriodicDates.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("finalPrincipalPayment")
		@RuneAttribute("finalPrincipalPayment")
		public PrincipalPayment.PrincipalPaymentBuilder getFinalPrincipalPayment() {
			return finalPrincipalPayment;
		}
		
		@Override
		public PrincipalPayment.PrincipalPaymentBuilder getOrCreateFinalPrincipalPayment() {
			PrincipalPayment.PrincipalPaymentBuilder result;
			if (finalPrincipalPayment!=null) {
				result = finalPrincipalPayment;
			}
			else {
				result = finalPrincipalPayment = PrincipalPayment.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("initialPrincipalPayment")
		@RuneAttribute("initialPrincipalPayment")
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setInitialPrincipalPayment(PrincipalPayment _initialPrincipalPayment) {
			this.initialPrincipalPayment = _initialPrincipalPayment == null ? null : _initialPrincipalPayment.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("intermediatePrincipalPayment")
		@RuneAttribute("intermediatePrincipalPayment")
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setIntermediatePrincipalPayment(AdjustableRelativeOrPeriodicDates _intermediatePrincipalPayment) {
			this.intermediatePrincipalPayment = _intermediatePrincipalPayment == null ? null : _intermediatePrincipalPayment.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("finalPrincipalPayment")
		@RuneAttribute("finalPrincipalPayment")
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder setFinalPrincipalPayment(PrincipalPayment _finalPrincipalPayment) {
			this.finalPrincipalPayment = _finalPrincipalPayment == null ? null : _finalPrincipalPayment.toBuilder();
			return this;
		}
		
		@Override
		public PrincipalPaymentSchedule build() {
			return new PrincipalPaymentSchedule.PrincipalPaymentScheduleImpl(this);
		}
		
		@Override
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder prune() {
			if (initialPrincipalPayment!=null && !initialPrincipalPayment.prune().hasData()) initialPrincipalPayment = null;
			if (intermediatePrincipalPayment!=null && !intermediatePrincipalPayment.prune().hasData()) intermediatePrincipalPayment = null;
			if (finalPrincipalPayment!=null && !finalPrincipalPayment.prune().hasData()) finalPrincipalPayment = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getInitialPrincipalPayment()!=null && getInitialPrincipalPayment().hasData()) return true;
			if (getIntermediatePrincipalPayment()!=null && getIntermediatePrincipalPayment().hasData()) return true;
			if (getFinalPrincipalPayment()!=null && getFinalPrincipalPayment().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder o = (PrincipalPaymentSchedule.PrincipalPaymentScheduleBuilder) other;
			
			merger.mergeRosetta(getInitialPrincipalPayment(), o.getInitialPrincipalPayment(), this::setInitialPrincipalPayment);
			merger.mergeRosetta(getIntermediatePrincipalPayment(), o.getIntermediatePrincipalPayment(), this::setIntermediatePrincipalPayment);
			merger.mergeRosetta(getFinalPrincipalPayment(), o.getFinalPrincipalPayment(), this::setFinalPrincipalPayment);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PrincipalPaymentSchedule _that = getType().cast(o);
		
			if (!Objects.equals(initialPrincipalPayment, _that.getInitialPrincipalPayment())) return false;
			if (!Objects.equals(intermediatePrincipalPayment, _that.getIntermediatePrincipalPayment())) return false;
			if (!Objects.equals(finalPrincipalPayment, _that.getFinalPrincipalPayment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (initialPrincipalPayment != null ? initialPrincipalPayment.hashCode() : 0);
			_result = 31 * _result + (intermediatePrincipalPayment != null ? intermediatePrincipalPayment.hashCode() : 0);
			_result = 31 * _result + (finalPrincipalPayment != null ? finalPrincipalPayment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PrincipalPaymentScheduleBuilder {" +
				"initialPrincipalPayment=" + this.initialPrincipalPayment + ", " +
				"intermediatePrincipalPayment=" + this.intermediatePrincipalPayment + ", " +
				"finalPrincipalPayment=" + this.finalPrincipalPayment +
			'}';
		}
	}
}
