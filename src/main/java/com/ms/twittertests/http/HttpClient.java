package com.ms.twittertests.http;

import java.io.IOException;

public interface HttpClient {
    String get(String url) throws IOException;
}
