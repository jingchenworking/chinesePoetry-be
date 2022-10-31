package com.chinese.poem.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "poem")
public class PoemModel {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ispost")
    private String isPost;

    private String title;

    @Column(name = "title_source")
    private String titleSource;

    private String style;
    @Column(name = "style_source")
    private String styleSource;

    private String author;

    @Column(name = "author_source")
    private String authorSource;

    private String dynasty;

    @Column(name = "dynasty_source")
    private String dynastySource;
    private String remarks;

}