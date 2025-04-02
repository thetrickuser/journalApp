package com.thetrickuser.journalapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.thetrickuser.journalapp.model.JournalEntry;
import com.thetrickuser.journalapp.model.User;
import com.thetrickuser.journalapp.repository.JournalEntryRepository;
import com.thetrickuser.journalapp.repository.UserRepository;

@Service
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepo;

  @Autowired
  private UserRepository userRepository;

  public List<JournalEntry> getAllEntries() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getJournalEntries();
  }

  public void saveEntry(JournalEntry entry) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    entry.setDate(LocalDateTime.now());
    JournalEntry saved = journalEntryRepo.save(entry);
    user.getJournalEntries().add(saved);
    userRepository.save(user);
  }

  public void saveEntry(ObjectId id, JournalEntry entry) {
    journalEntryRepo.save(entry);
  }

  public Optional<JournalEntry> findEntryById(ObjectId id) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user.getJournalEntries().stream().filter(entry -> entry.getId().equals(id)).findFirst();
  }

  public void deleteEntryById(ObjectId id) {
    journalEntryRepo.deleteById(id);
  }
}
