package com.ms.twittertests.http;

public class OauthResolver {
    private String headerName = "Authorization";
    private String headerValue = "OAuth oauth_consumer_key=\"M28ZXWxYag9P7AtUjmtSwyBIR\",oauth_token=\"993992099849736193-AZV48HUc1UigbvAliQWrDDq6BGRCqNm\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1525950654\",oauth_nonce=\"v45SrOTkGXC\",oauth_version=\"1.0\",oauth_signature=\"a0QR%2Fb997RQGKaNE9YbAVD3%2FWF4%3D\"";

    public String getHeaderValue() {
        return headerValue;
    }

    public String getHeaderName() {
        return headerName;
    }
}
