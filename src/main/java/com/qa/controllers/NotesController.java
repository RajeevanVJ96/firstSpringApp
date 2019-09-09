package com.qa.controllers;


import com.qa.models.Notes;
import com.qa.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class NotesController {

    @Autowired
    private NotesRepository repository;

    @RequestMapping(value = "notes", method = RequestMethod.GET)
    public List<Notes> listAllNotes(){
        return repository.findAll();
    }

    @RequestMapping(value = "notes", method = RequestMethod.POST)
    public Notes addNotes(@RequestBody Notes note){
        return repository.saveAndFlush(note);

    }

    @RequestMapping(value = "notes/{id}", method = RequestMethod.GET)
    public Notes getNotes(@PathVariable Long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "notes/{id}", method = RequestMethod.DELETE)
    public Notes delNotes(@PathVariable Long id){
        Notes existing = repository.findOne(id);
        repository.delete(id);
        return existing;

    }


}
