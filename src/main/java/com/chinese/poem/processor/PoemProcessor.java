package com.chinese.poem.processor;

import com.chinese.poem.model.PoemModel;
import com.chinese.poem.repository.PoemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PoemProcessor {
    @Autowired
    protected PoemRepository poemRepository;


    public PoemModel getByStart(){
        PoemModel poemModel = poemRepository.queryById("100001");
        System.out.println(poemModel.toString());
        return poemModel;
    }

    public List<PoemModel> searchByTitle(String title){
        List<PoemModel> poemModels = poemRepository.findByTitleContaining(title);
        if (poemModels != null){
            System.out.println("found "+poemModels.size());
        }
        return poemModels;
    }

    public List<PoemModel> searchByAuthor(String author){
        List<PoemModel> poemModels = poemRepository.findByAuthorContaining(author);
        if (poemModels != null){
            System.out.println("found "+poemModels.size());
        }
        return poemModels;
    }
}
