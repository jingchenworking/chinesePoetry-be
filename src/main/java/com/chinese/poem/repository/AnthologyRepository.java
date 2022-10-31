package com.chinese.poem.repository;

import com.chinese.poem.model.AnthologyModel;
import com.chinese.poem.model.PoemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface AnthologyRepository extends JpaRepository<AnthologyModel, String> {

    AnthologyModel queryById(String id);

    List<AnthologyModel> findByIdIn(Set<String> ids);

}