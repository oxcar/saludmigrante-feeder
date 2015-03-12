package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "filter_words")
public class FilterWord implements Serializable {

    @Id
    @JsonProperty("_id")
    protected String id;

    @JsonProperty("word")
    private String word;

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("hits")
    private Long hits;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public FilterWord() {
        super();
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getHits() {
        if(null != hits && hits.compareTo(0L) >= 0) {
            return hits;
        }
        return 0L;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    public void incrementHits(Long increment) {
        this.hits = getHits() + increment;
    }

}
