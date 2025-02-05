package com.tradeheader.iso20022.meta.isoToolkitEnvelope;

import com.handcoded.meta.DefaultSchemaRecogniser;
import com.handcoded.meta.SchemaRelease;
import com.tradeheader.iso20022.meta.version.ISOVersion;
import com.tradeheader.iso20022.meta.version.ISOVersionFactory;
import com.tradeheader.iso20022.utils.Extractor;
import org.w3c.dom.Document;

public class ISOToolkitEnvelopeSchemaRecogniser extends DefaultSchemaRecogniser {

    public ISOToolkitEnvelopeSchemaRecogniser() {
    }

    /**
     * {@inheritDoc}
     * <p>
     * Also checks that the identifier and version attribute matches the <CODE>SchemaRelease</CODE>
     * instance values.
     *
     * @since TFP 1.1
     */
    public boolean recognises(SchemaRelease release, Document document) {
        if (super.recognises(release, document)) {
            String releaseIdentifier = release.getIdentifier();
            String releaseVersion = release.getVersion();

            String identifier = Extractor.extractIdentifier(document);

            ISOVersion version = ISOVersionFactory.getISOVersion(release, document);
            if (identifier != null && releaseIdentifier != null && identifier.equals(releaseIdentifier) &&
                    (version != null && version.getISOVersionStringValue() != null && version.getISOVersionStringValue().equals(releaseVersion)))
                return (true);
        }
        return (false);
    }


}
