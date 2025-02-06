package cdm.enrichment.common;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.identifier.Identifier.IdentifierBuilder;
import cdm.enrichment.common.InputWrapper;
import cdm.enrichment.common.InputWrapper.InputWrapperBuilder;
import cdm.enrichment.common.InputWrapper.InputWrapperBuilderImpl;
import cdm.enrichment.common.InputWrapper.InputWrapperImpl;
import cdm.enrichment.common.meta.InputWrapperMeta;
import cdm.event.common.CorporateAction;
import cdm.event.common.CorporateAction.CorporateActionBuilder;
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
 * @version ${project.version}
 */
@RosettaDataType(value="InputWrapper", builder=InputWrapper.InputWrapperBuilderImpl.class, version="${project.version}")
@RuneDataType(value="InputWrapper", model="Common Domain Model", builder=InputWrapper.InputWrapperBuilderImpl.class, version="${project.version}")
public interface InputWrapper extends RosettaModelObject {

	InputWrapperMeta metaData = new InputWrapperMeta();

	/*********************** Getter Methods  ***********************/
	List<? extends Identifier> getEventIdentifier();
	CorporateAction getCorporateAction();

	/*********************** Build Methods  ***********************/
	InputWrapper build();
	
	InputWrapper.InputWrapperBuilder toBuilder();
	
	static InputWrapper.InputWrapperBuilder builder() {
		return new InputWrapper.InputWrapperBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends InputWrapper> metaData() {
		return metaData;
	}
	
	@Override
	@RuneAttribute("@type")
	default Class<? extends InputWrapper> getType() {
		return InputWrapper.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("eventIdentifier"), processor, Identifier.class, getEventIdentifier());
		processRosetta(path.newSubPath("corporateAction"), processor, CorporateAction.class, getCorporateAction());
	}
	

