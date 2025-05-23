package cdm.base.datetime;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessCenters.BusinessCentersBuilder;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.DateRange;
import cdm.base.datetime.DateRange.DateRangeBuilder;
import cdm.base.datetime.DayTypeEnum;
import cdm.base.datetime.PeriodEnum;
import cdm.base.datetime.RelativeDateOffset;
import cdm.base.datetime.RelativeDateOffset.RelativeDateOffsetBuilder;
import cdm.base.datetime.RelativeDateOffset.RelativeDateOffsetBuilderImpl;
import cdm.base.datetime.RelativeDateOffset.RelativeDateOffsetImpl;
import cdm.base.datetime.RelativeDates;
import cdm.base.datetime.RelativeDates.RelativeDatesBuilder;
import cdm.base.datetime.RelativeDates.RelativeDatesBuilderImpl;
import cdm.base.datetime.RelativeDates.RelativeDatesImpl;
import cdm.base.datetime.meta.RelativeDatesMeta;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder;
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
import com.rosetta.model.metafields.ReferenceWithMetaDate;
import com.rosetta.model.metafields.ReferenceWithMetaDate.ReferenceWithMetaDateBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class describing a set of dates defined as relative to another set of dates.
 * @version ${project.version}
 */
@RosettaDataType(value="RelativeDates", builder=RelativeDates.RelativeDatesBuilderImpl.class, version="${project.version}")
@RuneDataType(value="RelativeDates", model="Common Domain Model", builder=RelativeDates.RelativeDatesBuilderImpl.class, version="${project.version}")
public interface RelativeDates extends RelativeDateOffset {

	RelativeDatesMeta metaData = new RelativeDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number of periods in the referenced date schedule that are between each date in the relative date schedule. Thus a skip of 2 would mean that dates are relative to every second date in the referenced schedule. If present this should have a value greater than 1.
	 */
	Integer getPeriodSkip();
	/**
	 * The first and last dates of a schedule. This can be used to restrict the range of values in a reference series of dates.
	 */
	DateRange getScheduleBounds();

	/*********************** Build Methods  ***********************/
	RelativeDates build();
	
	RelativeDates.RelativeDatesBuilder toBuilder();
	
