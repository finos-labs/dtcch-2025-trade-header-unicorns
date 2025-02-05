package com.tradeheader.iso20022.schemes;

import com.handcoded.fpml.schemes.Scheme;
import com.handcoded.fpml.schemes.Value;

import java.util.Hashtable;

/**
 * Created by Tradeheader, SL
 *
 * @author mmartos
 * @since 25/04/2019
 */
public class ISOCachedScheme extends Scheme {
    protected Hashtable<String, Value> values = new Hashtable();

//    public ISOCachedScheme(String uri) {
//        super(uri);
//    }

//    public ISOCachedScheme(String uri, String canonicalUri) {
//        super(uri, canonicalUri);
//    }

//    public ISOCachedScheme(String uri, String canonicalUri, String name) {
//        super(uri, canonicalUri, name);
//    }

    public boolean isValid(String code) {
        return this.values.containsKey(code);
    }

    public boolean isDynamic() {
        return this.values.isEmpty();
    }

    protected final Value add(Value value) {
        return (Value)this.values.put(value.getCode(), value);
    }

    public ISOCachedScheme(String name) {super(name);}
}
