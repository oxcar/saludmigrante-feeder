package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CopiText {

    @JsonProperty("text")
    private String text;

    public CopiText() {
        super();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
