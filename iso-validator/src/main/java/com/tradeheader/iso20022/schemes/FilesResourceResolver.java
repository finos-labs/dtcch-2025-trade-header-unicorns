package com.tradeheader.iso20022.schemes;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tradeheader.validation.toolkit.utils.IOUtils;
import org.apache.xerces.dom.DOMInputImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class FilesResourceResolver implements LSResourceResolver {

    private final Logger LOGGER = LoggerFactory.getLogger(FilesResourceResolver.class);

    private final Pattern baseURIPattern = Pattern.compile(".*(?<path>(files-fpml(/[^/]+)*))/(?<item>[^/]+)");
    private final Pattern parentPathPattern = Pattern.compile("[^/]+/\\.\\./");

    @Override
    public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
        Matcher baseURIMatcher = baseURIPattern.matcher(baseURI);
        if (baseURIMatcher.matches()) {
            String baseURIPath = baseURIMatcher.group("path");
            String baseURIItem = baseURIMatcher.group("item");
            InputStream byteStream = IOUtils.openStream(resolveParentPaths(baseURIPath + "/" + systemId));
            logDebug(type, namespaceURI, publicId, systemId, baseURI, baseURIPath, baseURIItem);
            return new DOMInputImpl(publicId, systemId, baseURIPath + "/" + baseURIItem, byteStream, "UTF-8");
        } else {
            logDebug(type, namespaceURI, publicId, systemId, baseURI, null, null);
            return null;
        }
    }

    private String resolveParentPaths(String path) {
        Matcher matcher = parentPathPattern.matcher(path);
        if (matcher.find()) {
            return resolveParentPaths(matcher.replaceFirst(""));
        } else {
            return path;
        }
    }

    private void logDebug(
            String type, String namespaceURI, String publicId, String systemId, String baseURI,
            String baseURIItem, String baseURIPath
    ) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format(
                    "resolveResource type=%s namespaceURI=%s publicId=%s systemId=%s baseURI=%s => baseURIItem=%s baseURIPath=%s",
                    type, namespaceURI, publicId, systemId, baseURI, baseURIItem, baseURIPath
            ));
        }
    }
}
