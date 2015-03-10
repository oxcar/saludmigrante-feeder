package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "copis")
public class CopiChange extends Copi implements Serializable {

    @JsonProperty("announcement")
    private String announcement;

    @JsonProperty("begin")
    private Date begin;

    @JsonProperty("end")
    private Date end;

    @JsonProperty("ongId")
    private String ongId;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public CopiChange() {
        super();
        this.type = Type.CHANGE;
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getOngId() {
        return ongId;
    }

    public void setOngId(String ongId) {
        this.ongId = ongId;
    }

}
