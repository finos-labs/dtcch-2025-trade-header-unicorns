package com.tradeheader.iso20022.validation.constrain;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tradeheader, SL
 * List of Unitary constrains, between them they are related like ORs
 * @author mmartos
 * @since 24/11/2020
 */
public class ISOConstrains implements IConstrain {

    private List<IConstrain> conditions;

    public ISOConstrains() {
        this.conditions = new ArrayList<>();
    }

    public ISOConstrains(ISOUnitaryConstrain constrain) {
        this();
        this.conditions.add(constrain);
    }

    public ISOConstrains or(IConstrain constrain) {
        this.conditions.add(constrain);
        return this;
    }

    public boolean evaluate(NodeIndex nodeIndex, NodeList context) {
        if (this.conditions.isEmpty()) return true;
        return this.conditions.stream().anyMatch(it -> it.evaluate(nodeIndex, context));
    }
}
