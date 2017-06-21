package com.example.wilder.myapplicationgraphapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StreamModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("stream_url")
    @Expose
    private String streamUrl;
    @SerializedName("secure_stream_url")
    @Expose
    private String secureStreamUrl;
    @SerializedName("stream_secondary_urls")
    @Expose
    private List<Object> streamSecondaryUrls = null;
    @SerializedName("secure_stream_secondary_urls")
    @Expose
    private List<Object> secureStreamSecondaryUrls = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getSecureStreamUrl() {
        return secureStreamUrl;
    }

    public void setSecureStreamUrl(String secureStreamUrl) {
        this.secureStreamUrl = secureStreamUrl;
    }

    public List<Object> getStreamSecondaryUrls() {
        return streamSecondaryUrls;
    }

    public void setStreamSecondaryUrls(List<Object> streamSecondaryUrls) {
        this.streamSecondaryUrls = streamSecondaryUrls;
    }

    public List<Object> getSecureStreamSecondaryUrls() {
        return secureStreamSecondaryUrls;
    }

    public void setSecureStreamSecondaryUrls(List<Object> secureStreamSecondaryUrls) {
        this.secureStreamSecondaryUrls = secureStreamSecondaryUrls;
    }

}
