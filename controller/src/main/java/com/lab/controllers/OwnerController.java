package com.lab.controllers;

import com.lab.dto.CatDto;
import com.lab.dto.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lab.services.OwnerService;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }
    @GetMapping("/find")
    public List<OwnerDto> getAllOwner(){
        return ownerService.getAllOwner();
    }
    @RequestMapping(value = "/find", params = "id", method = RequestMethod.GET)
    public OwnerDto findById(@RequestParam("id") long id){
        return ownerService.findById(id);
    }
    @RequestMapping(value = "/find", params = "name", method = RequestMethod.GET)
    public List<OwnerDto> findByName(@RequestParam("name") String name){
        return ownerService.findByName(name);
    }
    @GetMapping("/cat-list")
    public List<CatDto> getAllCats(@RequestParam("id") long id){
        return ownerService.getAllCats(id);
    }
    @PostMapping("/create")
    public OwnerDto createOwner(@RequestBody OwnerDto ownerDto){
        return ownerService.createOwner(ownerDto);
    }
    @PutMapping("/update")
    public OwnerDto updateOwner(@RequestBody OwnerDto ownerDto){
        return ownerService.updateOwner(ownerDto);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteOwner(@PathVariable long id){
        return ownerService.deleteOwner(id);
    }
}
