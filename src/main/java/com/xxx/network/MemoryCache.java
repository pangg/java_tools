package com.xxx.network;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCache extends ResponseCache {
    private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<>();

    private final int maxEntries;

    public MemoryCache() {
        this(100);
    }

    public MemoryCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> rqstHeaders) throws IOException {
        if ("GET".equals(requestMethod)) {
            SimpleCacheResponse response = responses.get(uri);
            if (response != null && response.isExpired()) {
                responses.remove(response);
                response = null;
            }
            return response;
        } else {
            return null;
        }
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if (responses.size() >= maxEntries) {
            return null;
        }

        CacheControl control = new CacheControl(conn.getHeaderField("Cache-Control"));

        SimpleCacheRequest request = new SimpleCacheRequest();
        SimpleCacheResponse response = new SimpleCacheResponse(request, conn, control);

        responses.put(uri, response);
        return request;
    }
}
