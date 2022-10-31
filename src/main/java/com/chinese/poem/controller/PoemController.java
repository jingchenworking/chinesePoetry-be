package com.chinese.poem.controller;

import com.chinese.poem.model.AnthologyModel;
import com.chinese.poem.model.Poem;
import com.chinese.poem.model.PoemModel;
import com.chinese.poem.model.TitleSource;
import com.chinese.poem.processor.AnthologyProcessor;
import com.chinese.poem.processor.PoemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class PoemController {

    @Autowired
    PoemProcessor poemProcessor;

    @Autowired
    AnthologyProcessor anthologyProcessor;

    final String separator = "[;；]";

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
    public ResponseEntity<List<Poem>> getAllPoems(@RequestParam(required = true, name = "keyword") String keyword,
                                                  @RequestParam(required = true, name = "value") String value) {
        try {
            System.out.println(keyword);
            System.out.println(keyword);

            System.out.println(value);
            System.out.println(value);
            List<PoemModel> poemModels = new ArrayList<PoemModel>();
            List<Poem> result = new ArrayList<>();
            Set<String> anthology = new HashSet<>();
            List<TitleSource> titleSources = new ArrayList<>();
            Map<String, AnthologyModel> IdTitleSources = new HashMap<>();

            if (keyword.equalsIgnoreCase("title")){
                poemProcessor.searchByTitle(value).forEach(poemModels::add);
            }else if (keyword.equalsIgnoreCase("author")){
                poemProcessor.searchByAuthor(value).forEach(poemModels::add);
            }

            if (poemModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // get all titleSources -- anthology
            System.out.println("printing out anthology");
            for(PoemModel poemModel: poemModels){
                Set<String> sourceAnthology = getSources(poemModel.getTitleSource());
                for(String sa : sourceAnthology){
                    System.out.println(sa);
                    anthology.add(sa);
                }
            }

            // get all anthologies from the ids
            List<AnthologyModel> anthologyModels = anthologyProcessor.searchByIdsIn(anthology);

            // build map, key ID -> anthology
            for(AnthologyModel anthologyModel: anthologyModels){
                IdTitleSources.put(anthologyModel.getId(), anthologyModel);
                System.out.println("key "+anthologyModel.getId());
                System.out.println("value with title "+anthologyModel.getTitle()+" "+anthologyModel.getSource());
            }

            // build the result
            for(PoemModel poemModel: poemModels){
                String[] titles = poemModel.getTitle().split(separator);
                String origalTitleSource = poemModel.getTitleSource();
                System.out.println(poemModel.getTitleSource());
                System.out.println(poemModel.getTitle());
                System.out.println("titles.length: "+titles.length);
                Poem poem = new Poem();
                poem.setAuthor(poemModel.getAuthor());
                poem.setDynasty(poemModel.getDynasty());
                poem.setId(poemModel.getId());
                poem.setTitle(poemModel.getTitle());
                poem.setStyle(poemModel.getStyle());
                poem.setTitleSource(poemModel.getTitleSource());

                List<TitleSource> sources = new ArrayList<>();

                for(int i=0; i<titles.length; i++){
                    System.out.println("titles.length is: "+titles.length);
                    String title = titles[i];
                    int idx = origalTitleSource.indexOf(";");
                    String source;
                    if (idx<0){
                        source = origalTitleSource;
                    }else{
                        source = origalTitleSource.substring(0, idx).trim();
                        if (source.length()<1){
                            continue;
                        }
                        origalTitleSource = origalTitleSource.substring(idx+1);
                    }

                    System.out.println("title-"+title +"-source-"+source+"+++remaining origalTitleSource:"+origalTitleSource);
                    if (!(title.length() >0 && source.length() >0)){
                        System.out.println("data issue, skip");
                        continue;
                    }
                    Set<String> tSources = getSources(source);
                    for (String s: tSources){
                        if (!IdTitleSources.containsKey(s)){
                            continue;
                        }
                        AnthologyModel anthologyModel = IdTitleSources.get(s);
                        TitleSource titleSource = new TitleSource();
                        titleSource.setTitleSource(title);
                        titleSource.setId(anthologyModel.getId());
                        titleSource.setCreationYear(anthologyModel.getCreationYear());
                        titleSource.setEdition(anthologyModel.getEdition());
                        titleSource.setSource(anthologyModel.getSource());
                        titleSource.setTitle(anthologyModel.getTitle());
                        sources.add(titleSource);
                    }
                }
                poem.setSources(sources);
                result.add(poem);
            }

            for(PoemModel poem: poemModels){
                System.out.println(poem.getId());
                System.out.println(poem.getAuthor());
                System.out.println(poem.getDynasty());
            }

            for(Poem poem: result){
                System.out.println(poem.getId());
                System.out.println(poem.getAuthor());
                System.out.println(poem.getDynasty());
                System.out.println(poem.getSources());
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.fillInStackTrace());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Set<String> getSources(String titleSource){
        Set<String>  anthology = new HashSet<>();
        String[] sourceAnthology = titleSource.split(separator);
        for(String sa : sourceAnthology){
            if(sa.indexOf("-")>0){
                String[] sources = sa.split("-");
                for(String s: sources){
                    anthology.add(s);
                }
            }else{
                anthology.add(sa);
            }
        }
        return anthology;
    }

}