	/*********************** Builder Interface  ***********************/
	interface InputWrapperBuilder extends InputWrapper, RosettaModelObjectBuilder {
		Identifier.IdentifierBuilder getOrCreateEventIdentifier(int _index);
		@Override
		List<? extends Identifier.IdentifierBuilder> getEventIdentifier();
		CorporateAction.CorporateActionBuilder getOrCreateCorporateAction();
		@Override
		CorporateAction.CorporateActionBuilder getCorporateAction();
		InputWrapper.InputWrapperBuilder addEventIdentifier(Identifier eventIdentifier);
		InputWrapper.InputWrapperBuilder addEventIdentifier(Identifier eventIdentifier, int _idx);
		InputWrapper.InputWrapperBuilder addEventIdentifier(List<? extends Identifier> eventIdentifier);
		InputWrapper.InputWrapperBuilder setEventIdentifier(List<? extends Identifier> eventIdentifier);
		InputWrapper.InputWrapperBuilder setCorporateAction(CorporateAction corporateAction);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("eventIdentifier"), processor, Identifier.IdentifierBuilder.class, getEventIdentifier());
			processRosetta(path.newSubPath("corporateAction"), processor, CorporateAction.CorporateActionBuilder.class, getCorporateAction());
		}
		

		InputWrapper.InputWrapperBuilder prune();
	}

	/*********************** Immutable Implementation of InputWrapper  ***********************/
	class InputWrapperImpl implements InputWrapper {
		private final List<? extends Identifier> eventIdentifier;
		private final CorporateAction corporateAction;
		
		protected InputWrapperImpl(InputWrapper.InputWrapperBuilder builder) {
			this.eventIdentifier = ofNullable(builder.getEventIdentifier()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.corporateAction = ofNullable(builder.getCorporateAction()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("eventIdentifier")
		@RuneAttribute("eventIdentifier")
		public List<? extends Identifier> getEventIdentifier() {
			return eventIdentifier;
		}
		
		@Override
		@RosettaAttribute("corporateAction")
		@RuneAttribute("corporateAction")
		public CorporateAction getCorporateAction() {
			return corporateAction;
		}
		
		@Override
		public InputWrapper build() {
			return this;
		}
		
		@Override
		public InputWrapper.InputWrapperBuilder toBuilder() {
			InputWrapper.InputWrapperBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(InputWrapper.InputWrapperBuilder builder) {
			ofNullable(getEventIdentifier()).ifPresent(builder::setEventIdentifier);
			ofNullable(getCorporateAction()).ifPresent(builder::setCorporateAction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InputWrapper _that = getType().cast(o);
		
			if (!ListEquals.listEquals(eventIdentifier, _that.getEventIdentifier())) return false;
			if (!Objects.equals(corporateAction, _that.getCorporateAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (eventIdentifier != null ? eventIdentifier.hashCode() : 0);
			_result = 31 * _result + (corporateAction != null ? corporateAction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InputWrapper {" +
				"eventIdentifier=" + this.eventIdentifier + ", " +
				"corporateAction=" + this.corporateAction +
			'}';
		}
	}

	/*********************** Builder Implementation of InputWrapper  ***********************/
	class InputWrapperBuilderImpl implements InputWrapper.InputWrapperBuilder {
	
		protected List<Identifier.IdentifierBuilder> eventIdentifier = new ArrayList<>();
		protected CorporateAction.CorporateActionBuilder corporateAction;
		
		@Override
		@RosettaAttribute("eventIdentifier")
		@RuneAttribute("eventIdentifier")
		public List<? extends Identifier.IdentifierBuilder> getEventIdentifier() {
			return eventIdentifier;
		}
		
		@Override
		public Identifier.IdentifierBuilder getOrCreateEventIdentifier(int _index) {
		
			if (eventIdentifier==null) {
				this.eventIdentifier = new ArrayList<>();
			}
			Identifier.IdentifierBuilder result;
			return getIndex(eventIdentifier, _index, () -> {
						Identifier.IdentifierBuilder newEventIdentifier = Identifier.builder();
						return newEventIdentifier;
					});
		}
		
		@Override
		@RosettaAttribute("corporateAction")
		@RuneAttribute("corporateAction")
		public CorporateAction.CorporateActionBuilder getCorporateAction() {
			return corporateAction;
		}
		
		@Override
		public CorporateAction.CorporateActionBuilder getOrCreateCorporateAction() {
			CorporateAction.CorporateActionBuilder result;
			if (corporateAction!=null) {
				result = corporateAction;
			}
			else {
				result = corporateAction = CorporateAction.builder();
			}
			
			return result;
		}
		
		@Override
		@RosettaAttribute("eventIdentifier")
		@RuneAttribute("eventIdentifier")
		public InputWrapper.InputWrapperBuilder addEventIdentifier(Identifier _eventIdentifier) {
			if (_eventIdentifier != null) {
				this.eventIdentifier.add(_eventIdentifier.toBuilder());
			}
			return this;
		}
		
		@Override
		public InputWrapper.InputWrapperBuilder addEventIdentifier(Identifier _eventIdentifier, int _idx) {
			getIndex(this.eventIdentifier, _idx, () -> _eventIdentifier.toBuilder());
			return this;
		}
		
		@Override 
		public InputWrapper.InputWrapperBuilder addEventIdentifier(List<? extends Identifier> eventIdentifiers) {
			if (eventIdentifiers != null) {
				for (final Identifier toAdd : eventIdentifiers) {
					this.eventIdentifier.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RuneAttribute("eventIdentifier")
		public InputWrapper.InputWrapperBuilder setEventIdentifier(List<? extends Identifier> eventIdentifiers) {
			if (eventIdentifiers == null) {
				this.eventIdentifier = new ArrayList<>();
			} else {
				this.eventIdentifier = eventIdentifiers.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("corporateAction")
		@RuneAttribute("corporateAction")
		public InputWrapper.InputWrapperBuilder setCorporateAction(CorporateAction _corporateAction) {
			this.corporateAction = _corporateAction == null ? null : _corporateAction.toBuilder();
			return this;
		}
		
		@Override
		public InputWrapper build() {
			return new InputWrapper.InputWrapperImpl(this);
		}
		
		@Override
		public InputWrapper.InputWrapperBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InputWrapper.InputWrapperBuilder prune() {
			eventIdentifier = eventIdentifier.stream().filter(b->b!=null).<Identifier.IdentifierBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (corporateAction!=null && !corporateAction.prune().hasData()) corporateAction = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEventIdentifier()!=null && getEventIdentifier().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getCorporateAction()!=null && getCorporateAction().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public InputWrapper.InputWrapperBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			InputWrapper.InputWrapperBuilder o = (InputWrapper.InputWrapperBuilder) other;
			
			merger.mergeRosetta(getEventIdentifier(), o.getEventIdentifier(), this::getOrCreateEventIdentifier);
			merger.mergeRosetta(getCorporateAction(), o.getCorporateAction(), this::setCorporateAction);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			InputWrapper _that = getType().cast(o);
		
			if (!ListEquals.listEquals(eventIdentifier, _that.getEventIdentifier())) return false;
			if (!Objects.equals(corporateAction, _that.getCorporateAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (eventIdentifier != null ? eventIdentifier.hashCode() : 0);
			_result = 31 * _result + (corporateAction != null ? corporateAction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "InputWrapperBuilder {" +
				"eventIdentifier=" + this.eventIdentifier + ", " +
				"corporateAction=" + this.corporateAction +
			'}';
		}
	}
}
