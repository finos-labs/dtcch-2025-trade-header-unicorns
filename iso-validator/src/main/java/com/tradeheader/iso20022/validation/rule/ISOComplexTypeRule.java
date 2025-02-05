package com.tradeheader.iso20022.validation.rule;

import com.handcoded.validation.Precondition;
import com.handcoded.validation.Rule;
import com.handcoded.validation.ValidationErrorHandler;
import com.handcoded.xml.MutableNodeList;
import com.handcoded.xml.NodeIndex;
import com.tradeheader.iso20022.validation.constrain.IConstrain;
import com.tradeheader.iso20022.validation.constrain.ISOConstrainCondition;
import com.tradeheader.iso20022.validation.constrain.ISOUnitaryConstrain;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.tradeheader.iso20022.ISORuleSetLogic.computeISOContextType;

/**
 * Created by Tradeheader, SL
 * <p>
 * This will be the entry point and will define the rule/rules to be checked. Based on an iterative context, on which restrictions are applied and one or more conditions are defined that we will evaluate.
 *
 * @author mmartos
 * @since 20/10/2020
 */
public class ISOComplexTypeRule extends Rule {

    private static final IConstrain ALWAYS = new ISOUnitaryConstrain(ISOUnitaryConstrain.ConstrainMode.ALWAYS);

    private final String[] complexType;
    private final String errorDescription;
    private final String businessDescription;
    private final String errorCode;
    private IConstrain[] constrains = Collections.singletonList(ALWAYS).toArray(new IConstrain[1]);
    private final ISOConstrainCondition[] conditions;


    private ISOComplexTypeRule(Builder builder) {
        super(builder.precondition, builder.name);
        this.complexType = builder.complexType;
        this.errorDescription = builder.errorDescription;
        this.businessDescription = builder.businessDescription;
        if (builder.constrains != null) this.constrains = builder.constrains.toArray(new IConstrain[builder.constrains.size()]);
        this.conditions = builder.conditions;
        if (!Objects.equals(builder.errorCode, "")) this.errorCode = builder.errorCode;
        else this.errorCode = "error_" + builder.name;
    }

    public static class Builder {
        private final String name;

        private final String errorCode;
        private final String errorDescription;
        private final String[] complexType;
        private final ISOConstrainCondition[] conditions;

        private Precondition precondition = Precondition.ALWAYS;
        private List<IConstrain> constrains = null;
        private String businessDescription = "";

        public Builder(String name, String errorCode, String errorDescription, String[] complexType, ISOConstrainCondition... conditions) {
            this.name = name;
            this.errorCode = errorCode;
            this.complexType = complexType;
            this.conditions = conditions;
            this.errorDescription = errorDescription;
        }

        public Builder precondition(Precondition precondition) {
            this.precondition = precondition;
            return this;
        }

        public Builder constrains(List<IConstrain> constrains) {
            this.constrains = constrains;
            return this;
        }

        public Builder constrains(IConstrain constrain) {
            this.constrains = Arrays.asList(constrain);
            return this;
        }

        public Builder businessDescription(String businessDescription) {
            this.businessDescription = businessDescription;
            return this;
        }

        public ISOComplexTypeRule build() {
            return new ISOComplexTypeRule(this);
        }
    }

    @Override
    protected boolean validate(NodeIndex nodeIndex, ValidationErrorHandler validationErrorHandler) {
        NodeList context = computeISOContextType(nodeIndex, complexType);
        boolean isValid = false;
        if (context != null) {
            isValid = true;

            //for any coincidence in the context
            for (int index = 0; index < context.getLength(); index++) {

                NodeList currIteration = new MutableNodeList(context.item(index));

                //check constrain
                boolean evalsConstrain = Arrays.stream(this.constrains).allMatch(it -> it.evaluate(nodeIndex, currIteration));

                //If the constrain is false, we do not evaluate the condition results
                if (evalsConstrain) {
                    //check conditions stream as ANDS
                    boolean evalsCondition = Arrays.stream(this.conditions).allMatch(it -> it.evaluate(nodeIndex, currIteration));
                    isValid &= evalsCondition;
                }
            }
        }

        if (!isValid)
            validationErrorHandler.error(
                    this.errorCode,
                    null,
                    errorDescription.isEmpty() ? String.format(String.valueOf(this.complexType), this.conditions.length) : errorDescription,
                    getDisplayName(),
                    String.valueOf(complexType)
            );
        return isValid;
    }
}
