package com.chinese.poem.processor;

import com.chinese.poem.model.AnthologyModel;
import com.chinese.poem.model.PoemModel;
import com.chinese.poem.repository.AnthologyRepository;
import com.chinese.poem.repository.PoemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AnthologyProcessor {
    @Autowired
    protected AnthologyRepository anthologyRepository;


    public List<AnthologyModel> searchByIdsIn(Set<String> ids){
        List<AnthologyModel> anthologyModels = anthologyRepository.findByIdIn(ids);
        if (anthologyModels != null){
            System.out.println("found "+anthologyModels.size());
        }
        return anthologyModels;
    }
}
