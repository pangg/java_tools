package com.xxx.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleCacheResponse extends CacheResponse {
    private final SimpleCacheRequest request;
    private final Map<String, List<String>> headers;
    private final CacheControl control;
    private final Date expires;

    public SimpleCacheResponse(SimpleCacheRequest request, URLConnection uc, CacheControl control) {
        this.request = request;
        this.headers = Collections.unmodifiableMap(uc.getHeaderFields());
        this.control = control;
        this.expires = new Date(uc.getExpiration());
    }

    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(request.getData());
    }

    public CacheControl getControl() {
        return control;
    }

    public boolean isExpired() {
        Date now = new Date();
        if (control.getMaxAge().before(now)) {
            return true;
        }else{
            return false;
        }
    }
}
