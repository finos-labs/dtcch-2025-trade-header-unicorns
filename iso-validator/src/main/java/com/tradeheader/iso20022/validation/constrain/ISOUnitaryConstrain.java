package com.tradeheader.iso20022.validation.constrain;

import com.handcoded.xml.MutableNodeList;
import com.handcoded.xml.NodeIndex;
import com.handcoded.xml.XPath;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Tradeheader, SL
 *
 * The constrain itself can be evaluated (It is not binary)
 *
 * @author mmartos
 * @since 20/10/2020
 */
public class ISOUnitaryConstrain implements IConstrain {

    public enum ConstrainMode {
        ALWAYS,
        NEVER,
        ISPRESENT,
        ISABSENT,
        ISEQUAL,
        ISATLEASTONEEQUAL,
        ISNOTEQUAL
    }

    // As the key the xpath is used, and it maps to all the tokenized items obtained from the xpath being used as key.
    private Map<String, String[]> elements;
    // Which are the types of restrictions that can be evaluated
    private ConstrainMode mode;
    private String regex;

    /**
     *
     * Used (only) to create constrains that "always" or "never" evaluate
     *
     * @param mode
     */
    public ISOUnitaryConstrain(final ConstrainMode mode) {
        this.mode = mode;
    }
    /**
     * Used to create constrains that evaluates presence or absence of the xpath node in the context
     * @param xpath
     * @param mode
     */
    public ISOUnitaryConstrain(final String xpath, final ConstrainMode mode) {
        this(new String[]{xpath}, mode);
    }

    /**
     * Used to create constrains that evaluates the values of multiple xpath nodes in the context
     * @param xpath
     * @param mode
     */
    public ISOUnitaryConstrain(final String[] xpath, final ConstrainMode mode) {

        this.elements = new HashMap<>();
        Arrays.asList(xpath).forEach(it -> {
            String xp = it.replaceAll("\\[(.*?)\\]", "");
            String[] el = Arrays.stream(xp.split("/")).filter(e -> !e.isEmpty()).toArray(String[]::new);
            this.elements.put(xp, el);
        });

        this.mode = mode;

        // Set to null since we are not comparing it with a static value. Then we don't need regex expressions, the same xpaths suffice.
        this.regex = null;

    }

    /**
     * Used to create constrains that evaluates the value of the xpath node with a regular expression. It can be used with equality, non-equality, greaterthan, etc.
     * @param xpath
     * @param valueRegex
     * @param mode
     */
    public ISOUnitaryConstrain(final String xpath, final String valueRegex, final ConstrainMode mode) {
        this(new String[]{xpath}, valueRegex, mode);
    }

    /**
     * Used to create constrains that evaluates the value of multiple xpath nodes with regular expressions. It can be used with equality, non-equality, greaterthan, etc.
     * @param xpath
     * @param valueRegex
     * @param mode
     */
    public ISOUnitaryConstrain(final String[] xpath, final String valueRegex, final ConstrainMode mode) {

        this.elements = new HashMap<>();
        Arrays.asList(xpath).forEach(it -> {
            String xp = it.replaceAll("\\[(.*?)\\]", "");
            String[] el = Arrays.stream(xp.split("/")).filter(e -> !e.isEmpty()).toArray(String[]::new);
            this.elements.put(xp, el);
        });

        this.mode = mode;
        this.regex = valueRegex;
    }

    public boolean evaluate(final NodeIndex nodeIndex, final NodeList context) {
        boolean result = true;

        if (context == null || nodeIndex == null) result = false;
        else {
            switch (mode) {
                case ALWAYS:
                    result = true;
                    break;
                case NEVER:
                    result = false;
                    break;
                case ISEQUAL:
                case ISATLEASTONEEQUAL:
                case ISNOTEQUAL:
                    result = evaluateCompare(nodeIndex, context);
                    break;
                case ISABSENT:
                case ISPRESENT:
                    result = evaluatePresence(nodeIndex, context);
                    break;
            }
        }

        if (!result) {
            //will there be any description?
        }

        return result;
    }

    private boolean evaluateCompare(NodeIndex nodeIndex, NodeList context) {
        boolean result = true;

        List<String[]> matches = this.elements.entrySet().stream().map(entry -> eval(nodeIndex, context, entry.getKey(), entry.getValue())).collect(Collectors.toList());
        switch (mode) {
            case ISEQUAL:
                if (this.regex == null) {
                    long uniqueCount = matches.stream()
                            .flatMap(Arrays::stream)
                            .distinct()
                            .count();
                    result = uniqueCount <= 1;
                }
                else {
                    result = matches.stream().allMatch(it -> {
                        if (it == null) return false;
                        for (String s : it) {
                            if (!s.matches(this.regex)) return false;
                        }
                        return true;
                    });
                }
                break;
            case ISATLEASTONEEQUAL:
                if (this.regex == null) {
                    long uniqueCount = matches.stream()
                            .flatMap(Arrays::stream)
                            .distinct()
                            .count();
                    result = uniqueCount <= 1;
                }
                else {
                    result = matches.stream().anyMatch(it -> {
                        if (it == null) return false;
                        for (String s : it) {
                            if (s.matches(this.regex)) return true;
                        }
                        return false;
                    });
                }
                break;
            case ISNOTEQUAL:
                if (this.regex == null) {
                    long uniqueCount = matches.stream()
                            .flatMap(Arrays::stream)
                            .distinct()
                            .count();
                    result = uniqueCount > 1;
                }
                else {
                    result = matches.stream().noneMatch(it -> {
                        if (it == null) return false;
                        for (String s : it) {
                            if (s.matches(this.regex)) return true;
                        }
                        return false;
                    });
                }
                break;
        }

        return result;
    }

    private boolean evaluatePresence(NodeIndex nodeIndex, NodeList context) {
        boolean result = true;

        List<String[]> matches = this.elements.entrySet().stream().map(entry -> eval(nodeIndex, context, entry.getKey(), entry.getValue())).collect(Collectors.toList());
        switch(mode) {
            case ISPRESENT:
                result = matches.stream().allMatch(it -> it != null && it.length > 0);
                break;
            case ISABSENT:
                result = matches.stream().allMatch(Objects::isNull);
        }
        return result;
    }

    private String[] eval (NodeIndex nodeIndex, NodeList context, String xpath, String[] elements) {

        String[] result = new String[]{""};
        MutableNodeList attrContext = new MutableNodeList();

        for (int index = 0; index < elements.length; index++) {
            if (elements[index].contains("@")) {
                for (int j = 0; j < context.getLength(); j++) {
                    Node attr = context.item(j).getAttributes().getNamedItem(elements[index].replace("@", ""));
                    if (attr == null)
                        result = null;
                    else {
                        result[0] = attr.getTextContent();
                        attrContext.add(attr);
                    }
                }
                break;
            } else
                context = XPath.paths(context, elements[index]);

            if (context == null || context.getLength() == 0) {
                result = null;
                break;
            }
        }

        if (attrContext.getLength() > 0)
            context = attrContext;

        if (context != null && context.getLength() > 0) {
            result = new String[context.getLength()];
            for (int i = 0; i < context.getLength(); i++) {
                result[i] = context.item(i).getTextContent();
            }
        }

        return result;
    }
}
