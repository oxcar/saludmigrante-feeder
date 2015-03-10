package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FeedEntryProcess implements Serializable {

    @JsonProperty("words")
    private String words;

    @JsonProperty("stateCode")
    private String stateCode;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public FeedEntryProcess() {
        super();
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

}
