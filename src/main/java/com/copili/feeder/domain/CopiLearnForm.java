package com.copili.feeder.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CopiLearnForm extends CopiForm implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<String> authors;

    @JsonProperty("journal")
    private String journal;

    @JsonProperty("volume")
    private String volume;

    @JsonProperty("issue")
    private String issue;

    @JsonProperty("pages")
    private Long pages;

    @JsonProperty("year")
    private Long year;

    @JsonProperty("doi")
    private String doi;

    @JsonProperty("texts")
    private List<CopiText> texts;

    //----------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------

    public CopiLearnForm() {
        super();
        this.type = Type.LEARN;
        this.authors = new ArrayList<>();
        this.texts = new ArrayList<>();
    }

    //----------------------------------------------------------------------
    // Getters & Setters
    //----------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        if (StringUtils.isBlank(authors)) {
            return;
        }
        String[] authorsArray = StringUtils.split(authors, ",");
        this.authors = Arrays.asList(authorsArray);
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public List<CopiText> getTexts() {
        return texts;
    }

    public void setTexts(List<CopiText> texts) {
        this.texts = texts;
    }

    public List<CopiLearn> toLearnCopis() {
        List<CopiLearn> copis = new ArrayList<>();
        for (CopiText text : texts) {
            CopiLearn copiLearn = new CopiLearn();
            copiLearn.setUrl(url);
            copiLearn.setTitle(title);
            copiLearn.setAuthors(authors);
            copiLearn.setJournal(journal);
            copiLearn.setVolume(volume);
            copiLearn.setIssue(issue);
            copiLearn.setPages(pages);
            copiLearn.setYear(year);
            copiLearn.setDoi(doi);
            copiLearn.setState(state);
            copiLearn.setKeywords(keywords);
            copiLearn.setText(text.getText());
            copis.add(copiLearn);
        }
        return copis;
    }
}
