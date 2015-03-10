package com.copili.feeder.domain;

import com.copili.feeder.controller.json.JsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "copis")
public class CopiChangeForm extends CopiForm implements Serializable {

    @JsonProperty("announcement")
    private String announcement;

    @JsonProperty("begin")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date begin;

    @JsonProperty("end")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date end;

    @JsonProperty("ongId")
    private String ongId;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public CopiChangeForm() {
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

    //----------------------------------------------------------------------
    // Adapter
    //----------------------------------------------------------------------

    public CopiChange toCopiChange() {
        CopiChange copiChange = new CopiChange();
        copiChange.setText(text);
        copiChange.setBegin(begin);
        copiChange.setEnd(end);
        copiChange.setKeywords(keywords);
        copiChange.setState(state);
        copiChange.setAnnouncement(announcement);
        copiChange.setOngId(ongId);
        copiChange.setUrl(url);
        return copiChange;
    }

}
