package com.thetrickuser.journalapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thetrickuser.journalapp.model.JournalEntry;
import com.thetrickuser.journalapp.repository.JournalEntryRepository;

@Service
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository repository;

  public List<JournalEntry> getAllEntries() {
    return repository.findAll();
  }

  public JournalEntry addEntry(JournalEntry entry) {
    entry.setDate(LocalDateTime.now());
    return repository.save(entry);
  }

  public Optional<JournalEntry> findEntryById(ObjectId id) {
    return repository.findById(id);
  }

  public void deleteEntryById(ObjectId id) {
    repository.deleteById(id);
  }
}
