package com.qa.controller;


import com.qa.controllers.HomeController;
import com.qa.controllers.NotesController;
import com.qa.models.Notes;
import com.qa.repository.NotesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesControllerTest {

    @InjectMocks
    private NotesController notesController;

    @Mock
    private NotesRepository repository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllNotes(){

        List<Notes> notesList = new ArrayList<>();
        Notes note = new Notes();
        note.setDescription("blah");
        note.setName("blah");
        notesList.add(note);

        when(repository.findAll()).thenReturn(notesList);

        assertEquals(notesController.listAllNotes().get(0).getName(), "blah");

    }

    @Test
    public void testGetNote(){

        Notes note = new Notes();
        note.setId(1l);
        note.setDescription("blah");
        note.setName("blah");

        when(repository.findOne(1L)).thenReturn(note);

        assertEquals(notesController.getNotes(1L).getName(), "blah");
    }

    @Test
    public void testAddNote(){

        Notes note = new Notes();
        note.setId(1L);
        note.setDescription("blah");
        note.setName("blah");

        when(repository.saveAndFlush(note)).thenReturn(note);

        assertEquals(notesController.addNotes(note).getName(), "blah");
    }

    @Test
    public void testDelNotes(){

        Notes existing;
        Notes note = new Notes();
        note.setId(1L);
        note.setDescription("blah");
        note.setName("blah");

        when(repository.findOne(1L)).thenReturn(note);
        repository.delete(1L);
        assertEquals(notesController.delNotes(1L).getName(), "blah");
    }
}
