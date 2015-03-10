package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "feeds")
public class Feed extends BaseDocument implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("last_processed_feed_entry")
    private Date lastProcessedFeedEntry;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public Feed() {
        super();
    }

    public Feed(String name, String url, Date lastProcessedFeedEntry) {
        this.name = name;
        this.url = url;
        this.lastProcessedFeedEntry = lastProcessedFeedEntry;
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getLastProcessedFeedEntry() {
        if(null == lastProcessedFeedEntry) {
            return new Date(0);
        }
        return lastProcessedFeedEntry;
    }

    public void setLastProcessedFeedEntry(Date lastProcessedFeedEntry) {
        this.lastProcessedFeedEntry = lastProcessedFeedEntry;
    }
}
