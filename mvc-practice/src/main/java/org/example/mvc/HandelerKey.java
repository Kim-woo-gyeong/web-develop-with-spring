package org.example.mvc;

import org.example.annotation.RequestMethod;

import java.util.Objects;

public class HandelerKey {
    private final RequestMethod requestMethod;
    private final String uriPath;

    public HandelerKey(String uriPath, RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        this.uriPath = uriPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HandelerKey)) return false;
        HandelerKey that = (HandelerKey) o;
        return requestMethod == that.requestMethod && Objects.equals(uriPath, that.uriPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestMethod, uriPath);
    }
}
