package com.chinese.poem.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "anthology")
public class AnthologyModel {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ispost")
    private String isPost;

    private String title;

    private String edition;

    @Column(name = "creation_year")
    private String creationYear;
    @Column(name = "creation_year_dynasty")
    private String creationYearDynasty;
    @Column(name = "creation_year_remarks")
    private String creationYearRemarks;

    @Column(name = "publish_year")
    private String publishYear;
    @Column(name = "publish_year_dynasty")
    private String publishYearDynasty;

    private String source;

    private String editor;

}