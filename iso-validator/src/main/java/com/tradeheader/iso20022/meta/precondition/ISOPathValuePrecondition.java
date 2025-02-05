package com.tradeheader.iso20022.meta.precondition;

import com.handcoded.validation.Precondition;
import com.handcoded.xml.NodeIndex;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import static com.tradeheader.iso20022.ISORuleSetLogic.computeContext;

/**
 * Created by Tradeheader, SL
 *
 * @author mmartos
 * @since 21/10/2019
 */
public class ISOPathValuePrecondition extends Precondition {

    /**
     * A list of element names to check for.
     */
    private final String[] elements;
    /**
     * A list of values to check for.
     */
    private final String[] values;

    /**
     * Constructions a <CODE>ISOPathValuePrecondition</CODE> instance that checks
     * documents containing the indicated elements or types.
     *
     * @param elements The names of elements to check for.
     * @param values   The values to check for.
     */
    public ISOPathValuePrecondition(String[] elements, String[] values) {
        this.elements = elements;
        this.values = values;
    }

    /**
     * Constructions a <CODE>ISOPathValuePrecondition</CODE> instance that checks
     * documents containing the indicated elements or types.
     *
     * @param path   The simple xpath of elements to check for.
     * @param values The values to check for.
     */
    public ISOPathValuePrecondition(String path, String[] values) {
        String xpath = path.replaceAll("\\[(.*?)\\]", "");
        this.elements = Arrays.stream(xpath.split("/")).filter(it -> !it.isEmpty()).toArray(String[]::new);
        this.values = values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(NodeIndex nodeIndex, Map<Precondition, Boolean> hashtable) {
        NodeList context = computeContext(nodeIndex, elements);

        return haveInContextAnyOfTheValues(context);
    }

    private boolean haveInContextAnyOfTheValues(NodeList context) {
        if (context != null && context.getLength() > 0) {
            for (int index = 0; index < context.getLength(); index++) {
                Element e = (Element) context.item(index);
                if (Arrays.stream(this.values).filter(it -> Pattern.matches(it, e.getTextContent())).count() > 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return (String.format("path(%s) with value anyOf(%s)", String.join(",", elements), String.join(",", values)));
    }
}
