package com.chinese.poem.controller;

import com.chinese.poem.model.PoemModel;
import com.chinese.poem.processor.PoemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PoemController {

    @Autowired
    PoemProcessor poemProcessor;

    @GetMapping("/test")
    public String test(){
        PoemModel poemModel = poemProcessor.getByStart();
        return poemModel.toString();
    }

    @GetMapping("/api/poem")
    public String getPoem(String id){
        return "Hello & welcome to Poem World";
    }

    @GetMapping("/api/poems")
    public ResponseEntity<List<PoemModel>> getAllPoems(@RequestParam(required = true, name = "keyword") String keyword,
            @RequestParam(required = true, name = "value") String value) {
        try {
            System.out.println(keyword);
            System.out.println(keyword);

            System.out.println(value);
            System.out.println(value);
            List<PoemModel> poemModels = new ArrayList<PoemModel>();

            if (keyword.equalsIgnoreCase("title")){
                poemProcessor.searchByTitle(value).forEach(poemModels::add);
            }else if (keyword.equalsIgnoreCase("author")){
                poemProcessor.searchByAuthor(value).forEach(poemModels::add);
            }


            if (poemModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for(PoemModel poem: poemModels){
                System.out.println(poem.getId());
                System.out.println(poem.getAuthor());
                System.out.println(poem.getDynasty());
            }
            return new ResponseEntity<>(poemModels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