	static RelativeDates.RelativeDatesBuilder builder() {
		return new RelativeDates.RelativeDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RelativeDates> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends RelativeDates> getType() {
		return RelativeDates.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
		processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
		processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
		processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.class, getBusinessCenters());
		processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.class, getBusinessCentersReference());
		processRosetta(path.newSubPath("dateRelativeTo"), processor, ReferenceWithMetaDate.class, getDateRelativeTo());
		processor.processBasic(path.newSubPath("adjustedDate"), Date.class, getAdjustedDate(), this);
		processor.processBasic(path.newSubPath("periodSkip"), Integer.class, getPeriodSkip(), this);
		processRosetta(path.newSubPath("scheduleBounds"), processor, DateRange.class, getScheduleBounds());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RelativeDatesBuilder extends RelativeDates, RelativeDateOffset.RelativeDateOffsetBuilder {
		DateRange.DateRangeBuilder getOrCreateScheduleBounds();
		@Override
		DateRange.DateRangeBuilder getScheduleBounds();
		@Override
		RelativeDates.RelativeDatesBuilder setPeriodMultiplier(Integer periodMultiplier);
		@Override
		RelativeDates.RelativeDatesBuilder setPeriod(PeriodEnum period);
		@Override
		RelativeDates.RelativeDatesBuilder setMeta(MetaFields meta);
		@Override
		RelativeDates.RelativeDatesBuilder setDayType(DayTypeEnum dayType);
		@Override
		RelativeDates.RelativeDatesBuilder setBusinessDayConvention(BusinessDayConventionEnum businessDayConvention);
		@Override
		RelativeDates.RelativeDatesBuilder setBusinessCenters(BusinessCenters businessCenters);
		@Override
		RelativeDates.RelativeDatesBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters businessCentersReference);
		@Override
		RelativeDates.RelativeDatesBuilder setBusinessCentersReferenceValue(BusinessCenters businessCentersReference);
		@Override
		RelativeDates.RelativeDatesBuilder setDateRelativeTo(ReferenceWithMetaDate dateRelativeTo);
		@Override
		RelativeDates.RelativeDatesBuilder setDateRelativeToValue(Date dateRelativeTo);
		@Override
		RelativeDates.RelativeDatesBuilder setAdjustedDate(Date adjustedDate);
		RelativeDates.RelativeDatesBuilder setPeriodSkip(Integer periodSkip);
		RelativeDates.RelativeDatesBuilder setScheduleBounds(DateRange scheduleBounds);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("periodMultiplier"), Integer.class, getPeriodMultiplier(), this);
			processor.processBasic(path.newSubPath("period"), PeriodEnum.class, getPeriod(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("dayType"), DayTypeEnum.class, getDayType(), this);
			processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
			processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.BusinessCentersBuilder.class, getBusinessCenters());
			processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder.class, getBusinessCentersReference());
			processRosetta(path.newSubPath("dateRelativeTo"), processor, ReferenceWithMetaDate.ReferenceWithMetaDateBuilder.class, getDateRelativeTo());
			processor.processBasic(path.newSubPath("adjustedDate"), Date.class, getAdjustedDate(), this);
			processor.processBasic(path.newSubPath("periodSkip"), Integer.class, getPeriodSkip(), this);
			processRosetta(path.newSubPath("scheduleBounds"), processor, DateRange.DateRangeBuilder.class, getScheduleBounds());
		}
		

		RelativeDates.RelativeDatesBuilder prune();
	}

	/*********************** Immutable Implementation of RelativeDates  ***********************/
	class RelativeDatesImpl extends RelativeDateOffset.RelativeDateOffsetImpl implements RelativeDates {
		private final Integer periodSkip;
		private final DateRange scheduleBounds;
		
		protected RelativeDatesImpl(RelativeDates.RelativeDatesBuilder builder) {
			super(builder);
			this.periodSkip = builder.getPeriodSkip();
			this.scheduleBounds = ofNullable(builder.getScheduleBounds()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("periodSkip")
		@RuneAttribute("periodSkip")
		public Integer getPeriodSkip() {
			return periodSkip;
		}
		
		@Override
		@RosettaAttribute("scheduleBounds")
		@RuneAttribute("scheduleBounds")
		public DateRange getScheduleBounds() {
			return scheduleBounds;
		}
		
		@Override
		public RelativeDates build() {
			return this;
		}
		
		@Override
		public RelativeDates.RelativeDatesBuilder toBuilder() {
			RelativeDates.RelativeDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RelativeDates.RelativeDatesBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getPeriodSkip()).ifPresent(builder::setPeriodSkip);
			ofNullable(getScheduleBounds()).ifPresent(builder::setScheduleBounds);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			RelativeDates _that = getType().cast(o);
		
			if (!Objects.equals(periodSkip, _that.getPeriodSkip())) return false;
			if (!Objects.equals(scheduleBounds, _that.getScheduleBounds())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (periodSkip != null ? periodSkip.hashCode() : 0);
			_result = 31 * _result + (scheduleBounds != null ? scheduleBounds.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelativeDates {" +
				"periodSkip=" + this.periodSkip + ", " +
				"scheduleBounds=" + this.scheduleBounds +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of RelativeDates  ***********************/
	class RelativeDatesBuilderImpl extends RelativeDateOffset.RelativeDateOffsetBuilderImpl implements RelativeDates.RelativeDatesBuilder {
	
		protected Integer periodSkip;
		protected DateRange.DateRangeBuilder scheduleBounds;
		
		@Override
		@RosettaAttribute("periodSkip")
		@RuneAttribute("periodSkip")
		public Integer getPeriodSkip() {
			return periodSkip;
		}
		
		@Override
		@RosettaAttribute("scheduleBounds")
		@RuneAttribute("scheduleBounds")
		public DateRange.DateRangeBuilder getScheduleBounds() {
			return scheduleBounds;
		}
		
		@Override
		public DateRange.DateRangeBuilder getOrCreateScheduleBounds() {
			DateRange.DateRangeBuilder result;
			if (scheduleBounds!=null) {
				result = scheduleBounds;
			}
			else {
				result = scheduleBounds = DateRange.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("periodMultiplier")
		@RuneAttribute("periodMultiplier")
		public RelativeDates.RelativeDatesBuilder setPeriodMultiplier(Integer _periodMultiplier) {
			this.periodMultiplier = _periodMultiplier == null ? null : _periodMultiplier;
			return this;
		}
		
		@Override
		@RosettaAttribute("period")
		@RuneAttribute("period")
		public RelativeDates.RelativeDatesBuilder setPeriod(PeriodEnum _period) {
			this.period = _period == null ? null : _period;
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		@RuneAttribute("meta")
		@RuneMetaType
		public RelativeDates.RelativeDatesBuilder setMeta(MetaFields _meta) {
			this.meta = _meta == null ? null : _meta.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("dayType")
		@RuneAttribute("dayType")
		public RelativeDates.RelativeDatesBuilder setDayType(DayTypeEnum _dayType) {
			this.dayType = _dayType == null ? null : _dayType;
			return this;
		}
		
		@Override
		@RosettaAttribute("businessDayConvention")
		@RuneAttribute("businessDayConvention")
		public RelativeDates.RelativeDatesBuilder setBusinessDayConvention(BusinessDayConventionEnum _businessDayConvention) {
			this.businessDayConvention = _businessDayConvention == null ? null : _businessDayConvention;
			return this;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		@RuneAttribute("businessCenters")
		public RelativeDates.RelativeDatesBuilder setBusinessCenters(BusinessCenters _businessCenters) {
			this.businessCenters = _businessCenters == null ? null : _businessCenters.toBuilder();
			return this;
		}
		
		@Override
		@RosettaAttribute("businessCentersReference")
		@RuneAttribute("businessCentersReference")
		public RelativeDates.RelativeDatesBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters _businessCentersReference) {
			this.businessCentersReference = _businessCentersReference == null ? null : _businessCentersReference.toBuilder();
			return this;
		}
		
		@Override
		public RelativeDates.RelativeDatesBuilder setBusinessCentersReferenceValue(BusinessCenters _businessCentersReference) {
			this.getOrCreateBusinessCentersReference().setValue(_businessCentersReference);
			return this;
		}
		
		@Override
		@RosettaAttribute("dateRelativeTo")
		@RuneAttribute("dateRelativeTo")
		public RelativeDates.RelativeDatesBuilder setDateRelativeTo(ReferenceWithMetaDate _dateRelativeTo) {
			this.dateRelativeTo = _dateRelativeTo == null ? null : _dateRelativeTo.toBuilder();
			return this;
		}
		
		@Override
		public RelativeDates.RelativeDatesBuilder setDateRelativeToValue(Date _dateRelativeTo) {
			this.getOrCreateDateRelativeTo().setValue(_dateRelativeTo);
			return this;
		}
		
		@Override
		@RosettaAttribute("adjustedDate")
		@RuneAttribute("adjustedDate")
		public RelativeDates.RelativeDatesBuilder setAdjustedDate(Date _adjustedDate) {
			this.adjustedDate = _adjustedDate == null ? null : _adjustedDate;
			return this;
		}
		
		@Override
		@RosettaAttribute("periodSkip")
		@RuneAttribute("periodSkip")
		public RelativeDates.RelativeDatesBuilder setPeriodSkip(Integer _periodSkip) {
			this.periodSkip = _periodSkip == null ? null : _periodSkip;
			return this;
		}
		
		@Override
		@RosettaAttribute("scheduleBounds")
		@RuneAttribute("scheduleBounds")
		public RelativeDates.RelativeDatesBuilder setScheduleBounds(DateRange _scheduleBounds) {
			this.scheduleBounds = _scheduleBounds == null ? null : _scheduleBounds.toBuilder();
			return this;
		}
		
		@Override
		public RelativeDates build() {
			return new RelativeDates.RelativeDatesImpl(this);
		}
		
		@Override
		public RelativeDates.RelativeDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelativeDates.RelativeDatesBuilder prune() {
			super.prune();
			if (scheduleBounds!=null && !scheduleBounds.prune().hasData()) scheduleBounds = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getPeriodSkip()!=null) return true;
			if (getScheduleBounds()!=null && getScheduleBounds().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RelativeDates.RelativeDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			RelativeDates.RelativeDatesBuilder o = (RelativeDates.RelativeDatesBuilder) other;
			
			merger.mergeRosetta(getScheduleBounds(), o.getScheduleBounds(), this::setScheduleBounds);
			
			merger.mergeBasic(getPeriodSkip(), o.getPeriodSkip(), this::setPeriodSkip);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			RelativeDates _that = getType().cast(o);
		
			if (!Objects.equals(periodSkip, _that.getPeriodSkip())) return false;
			if (!Objects.equals(scheduleBounds, _that.getScheduleBounds())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (periodSkip != null ? periodSkip.hashCode() : 0);
			_result = 31 * _result + (scheduleBounds != null ? scheduleBounds.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RelativeDatesBuilder {" +
				"periodSkip=" + this.periodSkip + ", " +
				"scheduleBounds=" + this.scheduleBounds +
			'}' + " " + super.toString();
		}
	}
}
