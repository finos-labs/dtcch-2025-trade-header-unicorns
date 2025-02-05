package com.tradeheader.iso20022;

import com.handcoded.xml.MutableNodeList;
import com.handcoded.xml.NodeIndex;
import com.handcoded.xml.XPath;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Arrays;

public abstract class ISORuleSetLogic {

    /**
     * Traverses the nodeIndex up to the point where the {elements} specify.
     *
     * @param nodeIndex
     * @param elements
     * @return A nodeList (context) which is the nodeIndex up to the point (node) where elements specify or up to a
     * the first valid @ element.
     */
    public static NodeList computeContext(NodeIndex nodeIndex, String[] elements) {
        NodeList context = nodeIndex.getElementsByName(elements[0]);

        for (int index = 1; index < elements.length; index++) {
            if (context == null || context.getLength() == 0) return null;
            if (elements[index].contains("@")) {
                return isElementWithAtSignValidInTheContext(elements[index], context);
            } else {
                context = XPath.paths(context, elements[index]);
                if (context == null || context.getLength() == 0) return null;
            }
        }
        return context;
    }

    /**
     * Gets from the nodeIndex the nodes with the specified complex types in {type}
     *
     * @param nodeIndex
     * @param type
     * @return A nodeList (context) with nodeIndex nodes that have the specified types
     */
    public static NodeList computeISOContextType(NodeIndex nodeIndex, String[] type) {
        MutableNodeList context = new MutableNodeList();
        String[] types = nodeIndex.getTypesNames();
        for (String s : type) {
            if (s.endsWith("[*]")) { // ending with "[*]" enables the pattern behaviour of compute context type
                // 1st: adds the not-pattern type (without [*])
                context.addAll(nodeIndex.getElementsByTypeName(s.replace("[*]", "")));

                // 2nd: iterate over all combinations of possible patterns __i
                String[] matchingTypes = Arrays.stream(types)
                        .filter(t -> t.startsWith(s.replace("[*]", "").concat("__")))
                        .toArray(String[]::new);
                NodeList patternNL;
                for (String ty: matchingTypes) {
                    patternNL = nodeIndex.getElementsByTypeName(ty);
                    if (patternNL.getLength() > 0) {
                        context.addAll(patternNL);
                    }
                }
            } else {
                context.addAll(nodeIndex.getElementsByTypeName(s));
            }
        }
        return context;
    }

    private static NodeList isElementWithAtSignValidInTheContext(String element, NodeList context) {
        for (int j = 0; j < context.getLength(); j++) {
            Node attr = context.item(j).getAttributes().getNamedItem(element.replace("@", ""));
            if (attr == null)
                return null;
        }
        // if in the given context exists the element means the element is correct for the context
        return context;
    }

}
