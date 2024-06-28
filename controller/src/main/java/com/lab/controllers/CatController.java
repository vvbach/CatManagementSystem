package com.lab.controllers;

import com.lab.dto.CatDto;
import com.lab.models.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lab.services.CatService;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;
    @Autowired
    public CatController(CatService catService){
        this.catService = catService;
    }
    @RequestMapping(value = "/find", params = "id", method = RequestMethod.GET)
    public CatDto findByID(@RequestParam("id") long id){
        return catService.findByID(id);
    }
    @RequestMapping(value = "/find", params = "name", method = RequestMethod.GET)
    public List<CatDto> findByName(@RequestParam("name") String name){
        return catService.findByName(name);
    }
    @RequestMapping(value = "/find", params = "breed", method = RequestMethod.GET)
    public List<CatDto> findByBreed(@RequestParam("breed") String breed){
        return catService.findByBreed(breed);
    }
    @RequestMapping(value = "/find", params = "color", method = RequestMethod.GET)
    public List<CatDto> findByColor(@RequestParam("color") Color color){
        return catService.findByColor(color);
    }
    @GetMapping("/find")
    public List<CatDto> getAllCats(){
        return catService.getAllCats();
    }
    @GetMapping("/friends")
    public List<CatDto> getAllFriend(@RequestParam("id") long id){
        return catService.getAllFriend(id);
    }
    @PostMapping("/create")
    public CatDto createCat(@RequestBody CatDto catDto){
        return catService.createCat(catDto);
    }
    @PutMapping("/update")
    public CatDto updateCat(@RequestBody CatDto catDto){
        return catService.updateCat(catDto);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteCat(@PathVariable long id){
        return catService.deleteCat(id);
    }
    @PutMapping("/make-friend")
    public boolean addFriend(@RequestParam("id1") long mainCatId, @RequestParam("id2") long friendCatId){
        return catService.addFriend(mainCatId, friendCatId);
    }
    @PutMapping("/unfriend")
    public boolean removeFriend(@RequestParam("id1") long mainCatId, @RequestParam("id2") long friendCatId){
        return catService.removeFriend(mainCatId, friendCatId);
    }
}
