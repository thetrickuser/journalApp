package com.thetrickuser.journalapp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.thetrickuser.journalapp.model.JournalEntry;
import com.thetrickuser.journalapp.service.JournalEntryService;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        List<JournalEntry> allEntries = service.getAllEntries();
        return new ResponseEntity<>(allEntries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        Optional<JournalEntry> entry = service.findEntryById(id);
        if (entry.isPresent()) {
            return new ResponseEntity<JournalEntry>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

    @PostMapping
    public ResponseEntity<?> addEntry(@RequestBody JournalEntry entry) {
        try {
            service.saveEntry(entry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        Optional<JournalEntry> existingEntryOptional = service.findEntryById(id);
        if (existingEntryOptional.isPresent()) {
            JournalEntry existingEntry = existingEntryOptional.get();
            existingEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : existingEntry.getTitle());     
            existingEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : existingEntry.getContent());
            service.saveEntry(id, existingEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        service.deleteEntryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}
