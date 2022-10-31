package com.chinese.poem.repository;

import com.chinese.poem.model.PoemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;


@Repository
public interface PoemRepository extends JpaRepository<PoemModel, String> {

    PoemModel queryById(String id);

    List<PoemModel> findByTitleContaining(String title);

    List<PoemModel> findByAuthorContaining(String author);

}