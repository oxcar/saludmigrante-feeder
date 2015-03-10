package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "copis")
public class CopiExperience extends Copi implements Serializable {

    @JsonProperty("published_date")
    private Date publishedDate;

    @JsonProperty("source")
    private String source;

    @JsonProperty("thumbnail")
    protected String thumbnail;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public CopiExperience() {
        super();
        this.type = Type.EXPERIENCE;
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
