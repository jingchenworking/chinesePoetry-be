package com.chinese.poem.model;

import java.util.List;

public class Poem {
    private String id;

    private String title;

    private String titleSource;

    private List<TitleSource> sources;

    private String style;

    private String author;

    private String authorSource;

    private String dynasty;

    private String dynastySource;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSource() {
        return titleSource;
    }

    public void setTitleSource(String titleSource) {
        this.titleSource = titleSource;
    }

    public List<TitleSource> getSources() {
        return sources;
    }

    public void setSources(List<TitleSource> sources) {
        this.sources = sources;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorSource() {
        return authorSource;
    }

    public void setAuthorSource(String authorSource) {
        this.authorSource = authorSource;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getDynastySource() {
        return dynastySource;
    }

    public void setDynastySource(String dynastySource) {
        this.dynastySource = dynastySource;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
