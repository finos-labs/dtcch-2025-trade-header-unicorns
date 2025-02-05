package com.tradeheader.iso20022.schemes;

import com.handcoded.fpml.schemes.Enumerable;
import com.handcoded.fpml.schemes.Matchable;
import com.handcoded.fpml.schemes.Value;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Tradeheader, SL
 *
 * @author mmartos
 * @since 25/04/2019
 */
public class ISOClosedScheme extends ISOCachedScheme implements Enumerable, Matchable {

    public ISOClosedScheme(String name) {
        super(name);
    }

//    public ISOClosedScheme(String uri, String canonicalUri) {
//        super(uri, canonicalUri);
//    }

//    public ISOClosedScheme(String uri, String canonicalUri, String name) {
//        super(uri, canonicalUri, name);
//    }

    public final Value[] values() {
        Value[] result = new Value[this.values.size()];
        this.values.values().toArray(result);
        return result;
    }

    public final Value[] values(String pattern) {
        Vector<Value> matches = new Vector();
        Enumeration cursor = this.values.elements();

        while(cursor.hasMoreElements()) {
            Value value = (Value)cursor.nextElement();
            if (value.getCode().matches(pattern)) {
                matches.add(value);
            }
        }

        Value[] result = new Value[matches.size()];
        matches.copyInto(result);
        return result;
    }
}
