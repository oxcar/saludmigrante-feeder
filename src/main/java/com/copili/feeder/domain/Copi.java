package com.copili.feeder.domain;

import com.copili.feeder.domain.listener.BaseDocumentListener;
import com.copili.feeder.domain.listener.CopiListener;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "copis")
@EntityListeners(CopiListener.class)
public class Copi extends BaseDocument implements Serializable {

    @JsonProperty("url")
    protected String url;

    @JsonProperty("text")
    protected String text;

    @JsonProperty("keywords")
    protected List<String> keywords;

    @JsonProperty("state")
    protected String state;

    @JsonProperty("type")
    protected Type type;

    @JsonProperty("indexed")
    protected Boolean indexed;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public Copi() {
        super();
        indexed = false;
    }

    //----------------------------------------------------------------------
    // Inner types
    //----------------------------------------------------------------------

    public enum Type {
        LEARN,
        EXPERIENCE,
        CHANGE
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public Boolean getIndexed() {
        return indexed;
    }

    public void setIndexed(Boolean indexed) {
        this.indexed = indexed;
    }

}
