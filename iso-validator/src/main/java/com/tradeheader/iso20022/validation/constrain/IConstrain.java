package com.tradeheader.iso20022.validation.constrain;

import com.handcoded.xml.NodeIndex;
import org.w3c.dom.NodeList;

/**
 * Created by Tradeheader, SL
 * The minimal class to apply conditions and restrictions

 * @author mmartos
 * @since 24/11/2020
 */
public interface IConstrain {

    boolean evaluate(final NodeIndex nodeIndex, final NodeList context);
}
