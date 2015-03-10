package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "feed_entries")
public class FeedEntry extends BaseDocument implements Serializable {

    @JsonProperty("feedId")
    private String feedId;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("published_date")
    private Date publishedDate;

    @JsonProperty("matched_words")
    private List<String> matchedWords;

    @JsonProperty("matched_phrases")
    private List<String> matchedPhrases;

    @JsonProperty("thumbnails")
    private List<String> thumbnails;

    @JsonProperty("source")
    private String source;

    @JsonProperty("state")
    private String state;

    @JsonProperty("processed")
    private Boolean processed;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public FeedEntry(String feedId, String uri, String title, String description, Date publishedDate, String source) {
        this.feedId = feedId;
        this.uri = uri;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.matchedWords = new ArrayList<>();
        this.matchedPhrases = new ArrayList<>();
        this.thumbnails = new ArrayList<>();
        this.source = source;
        this.processed = false;
        this.state = "US";
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    @JsonProperty("matched_words_as_string")
    public String getMatchedWordsAsString() {
        StringBuilder sb = new StringBuilder();
        for (String word : matchedWords) {
            sb.append(word).append(',');
        }
        return StringUtils.removeEnd(sb.toString(), ",");
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

    public List<String> getMatchedPhrases() {
        return matchedPhrases;
    }

    public void setMatchedPhrases(List<String> matchedPhrases) {
        this.matchedPhrases = matchedPhrases;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
