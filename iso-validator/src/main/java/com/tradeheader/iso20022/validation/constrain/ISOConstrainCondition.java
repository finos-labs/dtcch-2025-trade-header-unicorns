package com.tradeheader.iso20022.validation.constrain;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tradeheader, SL
 *
 * List of Unitary constrains, between them they are related like ORs
 * @author mmartos
 * @since 20/10/2020
 */
public class ISOConstrainCondition {

    private List<ISOUnitaryConstrain> conditions;

    public ISOConstrainCondition() {
        this.conditions = new ArrayList<>();
    }

    public ISOConstrainCondition(ISOUnitaryConstrain constrain) {
        this();
        this.conditions.add(constrain);
    }

    public ISOConstrainCondition or(ISOUnitaryConstrain constrain) {
        this.conditions.add(constrain);
        return this;
    }

    public boolean evaluate(NodeIndex nodeIndex, NodeList context) {
        if (this.conditions.isEmpty()) return true;
        return this.conditions.stream().anyMatch(it -> it.evaluate(nodeIndex, context));
    }

}
