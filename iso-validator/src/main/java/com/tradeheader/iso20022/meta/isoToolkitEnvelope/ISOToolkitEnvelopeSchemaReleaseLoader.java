package com.tradeheader.iso20022.meta.isoToolkitEnvelope;

import com.handcoded.fpml.schemes.SchemeCollection;
import com.handcoded.meta.DefaultSchemaReleaseLoader;
import com.handcoded.meta.SchemaRelease;
import com.handcoded.meta.Specification;
import com.handcoded.xml.Types;
import com.handcoded.xml.XPath;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Hashtable;

public class ISOToolkitEnvelopeSchemaReleaseLoader extends DefaultSchemaReleaseLoader {

    @Override
    public void loadData (Specification specification, Element context,
                          Hashtable<String, SchemaRelease> loadedSchemas)
    {
        Attr id 		= context.getAttributeNode ("id");

        com.handcoded.fpml.meta.SchemaRelease release = new com.handcoded.fpml.meta.SchemaRelease(specification,
                getVersion (context), getNamespaceUri (context),
                getSchemaLocation (context), getPreferredPrefix (context),
                getAlternatePrefix (context),
                new ISOToolkitEnvelopeInstanceInitialiser(),
                new ISOToolkitEnvelopeSchemaRecogniser(), getRootElements (context),
                null, getSchemeCollection(context), getIdentifier(context));

        handleImports (release, context, loadedSchemas);

        if (id != null) loadedSchemas.put (id.getValue (), release);
    }

    /**
     * Build a <CODE>ISOSchemeCollection</CODE> instance for the release using
     * the scheme filenames from the XML section describing the schema.
     *
     * @param	context			The context <CODE>Element</CODE> for the section.
     * @return	A populated <CODE>ISOSchemeCollection</CODE> instance.
     * @since	TFP 1.5
     */
    private SchemeCollection getSchemeCollection (Element context)
    {
        SchemeCollection schemes = new SchemeCollection ();
        NodeList list = XPath.paths (context, "schemes");

        for (int index = 0; index < list.getLength (); ++index)
            schemes.parse (Types.toToken (list.item (index)));

        return (schemes);
    }
}
