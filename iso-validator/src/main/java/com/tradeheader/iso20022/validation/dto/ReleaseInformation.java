package com.tradeheader.iso20022.validation.dto;

import com.handcoded.meta.Specification;
import com.tradeheader.iso20022.meta.version.ISOVersion;

import java.util.Objects;

public class ReleaseInformation {
    private final String specification;
    private final ISOVersion version;
    private final String namespace;
    private final String identifier;

    private ReleaseInformation (
            String specification,
            ISOVersion version,
            String namespace,
            String identifier
    ) {
        this.specification = specification;
        this.version = version;
        this.namespace = namespace;
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ReleaseInformation that = (ReleaseInformation) object;
        return Objects.equals(specification, that.specification) && Objects.equals(version, that.version) && Objects.equals(namespace, that.namespace) && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specification, version, namespace, identifier);
    }

    public String getSpecification() {
        return specification;
    }

    public ISOVersion getVersion() {
        return version;
    }

    public String getNamespace() {
        return namespace;
    }

    public static final class Builder {

        private String specification;
        private ISOVersion version;
        private String namespace;
        private String identifier;

        private Builder() {
        }

        public static ReleaseInformation.Builder anInstance() {
            return new ReleaseInformation.Builder();
        }

        public ReleaseInformation.Builder withSpecification (Specification specification) {
            this.specification = (specification != null) ? specification.getName() : null;
            return this;
        }

        public ReleaseInformation.Builder withVersion(ISOVersion version) {
            this.version = version;
            return this;
        }

        public ReleaseInformation.Builder withNamespace (String namespace) {
            this.namespace = namespace;
            return this;
        }

        public ReleaseInformation.Builder withIdentifier (String identifier) {
            this.identifier = identifier;
            return this;
        }

        public ReleaseInformation build() {
            return new ReleaseInformation(specification, version, namespace, identifier);
        }
    }
}